package bigstats;

import bigstats.model.Question;
import bigstats.model.QuestionBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionBankTest {

    private QuestionBank bank;

    private Question makeQ(String unit) {
        return new Question("Prompt?", List.of("A", "B", "C", "D"), 0, unit);
    }

    @BeforeEach
    void setUp() {
        bank = new QuestionBank();
    }

    @Test
    void addAndRetrieveQuestion() {
        bank.addQuestion(makeQ("Unit1"));
        List<Question> result = bank.getQuestionsForUnit("Unit1");
        assertEquals(1, result.size());
    }

    @Test
    void getQuestionsForUnit_unknownUnit_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> bank.getQuestionsForUnit("NonexistentUnit"));
    }

    @Test
    void getQuestionsForUnit_returnsUnmodifiable() {
        bank.addQuestion(makeQ("Unit1"));
        List<Question> list = bank.getQuestionsForUnit("Unit1");
        assertThrows(UnsupportedOperationException.class, () -> list.add(makeQ("Unit1")));
    }

    @Test
    void getAllQuestions_acrossUnits() {
        bank.addQuestion(makeQ("Unit1"));
        bank.addQuestion(makeQ("Unit1"));
        bank.addQuestion(makeQ("Unit2"));
        assertEquals(3, bank.getAllQuestions().size());
    }

    @Test
    void totalSize_correct() {
        bank.addQuestion(makeQ("A"));
        bank.addQuestion(makeQ("A"));
        bank.addQuestion(makeQ("B"));
        assertEquals(3, bank.totalSize());
    }

    @Test
    void hasUnit_trueWhenPresent() {
        bank.addQuestion(makeQ("Unit1"));
        assertTrue(bank.hasUnit("Unit1"));
    }

    @Test
    void hasUnit_falseWhenAbsent() {
        assertFalse(bank.hasUnit("Ghost"));
    }

    @Test
    void getUnitTags_returnsAllUnits() {
        bank.addQuestion(makeQ("U1"));
        bank.addQuestion(makeQ("U2"));
        assertTrue(bank.getUnitTags().contains("U1"));
        assertTrue(bank.getUnitTags().contains("U2"));
    }

    @Test
    void emptyBank_getAllQuestions_returnsEmptyList() {
        assertTrue(bank.getAllQuestions().isEmpty());
    }
}
