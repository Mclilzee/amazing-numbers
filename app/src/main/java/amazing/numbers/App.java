package amazing.numbers;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final String[] properties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SUNNY", "SQUARE", "EVEN", "ODD"};
    private static final String[][] mutuallyExclusive = {{"EVEN", "ODD"}, {"DUCK", "SPY"}, {"SQUARE", "SUNNY"}};

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
            } else {
                propertySearchInput(userInputs);
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
        String[] searchProperties = Arrays.copyOfRange(inputs, 2, inputs.length);

        for (long i = 0; number + i < Long.MAX_VALUE; i++) {
            AmazingNumber amazingNumber = new AmazingNumber(number + i);

            if (checkAllProperties(amazingNumber, searchProperties)) {
                amazingNumber.printSimpleProperties();
                iterate--;
            }

            if (iterate == 0) {
                break;
            }
        }
    }

    private static boolean checkAllProperties(AmazingNumber number, String[] properties) {
        for (String property : properties) {
            if (!numberContainsProperty(number, property)) {
                return false;
            }
        }

        return true;
    }

    private static boolean numberContainsProperty(AmazingNumber number, String property) {
        property = property.toUpperCase();

        switch (property) {
            case "BUZZ":
                return number.isBuzz();
            case "DUCK":
                return number.isDuck();
            case "PALINDROMIC":
                return number.isPalindromic();
            case "GAPFUL":
                return number.isGapful();
            case "SPY":
                return number.isSpy();
            case "EVEN":
                return number.isEven();
            case "ODD":
                return number.isOdd();
            case "SUNNY":
                return number.isSunny();
            case "SQUARE":
                return number.isSquare();
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
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
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
            return isFirstInputValid(inputs[0]) && isSecondInputValid(inputs[1]) && arePropertyInputsValid(Arrays.copyOfRange(inputs, 2, inputs.length));
        }
    }

    private static boolean isFirstInputValid(String input) {
        if (input.matches("\\d+") && Long.parseLong(input) >= 0) {
            return true;
        } else {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }
    }

    private static boolean isSecondInputValid(String input) {
        if (input.matches("\\d+") && Long.parseLong(input) >= 0) {
            return true;
        } else {
            System.out.println("The second parameter should be a natural number.");
            return false;
        }
    }

    private static boolean arePropertyInputsValid(String[] inputs) {
        boolean containsWrongProperty = false;
        List<String> wrongProperties = new ArrayList<>();

        for (String property : inputs) {
            if (!propertyExists(property)) {
                wrongProperties.add(property);
                containsWrongProperty = true;
            }
        }

        // Check if properties a multiple values to format output accordingly
        boolean multi = wrongProperties.size() > 1;
        if (containsWrongProperty) {
            System.out.printf("The propert%s %s %s wrong.\n", multi ? "ies" : "y", wrongProperties.toString().toUpperCase(), multi ? "are" : "is");
            System.out.printf("Available properties: %s\n", Arrays.toString(properties));
            return false;
        }

        return validProperties(inputs);
    }

    private static boolean validProperties(String[] inputs) {
        for (String[] set : mutuallyExclusive) {
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i - 1].equalsIgnoreCase(set[0]) && inputs[i].equalsIgnoreCase(set[1]) || inputs[i - 1].equalsIgnoreCase(set[1]) && inputs[i].equalsIgnoreCase(set[0])) {
                    System.out.printf("The request contains mutually exclusive properties: %s\n", Arrays.toString(set).toUpperCase());
                    System.out.println("There are no numbers with these properties.");
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean propertyExists(String input) {
        for (String property : properties) {
            if (input.equalsIgnoreCase(property)) {
                return true;
            }
        }
        return false;
    }
}