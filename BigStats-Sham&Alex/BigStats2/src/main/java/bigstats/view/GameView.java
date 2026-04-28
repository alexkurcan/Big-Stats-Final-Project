package bigstats.view;

import bigstats.model.*;

public interface GameView {
    String promptPlayerName();
    void showTitleScreen();
    void showBossIntro(Boss boss, Player player);
    void showAPExamIntro(Boss boss, Player player);
    int  promptAnswer(Question q, int remaining, int total);
    void showCorrectFeedback(Boss boss, Player player);
    void showIncorrectFeedback(Question q, Boss boss, Player player);
    void showBossDefeated(Boss boss, Player player);
    void showPlayerDefeated(Player player);
    void showAPExamResult(int correct, int total, double accuracy, double threshold, Player player);
    void showVictoryScreen(Player player);
    void showGameOverScreen(Player player);
}
