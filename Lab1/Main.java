package Lab1;

/**
 * Main class
 * **/
public class Main {
    /**
     * This method checks command-line arguments
     * If passed integer or no arguments - calls function that prints all possible fibonacci elements
     * Else tells user to pass correct argument
     * @param args command-line arguments
    * */
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Pass one argument (N)");
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);
            System.out.printf("Input: N (number of first fibonacci numbers) = %d\n", n);

            printAllPossibleFibonacciElements(n);

        } catch (NumberFormatException e) {
            System.out.println("You should pass integer");
        }
    }

    /**
     * This method outputs in command-line all Fibonacci elements that meet this requirement:
     * element = w^2 - 1, where w^2 is any square number
     * @param n quantity of first numbers of Fibonacci sequence
     * */
    public static void printAllPossibleFibonacciElements(int n) {
        Fibonacci a = new Fibonacci(1, 1);
        Fibonacci b = new Fibonacci(1, 2);

        System.out.println("Output data: sequenceNumber. value");
        for (int i=0; i<n; i++){
            Fibonacci c = new Fibonacci(a.getValue()+b.getValue(), i+2);

            if (c.meetsRequirement()){
                System.out.printf("%d. %d\n", c.getSequenceNumber(), c.getValue());
            }

            a = b;
            b = c;
        }
    }
}
