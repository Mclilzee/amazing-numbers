package amazing.numbers;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        inputRequest();
    }

    public static void inputRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");
        System.out.println();

        while (true) {
            System.out.println();
            System.out.print("Enter a request: ");
            String input = scanner.nextLine();
            System.out.println();

            if (!input.matches("\\d*")) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if (input.equals("0")) {
                System.out.println("Goodbye!");
                break;
            } else {
                AmazingNumber number = new AmazingNumber(Long.parseLong(input));
                number.printProperties();
            }
        }
    }
}