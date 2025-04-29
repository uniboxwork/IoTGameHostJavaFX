package com.example.iotgamehostjavafx;


//concrete class of abstract NetIn...

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class NetInWIFI extends NetIn {

    //primary access to network
    //contains a thread that listens for network traffic (acts as server)
    //receives text message over TCP/IP and places into IoTGameMessage object and passes to the NetInController
    //continues until finished = true;


    //------------------
    //variables
    //------------------
    //flag for server loop
    boolean finished = true;


    //link to NetInController
    NetInController netInController;

    //link to dispatcher (or other place to send received messages)
    IoTGameMessageReceiver dispatcher;




    //============
    //constructor
    //============
    /*
    public void NetInWIFI(NetInController controller) {

        this.netInController = controller;     //set controller on initialise?

    }
     */



    public void setController(NetInController controller) {
        this.netInController = controller;

    } //setController


    @Override
    void setDispatch(IoTGameMessageReceiver receiver) { //set where to send received messages
        this.dispatcher = receiver;

    }

    //start the thread for listening
    public void start() {

        //reset loop flag
        this.finished = false;

        //create thread

        //reading loop here....








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


                    while (!finished) {   //loop until stop() sets finished true

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
                        //Platform.runLater( () -> {

                        //    inTextArea.setText(inTextArea.getText() + message + "\n");  //add to out textarea
                        //    inTextArea.setScrollTop(Double.MAX_VALUE);  //keep scrolled to bottom of list



                        //});

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
                            //String[] messageFields = message.split("#");
                            //display_pieceIDIn.setText(messageFields[0]);
                            //display_subjectIn.setText(messageFields[1]);
                            //display_valueIn.setText(messageFields[2]);

                            IoTGameMessage myMessage = new IoTGameMessage(message);
                            myMessage.print();

                            dispatcher.messageIn(myMessage);


                            //movePieceIcon(translateRFID(messageFields[2]));
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





























    }//end start()





    public void stop() {
        // set finished flag for loop to true
        this.finished = true;

    }//end stop()




    //=============================
    //      Main()
    //=============================
    //for testing
    public static void main(String[] args) {

        NetInWIFI in = new NetInWIFI();
        in.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        in.stop();



    }//end main()














}// end NetInWIFI
