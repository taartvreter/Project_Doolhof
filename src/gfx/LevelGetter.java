package gfx;

import data.model.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LevelGetter {

    private int cols = 5;
    private int rows = 5;

    private Tile[][] mazeTiles = new Tile[cols][rows];

    public Tile[][] loadMapToArray() {
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
                switch (gameObjectName) {
                    case "barricade":
                        this.mazeTiles[gameObjectLocationX - 1][gameObjectLocationY - 1].setStandingObject(new Barricade());
                        break;
                    case "wall":
                        this.mazeTiles[gameObjectLocationX - 1][gameObjectLocationY - 1].setStandingObject(new Wall());
                        break;
                    case "key":
                        this.mazeTiles[gameObjectLocationX - 1][gameObjectLocationY - 1].setStandingObject(new Key());
                        break;
                    case "endTile":
                        if (!hasEndTile) {
                            this.mazeTiles[gameObjectLocationX - 1][gameObjectLocationY - 1] = new EndTile(gameObjectLocationX, gameObjectLocationY);
                        }
                        break;
                }
                System.out.println(mazeObject[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mazeTiles;
    }

    private void resetAllTiles() {
        for (int colom = 0; colom < this.cols; colom++) {
            for (int row = 0; row < this.rows; row++) {
                this.mazeTiles[colom][row] = new Tile(row + 1, colom + 1);
            }
        }
    }
}