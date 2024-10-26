package main.java.game.droid;

import main.java.game.core.GameObject;
import main.java.game.core.TerminalDisplay;
import main.java.game.health.HealthManager;

public class Droid extends GameObject {
    private String name;
    private final HealthManager healthManager;
    private int damage;
    private int movementSpeed;

    public Droid(int x, int y, int width, int height, String representation, String color,
                 TerminalDisplay terminalDisplay, String name, int health, int damage, int movementSpeed) {
        super(x, y, width, height, representation, color, terminalDisplay);
        this.name = name;
        healthManager = new HealthManager(health);
        this.damage = damage;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void setX(int x){
        if (x > 0 && x < getDisplay().getWidth()-2){
            super.setX(x);
        }
    }

    public boolean isAlive(){ return healthManager.getHealth() > 0; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public HealthManager getHealthManager() { return healthManager; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public int getMovementSpeed() { return movementSpeed; }
    public void setMovementSpeed(int movementSpeed) { this.movementSpeed = movementSpeed; }
}
