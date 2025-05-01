package com.example.iotgamehostjavafx;

/*
   =========================================
   IoTGameServer
   -------------
   Class for hosting connection from IoT Game piece
   by: J.Grace
   Student ID: 22863531
   =========================================
   Contains JavaFX GUI with game board display, network message inbox/outbox' areas and send message button

 */

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

//networking imports
import java.io.*;
import java.net.*;
import java.util.HashMap;


public class IoTGameServer extends Application {



    //Variables
    String deviceID = "hst";         //id of this device - host
    String separator = "#";         //separator for fields in network messages

    //============================
    //GamePiece Icon variables...
    //============================
    Image gamePieceImage;
    ImageView gamePieceImageView;
    int iconPreviousPosition = 1;



    //============================================================
    //start() called by JavaFX on startup, passes in Stage object
    //============================================================
    @Override
    public void start(Stage stage) throws IOException {












        //================
        //board display
        //================
        Pane gameBoardPane = new Pane();       //pane has less layout control, allows children to move/animate

        Image gameboardImage;
        ImageView gameboardView;




        //read image file
        try {

            //game board background image
            FileInputStream backgroundInput = new FileInputStream(NetworkingTest_01.class.getResource("/images/game_board_for_screen_01_600x425.png").getFile()); //note: the dots '.' in the package name are converted into slashes
            gameboardImage = new Image(backgroundInput);
            gameboardView = new ImageView(gameboardImage);

            gameBoardPane.getChildren().add(gameboardView);

            //============================
            //Game Piece Icon loading...
            //============================
            //FileInputStream input = new FileInputStream(NetworkingTest_01.class.getResource("/images/face_laugh_01.png").getFile()); //note: the dots '.' in the package name are converted into slashes
            //FileInputStream input = new FileInputStream(NetworkingTest_01.class.getResource("/images/man_02_cartoon.png").getFile()); //note: the dots '.' in the package name are converted into slashes
            FileInputStream input = new FileInputStream(NetworkingTest_01.class.getResource("/images/man_01.png").getFile()); //note: the dots '.' in the package name are converted into slashes
            gamePieceImage = new Image(input);               //read image from disc
            gamePieceImageView = new ImageView(gamePieceImage);   //create display view of image

            gameBoardPane.getChildren().add(gamePieceImageView);    //attach icon to pane



            Path myPath = new Path();
            myPath.getElements().add(new MoveTo(0,0));
            myPath.getElements().add(new LineTo(300,200));

            PathTransition myPt = new PathTransition();
            myPt.setDuration(Duration.millis(400));
            myPt.setPath(myPath);
            //myPt.setDelay(Duration.millis(delay));

            //myPt.setNode(myIV);
            myPt.setNode(gamePieceImageView);
            myPt.play();





            //image resource was found. Add image to inBox VBox container...
            //inBox = new VBox(inLabel, inTextArea, myImageView); //add image in inbox

        }catch(NullPointerException n) {

            //image resource was NOT found. Don't add to inBox VBox container...
            //inBox = new VBox(inLabel, inTextArea);

        }




































        //======
        //inbox
        //======

        //label
        Label inLabel = new Label("IN");
        inLabel.setStyle("-fx-font-size: 16pt");

        //textarea
        TextArea inTextArea = new TextArea("Received...");
        inTextArea.setPrefRowCount(4);
        inTextArea.setWrapText(true);
        //inTextArea.setStyle("-fx-background-color: #f69b9b;"); //light red

        //image
        //WORKS:
        //FileInputStream input = new FileInputStream(NetworkingTest_01.class.getResource("/images/face_laugh_01.png").getFile()); //the dots '.' in the package name are converted into slashes

        //container inBox
        VBox inBox;

        //read image file
        try {
            FileInputStream input = new FileInputStream(NetworkingTest_01.class.getResource("/images/face_laugh_01.pngWRONG").getFile()); //note: the dots '.' in the package name are converted into slashes
            Image myImage = new Image(input);
            ImageView myImageView = new ImageView(myImage);

            //image resource was found. Add image to inBox VBox container...
            inBox = new VBox(inLabel, inTextArea, myImageView); //add image in inbox

        }catch(NullPointerException n) {

            //image resource was NOT found. Don't add to inBox VBox container...
            inBox = new VBox(inLabel, inTextArea);

        }









        //=======
        //outbox
        //=======

        //label
        Label outLabel = new Label("OUT");
        outLabel.setStyle("-fx-font-size: 16pt");

        //text area
        TextArea outTextArea = new TextArea("to send...");
        outTextArea.setPrefRowCount(4);                         //number of rows to display

        //send button
        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-font-size: 12pt");
        sendButton.setOnAction(actionEvent -> {


            //inTextArea.setText("SEND BUTTON PRESSED");
            //sendMessage("BUTTON PRESSED");
            sendMessage("cmd", outTextArea.getText());


        });

        //container outBox
        VBox outBox = new VBox(outLabel, outTextArea, sendButton);


        //====================
        //container both boxes
        //====================
        HBox middleBox = new HBox(inBox, outBox);













        //===============================
        //piece 1 data-in fields display
        //===============================
        //labels
        Label label_pieceDataIn = new Label("======= Piece1 Data In =======");
        Label label_pieceIDIn = new Label("PieceID: ");
        Label label_subjectIn = new Label("Subject: ");
        Label label_valueIn= new Label("Value: ");
        //Label label_pieceLocationIn = new Label("Location: ");
        //Label label_timeRemainingIn = new Label("Time remaining: ");

        //text fields
        TextField display_pieceIDIn = new TextField("...");
        TextField display_subjectIn = new TextField("...");
        TextField display_valueIn = new TextField("...");
        //TextField display_pieceLocationIn = new TextField("...");
        //TextField display_timeRemainingIn = new TextField("...");

        //text field rows
        HBox pieceInRow0 = new HBox(label_pieceDataIn);
        HBox pieceInRow1 = new HBox(label_pieceIDIn, display_pieceIDIn);
        HBox pieceInRow2 = new HBox(label_subjectIn, display_subjectIn);
        HBox pieceInRow3 = new HBox(label_valueIn, display_valueIn);
        //HBox pieceInRow4 = new HBox(label_pieceLocationIn, display_pieceLocationIn);
        //HBox pieceInRow5 = new HBox(label_timeRemainingIn, display_timeRemainingIn);

        //text field alignment
        pieceInRow0.setAlignment(Pos.CENTER_RIGHT);
        pieceInRow1.setAlignment(Pos.CENTER_RIGHT);
        pieceInRow2.setAlignment(Pos.CENTER_RIGHT);
        pieceInRow3.setAlignment(Pos.CENTER_RIGHT);
        //pieceInRow4.setAlignment(Pos.CENTER_RIGHT);
        //pieceInRow5.setAlignment(Pos.CENTER_RIGHT);


        //all data rows combined
        VBox piece1DataInBox = new VBox(pieceInRow0,
                pieceInRow1,
                pieceInRow2,
                pieceInRow3
                //pieceInRow4,
                //pieceInRow5
        );

        piece1DataInBox.setStyle("-fx-font-weight: bold;"+
                "-fx-font-size:12pt;"
        );












        //===============================
        //piece 1 data-out fields display
        //===============================
        //labels
        Label label_pieceDataOut = new Label("                        ======= Game Host Data Out =======");
        Label label_pieceIDOut = new Label("PieceID: ");
        Label label_subjectOut = new Label("Subject: ");
        Label label_valueOut= new Label("Value: ");
        //Label label_pieceLocationOut = new Label("Location: ");
        //Label label_timeRemainingOut = new Label("Time remaining: ");

        //text fields
        TextField display_pieceIDOut = new TextField("hst");
        TextField display_subjectOut = new TextField("cmd");
        TextField display_valueOut = new TextField("exit");
        //TextField display_pieceLocationOut = new TextField("");
        //TextField display_timeRemainingOut = new TextField("");

        //send button for fields values
        Button sendButtonFields = new Button("Send");
        sendButtonFields.setStyle("-fx-font-size: 12pt");
        sendButtonFields.setOnAction(actionEvent -> {


            //inTextArea.setText("SEND BUTTON PRESSED");
            //sendMessage("BUTTON PRESSED");
            sendMessage(display_subjectOut.getText(), display_valueOut.getText());


        });



        //text field rows
        HBox pieceOutRow0 = new HBox(label_pieceDataOut);
        HBox pieceOutRow1 = new HBox(label_pieceIDOut, display_pieceIDOut);
        HBox pieceOutRow2 = new HBox(label_subjectOut, display_subjectOut);
        HBox pieceOutRow3 = new HBox(label_valueOut, display_valueOut);
        //button row
        HBox pieceOutRow4 = new HBox(sendButtonFields);

        //HBox pieceOutRow4 = new HBox(label_pieceLocationOut, display_pieceLocationOut);
        //HBox pieceOutRow5 = new HBox(label_timeRemainingOut, display_timeRemainingOut);







        //text field alignment
        pieceOutRow0.setAlignment(Pos.CENTER_RIGHT);
        pieceOutRow1.setAlignment(Pos.CENTER_RIGHT);
        pieceOutRow2.setAlignment(Pos.CENTER_RIGHT);
        pieceOutRow3.setAlignment(Pos.CENTER_RIGHT);
        pieceOutRow4.setAlignment(Pos.CENTER_RIGHT);

        //pieceOutRow4.setAlignment(Pos.CENTER_RIGHT);
        //pieceOutRow5.setAlignment(Pos.CENTER_RIGHT);

//
         /*
        //send button for fields values
        Button sendButtonFields = new Button("Send");
        sendButtonFields.setStyle("-fx-font-size: 12pt");
        sendButtonFields.setOnAction(actionEvent -> {


            //inTextArea.setText("SEND BUTTON PRESSED");
            //sendMessage("BUTTON PRESSED");
            sendMessage(display_subjectOut.getText(), display_valueOut.getText());


        });
        */







        //all data rows combined
        VBox piece1DataOutBox = new VBox(pieceOutRow0,
                pieceOutRow1,
                pieceOutRow2,
                pieceOutRow3,
                //pieceOutRow4,
                //pieceOutRow5,
                pieceOutRow4);
                //sendButtonFields);

        piece1DataOutBox.setStyle("-fx-font-weight: bold;"+
                "-fx-font-size:12pt;"
        );





        HBox dataInOutBoxes = new HBox(piece1DataInBox, piece1DataOutBox);


































        VBox mainBox = new VBox(gameBoardPane, middleBox, dataInOutBoxes);

        mainBox.setAlignment(Pos.CENTER_LEFT);


        //Debug print image file path:
        //outTextArea.setText(NetworkingTest_01.class.getResource("/images/face_laugh_01.png").getFile());


        //======
        //scene
        //======
        Scene myScene = new Scene(mainBox); //returns to this size, when fullscreen exit

        //=======
        //stage
        //=======
        //note: stage object is created and passed into this start() method by JavaFX
        stage.setTitle("IoT Game Server");
        stage.setScene(myScene);
        //stage.setWidth(600);stage.setHeight(400);
        stage.setFullScreen(true); //makes full screen
        stage.show();



        //=============================
        //listen to keyboard clicks...
        //=============================
        //Note: input seems to be captured by the TextAreas and not bubbled up - nothing is printed to the console since TextAreas added
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {

            System.out.println("Key pressed: " + event.getCode());


        });



