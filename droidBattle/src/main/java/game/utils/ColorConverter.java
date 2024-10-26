package main.java.game.utils;

public class ColorConverter {
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_BLACK = "\033[1;30m";
    public static final String ANSI_RED = "\033[1;31m";
    public static final String ANSI_GREEN = "\033[1;32m";
    public static final String ANSI_YELLOW = "\033[1;33m";
    public static final String ANSI_BLUE = "\033[1;34m";
    public static final String ANSI_PURPLE = "\033[1;35m";
    public static final String ANSI_CYAN = "\033[1;36m";
    public static final String ANSI_WHITE = "\033[1;37m";
    public static final String ANSI_BLACK_BACKGROUND = "\033[1;40m";
    public static final String ANSI_RED_BACKGROUND = "\033[1;41m";
    public static final String ANSI_GREEN_BACKGROUND = "\033[1;42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\033[1;43m";
    public static final String ANSI_BLUE_BACKGROUND = "\033[1;44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\033[1;45m";
    public static final String ANSI_CYAN_BACKGROUND = "\033[1;46m";
    public static final String ANSI_WHITE_BACKGROUND = "\033[1;47m";

    public static String getTextColor(String colorName){
        switch (colorName.toLowerCase()){
            case "black" -> { return ANSI_BLACK; }
            case "red" -> { return ANSI_RED; }
            case "green" -> { return ANSI_GREEN; }
            case "yellow" -> { return ANSI_YELLOW; }
            case "blue" -> { return ANSI_BLUE; }
            case "purple" -> { return ANSI_PURPLE; }
            case "cyan" -> { return ANSI_CYAN; }
            case "white" -> { return ANSI_WHITE; }
            case "reset" -> { return ANSI_RESET; }
        }

        return colorName;
    }

    public static String getBackgroundColor(String colorName){
        switch (colorName.toLowerCase()){
            case "black" -> { return ANSI_BLACK_BACKGROUND; }
            case "red" -> { return ANSI_RED_BACKGROUND; }
            case "green" -> { return ANSI_GREEN_BACKGROUND; }
            case "yellow" -> { return ANSI_YELLOW_BACKGROUND; }
            case "blue" -> { return ANSI_BLUE_BACKGROUND; }
            case "purple" -> { return ANSI_PURPLE_BACKGROUND; }
            case "cyan" -> { return ANSI_CYAN_BACKGROUND; }
            case "white" -> { return ANSI_WHITE_BACKGROUND; }
            case "reset" -> { return ANSI_RESET; }
        }

        return colorName;
    }

    public static String getReset(){
        return ANSI_RESET;
    }
}