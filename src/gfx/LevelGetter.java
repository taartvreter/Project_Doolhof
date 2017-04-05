package gfx;

import data.model.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelGetter {

    private int cols = 10;
    private int rows = 10;

    private Player newCharater = null;

    private final ArrayList<Tile> mazeTiles = new ArrayList<>();

    public Tile[][] loadMapToArray() {
        this.resetAllTiles();
        //Source: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
        String fileLocation = "./res/gameData/maps/easy/maze1.csv";
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
                            //tileWithObject.setCharacter(new Player());
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
                this.mazeTiles.add(tileWithObject);
                //System.out.println("name object:" + mazeObject[0] + " postitionX:" + mazeObject[1] + " postitionY:" + mazeObject[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tile[][] newMazeMap = new Tile[cols][rows];
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < 10; j++) {
                newMazeMap[j][i] = new Tile(j, i);
            }
        }

        for (Tile GameElementTile : this.mazeTiles) {
            newMazeMap[GameElementTile.getLocationY() - 1][GameElementTile.getLocationX() - 1] = GameElementTile;
        }

        for (int i = 0; i < 10; i++) {
            //System.out.println(Arrays.toString(newMazeMap[i]));
        }

        return newMazeMap;
    }

    private void filterNonUsableGameElements() {

    }

    private void resetAllTiles() {
        this.mazeTiles.clear();
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
}
