package bigstats.model;

public class Boss {

    public enum Difficulty { EASY, MEDIUM, HARD }

    private final String unitName;
    private int health;
    private final int maxHealth;
    private final Difficulty difficulty;
    private final int dmgToPlayer;   // damage dealt to player on wrong answer
    private final int dmgToBoss;     // damage dealt to boss on correct answer

    public Boss(String unitName, int maxHealth, Difficulty difficulty) {
        if (maxHealth <= 0) throw new IllegalArgumentException("maxHealth must be positive.");
        this.unitName   = unitName;
        this.maxHealth  = maxHealth;
        this.health     = maxHealth;
        this.difficulty = difficulty;

        switch (difficulty) {
            case EASY   -> { dmgToPlayer = 10; dmgToBoss = 25; }
            case HARD   -> { dmgToPlayer = 20; dmgToBoss = 15; }
            default     -> { dmgToPlayer = 15; dmgToBoss = 20; } // MEDIUM
        }
    }

    public String getUnitName()           { return unitName; }
    public int getHealth()                { return health; }
    public int getMaxHealth()             { return maxHealth; }
    public Difficulty getDifficulty()     { return difficulty; }
    public int getPlayerDamagePerWrong()  { return dmgToPlayer; }
    public int getBossDamagePerCorrect()  { return dmgToBoss; }
    public boolean isDefeated()           { return health <= 0; }

    public void takeDamage(int dmg) {
        if (dmg < 0) throw new IllegalArgumentException("Damage cannot be negative.");
        health = Math.max(0, health - dmg);
    }

    @Override
    public String toString() {
        return String.format("Boss{unit='%s', hp=%d/%d, difficulty=%s}",
                unitName, health, maxHealth, difficulty);
    }
}
