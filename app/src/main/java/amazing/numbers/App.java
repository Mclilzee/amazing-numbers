package amazing.numbers;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.matches("\\d*")) {
            showResult(Integer.parseInt(input));
        } else {
            System.out.println("This number is not natural!");
        }
    }

    public static void showResult(int number) {

        String numberType = number % 2 == 0 ? "Even" : "Odd";
        System.out.printf("This number is %s.\n", numberType);

        if (number % 7 == 0 || number % 10 == 7) {
            System.out.println("It is a Buzz number.");
        } else {
            System.out.println("It is not a Buzz number.");
        }

        System.out.println("Explanation:");
        explainNumber(number);
    }

    private static void explainNumber(int number) {
        if (number % 7 == 0) {
            System.out.println(number + " is divisible by 7.");
        } else if (number % 10 == 7) {
            System.out.println(number + " ends with 7.");
        } else {
            System.out.println(number + " is neither divisible by 7 nor does it end with 7.");
        }
    }
}
