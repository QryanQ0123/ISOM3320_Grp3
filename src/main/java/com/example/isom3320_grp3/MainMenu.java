package com.example.isom3320_grp3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        btCreateTransaction.setOnAction(e -> {System.out.println("Create Transaction");});
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
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);

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

        // Add textfield t0 account menu
        buttonBox.getChildren().addAll(accountNameBox, currencyTypeBox, initialBalanceBox );
        accountMenu.setCenter(buttonBox);

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

}