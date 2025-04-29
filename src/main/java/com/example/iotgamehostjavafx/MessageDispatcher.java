package com.example.iotgamehostjavafx;

import java.util.HashMap;


//on receiving a message, sends to the appropriate destination object

public class MessageDispatcher implements IoTGameMessageReceiver{


    //directory of destinations by subject for message dispatch
    //e.g. 'TAG' -> to GameLogic
    //e.g. 'sys' -> to GameSystem
    HashMap<String, IoTGameMessageReceiver> destinations = new HashMap<>();



    //adds routing destinations, filtering by message subject e.g. 'TAG' : GameLogic
    //                                                             'sys' : System
    public void addDestination(String subject, IoTGameMessageReceiver receiver) {

        this.destinations.put(subject, receiver);

    }



    //Reads a game message subject and passes message on to matching destination object
    public void messageIn(IoTGameMessage message){

        //read subject

        //subject entry filter exists?...
        if(this.destinations.containsKey(message.getSubject())) {

            destinations.get(message.getSubject()).messageIn(message);  //send message to destination object


        }else { //no entry for message subject...
            System.out.println("MessageDispatcher.messageIn(): error - no entry for message subject");

        }


        //send to appropriate destination


    }//end messageIn()


    public static void main(String[] args) {


        MessageDispatcher dispatcher = new MessageDispatcher(); //create dispatcher

        //network control
        NetInController controller = new NetInController();
        controller.setInAdapter(new NetInWIFI());                      //the WIFI adapter
        controller.getIn().setDispatch(dispatcher);             //set the WIFI adapter dispatcher (place to send received messages)

        GameLogic myGameLogic = new GameLogic();                //create logic
        GameSystem myGameSystem = new GameSystem();             //create system

        dispatcher.addDestination("TAG", myGameLogic);
        dispatcher.addDestination("sys", myGameSystem);



        controller.start(); //start network controller reading network messages


        //practice messages
        //IoTGameMessage myMessage1 = new IoTGameMessage("gp1#TAG#123456789");
        //IoTGameMessage myMessage2 = new IoTGameMessage("gp1#sys#restart");

        //put messages through dispatcher
        //dispatcher.messageIn(myMessage1);
        //dispatcher.messageIn(myMessage2);

    }//end main()




}//end MessageDispatcher
