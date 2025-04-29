package com.example.iotgamehostjavafx;

public class GameSystem implements IoTGameMessageReceiver {

    IoTGameMessage receivedMessage;


    @Override
    public void messageIn(IoTGameMessage message) {
        this.receivedMessage = message;
        print();

    }




    public void print() {


        System.out.println("==============================");
        System.out.println("       S Y S T E M");
        System.out.println("==============================");
        System.out.println("Message");
        System.out.println("from: " + receivedMessage.getFrom());
        System.out.println("subject: " + receivedMessage.getSubject());
        System.out.println("content: " + receivedMessage.getContent());
        System.out.println("======================================");
        System.out.println("======================================");


    }











}//end GameSystem
