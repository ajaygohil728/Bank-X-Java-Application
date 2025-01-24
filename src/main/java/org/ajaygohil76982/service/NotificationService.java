package org.ajaygohil76982.service;

import org.ajaygohil76982.model.Customer;
import org.ajaygohil76982.model.Transaction;

public class NotificationService {
    public void sendNotification(Customer customer, Transaction transaction) {
        System.out.println("Notification sent to " + customer.getName() + ": " + transaction);
    }
}
