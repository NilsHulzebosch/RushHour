package com.company;

public class Main {

    // NILS MAANDAGAVOND 24U:
    // IK HEB EEN KLEIN PROGRAMMA'TJE GESCHREVEN DAT DE RODE AUTO OP DE X-AS
    // NAAR DE POSITIE VOOR DE UITGANG BEWEEGT, KLIK OP RUN OM HET TE ZIEN GEBEUREN

    // board size
    static final int BOARD_SIZE = 6;
    static final int EXIT_X = 4; // if car is at x=4 (in matrix), it's in front of the exit
    static final int EXIT_Y = 2;

    private static String horizontal3length = "hor3";
    private static String horizontal2length = "hor2";
    private static String vertical3length = "ver3";
    private static String vertical2length = "ver2";
    private static String redCar = "hor2RED";

    // tileBoard with cars on it
    private static String[][] tileBoard = new String[BOARD_SIZE][BOARD_SIZE];


/*
    HUIDIGE TILEBOARD

    []["hor3"][][][][]
    [][][][][][]
    ["hor2RED"][][][][][]
    [][][]["ver2"][][]
    ["ver2"][][][][][]
    [][][][][][]
*/
    public static void main(String[] args) {

        // put 4 cars on the tileBoard
        tileBoard[0][1] = horizontal3length;
        tileBoard[2][0] = redCar;   // de auto waar het om draait
        tileBoard[3][3] = vertical2length;
        tileBoard[4][0] = vertical2length;

        // while redCar is not in front of exit, move to the left (animation)
        while(!redCar.equals(tileBoard[EXIT_X][EXIT_Y])) {
            for(int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {

                    // if j is smaller than 4 (the exit) and there is no car in front of the red car,
                    // move the red car to the right (later we will add to the left)
                    if (redCar.equals(tileBoard[i][j]) && j < 4) {
                        if(tileBoard[i][j+2] == null) {
                            tileBoard[i][j+1] = redCar;
                            tileBoard[i][j] = null;

                            // delay function 2 seconds (2000 milliseconds)
                            try {
                                Thread.sleep(2000);
                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }

                            // 'whipe' the screen (later we will use something more efficient)
                            System.out.print("\n");
                            System.out.print("\n");
                            System.out.print("\n");
                            System.out.print("\n");
                            System.out.print("\n");
                            System.out.print("\n");

                            System.out.println("Move red car to x pos " + (j+1) + ":");


                            // print the current tileBoard
                            for (int k = 0; k < BOARD_SIZE; k++) {
                                for (int l = 0; l < BOARD_SIZE; l++) {
                                    System.out.print(tileBoard[k][l] + " ");
                                }
                                System.out.print("\n");
                            }
                        }
                    }
                }
            }
        }

/*
ALLE ANDERE AUTO's BEWEGEN
WERKT NU NOG NIET HELEMAAL,
GA IK LATER NOG AANPASSEN

        // als richting vd auto horizontaal is, check links en rechts vd auto
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {

                if(redCar.equals(tileBoard[i][j])) {
                    if(tileBoard[i][j-1] == null && j > 0) {
                        tileBoard[i][j-1] = redCar;
                        tileBoard[i][j] = null;
                    }

                    if(tileBoard[i][j+3] == null && j < 4) {
                        tileBoard[i][j+1] = redCar;
                        tileBoard[i][j] = null;
                    }
                }

                if(horizontal3length.equals(tileBoard[i][j])) {
                    // kijk of links (j-1) vrij is en beweeg evt car
                    if(tileBoard[i][j-1] == null && j > 0) {
                        tileBoard[i][j-1] = horizontal3length;
                        tileBoard[i][j] = null;
                    }

                    // kijk of rechts (j+3) vrij is en beweeg evt car
                    //if(tileBoard[i][j+3] == null && j < 3) {
                    //    tileBoard[i][j+1] = horizontal3length;
                    //    tileBoard[i][j] = null;
                    //}

                    System.out.println(i + "|" + j + ": " + tileBoard[i][j]);
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

                    System.out.println(i + "|" + j + ": " +  tileBoard[i][j]);
                }

                if(vertical3length.equals(tileBoard[i][j])) {
                    System.out.println(i + "|" + j + ": " +  tileBoard[i][j]);
                }

                if(vertical2length.equals(tileBoard[i][j])) {
                    System.out.println(i + "|" + j + ": " +  tileBoard[i][j]);
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

*/


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