package com.example.iotgamehostjavafx;

public class IoTGameMessage {


    private String fieldSeparator = "#";    //character to split network message strings by

    private String from;
    private String subject;
    private String content;

    //--------------------------
    //default blank constructor
    //--------------------------
    //no input, do nothing
    public IoTGameMessage() {

    }

    //---------------------------
    //message string constructor
    //---------------------------
    //message from string - decode...
    public IoTGameMessage(String messageString) {

            String[] messageFields = messageString.split("#");
            this.from = messageFields[0];
            this.subject = messageFields[1];
            this.content = messageFields[2];

    }

    public void setFrom (String from) {
        this.from = from;

    }

    public void setSubject (String subject) {
        this.subject = subject;

    }

    public void setContent (String content) {
        this.content = content;

    }



    public String getFrom() {

        return this.from;
    }


    public String getSubject() {

        return this.subject;
    }


    public String getContent() {

        return this.content;
    }



    public void print() {

        System.out.println("=============================");
        System.out.println("       M E S S A G E");
        System.out.println("==============================");
        System.out.println("from: " + this.from);
        System.out.println("subject: " + this.subject);
        System.out.println("content: " + this.content);
        System.out.println("==============================");


    }//end print()



}//end IoTGameMessage
