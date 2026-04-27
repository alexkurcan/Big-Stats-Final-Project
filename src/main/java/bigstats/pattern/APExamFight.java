package bigstats.pattern;

import bigstats.model.*;
import bigstats.view.GameView;

/**
 * Concrete fight for the AP Exam final boss.
 * Questions are drawn from ALL units in randomized order.
 * Win condition: answer >= 75% correctly.
 */
public class APExamFight extends AbstractBossFight {

    public static final double WIN_THRESHOLD = 0.75;

    // exam-local counters (separate from the overall player score so we can compute exam accuracy)
    private int examCorrect  = 0;
    private int examAnswered = 0;

    public APExamFight(Boss boss, Player player, QuestionBank questionBank, GameView view) {
        super(boss, player, questionBank, view);
    }

    @Override
    protected void loadQuestions() {
        // AP Exam draws from ALL units, shuffled
        iterator = new QuestionIterator(questionBank.getAllQuestions(), true);
    }

    @Override
    protected void showIntro() {
        view.showAPExamIntro(boss, player);
    }

    @Override
    protected int presentQuestion(Question q) {
        return view.promptAnswer(q, iterator.remaining(), iterator.total());
    }

    @Override
    protected boolean applyResult(Question q, int answerIndex) {
        boolean correct = q.isCorrect(answerIndex);
        examAnswered++;
        if (correct) {
            examCorrect++;
            player.recordCorrect();
            view.showCorrectFeedback(boss, player);
        } else {
            player.recordIncorrect();
            view.showIncorrectFeedback(q, boss, player);
        }
        return correct;
    }

    @Override
    protected boolean isBossDefeated() {
        // The exam "boss" is defeated when the player finishes all questions with >= 75%
        // During iteration this is checked AFTER the loop ends.
        return !iterator.hasNext() && getExamAccuracy() >= WIN_THRESHOLD;
    }

    /** Exam accuracy based only on this exam session. */
    public double getExamAccuracy() {
        if (examAnswered == 0) return 0.0;
        return (double) examCorrect / examAnswered;
    }

    public int getExamCorrect()   { return examCorrect; }
    public int getExamAnswered()  { return examAnswered; }

    @Override
    protected void showOutro() {
        view.showAPExamResult(examCorrect, examAnswered, getExamAccuracy(), WIN_THRESHOLD, player);
    }
}
