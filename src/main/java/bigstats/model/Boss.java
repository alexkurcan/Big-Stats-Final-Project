package bigstats.model;

/**
 * Represents a boss enemy in Big Stats.
 * Each boss corresponds to an AP Statistics unit.
 */
public class Boss {

    public enum Difficulty { EASY, MEDIUM, HARD }

    private final String unitName;
    private int health;
    private final int maxHealth;
    private final Difficulty difficulty;

    /** Damage dealt to the player when they answer incorrectly. */
    private final int playerDamagePerWrong;

    /** Damage dealt to the boss when the player answers correctly. */
    private final int bossDamagePerCorrect;

    public Boss(String unitName, int maxHealth, Difficulty difficulty) {
        if (maxHealth <= 0) throw new IllegalArgumentException("maxHealth must be positive.");
        this.unitName    = unitName;
        this.maxHealth   = maxHealth;
        this.health      = maxHealth;
        this.difficulty  = difficulty;

        // Scale damage values based on difficulty
        switch (difficulty) {
            case EASY   -> { playerDamagePerWrong = 10; bossDamagePerCorrect = 25; }
            case MEDIUM -> { playerDamagePerWrong = 15; bossDamagePerCorrect = 20; }
            case HARD   -> { playerDamagePerWrong = 20; bossDamagePerCorrect = 15; }
            default     -> { playerDamagePerWrong = 15; bossDamagePerCorrect = 20; }
        }
    }

    // ── health ───────────────────────────────────────────────────────────────

    public int getHealth()    { return health; }
    public int getMaxHealth() { return maxHealth; }

    public void takeDamage(int damage) {
        if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative.");
        health = Math.max(0, health - damage);
    }

    public boolean isDefeated() { return health <= 0; }

    // ── combat values ────────────────────────────────────────────────────────

    public int getPlayerDamagePerWrong()  { return playerDamagePerWrong; }
    public int getBossDamagePerCorrect()  { return bossDamagePerCorrect; }

    // ── identity ──────────────────────────────────────────────────────────────

    public String getUnitName()      { return unitName; }
    public Difficulty getDifficulty(){ return difficulty; }

    @Override
    public String toString() {
        return String.format("Boss{unit='%s', health=%d/%d, difficulty=%s}",
                unitName, health, maxHealth, difficulty);
    }
}
