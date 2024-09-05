package Lab1;

public class Main {
    /**
    * This method outputs in console all Fibonacci elements that meet this requirement: element = w^2 - 1,
     * where w^2 is element of Fibonacci sequence
     * @param args command-line arguments
    * */
    public static void main(String[] args) {
        // Check only one argument was passed
        if (args.length > 1) {
            System.out.println("Pass only one argument (N - first Fibonacci numbers)");
            return;
        }

        int n = 91;

        // Check passed argument is int
        try {
            if (args.length == 0){
                System.out.println("You didn't pass N, so default value (91) of N will be used");
            } else {
                n = Integer.parseInt(args[0]);
                System.out.printf("Incoming data: N (first fibonacci numbers) = %d\n", n);
            }

            Fibonacci[] numbers = fib(n);

            for (Fibonacci i: numbers){
                double sqrt = Math.sqrt(i.value+1);

                if (sqrt == (long)sqrt){
                    System.out.printf("%d. %d\n", i.sequenceNumber, i.value);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("You should pass integer");
        }

    }

    /**
     * This class contains info about Fibonacci sequence element
     * **/
    static class Fibonacci {
        // Number
        public long value;
        // sequence number of value in Fibonacci sequence
        public int sequenceNumber;

        public Fibonacci(long value, int sequenceNumber) {
            this.value = value;
            this.sequenceNumber = sequenceNumber;
        }

        long getValue() {
            return this.value;
        }

        long getSequenceNumber() {
            return this.sequenceNumber;
        }

        void setValue(long value) {
            this.value = value;
        }

        void setSequenceNumber(int sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
        }
    }

    /**
     * This function computes elements of Fibonacci sequence and stores them in array
     * @param n maximum number of elements of Fibonacci sequence
     * @return array of Fibonacci elements
     * **/
    static Fibonacci[] fib(int n) {
        long x1 = 1, x2 = 1;

        Fibonacci[] result = new Fibonacci[n];

        for (int i=0; i < n; i++) {
            result[i] = new Fibonacci(x2, i+1);

            long x3 = x1 + x2;
            x1 = x2;
            x2 = x3;
        }

        return result;
    }
}
