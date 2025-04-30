package com.example.iotgamehostjavafx;

import javafx.application.Platform;

public class GameLogic implements IoTGameMessageReceiver {

    IoTGameMessage receivedMessage; //debugging


    //variables
    //---------
    GameSystem gameSystem;          //link to GameSystem. Used to access register of game pieces
    GameBoard gameBoard;            //link to the GameBoard

    IoTGameServer_02 gameServerGUI ;  //connection to JavaFX screen


    //=============
    // constructor
    //=============
    //takes a link to the GameSystem and Gameboard
    public GameLogic(GameSystem gameSystem, GameBoard gameBoard, IoTGameServer_02 gameServerGUI) {
        setGameSystem(gameSystem);
        setGameBoard(gameBoard);
        setGameServerGUI(gameServerGUI);


    }


    public void setGameSystem(GameSystem system) {

        this.gameSystem = system;
    }

    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;

    }

    public void setGameServerGUI(IoTGameServer_02 gameServerGUI) {

        this.gameServerGUI = gameServerGUI;
    }




    //=============
    // messageIn()
    //=============
    //receives messages from dispatcher (or other objects)
    @Override
    public void messageIn(IoTGameMessage message) {

        this.receivedMessage = message;
        this.print();                   //just prints message received


        //what action to take? Check subject...
        switch(message.getSubject()) {
            case "TAG":
                //move game piece
                movePiece(message);
                break;
            case "stat":
                //update state
                break;
            case "sys":
                System.out.println("GameLogic.messageIn(): - error - sys message received");
            default:
                System.out.println("GameLogic.messageIn(): - error - message subject not recognised");


        }//end switch()

    }//end messageIn()



    public void movePiece(IoTGameMessage message) {

        gameServerGUI.updateInTextArea("GameLogic.movePiece(): ");
        gameServerGUI.updateInTextArea("from: " + message.getFrom());
        gameServerGUI.updateInTextArea("subj: " + message.getSubject());
        gameServerGUI.updateInTextArea("cont: " + message.getContent());



        //get current position of piece

        //get game piece associated with message
        GamePiece gamePiece = gameSystem.getGamePiece(message.getFrom()); //get the gamePiece object associated with the sender of the message

        //get the current board location of the piece
        GameSquare currentSquare = gamePiece.getLocation();  //get the current square

        //get the TAG location (translate tag serial number to location)
        GameSquare nextSquare = gameBoard.getSquareFromSerial(message.getContent());

        //move piece visually and set the pieces location to new location

        //get start and finish squares
        int squareNumberStart = currentSquare.getNumber();
        int squareNumberFinish = nextSquare.getNumber();

        //trying moving the piece from start square to finish square on screen...
        gameServerGUI.movePieceIcon(squareNumberStart, squareNumberFinish);

        gamePiece.setLocation(nextSquare);  //set the players location to point to the new square








        //trying moving the piece on screen...
        //gameServerGUI.movePieceIcon(7,17);





        /*

        //move logically
        //---------------
        //get game piece associated with message
        GamePiece gamePiece = gameSystem.getGamePiece(message.getFrom()); //get the gamePiece object associated with the sender of the message

        //get the current board location of the piece
        GameSquare currentSquare = gamePiece.getLocation();  //get the current square

        //get the TAG location (translate tag serial number to location)
        GameSquare nextSquare = gameBoard.getSquareFromSerial(message.getContent());


        //move piece to new position logically
        nextSquare.addOccupier(gamePiece);

        //remove piece from previous/current square
        currentSquare.removeOccupier(gamePiece);

        //set gamePiece location to point to new location also
        gamePiece.setLocation(nextSquare);


        //move visually
        //-------------
        //draw from current square to nextSquare locations


        //now draw move visually on screen

        //get player id

        //get player

        //get current square



        //logical move

        //visual animation move



         */

    }//end move




    //==========
    // print()
    //==========
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


    //=========
    // main()
    //=========
    //for testing...
    public static void main(String[] args) {

        /*
        GameSystem sys = new GameSystem();
        GameBoard board = new GameBoard(20);
        board.setTranslator(new TranslatorRFID());

        //board.print();

        GameLogic myGameLogic = new GameLogic(sys,board);
        myGameLogic.movePiece(new IoTGameMessage("gp1#TAG#7899876"));

        //ask it to move something
        //board.print();

         */


    }

}
