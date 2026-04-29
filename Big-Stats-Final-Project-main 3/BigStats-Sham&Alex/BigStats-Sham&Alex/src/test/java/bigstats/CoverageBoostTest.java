package bigstats;

import bigstats.model.*;
import bigstats.pattern.UnitFight;
import bigstats.view.GameView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoverageBoostTest {

    static class NullView implements GameView {
        private final int[] answers;
        private int idx = 0;
        NullView(int... answers) { this.answers = answers; }
        @Override public String promptPlayerName() { return "X"; }
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
        @Override public void showAPExamResult(int c, int t, double a, double th, Player p) {}
        @Override public void showVictoryScreen(Player p) {}
        @Override public void showGameOverScreen(Player p) {}
    }

    // ── GameState ─────────────────────────────────────────────────────────────

    @Test
    void gameState_getBosses_returnsList() {
        GameState state = new GameState(List.of(new Boss("U1", 100, Boss.Difficulty.EASY)));
        assertEquals(1, state.getBosses().size());
    }

    @Test
    void gameState_getBosses_isUnmodifiable() {
        GameState state = new GameState(List.of(new Boss("U", 50, Boss.Difficulty.EASY)));
        assertThrows(UnsupportedOperationException.class,
            () -> state.getBosses().add(new Boss("Extra", 50, Boss.Difficulty.EASY)));
    }

    @Test
    void gameState_getDefeatedBossCount_afterAdvancing() {
        GameState state = new GameState(List.of(
            new Boss("U1", 100, Boss.Difficulty.EASY),
            new Boss("U2", 100, Boss.Difficulty.EASY)
        ));
        state.advanceToNextBoss();
        assertEquals(1, state.getDefeatedBossCount());
    }

    @Test
    void gameState_isPlayerWon_falseInitially() {
        GameState state = new GameState(List.of(new Boss("U", 50, Boss.Difficulty.EASY)));
        assertFalse(state.isPlayerWon());
    }

    // ── QuestionBank ──────────────────────────────────────────────────────────

    @Test
    void questionBank_getAllQuestions_isUnmodifiable() {
        QuestionBank bank = new QuestionBank();
        bank.addQuestion(new Question("Q?", List.of("A", "B"), 0, "U"));
        assertThrows(UnsupportedOperationException.class,
            () -> bank.getAllQuestions().add(new Question("Q2?", List.of("A", "B"), 0, "U")));
    }

    // ── QuestionIterator ──────────────────────────────────────────────────────

    @Test
    void questionIterator_resetWithReshuffle_restoresAllQuestions() {
        List<Question> qs = List.of(
            new Question("Q1?", List.of("A", "B"), 0, "U"),
            new Question("Q2?", List.of("A", "B"), 0, "U"),
            new Question("Q3?", List.of("A", "B"), 0, "U")
        );
        QuestionIterator it = new QuestionIterator(qs, false);
        it.next(); it.next(); it.next();
        it.reset(true);
        assertEquals(3, it.remaining());
    }

    // ── UnitFight ─────────────────────────────────────────────────────────────

    @Test
    void unitFight_questionsRunOutBeforeBossDefeated_returnsFalse() {
        String unit = "TestUnit";
        QuestionBank bank = new QuestionBank();
        for (int i = 0; i < 2; i++)
            bank.addQuestion(new Question("Q?", List.of("Correct", "Wrong"), 0, unit));

        Boss boss = new Boss(unit, 200, Boss.Difficulty.EASY); // needs 8 correct, only 2 questions
        Player player = new Player("Hero", 100);

        assertFalse(new UnitFight(boss, player, bank, new NullView(0, 0)).runFight());
        assertTrue(player.isAlive());
        assertFalse(boss.isDefeated());
    }

    @Test
    void unitFight_bossDefeatedWithOneHit_returnsTrue() {
        String unit = "TestUnit2";
        QuestionBank bank = new QuestionBank();
        bank.addQuestion(new Question("Q?", List.of("Correct", "Wrong"), 0, unit));

        Boss boss = new Boss(unit, 1, Boss.Difficulty.EASY); // 1 HP, dies in 1 correct answer
        Player player = new Player("Hero", 100);

        assertTrue(new UnitFight(boss, player, bank, new NullView(0)).runFight());
    }
}
