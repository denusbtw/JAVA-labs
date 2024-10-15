package Lab3;

public class ColorConverter {
    protected String ANSI_RESET = "\033[0m";
    protected String ANSI_BLACK = "\033[1;30m";
    protected String ANSI_RED = "\033[1;31m";
    protected String ANSI_GREEN = "\033[1;32m";
    protected String ANSI_YELLOW = "\033[1;33m";
    protected String ANSI_BLUE = "\033[1;34m";
    protected String ANSI_PURPLE = "\033[1;35m";
    protected String ANSI_CYAN = "\033[1;36m";
    protected String ANSI_WHITE = "\033[1;37m";
    protected String ANSI_BLACK_BACKGROUND = "\033[1;40m";
    protected String ANSI_RED_BACKGROUND = "\033[1;41m";
    protected String ANSI_GREEN_BACKGROUND = "\033[1;42m";
    protected String ANSI_YELLOW_BACKGROUND = "\033[1;43m";
    protected String ANSI_BLUE_BACKGROUND = "\033[1;44m";
    protected String ANSI_PURPLE_BACKGROUND = "\033[1;45m";
    protected String ANSI_CYAN_BACKGROUND = "\033[1;46m";
    protected String ANSI_WHITE_BACKGROUND = "\033[1;47m";

    public String getTextColor(String colorName){
        switch (colorName.toLowerCase()){
            case "black" -> { return this.ANSI_BLACK; }
            case "red" -> { return this.ANSI_RED; }
            case "green" -> { return this.ANSI_GREEN; }
            case "yellow" -> { return this.ANSI_YELLOW; }
            case "blue" -> { return this.ANSI_BLUE; }
            case "purple" -> { return this.ANSI_PURPLE; }
            case "cyan" -> { return this.ANSI_CYAN; }
            case "white" -> { return this.ANSI_WHITE; }
            case "reset" -> { return this.ANSI_RESET; }
        }

        return colorName;
    }

    public String getBackgroundColor(String colorName){
        switch (colorName.toLowerCase()){
            case "black" -> { return this.ANSI_BLACK_BACKGROUND; }
            case "red" -> { return this.ANSI_RED_BACKGROUND; }
            case "green" -> { return this.ANSI_GREEN_BACKGROUND; }
            case "yellow" -> { return this.ANSI_YELLOW_BACKGROUND; }
            case "blue" -> { return this.ANSI_BLUE_BACKGROUND; }
            case "purple" -> { return this.ANSI_PURPLE_BACKGROUND; }
            case "cyan" -> { return this.ANSI_CYAN_BACKGROUND; }
            case "white" -> { return this.ANSI_WHITE_BACKGROUND; }
            case "reset" -> { return this.ANSI_RESET; }
        }

        return colorName;
    }
}
