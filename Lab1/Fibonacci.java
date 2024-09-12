package Lab1;

/**
 * Class that contains information about element of Fibonacci sequence
 * **/
public class Fibonacci {
    /** The value of the Fibonacci number */
    private long value;
    /** The sequence number of the Fibonacci number */
    private int sequenceNumber;

    /**
     * Constructor to initialize Fibonacci number and its sequence position.
     * @param value the actual value of the Fibonacci number
     * @param sequenceNumber the position of the Fibonacci number in the sequence
     */
    public Fibonacci(long value, int sequenceNumber) {
        this.value = value;
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Returns the value of the Fibonacci number.
     * @return Fibonacci value
     */
    public long getValue() {
        return this.value;
    }

    /**
     * Returns the sequence number of the Fibonacci number.
     * @return the sequence number
     */
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    /**
     * Sets the value of the Fibonacci number.
     * @param value the new value to set
     */
    public void setValue(long value) {
        this.value = value;
    }

    /**
     * Sets the sequence number of the Fibonacci number.
     * @param sequenceNumber the new sequence number to set
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Returns true if Fibonacci element meets this requirement:
     * element = w^2 - 1, where w^2 is any square number
     * */
    public boolean meetsRequirement() {
        double sqrt = Math.sqrt(this.value+1);

        return (sqrt == (long)sqrt);
    }
}
