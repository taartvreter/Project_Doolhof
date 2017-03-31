package gfx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LevelGetter {

    static String xStrPath;
    static double[][] myArray;

    public static void laadLevelInArray() {  //laad een level in een 2d array.

        int cols = 10;
        int rows = 10;
        String[][] myArray = new String[cols][rows];

        //Aangeven waar de textfile zich bevind
        String fileLocation = "C:/Users/Jim/Documents/NetBeansProjects/HetGoedeProject/res/textures/test.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            int lineNumber = 0, previousLineNumber = 1;

            int i = 0;
            int j = 0;

            while ((fileLocation = br.readLine()) != null) {    //zolang er tekst staat op deze regel.

                String[] result = fileLocation.split(",");

//                if (previousLineNumber != lineNumber) {
//                    System.out.println("");
//                    previousLineNumber++;
//                }
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

            System.out.println(Arrays.deepToString(myArray));  //uitprinten om te testen of het goed gaat.
           
           
           
           
            //catch troep voor als de file niet gevonden is.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

   
