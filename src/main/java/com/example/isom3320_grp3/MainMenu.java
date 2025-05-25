package com.example.isom3320_grp3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Main Menu");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        //buttons
        Button btCreateAccount =  new Button("Create Account");
        Button btCreateType = new Button("Create Type");
        Button btCreateTransaction = new Button("Create Transaction");
        Button btDisplayTransaction = new Button("Display Transaction");

        //ActionEvent Test
        btCreateAccount.setOnAction(e -> {System.out.println("Create Account");});
        btCreateType.setOnAction(e -> {System.out.println("Create Type");});
        btCreateTransaction.setOnAction(e -> {System.out.println("Create Transaction");});
        btDisplayTransaction.setOnAction(e -> {System.out.println("Display Transaction");});

        //Add to VBox
        vbox.getChildren().addAll(btCreateAccount, btCreateType, btCreateTransaction, btDisplayTransaction);

        //Scene
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}