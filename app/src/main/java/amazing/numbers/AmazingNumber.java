package amazing.numbers;

public class AmazingNumber {
    private final long number;

    public AmazingNumber(long number) {
        this.number = number;
    }

    private boolean isEven() {
        return this.number % 2 == 0;
    }

    private boolean isOdd() {
        return this.number % 2 != 0;
    }

    private boolean isBuzz() {
        return this.number % 7 == 0 || this.number % 10 == 7;
    }

    private boolean isDuck() {
        long copyNumber = this.number;
        while (copyNumber > 0) {
            int digit = (int) copyNumber % 10;
            copyNumber /= 10;

            if (digit == 0) {
                return true;
            }
        }

        return false;
    }

    public void printProperties() {
        System.out.println("Properties of " + this.number);
        System.out.println("\teven: " + isEven());
        System.out.println("\todd: " + isOdd());
        System.out.println("\tbuzz: " + isBuzz());
        System.out.println("\tduck: " + isDuck());
    }
}
