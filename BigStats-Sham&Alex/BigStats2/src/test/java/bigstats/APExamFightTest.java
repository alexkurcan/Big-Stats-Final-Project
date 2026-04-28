package bigstats;

import bigstats.model.*;
import bigstats.pattern.APExamFight;
import bigstats.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the AP Exam fight win/loss boundary (75% threshold).
 * Uses a stub GameView so no console I/O is needed.
 */
class APExamFightTest {

    // ── Stub View ──────────────────────────────────────────────────────────────

    /**
     * A stub GameView that returns a preset sequence of answer indices.
     */
    static class StubView implements GameView {
        private final int[] answers;
        private int idx = 0;

        StubView(int... answers) { this.answers = answers; }

        @Override public String promptPlayerName() { return "Tester"; }
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

    // ── setup ─────────────────────────────────────────────────────────────────

    private QuestionBank bank;
    private Boss         examBoss;
    private Player       player;

    /** Build a bank with exactly 4 questions, all in "TestUnit", all with correct index 0. */
    @BeforeEach
    void setUp() {
        bank = new QuestionBank();
        for (int i = 0; i < 4; i++) {
            bank.addQuestion(new Question(
                "Q" + i + "?", List.of("Correct", "Wrong", "Wrong", "Wrong"), 0, "TestUnit"
            ));
        }
        examBoss = new Boss("AP Exam", 1000, Boss.Difficulty.HARD);
        player   = new Player("Tester", 100);
    }

    // ── tests ─────────────────────────────────────────────────────────────────

    @Test
    void exactlyAt75Percent_isPass() {
        // 4 questions, answer 3 correctly (75%) → should PASS
        // indices: 0=correct, 0=correct, 0=correct, 1=wrong
        StubView view = new StubView(0, 0, 0, 1);
        APExamFight fight = new APExamFight(examBoss, player, bank, view);
        fight.runFight();

        assertEquals(3, fight.getExamCorrect());
        assertEquals(4, fight.getExamAnswered());
        assertEquals(0.75, fight.getExamAccuracy(), 0.001);
        assertTrue(fight.getExamAccuracy() >= APExamFight.WIN_THRESHOLD);
    }

    @Test
    void below75Percent_isFail() {
        // 4 questions, answer 2 correctly (50%) → should FAIL
        StubView view = new StubView(0, 0, 1, 1);
        APExamFight fight = new APExamFight(examBoss, player, bank, view);
        fight.runFight();

        assertEquals(2, fight.getExamCorrect());
        assertEquals(4, fight.getExamAnswered());
        assertEquals(0.5, fight.getExamAccuracy(), 0.001);
        assertFalse(fight.getExamAccuracy() >= APExamFight.WIN_THRESHOLD);
    }

    @Test
    void allCorrect_isPass() {
        StubView view = new StubView(0, 0, 0, 0);
        APExamFight fight = new APExamFight(examBoss, player, bank, view);
        fight.runFight();

        assertEquals(4, fight.getExamCorrect());
        assertEquals(1.0, fight.getExamAccuracy(), 0.001);
        assertTrue(fight.getExamAccuracy() >= APExamFight.WIN_THRESHOLD);
    }

    @Test
    void allWrong_isFail() {
        StubView view = new StubView(1, 1, 1, 1);
        APExamFight fight = new APExamFight(examBoss, player, bank, view);
        fight.runFight();

        assertEquals(0, fight.getExamCorrect());
        assertEquals(0.0, fight.getExamAccuracy(), 0.001);
        assertFalse(fight.getExamAccuracy() >= APExamFight.WIN_THRESHOLD);
    }

    @Test
    void examAccuracy_zeroQuestions_returnsZero() {
        APExamFight fight = new APExamFight(examBoss, player, bank, new StubView());
        assertEquals(0.0, fight.getExamAccuracy());
    }
}
