package com.example.isom3320_grp3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private static final ArrayList<Accounts> accountsList = new ArrayList<>(); // ArrayList to store accounts
    private static final ArrayList<Transactions> transactionsList = new ArrayList<>(); // ArrayList to store transaction
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Main Menu");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        //Primary scene (Main Menu)
        Scene primaryScene = new Scene(vbox , 500 , 300);
        primaryStage.setScene(primaryScene);

        //buttons
        Button btCreateAccount =  new Button("Create Account");
        Button btCreateType = new Button("Create Type");
        Button btCreateTransaction = new Button("Create Transaction");
        Button btDisplayTransaction = new Button("Display Transaction");

        //ActionEvent Test
        btCreateAccount.setOnAction(e -> {
            //new scene for create account
            Scene accountScene = createAccountScene(primaryStage, primaryScene);
            primaryStage.setScene(accountScene);
        });
        btCreateType.setOnAction(e -> {
            Scene typeScene = createTypeScene(primaryStage, primaryScene);
            primaryStage.setScene(typeScene);
        });
        btCreateTransaction.setOnAction(e -> {
            if (accountsList.isEmpty()){
                showAlert(Alert.AlertType.ERROR, "Error", "No Account Created.");
            } else if (Transactions.getTransactionTypes().isEmpty()){
                showAlert(Alert.AlertType.ERROR, "Error", "No Transaction Types Created.");
            } else {
                Scene transactionScene = createTransactionScene(primaryStage, primaryScene);
                primaryStage.setScene(transactionScene);
            }
        });
        btDisplayTransaction.setOnAction(e -> {
                    if (transactionsList.isEmpty()) {
                        showAlert(Alert.AlertType.ERROR, "Error", "No Transaction Found.");
                    } else {
                        Scene dpTransactionScene = displayTransactionScene(primaryStage, primaryScene);
                        primaryStage.setScene(dpTransactionScene);
                    }
                });

        //Add to VBox
        vbox.getChildren().addAll(btCreateAccount, btCreateType, btCreateTransaction, btDisplayTransaction);

        //show
        primaryStage.show();
    }
    public static void main(String[] args) {launch(args);}

    //method for createAccount
    private Scene createAccountScene(Stage primaryStage , Scene menuScene) {
        primaryStage.setTitle("Create Account");

        BorderPane accountMenu = new BorderPane();
        VBox tfBox = new VBox(10);
        tfBox.setAlignment(Pos.CENTER);

        //textfields
        HBox accountNameBox = new HBox(10);
        Label lblAccountName = new Label("Account Name:");
        TextField tfAccountName = new TextField();
        tfAccountName.setPromptText("Account Name");
        accountNameBox.getChildren().addAll(lblAccountName, tfAccountName);

        HBox currencyTypeBox = new HBox(10);
        Label lblCurrencyType = new Label("Currency Type:");
        ComboBox<String> currencyTypeComboBox = new ComboBox<>();
        currencyTypeComboBox.setPromptText("Select Currency");
        currencyTypeComboBox.getItems().addAll("HKD", "EUR", "USD");
        currencyTypeBox.getChildren().addAll(lblCurrencyType, currencyTypeComboBox);

        HBox initialBalanceBox = new HBox(10);
        Label lblInitialBalance = new Label("Initial Balance:");
        TextField tfInitialBalance = new TextField();
        tfInitialBalance.setPromptText("Initial Balance");
        initialBalanceBox.getChildren().addAll(lblInitialBalance, tfInitialBalance);

        // Add textfield box t0 account menu
        tfBox.getChildren().addAll(accountNameBox, currencyTypeBox, initialBalanceBox );
        accountMenu.setCenter(tfBox);

        //Buttons
        Button btBackToMenu = new Button("Back");
        Button btCreateAccount = new Button("Create Account");

        // Add actionevents
        btBackToMenu.setOnAction(e ->{
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(menuScene);
        });

        btCreateAccount.setOnAction(e -> {
            try {
                String accountName = tfAccountName.getText().trim();
                String currencyType = currencyTypeComboBox.getValue();
                double initialBalance = Double.parseDouble(tfInitialBalance.getText().trim());

                // Basic validation
                if (accountName.isEmpty() || currencyType == null) {
                    showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill in and select all fields.");
                    return;
                }
                if (initialBalance < 0) {
                    showAlert(Alert.AlertType.ERROR, "Input Error", "Initial balance cannot be negative.");
                    return;
                }

                // Create new account
                Accounts newAccount = new Accounts(accountName, currencyType, initialBalance);
                accountsList.add(newAccount);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully: ID " + newAccount.getAccountID()+ ", Name: " + newAccount.getAccountName());

                // Clear fields
                tfAccountName.clear();
                currencyTypeComboBox.setValue(null);
                tfInitialBalance.clear();

                // Return to main menu
                primaryStage.setTitle("Main Menu");
                primaryStage.setScene(menuScene);
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid number for initial balance.");
            }});

        //two hbox
        HBox leftBox = new HBox(btBackToMenu);
        HBox rightBox = new HBox(btCreateAccount);
        leftBox.setAlignment(Pos.BASELINE_LEFT);
        rightBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox bottomBox = new HBox(btBackToMenu, btCreateAccount);
        bottomBox.setSpacing(160);
        accountMenu.setBottom(bottomBox);

        // Create and return new scene
        return new Scene(accountMenu , 500 , 300);
    }


    // Method for creating the transaction type scene
    private Scene createTypeScene(Stage primaryStage, Scene menuScene) {
        primaryStage.setTitle("Create Transaction Type");

        // Use BorderPane as the main layout
        BorderPane typeLayout = new BorderPane();


        // HBox for Transaction Type Input
        HBox typeBox = new HBox();
        typeBox.setAlignment(Pos.CENTER);
        typeBox.setSpacing(10); // Fixed spacing
        Label lblType = new Label("Transaction Type:");
        TextField tftype = new TextField();
        tftype.setPromptText("Transaction Type");

        // Bind the width of the TextField to a percentage of the scene width
        tftype.prefWidthProperty().bind(typeLayout.widthProperty().multiply(0.5)); // 50% of the scene's width

        // Add the Label and TextField to the HBox
        typeBox.getChildren().addAll(lblType, tftype);
        typeLayout.setCenter(typeBox);

        // Bottom HBox for Buttons
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new javafx.geometry.Insets(10));

        // Buttons
        Button btBackToMenu = new Button("Back");
        Button btCreateType = new Button("Create Type");

        // Bind button widths to a percentage of the scene width
        btBackToMenu.prefWidthProperty().bind(typeLayout.widthProperty().multiply(0.2)); // 20% of the scene's width
        btCreateType.prefWidthProperty().bind(typeLayout.widthProperty().multiply(0.2)); // 20% of the scene's width

        // Add Buttons to Bottom HBox
        bottomBox.getChildren().addAll(btBackToMenu, btCreateType);
        bottomBox.setSpacing(160); // Fixed spacing between buttons
        typeLayout.setBottom(bottomBox);

        // Add ActionEvents for Buttons
        btBackToMenu.setOnAction(e -> {
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(menuScene);
        });

        btCreateType.setOnAction(e -> {
            // Add logic for creating a transaction type
            String transactionType = tftype.getText().trim();

            // Validate input
            if (Transactions.addTransactionType(transactionType)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Transaction type added: " + transactionType);
                tftype.clear();
                System.out.println(Transactions.getTransactionTypes());
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid or duplicate transaction type.");
            }
        });

        // Create and return the scene
        Scene typeScene = new Scene(typeLayout, 500, 300);
        return typeScene;
    }

    //Create Transaction
    private Scene createTransactionScene(Stage primaryStage , Scene menuScene) {
        primaryStage.setTitle("Create Transaction");
        BorderPane transactionMenu = new BorderPane();
        VBox transactionBox = new VBox(10);
        transactionBox.setAlignment(Pos.CENTER);

        //ID
        HBox transactionIDBox = new HBox(10);
        Label lblTransactionID = new Label("Transaction ID:");
        Label tfTransactionID = new Label(Integer.toString(Transactions.getIdCounter()));
        transactionIDBox.getChildren().addAll(lblTransactionID, tfTransactionID);

        //Date
        HBox dateBox = new HBox(10);
        Label lblDate = new Label("Date:");
        ComboBox<String> dateComboBox = new ComboBox<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 1461; i++) {
            LocalDate date = today.minusDays(i);
            dateComboBox.getItems().add(date.format(formatter));
        }
        dateBox.getChildren().addAll(lblDate, dateComboBox);

        //Account
        HBox transactionAccountBox = new HBox(10);
        Label lblTransactionAccount = new Label("Account ID");
        ComboBox<Integer> transactionAccountComboBox = new ComboBox<>();
        Label lblTransactionCurrency = new Label(null);
        Label lblAccountBal = new Label(null);
        Label lblAccountName = new Label(null);
        for (int i = 0; i < accountsList.size(); i++){
            transactionAccountComboBox.getItems().add(accountsList.get(i).getAccountID());
        }
        transactionAccountBox.getChildren().addAll(lblTransactionAccount, transactionAccountComboBox, lblAccountName, lblAccountBal);
        transactionAccountComboBox.setOnAction(e -> {
            Accounts tempAcc = findAccountByID(transactionAccountComboBox.getValue());
            lblTransactionCurrency.setText(tempAcc.getCurrencyType());
            lblAccountName.setText(tempAcc.getAccountName());
            lblAccountBal.setText("Remaining Balance: " + Double.toString(tempAcc.getBalance()));
        });

        //Type
        HBox transactionTypeBox = new HBox(10);
        Label lblTransactionType = new Label("Transaction Type:");
        ComboBox<String> transactionTypeComboBox = new ComboBox<>(FXCollections.observableArrayList(Transactions.getTransactionTypes()));
        transactionTypeBox.getChildren().addAll(lblTransactionType, transactionTypeComboBox);

        //Amount
        HBox transactionAmountBox = new HBox(10);
        Label lblTransactionAmount = new Label("Transaction Amount:");
        TextField tfTransactionAmount = new TextField();
        tfTransactionAmount.setPromptText("Transaction Amount");
        transactionAmountBox.getChildren().addAll(lblTransactionAmount, lblTransactionCurrency, tfTransactionAmount);

        //Remarks
        HBox transactionRemarksBox = new HBox(10);
        Label lblTransactionRemarks = new Label("Transaction Remarks:");
        TextField tfTransactionRemarks = new TextField();
        tfTransactionRemarks.setPromptText("Remarks");
        transactionRemarksBox.getChildren().addAll(lblTransactionRemarks, tfTransactionRemarks);

        //add transaction box to transaction menu
        transactionBox.getChildren().addAll(
                transactionIDBox, dateBox , transactionAccountBox,
                transactionTypeBox, transactionAmountBox, transactionRemarksBox);
        transactionMenu.setCenter(transactionBox);

        //Add buttons
        Button btBackToMenu = new Button("Back");
        Button btCreateTransaction = new Button("Create Transaction");

        //SetOnActions

        btBackToMenu.setOnAction(e ->{
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(menuScene);
        });
        btCreateTransaction.setOnAction(e -> {
            try{

            String transCurrency = lblTransactionCurrency.getText();
            LocalDate transDate = LocalDate.parse(dateComboBox.getValue());
            String transType = transactionTypeComboBox.getValue();
            Accounts targetAcc = findAccountByID(transactionAccountComboBox.getValue());
            double amt = Double.parseDouble(tfTransactionAmount.getText());
            String rmk = tfTransactionRemarks.getText();

            if (transCurrency == null || transDate == null || transType == null || targetAcc == null){
                showAlert(Alert.AlertType.ERROR, "Error", "Please Input all the fields.");
                return;
            }
            if (amt < 0){
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter a non-negative number for initial balance.");
                return;
            }
            if (amt > targetAcc.getBalance()){
                showAlert(Alert.AlertType.ERROR, "Error", "Insufficient Balance! Please enter another value.");
                return;
            }
            Transactions newTransactions = new Transactions(transCurrency, transDate, transType, targetAcc, amt, rmk);
            targetAcc.deductFromBalance(amt);
            transactionsList.add(newTransactions);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Transaction added. Transaction ID: " + newTransactions.getTransactionID());

            transactionAccountComboBox.setValue(null);
            dateComboBox.setValue(null);
            transactionTypeComboBox.setValue(null);
            transactionAccountComboBox.setValue(null);
            tfTransactionAmount.clear();
            tfTransactionRemarks.clear();

            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(menuScene);

        }catch (NumberFormatException ex) {

                showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid number for transaction balance.");
            }

        });


        HBox leftBox = new HBox(btBackToMenu);
        HBox rightBox = new HBox(btCreateTransaction);
        HBox buttonBox = new HBox(leftBox, rightBox);
        buttonBox.setSpacing(160);
        transactionMenu.setBottom(buttonBox);

        return new Scene(transactionMenu , 500 , 300);
    }


    //Display Transaction
    private Scene displayTransactionScene(Stage primaryStage, Scene menuScene) {
        primaryStage.setTitle("Display Transactions");

        // Root layout for the scene
        BorderPane root = new BorderPane();

        // TableView for displaying transactions
        TableView<Transactions> tableView = new TableView<>();

        // Define columns for the table
        TableColumn<Transactions, Integer> colID = new TableColumn<>("Transaction ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));

        TableColumn<Transactions, String> colCurrency = new TableColumn<>("Currency");
        colCurrency.setCellValueFactory(new PropertyValueFactory<>("transCurrencyType"));

        TableColumn<Transactions, LocalDate> colDate = new TableColumn<>("Date");
        colDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

        TableColumn<Transactions, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

        TableColumn<Transactions, Integer> colAccount = new TableColumn<>("Account ID");
        colAccount.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getAccount().getAccountID()).asObject()
        );

        TableColumn<Transactions, Double> colAmount = new TableColumn<>("Amount");
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Transactions, String> colRemarks = new TableColumn<>("Remarks");
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        // Add columns to the table
        tableView.getColumns().addAll(colID, colCurrency, colDate, colType, colAccount, colAmount, colRemarks);

        // Populate the table with data
        tableView.getItems().addAll(Transactions.getTransactionList());

        // Button to go back to the main menu
        Button btBackToMenu = new Button("Back");
        btBackToMenu.setOnAction(e -> {
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(menuScene);
        });

        // Layout the table and button
        root.setCenter(tableView);
        root.setBottom(btBackToMenu);
        BorderPane.setAlignment(btBackToMenu, Pos.CENTER);
        BorderPane.setMargin(btBackToMenu, new javafx.geometry.Insets(10));

        // Create and return the scene
        return new Scene(root, 500, 300);
    }

    // Helper method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static Accounts findAccountByID(int accountID) {

        for (Accounts account : accountsList) {
            if (account.getAccountID() == accountID) {
                return account;
            }
        }
        return null;
    }

}