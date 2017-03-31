/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.image.BufferedImage;


/**
 *
 * @author Frenky
 */
public class Assets {
    
    private static final int width = 70, height = 70;
    
    public static BufferedImage barricade, player, wall, endTile, tree;
    
    public static void init(){
        SpriteSheet sheet  = new SpriteSheet(ImageLoader.LoadImage("/textures/sheet1.png"));

        
        barricade = sheet.crop(0, 0, width, height);
        player = sheet.crop(width, 0 , width, height);
        wall = sheet.crop(width * 2, 0, width, height);
        endTile =  sheet.crop(width * 3, 0, width, height);
        tree =  sheet.crop(0, height, width, height);
        
    }
}
