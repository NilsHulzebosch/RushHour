package com.company;

public class Main {

    public static void main(String[] args) {
        String text = "Hello world";
        String text_2 = "";
        for (int i = 0; i < text.length(); i++) {
            text_2 = text_2 + text.charAt(i);
            System.out.println(text_2);
        }

        // car lengths
        int smallCar = 2;
        int bigCar = 3;

        // tileBoard with cars on it
        int[][] tileBoard = new int[6][6];
        tileBoard[3][2] = 5;
        int redCar = tileBoard[3][2];
        System.out.println(redCar);


        // show available spots on tileboard
        boolean[][] isAvailable = new boolean[6][6];



    }
}