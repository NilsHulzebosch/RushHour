package com.company;
import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    public static void main(String[] args) {
        Grid grid = new Grid(6);

        Vehicle hor_car = new Vehicle(true, 2);
        Vehicle hor_truck = new Vehicle(true, 3);
        Vehicle ver_car = new Vehicle(false, 2);
        Vehicle ver_truck = new Vehicle(false, 3);

        grid.addVehicle(hor_car, 0, 2);
        grid.addVehicle(ver_truck, 4, 0);

        grid.printGrid();

        // delay 3 seconds
        try {
            Thread.sleep(3000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.print("\n");



        grid.moveRight(ver_truck, 4, 0);
        //grid.moveRight(hor_car, 0, 2);
        //grid.moveDown(ver_truck, 4, 0);

        grid.printGrid();


    }
}

/*
// DELAY: Kunnen we later gebruiken voor animatie
// delay function 2 seconds (2000 milliseconds)
try {
        Thread.sleep(2000);
} catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
}
*/

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
                    // hor3 naar links; kijk of alle plekken links vrij zijn
                    if(tileBoard[i][j-1] == null &&
                        tileBoard[i][j-2] == null &&
                        tileBoard[i][j-3] != horizontal3length && j > 0) {
                            if(i > 0) {
                                if(tileBoard[i-1][j-1] == null &&
                                    tileBoard[i-2][j-1] != vertical3length) {
                                       tileBoard[i][j-1] = horizontal3length;
                                       tileBoard[i][j] = null;
                                }
                            }  else {
                                tileBoard[i][j-1] = horizontal3length;
                                tileBoard[i][j] = null;
                            }
                    }

                    // hor3 naar rechts; kijk of alle plekken rechts vrij zijn
                    if(tileBoard[i][j+3] == null && j < 3) {
                        if(i > 0) {
                            if(tileBoard[i-1][j+3] == null &&
                                    tileBoard[i-2][j+3] != vertical3length ) {
                                tileBoard[i][j+1] = horizontal3length;
                                tileBoard[i][j] = null;
                            }
                        } else {
                            tileBoard[i][j+1] = horizontal3length;
                            tileBoard[i][j] = null;
                    }

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