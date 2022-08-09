package amazing.numbers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("        buzz: " + isBuzz());
        System.out.println("        duck: " + isDuck());
        System.out.println(" palindromic: " + isPalindromic());
        System.out.println("      gapful: " + isGapful());
        System.out.println("        even: " + isEven());
        System.out.println("         odd: " + isOdd());
    }

    public void printSimpleProperties() {
        List<String> properties = new ArrayList<>();

        if (isBuzz()) {
            properties.add("buzz");
        }

        if (isDuck()) {
            properties.add("duck");
        }

        if (isPalindromic()) {
            properties.add("palindromic");
        }

        if (isGapful()) {
            properties.add("gapful");
        }

        if (isEven()) {
            properties.add("even");
        }

        if (isOdd()) {
            properties.add("odd");
        }

        System.out.printf("\t\t%d is ", number);

        for (int i = 0; i < properties.size(); i++) {
            if (i == properties.size() - 1) {
                System.out.print(properties.get(i));
            } else {
                System.out.print(properties.get(i) + ", ");
            }
        }
        System.out.println();
    }
}
