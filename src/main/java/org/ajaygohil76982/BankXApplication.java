package org.ajaygohil76982;

import org.ajaygohil76982.model.Customer;
import org.ajaygohil76982.service.BankService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankXApplication {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n--- Banking Application Menu ---");
                System.out.println("1. Onboard Customer");
                System.out.println("2. Transfer Money (Savings -> Current)");
                System.out.println("3. Make Payment (Current Account)");
                System.out.println("4. Apply Interest to Savings Account");
                System.out.println("5. View Transaction History");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = getValidChoice(scanner);
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter customer name: ");
                        String name = scanner.nextLine();
                        bankService.onboardCustomer(name);
                    }
                    case 2 -> {
                        System.out.print("Enter customer ID: ");
                        String customerId = scanner.nextLine();
                        System.out.print("Enter transfer amount: ");
                        double amount = getValidAmount(scanner);
                        Customer customer = bankService.getCustomer(customerId);
                        if (customer != null) {
                            bankService.transfer(customer, amount);
                        } else {
                            System.out.println("Customer not found!");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter customer ID: ");
                        String customerId = scanner.nextLine();
                        System.out.print("Enter payment amount: ");
                        double amount = getValidAmount(scanner);
                        Customer customer = bankService.getCustomer(customerId);
                        if (customer != null) {
                            bankService.makePayment(customer, amount);
                        } else {
                            System.out.println("Customer not found!");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter customer ID: ");
                        String customerId = scanner.nextLine();
                        Customer customer = bankService.getCustomer(customerId);
                        if (customer != null) {
                            bankService.applyInterest(customer);
                        } else {
                            System.out.println("Customer not found!");
                        }
                    }
                    case 5 -> bankService.showTransactionHistory();
                    case 6 -> {
                        System.out.println("Exiting application. Goodbye!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }

    // Helper method to ensure a valid choice input
    private static int getValidChoice(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    // Helper method to ensure valid amount input
    private static double getValidAmount(Scanner scanner) {
        while (true) {
            try {
                double amount = scanner.nextDouble();
                if (amount <= 0) {
                    throw new IllegalArgumentException("Amount must be greater than zero.");
                }
                return amount;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid number for the amount.");
                scanner.nextLine(); // Consume the invalid input
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
