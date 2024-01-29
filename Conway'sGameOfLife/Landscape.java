/*
 * File:    Landscape.java
 * Name:    Duilio Lucio
 * Recent Modification:     02/26/2023
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Sudoku.Cell;

public class Landscape {

    private Cell[][] landscape; //The underlying grid of Cells for Conway's Game

    private double initialChance; //The original probability each individual Cell is alive

    public Landscape(int rows, int columns) { //Constructs a Landscape of the specified number of rows and columns.
        // @param rows    the number of rows in the Landscape
        // @param columns the number of columns in the Landscape
        landscape = new Cell[rows][columns]; 
        initialChance = 0; // All Cells are initially dead.
        reset();
    }

    // @param rows    the number of rows in the Landscape
    // @param columns the number of columns in the Landscape
    // @param chance  the probability each individual Cell is initially alive
    public Landscape(int rows, int columns, double chance) { //Constructs a Landscape of the specified number of rows and columns.
        this(rows, columns);
        initialChance = chance; // Each Cell is initially alive with probability specified by chance.
        reset();
    }

    public void reset() {
        for (int i = 0; i < getRows(); i++) { //recreates the grid via the same method as it was originally created.
            for (int x = 0; x < getCols(); x++) {
                if (ThreadLocalRandom.current().nextInt(0, 100) / 100. < initialChance) {
                    landscape[i][x] = new Cell(true);
                }
                else {
                    landscape[i][x] = new Cell(false);
                }
            }

        }
    }

    public int getRows() {
        return landscape.length; //Returns the number of rows in the Landscape
    }

    public int getCols() {
        return landscape[0].length; //Returns the number of columns in the Landscape.
    }

   
    // @param row the row of the desired Cell
    // @param col the column of the desired Cell
    // @return the Cell specified the given row and column
    public Cell getCell(int row, int col) {
        return landscape[row][col]; // returns a reference to the Cell located at position (row, col)
    }

    
    //Returns a String representation of the Landscape.
    public String toString() {
        String value = ""; //converts the Landscape into a text-based string representation.
        for (int i = 0; i < getRows(); i++) {
            for (int x = 0; x < getCols(); x++){
                value += landscape[i][x] + " ";
            }
            value += "\n"; //At the end of each row, put a carriage return ("\n").
        }
        return value;
    }

    //returns a list of references to the neighbors of the Cell at location (row, col). 
    //Pay attention to the boundaries of the Landscape when writing this function. 
    //The returned list should not contain the Cell at (row, col).
    public ArrayList<Cell> getNeighbors(int row, int col) {
        boolean getRight = true;
        boolean getLeft = true;
        boolean getTop = true;
        boolean getBottom = true;
        if (row == getRows() - 1) { // @param row the row of the specified Cell
            getRight = false;
        }
        else if(row == 0) {
            getLeft = false;
        }
        if (col == getCols() - 1) { // @param col the column of the specified Cell
            getBottom = false;
        }
        else if (col == 0) {
            getTop = false;
        }
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        if (getRight) neighbors.add(landscape[row + 1][col]);
        if (getLeft) neighbors.add(landscape[row - 1][col]);
        if (getTop) neighbors.add(landscape[row][col - 1]);
        if (getBottom) neighbors.add(landscape[row][col + 1]);
        if (getRight && getTop) neighbors.add(landscape[row + 1][col - 1]); // Top Right Cell
        if (getRight && getBottom) neighbors.add(landscape[row + 1][col + 1]); // Bottom Right Cell 
        if (getLeft && getTop) neighbors.add(landscape[row - 1][col - 1]); // Top Left Cell
        if (getLeft && getBottom) neighbors.add(landscape[row - 1][col + 1]); // Bottom Left Cell
        return neighbors;   //Returns an ArrayList of the neighboring Cells to the specified location.

    }

    //Advances the current Landscape by one step, While also returning population number(generation)
    public int advance() {
        Cell[][] newLandscape = new Cell[getRows()][getCols()];
        int aliveCells = 0;
        for (int i = 0; i < getRows(); i++){
            for (int x = 0; x < getCols(); x++) {
                newLandscape[i][x] = new Cell(landscape[i][x].getAlive());
                newLandscape[i][x].updateState(getNeighbors(i, x));
                if (newLandscape[i][x].getAlive()) {
                    aliveCells++;
                }
            }
        }
        landscape = newLandscape;
        return aliveCells;
    }

    public void setAlive(int row, int col) {    //set a cell alive interactively
        landscape[row][col].setAlive(true);
    }

    //@param g:   the Graphics object on which to draw
    //@param scale:     the scale of the representation of this Cell
    public void draw(Graphics g, int scale) { //Draws the Cell to the given Graphics object at the specified scale.
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.gray); //An alive Cell is drawn with a black color; a dead Cell is drawn gray.
                g.fillOval(x * scale, y * scale, scale, scale);
            }
        }
    }

    public static void main(String[] args) {
    }
}