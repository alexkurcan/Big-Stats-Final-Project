package bigstats.model;

public class Boss {
    public enum Difficulty { EASY, MEDIUM, HARD }

    private final String unitName;
    private final int maxHealth;
    private final Difficulty difficulty;
    private final int dmgToPlayer, dmgToBoss;
    private int health;

    public Boss(String unitName, int maxHealth, Difficulty difficulty) {
        if (maxHealth <= 0) throw new IllegalArgumentException("maxHealth must be positive.");
        this.unitName   = unitName;
        this.maxHealth  = health = maxHealth;
        this.difficulty = difficulty;
        dmgToPlayer = difficulty == Difficulty.EASY ? 10 : difficulty == Difficulty.HARD ? 20 : 15;
        dmgToBoss   = difficulty == Difficulty.EASY ? 25 : difficulty == Difficulty.HARD ? 15 : 20;
    }

    public String     getUnitName()          { return unitName; }
    public int        getHealth()            { return health; }
    public int        getMaxHealth()         { return maxHealth; }
    public Difficulty getDifficulty()        { return difficulty; }
    public int        getPlayerDamagePerWrong()  { return dmgToPlayer; }
    public int        getBossDamagePerCorrect()  { return dmgToBoss; }
    public boolean    isDefeated()           { return health <= 0; }

    public void takeDamage(int d) {
        if (d < 0) throw new IllegalArgumentException("Damage cannot be negative.");
        health = Math.max(0, health - d);
    }
}
