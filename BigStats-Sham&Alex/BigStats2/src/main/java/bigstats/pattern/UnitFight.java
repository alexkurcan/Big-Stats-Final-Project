package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

public class UnitFight extends AbstractBossFight {
    public UnitFight(Boss boss, Player player, QuestionBank qb, GameView view) {
        super(boss, player, qb, view);
    }

    @Override protected void    loadQuestions()  { iterator = new QuestionIterator(questionBank.getQuestionsForUnit(boss.getUnitName()), false); }
    @Override protected void    showIntro()       { view.showBossIntro(boss, player); }
    @Override protected boolean isBossDefeated()  { return boss.isDefeated(); }
    @Override protected void    showOutro()       {
        if (player.isAlive()) view.showBossDefeated(boss, player);
        else                  view.showPlayerDefeated(player);
    }

    @Override
    protected void applyResult(Question q, int answer) {
        if (q.isCorrect(answer)) { boss.takeDamage(boss.getBossDamagePerCorrect());   player.recordCorrect();   view.showCorrectFeedback(boss, player); }
        else                     { player.takeDamage(boss.getPlayerDamagePerWrong()); player.recordIncorrect(); view.showIncorrectFeedback(q, boss, player); }
    }
}
