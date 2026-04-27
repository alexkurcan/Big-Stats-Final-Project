package bigstats.view;

import bigstats.model.*;

/**
 * View interface – decouples the display layer from model/controller logic.
 * Console and GUI implementations both implement this contract.
 */
public interface GameView {

    /** Ask the player for their name. */
    String promptPlayerName();

    /** Show the main title screen. */
    void showTitleScreen();

    /** Show an intro for a unit boss fight. */
    void showBossIntro(Boss boss, Player player);

    /** Show the intro for the AP Exam. */
    void showAPExamIntro(Boss boss, Player player);

    /**
     * Display the question and return the player's 0-based answer index.
     *
     * @param q         the current question
     * @param remaining questions left after this one
     * @param total     total questions in this fight
     */
    int promptAnswer(Question q, int remaining, int total);

    /** Shown immediately after a correct answer. */
    void showCorrectFeedback(Boss boss, Player player);

    /** Shown immediately after an incorrect answer, revealing the right answer. */
    void showIncorrectFeedback(Question q, Boss boss, Player player);

    /** Boss HP reached 0. */
    void showBossDefeated(Boss boss, Player player);

    /** Player HP reached 0. */
    void showPlayerDefeated(Player player);

    /** AP Exam summary screen. */
    void showAPExamResult(int correct, int total, double accuracy, double threshold, Player player);

    /** Final victory screen. */
    void showVictoryScreen(Player player);

    /** Game over screen (player died). */
    void showGameOverScreen(Player player);
}
