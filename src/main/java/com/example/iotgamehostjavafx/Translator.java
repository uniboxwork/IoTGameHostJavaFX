package com.example.iotgamehostjavafx;


//Abstract class for translating game square serial numbers.
//Used by GameBoard to translate from serial number e.g. RFID or Barcode to board square number
public abstract class Translator {

    abstract public int getSquare(String serialNumber);

}
