package com.example.iotgamehostjavafx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class GameSquare
{

    private int number;
    private String action;
    private HashMap<String, String> items;
    private ArrayList<GamePiece> occupiers;


    //============
    //constructor
    //============
    public GameSquare() {
        number = -1;
        action = "NONE";
        items = new HashMap<>();
        occupiers = new ArrayList<>();

    }

    public void setNumber(int number){
        this.number = number;

    }

    public int getNumber(){
        return this.number;

    }


    public void setAction(String action) {
        this.action = action;
    }


    public String getAction() {
        return this.action;
    }

    //================
    // addItem()
    //================
    public void addItem(String itemName, String itemValue) {

        items.put(itemName, itemValue);

    }

    //================
    // getItem()
    //================
    public String getItem(String itemName) {

        String result;

        //if item exists
        if(items.containsKey(itemName)) {
            return items.get(itemName);
        }else {

            return "NOTFOUND";
        }

    }


    //================
    // removeItem()
    //================
    public void removeItem(String itemName) {

        //item exists?
        if(items.containsKey(itemName)) {
            items.remove(itemName);     //remove item
        }else {
            System.out.println("Square.removeItem(): - Error - Item does not exist");
        }


    }


    //===============
    // addOccupier
    //===============
    public void addOccupier(GamePiece gamePiece) {

        this.occupiers.add(gamePiece);

    }

    //==================
    // removeOccupier()
    //==================
    public void removeOccupier(GamePiece gamePiece) {

        //game piece exists?
        if(occupiers.contains(gamePiece)){
            occupiers.remove(gamePiece);

        }else {

            System.out.println("Square.removeOccupier(): - error - gamePiece does not exist");
        }

    }

    //================
    // occupied()
    //================
    public boolean occupied() {
        boolean result;

        if(this.occupiers.isEmpty()) {
            return false;
        }else {
            return true;
        }

    }

    //================
    // getOccupiers()
    //================
    //returns the squares Occupiers list
    public ArrayList<GamePiece> getOccupiers() {

        return this.occupiers;

    }


    //=======
    // Print
    //=======
    public void print() {

        System.out.println("===========================");
        System.out.println("      SQUARE " + this.number);
        System.out.println("===========================");
        System.out.println("number: " + this.getNumber());
        System.out.println("action: " + this.getAction());
        System.out.println("items:" + this.items.toString());
        System.out.println("occupiers: " + this.getOccupiers().toString());
        System.out.println("occuppied(): " + this.occupied());
        System.out.println("============================");

    }


    //=======
    // main
    //=======
    //for testing...
    public static void main(String[] args) {

        /*
          number = -1;
        action = "NONE";
        items = new HashMap<>();
        occupiers = new ArrayList<>();
         */

        GamePiece gp = new GamePiece();

        GameSquare sq = new GameSquare();

        sq.setNumber(1);
        sq.setAction("+10");
        sq.addItem("token", "+50");
        sq.addItem("homework", "");
        sq.occupiers.add(gp);

        sq.print();

        sq.removeItem("homework");
        sq.removeOccupier(gp);

        sq.print();


    }






}// end GameSquare
