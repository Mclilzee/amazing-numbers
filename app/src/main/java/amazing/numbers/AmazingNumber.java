package amazing.numbers;

public class AmazingNumber {
    private final int number;

    public AmazingNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEven() {
        return this.number % 2 == 0;
    }

    public boolean isOdd() {
        return this.number % 2 != 0;
    }

    public boolean isBuzz() {
        return this.number % 7 == 0 || this.number % 10 == 7;
    }

    public boolean isDuck() {
        int copyNumber = this.number;
        while (copyNumber > 0) {
            int digit = copyNumber % 10;
            copyNumber /= 10;

            if (digit == 0) {
                return true;
            }
        }

        return false;
    }
}
