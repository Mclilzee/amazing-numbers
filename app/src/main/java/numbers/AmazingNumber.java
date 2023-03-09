package numbers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class AmazingNumber {
    private long number;

    public AmazingNumber(long number) {
        this.number = number;
    }

    public long getNumber() {
        return this.number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public boolean isEven() {
        return this.number % 2 == 0;
    }

    public boolean isOdd() {
        return !isEven();
    }

    public boolean isBuzz() {
        return this.number % 7 == 0 || this.number % 10 == 7;
    }

    public boolean isDuck() {
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

    public boolean isPalindromic() {
        String stringNumber = this.number + "";
        int stringLength = stringNumber.length();
        for (int i = 0; i < stringLength / 2; i++) {
            if (stringNumber.charAt(i) != stringNumber.charAt(stringLength - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isGapful() {
        if (number / 100 == 0) {
            return false;
        }

        String[] numberArray = String.valueOf(number).split("");
        int firstAndLastDigit = Integer.parseInt(numberArray[0] + numberArray[numberArray.length - 1]);

        return number % firstAndLastDigit == 0;
    }

    public boolean isSpy() {
        String[] numbers = String.valueOf(this.number).split("");
        int product = 1;
        int sum = 0;
        for (String number : numbers) {
            int integer = Integer.parseInt(number);
            product *= integer;
            sum += integer;
        }

        return sum == product;
    }

    public boolean isSunny() {
        return checkIfSquare(this.number + 1);
    }

    public boolean isSquare() {
        return checkIfSquare(this.number);
    }

    private boolean checkIfSquare(long number) {
        double square = Math.sqrt(number);
        double floor = Math.floor(Math.sqrt(number));

        return square - floor == 0;
    }

    public boolean isJumping() {
        long numberCopy = this.number;

        while (numberCopy > 9) {
            int firstDigit = (int) numberCopy % 10;
            int secondDigit = (int) (numberCopy % 100) / 10;

            int difference = Math.abs(firstDigit - secondDigit);

            if (difference != 1) {
                return false;
            }

            numberCopy /= 10;
        }

        return true;
    }

    public boolean isHappy() {
        int sum = (int) digitsSquaredSum(number);
        while (sum > 9) {
            sum = (int) digitsSquaredSum(sum);
        }

        return sum == 1;
    }

    public boolean isSad() {
        return !isHappy();
    }

    private long digitsSquaredSum(long number) {
        int sum = 0;
        while (number > 0) {
            int digit = (int) (number % 10);
            sum += digit * digit;

            number /= 10;
        }

        return sum;
    }

    public void printDetailedProperties() {
        NumberFormat readableNumbers = NumberFormat.getInstance();
        readableNumbers.setGroupingUsed(true);

        System.out.println("Properties of " + readableNumbers.format(this.number));
        System.out.println("        buzz: " + isBuzz());
        System.out.println("        duck: " + isDuck());
        System.out.println(" palindromic: " + isPalindromic());
        System.out.println("      gapful: " + isGapful());
        System.out.println("         spy: " + isSpy());
        System.out.println("      square: " + isSquare());
        System.out.println("       sunny: " + isSunny());
        System.out.println("     jumping: " + isJumping());
        System.out.println("       happy: " + isHappy());
        System.out.println("         sad: " + isSad());
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

        if (isSpy()) {
            properties.add("spy");
        }

        if (isGapful()) {
            properties.add("gapful");
        }

        if (isSunny()) {
            properties.add("sunny");
        }

        if (isSquare()) {
            properties.add("square");
        }

        if (isJumping()) {
            properties.add("jumping");
        }

        if (isHappy()) {
            properties.add("happy");
        } else {
            properties.add("sad");
        }

        if (isEven()) {
            properties.add("even");
        } else {
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
