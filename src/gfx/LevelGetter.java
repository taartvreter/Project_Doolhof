package gfx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LevelGetter {

    static String xStrPath;
    static double[][] myArray;

    public static String[][] laadLevelInArray() {  //laad een level in een 2d array.

        int cols = 10;
        int rows = 10;
        String[][] myArray = new String[cols][rows];

        //Aangeven waar de textfile zich bevind
        String fileLocation = "./res/textures/test.txt";

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

                    myArray[j][i] = huidig;
                    i++;
                }
            }
            
//            for(int a = 0; a<10;a++){       //printen om te testen of het goed gaat
//                for(int b = 0; b<10; b++){
//                    System.out.print(myArray[a][b] + " ");
//                }
//                System.out.println("");
//            }

            //System.out.println(Arrays.deepToString(myArray));  //uitprinten om te testen of het goed gaat.
           
           
           
           
            //catch troep voor als de file niet gevonden is.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myArray;
    }
    
}

   
