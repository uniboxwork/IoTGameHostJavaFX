package com.example.iotgamehostjavafx;

import java.util.HashMap;

public class TranslatorRFID extends Translator {

    //holder Hashmap of serial numbers to board squares
    //e.g. 878998789 : 3
    HashMap<String, Integer> tagMappings;

    //============
    //Constructor
    //============
    public TranslatorRFID() {

        tagMappings = new HashMap<>();

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

    }


    //===============
    // getSquare()
    //===============
    @Override
    public int getSquare(String serialNumber) {

        Integer result = tagMappings.get(serialNumber);

        //if not a recognised serial number, return square as 1 NOTE: need to check that this conversion from Integer to int is working correctly
        if(result == null) {
            result = 1;
        }

        return result;


    }




    //==========
    // print()
    //==========
    public void print() {
        System.out.println("=======================================");
        System.out.println("     T R A N S L A T O R   R F I D ");
        System.out.println("=======================================");
        System.out.println(this.tagMappings);


    }




    //======
    // main
    //======
    //used for testing...
    public static void main(String[] args) {

        TranslatorRFID translator = new TranslatorRFID();
        translator.print();

        System.out.println("Serial Number: 584604745789 " + translator.getSquare("584604745789") );
        System.out.println("Serial Number: 584604680242 " + translator.getSquare("584604680242") );
        System.out.println("Serial Number: 584604155914 " + translator.getSquare("584604155914") );





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


    }




}
