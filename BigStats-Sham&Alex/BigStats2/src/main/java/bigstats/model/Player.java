package bigstats.model;

public class Player {
    public static final int DEFAULT_MAX_HEALTH = 100;

    private final String name;
    private final int maxHealth;
    private int health, correct, answered;

    public Player(String name) { this(name, DEFAULT_MAX_HEALTH); }
    public Player(String name, int maxHealth) {
        if (maxHealth <= 0) throw new IllegalArgumentException("maxHealth must be positive.");
        this.name = name;
        this.maxHealth = health = maxHealth;
    }

    public String getName()      { return name; }
    public int    getHealth()    { return health; }
    public int    getMaxHealth() { return maxHealth; }
    public int    getScore()     { return correct; }
    public int    getTotalAnswered() { return answered; }
    public boolean isAlive()     { return health > 0; }
    public double getAccuracy()  { return answered == 0 ? 0.0 : (double) correct / answered; }

    public void takeDamage(int d) {
        if (d < 0) throw new IllegalArgumentException("Damage cannot be negative.");
        health = Math.max(0, health - d);
    }
    public void heal(int a) {
        if (a < 0) throw new IllegalArgumentException("Heal amount cannot be negative.");
        health = Math.min(maxHealth, health + a);
    }
    public void recordCorrect()   { correct++; answered++; }
    public void recordIncorrect() { answered++; }
}
