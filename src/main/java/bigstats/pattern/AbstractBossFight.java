package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

/**
 * Template Method pattern – defines the skeleton of a boss fight.
 *
 * The overall algorithm is fixed here:
 *   1. loadQuestions()
 *   2. showIntro()
 *   3. loop: presentQuestion() → acceptAnswer() → applyResult() → checkBossDefeated()
 *   4. showOutro()
 *
 * Subclasses override the steps that differ per boss/stage.
 */
public abstract class AbstractBossFight {

    protected final Boss         boss;
    protected final Player       player;
    protected final QuestionBank questionBank;
    protected final GameView     view;

    protected QuestionIterator iterator;

    // fight-local tracking
    protected int correctThisFight  = 0;
    protected int answeredThisFight = 0;

    public AbstractBossFight(Boss boss, Player player, QuestionBank questionBank, GameView view) {
        this.boss         = boss;
        this.player       = player;
        this.questionBank = questionBank;
        this.view         = view;
    }

    // ── Template Method ───────────────────────────────────────────────────────

    /**
     * Run the complete boss fight. Returns true if the player survived and defeated/passed the boss.
     */
    public final boolean runFight() {
        loadQuestions();
        showIntro();

        while (iterator.hasNext() && player.isAlive() && !isBossDefeated()) {
            Question q      = iterator.next();
            int      answer = presentQuestion(q);
            boolean  correct = applyResult(q, answer);
            if (correct) {
                correctThisFight++;
            }
            answeredThisFight++;
            checkAfterAnswer();
        }

        showOutro();
        return player.isAlive() && isBossDefeated();
    }

    // ── Steps subclasses MUST implement ──────────────────────────────────────

    /** Build the QuestionIterator for this fight from the QuestionBank. */
    protected abstract void loadQuestions();

    /** Display an introduction for this boss. */
    protected abstract void showIntro();

    /** Present the question to the player and return their 0-based answer index. */
    protected abstract int presentQuestion(Question q);

    /**
     * Apply the result of the player's answer: deal damage, update player score, etc.
     * Returns true if the answer was correct.
     */
    protected boolean applyResult(Question q, int answerIndex) {
        boolean correct = q.isCorrect(answerIndex);
        if (correct) {
            boss.takeDamage(boss.getBossDamagePerCorrect());
            player.recordCorrect();
            view.showCorrectFeedback(boss, player);
        } else {
            player.takeDamage(boss.getPlayerDamagePerWrong());
            player.recordIncorrect();
            view.showIncorrectFeedback(q, boss, player);
        }
        return correct;
    }

    /** Hook – called after each answer; subclasses may override (default does nothing). */
    protected void checkAfterAnswer() { }

    /** Returns true when the boss should be considered defeated. */
    protected abstract boolean isBossDefeated();

    /** Display a closing summary for this fight. */
    protected abstract void showOutro();
}
