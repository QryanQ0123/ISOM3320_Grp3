package com.example.isom3320_grp3;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transactions {
    private static int idCounter = 1; // Static ID generator for transactions
    private int transactionID;
    private String transCurrencyType;
    private LocalDate transactionDate;  // import java.util.date
    private String transactionType; // Merged field from `TransactionType`
    private Accounts account; // Reference to an account
    private double amount;
    private String remarks;
    private static ArrayList<String> transactionTypes = new ArrayList<>(); //ArrayList to store Transaction Types
    private static ArrayList<Transactions> transactionsList = new ArrayList<>(); // ArrayList to store transaction


    // Constructor for creating a transaction
    public Transactions(String transCurrencyType, LocalDate transactionDate, String transactionType, Accounts account, double amount, String remarks) {
        this.transactionID = idCounter++; // Auto-generate unique transaction ID
        this.transCurrencyType = transCurrencyType;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType; // The type of transaction (e.g., "Food", "Taxi")
        this.account = account;
        this.amount = amount;
        this.remarks = remarks;
        transactionsList.add(this);

    }
    // Add a new transaction type
    public static boolean addTransactionType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return false; // Invalid type
        }
        if (!transactionTypes.contains(type)) {
            transactionTypes.add(type);
            return true; // Successfully added
        }
        return false; // Type already exists
    }

    //Setters
    public void setTransDate(LocalDate transdate){
        transactionDate = transdate;
    }

    public void setTransType(String type){
        transactionType = type;
    }

    public void setCurrency(String type){
        transCurrencyType = type;
    }

    public void setAccount(Accounts acc){
        account = acc;
    }

    public void setAmount(double amt){
        amount = amt;
    }

    public void setRemarks(String rmk){
        remarks = rmk;
    }

    //Getters

    public static int getIdCounter(){
        return idCounter;
    }
    
    public int getTransactionID(){
        return transactionID;
    }

    public String getTransCurrencyType() {return transCurrencyType;}

    public LocalDate getTransactionDate(){
        return transactionDate;
    }

    public String getTransactionType(){
        return transactionType;
    }

    public Accounts getAccount(){
        return account;
    }

    public double getAmount(){
        return amount;
    }

    public String getRemarks(){
        return remarks;
    }

    public static ArrayList<String> getTransactionTypes() {return transactionTypes; }

    public static ArrayList<Transactions> getTransactionList(){return transactionsList;}


    // Display transaction details
    public void displayTransactionInfo() {
        System.out.printf(
                "Transaction ID: %d | Currency Type: %s | Date: %s | Type: %s | Account ID: %d | Amount: %.2f | Remarks: %s%n",
                transactionID, transCurrencyType, transactionDate, transactionType, account.getAccountID(), amount, remarks
        );
    }
}