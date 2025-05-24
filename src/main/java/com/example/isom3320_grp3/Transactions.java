package com.example.isom3320_grp3;

import java.time.LocalDate;

public class Transactions {
    private static int idCounter = 1; // Static ID generator for transactions
    private int transactionID;
    private String transCurrencyType;
    private LocalDate transactionDate;  // import java.util.date
    private String transactionType; // Merged field from `TransactionType`
    private Accounts account; // Reference to an account
    private double amount;
    private String remarks;

    // Constructor for creating a transaction
    public Transactions(String transCurrencyType, LocalDate transactionDate, String transactionType, Accounts account, double amount, String remarks) {
        this.transactionID = idCounter++; // Auto-generate unique transaction ID
        this.transCurrencyType = transCurrencyType;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType; // The type of transaction (e.g., "Food", "Taxi")
        this.account = account;
        this.amount = amount;
        this.remarks = remarks;
    }
    //Getters
    //Getters
    public int getTransID(){
        return transactionID;
    }

    public LocalDate getTransDate(){
        return transactionDate;
    }

    public String getTransType(){
        return transactionType;
    }

    public Accounts getTransAccounts(){
        return account;
    }

    public double getAmount(){
        return amount;
    }

    public String getRemarks(){
        return remarks;
    }

    // Display transaction details
    public void displayTransactionInfo() {
        System.out.printf(
                "Transaction ID: %d | Currency Type: %s | Date: %s | Type: %s | Account ID: %d | Amount: %.2f | Remarks: %s%n",
                transactionID, transCurrencyType, transactionDate, transactionType, account.getAccountID(), amount, remarks
        );
    }
}