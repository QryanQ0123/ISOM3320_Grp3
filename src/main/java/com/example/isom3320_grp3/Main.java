package com.example.isom3320_grp3;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Accounts> accountsList = new ArrayList<>(); // ArrayList to store accounts
    private static final ArrayList<Transactions> transactionsList = new ArrayList<>(); // ArrayList to store transactions
    private static final ArrayList<String> transactionTypes = new ArrayList<>(); //ArrayList to store Transaction Types

    private static Scanner scanner = new Scanner(System.in);

    public static void assignment1Main() {
        while (true) {
            System.out.println("\nFinance Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Create Transaction Type");
            System.out.println("3. Create Transaction");
            System.out.println("4. Display Accounts");
            System.out.println("5. Display Transactions");
            System.out.println("6. Display Transaction Types");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.next();
            switch (choice) {
                case "1" -> createAccount();
                case "2" -> createTransactionType();
                case "3" -> createTransaction();
                case "4" -> displayAccounts();
                case "5" -> sortDisplay();
                case "6" -> displayTransactionTypes();
                case "7" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method to create a new account
    public static void createAccount() {
        System.out.println("\nSelect the currency type:");
        System.out.println("1. HKD");
        System.out.println("2. USD");
        System.out.println("3. EUR");
        System.out.print("Enter your choice (1-3): ");
        int currencyChoice = scanner.nextInt();

        String currencyType;
        switch (currencyChoice) {
            case 1:
                currencyType = "HKD";
                break;
            case 2:
                currencyType = "USD";
                break;
            case 3:
                currencyType = "EUR";
                break;
            default:
                System.out.println("Invalid currency! Defaulting to 'HKD'.");
                currencyType = "HKD";
        }
        System.out.println("Enter the initial balance: ");
        double balance = scanner.nextDouble();

        if (balance < 0) {
            System.out.println("Initial balance cannot be negative! Setting to 0.");
            balance = 0;
        }

        Accounts account = new Accounts(currencyType, balance);
        accountsList.add(account); // Add the new account to the ArrayList

        System.out.println("Account created successfully!");
        account.displayAccountInfo();
    }

    // Method to create a new transaction type
    public static void createTransactionType() {
        System.out.println("Enter the name of the new transaction type: ");
        scanner.nextLine(); // Clear the newline character
        String typeName = scanner.nextLine();

        // Check if the transaction type already exists (case-insensitive)
        for (String existingType : transactionTypes) {
            if (existingType.equalsIgnoreCase(typeName)) {
                System.out.println("Transaction type already exists!");
                return;
            }
        }

        // Add the new transaction type
        transactionTypes.add(typeName);
        System.out.println("Transaction type added successfully!");
    }


    // Method to create a new transaction
    public static void createTransaction() {
        if (accountsList.isEmpty()) {
            System.out.println("No accounts exist! Please create an account first.");
            return;
        }
        if (transactionTypes.isEmpty()) {
            System.out.println("No transaction types exist! Please add a transaction type first.");
            return;
        }
        LocalDate date;
        LocalDate today = LocalDate.now();
        while (true) {
            System.out.println("\nEnter the transaction date (e.g., YYYY-MM-DD): ");
            String dateInput = scanner.next();

            try {
                date = LocalDate.parse(dateInput); // Parse and validate the date
                if (date.isAfter(today)) {
                    System.out.println("Cannot input future dates!");
                    continue;
                }
                break; // Exit loop if the date is valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please enter the date in 'YYYY-MM-DD' format.");
            }

        }
        System.out.println("\nSelect the currency type:");
        System.out.println("1. HKD");
        System.out.println("2. USD");
        System.out.println("3. EUR");
        System.out.print("Enter your choice (1-3): ");
        int transactionCurrency = scanner.nextInt();

        String transCurrencyType = switch (transactionCurrency) {
            case 1 -> "HKD";
            case 2 -> "USD";
            case 3 -> "EUR";
            default -> {
                System.out.println("Invalid currency! Defaulting to 'HKD'.");
                yield "HKD";
            }
        };

        // Display transaction type options
        System.out.println("Select the transaction type:");
        for (int i = 0; i < transactionTypes.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), transactionTypes.get(i));
        }
        System.out.print("Enter your choice: ");
        int typeChoice = scanner.nextInt();

        // Validate type choice
        if (typeChoice < 1 || typeChoice > transactionTypes.size()) {
            System.out.println("Invalid choice! Transaction type selection failed.");
            return;
        }
        String type = transactionTypes.get(typeChoice - 1);


        System.out.println("Enter the account ID: ");
        int accountID = scanner.nextInt();

        // Check if account exists
        Accounts account = findAccountByID(accountID);
        if (account == null) {
            System.out.println("Error: Account with ID " + accountID + " does not exist!");
            return;
        }
        if (!account.getCurrencyType().equals(transCurrencyType)) {
            System.out.println("Error: Account currency does not match transaction");
            return;
        }

        System.out.println("Enter the transaction amount: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Error: Transaction amount must be positive!");
            return;
        }

        // Check if balance is sufficient
        if (amount > account.getBalance()) {
            System.out.println("Error: Transaction amount (" + amount + ") exceeds account balance (" + account.getBalance() + ")!");
            return;
        }

        System.out.println("Enter remarks for the transaction: ");
        scanner.nextLine(); // Clear the newline
        String remarks = scanner.nextLine();

        // Deduct from balance and create transaction
        account.deductFromBalance(amount); // This already has its own insufficient balance check, but we checked earlier too
        Transactions transaction = new Transactions(transCurrencyType, date, type, account, amount, remarks);
        transactionsList.add(transaction); // Add the new transaction to the ArrayList

        System.out.println("Transaction created successfully!");
        transaction.displayTransactionInfo();
    }

    // Method to display all accounts
    public static void displayAccounts() {
        if (accountsList.isEmpty()) {
            System.out.println("\nNo accounts to display.");
        } else {
            System.out.println("\nAccounts:");
            for (Accounts account : accountsList) {
                account.displayAccountInfo();
            }
        }
    }

    // Method to display all transactions
    public static void displayTransactions() {
        if (transactionsList.isEmpty()) {
            System.out.println("\nNo transactions to display.");
        } else {
            System.out.println("\nTransactions:");
            for (Transactions transaction : transactionsList) {
                transaction.displayTransactionInfo();
            }
        }
    }

    // Method to display all transaction types
    public static void displayTransactionTypes() {
        if (transactionTypes.isEmpty()) {
            System.out.println("\nNo transaction types to display.");
        } else {
            System.out.println("\nTransaction Types:");
            for (int i = 0; i < transactionTypes.size(); i++) {
                System.out.printf("%d. %s%n", (i + 1), transactionTypes.get(i));
            }
        }
    }

    // Utility method to find an account by ID
    private static Accounts findAccountByID(int accountID) {
        for (Accounts account : accountsList) {
            if (account.getAccountID() == accountID) {
                return account;
            }
        }
        return null;
    }

    public static void sortDisplay() {
        System.out.println("\nYou can choose to sort the transactions by the following categories: ");
        System.out.println("The default sorting method is by Transaction ID.");
        System.out.println("1. Transaction Date");
        System.out.println("2. Transaction Type");
        System.out.println("3. Transaction Account ID");
        System.out.println("4. Transaction Amount");
        System.out.print("Enter your choice: ");

        int sortChoice = scanner.nextInt(); // Use a shared scanner object
        switch (sortChoice) {
            case 1 -> sortDate();
            case 2 -> sortType();
            case 3 -> sortAcc();
            case 4 -> sortAmt();
            default -> {
                System.out.println("Invalid choice! Displaying transactions without sorting.");
                displayTransactions();
            }
        }
    }

    // Sorting by Transaction Date (Ascending)
    public static void sortDate() {
        for (int i = 0; i < transactionsList.size() - 1; i++) {
            for (int j = 0; j < transactionsList.size() - i - 1; j++) {
                if (transactionsList.get(j).getTransDate().isAfter(transactionsList.get(j + 1).getTransDate())) {
                    // Swap adjacent transactions if they are in the wrong order
                    Transactions temp = transactionsList.get(j);
                    transactionsList.set(j, transactionsList.get(j + 1));
                    transactionsList.set(j + 1, temp);
                }
            }
        }
        System.out.println("\nTransactions sorted by Date:");
        displayTransactions();
    }

    // Sorting by Transaction Type (Alphabetically)
    public static void sortType() {
        for (int i = 0; i < transactionsList.size() - 1; i++) {
            for (int j = 0; j < transactionsList.size() - i - 1; j++) {
                // Use compareToIgnoreCase for case-insensitive comparison
                if (transactionsList.get(j).getTransType().compareToIgnoreCase(transactionsList.get(j + 1).getTransType()) > 0) {
                    // Swap transactions if the current type is greater than the next
                    Transactions temp = transactionsList.get(j);
                    transactionsList.set(j, transactionsList.get(j + 1));
                    transactionsList.set(j + 1, temp);
                }
            }
        }
        System.out.println("\nTransactions sorted by Type (case-insensitive):");
        displayTransactions();
    }

    // Sorting by Account ID (Ascending)
    public static void sortAcc() {
        for (int i = 0; i < transactionsList.size() - 1; i++) {
            for (int j = 0; j < transactionsList.size() - i - 1; j++) {
                if (transactionsList.get(j).getTransAccounts().getAccountID() > transactionsList.get(j + 1).getTransAccounts().getAccountID()) {
                    // Swap if the current account ID is greater than the next
                    Transactions temp = transactionsList.get(j);
                    transactionsList.set(j, transactionsList.get(j + 1));
                    transactionsList.set(j + 1, temp);
                }
            }
        }
        System.out.println("\nTransactions sorted by Account ID:");
        displayTransactions();
    }

    // Sorting by Transaction Amount (Ascending)
    public static void sortAmt() {
        for (int i = 0; i < transactionsList.size() - 1; i++) {
            for (int j = 0; j < transactionsList.size() - i - 1; j++) {
                if (transactionsList.get(j).getAmount() > transactionsList.get(j + 1).getAmount()) {
                    // Swap if the current amount is greater than the next
                    Transactions temp = transactionsList.get(j);
                    transactionsList.set(j, transactionsList.get(j + 1));
                    transactionsList.set(j + 1, temp);
                }
            }
        }
        System.out.println("\nTransactions sorted by Amount:");
        displayTransactions();
    }
}