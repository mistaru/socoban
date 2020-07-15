import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.File;
import java.awt.Image;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

/**
 * Copyright (C) 2020 Intern Labs O!
 * <p>
 * <p>
 * Sokoban is a logical puzzle game in which the player moves boxes
 * through a maze shown as a plan in order to put all the boxes
 * in the specified final positions. Only one box can be moved at a time,
 * and the hero of the game — the "storekeeper" — can only push the boxes,
 * but not pull them. Since the game is quite difficult to recreate physically,
 * it is usually implemented as a computer game.
 *
 * @author Argen Kasymov
 */


/**
 * All the game animations happen here
 */
public class Canvas extends JPanel {
    private Model model;
    private Image imageGamer;
    private Image imageWall;
    private Image imageBox;
    private Image imageGoal;
    private Image backGround;


    Canvas(Model model) {
        Color color = new Color(122, 82, 26);
        this.model = model;
        setOpaque(true);
        setBackground(color);

        File fileNameGamer = new File("images/gamer.png");
        File fileNameWall = new File("images/wall.png");
        File fileNameBox = new File("images/box.png");
        File fileNameGoal = new File("images/cross.png");
        File fileNameBack = new File("images/back.png");

        try {
            imageGamer = ImageIO.read(fileNameGamer);
            imageWall = ImageIO.read(fileNameWall);
            imageBox = ImageIO.read(fileNameBox);
            imageGoal = ImageIO.read(fileNameGoal);
            backGround = ImageIO.read(fileNameBack);
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe);
        }
    }


    public void paint(Graphics g) {
        super.paint(g);
        printMyCanvas(g);

    }


    /**
     * Use a two-dimensional array from the model to represent an animation
     * Draw things with the graphics
     * 1-player
     * 2-wall
     * 3-box
     * 4-Goal
     *
     */
    public void printMyCanvas(Graphics g) {
        int x = 50;
        int y = 50;
        int width = 50;
        int height = 50;
        int offset = 0;

        int[][] desktop = model.getDesktop();

        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {

                if (desktop[i][j] == 1) {
                    g.drawImage(imageGamer, x, y, null);
                } else if (desktop[i][j] == 2) {
                    g.drawImage(imageWall, x, y, null);

                } else if (desktop[i][j] == 3) {
                    g.drawImage(imageBox, x, y, null);

                } else if (desktop[i][j] == 4) {
                    g.drawImage(imageGoal, x, y, null);

                } else {
                    g.drawImage(backGround, x, y, null);
                }
                x = x + width + offset;
            }
            x = 50;
            y = y + height + offset;
        }
    }

}