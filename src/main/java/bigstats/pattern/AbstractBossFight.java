package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

/**
 * Template Method pattern – defines the skeleton of every boss fight.
 * Subclasses implement the steps that differ per stage.
 */
public abstract class AbstractBossFight {
    protected final Boss boss;
    protected final Player player;
    protected final QuestionBank questionBank;
    protected final GameView view;
    protected QuestionIterator iterator;

    public AbstractBossFight(Boss boss, Player player, QuestionBank qb, GameView view) {
        this.boss = boss; this.player = player; this.questionBank = qb; this.view = view;
    }

    /** Runs the full fight; returns true if the player passed. */
    public final boolean runFight() {
        loadQuestions();
        showIntro();
        while (iterator.hasNext() && player.isAlive() && !isBossDefeated()) {
            Question q = iterator.next();
            applyResult(q, view.promptAnswer(q, iterator.remaining(), iterator.total()));
        }
        showOutro();
        return player.isAlive() && isBossDefeated();
    }

    protected abstract void    loadQuestions();
    protected abstract void    showIntro();
    protected abstract void    applyResult(Question q, int answer);
    protected abstract boolean isBossDefeated();
    protected abstract void    showOutro();
}
