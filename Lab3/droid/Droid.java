package Lab3.droid;

import Lab3.gamer.GameObject;


public class Droid extends GameObject {
    private String name;
    private HealthManager healthManager;
    private MovementManager movementManager;
    private int damage;
    private int speed;
    private String description;

    public Droid(int x, int y, int width, int height, String representation, String color, boolean isActive,
                 String name, int maxHealth, int damage, int speed,
                 String description) {
        super(x, y, width, height, representation, color, isActive);
        this.name = name;
        this.healthManager = new HealthManager(maxHealth, maxHealth);
        this.movementManager = new MovementManager();
        this.damage = damage;
        this.speed = speed;
        this.description = description;
    }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public HealthManager getHealthManager(){ return healthManager; }
    public void setHealthManager(HealthManager healthManager){ this.healthManager = healthManager; }

    public MovementManager getMovementManager(){ return movementManager; }
    public void setMovementManager(MovementManager movementManager){ this.movementManager = movementManager; }

    public int getDamage(){ return damage; }
    public void setDamage(int damage){ this.damage = damage; }

    public int getSpeed(){ return speed; }
    public void setSpeed(int speed){ this.speed = speed; }

    public boolean getIsActive(){ return isActive; }
    public void setIsActive(boolean isActive){ this.isActive = isActive; }

    public void move(String direction){
        movementManager.move(this, direction);
    }

    @Override
    public Object clone() {
        return new Droid(
                getX(),
                getY(),
                getWidth(),
                getHeight(),
                getRepresentation(),
                getColor(),
                getIsActive(),
                name,
                healthManager.getHealth(),
                damage,
                speed,
                description
        );
    }

}
