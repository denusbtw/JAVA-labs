package Lab3;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

public class Droid extends GameObject {
    private String name;
    private Weapon weapon;
    private int health;
    private int speed;
    private String description;
    private SoundPlayer soundPlayer;

    public Droid (Screen screen, String symbol, String name, String color, Weapon weapon,
                  int health, int speed, String description, int x, int y, SoundPlayer soundPlayer) {
        super(symbol, color, x, y, screen);
        this.name = name;
        this.weapon = weapon;
        this.health = health;
        this.speed = speed;
        this.description = description;
        this.soundPlayer = soundPlayer;
    }

    public String getName(){
        return this.name;
    }

    public int getHealth(){
        return this.health;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    public int getSpeed(){
        return this.speed;
    }

    public String getDescription(){ return this.description; }

    public int getDamage(){ return this.weapon.getDamage(); }

    public double getFireRate(){ return this.weapon.getFireRate(); }

    public void setName(String name){
        this.name = name;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void setSpeed(int speed){ this.speed = speed; }

    @Override
    public void setX(int x){
        if (x > 0 && x < getScreen().getMaxWidth()-2){
            this.x = x;
        }
    }

    public void move(String direction, ReplaySystem replaySystem){
        switch (direction){
            case "left" -> this.setX(this.getX() - this.speed);
            case "right" -> this.setX(this.getX() + this.speed);
            case "up" -> this.setY(this.getY() - this.speed);
            case "down" -> this.setY(this.getY() + this.speed);
        }

        replaySystem.logEvent("droidMove", List.of(this.getName(), String.valueOf(this.getX()),
                String.valueOf(this.getY()), direction));

        synchronized (this.getScreen()) {
            this.getScreen().clear();
            this.getScreen().drawAllObjects();
        }
    }

    public void fire(String direction, List<Droid> enemyDroids, ReplaySystem replaySystem) {
        AtomicBoolean running = new AtomicBoolean(true);

        replaySystem.logEvent("fire", List.of(this.getName(), "x: " + this.getX(), "y: " + this.getY(), direction));

        soundPlayer.playSound("sounds/blaster.wav");

        Thread t = new Thread(() -> {
            if (!this.getWeapon().canFire()){
                return;
            }

            int startY = this.getY() + (direction.equals("down") ? 1 : -1);
            String plasmaSymbol = direction.equals("down") ? "⬇" : "⬆";
            GameObject plasma = new GameObject(plasmaSymbol, this.getWeapon().getColor(), this.getX()+1, startY, this.getScreen());

            this.getScreen().addObject(plasma);

            while (running.get()) {
                int oldY = plasma.getY();
                plasma.setY(plasma.getY() + (direction.equals("down") ? 1 : -1));

                if (plasma.getY() == oldY){
                    running.set(false);
                    break;
                }

                Droid hitDroid = hasHitEnemy(enemyDroids, plasma);

                if (hitDroid != null) {
                    replaySystem.logEvent("hit", List.of(hitDroid.getName(), "x: " + hitDroid.getX(), "y: " + hitDroid.getY(), "takenDamage: " + this.getDamage()));

                    soundPlayer.playSound("sounds/hit.wav");
                    int newHealth = hitDroid.getHealth() - this.getDamage();

                    if (newHealth <= 0) {
                        if (enemyDroids.size() == 1){
//                            TODO:
                            replaySystem.logEvent("gameOver", List.of(hitDroid.getName()));
                            getScreen().endGame(hitDroid);
                        } else {
                            soundPlayer.playSound("sounds/explosion.wav");
                            replaySystem.logEvent("droidDestroyed", List.of(hitDroid.getName()));

                            enemyDroids.remove(hitDroid);
                            getScreen().removeObject(hitDroid);
                        }
                    } else {
                        changeHealth(hitDroid, newHealth, replaySystem);
                    }

                    this.getScreen().removeObject(plasma);
                    running.set(false);
                }

                synchronized (this.getScreen()) {
                    plasma.getScreen().clear();
                    plasma.getScreen().drawAllObjects();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running.set(false);
                }
            }

            synchronized (this.getScreen()) {
                plasma.getScreen().removeObject(plasma);
                plasma.getScreen().clear();
                plasma.getScreen().drawAllObjects();
            }
        });

        t.start();
    }

    public void changeHealth(Droid droid, int newHealth, ReplaySystem replaySystem){
        droid.setHealth(newHealth);

        replaySystem.logEvent("healthChanged", List.of(this.getName(), "x: " + this.getX(), "y: " + this.getY(), "newHealth: " + newHealth));
    }

    public Droid hasHitEnemy(List<Droid> enemyDroids, GameObject plasma){
        for (Droid droid: enemyDroids){
            boolean b = (plasma.getX() == droid.getX()
                    || plasma.getX() == droid.getX() + 1
                    || plasma.getX() == droid.getX() + 2)
                    && (plasma.getY() == droid.getY());
            if (b){
                return droid;
            }
        }

        return null;
    }

    public String toString(){
        return String.format("Name: %s\n", this.getName())
                + String.format("Health: %d\n", this.getHealth())
                + String.format("Movement speed: %d\n", this.getSpeed())
                + String.format("Damage: %d\n", this.getWeapon().getDamage())
                + String.format("Fire rate: %.1f\n", this.getWeapon().getFireRate())
                + String.format("Description: %s\n", this.getDescription());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Droid(
                this.getScreen(),
                this.getString(),
                this.name,
                this.getColor(),
                this.weapon,
                this.health,
                this.speed,
                this.description,
                this.getX(),
                this.getY(),
                this.soundPlayer
        );
    }
}