        //=========================
        //close window 'X' clicked
        //=========================
        stage.setOnCloseRequest((event) -> {

            System.out.println("Closing Stage");
            System.exit(0); //exit java interpreter with error code 0

        });



        //=============
        //mouse cursor
        //=============
        myScene.setCursor(Cursor.OPEN_HAND);  //hand more appropriate for game?
        //myScene.setCursor(Cursor.CROSSHAIR);






        //=================
        //NETWORKING - IN
        //=================

        Thread myThread1 = new Thread(new Thread() {

            int count = 0;

            public void run() {

                ServerSocket serverSocket;

                //-----------
                //networking
                //-----------


                //-------
                //receive
                //-------

                //code taken from: https://www.youtube.com/watch?v=6G_W54zuadg&list=PLR7At0Hp_70SwUdsQrOkIdyflGr8ySPua
                //Java - Sockets - Introduction - 1 of 3
                //by: OneByteAtATime
                //publish date: 12 Mar 2012
                //taken: 20/04/25


                try {
                    serverSocket = new ServerSocket(50000);    //listen port 50,000

                    boolean finished = false;

                    while (!finished) {

                        System.out.println("Starting listening...");
                        Socket socket = serverSocket.accept();  //will halt waiting for connection?
                        InputStreamReader iR = new InputStreamReader(socket.getInputStream());
                        //BufferedReader bR = new BufferedReader(iR);
                        BufferedReader bR = new BufferedReader(iR);

                        String message = bR.readLine();

                        System.out.println("Connection received...");
                        System.out.println("Message: " + message);
                        //inTextArea.setText(message);



                        //Safely gets elements in the JavaFX thread to change
                        Platform.runLater( () -> {

                            inTextArea.setText(inTextArea.getText() + message + "\n");  //add to out textarea
                            inTextArea.setScrollTop(Double.MAX_VALUE);  //keep scrolled to bottom of list



                        });

                        //inTextArea.setText(inTextArea.getText() + message + "\n");  //add to out textarea
                        //inTextArea.setScrollTop(Double.MAX_VALUE);  //keep scrolled to bottom of list


                        /*
                        //movement icon test
                        Path myPath = new Path();
                        myPath.getElements().add(new MoveTo(300,300));
                        myPath.getElements().add(new LineTo(0,0));

                        PathTransition myPt = new PathTransition();
                        myPt.setDuration(Duration.millis(5000));
                        myPt.setPath(myPath);
                        //myPt.setDelay(Duration.millis(delay));

                        //myPt.setNode(myIV);
                        myPt.setNode(testImageView);
                        myPt.play();
                        */


                        //movePieceIcon(1);



                        //split message into fields
                        if(message != null) {
                            String[] messageFields = message.split("#");
                            display_pieceIDIn.setText(messageFields[0]);
                            display_subjectIn.setText(messageFields[1]);
                            display_valueIn.setText(messageFields[2]);



                            IoTGameMessage myMessage = new IoTGameMessage(message);
                            myMessage.print();


                            movePieceIcon(translateRFID(messageFields[2]));
                        }



                        //NOTE: does not display the received message until the client exits/ends connection
                        //      then shows the buffer contents (doesn't move on from the buffered reader line until then)


                    }

                }catch(java.io.IOException e) {

                    System.out.println("Couldn't open port for listening");

                }





            }//end run()


        });



