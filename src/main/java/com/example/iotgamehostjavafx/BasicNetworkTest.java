package com.example.iotgamehostjavafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BasicNetworkTest {



        public static void main(String args[]) {


            ServerSocket serverSocket;

            //-----------
            //networking
            //-----------

            /*
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

                while (finished == false) {

                    System.out.println("Starting listening...");
                    Socket socket = serverSocket.accept();  //will halt waiting for connection?
                    InputStreamReader iR = new InputStreamReader(socket.getInputStream());
                    BufferedReader bR = new BufferedReader(iR);

                    String message = bR.readLine();

                    System.out.println("Connection received...");
                    System.out.println("Message: " + message);

                    //NOTE: does not display the received message until the client exits/ends connection
                    //      then shows the buffer contents (doesn't move on from the buffered reader line until then)


                }

            }catch(java.io.IOException e) {

                System.out.println("Couldn't open port for listening");

            }
            */



            //-----
            //send
            //-----

            try {
                Socket socket = new Socket("192.168.1.27", 50000);

                PrintStream pS = new PrintStream(socket.getOutputStream());
                //pS.println("Java - Hello!");

                boolean finished = false;
                Scanner myInput = new Scanner(System.in);

                while(!finished) {
                    System.out.println("Enter a message: ");

                    String myMessage = myInput.next();      //read keyboard input

                    if(myMessage.equals("exit")) {
                        finished = true;
                    }
                    pS.print(myMessage);                  //send message

                }//end while

                myInput.close();



                socket.close();


            }catch (IOException e) {

                System.out.println("Couldn't open client socket connection");
            }









        }//end main()



}
