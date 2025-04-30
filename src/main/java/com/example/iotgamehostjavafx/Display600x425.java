package com.example.iotgamehostjavafx;

public class Display600x425 extends ScreenLocator {




    //game board screen pixel locations of squares no. 1 - 20  (0 is not used/ignored)
    int[][] squareLocation = { {0,0},
            {533,373},//1
            {452,370},//2
            {376,370},//3
            {300,370},//4
            {224,370},//5
            {148,370},//6
            {68,370},//7
            {65,288},//8
            {64,211},//9
            {64,131},//10
            {67,51},//11
            {147,55},//12
            {225,55},//13
            {300,55},//14
            {376,55},//15
            {452,55},//16
            {533,50},//17
            {535,131},//18
            {535,210},//19
            {535,289},//20
    };



    @Override
    public int[] getLocation(int square) {

        if(square >= squareLocation.length) {
            //error index out of bounds
            System.out.println("Display600x425.getLocation(): - error - square out of bounds");
            return null;

        }else {
            int[] result = {squareLocation[square][0], squareLocation[square][1]};
            return result;
        }



    }




    //============
    // main
    //============
    //for testing...
    public static void main(String[] args) {

        Display600x425 display = new Display600x425();

        int[] location = display.getLocation(1);
        System.out.println("display.getLocation(1): " + location[0] + "," + location[1]);

        location = display.getLocation(5);
        System.out.println("display.getLocation(5): " + location[0] + "," + location[1]);

        location = display.getLocation(20);
        System.out.println("display.getLocation(20): " + location[0] + "," + location[1]);

        //location = display.getLocation(21); //larger than array
        //ystem.out.println("display.getLocation(20): " + location[0] + "," + location[1]);

    }










}//end Display600x425


