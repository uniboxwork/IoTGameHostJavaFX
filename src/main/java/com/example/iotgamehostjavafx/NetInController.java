package com.example.iotgamehostjavafx;

public class NetInController {

    //acts as an interface to NetIn child objects

    NetIn netIn; //holder for the netIn child object controlled by this controller e.g. a NetInWIFI or NetInBluetooth


    //attach NetIn object for controlling (starting/stopping, getting messages passed in from)
    public void setIn(NetIn netIn) {
        this.netIn = netIn;


    }//end attach



    public NetIn getIn(){

        return this.netIn;

    }



    public void start() {
        //start attached NetIn object

        if(this.netIn != null) {  //controlled object exists...
            netIn.start();
        }else {
            //error
            System.out.println("NetInController.start(): error - no NetIn object attached.");

          }

    }//end start()



    public void stop() {
        //stop attached NetIn object

        if(this.netIn != null) {  //controlled object exists...
            netIn.stop();
        }else {
            //error
            System.out.println("NetInController.stop(): error - no NetIn object attached.");

        }





    }//end stop()


    /*
    //abstract void attach(IoTGameServer iGS);
    //it will call iGS.messageIn(message); -- this is the connection back to the game server making an attachment.

    //use (this) to pass the IoTGameServer to make the attachment
    the thread code, put at call to iGS.messageIn(Message) when ever a message comes in, pass it there
    */


    public static void main(String[] args) {

        NetInWIFI netIn = new NetInWIFI();

        NetInController myController = new NetInController();
        myController.setIn(netIn);

        myController.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        myController.stop();


    }//end main()








}