        //-------------------------
        //NETWORKING - IN - START
        //-------------------------
        myThread1.start();
















        /*
        //=======================
        // NETWORKING - OUT
        //=======================
        //sends a message every second: "Java ++1"
        Thread myThread2 = new Thread(new Thread() {



            public void run() {


                //-----
                //send
                //-----

                int count = 0;

                try {
                    //Socket socket = new Socket("192.168.1.27", 50000); //game piece

                    //PrintStream pS = new PrintStream(socket.getOutputStream());
                    //pS.println("Java - Hello!");

                    boolean finished = false;
                    //Scanner myInput = new Scanner(System.in);   //utility for reading keyboard input

                    while(!finished) {


                        Socket socket = new Socket("192.168.1.27", 50000); //create socket connection to game piece
                        PrintStream pS = new PrintStream(socket.getOutputStream());  //create print stream for writing to game piece




                        //System.out.println("Enter a message: ");
                        //String myMessage = myInput.next();      //read keyboard input

                        //if(myMessage.equals("exit")) {          //message is exit
                        //finished = true;                    //quit loop
                        //}

                        String myMessage = ("Java: " + count++);    //create message

                        pS.print(myMessage);                    //send message

                        socket.close();                         //close socket connection


                        try {
                            Thread.sleep(1000);             //pause 1 sec
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }




                    }//end while

                    //myInput.close();       //close keyboard input connection



                    //socket.close();         //close socket connection


                }catch (IOException e) {

                    System.out.println("Couldn't open client socket connection");
                }




            }//end run()


        }); //end myThread2


        //----------------------
        //NETWORK - OUT - START
        //----------------------
        myThread2.start();


         */










    }//end start()





    //==============
    //SEND MESSAGE
    //==============
    public void sendMessage(String subject, String message) {



        //=======================
        // NETWORKING - OUT
        //=======================
        Thread outThread = new Thread(new Thread() {



            public void run() {


                //-----
                //send
                //-----

                //int count = 0;

                try {
                    //Socket socket = new Socket("192.168.1.27", 50000); //game piece

                    //PrintStream pS = new PrintStream(socket.getOutputStream());
                    //pS.println("Java - Hello!");

                    //boolean finished = false;
                    //Scanner myInput = new Scanner(System.in);   //utility for reading keyboard input

                    //while(!finished) {


                    Socket socket = new Socket("192.168.1.27", 50000); //create socket connection to game piece
                    PrintStream pS = new PrintStream(socket.getOutputStream());  //create print stream for writing to game piece




                    //System.out.println("Enter a message: ");
                    //String myMessage = myInput.next();      //read keyboard input

                    //if(myMessage.equals("exit")) {          //message is exit
                    //finished = true;                    //quit loop
                    //}

                    //String myMessage = (message);    //create message
                    String myMessage = (deviceID + separator + subject + separator + message);    //create message


                    System.out.println("**** MESSAGE TO Pi:" + myMessage);

                    pS.print(myMessage);                    //send message

                    socket.close();                         //close socket connection


                    //try {
                    //    Thread.sleep(1000);             //pause 1 sec
                    //} catch (InterruptedException e) {
                    //    throw new RuntimeException(e);
                    //}




                    //}//end while

                    //myInput.close();       //close keyboard input connection



                    //socket.close();         //close socket connection


                }catch (IOException e) {

                    System.out.println("Couldn't open client socket connection");
                }




            }//end run()


        }); //end outThread


        //----------------------
        //NETWORK - OUT - START
        //----------------------
        outThread.start();











    }//end sendMessage()



    /*
      ======================
       translateRFID()
      ======================
      translates the string serial number of an RFID tag to a game board square number
    */
    public int translateRFID(String serialNum) {

        HashMap <String, Integer> tagMappings = new HashMap<>();

        //inelegant way of doing this, but ran out of time.

        //rfid's were read into textbox of JavaFX. Pasted into notepad. Find and replace
        //used to add HashMap code around surroundings.

        tagMappings.put("584604745789",1);
        tagMappings.put("584604680242",2);
        tagMappings.put("584604614707",3);
        tagMappings.put("584604483633",4);
        tagMappings.put("584615428318",5);
        tagMappings.put("584615362783",6);
        tagMappings.put("584606887152",7);
        tagMappings.put("584615166162",8);
        tagMappings.put("584615231709",9);
        tagMappings.put("584615558951",10);
        tagMappings.put("584615821115",11);
        tagMappings.put("584615624484",12);
        tagMappings.put("584615690021",13);
        tagMappings.put("584615755578",14);
        tagMappings.put("584604155914",15);
        tagMappings.put("584604221493",16);
        tagMappings.put("584604287028",17);
        tagMappings.put("584604352567",18);
        tagMappings.put("584604418102",19);
        tagMappings.put("584604549168",20);

        Integer result = tagMappings.get(serialNum);

        //if not a recognised serial number, return square as 1 NOTE: need to check that this conversion from Integer to int is working correctly
        if(result == null) {
            result = 1;
        }

        return result;
    }



