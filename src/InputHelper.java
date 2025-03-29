package src;

import java.util.InputMismatchException;
import java.util.Scanner;

class InputHelper {
    private static Scanner scanner = new Scanner(System.in);
    
    public static int getInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }
    
    public static double getDouble(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }
    
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }   
}
