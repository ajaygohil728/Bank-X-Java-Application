package org.ajaygohil76982.model;
import java.util.UUID;
public class Account {
    private String id;
    private String type;
    private double balance;

    public Account(String type, double balance) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
