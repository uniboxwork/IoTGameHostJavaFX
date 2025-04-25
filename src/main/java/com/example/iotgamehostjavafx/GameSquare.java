package com.example.iotgamehostjavafx;

import java.util.ArrayList;

public class GameSquare
{

    private int id;
    private String label;
    private String action;
    private ArrayList<String> tags;                         //RFID tags that represent this square. Can be more than one to allow for larger squares represented by multiple tags
    private ArrayList<String> items;
    private ArrayList<GamePiece> visitors;

    //constructor
    public GameSquare() {
        id = -1;
        label = "NONE";
        tags = new ArrayList<>();
        items = new ArrayList<>();
        visitors = new ArrayList<>();

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addTag(String tagID) {
        this.tags.add(tagID);

    }


    public void setTag(String tag) {

        this.tag = tag;
    }

    public void addItem()




}// end GameSquare
