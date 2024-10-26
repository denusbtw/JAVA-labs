package src.utils;

public class ColorConverter {
    public static String getTextColor(String colorName){
        switch (colorName.toLowerCase()){
            case "black" -> { return "\033[1;30m"; }
            case "red" -> { return "\033[1;31m"; }
            case "green" -> { return "\033[1;32m"; }
            case "yellow" -> { return "\033[1;33m"; }
            case "blue" -> { return "\033[1;34m"; }
            case "purple" -> { return "\033[1;35m"; }
            case "cyan" -> { return "\033[1;36m"; }
            case "white" -> { return "\033[1;37m"; }
        }

        return null;
    }

    public static String getBackgroundColor(String colorName){
        switch (colorName.toLowerCase()){
            case "black" -> { return "\033[1;40m"; }
            case "red" -> { return "\033[1;41m"; }
            case "green" -> { return "\033[1;42m"; }
            case "yellow" -> { return "\033[1;43m"; }
            case "blue" -> { return "\033[1;44m"; }
            case "purple" -> { return "\033[1;45m"; }
            case "cyan" -> { return "\033[1;46m"; }
            case "white" -> { return "\033[1;47m"; }
        }

        return null;
    }

    public static String getReset(){
        return "\033[0m";
    }
}
