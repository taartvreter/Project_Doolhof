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

    private static final int WIDTH = 70, HEIGHT = 70;

    public static BufferedImage barricade, player, wall, endTile, tree, cube, key;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/sheet1.png"));

        barricade = sheet.crop(0, 0, WIDTH, HEIGHT);
        player = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        wall = sheet.crop(WIDTH * 2, 0, WIDTH, HEIGHT);
        endTile = sheet.crop(WIDTH * 3, 0, WIDTH, HEIGHT);
        key = sheet.crop(WIDTH * 5, 0, WIDTH, HEIGHT);
       
        tree = sheet.crop(0, HEIGHT, WIDTH, HEIGHT);
        cube = sheet.crop(WIDTH * 4, 0, WIDTH, HEIGHT);

    }
}
