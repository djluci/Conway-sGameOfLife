/*
file name:      LandscapeTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  9/18/2022

How to run:     java -ea LandscapeTests
*/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Sudoku.Cell;

public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int)
        {
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10);

            // verify
            System.out.println(l1);
            System.out.println("\n");
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int)";
            assert l2 != null : "Error in Landscape::Landscape(int, int)";
        }

        // case 2: testing reset()
        {
            // set up
            Landscape landscape = new Landscape(2, 4, 0);
            for (int i = 0; i < landscape.getRows(); i++) {
                for (int x = 0; x < landscape.getCols(); x++){
                    landscape.setAlive(i, x);
                }
            }
            landscape.reset();

            int aliveCells = 0;
            for (int i = 0; i < landscape.getRows(); i++) {
                for (int x = 0; x < landscape.getCols(); x++) {
                    aliveCells += landscape.getCell(i, x).getAlive() ? 1 : 0;
                }
            }


            // verify
            System.out.println("0 == " + aliveCells);

            // test
            assert 0 == aliveCells : "Error in landscape:reset()";
        }

        // case 3: testing getRows()
        {
            // set up
            Landscape landscape = new Landscape(2, 4, 0);
            int rows = landscape.getRows();

            // verify
            System.out.println("2 == " + rows);

            // test
            assert 2 == rows : "Errors in landscape:getRows()";
        }

        // case 4: testing getCols()
        {
            // set up
            Landscape landscape = new Landscape(2, 4, 0);
            int cols = landscape.getCols();

            // verify
            System.out.println("4 == " + cols);

            // test
            assert 4 == cols : "Errors in landscape:getColes()";
        }

        // case 5: testing getCell(int, int)
        {
            // set up  
            Landscape landscape = new Landscape(2, 4, 0);
            landscape.setAlive(0,0);
            boolean cellValue = landscape.getCell(0, 0).getAlive();

            // verify
            System.out.println("true == " + cellValue);

            // test
            assert true == cellValue : "Error in landscape:getCell()";
        }

        // case 6: testing getNeighbors()
        {
            // set up  
            Landscape landscape = new Landscape(2, 4, 0);
            ArrayList<Cell> neighbors = landscape.getNeighbors(0, 0);

            // verify
            System.out.println("3 == " + neighbors.size());

            // test
            assert 3 == neighbors.size() : "Error in landscape:getNeighbors()";
        }

        // case 7: testing advance()
        {
            // set up
            Landscape landscape = new Landscape(10, 10, 0);
            Arrays.stream(Patterns.Blinker).forEach((Integer[] cellCords) -> landscape.setAlive(cellCords[0], cellCords[1]));
            landscape.advance();
            boolean AliveCell = landscape.getCell(2, 2).getAlive();

            // verify
            System.out.println("true == " + AliveCell);

            // test
            assert true == AliveCell: "Error in landscape:getCell()";
        }

    }


    public static void main(String[] args) {

        landscapeTests();
    }
}