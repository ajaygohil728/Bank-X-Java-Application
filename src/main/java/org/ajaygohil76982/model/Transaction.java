package org.ajaygohil76982.model;

import java.time.LocalDateTime;

public class Transaction {
    private String accountId;
    private String type; // CREDIT or DEBIT
    private double amount;
    private String description;
    private LocalDateTime timestamp;

    public Transaction(String accountId, String type, double amount, String description) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountId='" + accountId + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
