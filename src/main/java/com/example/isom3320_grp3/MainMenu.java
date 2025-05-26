package com.example.isom3320_grp3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Main Menu");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        //Primary scene (Main Menu)
        Scene primaryScene = new Scene(vbox , 300 , 200);
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
            Scene transactionScene = createTransactionScene(primaryStage, primaryScene);
            primaryStage.setScene(transactionScene);
        });
        btDisplayTransaction.setOnAction(e -> {System.out.println("Display Transaction");});

        //Add to VBox
        vbox.getChildren().addAll(btCreateAccount, btCreateType, btCreateTransaction, btDisplayTransaction);

        //show
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }

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
        TextField tfCurrencyType = new TextField();
        tfCurrencyType.setPromptText("Currency Type");
        currencyTypeBox.getChildren().addAll(lblCurrencyType, tfCurrencyType);

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
            //
            //need stuff here
            //
            System.out.println("Create Account");});

        //two hbox
        HBox leftBox = new HBox(btBackToMenu);
        HBox rightBox = new HBox(btCreateAccount);
        leftBox.setAlignment(Pos.BASELINE_LEFT);
        rightBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox bottomBox = new HBox(btBackToMenu, btCreateAccount);
        bottomBox.setSpacing(160);
        accountMenu.setBottom(bottomBox);

        // Create and return new scene
        return new Scene(accountMenu , 300 , 200);
    }

    //method for create transactions type
    private Scene createTypeScene(Stage primaryStage , Scene menuScene) {
        primaryStage.setTitle("Create Transaction Type");
        BorderPane typeMenu = new BorderPane();

        //textfields
        VBox tfBox = new VBox(10);
        tfBox.setAlignment(Pos.CENTER);

        HBox typeBox = new HBox(10);
        Label lblType = new Label("Transaction Type:");
        TextField tftype = new TextField();
        tftype.setPromptText("Transaction Type");
        typeBox.getChildren().addAll(lblType, tftype);

        // Add tf t0 account menu
        tfBox.getChildren().addAll(typeBox);
        typeMenu.setCenter(tfBox);

        //Buttons
        Button btBackToMenu = new Button("Back");
        Button btCreateAccount = new Button("Create Account");

        // Add actionevents
        btBackToMenu.setOnAction(e ->{
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(menuScene);
        });

        btCreateAccount.setOnAction(e -> {
            //
            //need stuff here
            //
            System.out.println("Create Account");});

        //two hbox
        HBox leftBox = new HBox(btBackToMenu);
        HBox rightBox = new HBox(btCreateAccount);
        leftBox.setAlignment(Pos.BASELINE_LEFT);
        rightBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox bottomBox = new HBox(btBackToMenu, btCreateAccount);
        bottomBox.setSpacing(160);
        typeMenu.setBottom(bottomBox);

        // Create and return new scene
        return new Scene(typeMenu , 300 , 200);
    }

    //Create Transaction
    private Scene createTransactionScene(Stage primaryStage , Scene menuScene) {
        primaryStage.setTitle("Create Transaction Type");
        BorderPane transactionMenu = new BorderPane();

        //textfields
        VBox transactionBox = new VBox(10);
        transactionBox.setAlignment(Pos.CENTER);

        HBox transactionIDBox = new HBox(10);
        Label lblTransactionID = new Label("Transaction ID:");
        TextField tfTransactionID = new TextField();
        tfTransactionID.setPromptText("Transaction ID");
        transactionIDBox.getChildren().addAll(lblTransactionID, tfTransactionID);

        HBox dateBox = new HBox(10);
        Label lblDate = new Label("Date:");
        TextField tfDate = new TextField();
        tfDate.setPromptText("Date");
        dateBox.getChildren().addAll(lblDate, tfDate);

        //ComboBox
        HBox transactionAccountBox = new HBox(10);
        Label lblTransactionAccount = new Label("Transaction Type:");
        ComboBox<String> transactionAccountComboBox = new ComboBox<>();
        transactionAccountComboBox.setPromptText("Transaction Account");
        //transactionAccountComboBox.getItems()
        transactionAccountBox.getChildren().addAll(lblTransactionAccount, transactionAccountComboBox);

        HBox transactionTypeBox = new HBox(10);
        Label lblTransactionType = new Label("Transaction Type:");
        ComboBox<String> transactionTypeComboBox = new ComboBox<>();
        transactionTypeComboBox.setPromptText("Transaction Type");
        //transactionTypeComboBox.getItems()
        transactionTypeBox.getChildren().addAll(lblTransactionType, transactionTypeComboBox);

        //textfields
        HBox transactionAmountBox = new HBox(10);
        Label lblTransactionAmount = new Label("Transaction Amount:");
        TextField tfTransactionAmount = new TextField();
        tfTransactionAmount.setPromptText("Transaction Amount");
        transactionAmountBox.getChildren().addAll(lblTransactionAmount, tfTransactionAmount);

        HBox transactionRemarksBox = new HBox(10);
        Label lblTransactionRemarks = new Label("Transaction Remarks:");
        TextField tfTransactionRemarks = new TextField();
        tfTransactionRemarks.setPromptText("Transaction Remarks");
        transactionRemarksBox.getChildren().addAll(lblTransactionRemarks, tfTransactionRemarks);
        HBox transactionDateBox = new HBox(10);

        //add transaction box to transaction menu
        transactionBox.getChildren().addAll(
                transactionIDBox, dateBox , transactionAccountBox,
                transactionTypeBox, transactionAmountBox, transactionRemarksBox);
        transactionMenu.setCenter(transactionBox);

        //Add buttons
        Button btBackToMenu = new Button("Back");
        Button btCreateAccount = new Button("Create Account");

        btBackToMenu.setOnAction(e ->{
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(menuScene);
        });
        btCreateAccount.setOnAction(e -> {
            System.out.println("Create Account");
        });
        HBox leftBox = new HBox(btBackToMenu);
        HBox rightBox = new HBox(btCreateAccount);
        HBox buttonBox = new HBox(leftBox, rightBox);
        buttonBox.setSpacing(160);
        transactionMenu.setBottom(buttonBox);

        return new Scene(transactionMenu , 300 , 200);
    }
}