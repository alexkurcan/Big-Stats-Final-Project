package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

/**
 * Concrete boss fight for a standard AP Stats unit boss.
 * Questions come only from the boss's own unit tag.
 */
public class UnitFight extends AbstractBossFight {

    public UnitFight(Boss boss, Player player, QuestionBank questionBank, GameView view) {
        super(boss, player, questionBank, view);
    }

    @Override
    protected void loadQuestions() {
        // Load only the questions for this unit (no shuffle for unit bosses)
        iterator = new QuestionIterator(questionBank.getQuestionsForUnit(boss.getUnitName()), false);
    }

    @Override
    protected void showIntro() {
        view.showBossIntro(boss, player);
    }

    @Override
    protected int presentQuestion(Question q) {
        return view.promptAnswer(q, iterator.remaining(), iterator.total());
    }

    @Override
    protected boolean isBossDefeated() {
        return boss.isDefeated();
    }

    @Override
    protected void showOutro() {
        if (boss.isDefeated()) {
            view.showBossDefeated(boss, player);
        } else if (!player.isAlive()) {
            view.showPlayerDefeated(player);
        } else {
            // Ran out of questions but boss not dead – still counts as a unit win
            view.showBossDefeated(boss, player);
        }
    }
}
