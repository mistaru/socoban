import javax.swing.*;
import java.io.IOException;
import java.awt.*;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Copyright (C) 2020 Intern Labs O!
 * <p>
 *
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
 * The Viewer class draws the program's graphics. The Viewer receives data and displays it on the screen.
 */
public class Viewer {
    private Canvas canvas;
    private JFrame frame;
    private Controller controller;
    private Color color;
    private JFileChooser fileChooser;


    /**
     * Viewer Constructor:
     * Initialized: a controller with a reference to this class
     * model instance from the controller
     * canvas instance with reference to the model
     * The rest is the program's graphical interface.
     */
    Viewer() throws IOException {
        controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model);
        color = new Color(84, 153, 75);

        frame = new JFrame("Sokoban Intern Labs");
        frame.setSize(600, 650);
        frame.setLocation(300, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add("Center", canvas);
        JMenuBar gameMenuBar = getMenuBar();
        frame.setJMenuBar(gameMenuBar);
        frame.setVisible(true);
        frame.addKeyListener(controller);
    }


    /**
     * Contains drop-down buttons
     * Each button contains a list of options
     * Select game levels both in-game and download from a file
     * Select the game level from a remote server
     * Ability to restart the game and exit the game
     */
    private JMenuBar getMenuBar() {

        JMenuItem restartGameItem = new JMenuItem("RESTART", new ImageIcon("images/restart.png"));
        restartGameItem.addActionListener(controller);
        restartGameItem.setBackground(color);
        restartGameItem.setActionCommand("restart game");

        JMenuItem quitItem = new JMenuItem("QUIT", new ImageIcon("images/quit.png"));
        quitItem.addActionListener(controller);
        quitItem.setActionCommand("quit");

        JMenu optionMenu = new JMenu("          OPTION          ");
        optionMenu.add(restartGameItem);
        optionMenu.add(quitItem);

        JMenuItem level1 = new JMenuItem("LEVEL ", new ImageIcon("images/level1.png"));
        level1.addActionListener(controller);
        level1.setActionCommand("level 1");

        JMenuItem level2 = new JMenuItem("LEVEL", new ImageIcon("images/level2.png"));
        level2.addActionListener(controller);
        level2.setActionCommand("level 2");

        JMenuItem level3 = new JMenuItem("LEVEL", new ImageIcon("images/level3.png"));
        level3.addActionListener(controller);
        level3.setActionCommand("level 3");

        JMenuItem level4 = new JMenuItem("LEVEL", new ImageIcon("images/level4.png"));
        level4.addActionListener(controller);
        level4.setActionCommand("level 4");

        JMenuItem addLevelFromFile = new JMenuItem("ADD NEW LEVEL", new ImageIcon("images/add.png"));
        addLevelFromFile.addActionListener(controller);
        addLevelFromFile.setActionCommand("add level");

        JMenu addLevelFromServer = new JMenu("ADD NEW LEVEL FROM SERVER");
        addLevelFromServer.addActionListener(controller);
        addLevelFromServer.setActionCommand("server");


        JMenuItem fifthLevel = new JMenuItem(" LEVELS 5");
        fifthLevel.addActionListener(controller);
        fifthLevel.setActionCommand("fifth");

        JMenuItem sixthLevel = new JMenuItem(" LEVELS 6");
        sixthLevel.addActionListener(controller);
        sixthLevel.setActionCommand("sixth");

        JMenuItem seventhLevel = new JMenuItem(" LEVELS 7");
        seventhLevel.addActionListener(controller);
        seventhLevel.setActionCommand("seventh");

        addLevelFromServer.add(fifthLevel);
        addLevelFromServer.add(sixthLevel);
        addLevelFromServer.add(seventhLevel);

        JMenu levelGame = new JMenu("            LEVEL           ");
        levelGame.add(level1);
        levelGame.add(level2);
        levelGame.add(level3);
        levelGame.add(level4);
        levelGame.add(addLevelFromFile);
        levelGame.add(addLevelFromServer);

        JMenuBar gameMenuBar = new JMenuBar();
        gameMenuBar.add(optionMenu);
        gameMenuBar.add(levelGame);
        gameMenuBar.setBackground(color);

        return gameMenuBar;

    }

    /**
     * Updates text
     */
    public void update() {
        canvas.repaint();
    }

    public JFrame getMyFrame() {
        return frame;
    }

    /**
     * Opens the file selection window
     * Ability to open a file from any location
     *
     * @return a String with the path to the file
     */
    public String openFileChooser() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        int returnVal = fileChooser.showOpenDialog(frame);
        File file = fileChooser.getSelectedFile();
        if (file == null) {
            return null;
        } else {
            return file.getPath();
        }
    }
}