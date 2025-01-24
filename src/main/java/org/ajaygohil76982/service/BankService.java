package org.ajaygohil76982.service;

import org.ajaygohil76982.model.Account;
import org.ajaygohil76982.model.Customer;
import org.ajaygohil76982.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<String, Customer> customers;
    private List<Transaction> transactions;
    private NotificationService notificationService;

    public BankService() {
        this.customers = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.notificationService = new NotificationService();
    }

    public Customer onboardCustomer(String name) {
        try {
            Customer customer = new Customer(name);
            customers.put(customer.getId(), customer);
            System.out.println("Customer onboarded with ID: " + customer.getId());
            return customer;
        } catch (Exception e) {
            System.err.println("Error onboarding customer: " + e.getMessage());
            throw new RuntimeException("Failed to onboard customer", e);
        }
    }

    public void transfer(Customer customer, double amount) {
        try {
            Account fromAccount = customer.getSavingsAccount();
            Account toAccount = customer.getCurrentAccount();

            if (fromAccount == null || toAccount == null) {
                throw new IllegalArgumentException("Accounts are not initialized for the customer.");
            }

            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                logTransaction(fromAccount.getId(), "DEBIT", amount, "Transfer to Current Account", customer);
                logTransaction(toAccount.getId(), "CREDIT", amount, "Transfer from Savings Account", customer);
                System.out.println("Transfer successful!");
            } else {
                System.out.println("Insufficient balance in Savings Account.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during transfer: " + e.getMessage());
        }
    }

    public void makePayment(Customer customer, double amount) {
        try {
            Account currentAccount = customer.getCurrentAccount();
            double fee = amount * 0.0005; // 0.05% fee

            if (currentAccount == null) {
                throw new IllegalArgumentException("Current Account is not initialized for the customer.");
            }

            if (currentAccount.getBalance() >= (amount + fee)) {
                currentAccount.setBalance(currentAccount.getBalance() - (amount + fee));
                logTransaction(currentAccount.getId(), "DEBIT", amount + fee, "Payment made", customer);
                System.out.println("Payment successful! Fee charged: " + fee);
            } else {
                System.out.println("Insufficient balance in Current Account.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error making payment: " + e.getMessage());
        }
    }

    public void applyInterest(Customer customer) {
        try {
            Account savingsAccount = customer.getSavingsAccount();

            if (savingsAccount == null) {
                throw new IllegalArgumentException("Savings Account is not initialized for the customer.");
            }

            double interest = savingsAccount.getBalance() * 0.005; // 0.5% interest
            savingsAccount.setBalance(savingsAccount.getBalance() + interest);
            logTransaction(savingsAccount.getId(), "CREDIT", interest, "Interest credited", customer);
            System.out.println("Interest applied successfully!");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error applying interest: " + e.getMessage());
        }
    }

    public void showTransactionHistory() {
        try {
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        } catch (Exception e) {
            System.err.println("Error displaying transaction history: " + e.getMessage());
        }
    }

    private void logTransaction(String accountId, String type, double amount, String description, Customer customer) {
        try {
            Transaction transaction = new Transaction(accountId, type, amount, description);
            transactions.add(transaction);
            notificationService.sendNotification(customer, transaction);
        } catch (Exception e) {
            System.err.println("Error logging transaction: " + e.getMessage());
        }
    }

    public Customer getCustomer(String customerId) {
        try {
            if (customers.containsKey(customerId)) {
                return customers.get(customerId);
            } else {
                throw new IllegalArgumentException("Customer not found with ID: " + customerId);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error fetching customer: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return null;
        }
    }
}
