package com.example.iotgamehostjavafx;

/*
   =========================================
   Class for testing network communication
   =========================================
   Contains JavaFX GUI with inbox and outbox textareas and send button

 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//networking imports
import java.io.*;
import java.net.*;


public class NetworkingTest_01 extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("IoT Game - Networking Test");
        //stage.setScene(scene);




        //  #f69b9b  light red

//        myTextArea.setPrefColumnCount(50);

//        //button styling
//        button1.setStyle("-fx-font-size:24pt;" +
//                "-fx-background-color: #b23a3a;" +
//                "-fx-text-fill: #720505;"
//
//        );

        //inbox
        Label inLabel = new Label("IN");
        inLabel.setStyle("-fx-font-size: 24pt");
        TextArea inTextArea = new TextArea("Received...");
        //inTextArea.setStyle("-fx-background-color: #f69b9b;"); //light red

        //image
        //WORKS:
        //FileInputStream input = new FileInputStream(NetworkingTest_01.class.getResource("/images/face_laugh_01.png").getFile()); //the dots '.' in the package name are converted into slashes


        VBox inBox;

        try {
            FileInputStream input = new FileInputStream(NetworkingTest_01.class.getResource("/images/face_laugh_01.png").getFile()); //the dots '.' in the package name are converted into slashes
            Image myImage = new Image(input);
            ImageView myImageView = new ImageView(myImage);

            //image resource was found. Add image to inBox VBox...
            inBox = new VBox(inLabel, inTextArea, myImageView); //add image in inbox

        }catch(NullPointerException n) {

            //image resource was NOT found. Don't add to inBox VBox...
            inBox = new VBox(inLabel, inTextArea);

        }










        //outbox
        Label outLabel = new Label("OUT");
        outLabel.setStyle("-fx-font-size: 24pt");
        TextArea outTextArea = new TextArea("to send...");
        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-font-size: 24pt");

        sendButton.setOnAction(actionEvent -> {


                inTextArea.setText("SEND BUTTON PRESSED");


        });










        VBox outBox = new VBox(outLabel, outTextArea, sendButton);


        HBox middleBox = new HBox(inBox, outBox);


        //Debug print image file path:
        //outTextArea.setText(NetworkingTest_01.class.getResource("/images/face_laugh_01.png").getFile());

        Scene myScene = new Scene(middleBox); //returns to this size, when fullscreen exit
        //myScene.add(button);

        stage.setScene(myScene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setFullScreen(true); //makes full screen
        stage.show();


        //listen to keyboard clicks...
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {

            System.out.println("Key pressed: " + event.getCode());


        });


        //close window 'X' clicked
        stage.setOnCloseRequest((event) -> {

            System.out.println("Closing Stage");
            System.exit(0); //exit java interpreter with error code 0

        });





        //set scene mouse cursor icon
        myScene.setCursor(Cursor.OPEN_HAND);
        //myScene.setCursor(Cursor.CROSSHAIR);



















    }

    public static void main(String[] args) {
        launch();


    }
}
























