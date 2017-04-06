package gfx;

import data.model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevelGetter {

    private final int cols = 10;
    private final int rows = 10;

    private Player newCharater = null;

    private final ArrayList<Tile> replacableTiles = new ArrayList<>();
    private final Tile[][] levelMap = new Tile[this.cols][this.rows];

    public Tile[][] loadMapToArray(int levelNumber) {
        this.resetReplaceableTiles();
        //Source: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
        String fileLocation = "./res/gameData/maps/maze" + levelNumber + ".csv";
        String line = "";
        String csvSplitBy = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            boolean hasEndTile = false;
            newCharater = null;
            while ((line = br.readLine()) != null) {

                String[] mazeObject = line.split(csvSplitBy);
                String gameObjectName = mazeObject[0].toLowerCase();
                int gameObjectLocationX = Integer.parseInt(mazeObject[1]);
                int gameObjectLocationY = Integer.parseInt(mazeObject[2]);
                int keyNumber = Integer.parseInt(mazeObject[3]);
                //System.out.println(keyNumber);
                //nt pincode = Integer.parseInt(mazeObject[3]);
                Tile tileWithObject = new Tile(gameObjectLocationX, gameObjectLocationY);
                switch (gameObjectName) {
                    case "player":
                        if (newCharater == null) {
                            this.newCharater = new Player(gameObjectLocationX, gameObjectLocationY);
                        }
                        break;
                    case "barricade":
                        tileWithObject.setStandingObject(new Barricade(keyNumber));
                        break;
                    case "wall":
                        tileWithObject.setStandingObject(new Wall());
                        break;
                    case "key":
                        tileWithObject.setStandingObject(new Key(keyNumber));
                        break;
                    case "endtile":
                        if (!hasEndTile) {
                            tileWithObject = new EndTile(gameObjectLocationX, gameObjectLocationY);
                            hasEndTile = true;
                        }
                        break;
                }
                this.replacableTiles.add(tileWithObject);
                //System.out.println("name object:" + mazeObject[0] + " postitionX:" + mazeObject[1] + " postitionY:" + mazeObject[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.resetMapTiles();
        this.placeMapObjects();

        return this.levelMap;
    }

    private void resetMapTiles() {
        for (int i = 0; i < this.levelMap.length; i++) {
            for (int j = 0; j < this.levelMap[i].length; j++) {
                levelMap[j][i] = new Tile(j, i);
            }
        }
    }

    private void placeMapObjects() {
        for (Tile GameElementTile : this.replacableTiles) {
            this.levelMap[GameElementTile.getLocationY() - 1][GameElementTile.getLocationX() - 1] = GameElementTile;
        }

    }

//    private void filterNonUsableGameElements() {
//
//    }

    private void resetReplaceableTiles() {
        this.replacableTiles.clear();
    }

    public Player loadPlayer() {
        Player returningPlayer;
        if (this.newCharater != null) {
            returningPlayer = this.newCharater;
        } else {
            returningPlayer = new Player(1, 1);
        }
        return returningPlayer;
    }

    public int getNumberOfLevels() {
        int numberOfLevels = 0;
        try {
            numberOfLevels = (int) Files.list(Paths.get("./res/gameData/maps/")).count();
        } catch (IOException ex) {
            Logger.getLogger(LevelGetter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberOfLevels;
    }
}
