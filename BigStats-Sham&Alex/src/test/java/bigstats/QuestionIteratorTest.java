package bigstats;

import bigstats.model.Question;
import bigstats.model.QuestionIterator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QuestionIteratorTest {

    private List<Question> makeQuestions(int count) {
        List<Question> list = new java.util.ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Question("Q" + i + "?", List.of("A", "B"), 0, "TestUnit"));
        }
        return list;
    }

    @Test
    void hasNext_trueWhenQuestionsRemain() {
        QuestionIterator it = new QuestionIterator(makeQuestions(3), false);
        assertTrue(it.hasNext());
    }

    @Test
    void hasNext_falseWhenExhausted() {
        QuestionIterator it = new QuestionIterator(makeQuestions(1), false);
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    void next_returnsQuestionsInOrder_whenNoShuffle() {
        List<Question> questions = makeQuestions(3);
        QuestionIterator it = new QuestionIterator(questions, false);
        assertEquals(questions.get(0).getPrompt(), it.next().getPrompt());
        assertEquals(questions.get(1).getPrompt(), it.next().getPrompt());
        assertEquals(questions.get(2).getPrompt(), it.next().getPrompt());
    }

    @Test
    void next_throwsWhenExhausted() {
        QuestionIterator it = new QuestionIterator(makeQuestions(1), false);
        it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void remaining_decreasesWithEachNext() {
        QuestionIterator it = new QuestionIterator(makeQuestions(4), false);
        assertEquals(4, it.remaining());
        it.next();
        assertEquals(3, it.remaining());
        it.next();
        assertEquals(2, it.remaining());
    }

    @Test
    void total_alwaysReturnsTotalCount() {
        QuestionIterator it = new QuestionIterator(makeQuestions(5), false);
        it.next();
        it.next();
        assertEquals(5, it.total());
    }

    @Test
    void reset_restoresIteration() {
        QuestionIterator it = new QuestionIterator(makeQuestions(3), false);
        it.next(); it.next(); it.next();
        assertFalse(it.hasNext());
        it.reset(false);
        assertTrue(it.hasNext());
        assertEquals(3, it.remaining());
    }

    @Test
    void shuffle_doesNotChangeTotalCount() {
        QuestionIterator it = new QuestionIterator(makeQuestions(10), true);
        assertEquals(10, it.total());
        assertEquals(10, it.remaining());
    }

    @Test
    void externalListChanges_doNotAffectIterator() {
        List<Question> source = new java.util.ArrayList<>(makeQuestions(3));
        QuestionIterator it = new QuestionIterator(source, false);
        source.clear(); // modify source after creating iterator
        assertEquals(3, it.total()); // iterator should still have 3
    }
}
