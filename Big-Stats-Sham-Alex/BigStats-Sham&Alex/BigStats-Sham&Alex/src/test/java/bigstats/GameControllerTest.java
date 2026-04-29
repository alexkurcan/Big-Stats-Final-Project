package bigstats;

import bigstats.controller.GameController;
import bigstats.model.*;
import bigstats.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for GameController – covers startGame paths:
 *   1. Player defeats all unit bosses then passes the AP Exam (victory)
 *   2. Player defeats all unit bosses then fails the AP Exam (game over)
 *   3. Player dies during a unit boss fight (early game over)
 */
class GameControllerTest {

    // ── Stub View ─────────────────────────────────────────────────────────────

    static class StubView implements GameView {
        private final int[] answers;
        private int idx = 0;
        String lastPlayerName = "Hero";

        StubView(int... answers) { this.answers = answers; }

        @Override public String promptPlayerName() { return lastPlayerName; }
        @Override public void showTitleScreen() {}
        @Override public void showBossIntro(Boss b, Player p) {}
        @Override public void showAPExamIntro(Boss b, Player p) {}
        @Override public int promptAnswer(Question q, int remaining, int total) {
            return (idx < answers.length) ? answers[idx++] : 0;
        }
        @Override public void showCorrectFeedback(Boss b, Player p) {}
        @Override public void showIncorrectFeedback(Question q, Boss b, Player p) {}
        @Override public void showBossDefeated(Boss b, Player p) {}
        @Override public void showPlayerDefeated(Player p) {}
        @Override public void showAPExamResult(int c, int t, double acc, double thr, Player p) {}
        @Override public void showVictoryScreen(Player p) {}
        @Override public void showGameOverScreen(Player p) {}
    }

    // ── Fixtures ──────────────────────────────────────────────────────────────

    /**
     * Builds a QuestionBank with one unit whose name matches the given boss,
     * plus a catch-all "AP Exam" bank drawing from all questions.
     * All questions have correct index 0.
     */
    private QuestionBank buildSimpleBank(String unitName) {
        QuestionBank bank = new QuestionBank();
        for (int i = 0; i < 10; i++) {
            bank.addQuestion(new Question("Q" + i + "?",
                    List.of("Correct", "Wrong", "Wrong", "Wrong"), 0, unitName));
        }
        return bank;
    }

    // ── Tests ─────────────────────────────────────────────────────────────────

    /**
     * Full victory path: single EASY unit boss (HP=25, defeated in 1 correct answer),
     * then AP exam with all-correct answers → VICTORY.
     */
    @Test
    void startGame_victoryPath_stateIsVictory() {
        String unit = QuestionFactory.UNIT_1;
        QuestionBank bank = QuestionFactory.buildBank();

        // Need to defeat unit boss (EASY, HP 25 → 1 correct) then ace AP exam (60 questions, all correct)
        // Boss HP=25, EASY dmg=25/correct → 1 correct defeats it
        // AP exam: 60 questions, all correct → 100% ≥ 75%
        int[] answers = new int[1 + 60]; // 1 for unit boss, 60 for AP exam, all 0 = correct
        Boss unitBoss = new Boss(unit, 25, Boss.Difficulty.EASY);

        StubView view = new StubView(answers);
        GameController controller = new GameController(view, bank);
        controller.startGame(List.of(unitBoss));

        assertNotNull(controller.getPlayer());
        assertNotNull(controller.getGameState());
        assertTrue(controller.getGameState().isVictory());
        assertFalse(controller.getGameState().isGameOver());
    }

