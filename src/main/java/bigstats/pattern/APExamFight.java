package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

/** Concrete fight for the AP Exam final boss. Win condition: ≥ 75% correct. */
public class APExamFight extends AbstractBossFight {

    public static final double WIN_THRESHOLD = 0.75;

    private int examCorrect  = 0;
    private int examAnswered = 0;

    public APExamFight(Boss boss, Player player, QuestionBank questionBank, GameView view) {
        super(boss, player, questionBank, view);
    }

    @Override
    protected void loadQuestions() {
        iterator = new QuestionIterator(questionBank.getAllQuestions(), true); // shuffled
    }

    @Override protected void showIntro() { view.showAPExamIntro(boss, player); }

    @Override
    protected int presentQuestion(Question q) {
        return view.promptAnswer(q, iterator.remaining(), iterator.total());
    }

    @Override
    protected boolean applyResult(Question q, int answer) {
        examAnswered++;
        if (q.isCorrect(answer)) {
            examCorrect++;
            player.recordCorrect();
            view.showCorrectFeedback(boss, player);
            return true;
        } else {
            player.recordIncorrect();
            view.showIncorrectFeedback(q, boss, player);
            return false;
        }
    }

    @Override
    protected boolean isBossDefeated() {
        return !iterator.hasNext() && getExamAccuracy() >= WIN_THRESHOLD;
    }

    @Override
    protected void showOutro() {
        view.showAPExamResult(examCorrect, examAnswered, getExamAccuracy(), WIN_THRESHOLD, player);
    }

    public double getExamAccuracy() {
        return examAnswered == 0 ? 0.0 : (double) examCorrect / examAnswered;
    }

    public int getExamCorrect()  { return examCorrect; }
    public int getExamAnswered() { return examAnswered; }
}
