package bigstats.view;

import bigstats.model.*;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner sc = new Scanner(System.in);

    private void line() { System.out.println("─".repeat(60)); }
    private void bar(String label, int cur, int max) {
        int f = (int) Math.round(30.0 * cur / max);
        System.out.printf("  %-10s [%s%s] %d/%d%n", label, "█".repeat(f), "░".repeat(30-f), cur, max);
    }
    private void pause() { System.out.print("[Enter] "); sc.nextLine(); }

    @Override public String promptPlayerName() { System.out.print("Your name: "); return sc.nextLine().trim(); }

    @Override public void showTitleScreen() {
        line(); System.out.println("  BIG STATS – Study for AP Statistics!"); line();
    }

    @Override public void showBossIntro(Boss boss, Player player) {
        line();
        System.out.printf("  ⚔ BOSS: %s [%s]%n", boss.getUnitName(), boss.getDifficulty());
        bar("Boss HP", boss.getHealth(), boss.getMaxHealth());
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
        System.out.printf("  Correct → %d boss dmg | Wrong → %d player dmg%n",
                boss.getBossDamagePerCorrect(), boss.getPlayerDamagePerWrong());
        line(); pause();
    }

    @Override public void showAPExamIntro(Boss boss, Player player) {
        line();
        System.out.println("  ★ THE AP EXAM – all units, random order. Need ≥75% to pass.");
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
        line(); pause();
    }

    @Override public int promptAnswer(Question q, int remaining, int total) {
        System.out.printf("%n  Q%d/%d [%s]%n  %s%n%n", total - remaining, total, q.getUnitTag(), q.getPrompt());
        var c = q.getChoices();
        for (int i = 0; i < c.size(); i++) System.out.printf("    %c) %s%n", 'A'+i, c.get(i));
        while (true) {
            System.out.print("  Answer (A/B/C/D): ");
            String in = sc.nextLine().trim().toUpperCase();
            if (in.length() == 1) { int idx = in.charAt(0)-'A'; if (idx >= 0 && idx < c.size()) return idx; }
            System.out.println("  Invalid – enter A, B, C, or D.");
        }
    }

    @Override public void showCorrectFeedback(Boss boss, Player player) {
        System.out.println("  ✔ CORRECT!");
        bar("Boss HP", boss.getHealth(), boss.getMaxHealth());
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override public void showIncorrectFeedback(Question q, Boss boss, Player player) {
        System.out.printf("  ✘ INCORRECT! Answer: %c) %s%n",
                'A'+q.getCorrectIndex(), q.getChoices().get(q.getCorrectIndex()));
        bar("Boss HP", boss.getHealth(), boss.getMaxHealth());
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override public void showBossDefeated(Boss boss, Player player) {
        line(); System.out.printf("  🏆 Defeated %s! HP: %d/%d%n",
                boss.getUnitName(), player.getHealth(), player.getMaxHealth()); line(); pause();
    }

    @Override public void showPlayerDefeated(Player p) {
        line(); System.out.println("  💀 You were defeated. Study harder!"); line();
    }

    @Override public void showAPExamResult(int correct, int total, double acc, double thr, Player p) {
        line();
        System.out.printf("  AP EXAM: %d/%d (%.1f%%) – %s%n",
                correct, total, acc*100, acc >= thr ? "★ PASSED!" : "✘ Failed.");
        line();
    }

    @Override public void showVictoryScreen(Player p) {
        line(); System.out.printf("  🎉 YOU WIN, %s! Score: %d | Accuracy: %.1f%%%n",
                p.getName().toUpperCase(), p.getScore(), p.getAccuracy()*100); line();
    }

    @Override public void showGameOverScreen(Player p) {
        line(); System.out.printf("  💀 GAME OVER – %s. Score: %d | Accuracy: %.1f%%%n",
                p.getName(), p.getScore(), p.getAccuracy()*100); line();
    }
}
