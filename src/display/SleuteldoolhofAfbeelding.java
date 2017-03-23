package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.javafx.iio.ImageLoader;
import static game.SleuteldoolhofAfbeelding.LoadImage;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author hwpva
 */
public class SleuteldoolhofAfbeelding extends JComponent {

    private final Color bgColor;
    private BufferedImage test;

    public SleuteldoolhofAfbeelding() {
        this.bgColor = java.awt.Color.GRAY;
        this.initComponent();
    }

    private void initComponent() {
        this.setPreferredSize(new Dimension(1000, 1000));
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.bgColor);
        g.fillRect(0, 0, this.getHeight(), this.getWidth());
        g.setColor(Color.BLACK);
        for (int widthX = this.getWidth(); widthX > 0; widthX -= 100) {
            g.drawLine(widthX, 0, widthX, this.getHeight());
            g.drawLine(0, widthX, this.getWidth(), widthX);
        }
        g.setColor(Color.GREEN);            //eindvak
        g.fillRect(901, 901, 100, 100);

        int mBreedte = 10;
        int mLengte = 10;
        int aantalMuren = 15;       //er wordenn 15 muren geplaatst, let op: muren kunnen zichzelf op zichzelf plaatsen.
        int aantalBarricades = 40;  //er worden 30 muren geplaatst, let op: barricades kunnen barricades op zichzelf plaatsen. Barricades kunnen niet op reeds getekende muren geplaats worden

        int[][] m = new int[mLengte][mBreedte];

        Random randomGenerator = new Random();
        for (int idx = 0; idx <= (aantalMuren-1); ++idx) { //aantalMuren bepaalt hoevaak een randomInt wordt aangemaakt
            int xMuur = 0;    //x en y coordinaat weer op 0 zetten.
            int yMuur = 0;

            int randomInt = randomGenerator.nextInt(1000);  //genereer random getal tot 1000
            int randomInt2 = randomGenerator.nextInt(1000);
            randomInt = randomInt / 100 * 100;  //zorgen dat het een macht van 100 is
            randomInt2 = randomInt2 / 100 * 100;
            xMuur += randomInt + 1;
            yMuur += randomInt2 + 1;

            if ((!(xMuur == 901 && yMuur == 901)) && (!(xMuur == 1 && yMuur == 1))) {       //deze if statement zorgt ervoor dat er nooit een muur op het eindvlak/beginvlak kan komen
                g.setColor(Color.WHITE);
                g.fillRect(xMuur, yMuur, 99, 99);        //printen op bord
                g.setColor(Color.darkGray);
                g.fillRect(xMuur + 10, yMuur + 10, 79, 79);

                //opslaan in array
                xMuur = (xMuur - 1) / 100;
                yMuur = (yMuur - 1) / 100;
                m[yMuur][xMuur] = 1; //hierbij staat het getal 1 voor een muur en het getal 0 voor nxMuur
            }
        }

        for (int idx = 0; idx <= (aantalBarricades-1); ++idx) { //30 keer een randomInt genereren
            @SuppressWarnings("UnusedAssignment")
            int xBarricade = 0;    //x en y coordinaat weer op 0 zetten.
            @SuppressWarnings("UnusedAssignment")
            int yBarricade = 0;

            this.test = LoadImage("/textures/sheet.png");
           
            
            int randomInt = randomGenerator.nextInt(1000);  //genereer random getal tot 1000
            int randomInt2 = randomGenerator.nextInt(1000);
            randomInt = randomInt / 100;  //zorgen dat het een macht van 100 is
            randomInt2 = randomInt2 / 100;

            xBarricade = randomInt;
            yBarricade = randomInt2;

            //System.out.print(xBarricade + " " + yBarricade);
            if (m[yBarricade][xBarricade] == 0) {
                xBarricade = (xBarricade * 100) + 1;
                yBarricade = (yBarricade * 100) + 1;
                if ((!(xBarricade == 901 && yBarricade == 901)) && (!(xBarricade == 1 && yBarricade == 1))) {
                    g.setColor(Color.YELLOW);
                    //g.fillRect(xBarricade, yBarricade, 99, 99);        //printen op bord
                     g.drawImage(test, xBarricade, yBarricade, this);

                    //opslaan in array
                    xBarricade = (xBarricade - 1) / 100;
                    yBarricade = (yBarricade - 1) / 100;
                    m[yBarricade][xBarricade] = 100; //hierbij staat het getal 1 voor een muur en het getal 0 voor leeg en het getal 2 voor een barricade
                }
            }
        }
            for (int i = 0; i < mLengte; i++) {      //printen gehele array
                for (int j = 0; j < mBreedte; j++) {
                    System.out.print(m[i][j] + "  ");
                }
                System.out.println("");
            }
            
            
            //uitleg array:
            //0 is een leeg vlak
            //1 is een muur
            //2 is een barricade
            //

        

    }
    
  
    
    public static BufferedImage LoadImage(String path){
        try {
            return ImageIO.read(SleuteldoolhofAfbeelding.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /*
    private void laadSleuteldoolhof(){
        
    }
     */
    private void maakDoolhofRaster() {
    }
}
