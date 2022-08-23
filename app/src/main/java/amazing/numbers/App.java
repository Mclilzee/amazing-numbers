package amazing.numbers;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final String[] PROPERTIES = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SUNNY", "SQUARE",
            "EVEN", "ODD", "JUMPING"};

    private static final ArrayList<String[]> MUTUALLY_EXCLUSIVE = new ArrayList<>();

    private static void fillMutuallyExclusiveArray() {
        MUTUALLY_EXCLUSIVE.add(new String[]{"EVEN", "ODD"});
        MUTUALLY_EXCLUSIVE.add(new String[]{"DUCK", "SPY"});
        MUTUALLY_EXCLUSIVE.add(new String[]{"SQUARE", "SUNNY"});
        MUTUALLY_EXCLUSIVE.add(new String[]{"HAPPY", "SAD"});
        MUTUALLY_EXCLUSIVE.add(new String[]{"-HAPPY", "-SAD"});
        MUTUALLY_EXCLUSIVE.add(new String[]{"-EVEN", "-ODD"});
        MUTUALLY_EXCLUSIVE.add(new String[]{"-DUCK", "-SPY"});

        for (String property : PROPERTIES) {
            MUTUALLY_EXCLUSIVE.add(new String[]{property, "-" + property});
        }

        System.out.println(MUTUALLY_EXCLUSIVE);
    }

    public static void main(String[] args) {
        fillMutuallyExclusiveArray();
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
        AmazingNumber amazingNumber = new AmazingNumber(0);

        for (int i = 0; i < length; i++) {
            amazingNumber.setNumber(number + i);
            amazingNumber.printSimpleProperties();
        }
    }

    public static void propertySearchInput(String[] inputs) {
        long number = Long.parseLong(inputs[0]);
        int iterate = Integer.parseInt(inputs[1]);
        String[] searchProperties = Arrays.copyOfRange(inputs, 2, inputs.length);
        AmazingNumber amazingNumber = new AmazingNumber(0);

        for (long i = number; i < Long.MAX_VALUE; i++) {
            amazingNumber.setNumber(i);

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
            case "JUMPING":
                return number.isJumping();
            case "HAPPY":
                return number.isHappy();
            case "SAD":
                return number.isSad();
            default:
                // if property contain minus, return reverse boolean
                if (property.charAt(0) == '-') {
                    return !numberContainsProperty(number, property.substring(1));
                } else {
                    throw new InvalidParameterException();
                }
        }
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

    // receives input of property names only
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
            System.out.printf("Available properties: %s\n", Arrays.toString(PROPERTIES));
            return false;
        }

        return !mutuallyExclusiveProperties(inputs);
    }

    // check if properties are not mutually exclusive
    private static boolean mutuallyExclusiveProperties(String[] inputs) {

        for (int i = 0; i < inputs.length; i++) {
            for (int j = i + 1; j < inputs.length; j++) {
                String firstProperty = inputs[i].toUpperCase();
                String secondProperty = inputs[j].toUpperCase();

                if (propertiesMutuallyExclusive(firstProperty, secondProperty)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean propertiesMutuallyExclusive(String firstProperty, String secondProperty) {
        for (String[] set : MUTUALLY_EXCLUSIVE) {

            if (firstProperty.equalsIgnoreCase(set[0]) && secondProperty.equalsIgnoreCase(set[1]) || firstProperty.equalsIgnoreCase(set[1]) && secondProperty.equalsIgnoreCase(set[0])) {
                System.out.printf("The request contains mutually exclusive properties: %s\n", Arrays.toString(set).toUpperCase());
                System.out.println("There are no numbers with these properties.");
                return true;
            }
        }

        return false;
    }

    private static boolean propertyExists(String input) {
        for (String property : PROPERTIES) {
            if (input.charAt(0) == '-') {
                input = input.substring(1);
            }
            if (input.equalsIgnoreCase(property)) {
                return true;
            }
        }
        return false;
    }
}