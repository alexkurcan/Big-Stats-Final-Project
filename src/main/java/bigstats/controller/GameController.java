package bigstats.controller;

import bigstats.model.*;
import bigstats.pattern.*;
import bigstats.view.GameView;

import java.util.List;

/**
 * Controller – ties together Model, View, and the fight patterns.
 * Manages the game loop and stage transitions.
 */
public class GameController {

    private final GameView     view;
    private final QuestionBank questionBank;
    private       Player       player;
    private       GameState    gameState;

    public GameController(GameView view, QuestionBank questionBank) {
        this.view         = view;
        this.questionBank = questionBank;
    }

    // ── public entry point ────────────────────────────────────────────────────

    public void startGame(List<Boss> bosses) {
        view.showTitleScreen();

        String name = view.promptPlayerName();
        player    = new Player(name);
        gameState = new GameState(bosses);

        runGameLoop();
    }

    // ── game loop ─────────────────────────────────────────────────────────────

    private void runGameLoop() {
        // ── Unit boss phase ───────────────────────────────────────────────────
        while (gameState.getStage() == GameState.Stage.UNIT_BOSS) {
            Boss currentBoss = gameState.getCurrentBoss();

            UnitFight fight = new UnitFight(currentBoss, player, questionBank, view);
            boolean   won   = fight.runFight();

            if (!player.isAlive()) {
                gameState.setGameOver();
                break;
            }

            if (won) {
                // Heal a small amount between bosses as a reward
                player.heal(20);
                gameState.advanceToNextBoss();
            }
            // If player survived but boss ran out of questions without dying → still advance
            else if (!currentBoss.isDefeated()) {
                gameState.advanceToNextBoss();
            }
        }

        if (gameState.isGameOver()) {
            view.showGameOverScreen(player);
            return;
        }

        // ── AP Exam phase ─────────────────────────────────────────────────────
        if (gameState.getStage() == GameState.Stage.AP_EXAM) {
            // Build a special "AP Exam" boss
            Boss apExamBoss = new Boss("AP Statistics Exam", 1000, Boss.Difficulty.HARD);
            APExamFight examFight = new APExamFight(apExamBoss, player, questionBank, view);
            examFight.runFight();

            double accuracy = examFight.getExamAccuracy();
            if (accuracy >= APExamFight.WIN_THRESHOLD) {
                gameState.setVictory();
                view.showVictoryScreen(player);
            } else {
                gameState.setGameOver();
                view.showGameOverScreen(player);
            }
        }
    }

    // ── accessors (for testing) ───────────────────────────────────────────────

    public Player    getPlayer()    { return player; }
    public GameState getGameState() { return gameState; }
}
