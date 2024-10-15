package Lab3;

public class DroidHud extends GameObject {
    private final Droid droid1;
    private final Droid droid2;
    private final String position;

    public DroidHud(Droid droid1, String position, int x, int y, Screen screen) {
        super("", "white", x, y, screen);
        this.position = position;
        this.droid1 = droid1;
        this.droid2 = null;
    }

    public DroidHud(Droid droid1, Droid droid2, String position, int x, int y, Screen screen) {
        super("", "white", x, y, screen);
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.position = position;
    }

    @Override
    public void draw() {
        getScreen().moveCursor(getX(), getY());

        int screenWidth = getScreen().getMaxWidth();

        int halfScreenWidth = screenWidth / 2;

        String separatorLine = "=".repeat(screenWidth);

        if (this.position.equals("bottom")) {
            System.out.print(separatorLine);
        }

        String string1 = String.format("♥ %d\t\uD83D\uDDE1 %d\t\uD83D\uDDF2 %d\t%s",
                this.droid1.getHealth(), this.droid1.getDamage(), this.droid1.getSpeed(), this.droid1.getName());

        System.out.print(string1);

        if (this.droid2 != null){
            // Calculate the remaining space for the second droid (right side)
            String string2 = String.format("♥ %d\t\uD83D\uDDE1 %d\t\uD83D\uDDF2 %d\t%s",
                    this.droid2.getHealth(), this.droid2.getDamage(), this.droid2.getSpeed(), this.droid2.getName());

            int remainingSpace = (halfScreenWidth - string1.length() - 11)  + (halfScreenWidth - string2.length() - 11);

            System.out.print(" ".repeat(remainingSpace > 0 ? remainingSpace : 1));

            System.out.print(string2);
        }

        // If HUD is for the top position, print the top separator line
        if (this.position.equals("top")) {
            System.out.println();
            System.out.print(separatorLine);
        }
    }
}
