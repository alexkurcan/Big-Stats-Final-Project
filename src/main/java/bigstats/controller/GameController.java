package bigstats.controller;

import bigstats.model.*;
import bigstats.pattern.*;
import bigstats.view.GameView;

import java.util.List;

public class GameController {

    private final GameView     view;
    private final QuestionBank questionBank;
    private       Player       player;
    private       GameState    gameState;

    public GameController(GameView view, QuestionBank questionBank) {
        this.view         = view;
        this.questionBank = questionBank;
    }

    public void startGame(List<Boss> bosses) {
        view.showTitleScreen();
        player    = new Player(view.promptPlayerName());
        gameState = new GameState(bosses);
        runGameLoop();
    }

    private void runGameLoop() {
        // Unit boss phase
        while (gameState.getStage() == GameState.Stage.UNIT_BOSS) {
            new UnitFight(gameState.getCurrentBoss(), player, questionBank, view).runFight();
            if (!player.isAlive()) { gameState.setGameOver(); break; }
            player.heal(20);
            gameState.advanceToNextBoss();
        }

        if (gameState.isGameOver()) { view.showGameOverScreen(player); return; }

        // AP Exam phase
        if (gameState.getStage() == GameState.Stage.AP_EXAM) {
            Boss apBoss = new Boss("AP Statistics Exam", 1000, Boss.Difficulty.HARD);
            APExamFight exam = new APExamFight(apBoss, player, questionBank, view);
            exam.runFight();

            if (exam.getExamAccuracy() >= APExamFight.WIN_THRESHOLD) {
                gameState.setVictory();
                view.showVictoryScreen(player);
            } else {
                gameState.setGameOver();
                view.showGameOverScreen(player);
            }
        }
    }

    public Player    getPlayer()    { return player; }
    public GameState getGameState() { return gameState; }
}
