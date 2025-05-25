package com.example.isom3320_grp3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Main Menu");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        //Primary scene (Main Menu)
        Scene primaryScene = new Scene(vbox);
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
        btCreateType.setOnAction(e -> {System.out.println("Create Type");});
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
        BorderPane accountMenu = new BorderPane();
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button btAccountName = new Button("Account Name");
        Button btCurrencyType = new Button("Currency Type");
        Button btInitialBalance = new Button("Initial Balance");
        Button btBackToMenu = new Button("Back");
        // Add actionevents
        btAccountName.setOnAction(e -> System.out.println("Account Name"));
        btCurrencyType.setOnAction(e -> System.out.println("Currency Type"));
        btInitialBalance.setOnAction(e -> System.out.println("Initial Balance"));
        btBackToMenu.setOnAction(e ->
                primaryStage.setScene(menuScene)
                );
        // Add buttons t0 account menu
        buttonBox.getChildren().addAll(btAccountName, btCurrencyType, btInitialBalance);
        accountMenu.setCenter(buttonBox);
        accountMenu.setBottom(btBackToMenu);
        BorderPane.setAlignment(btBackToMenu, Pos.BOTTOM_LEFT);
        // Create and return new scene
        return new Scene(accountMenu);
    }


}