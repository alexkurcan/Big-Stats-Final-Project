package bigstats.view;

import bigstats.model.*;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner sc = new Scanner(System.in);

    private void line() { System.out.println("═".repeat(64)); }
    private void thinLine() { System.out.println("─".repeat(64)); }
    private void bar(String label, int cur, int max) {
        int f = (int) Math.round(30.0 * cur / max);
        System.out.printf("  %-10s [%s%s] %d/%d%n", label, "█".repeat(f), "░".repeat(30-f), cur, max);
    }
    private void pause() { System.out.print("\n  [Press Enter to continue...] "); sc.nextLine(); }

    // ─────────────────────── ASCII ART LIBRARY ───────────────────────

    private static final String TITLE_ART =
        "  ██████╗ ██╗ ██████╗     ███████╗████████╗ █████╗ ████████╗███████╗\n" +
        "  ██╔══██╗██║██╔════╝     ██╔════╝╚══██╔══╝██╔══██╗╚══██╔══╝██╔════╝\n" +
        "  ██████╔╝██║██║  ███╗    ███████╗   ██║   ███████║   ██║   ███████╗\n" +
        "  ██╔══██╗██║██║   ██║    ╚════██║   ██║   ██╔══██║   ██║   ╚════██║\n" +
        "  ██████╔╝██║╚██████╔╝    ███████║   ██║   ██║  ██║   ██║   ███████║\n" +
        "  ╚═════╝ ╚═╝ ╚═════╝     ╚══════╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝   ╚══════╝";

    // Boss 1: Exploring One-Variable Data – a giant histogram monster
    private static final String BOSS_1_ART =
        "         THE HISTOGRAM HYDRA\n" +
        "            ___   ___\n" +
        "           |   | |   |\n" +
        "        ___|   |_|   |___\n" +
        "       |   \\  / V \\  /   |\n" +
        "       |    \\/ o o \\/    |\n" +
        "       |    (  ___  )    |\n" +
        "       |_____|_/_\\_|_____|\n" +
        "      /  ___|       |___ \\ \n" +
        "     /  /  █ █ █ █ █  \\  \\\n" +
        "    |  |   █ █ █ █ █   |  |\n" +
        "    |  |  _█_█_█_█_█_  |  |\n" +
        "     \\  \\/             \\/ /\n" +
        "      [  Q1 -- μ -- Q3  ]\n" +
        "       UNIT I • EASY";

    // Boss 2: Exploring Two-Variable Data – a scatter plot spider
    private static final String BOSS_2_ART =
        "       THE SCATTERPLOT SPIDER\n" +
        "            * . *   *\n" +
        "          *   . * .   *\n" +
        "        *  .   .*.   .  *\n" +
        "          \\  .  |  .  /\n" +
        "       ----\\---( )---/----\n" +
        "          /  . |r| .  \\\n" +
        "        *  .   .|.   .  *\n" +
        "          *   . * .   *\n" +
        "            * . *   *\n" +
        "         ŷ = b₀ + b₁x\n" +
        "       UNIT II • EASY";

    // Boss 3: Collecting Data – a survey phantom
    private static final String BOSS_3_ART =
        "        THE SURVEY PHANTOM\n" +
        "         .--\"\"--.\n" +
        "        / .-\"\"-.  \\\n" +
        "       | / /´`\\ \\ |\n" +
        "       | \\ \\__/ / |\n" +
        "        \\ `----' /\n" +
        "    ___  `------'  ___\n" +
        "   /   \\ |SURVEY| /   \\\n" +
        "  | [?] || Form || [?] |\n" +
        "   \\___/ |______| \\___/\n" +
        "    ~Randomize or perish~\n" +
        "      UNIT III • MEDIUM";

    // Boss 4: Probability – a dice golem
    private static final String BOSS_4_ART =
        "       THE PROBABILITY GOLEM\n" +
        "         .----------.\n" +
        "        / ⚀  ⚁  ⚂  /|\n" +
        "       /  ⚃  ⚄  ⚅ / |\n" +
        "      /___________/  |\n" +
        "      |  P(A∪B) =  | /\n" +
        "      | P(A)+P(B) -|/\n" +
        "      |  P(A∩B)    |\n" +
        "      |____________|\n" +
        "       /|  μ=np   |\\\n" +
        "      / | σ=√npq  | \\\n" +
        "     UNIT IV • MEDIUM";

    // Boss 5: Sampling Distributions – a CLT titan
    private static final String BOSS_5_ART =
        "       THE CLT TITAN\n" +
        "       ___________\n" +
        "      /           \\\n" +
        "     | . . . . . . |\n" +
        "     | .  .  .  .  |\n" +
        "     |  .  Bell .  |\n" +
        "     |   . Curve.  |\n" +
        "     |    .    .   |\n" +
        "     |      ..     |\n" +
        "     |_____________|\n" +
        "    /  n→∞  σ/√n   \\\n" +
        "   /  As n grows,   \\\n" +
        "  [  x̄ ~ N(μ, σ/√n)  ]\n" +
        "      UNIT V • HARD";

    // Boss 6: Inference – the Chi-Square Dragon
    private static final String BOSS_6_ART =
        "      THE CHI-SQUARE DRAGON\n" +
        "              /\\    /\\\n" +
        "             /  \\  /  \\\n" +
        "            / χ² \\/ χ² \\\n" +
        "           /   SCALES   \\\n" +
        "          /    _    _    \\\n" +
        "          \\   / \\  / \\   /\n" +
        "           \\_/ H0\\/  H1\\_/\n" +
        "           ( p-value < α )\n" +
        "            \\  REJECT!  /\n" +
        "             \\ H0 dies /\n" +
        "              \\_______/\n" +
        "      UNIT VI • HARD";

    // AP Exam final boss
    private static final String AP_EXAM_ART =
        "  ╔══════════════════════════════════════════════╗\n" +
        "  ║    ★  THE AP STATISTICS EXAM  ★              ║\n" +
        "  ║                                              ║\n" +
        "  ║   ┌──────────────────────────────────┐      ║\n" +
        "  ║   │  Section I   │  Section II       │      ║\n" +
        "  ║   │  40 MCQ      │  Free Response    │      ║\n" +
        "  ║   │  50% weight  │  50% weight       │      ║\n" +
        "  ║   └──────────────────────────────────┘      ║\n" +
        "  ║                                              ║\n" +
        "  ║      All 6 units. Random order.             ║\n" +
        "  ║      Score ≥ 75% to pass and SURVIVE.       ║\n" +
        "  ╚══════════════════════════════════════════════╝";

    private static final String VICTORY_ART =
        "  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
        "  ░                                                      ░\n" +
        "  ░   ██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗██╗███╗  ░\n" +
        "  ░   ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██║████╗ ░\n" +
        "  ░    ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║██╔██╗░\n" +
        "  ░     ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║██║╚██╗░\n" +
        "  ░      ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║██║ ╚█╗░\n" +
        "  ░      ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝╚═╝  ╚╝░\n" +
        "  ░                                                      ░\n" +
        "  ░          🎓  AP STATISTICS: CONQUERED  🎓            ░\n" +
        "  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░";

    private static final String GAME_OVER_ART =
        "  ╔══════════════════════════════════════════════════════╗\n" +
        "  ║                                                      ║\n" +
        "  ║   ░██████╗░░█████╗░███╗░░░███╗███████╗              ║\n" +
        "  ║   ██╔════╝░██╔══██╗████╗░████║██╔════╝              ║\n" +
        "  ║   ██║░░██╗░███████║██╔████╔██║█████╗░░              ║\n" +
        "  ║   ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░              ║\n" +
        "  ║   ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗              ║\n" +
        "  ║   ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░╚═╝╚══════╝              ║\n" +
        "  ║            ░░░░    OVER    ░░░░                      ║\n" +
        "  ║                                                      ║\n" +
        "  ║         📚  Open your textbook and try again  📚     ║\n" +
        "  ╚══════════════════════════════════════════════════════╝";

    private String getBossArt(Boss boss) {
        String name = boss.getUnitName();
        if (name.contains("One-Variable"))   return BOSS_1_ART;
        if (name.contains("Two-Variable"))   return BOSS_2_ART;
        if (name.contains("Collecting"))     return BOSS_3_ART;
        if (name.contains("Probability"))    return BOSS_4_ART;
        if (name.contains("Sampling"))       return BOSS_5_ART;
        if (name.contains("Inference"))      return BOSS_6_ART;
        return "  ⚔  " + boss.getUnitName();
    }

    // ─────────────────────── INTERFACE METHODS ───────────────────────

    @Override public String promptPlayerName() {
        System.out.print("\n  Enter your name, challenger: ");
        return sc.nextLine().trim();
    }

    @Override public void showTitleScreen() {
        System.out.println();
        line();
        System.out.println();
        System.out.println(TITLE_ART);
        System.out.println();
        thinLine();
        System.out.println("   Study your way through 6 AP Stats bosses & survive the exam!");
        System.out.println("   Answer correctly to damage bosses. Wrong answers hurt YOU.");
        thinLine();
        System.out.println();
    }

    @Override public void showBossIntro(Boss boss, Player player) {
        System.out.println();
        line();
        System.out.println();
        System.out.println(getBossArt(boss));
        System.out.println();
        thinLine();
        System.out.printf("  ⚔  BOSS ENCOUNTER: %s%n", boss.getUnitName());
        System.out.printf("  DIFFICULTY: %s%n", boss.getDifficulty());
        thinLine();
        bar("Boss HP", boss.getHealth(), boss.getMaxHealth());
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
        thinLine();
        System.out.printf("  ✔ Correct answer → %d damage to boss%n", boss.getBossDamagePerCorrect());
        System.out.printf("  ✘ Wrong answer   → %d damage to YOU%n",  boss.getPlayerDamagePerWrong());
        line();
        pause();
    }

    @Override public void showAPExamIntro(Boss boss, Player player) {
        System.out.println();
        line();
        System.out.println();
        System.out.println(AP_EXAM_ART);
        System.out.println();
        thinLine();
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
        line();
        pause();
    }

    @Override public int promptAnswer(Question q, int remaining, int total) {
        System.out.println();
        thinLine();
        System.out.printf("  Q%d/%d  [Unit: %s]%n", total - remaining + 1, total, q.getUnitTag());
        thinLine();
        System.out.printf("%n  %s%n%n", q.getPrompt());
        var c = q.getChoices();
        for (int i = 0; i < c.size(); i++) System.out.printf("    %c) %s%n", 'A'+i, c.get(i));
        System.out.println();
        while (true) {
            System.out.print("  Your answer (A/B/C/D): ");
            String in = sc.nextLine().trim().toUpperCase();
            if (in.length() == 1) { int idx = in.charAt(0)-'A'; if (idx >= 0 && idx < c.size()) return idx; }
            System.out.println("  ⚠  Invalid – enter A, B, C, or D.");
        }
    }

    @Override public void showCorrectFeedback(Boss boss, Player player) {
        System.out.println();
        System.out.println("  ╔════════════════════╗");
        System.out.println("  ║  ✔  CORRECT!  +XP  ║");
        System.out.println("  ╚════════════════════╝");
        bar("Boss HP",       boss.getHealth(),   boss.getMaxHealth());
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override public void showIncorrectFeedback(Question q, Boss boss, Player player) {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.printf ("  ║  ✘  WRONG! Correct: %c) %-19s║%n",
                'A'+q.getCorrectIndex(),
                truncate(q.getChoices().get(q.getCorrectIndex()), 19));
        System.out.println("  ╚══════════════════════════════════════════╝");
        bar("Boss HP",       boss.getHealth(),   boss.getMaxHealth());
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
    }

    @Override public void showBossDefeated(Boss boss, Player player) {
        System.out.println();
        line();
        System.out.println("          ★ ★ ★  BOSS DEFEATED  ★ ★ ★");
        System.out.println();
        System.out.printf ("   \"%s\" has been vanquished!%n", boss.getUnitName());
        System.out.println();
        bar(player.getName(), player.getHealth(), player.getMaxHealth());
        line();
        pause();
    }

    @Override public void showPlayerDefeated(Player p) {
        System.out.println();
        line();
        System.out.println("  ╔══════════════════════════════╗");
        System.out.println("  ║  💀  YOU HAVE BEEN DEFEATED  ║");
        System.out.println("  ╚══════════════════════════════╝");
        System.out.println("   Your stats weren't big enough. Study harder!");
        line();
    }

    @Override public void showAPExamResult(int correct, int total, double acc, double thr, Player p) {
        System.out.println();
        line();
        boolean passed = acc >= thr;
        System.out.printf("  AP EXAM RESULTS: %d / %d correct  (%.1f%%)%n", correct, total, acc * 100);
        System.out.println();
        if (passed) {
            System.out.println("  ★ ★ ★  PASSED!  ★ ★ ★");
            System.out.println("  You've mastered AP Statistics!");
        } else {
            System.out.printf("  ✘ FAILED  (needed ≥ %.0f%%)%n", thr * 100);
            System.out.println("  Review your notes and try again.");
        }
        line();
    }

    @Override public void showVictoryScreen(Player p) {
        System.out.println();
        System.out.println(VICTORY_ART);
        System.out.println();
        line();
        System.out.printf("  Challenger: %s%n", p.getName().toUpperCase());
        System.out.printf("  Final Score:   %d%n", p.getScore());
        System.out.printf("  Accuracy:      %.1f%%%n", p.getAccuracy() * 100);
        line();
    }

    @Override public void showGameOverScreen(Player p) {
        System.out.println();
        System.out.println(GAME_OVER_ART);
        System.out.println();
        line();
        System.out.printf("  Player:    %s%n", p.getName());
        System.out.printf("  Score:     %d%n", p.getScore());
        System.out.printf("  Accuracy:  %.1f%%%n", p.getAccuracy() * 100);
        line();
    }

    // ─────────────────────── UTILITIES ───────────────────────

    private String truncate(String s, int max) {
        return s.length() <= max ? s + " ".repeat(max - s.length()) : s.substring(0, max - 1) + "…";
    }
}
