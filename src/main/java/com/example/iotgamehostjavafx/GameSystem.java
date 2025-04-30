package com.example.iotgamehostjavafx;

import java.util.HashMap;

public class GameSystem implements IoTGameMessageReceiver {

    IoTGameMessage receivedMessage;


    //variables
    GameBoard gameBoard;                    //gameboard of game
    HashMap<String, GamePiece> gamePieces;  //registered game pieces
    private String baseName = "gp";         //basis for generating names. gp = game piece. Added with intName beow to produce unique ids
    private int intNamer;                   //a number appended to generate new user names. Incremented by 1 each use.



    //=============
    // constructor
    //=============
    public GameSystem(GameBoard gameBoard) {
        gameBoard = gameBoard;
        gamePieces = new HashMap<>();
        intNamer = 1;

        //for development purposes create and register initial user
        GamePiece gp1 = new GamePiece();

        GameSquare startSquare = gameBoard.getSquare(1);        //start piece at square 1
        gp1.setLocation(startSquare);

        gp1.setId("gp1");
        register("gp1", gp1);


    }




    //=============
    // messageIn()
    //=============
    //receives messages from the dispatcher (and other objects)
    @Override
    public void messageIn(IoTGameMessage message) {
        this.receivedMessage = message;
        print();



    }//messageIn()


    //================
    // getGamePiece()
    //================
    public GamePiece getGamePiece(String id) {

        if(gamePieces.containsKey(id)) {
            return gamePieces.get(id);      //return the gamePiece associated with id

        }else {
            System.out.println("GameSystem.getGamePiece(): - error - id does not exist");
            return null;
        }


    }//end getGamePiece()






    //============
    // newName()
    //============
    //helper method for registering a new GamePiece. Creates a unique name id
    private String newName() {

        String newName = baseName + intNamer;   //e.g gp1, gp2...
        intNamer++;

        return newName;
    }



    //============
    // register()
    //============
    public void register(String id, GamePiece gamePiece) {
        if(!gamePieces.containsKey(id)) {
            gamePieces.put(id, gamePiece);

        }else {
            System.out.println("GameSystem.register(): - error - id already registered");
        }

    }//end register()






    //=========
    // print()
    //=========
    public void print() {


        System.out.println("==============================");
        System.out.println("       S Y S T E M");
        System.out.println("==============================");
        System.out.println("Message");
        System.out.println("from: " + receivedMessage.getFrom());
        System.out.println("subject: " + receivedMessage.getSubject());
        System.out.println("content: " + receivedMessage.getContent());
        System.out.println("-------------------------------------");
        System.out.println("Game Pieces: " + gamePieces);
        System.out.println("======================================");
        System.out.println();




    }//end print()



    //============
    // main()
    //============
    //used for testing...
    public static void main(String[] args) {

        GameBoard testGameBoard = new GameBoard(20);
        GameSystem gS = new GameSystem(testGameBoard);

        /*
        //registering game pieces
        GamePiece gp1, gp2, gp3;
        gp1 = new GamePiece();
        gp2 = new GamePiece();
        gp3 = new GamePiece();

        gS.register("gp1", gp1);
        gS.register("gp2", gp2);
        gS.register("gp3", gp3);

         */


        System.out.println("GameSystem.newName(): " + gS.newName());
        IoTGameMessage message = new IoTGameMessage("gp1#sys#pause");
        gS.messageIn(message);
        gS.print();

        GamePiece myGamePiece = gS.getGamePiece("gp1");
        System.out.println("getGamePiece(): ");
        System.out.println(myGamePiece);





    }//end main()











}//end GameSystem
