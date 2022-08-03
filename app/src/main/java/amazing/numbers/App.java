package amazing.numbers;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

    }

    public static void inputRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                                
                """);

        while (true) {
            System.out.print("Enter a natural number: ");
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