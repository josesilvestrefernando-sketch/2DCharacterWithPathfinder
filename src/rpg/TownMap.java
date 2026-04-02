package rpg;

import pathfinder.Gridmap;
import processing.core.PApplet;

public class TownMap extends Gridmap {
    public TownMap(PApplet pApplet1, int w1, int h1, int cellsize1) {
        super(pApplet1, w1, h1, cellsize1);
    }
    public void draw() {
        pApplet.background(0);
        // draw walkable / blocked cells
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                 if (grid[y][x].walkable==false) {
                    pApplet.fill(50);
                } else {
                    pApplet.fill(255);
                }

                pApplet.rect(x * cellsize, y * cellsize, cellsize, cellsize);
            }
        }
    }
}
