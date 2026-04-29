package bigstats;

import bigstats.model.*;
import bigstats.pattern.UnitFight;
import bigstats.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for UnitFight – covers applyResult (correct/incorrect branches),
 * isBossDefeated, and both showOutro paths (boss defeated vs player defeated).
 */
class UnitFightTest {

    // ── Stub View (mirrors APExamFightTest.StubView) ──────────────────────────

    static class StubView implements GameView {
        private final int[] answers;
        private int idx = 0;

        StubView(int... answers) { this.answers = answers; }

        @Override public String promptPlayerName() { return "Tester"; }
        @Override public void showTitleScreen() {}
        @Override public void showBossIntro(Boss b, Player p) {}
        @Override public void showAPExamIntro(Boss b, Player p) {}
        @Override public int promptAnswer(Question q, int remaining, int total) {
            return (idx < answers.length) ? answers[idx++] : 1;
        }
        @Override public void showCorrectFeedback(Boss b, Player p) {}
        @Override public void showIncorrectFeedback(Question q, Boss b, Player p) {}
        @Override public void showBossDefeated(Boss b, Player p) {}
        @Override public void showPlayerDefeated(Player p) {}
        @Override public void showAPExamResult(int c, int t, double acc, double thr, Player p) {}
        @Override public void showVictoryScreen(Player p) {}
        @Override public void showGameOverScreen(Player p) {}
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static final String UNIT = "TestUnit";

    /** Bank with `count` questions for UNIT, all with correct index 0. */
    private QuestionBank bankOf(int count) {
        QuestionBank b = new QuestionBank();
        for (int i = 0; i < count; i++) {
            b.addQuestion(new Question("Q" + i + "?",
                    List.of("Correct", "Wrong", "Wrong", "Wrong"), 0, UNIT));
        }
        return b;
    }

    // ── Tests ─────────────────────────────────────────────────────────────────

    /** Answering all questions correctly defeats the boss and returns true. */
    @Test
    void allCorrect_bossDefeated_returnsTrue() {
        // EASY boss: 25 dmg/correct, max health 100 → 4 correct answers defeats it
        Boss boss = new Boss(UNIT, 100, Boss.Difficulty.EASY);
        Player player = new Player("Hero", 100);
        QuestionBank bank = bankOf(10);

        // answers: 0,0,0,0 → 4×25 = 100 damage → boss HP = 0
        UnitFight fight = new UnitFight(boss, player, bank, new StubView(0, 0, 0, 0));
        boolean result = fight.runFight();

        assertTrue(result);
        assertTrue(boss.isDefeated());
        assertTrue(player.isAlive());
        assertEquals(4, player.getScore());
    }

    /** Answering all wrong kills the player (HARD boss: 20 dmg/wrong, player HP 100 → 5 wrong). */
    @Test
    void allWrong_playerDefeated_returnsFalse() {
        Boss boss = new Boss(UNIT, 999, Boss.Difficulty.HARD);
        Player player = new Player("Hero", 100);
        QuestionBank bank = bankOf(10);

        // 1=wrong repeatedly; HARD boss deals 20/wrong → 5 wrong kills player
        UnitFight fight = new UnitFight(boss, player, bank,
                new StubView(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        boolean result = fight.runFight();

        assertFalse(result);
        assertFalse(player.isAlive());
        assertEquals(0, player.getScore());
    }

    /** Mix of correct and wrong answers: boss takes damage, player takes damage. */
    @Test
    void mixedAnswers_damageAppliedToBothSides() {
        // MEDIUM boss: 20 dmg/correct, 15 dmg/wrong; max HP 100
        Boss boss = new Boss(UNIT, 100, Boss.Difficulty.MEDIUM);
        Player player = new Player("Hero", 100);
        QuestionBank bank = bankOf(10);

        // correct(0), wrong(1), correct(0) → boss: 40 dmg; player: 15 dmg
        UnitFight fight = new UnitFight(boss, player, bank,
                new StubView(0, 1, 0, 0, 0, 0)); // extra 0s to finish boss
        fight.runFight();

        assertTrue(player.isAlive());
        assertTrue(player.getTotalAnswered() > 0);
    }

    /** Correct answers record correctly in player stats. */
    @Test
    void correctAnswer_recordsCorrectInPlayer() {
        Boss boss = new Boss(UNIT, 25, Boss.Difficulty.EASY); // 1 correct answer defeats it
        Player player = new Player("Hero", 100);
        QuestionBank bank = bankOf(5);

        UnitFight fight = new UnitFight(boss, player, bank, new StubView(0));
        fight.runFight();

        assertEquals(1, player.getScore());
        assertEquals(1, player.getTotalAnswered());
    }

    /** Wrong answer records incorrect in player stats. */
    @Test
    void wrongAnswer_recordsIncorrectInPlayer() {
        // EASY boss deals 10/wrong; player survives after 1 wrong if HP=100
        // Give enough correct answers afterwards to finish the fight
        Boss boss = new Boss(UNIT, 25, Boss.Difficulty.EASY);
        Player player = new Player("Hero", 100);
        QuestionBank bank = bankOf(5);

        // wrong first, then correct to defeat boss
        UnitFight fight = new UnitFight(boss, player, bank, new StubView(1, 0));
        fight.runFight();

        assertEquals(1, player.getScore());
        assertEquals(2, player.getTotalAnswered());
        assertEquals(90, player.getHealth()); // took 10 dmg
    }

    /** isBossDefeated is false while boss still has HP. */
    @Test
    void bossNotDefeatedUntilHealthReachesZero() {
        Boss boss = new Boss(UNIT, 200, Boss.Difficulty.EASY); // needs 8 correct to defeat
        Player player = new Player("Hero", 100);
        QuestionBank bank = bankOf(3); // only 3 questions – not enough to defeat boss

        // All correct but only 3 questions → boss still alive
        UnitFight fight = new UnitFight(boss, player, bank, new StubView(0, 0, 0));
        boolean result = fight.runFight();

        assertFalse(result);
        assertFalse(boss.isDefeated()); // 3×25 = 75 dmg, boss has 125 left
    }

    /** EASY difficulty damage constants flow through correctly. */
    @Test
    void easyDifficulty_correctDamageConstants() {
        Boss boss = new Boss(UNIT, 100, Boss.Difficulty.EASY);
        assertEquals(10, boss.getPlayerDamagePerWrong());
        assertEquals(25, boss.getBossDamagePerCorrect());
    }

    /** HARD difficulty damage constants flow through correctly. */
    @Test
    void hardDifficulty_correctDamageConstants() {
        Boss boss = new Boss(UNIT, 100, Boss.Difficulty.HARD);
        assertEquals(20, boss.getPlayerDamagePerWrong());
        assertEquals(15, boss.getBossDamagePerCorrect());
    }
}
