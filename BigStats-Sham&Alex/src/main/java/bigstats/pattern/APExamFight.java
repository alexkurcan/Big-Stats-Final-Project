package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

public class APExamFight extends AbstractBossFight {
    public static final double WIN_THRESHOLD = 0.75;
    private int examCorrect = 0, examAnswered = 0;

    public APExamFight(Boss boss, Player player, QuestionBank qb, GameView view) {
        super(boss, player, qb, view);
    }

    @Override protected void    loadQuestions() { iterator = new QuestionIterator(questionBank.getAllQuestions(), true); }
    @Override protected void    showIntro()      { view.showAPExamIntro(boss, player); }
    @Override protected boolean isBossDefeated() { return !iterator.hasNext() && getExamAccuracy() >= WIN_THRESHOLD; }
    @Override protected void    showOutro()      { view.showAPExamResult(examCorrect, examAnswered, getExamAccuracy(), WIN_THRESHOLD, player); }

    @Override
    protected void applyResult(Question q, int answer) {
        examAnswered++;
        if (q.isCorrect(answer)) { examCorrect++; player.recordCorrect();   view.showCorrectFeedback(boss, player); }
        else                     {                player.recordIncorrect(); view.showIncorrectFeedback(q, boss, player); }
    }

    public double getExamAccuracy() { return examAnswered == 0 ? 0.0 : (double) examCorrect / examAnswered; }
    public int    getExamCorrect()  { return examCorrect; }
    public int    getExamAnswered() { return examAnswered; }
}
