package main.java.game.enemies;

import main.java.game.droid.Droid;

import java.util.ArrayList;
import java.util.List;


public class EnemyManager {
    private List<Droid> topDroids;
    private List<Droid> bottomDroids;

    public EnemyManager(){
        topDroids = new ArrayList<>();
        bottomDroids = new ArrayList<>();
    }

    public void addTopDroid(Droid droid){
        topDroids.add(droid);
    }

    public void addBottomDroid(Droid droid){
        bottomDroids.add(droid);
    }

    public void removeTopDroid(Droid droid){
        topDroids.remove(droid);
    }

    public void removeBottomDroid(Droid droid){
        bottomDroids.remove(droid);
    }

    public List<Droid> getTopDroids(){
        return topDroids;
    }

    public List<Droid> getBottomDroids(){
        return bottomDroids;
    }
}
