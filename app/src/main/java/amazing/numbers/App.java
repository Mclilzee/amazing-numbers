package amazing.numbers;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        inputRequest();
    }

    public static void inputRequest() {
        Scanner scanner = new Scanner(System.in);
        printInstructions();

        while (true) {
            System.out.println();
            System.out.print("Enter a request: ");
            String[] userInputs = scanner.nextLine().split(" ");
            System.out.println();

            if (!isValidInput(userInputs)) {
                continue;
            }

            if (userInputs.length == 1 && userInputs[0].equals("0")) {
                System.out.println("Goodbye!");
                break;
            }

            if (userInputs.length == 1) {
                singleInputProcessing(userInputs);
            } else if (userInputs.length == 2) {
                numberRangeInput(userInputs);
            } else if (userInputs.length == 3) {
                propertySearchInput(userInputs);
            } else {
                System.out.println("Wrong input values range.");
            }
        }
    }

    public static void numberRangeInput(String[] inputs) {
        long number = Long.parseLong(inputs[0]);
        int length = Integer.parseInt(inputs[1]);

        for (int i = 0; i < length; i++) {
            new AmazingNumber(number + i).printSimpleProperties();
        }
    }

    public static void propertySearchInput(String[] inputs) {
        long number = Long.parseLong(inputs[0]);
        int iterate = Integer.parseInt(inputs[1]);
        String searchProperty = inputs[2];

        for (long i = 0; i < Long.MAX_VALUE; i++) {
            AmazingNumber amazingNumber = new AmazingNumber(number + i);
            if (checkProperty(amazingNumber, searchProperty)) {
                amazingNumber.printSimpleProperties();
                iterate--;
            }

            if (iterate == 0) {
                break;
            }
        }
    }

    private static boolean checkProperty(AmazingNumber number, String property) {

        switch (property) {
            case "buzz":
                return number.isBuzz();
            case "duck":
                return number.isDuck();
            case "palindromic":
                return number.isPalindromic();
            case "gapful":
                return number.isGapful();
            case "spy":
                return number.isSpy();
            case "even":
                return number.isEven();
            case "odd":
                return number.isOdd();
        }

        throw new InvalidParameterException();
    }

    public static void singleInputProcessing(String[] input) {
        long number = Long.parseLong(input[0]);

        new AmazingNumber(number).printDetailedProperties();
    }

    public static void printInstructions() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public static boolean isValidInput(String[] inputs) {
        if (inputs[0].isEmpty()) {
            printInstructions();
            return false;
        }

        if (inputs.length == 1) {
            return isFirstInputValid(inputs[0]);
        } else if (inputs.length == 2) {
            return isFirstInputValid(inputs[0]) && isSecondInputValid(inputs[1]);
        } else {
            return isFirstInputValid(inputs[0]) && isSecondInputValid(inputs[1]) && isThirdInputValid(inputs[2]);
        }
    }

    private static boolean isFirstInputValid(String input) {
        if (input.matches("\\d+") && Integer.parseInt(input) >= 0) {
            return true;
        } else {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }
    }

    private static boolean isSecondInputValid(String input) {
        if (input.matches("\\d+") && Integer.parseInt(input) >= 0) {
            return true;
        } else {
            System.out.println("second parameter should be a natural number");
            return false;
        }
    }

    private static boolean isThirdInputValid(String input) {
        String[] properties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD"};

        for (String property : properties) {
            if (input.equalsIgnoreCase(property)) {
                return true;
            }
        }

        System.out.printf("The property [%s] is wrong.\n", input.toUpperCase());
        System.out.printf("Available properties: %s\n", Arrays.toString(properties));
        return false;
    }
}