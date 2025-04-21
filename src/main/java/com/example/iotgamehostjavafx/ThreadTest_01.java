package com.example.iotgamehostjavafx;

public class ThreadTest_01 {

    int outSideVariable = 0;

    ThreadTest_01() {



    Thread myThread1 = new Thread(new Thread() {

        int count = 0;

        public void run() {

            while(true) {

                System.out.println("Thread1: " + count++);
                System.out.println("outsideVariable: " + outSideVariable++);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }//end while


        }


    });



    Thread myThread2 = new Thread(new Thread() {

        int count = 0;

        public void run() {

            while(true) {

                System.out.println("Thread2: " + count++);
                System.out.println("outsideVariable: " + outSideVariable++);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }//end while


        }


    });


    myThread1.start();
    myThread2.start();


    }//end constructor()



    public static void main(String[] args) {

        ThreadTest_01 tt1 = new ThreadTest_01();


    }



}
