package bigstats.view;

import bigstats.model.*;
import java.util.Scanner;

public class ConsoleView implements GameView {

    private final Scanner scanner = new Scanner(System.in);

    // ── helpers ───────────────────────────────────────────────────────────────

    private void line() { System.out.println("═".repeat(60)); }

    private void healthBar(String label, int cur, int max) {
        int filled = (int) Math.round(((double) cur / max) * 30);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 30; i++) bar.append(i < filled ? "█" : "░");
        bar.append("]");
        System.out.printf("  %-10s %s  %d/%d%n", label, bar, cur, max);
    }

    private void pause() {
        System.out.print("  [Press ENTER to continue] ");
        scanner.nextLine();
    }

    // ── GameView ──────────────────────────────────────────────────────────────

    @Override
    public String promptPlayerName() {
        System.out.print("Enter your name, student: ");
        return scanner.nextLine().trim();
    }

    @Override
    public void showTitleScreen() {
        line();
        System.out.println("         BIG STATS: Study for AP Statistics!");
        System.out.println("  Answer questions to defeat unit bosses, then ace the AP Exam.");
        line();
    }

    @Override
    public void showBossIntro(Boss boss, Player player) {
        line();
        System.out.printf("  ⚔  BOSS: %s  [%s]%n", boss.getUnitName(), boss.getDifficulty());
        healthBar("Boss HP",       boss.getHealth(),   boss.getMaxHealth());
        healthBar(player.getName(), player.getHealth(), player.getMaxHealth());
        System.out.printf("  Correct → %d dmg to boss | Wrong → %d dmg to you%n",
                boss.getBossDamagePerCorrect(), boss.getPlayerDamagePerWrong());
        line();
        pause();
    }

    @Override
    public void showAPExamIntro(Boss boss, Player player) {
        line();
        System.out.println("  ★★★  THE AP STATISTICS EXAM  ★★★");
        System.out.println("  Questions from ALL units, random order.");
        System.out.printf("  Answer ≥ 75%% correctly to pass (score 3+).%n");
        healthBar(player.getName(), player.getHealth(), player.getMaxHealth());
        line();
        pause();
    }

    @Override
    public int promptAnswer(Question q, int remaining, int total) {
        int num = total - remaining + 1;
        System.out.printf("%n  Q%d of %d  [%s]%n%n  %s%n%n", num, total, q.getUnitTag(), q.getPrompt());
        var choices = q.getChoices();
        for (int i = 0; i < choices.size(); i++)
            System.out.printf("    %c) %s%n", 'A' + i, choices.get(i));
        while (true) {
            System.out.print("%n  Your answer (A/B/C/D): ".formatted());
            String in = scanner.nextLine().trim().toUpperCase();
            if (in.length() == 1) {
                int idx = in.charAt(0) - 'A';
                if (idx >= 0 && idx < choices.size()) return idx;
            }
            System.out.println("  ⚠  Please enter A, B, C, or D.");
        }
    }

    @Override
    public void showCorrectFeedback(Boss boss, Player player) {
        System.out.println("\n  ✔  CORRECT!");
        healthBar("Boss HP",       boss.getHealth(),   boss.getMaxHealth());
        healthBar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override
    public void showIncorrectFeedback(Question q, Boss boss, Player player) {
        System.out.println("\n  ✘  INCORRECT!");
        System.out.printf("     Correct answer: %c) %s%n",
                'A' + q.getCorrectIndex(), q.getChoices().get(q.getCorrectIndex()));
        healthBar("Boss HP",       boss.getHealth(),   boss.getMaxHealth());
        healthBar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override
    public void showBossDefeated(Boss boss, Player player) {
        line();
        System.out.printf("  🏆  Defeated %s!  HP: %d/%d%n",
                boss.getUnitName(), player.getHealth(), player.getMaxHealth());
        line();
        pause();
    }

    @Override
    public void showPlayerDefeated(Player player) {
        line();
        System.out.println("  💀  You have been defeated. Study harder!");
        line();
    }

    @Override
    public void showAPExamResult(int correct, int total, double accuracy, double threshold, Player player) {
        line();
        System.out.println("  📊  AP EXAM RESULTS");
        System.out.printf("  Correct: %d / %d  (%.1f%%)%n", correct, total, accuracy * 100);
        System.out.printf("  Pass threshold: %.0f%%%n", threshold * 100);
        System.out.println(accuracy >= threshold ? "  ★  PASSED! Score: 3 or higher!" : "  ✘  Did not pass.");
        line();
    }

    @Override
    public void showVictoryScreen(Player player) {
        line();
        System.out.printf("  🎉  CONGRATULATIONS, %s!%n", player.getName().toUpperCase());
        System.out.printf("  Final — Score: %d | Accuracy: %.1f%%%n",
                player.getScore(), player.getAccuracy() * 100);
        line();
    }

    @Override
    public void showGameOverScreen(Player player) {
        line();
        System.out.printf("  💀  GAME OVER — %s has fallen.%n", player.getName());
        System.out.printf("  Final — Score: %d | Accuracy: %.1f%%%n",
                player.getScore(), player.getAccuracy() * 100);
        line();
    }
}
