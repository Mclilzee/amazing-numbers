package amazing.numbers;

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
            String input = userInputs[0];
            System.out.println();

            if (input.isEmpty()) {
                printInstructions();
            } else if (!input.matches("\\d*")) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if (input.equals("0")) {
                System.out.println("Goodbye!");
                break;
            } else if (userInputs.length == 1) {
                singleInputProcessing(input);
            } else {
                multipleInputsProcessing(userInputs);
            }
        }
    }

    public static void multipleInputsProcessing(String[] inputs) {
        if (!inputs[1].matches("\\d+") || Integer.parseInt(inputs[1]) < 0) {
            System.out.println("second parameter should be a natural number");
            return;
        }

        long number = Long.parseLong(inputs[0]);
        int length = Integer.parseInt(inputs[1]);


        for (int i = 0; i < length; i++) {
            new AmazingNumber(number + i).printSimpleProperties();
        }
    }

    public static void singleInputProcessing(String input) {
        long number = Long.parseLong(input);

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
}