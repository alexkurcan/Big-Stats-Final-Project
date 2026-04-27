package bigstats.view;

import bigstats.model.*;

import java.util.Scanner;

/**
 * Console-based implementation of GameView.
 */
public class ConsoleView implements GameView {

    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    // ── helpers ───────────────────────────────────────────────────────────────

    private void printDivider() {
        System.out.println("═".repeat(60));
    }

    private void printHealthBar(String label, int current, int max) {
        int barLength = 30;
        int filled    = (int) Math.round(((double) current / max) * barLength);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) bar.append(i < filled ? "█" : "░");
        bar.append("]");
        System.out.printf("  %-10s %s  %d/%d%n", label, bar, current, max);
    }

    // ── GameView ──────────────────────────────────────────────────────────────

    @Override
    public String promptPlayerName() {
        System.out.print("Enter your name, student: ");
        return scanner.nextLine().trim();
    }

    @Override
    public void showTitleScreen() {
        printDivider();
        System.out.println("         ██████╗ ██╗ ██████╗     ███████╗████████╗ █████╗ ████████╗███████╗");
        System.out.println("         ██╔══██╗██║██╔════╝     ██╔════╝╚══██╔══╝██╔══██╗╚══██╔══╝██╔════╝");
        System.out.println("         ██████╔╝██║██║  ███╗    ███████╗   ██║   ███████║   ██║   ███████╗");
        System.out.println("         ██╔══██╗██║██║   ██║    ╚════██║   ██║   ██╔══██║   ██║   ╚════██║");
        System.out.println("         ██████╔╝██║╚██████╔╝    ███████║   ██║   ██║  ██║   ██║   ███████║");
        System.out.println("         ╚═════╝ ╚═╝ ╚═════╝     ╚══════╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝   ╚══════╝");
        System.out.println();
        System.out.println("                  Study for the AP Statistics Exam!");
        System.out.println("      Answer questions correctly to defeat unit bosses.");
        System.out.println("      Survive all bosses and ace the AP Exam to WIN!");
        printDivider();
    }

    @Override
    public void showBossIntro(Boss boss, Player player) {
        printDivider();
        System.out.println("⚔  NEW BOSS ENCOUNTER!");
        System.out.println();
        System.out.printf("  Boss: %s  [%s]%n", boss.getUnitName(), boss.getDifficulty());
        printHealthBar("Boss HP", boss.getHealth(), boss.getMaxHealth());
        printHealthBar(player.getName(), player.getHealth(), player.getMaxHealth());
        System.out.println();
        System.out.printf("  Each CORRECT answer deals %d damage to the boss.%n",
                boss.getBossDamagePerCorrect());
        System.out.printf("  Each WRONG answer deals   %d damage to you.%n",
                boss.getPlayerDamagePerWrong());
        printDivider();
        pause();
    }

    @Override
    public void showAPExamIntro(Boss boss, Player player) {
        printDivider();
        System.out.println("★★★  THE AP STATISTICS EXAM  ★★★");
        System.out.println();
        System.out.println("  You have defeated all unit bosses!");
        System.out.println("  Now face the FINAL BOSS: The AP Exam.");
        System.out.println();
        System.out.println("  Questions from ALL units will appear in random order.");
        System.out.printf("  You must answer at least %.0f%% correctly to pass (score 3+).%n", 75.0);
        System.out.println();
        printHealthBar(player.getName(), player.getHealth(), player.getMaxHealth());
        printDivider();
        pause();
    }

    @Override
    public int promptAnswer(Question q, int remaining, int total) {
        int answered = total - remaining;
        System.out.println();
        System.out.printf("  Question %d of %d  [%s]%n", answered + 1, total, q.getUnitTag());
        System.out.println();
        System.out.println("  " + q.getPrompt());
        System.out.println();
        var choices = q.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.printf("    %c) %s%n", 'A' + i, choices.get(i));
        }
        System.out.println();

        while (true) {
            System.out.print("  Your answer (A/B/C/D): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1) {
                int idx = input.charAt(0) - 'A';
                if (idx >= 0 && idx < choices.size()) return idx;
            }
            System.out.println("  ⚠  Invalid input. Please enter A, B, C, or D.");
        }
    }

    @Override
    public void showCorrectFeedback(Boss boss, Player player) {
        System.out.println();
        System.out.println("  ✔  CORRECT!");
        printHealthBar("Boss HP", boss.getHealth(), boss.getMaxHealth());
        printHealthBar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override
    public void showIncorrectFeedback(Question q, Boss boss, Player player) {
        System.out.println();
        System.out.println("  ✘  INCORRECT!");
        System.out.printf("     The correct answer was: %c) %s%n",
                'A' + q.getCorrectIndex(), q.getChoices().get(q.getCorrectIndex()));
        printHealthBar("Boss HP", boss.getHealth(), boss.getMaxHealth());
        printHealthBar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override
    public void showBossDefeated(Boss boss, Player player) {
        printDivider();
        System.out.printf("  🏆  You defeated %s!%n", boss.getUnitName());
        System.out.printf("  Your HP: %d/%d%n", player.getHealth(), player.getMaxHealth());
        printDivider();
        pause();
    }

    @Override
    public void showPlayerDefeated(Player player) {
        printDivider();
        System.out.println("  💀  You have been defeated…");
        System.out.println("  Study harder and try again!");
        printDivider();
    }

    @Override
    public void showAPExamResult(int correct, int total, double accuracy, double threshold, Player player) {
        printDivider();
        System.out.println("  📊  AP EXAM RESULTS");
        System.out.println();
        System.out.printf("  Correct answers: %d / %d%n", correct, total);
        System.out.printf("  Accuracy       : %.1f%%%n", accuracy * 100);
        System.out.printf("  Pass threshold : %.0f%%%n", threshold * 100);
        System.out.println();
        if (accuracy >= threshold) {
            System.out.println("  ★  You passed the AP Exam! Score: 3 or higher!");
        } else {
            System.out.println("  ✘  You did not pass. Score: below 3.");
        }
        printDivider();
    }

    @Override
    public void showVictoryScreen(Player player) {
        printDivider();
        System.out.println();
        System.out.println("  🎉  CONGRATULATIONS, " + player.getName().toUpperCase() + "! 🎉");
        System.out.println();
        System.out.println("  You defeated every unit boss AND passed the AP Exam!");
        System.out.printf("  Final Stats — Score: %d | Accuracy: %.1f%%%n",
                player.getScore(), player.getAccuracy() * 100);
        System.out.println();
        printDivider();
    }

    @Override
    public void showGameOverScreen(Player player) {
        printDivider();
        System.out.println();
        System.out.println("  💀  GAME OVER — " + player.getName() + " has fallen.");
        System.out.println();
        System.out.printf("  Final Stats — Score: %d | Accuracy: %.1f%%%n",
                player.getScore(), player.getAccuracy() * 100);
        System.out.println("  Better luck next time. Study those units!");
        System.out.println();
        printDivider();
    }

    // ── utility ───────────────────────────────────────────────────────────────

    private void pause() {
        System.out.print("  [Press ENTER to continue] ");
        scanner.nextLine();
    }
}
