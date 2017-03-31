package gfx;

import data.model.Barricade;
import data.model.Tile;
import data.model.Wall;
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
/*

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            int lineNumber = 0, previousLineNumber = 1;

            int i = 0;
            int j = 0;

            while ((fileLocation = br.readLine()) != null) {    //zolang er tekst staat op deze regel.

                String[] result = fileLocation.split(",");

                lineNumber++;

                for (int x = 0; x < result.length; x++) {
                    String huidig = result[x];

                    if (previousLineNumber != lineNumber) {
                        j++;
                        i = 0;
                        previousLineNumber++;
                    }

                    
                    mazeTiles[j][i] = new Tile(i,j);
                    i++;
                }
            }
 */
//            for(int a = 0; a<10;a++){       //printen om te testen of het goed gaat
//                for(int b = 0; b<10; b++){
//                    System.out.print(myArray[a][b] + " ");
//                }
//                System.out.println("");
//            }
//System.out.println(Arrays.deepToString(myArray));  //uitprinten om te testen of het goed gaat.
//catch troep voor als de file niet gevonden is.
/*
    catch (FileNotFoundException e

    
        ) {
            e.printStackTrace();
    }
    catch (IOException e

    
        ) {
            e.printStackTrace();
    }*/
