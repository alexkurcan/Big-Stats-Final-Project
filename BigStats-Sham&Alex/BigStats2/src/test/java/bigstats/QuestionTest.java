package bigstats;

import bigstats.model.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question makeQ() {
        return new Question("What is 2+2?",
                List.of("3", "4", "5", "6"), 1, "Math");
    }

    @Test
    void constructor_setsFields() {
        Question q = makeQ();
        assertEquals("What is 2+2?", q.getPrompt());
        assertEquals(4, q.getChoices().size());
        assertEquals(1, q.getCorrectIndex());
        assertEquals("Math", q.getUnitTag());
    }

    @Test
    void isCorrect_trueForCorrectIndex() {
        assertTrue(makeQ().isCorrect(1));
    }

    @Test
    void isCorrect_falseForWrongIndex() {
        assertFalse(makeQ().isCorrect(0));
        assertFalse(makeQ().isCorrect(2));
        assertFalse(makeQ().isCorrect(3));
    }

    @Test
    void choicesAreImmutable() {
        Question q = makeQ();
        assertThrows(UnsupportedOperationException.class,
                () -> q.getChoices().add("7"));
    }

    @Test
    void constructor_tooFewChoices_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Question("Q?", List.of("only one"), 0, "Unit"));
    }

    @Test
    void constructor_correctIndexOutOfRange_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Question("Q?", List.of("A", "B"), 5, "Unit"));
        assertThrows(IllegalArgumentException.class,
                () -> new Question("Q?", List.of("A", "B"), -1, "Unit"));
    }

    @Test
    void toString_containsPromptAndChoices() {
        String s = makeQ().toString();
        assertTrue(s.contains("What is 2+2?"));
        assertTrue(s.contains("4"));  // the correct answer text
    }
}
