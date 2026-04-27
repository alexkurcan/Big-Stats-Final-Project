package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

/**
 * Template Method pattern – defines the skeleton of every boss fight.
 *
 * Algorithm (fixed here):
 *   1. loadQuestions()
 *   2. showIntro()
 *   3. loop: presentQuestion → applyResult → checkAfterAnswer
 *   4. showOutro()
 *
 * Subclasses override the steps that differ per stage.
 */
public abstract class AbstractBossFight {

    protected final Boss         boss;
    protected final Player       player;
    protected final QuestionBank questionBank;
    protected final GameView     view;
    protected QuestionIterator   iterator;

    public AbstractBossFight(Boss boss, Player player, QuestionBank questionBank, GameView view) {
        this.boss         = boss;
        this.player       = player;
        this.questionBank = questionBank;
        this.view         = view;
    }

    /** Run the fight. Returns true if the player passed this stage. */
    public final boolean runFight() {
        loadQuestions();
        showIntro();
        while (iterator.hasNext() && player.isAlive() && !isBossDefeated()) {
            Question q = iterator.next();
            applyResult(q, presentQuestion(q));
            checkAfterAnswer();
        }
        showOutro();
        return player.isAlive() && isBossDefeated();
    }

    // Steps subclasses must implement
    protected abstract void    loadQuestions();
    protected abstract void    showIntro();
    protected abstract int     presentQuestion(Question q);
    protected abstract boolean isBossDefeated();
    protected abstract void    showOutro();

    /** Apply the answer; deal damage and update the player. Returns true if correct. */
    protected boolean applyResult(Question q, int answer) {
        if (q.isCorrect(answer)) {
            boss.takeDamage(boss.getBossDamagePerCorrect());
            player.recordCorrect();
            view.showCorrectFeedback(boss, player);
            return true;
        } else {
            player.takeDamage(boss.getPlayerDamagePerWrong());
            player.recordIncorrect();
            view.showIncorrectFeedback(q, boss, player);
            return false;
        }
    }

    /** Hook – subclasses may override (default: no-op). */
    protected void checkAfterAnswer() {}
}
