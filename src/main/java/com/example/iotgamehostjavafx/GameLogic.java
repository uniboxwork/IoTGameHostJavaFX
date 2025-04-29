package com.example.iotgamehostjavafx;

public class GameLogic implements IoTGameMessageReceiver {

    IoTGameMessage receivedMessage;

    @Override
    public void messageIn(IoTGameMessage message) {

        this.receivedMessage = message;
        this.print();                   //just prints message received

    }

    public void print() {


        System.out.println("===========================");
        System.out.println("       L O G I C");
        System.out.println("===========================");
        System.out.println("Message");
        System.out.println("from: " + receivedMessage.getFrom());
        System.out.println("subject: " + receivedMessage.getSubject());
        System.out.println("content: " + receivedMessage.getContent());
        System.out.println("======================================");
        System.out.println("======================================");


    }


    public static void main(String[] args) {


        GameLogic myGameLogic = new GameLogic();


    }

}
