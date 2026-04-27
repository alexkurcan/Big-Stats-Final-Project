package bigstats.model;

import java.util.*;

/**
 * Iterator pattern implementation for traversing a sequence of Questions.
 * The underlying list may be shuffled (for the AP Exam stage) or ordered (for unit bosses).
 */
public class QuestionIterator implements Iterator<Question> {

    private final List<Question> questions;
    private int currentIndex = 0;

    /**
     * @param questions source list – a defensive copy is made so external changes don't affect iteration.
     * @param shuffle   if true the questions are presented in a random order.
     */
    public QuestionIterator(List<Question> questions, boolean shuffle) {
        this.questions = new ArrayList<>(questions);
        if (shuffle) {
            Collections.shuffle(this.questions);
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < questions.size();
    }

    @Override
    public Question next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more questions in this iterator.");
        }
        return questions.get(currentIndex++);
    }

    /** How many questions remain (not yet returned by next()). */
    public int remaining() {
        return questions.size() - currentIndex;
    }

    /** Total number of questions managed by this iterator (regardless of position). */
    public int total() {
        return questions.size();
    }

    /** Resets the iterator to the beginning (order preserved / re-shuffled if desired). */
    public void reset(boolean reshuffle) {
        currentIndex = 0;
        if (reshuffle) {
            Collections.shuffle(questions);
        }
    }
}
