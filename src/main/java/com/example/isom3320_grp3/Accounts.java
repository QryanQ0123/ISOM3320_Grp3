package com.example.isom3320_grp3;

public class Accounts {
    private static int idCounter = 1; // counter
    private int accountID;
    private String accountName;
    private String currencyType;
    private double balance;

    // Constructor for creating accounts
    public Accounts(String accountName, String currencyType, double balance) {
        this.accountID = idCounter++; // Auto-generate unique account ID
        this.accountName = accountName;
        this.currencyType = currencyType;
        this.balance = balance;
    }

    // Getters
    public int getAccountID() {

        return accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public double getBalance() {
        return balance;
    }

    // Add to balance
    public void addToBalance(double amount) {
        this.balance += amount;
    }

    // Subtract from balance
    public void deductFromBalance(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            this.balance -= amount;
        }
    }

    // Display account details We can just execute this with this.displayAccountInfo()
    public void displayAccountInfo() {
        System.out.printf("Account ID: %d | Currency: %s | Balance: %.2f%n", accountID, currencyType, balance);
    }
}
