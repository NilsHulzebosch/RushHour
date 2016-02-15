package com.company;

public class Main {

    // board size
    static final int BOARD_SIZE = 6;

    private static String horizontal3length = "hor3";
    private static String horizontal2length = "hor2";
    private static String vertical3length = "ver3";
    private static String vertical2length = "ver2";

    // tileBoard with cars on it
    private static String[][] tileBoard = new String[BOARD_SIZE][BOARD_SIZE];


/*
    ["hor3"][][][][][]
    [][][][][][]
    [][][][][][]
    [][][]["ver2"][][]
    ["ver2"][][][][][]
    [][][][][][]
*/
    public static void main(String[] args) {

        tileBoard[0][0] = horizontal3length;
        tileBoard[0][3] = vertical2length;
        tileBoard[0][4] = vertical2length;

        // print the cars and their (top/left) coordinates
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                if(tileBoard[i][j] != null) {
                    System.out.println(i + j + tileBoard[i][j]);
                }
            }
        }

        /*
        String text = "Hello world";
        String text_2 = "";
        for (int i = 0; i < text.length(); i++) {
            text_2 = text_2 + text.charAt(i);
            System.out.println(text_2);
        }
        */


    }
}