/*
 * File:    LifeSimulation.java
 * Name:    Duilio Lucio    
 * Recent Modification: 02/26/2023
 */

import java.util.Arrays;

import Sudoku.LandscapeDisplay;

public class LifeSimulation {
    public static void main(String[] args) throws InterruptedException {
        Landscape landscape = new Landscape(100, 100, 0);

        Arrays.stream(Patterns.GosperGliderGun).forEach((Integer[] cellCords) -> landscape.setAlive(cellCords[0], cellCords[1]));

        LandscapeDisplay display = new LandscapeDisplay(landscape, 10);

        while (true) {
            Thread.sleep(10);
            landscape.advance();
            display.repaint();
           }
        } 
    }
