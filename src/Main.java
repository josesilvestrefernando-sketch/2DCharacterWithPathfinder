import pathfinder.Gridmap;
import processing.core.*;
import rpg.Direction;
import rpg.TownMap;


public class Main extends PApplet {

    TownMap townMap;
    rpg.Character character;
    int cellSize = 40;
    int cols, rows;

    public void setup() {
        surface.setTitle("2D RPG Game");

        cols = width / cellSize;
        rows = height / cellSize;

        townMap = new TownMap(this, cols, rows, cellSize);
        character = new rpg.Character(this, townMap, 0, 1);
        frameRate(1);
    }

    public void draw() {
        // Draw Grid and Obstacles
        townMap.draw();
        character.draw();
        character.moveToThePath();

    }

    public void mousePressed() {
        character.mousePressed(mouseX,mouseY);
    }

    public void keyPressed() {
         if (key == 'a' || key == 'A') {
            character.move(Direction.LEFT);
        } else if (key == 'd' || key == 'D') {
            character.move(Direction.RIGHT);
        } else if (key == 's' || key == 'S') {
            character.move(Direction.DOWN);
        } else if (key == 'w' || key == 'W') {
            character.move(Direction.UP);
        }
    }

    public void settings() {
        size(800, 600);
    }

    static public void main(String[] passedArgs) {

        PApplet.main("Main");

    }
}
