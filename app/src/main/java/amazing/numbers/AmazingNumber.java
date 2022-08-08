package amazing.numbers;

import java.text.NumberFormat;

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
            byte digit = (byte) (copyNumber % 10);
            copyNumber /= 10;

            if (digit == 0) {
                return true;
            }
        }

        return false;
    }

    private boolean isPalindromic() {
        String stringNumber = this.number + "";
        int stringLength = stringNumber.length();
        for (int i = 0; i < stringLength / 2; i++) {
            if (stringNumber.charAt(i) != stringNumber.charAt(stringLength - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isGapful() {
        if (number / 100 == 0) {
            return false;
        }

        String[] numberArray = String.valueOf(number).split("");
        int firstAndLastDigit = Integer.parseInt(numberArray[0] + numberArray[numberArray.length - 1]);

        return number % firstAndLastDigit == 0;
    }

    public void printDetailedProperties() {
        NumberFormat readableNumbers = NumberFormat.getInstance();
        readableNumbers.setGroupingUsed(true);

        System.out.println("Properties of " + readableNumbers.format(this.number));
        System.out.println("        even: " + isEven());
        System.out.println("         odd: " + isOdd());
        System.out.println("        buzz: " + isBuzz());
        System.out.println("        duck: " + isDuck());
        System.out.println(" palindromic: " + isPalindromic());
    }

    public void printSimpleProperties() {

    }
}
