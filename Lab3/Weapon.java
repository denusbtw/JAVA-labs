package Lab3;

import java.io.Serializable;

public class Weapon {
    private String color;
    private int damage;
    private double fireRate;
    private long lastFireTime = 0;

    public Weapon(String color, int damage, double fireRate){
        this.color = color;
        this.damage = damage;
        this.fireRate = fireRate;
    }

    public String getColor(){ return this.color; }

    public int getDamage(){ return this.damage; }

    public double getFireRate(){ return this.fireRate; }

    void setColor(String color){ this.color = color; }

    void setDamage(int damage){ this.damage = damage; }

    void setFireRate(int fireRate){ this.fireRate = fireRate; }

    public boolean canFire() {
        long currentTime = System.currentTimeMillis();
        long timeBetweenShots = (long) (1000 / this.fireRate);

        if (currentTime - lastFireTime >= timeBetweenShots) {
            lastFireTime = currentTime;
            return true;
        }
        return false;
    }
}
