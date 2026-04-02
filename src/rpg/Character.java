package rpg;

import pathfinder.Gridmap;
import pathfinder.Pathfinder;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Character {
    PApplet pApplet;
    private int x, y;
    Gridmap gridmap;
    PVector targetpos;
    ArrayList<PVector> path = new ArrayList<>();

    public Character(PApplet pApplet1, Gridmap gridmap1, int x1, int y1) {
        this.x = x1;
        this.y = y1;
        pApplet = pApplet1;
        gridmap = gridmap1;
    }

    public void draw() {
        pApplet.fill(100, 20, 210,100);
        for (PVector p : path) {
            pApplet.rect(p.x * gridmap.cellsize ,
                    p.y * gridmap.cellsize ,
                    gridmap.cellsize , gridmap.cellsize);
        }
        //Optional: Draw a small bounding box for debugging
        pApplet.fill(255, 0, 0); // Red color
        pApplet.ellipse(x * gridmap.cellsize + gridmap.cellsize / 2,
                y * gridmap.cellsize + gridmap.cellsize / 2, gridmap.cellsize, gridmap.cellsize);


    }

    public void mousePressed(int mouseX, int mouseY) {
        int x1 = mouseX;
        int y1 = mouseY;

        // Determine which cell was selected
        int selectedCellX = pApplet.floor(x1 / gridmap.cellsize);
        int selectedCellY = pApplet.floor(y1 / gridmap.cellsize);

        // Check if the selected cell is within the gridmap bounds
        if (selectedCellX >= 0 && selectedCellX < gridmap.w && selectedCellY >= 0 && selectedCellY < gridmap.h) {


            targetpos = new PVector(selectedCellX, selectedCellY);
            //x = (int) targetpos.x;
            // y = (int) targetpos.y;
            gridmap.clearVisit();
            Pathfinder pathfinder = new Pathfinder(pApplet, new PVector(x, y), targetpos, gridmap);
            pathfinder.search();

            path = new ArrayList<>(pathfinder.path);
            path.add(targetpos);


        }
    }

    public void moveToThePath() {
        if (path.size() > 0) {
            PVector nextpos = path.get(0);
            x = (int) nextpos.x;
            y = (int) nextpos.y;
            path.remove(0);
        }
    }

    // Method to move the character to a new location
    public void move(Direction direction) {

        int newX, newY;
        if (direction == Direction.LEFT) {
            // Left
            newX = x - 1;
            if (checkIfCellValid(newX, y)) {
                x = newX;
            }
        } else if (direction == Direction.RIGHT) {
            // Right
            newX = x + 1;
            if (checkIfCellValid(newX, y)) {
                x = newX;
            }
        } else if (direction == Direction.UP) {
            // Up
            newY = y - 1;
            if (checkIfCellValid(x, newY)) {
                y = newY;
            }
        } else if (direction == Direction.DOWN) {
            // Down
            newY = y + 1;
            if (checkIfCellValid(x, newY)) {
                y = newY;
            }
        }
    }

    // Helper method to check if a cell is valid
    private boolean checkIfCellValid(int cellX, int cellY) {
        // Check if the cell is at the edge of the grid
        if (cellX < 0 || cellX >= gridmap.w || cellY < 0 || cellY >= gridmap.h) {
            return false;
        }
        return true;
    }
}