    /**
     * Fail AP exam path: player defeats the unit boss, then fails the AP exam → GAME_OVER.
     * AP exam needs ≥75%; answering all wrong gives 0% → game over.
     */
    @Test
    void startGame_failAPExam_stateIsGameOver() {
        String unit = QuestionFactory.UNIT_1;
        QuestionBank bank = QuestionFactory.buildBank();

        // Defeat unit boss with 1 correct (index 0), then answer AP exam all wrong (index 1)
        int[] answers = new int[1 + 60];
        answers[0] = 0; // correct → defeats EASY boss with HP=25
        for (int i = 1; i <= 60; i++) answers[i] = 1; // all wrong on AP exam

        Boss unitBoss = new Boss(unit, 25, Boss.Difficulty.EASY);
        StubView view = new StubView(answers);
        GameController controller = new GameController(view, bank);
        controller.startGame(List.of(unitBoss));

        assertTrue(controller.getGameState().isGameOver());
        assertFalse(controller.getGameState().isVictory());
    }

    /**
     * Player dies during unit boss fight → game over before AP exam.
     */
    @Test
    void startGame_playerDiesDuringUnitFight_stateIsGameOver() {
        String unit = QuestionFactory.UNIT_1;
        QuestionBank bank = QuestionFactory.buildBank();

        // HARD boss (20 dmg/wrong). Player has 100 HP → 5 wrong answers = dead.
        // All answers wrong (index 1).
        int[] answers = new int[10];
        for (int i = 0; i < 10; i++) answers[i] = 1;

        Boss hardBoss = new Boss(unit, 9999, Boss.Difficulty.HARD);
        StubView view = new StubView(answers);
        GameController controller = new GameController(view, bank);
        controller.startGame(List.of(hardBoss));

        assertTrue(controller.getGameState().isGameOver());
        assertFalse(controller.getPlayer().isAlive());
    }

    /**
     * getPlayer() and getGameState() return non-null after startGame.
     */
    @Test
    void getters_returnCorrectObjectsAfterGame() {
        QuestionBank bank = QuestionFactory.buildBank();
        Boss unitBoss = new Boss(QuestionFactory.UNIT_1, 25, Boss.Difficulty.EASY);
        int[] answers = new int[61]; // all correct
        StubView view = new StubView(answers);
        GameController controller = new GameController(view, bank);
        controller.startGame(List.of(unitBoss));

        assertNotNull(controller.getPlayer());
        assertEquals("Hero", controller.getPlayer().getName());
        assertNotNull(controller.getGameState());
    }

    /**
     * Player defeats multiple unit bosses, then wins AP exam.
     */
    @Test
    void startGame_multipleUnitBosses_thenVictory() {
        QuestionBank bank = QuestionFactory.buildBank();

        // Two unit bosses, both EASY with HP=25 (1 correct each to defeat)
        // Then 60-question AP exam all correct
        int[] answers = new int[2 + 60]; // 2 boss kills + 60 AP exam
        Boss boss1 = new Boss(QuestionFactory.UNIT_1, 25, Boss.Difficulty.EASY);
        Boss boss2 = new Boss(QuestionFactory.UNIT_2, 25, Boss.Difficulty.EASY);

        StubView view = new StubView(answers);
        GameController controller = new GameController(view, bank);
        controller.startGame(List.of(boss1, boss2));

        assertTrue(controller.getGameState().isVictory());
    }

    /**
     * GameState tracks defeated bosses correctly across the fight.
     */
    @Test
    void gameState_tracksBossProgression() {
        QuestionBank bank = QuestionFactory.buildBank();
        Boss boss1 = new Boss(QuestionFactory.UNIT_1, 25, Boss.Difficulty.EASY);
        Boss boss2 = new Boss(QuestionFactory.UNIT_2, 25, Boss.Difficulty.EASY);

        // Defeat both bosses, fail AP exam
        int[] answers = new int[2 + 60];
        for (int i = 2; i < answers.length; i++) answers[i] = 1; // fail AP exam

        StubView view = new StubView(answers);
        GameController controller = new GameController(view, bank);
        controller.startGame(List.of(boss1, boss2));

        assertEquals(2, controller.getGameState().getTotalBosses());
    }
}
