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
    []["hor3"][][][][]
    [][][][][][]
    [][][][][][]
    [][][]["ver2"][][]
    ["ver2"][][][][][]
    [][][][][][]
*/
    public static void main(String[] args) {

        // put 3 cars on the tileBoard
        tileBoard[0][1] = horizontal3length;
        tileBoard[3][3] = vertical2length;
        tileBoard[4][0] = vertical2length;

        // print the current tileBoard
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(tileBoard[i][j] + " ");
            }
            System.out.print("\n");
        }

        /*
        // print the cars and their (top/left) coordinates
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(tileBoard[i][j] != null) {
                    System.out.println(i + j + tileBoard[i][j]);
                }
            }
        }
        */

        // als richting vd auto horizontaal is, check links en rechts vd auto
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(horizontal3length.equals(tileBoard[i][j]) && j > 0) {
                    // kijk of links (j-1) vrij is en beweeg evt car
                    if(tileBoard[i][j-1] == null) {
                        tileBoard[i][j-1] = horizontal3length;
                        tileBoard[i][j] = null;
                    }

                    // kijk of rechts (j+3) vrij is en beweeg evt car
                    //if(tileBoard[i][j+3] == null) {
                    //    tileBoard[i][j+1] = horizontal3length;
                    //    tileBoard[i][j] = null;
                    //}

                    System.out.println(i + "|" + j + tileBoard[i][j]);
                }

                if(horizontal2length.equals(tileBoard[i][j]) && j > 0) {
                    // kijk of links (j-1) vrij is en beweeg evt car
                    if(tileBoard[i][j-1] == null) {
                        tileBoard[i][j-1] = horizontal3length;
                        tileBoard[i][j] = null;
                    }

                    // kijk of rechts (j+2) vrij is en beweeg evt car
                    if(tileBoard[i][j+2] == null) {
                        tileBoard[i][j+1] = horizontal3length;
                        tileBoard[i][j] = null;
                    }

                    System.out.println(i + "|" + j + tileBoard[i][j]);
                }

                if(vertical3length.equals(tileBoard[i][j])) {
                    System.out.println(i + "|" + j + tileBoard[i][j]);
                }

                if(vertical2length.equals(tileBoard[i][j])) {
                    System.out.println(i + "|" + j + tileBoard[i][j]);
                }
            }
        }

        // print the new tileBoard
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(tileBoard[i][j] + " ");
            }
            System.out.print("\n");
        }

        // als richting vd auto verticaal is, check boven en onder vd auto


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