package main.java.game.core;

import main.java.game.utils.ColorConverter;

public class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private String representation;
    private String color;
    private final TerminalDisplay terminalDisplay;

    public GameObject(int x, int y, int width, int height, String representation, String color,
                      TerminalDisplay terminalDisplay) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.representation = representation;
        this.color = color;
        this.terminalDisplay = terminalDisplay;
    }

    public int getX(){ return x; }
    public void setX(int x){
        if (x > 0 && x < terminalDisplay.getWidth()){
            this.x = x;
        }
    }

    public int getY(){ return y; }
    public void setY(int y){
        if (y > 2 && y < terminalDisplay.getHeight()-1){
            this.y = y;
        }
    }

    public int getWidth(){ return width; }
    public void setWidth(int width){ this.width = width; }

    public int getHeight(){ return height; }
    public void setHeight(int height){ this.height = height; }

    public String getRepresentation(){ return representation; }
    public void setRepresentation(String representation){ this.representation = representation; }

    public String getColor(){ return color; }
    public void setColor(String color){ this.color = color; }

    public TerminalDisplay getDisplay(){ return terminalDisplay; }

    public void render(){
        terminalDisplay.moveTo(x, y);

        String ANSI_TEXT_COLOR = ColorConverter.getTextColor(color);
        String ANSI_RESET = ColorConverter.getReset();
        System.out.printf("%s%s%s", ANSI_TEXT_COLOR, representation, ANSI_RESET);
    }
}
