package com.example.iotgamehostjavafx;

import java.util.HashMap;

public class GamePiece {

    private String id;                  //identifier e.g. gp1
    private String IPAddress;           //IP address of game piece
    //private String tagRead;           //most recent read RFID tag
    private GameSquare location;        //current board location
    private HashMap<String, String> pocket; //pocket for collected items
    private int score;


    //constructor
    public GamePiece() {

        pocket = new HashMap<>();

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public GameSquare getLocation() {
        return location;
    }

    public void setLocation(GameSquare location) {
        this.location = location;
    }

    public HashMap<String, String> getPocket() {
        return pocket;
    }

    public void setPocket(HashMap<String, String> pocket) {
        this.pocket = pocket;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }




    public void addToPocket(String itemName, String itemValue) {

        this.pocket.put(itemName, itemValue);

    }


    public String takeFromPocket(String itemName) {

        String result;

        if(pocket.containsKey(itemName)){

            result = pocket.get(itemName);
            pocket.remove(itemName);

        }else {
            result = "NOTFOUND";

        }

        return result;

    }


    public boolean removeFromPocket(String itemName) {

        if(pocket.containsKey(itemName)) {
            pocket.remove(itemName);
            return true;

        }else {
            System.out.println("GamePiece.removeFromPocket(): - error - item does not exist");
            return false;
        }

    }

    public boolean inPocket(String item) {

        if(pocket.containsKey(item)) {
            return true;
        }else {
            return false;
        }

    }









    public void print() {

        System.out.println("============================");
        System.out.println("         P I E C E ");
        System.out.println("============================");
        System.out.println("id: " + getId());
        System.out.println("IPAddress: " + this.getIPAddress());
        System.out.println("location: " + this.getLocation());
        System.out.println("pocket: " + this.getPocket());
        System.out.println("score: " + this.getScore());


    }


    public static void main(String[] args) {

        //testing...

        GameSquare sq = new GameSquare();
        sq.setNumber(1);
        sq.setAction("+10");
        sq.addItem("homework","geography");
        sq.addItem("bonus", "+50");


        GameSquare sq2 = new GameSquare();
        sq2.setNumber(2);
        sq2.setAction("-20");
        sq2.addItem("record","song");
        sq2.addItem("bonus", "+100");


        GamePiece piece = new GamePiece();

        piece.setId("gp1");
        piece.setIPAddress("111.111.111.111");
        piece.setLocation(sq);
        piece.addToPocket("redkey", "10");
        piece.setScore(100);


        piece.print();



        piece.removeFromPocket("redkey");
        piece.removeFromPocket("redkey");
        piece.setLocation(sq2);

        piece.print();



    }










}//end GamePiece
