package com.example.iotgamehostjavafx;

public abstract class NetIn {


    //To be implemented by child classes...
    //e.g. NetInWIFI, NetInBluetooth etc.

    //start and stop reading from network
    abstract void start();
    abstract void stop();
    abstract void setDispatch(IoTGameMessageReceiver receiver);   //the place to send received messages to
    abstract void setController(NetInController controller);




}
