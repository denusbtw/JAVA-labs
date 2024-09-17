package Lab2;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";


    public static void main(String[] args) {
        menu();
    }

    static void printMenuChoices() {
        System.out.println("\n0. Exit ");
        System.out.println("1. With test data ");
        System.out.println("2. Manually input data ");
        System.out.println("3. Filter products by name ");
        System.out.println("4. Filter products by name and price <= ");
        System.out.println("5. Filter products by expiration after ");
        System.out.println("6. Print all products ");
    }
    
    static void menu(){
        Scanner sc = new Scanner(System.in);

        List<Product> products = new ArrayList<>();
        List<Product> filteredProducts;
        String name, expiration;
        double price;

        while (true){
            printMenuChoices();

            int choice = sc.nextInt();
            sc.skip("\n");

            switch (choice){
                case 0:
                    sc.close();
                    return;
                case 1:
                    createTestProducts(products);

                    System.out.println("All test products:");
                    printTable(products);
                    break;
                case 2:
                    System.out.println("Enter (-1) to stop");

                    while (true){
                        Product product = createProduct();
                        if (product == null){
                            System.out.println("Stopping creating product...");
                            printTable(products);
                            break;
                        }

                        products.add(product);
                    }

                    break;
                case 3:
                    if (products.isEmpty()){
                        System.out.println(ANSI_RED + "There are no products in the list" + ANSI_RESET);
                        break;
                    }

                    System.out.println("Enter name of product: ");
                    name = sc.nextLine();

                    filteredProducts = filterByName(name, products);
                    if (filteredProducts.isEmpty()){
                        System.out.printf(ANSI_RED + "Not found products with name %s\n" + ANSI_RESET, name);
                        break;
                    }

                    System.out.printf("Products with name '%s'\n", name);
                    printTable(filteredProducts);
                    break;
                case 4:
                    if (products.isEmpty()){
                        System.out.println(ANSI_RED + "There are no products in the list" + ANSI_RESET);
                        break;
                    }

                    System.out.println("Enter name of product: ");
                    name = sc.nextLine();
                    System.out.println("Enter price: ");
                    price = sc.nextDouble();
                    sc.skip("\n");

                    filteredProducts = filterByNameAndPriceLessThanOrEqual(price, name, products);
                    if (filteredProducts.isEmpty()){
                        System.out.printf(ANSI_RED + "Not found products with name %s and price %.2f$\n" + ANSI_RESET, name, price);
                        break;
                    }

                    System.out.printf("Products with name '%s' and price <= %.2f$\n", name, price);
                    printTable(filteredProducts);
                    break;
                case 5:
                    if (products.isEmpty()){
                        System.out.println(ANSI_RED + "There are no products in the list" + ANSI_RESET);
                        break;
                    }

                    System.out.println("Enter expiration: ");
                    expiration = sc.nextLine();

                    filteredProducts = filterByExpirationAfter(expiration, products);
                    System.out.printf("Products with expiration after '%s'\n", expiration);
                    printTable(filteredProducts);
                    break;
                case 6:
                    printTable(products);
                    break;
            }

            if (sc.hasNextLine()){
                sc.nextLine();
            }
        }
    }

    static void createTestProducts(List<Product> products) {
        products.add(new Product(1, "Milk", "DairyFresh", 2.99, 200, "2024-09-30"));
        products.add(new Product(2, "Bread", "Baker's Delight", 1.99, 150, "2024-09-25"));
        products.add(new Product(3, "Eggs", "FarmBest", 3.50, 300, "2024-10-10"));
        products.add(new Product(4, "Yogurt", "DairyFresh", 4.99, 100, "2024-10-05"));
        products.add(new Product(5, "Cheese", "CheddarMelt", 5.99, 80, "2024-12-15"));
        products.add(new Product(6, "Butter", "CreamySpread", 2.50, 120, "2025-01-10"));
        products.add(new Product(7, "Orange Juice", "CitrusSplash", 3.99, 200, "2024-11-20"));
        products.add(new Product(8, "Frozen Pizza", "QuickBake", 7.50, 90, "2025-06-30"));
        products.add(new Product(9, "Chicken Breast", "PoultryPrime", 9.99, 50, "2024-09-22"));
        products.add(new Product(10, "Ice Cream", "SweetTreats", 6.99, 100, "2024-09-22"));
    }

    static Product createProduct(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter id of product: ");
        int id = sc.nextInt();
        sc.skip("\n");
        if (id == -1) {
            return null;
        }

        System.out.println("Enter product name: ");
        String name = sc.nextLine();
        if (name.equals("-1")){
            return null;
        }

        System.out.println("Enter product manufacturer: ");
        String manufacturer = sc.nextLine();
        if (manufacturer.equals("-1")){
            return null;
        }

        System.out.println("Enter product price: ");
        double price = sc.nextDouble();
        sc.skip("\n");
        if (price == -1){
            return null;
        }

        System.out.println("Enter product quantity: ");
        int quantity = sc.nextInt();
        sc.skip("\n");
        if (quantity == -1){
            return null;
        }

        System.out.println("Enter product expiration date (yyyy-mm-dd): ");
        String expiration = sc.nextLine();

        if (expiration.equals("-1")){
            return null;
        }

        return new Product(id, name, manufacturer, price, quantity, expiration);
    }

    static List<Product> filterByName(String name, List<Product> products){
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product: products) {
            if (product.getName().equals(name)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    static List<Product> filterByNameAndPriceLessThanOrEqual(double price, String name, List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product: products) {
            if (product.getPrice() <= price && product.getName().equals(name)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    static List<Product> filterByExpirationAfter(String expiration, List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();

        Date expirationDate = Product.createExpirationDate(expiration);

        for (Product product: products) {
            if (product.getExpiration().after(expirationDate)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }


    static void printTable(List<Product> products) {
        String[] headers = {"ID", "Name", "Manufacturer", "Price", "Quantity", "Expiration"};


        int[] columnWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }

        if (products.isEmpty()){
            printSeparator(columnWidths);
            printRow(headers, columnWidths);
            printSeparator(columnWidths);
            return;
        }


        for (Product product : products) {
            columnWidths[0] = Math.max(columnWidths[0], String.valueOf(product.getId()).length());
            columnWidths[1] = Math.max(columnWidths[1], product.getName().length());
            columnWidths[2] = Math.max(columnWidths[2], product.getManufacturer().length());
            columnWidths[3] = Math.max(columnWidths[3], product.getFormattedPrice().length());
            columnWidths[4] = Math.max(columnWidths[4], String.valueOf(product.getQuantity()).length());
            columnWidths[5] = Math.max(columnWidths[5], product.getFormattedExpiration().length());
        }


        printSeparator(columnWidths);
        printRow(headers, columnWidths);
        printSeparator(columnWidths);
        for (Product product : products) {
            String[] row = {
                    String.valueOf(product.getId()),
                    product.getName(),
                    product.getManufacturer(),
                    product.getFormattedPrice(),
                    String.valueOf(product.getQuantity()),
                    product.getFormattedExpiration()
            };
            printRow(row, columnWidths);
        }
        printSeparator(columnWidths);
        System.out.println();
    }

    static void printRow(String[] row, int[] columnWidths) {
        System.out.print(ANSI_GREEN + "|" + ANSI_RESET);
        for (int i = 0; i < row.length; i++) {
            System.out.print(centerString(row[i], columnWidths[i]));
            System.out.print(ANSI_GREEN + "|" + ANSI_RESET);
        }
        System.out.println();
    }

    static void printSeparator(int[] columnWidths) {
        System.out.print(ANSI_GREEN);
        System.out.print("+");
        for (int width : columnWidths) {
            char[] line = new char[width + 2];
            Arrays.fill(line, '-');
            System.out.print(new String(line));
            System.out.print("+");
        }
        System.out.println(ANSI_RESET);
    }

    static String centerString(String text, int width) {
        int padding = width - text.length();
        int paddingLeft = padding / 2;
        int paddingRight = padding - paddingLeft;
        return String.format(" %s%s%s ", " ".repeat(paddingLeft), text, " ".repeat(paddingRight));
    }
}