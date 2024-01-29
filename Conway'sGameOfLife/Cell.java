/*
 * File:    Cell.java
 * Author:  Duilio Lucio
 * Recent Modification: 02/26/2023
 */
import java.util.ArrayList;

import Sudoku.Cell;

public class Cell {

    private boolean alive; // The status of the Cell.

    public Cell() { 
        alive = false;  //constructor method. (By default, the Cell is dead.)
                        // Constructs a dead cell.
    }

    public Cell(boolean status) {
        alive = status; 
                        //constructor method that specifies the Cell's state. 
                        //A True argument means the Cell is alive, a False argument means 
                        //the Cell is dead.
    }

    public boolean getAlive() {
        return alive; //returns whether the Cell is alive.
    }

    public void setAlive(boolean status) {
        alive = status; //Sets the current status of the cell to the specified status.
                        //@param status a boolean to specify if the Cell is alive or dead
    }   

    public void updateState( ArrayList<Cell> neighbors ) {  //Updates the state of the Cell.
        //@param neighbors An ArrayList of Cells
        int aliveNeighbors = neighbors.stream().map((x) -> x.getAlive() ? 1 : 0).reduce(0, (a, b) -> a + b);
        if (aliveNeighbors == 3 && !getAlive()) {   //If this Cell is dead and there are 3 alive neighbors,
            setAlive(true);     //this Cell comes back to life.
        } 
        else if (aliveNeighbors != 2 && aliveNeighbors != 3 && getAlive()) {  //If this Cell is dead and there are 3 alive neighbors,
            setAlive(false);    //this Cell comes back to life. Otherwise, it stays dead.
        }
    }

    public String toString() {  //Returns a String representation of this Cell.
        return alive ? "1" : "0";   //@return 1 if this Cell is alive, otherwise 0.
    }
}