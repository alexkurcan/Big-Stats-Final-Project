package bigstats.model;

public class Player {

    public static final int DEFAULT_MAX_HEALTH = 100;

    private final String name;
    private int health;
    private final int maxHealth;
    private int correct;
    private int answered;

    public Player(String name) { this(name, DEFAULT_MAX_HEALTH); }

    public Player(String name, int maxHealth) {
        if (maxHealth <= 0) throw new IllegalArgumentException("maxHealth must be positive.");
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public String getName()     { return name; }
    public int getHealth()      { return health; }
    public int getMaxHealth()   { return maxHealth; }
    public int getScore()       { return correct; }
    public int getTotalAnswered(){ return answered; }
    public boolean isAlive()    { return health > 0; }

    public void takeDamage(int dmg) {
        if (dmg < 0) throw new IllegalArgumentException("Damage cannot be negative.");
        health = Math.max(0, health - dmg);
    }

    public void heal(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Heal amount cannot be negative.");
        health = Math.min(maxHealth, health + amount);
    }

    public void recordCorrect()   { correct++; answered++; }
    public void recordIncorrect() { answered++; }

    public double getAccuracy() {
        return answered == 0 ? 0.0 : (double) correct / answered;
    }

    @Override
    public String toString() {
        return String.format("Player{name='%s', hp=%d/%d, score=%d/%d}",
                name, health, maxHealth, correct, answered);
    }
}
