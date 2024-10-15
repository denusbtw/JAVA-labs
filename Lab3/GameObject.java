package Lab3;

public class GameObject {
    protected String string;
    protected String color;
    protected int x;
    protected int y;
    private final Screen screen;
    private final ColorConverter colorConverter;

    public GameObject(String string, String color, int x, int y, Screen screen) {
        this.string = string;
        this.color = color;
        this.x = x;
        this.y = y;
        this.screen = screen;
        this.colorConverter = new ColorConverter();
    }

    public String getString() { return this.string; }

    public String getColor() { return this.color; }

    public int getX(){ return this.x; }

    public int getY(){ return this.y; }

    public Screen getScreen(){ return this.screen; }

    public ColorConverter getColorConverter(){ return this.colorConverter; }

    public void setString(String string) { this.string = string; }

    public void setColor(String color) { this.color = color; }

    public void setX(int x){
        if (x > 0 && x < screen.getMaxWidth()){
            this.x = x;
        }
    }

    public void setY(int y){
        if (y > 2 && y < screen.getMaxHeight()-1){
            this.y = y;
        }
    }

    public void draw(){
        String ANSI_COLOR = this.colorConverter.getTextColor(this.color);
        String ANSI_RESET = this.colorConverter.ANSI_RESET;
        System.out.printf("\033[%d;%df %s%s%s", this.getY(), this.getX(), ANSI_COLOR, this.string, ANSI_RESET);
    }
}
