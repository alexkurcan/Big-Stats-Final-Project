package bigstats.model;

import java.util.*;

public class QuestionIterator implements Iterator<Question> {
    private final List<Question> questions;
    private int index = 0;

    public QuestionIterator(List<Question> questions, boolean shuffle) {
        this.questions = new ArrayList<>(questions);
        if (shuffle) Collections.shuffle(this.questions);
    }

    @Override public boolean  hasNext()   { return index < questions.size(); }
    @Override public Question next()      {
        if (!hasNext()) throw new NoSuchElementException("No more questions.");
        return questions.get(index++);
    }
    public int  remaining()              { return questions.size() - index; }
    public int  total()                  { return questions.size(); }
    public void reset(boolean reshuffle) { index = 0; if (reshuffle) Collections.shuffle(questions); }
}
