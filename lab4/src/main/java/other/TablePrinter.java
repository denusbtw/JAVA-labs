package other;

import model.Tour;

import java.util.*;

public class TablePrinter {
    private Map<String, Integer> headers;

    public TablePrinter() {
        headers = new LinkedHashMap<>();

        headers.put("Index", "Index".length());
        headers.put("Name", "Name".length());
        headers.put("Type", "Type".length());
        headers.put("Transport", "Transport".length());
        headers.put("Meal option", "Meal option".length());
        headers.put("Number of days", "Number of days".length());
        headers.put("Price", "Price".length()+1);
        headers.put("Rating", "Rating".length());
    }

    public void print(List<Tour> tours){
        headers = adjustHeadersLengths(tours);

        String lastKey = headers.keySet().toArray(new String[0])[headers.size()-1];

        headers.forEach((header, length) -> {
            int paddingLeft = (length - header.length()) / 2;
            int paddingRight = length - header.length() - paddingLeft;

            System.out.print(" ".repeat(paddingLeft+1) + header + " ".repeat(paddingRight+1));

            if (!header.equals(lastKey)) {
                System.out.print("|");
            }
        });

        System.out.println();
        headers.forEach((header, length) -> {
            System.out.print("-".repeat(length+2));

            if (!header.equals(lastKey)){
                System.out.print("+");
            }
        });

        System.out.println();
        for (int i=0; i < tours.size(); i++) {
            Tour tour = tours.get(i);

            System.out.print(" ".repeat(headers.get("Index") - String.valueOf(i+1).length() + 1) + (i+1) + " |");
            System.out.print(" " + tour.getName() + " ".repeat(headers.get("Name") - tour.getName().length() + 1) + "|");
            System.out.print(" " + tour.getType() + " ".repeat(headers.get("Type") - tour.getType().length() + 1) + "|");
            System.out.print(" " + tour.getTransport() + " ".repeat(headers.get("Transport") - tour.getTransport().length() + 1) + "|");
            System.out.print(" " + tour.getMealOption() + " ".repeat(headers.get("Meal option") - tour.getMealOption().length() + 1) + "|");
            System.out.print(" ".repeat(headers.get("Number of days") - String.valueOf(tour.getNumberOfDays()).length() + 1) + tour.getNumberOfDays() + " |");
            System.out.print(" ".repeat(headers.get("Price") - String.valueOf(tour.getPrice()).length()) + tour.getPrice() + "$ |");
            System.out.print(" ".repeat(headers.get("Rating") - String.valueOf(tour.getRating()).length() + 1) + tour.getRating() + "\n");
        }
    }

    private Map<String, Integer> adjustHeadersLengths(List<Tour> tours){
        int maxIndexLength = headers.get("Index");
        int maxNameLength = headers.get("Name");
        int maxTypeLength = headers.get("Type");
        int maxTransportLength = headers.get("Transport");
        int maxMealOptionLength = headers.get("Meal option");
        int maxNumberOfDaysLength = headers.get("Number of days");
        int maxPriceLength = headers.get("Price");
        int maxRatingLength = headers.get("Rating");

        Map<String, Integer> adjustedHeaders = new LinkedHashMap<>();

        for (int i=0; i<tours.size(); i++){
            String indexStr = String.valueOf(i+1);
            if(indexStr.length() > maxIndexLength){
                maxIndexLength = indexStr.length();
            }

            String name = tours.get(i).getName();
            if (name.length() > maxNameLength){
                maxNameLength = name.length();
            }

            String type = tours.get(i).getType();
            if (type.length() > maxTypeLength){
                maxTypeLength = type.length();
            }

            String transport = tours.get(i).getTransport();
            if (transport.length() > maxTransportLength){
                maxTransportLength = transport.length();
            }

            String mealOption = tours.get(i).getMealOption();
            if (mealOption.length() > maxMealOptionLength){
                maxMealOptionLength = mealOption.length();
            }

            String numberOfDaysStr = String.valueOf(tours.get(i).getNumberOfDays());
            if (numberOfDaysStr.length() > maxNumberOfDaysLength){
                maxNumberOfDaysLength = numberOfDaysStr.length();
            }

            String priceStr = String.valueOf(tours.get(i).getPrice());
            if (priceStr.length() + 1 > maxPriceLength) {
                maxPriceLength = priceStr.length() + 1;
            }

            String ratingStr = String.valueOf(tours.get(i).getRating());
            if (ratingStr.length() > maxRatingLength){
                maxRatingLength = ratingStr.length();
            }
        }

        adjustedHeaders.put("Index", maxIndexLength);
        adjustedHeaders.put("Name", maxNameLength);
        adjustedHeaders.put("Type", maxTypeLength);
        adjustedHeaders.put("Transport", maxTransportLength);
        adjustedHeaders.put("Meal option", maxMealOptionLength);
        adjustedHeaders.put("Number of days", maxNumberOfDaysLength);
        adjustedHeaders.put("Price", maxPriceLength);
        adjustedHeaders.put("Rating", maxRatingLength);

        return adjustedHeaders;
    }
}
