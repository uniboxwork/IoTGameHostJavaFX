package com.example.iotgamehostjavafx;

import java.util.ArrayList;



//==========================
//  G A M E   B O A R D
//==========================
public class GameBoard {

    //variables
    //---------
    ArrayList<GameSquare> squares;          //holder for game squares
    private Translator translator;          //abstract translator object for translating serial numbers to square positions. Could be RFIDTranslator or BarcodeTranslator or other


    //============
    //constructor
    //============
    //creates a board with the number of squares
    public GameBoard(int numberOfSquares) {

        this.squares = new ArrayList<>(); //initialise ArrayList

        create(numberOfSquares);

        //loop for all squares



    }



    //==========
    // create()
    //==========
    public void create(int number) {

        //arrays are zero based but boards are usually one based
        //It's important that the number field of each square matches its ArrayList position
        //so that position calculations from the dice can be made. e.g. move forward 6 places.
        //Solution: create a 0 square but do not use it and increase the number of squares created by one
        //To do this: change the if condition in the loop from '< number' to '<= number' should give one
        //more extra.
        //Will need to check for correct cross over from last square on board back to first. Must go to
        //position 1 and not 0

        for(int i = 0; i <= number; i++) {
            GameSquare gSQ = new GameSquare();
            gSQ.setNumber(i);
            this.squares.add(gSQ);

        }//end for


    }//end create()


    public void setTranslator(Translator translator) {
        this.translator = translator;
    }



    public GameSquare getSquare(int number) {

        if(number < squares.size()) {
            return squares.get(number);
        }else {
            System.out.println("GameSquare.getSquare(): - error - number larger than ArrayList size");
            return null;
        }


    }//end getSquare()


    //=======================
    // getSquareFromSerial()
    //=======================
    //translates a string serial number into a board location, using the Translator object
    public GameSquare getSquareFromSerial(String serial) {

        return getSquare(translator.getSquare(serial));

    }







    //================
    // printSquares()
    //================
    //prints all squares
    public void printSquares() {

        //loop for all squares in the ArrayList...
        for(int i=0; i<squares.size(); i++) {

            squares.get(i).print();     //print the square

        }

    }



    //=======
    //print
    //=======
    public void print() {

        System.out.println("===============================");
        System.out.println("     G A M E   B O A R D ");
        System.out.println("===============================");
        System.out.println("translator: " + this.translator.toString());
        this.printSquares();

    }


    //=======
    // main
    //=======
    //used for testing...
    public static void main(String[] args) {



        GameBoard board = new GameBoard(5);
        board.setTranslator(new TranslatorRFID());

        GameSquare mySquare = board.getSquare(3);
        System.out.println("board.getSquare(3): ");
        mySquare.print();




        /*
        for samples...
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
         */

        board.print();

        //test translator...
        GameSquare sqFromSerial = board.getSquareFromSerial("584604745789");
        System.out.println("board.getSquareFromSerial(584604745789): ");
        sqFromSerial.print();







    }



}//end board
