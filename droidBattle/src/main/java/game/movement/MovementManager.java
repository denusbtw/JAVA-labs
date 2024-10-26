package main.java.game.movement;

import main.java.game.droid.Droid;

public class MovementManager {
    public static void move(Droid droid, String direction) {
        switch (direction){
            case "left" -> droid.setX(droid.getX() - droid.getMovementSpeed());
            case "right" -> droid.setX(droid.getX() + droid.getMovementSpeed());
            case "up" -> droid.setY(droid.getY() - droid.getMovementSpeed());
            case "down" -> droid.setY(droid.getY() + droid.getMovementSpeed());
        }
    }
}