/*
========================
 movePieceIcon()
========================
moves icon representation of game piece on the game board
 */
public void movePieceIcon(int squareNumber) {


        //game board screen pixel locations of squares no. 1 - 20  (0 is not used/ignored)
        int[][] squareLocation = { {0,0},
                {533,373},//1
                {452,370},//2
                {376,370},//3
                {300,370},//4
                {224,370},//5
                {148,370},//6
                {68,370},//7
                {65,288},//8
                {64,211},//9
                {64,131},//10
                {67,51},//11
                {147,55},//12
                {225,55},//13
                {300,55},//14
                {376,55},//15
                {452,55},//16
                {533,50},//17
                {535,131},//18
                {535,210},//19
                {535,289},//20
        };


    //movement test
    Path myPath = new Path();
    //myPath.getElements().add(new MoveTo(300,300));
    myPath.getElements().add(new MoveTo(squareLocation[iconPreviousPosition][0],squareLocation[iconPreviousPosition][1]));
    //myPath.getElements().add(new LineTo(0,0));
    myPath.getElements().add(new LineTo(squareLocation[squareNumber][0],squareLocation[squareNumber][1]));

    PathTransition myPt = new PathTransition();
    myPt.setDuration(Duration.millis(250));
    myPt.setPath(myPath);
    //myPt.setDelay(Duration.millis(delay));

    //myPt.setNode(myIV);
    myPt.setNode(gamePieceImageView);
    myPt.play();

    iconPreviousPosition = squareNumber; //set icons previous location to now point to this square (for drawing a motion line from next time)


}//end movePieceIcon()










    //======
    //main
    //======
    public static void main(String[] args) {
        launch();


    }//end main()



}//end NetworkingTest_01 class
























