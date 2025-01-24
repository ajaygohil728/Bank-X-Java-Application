package org.ajaygohil76982.model;
import java.util.UUID;
public class Customer {
    private String id;
    private String name;
    private Account savingsAccount;
    private Account currentAccount;

    public Customer(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.savingsAccount = new Account("SAVINGS", 500.00); // Savings account with joining bonus
        this.currentAccount = new Account("CURRENT", 0.00); // Current account with zero balance
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Account getSavingsAccount() {
        return savingsAccount;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }
}
