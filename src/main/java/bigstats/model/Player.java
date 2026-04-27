package bigstats.model;

/**
 * Represents the player in Big Stats.
 */
public class Player {

    public static final int DEFAULT_MAX_HEALTH = 100;

    private final String name;
    private int health;
    private final int maxHealth;
    private int score;          // total correct answers across the whole game
    private int totalAnswered;  // total questions answered

    public Player(String name) {
        this(name, DEFAULT_MAX_HEALTH);
    }

    public Player(String name, int maxHealth) {
        if (maxHealth <= 0) throw new IllegalArgumentException("maxHealth must be positive.");
        this.name      = name;
        this.maxHealth = maxHealth;
        this.health    = maxHealth;
        this.score     = 0;
        this.totalAnswered = 0;
    }

    // ── health ──────────────────────────────────────────────────────────────

    public int getHealth()    { return health; }
    public int getMaxHealth() { return maxHealth; }

    /** Reduce health by damage. Health cannot go below 0. */
    public void takeDamage(int damage) {
        if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative.");
        health = Math.max(0, health - damage);
    }

    /** Restore health by amount, capped at maxHealth. */
    public void heal(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Heal amount cannot be negative.");
        health = Math.min(maxHealth, health + amount);
    }

    public boolean isAlive() { return health > 0; }

    // ── scoring ─────────────────────────────────────────────────────────────

    public int getScore()        { return score; }
    public int getTotalAnswered(){ return totalAnswered; }

    /** Record a correct answer. */
    public void recordCorrect() {
        score++;
        totalAnswered++;
    }

    /** Record an incorrect answer. */
    public void recordIncorrect() {
        totalAnswered++;
    }

    /**
     * Returns the player's accuracy as a value between 0.0 and 1.0.
     * Returns 0.0 if no questions have been answered yet.
     */
    public double getAccuracy() {
        if (totalAnswered == 0) return 0.0;
        return (double) score / totalAnswered;
    }

    // ── identity ─────────────────────────────────────────────────────────────

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("Player{name='%s', health=%d/%d, score=%d/%d (%.0f%%)}",
                name, health, maxHealth, score, totalAnswered, getAccuracy() * 100);
    }
}
