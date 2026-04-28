package bigstats.controller;

import bigstats.model.*;
import bigstats.pattern.*;
import bigstats.view.GameView;
import java.util.List;

public class GameController {
    private final GameView view;
    private final QuestionBank questionBank;
    private Player player;
    private GameState gameState;

    public GameController(GameView view, QuestionBank qb) { this.view = view; this.questionBank = qb; }

    public void startGame(List<Boss> bosses) {
        view.showTitleScreen();
        player    = new Player(view.promptPlayerName());
        gameState = new GameState(bosses);

        // Unit boss phase
        while (gameState.getStage() == GameState.Stage.UNIT_BOSS) {
            new UnitFight(gameState.getCurrentBoss(), player, questionBank, view).runFight();
            if (!player.isAlive()) { gameState.setGameOver(); break; }
            player.heal(20);
            gameState.advanceToNextBoss();
        }

        // AP Exam phase
        if (gameState.getStage() == GameState.Stage.AP_EXAM) {
            APExamFight exam = new APExamFight(new Boss("AP Exam", 1, Boss.Difficulty.HARD), player, questionBank, view);
            exam.runFight();
            if (exam.getExamAccuracy() >= APExamFight.WIN_THRESHOLD) { gameState.setVictory();  view.showVictoryScreen(player); }
            else                                                      { gameState.setGameOver(); view.showGameOverScreen(player); }
        } else if (gameState.isGameOver()) {
            view.showGameOverScreen(player);
        }
    }

    public Player    getPlayer()    { return player; }
    public GameState getGameState() { return gameState; }
}
