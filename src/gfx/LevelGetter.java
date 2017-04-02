package gfx;

import data.model.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelGetter {

    private int cols = 5;
    private int rows = 5;

    private final ArrayList<Tile> mazeTiles = new ArrayList<>();

    public Tile[] loadMapToArray() {
        this.resetAllTiles();
        //Source: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
        String fileLocation = "./res/textures/maze1.csv";
        String line = "";
        String csvSplitBy = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            boolean hasEndTile = false;
            while ((line = br.readLine()) != null) {

                String[] mazeObject = line.split(csvSplitBy);
                String gameObjectName = mazeObject[0];
                int gameObjectLocationX = Integer.parseInt(mazeObject[1]);
                int gameObjectLocationY = Integer.parseInt(mazeObject[2]);
                Tile tileWithObject = new Tile(gameObjectLocationY, gameObjectLocationY);
                switch (gameObjectName) {
                    case "barricade":
                        tileWithObject.setStandingObject(new Barricade(1));
                        this.mazeTiles.add(tileWithObject);
                        break;
                    case "wall":
                        tileWithObject.setStandingObject(new Wall());
                        this.mazeTiles.add(tileWithObject);
                        break;
                    case "key":
                        tileWithObject.setStandingObject(new Key());
                        break;
                    case "endTile":
                        if (!hasEndTile) {
                            tileWithObject = new EndTile(gameObjectLocationX, gameObjectLocationY);
                        }
                        break;
                }
                System.out.println("name object:" + mazeObject[0] + " postitionX:" + mazeObject[1] + " postitionY:" + mazeObject[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.mazeTiles.toArray(new Tile[0]);
    }

    private void resetAllTiles() {
        this.mazeTiles.clear();

    }
}
