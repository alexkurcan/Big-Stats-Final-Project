package bigstats;

import bigstats.model.*;
import bigstats.view.ConsoleView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleViewTest {

    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        originalIn  = System.in;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private ConsoleView viewWith(String... lines) {
        String input = String.join("\n", lines) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        return new ConsoleView();
    }

    private Boss boss(String name) { return new Boss(name, 100, Boss.Difficulty.EASY); }
    private Player player()        { return new Player("Alex", 100); }
    private Question question()    { return new Question("Q?", List.of("A","B","C","D"), 0, "U"); }

    @Test
    void showTitleScreen_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith().showTitleScreen());
    }

    @Test
    void promptPlayerName_returnsInput() {
        assertEquals("Alex", viewWith("Alex").promptPlayerName());
    }

    @Test
    void showBossIntro_unit1_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossIntro(boss(QuestionFactory.UNIT_1), player()));
    }

    @Test
    void showBossIntro_unit2_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossIntro(boss(QuestionFactory.UNIT_2), player()));
    }

    @Test
    void showBossIntro_unit3_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossIntro(new Boss(QuestionFactory.UNIT_3, 100, Boss.Difficulty.MEDIUM), player()));
    }

    @Test
    void showBossIntro_unit4_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossIntro(new Boss(QuestionFactory.UNIT_4, 100, Boss.Difficulty.MEDIUM), player()));
    }

    @Test
    void showBossIntro_unit5_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossIntro(new Boss(QuestionFactory.UNIT_5, 100, Boss.Difficulty.HARD), player()));
    }

    @Test
    void showBossIntro_unit6_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossIntro(new Boss(QuestionFactory.UNIT_6, 100, Boss.Difficulty.HARD), player()));
    }

    @Test
    void showBossIntro_unknownUnit_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossIntro(boss("Unknown"), player()));
    }

    @Test
    void showAPExamIntro_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showAPExamIntro(new Boss("AP Exam", 1, Boss.Difficulty.HARD), player()));
    }

    @Test
    void promptAnswer_validInput_returnsIndex() {
        assertEquals(1, viewWith("B").promptAnswer(question(), 3, 10));
    }

    @Test
    void promptAnswer_invalidThenValid_retries() {
        assertEquals(3, viewWith("Z", "D").promptAnswer(question(), 1, 5));
    }

    @Test
    void showCorrectFeedback_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith().showCorrectFeedback(boss("U"), player()));
    }

    @Test
    void showIncorrectFeedback_shortAnswer_doesNotThrow() {
        Question q = new Question("Q?", List.of("Short", "B", "C", "D"), 0, "U");
        assertDoesNotThrow(() -> viewWith().showIncorrectFeedback(q, boss("U"), player()));
    }

    @Test
    void showIncorrectFeedback_longAnswer_doesNotThrow() {
        Question q = new Question("Q?", List.of("This is a very long answer that exceeds the limit", "B", "C", "D"), 0, "U");
        assertDoesNotThrow(() -> viewWith().showIncorrectFeedback(q, boss("U"), player()));
    }

    @Test
    void showBossDefeated_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith("").showBossDefeated(boss("U"), player()));
    }

    @Test
    void showPlayerDefeated_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith().showPlayerDefeated(player()));
    }

    @Test
    void showAPExamResult_passed_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith().showAPExamResult(8, 10, 0.80, 0.75, player()));
    }

    @Test
    void showAPExamResult_failed_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith().showAPExamResult(5, 10, 0.50, 0.75, player()));
    }

    @Test
    void showVictoryScreen_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith().showVictoryScreen(player()));
    }

    @Test
    void showGameOverScreen_doesNotThrow() {
        assertDoesNotThrow(() -> viewWith().showGameOverScreen(player()));
    }
}
