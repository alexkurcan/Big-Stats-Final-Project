package bigstats;

import bigstats.model.QuestionFactory;
import bigstats.model.QuestionBank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionFactoryTest {

    @Test
    void buildBank_containsAllSixUnits() {
        QuestionBank bank = QuestionFactory.buildBank();
        assertTrue(bank.hasUnit(QuestionFactory.UNIT_1));
        assertTrue(bank.hasUnit(QuestionFactory.UNIT_2));
        assertTrue(bank.hasUnit(QuestionFactory.UNIT_3));
        assertTrue(bank.hasUnit(QuestionFactory.UNIT_4));
        assertTrue(bank.hasUnit(QuestionFactory.UNIT_5));
        assertTrue(bank.hasUnit(QuestionFactory.UNIT_6));
    }

    @Test
    void buildBank_each_unit_has_ten_questions() {
        QuestionBank bank = QuestionFactory.buildBank();
        assertEquals(10, bank.getQuestionsForUnit(QuestionFactory.UNIT_1).size());
        assertEquals(10, bank.getQuestionsForUnit(QuestionFactory.UNIT_2).size());
        assertEquals(10, bank.getQuestionsForUnit(QuestionFactory.UNIT_3).size());
        assertEquals(10, bank.getQuestionsForUnit(QuestionFactory.UNIT_4).size());
        assertEquals(10, bank.getQuestionsForUnit(QuestionFactory.UNIT_5).size());
        assertEquals(10, bank.getQuestionsForUnit(QuestionFactory.UNIT_6).size());
    }

    @Test
    void buildBank_totalIs60() {
        QuestionBank bank = QuestionFactory.buildBank();
        assertEquals(60, bank.totalSize());
    }

    @Test
    void allQuestions_haveValidCorrectIndex() {
        QuestionBank bank = QuestionFactory.buildBank();
        bank.getAllQuestions().forEach(q -> {
            int ci = q.getCorrectIndex();
            assertTrue(ci >= 0 && ci < q.getChoices().size(),
                "Correct index out of range for: " + q.getPrompt());
        });
    }
}
