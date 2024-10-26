package Lab3.app;

import Lab3.droid.Droid;

import java.util.ArrayList;
import java.util.List;


public class DroidInitializer {
    public static List<Droid> initializeDroids() {
        List<Droid> droids = new ArrayList<>();

        Droid blazeBot = new Droid(
                0,
                0,
                3,
                1,
                "[*]",
                "red",
                true,
                "BlazeBot",
                150,
                25,
                2,
                "BlazeBot is a fierce combatant equipped with a highly powerful yellow weapon. Its attacks are swift, delivering solid damage while maintaining a good pace."
                );

        Droid aquaDroid = new Droid(
                0,
                0,
                3,
                1,
                "[&]",
                "blue",
                true,
                "AquaDroid",
                120,
                18,
                1,
                "AquaDroid represents stability on the battlefield. With a purple weapon that balances damage and speed, it excels in tactical engagements."
                );

        Droid smashTrooper = new Droid(
                0,
                0,
                3,
                1,
                "[#]",
                "green",
                true,
                "SmashTrooper",
                180,
                30,
                2,
                "SmashTrooper is a heavy-hitting combat machine. Its red weapon delivers crushing blows with slow, devastating attacks."
                );

        Droid laserScout = new Droid(
                0,
                0,
                3,
                1,
                "[+]",
                "yellow",
                true,
                "LaserScout",
                110,
                20,
                3,
                "LaserScout is an agile fighter, using quick attacks to deal consistent damage while rapidly repositioning on the battlefield."
                );

        Droid voltAssault = new Droid(
                0,
                0,
                3,
                1,
                "[%]",
                "purple",
                true,
                "VoltAssault",
                130,
                22,
                1,
                "VoltAssault is a versatile fighter with a balance between damage and speed, capable of holding its own in prolonged battles."
                );

        droids.add(blazeBot);
        droids.add(aquaDroid);
        droids.add(smashTrooper);
        droids.add(laserScout);
        droids.add(voltAssault);

        return droids;
    }
}
