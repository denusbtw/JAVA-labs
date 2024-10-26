package main.java.game.health;

public class HealthManager {
    private int currentHealth;
    private final int maxHealth;

    public HealthManager(int health) {
        this.currentHealth = health;
        this.maxHealth = health;
    }

    public int getHealth(){ return currentHealth; }

    public int getMaxHealth(){ return maxHealth; }

    public void changeHealth(int delta) {
        System.out.print("Health changed: " + currentHealth);
        currentHealth = Math.max(0, Math.min(maxHealth, currentHealth + delta));
        System.out.println(" -> " + currentHealth);

        if (currentHealth == 0){
            System.out.println("Droid destroyed!");
        }
    }
}
