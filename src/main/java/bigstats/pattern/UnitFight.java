package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

/** Concrete fight for a standard AP Stats unit boss. */
public class UnitFight extends AbstractBossFight {

    public UnitFight(Boss boss, Player player, QuestionBank questionBank, GameView view) {
        super(boss, player, questionBank, view);
    }

    @Override
    protected void loadQuestions() {
        iterator = new QuestionIterator(questionBank.getQuestionsForUnit(boss.getUnitName()), false);
    }

    @Override protected void showIntro() { view.showBossIntro(boss, player); }

    @Override
    protected int presentQuestion(Question q) {
        return view.promptAnswer(q, iterator.remaining(), iterator.total());
    }

    @Override protected boolean isBossDefeated() { return boss.isDefeated(); }

    @Override
    protected void showOutro() {
        if (player.isAlive()) view.showBossDefeated(boss, player);
        else                  view.showPlayerDefeated(player);
    }
}
