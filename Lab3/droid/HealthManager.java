package Lab3.droid;


public class HealthManager {
    private int health;
    private final int maxHealth;

    public HealthManager(int initialHealth, int maxHealth) {
        this.health = initialHealth;
        this.maxHealth = maxHealth;
    }

    public int getHealth(){ return health; }

    public int getMaxHealth(){ return maxHealth; }

    public void changeHealth(int delta) {
        System.out.print("Health changed: " + this.health);
        this.health = Math.max(0, Math.min(maxHealth, this.health + delta));
        System.out.println(" -> " + this.health);

        if (this.health == 0){
            System.out.println("Droid destroyed!");
        }
    }
}
