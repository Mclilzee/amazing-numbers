package amazing.numbers;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        String input = scanner.nextLine();

        if (input.matches("\\d*") && !input.equals("0")) {
            AmazingNumber number = new AmazingNumber(Integer.parseInt(input));
            number.printProperties();
        } else {
            System.out.println("This number is not natural!");
        }
    }
}