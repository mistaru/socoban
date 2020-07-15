import java.io.IOException;

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
 * The Model class manages data and program logic.
 * In the class, the Model is initialized by the Viewer to send data directly to the Viewer
 * Allows you to load new levels
 */
public class Model {
    private Viewer viewer;
    private int[][] desktop;
    private Levels levels;
    private int indexX;
    private int indexY;
    private int[][] arrayIndexes;


    Model(Viewer viewer) throws IOException {
        this.viewer = viewer;

        levels = new Levels();
        desktop = levels.nextLevel();

        indexX = 4;
        indexY = 3;

        initialization();

    }


    public void initialization() {
        int counterFour = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 4) {
                    counterFour = counterFour + 1;
                }
            }
        }

        arrayIndexes = new int[2][counterFour];

        int count = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 4) {
                    arrayIndexes[0][count] = i;
                    arrayIndexes[1][count] = j;
                    count++;
                }
            }
        }

        for (int i = 0; i < arrayIndexes.length; i++) {
            for (int j = 0; j < arrayIndexes[i].length; j++) {
                System.out.print(arrayIndexes[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    /**
    * returns a map of the game
    */
    public int[][] getDesktop() {
        return desktop;
    }

    /**
     * This function listens for driving directions from the controller
     * Then updates the viewer and checks if the player has won
     *
     */
    public void move(String direction) {
        System.out.println(direction);

        switch (direction) {
            case "Left":
                moveLeft();
                break;
            case "Up":
                moveUp();
                break;

            case "Right":
                moveRight();
                break;

            case "Down":
                moveDown();
                break;

            default:
                return;

        }
        print();
        check();

        viewer.update();
        won();
    }

    /**
     * Step to the left
     */
    private void moveLeft() {

        if (desktop[indexX][indexY - 1] == 3) {
            if (desktop[indexX][indexY - 2] == 0 || desktop[indexX][indexY - 2] == 4) {
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }


        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * Step to the up
     */
    private void moveUp() {

        if (desktop[indexX - 1][indexY] == 3) {
            if (desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4) {
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }


        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * Step to the right
     */
    private void moveRight() {

        if (desktop[indexX][indexY + 1] == 3) {
            if (desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4) {
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }

        if (desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * Step to the down
     */
    private void moveDown() {

        if (desktop[indexX + 1][indexY] == 3) {
            if (desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4) {
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }


        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * Checks whether the player has won
     */
    private void check() {
        int t = 0;
        for (int j = 0; j < arrayIndexes[0].length; j++) {
            int x = arrayIndexes[0][t];
            int y = arrayIndexes[1][t];
            if (desktop[x][y] == 0) {
                desktop[x][y] = 4;
                break;
            }
            t++;
        }


    }

    /**
     * Checks whether the player has won
     * If you win, it takes the player to the next level
     */
    private void won() {
        boolean flag = true;
        int t = 0;
        for (int i = 0; i < arrayIndexes[0].length; i++) {
            int x = arrayIndexes[0][t];
            int y = arrayIndexes[1][t];
            if (desktop[x][y] != 3) {
                flag = false;
                break;
            }
            t++;
        }
        if (flag) {
            javax.swing.JOptionPane.showMessageDialog(viewer.getMyFrame(), "You won!");
            desktop = levels.nextLevel();
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();
        }

    }

    private void print() {
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                System.out.print(desktop[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }


    /**
     * doAction
     * Basic logic of the program
     * The controller sends a command,
     * the model examines it, and when detected,
     * performs this action,
     * after which the break statement is called
     * @param choice stores the command name
     */
    public void menuAction(String choice) {
        if (choice.equals("restart game")) {
            desktop = null;
            desktop = levels.restartGame();
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();

        } else if (choice.equals("level 1")) {
            desktop = null;
            desktop = levels.firstLevel();
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();
        } else if (choice.equals("level 2")) {
            desktop = null;
            desktop = levels.secondLevel();
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();

        } else if (choice.equals("level 3")) {
            desktop = null;
            desktop = levels.thirdLevel();
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();


        } else if (choice.equals("level 4")) {
            desktop = null;
            desktop = levels.fourthLevel();
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();
        } else if (choice.equals("add level")) {
            //desktop = null;
            desktop = levels.readLevel(viewer.openFileChooser());
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();
        } else if (choice.equals("fifth")) {
            desktop = null;
            desktop = levels.readLevelFromServer(5);
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();
        }
        else if (choice.equals("sixth")) {
            desktop = null;
            desktop = levels.readLevelFromServer(6);
            indexX = 4;
            indexY = 3;
            initialization();
            viewer.update();
    }
    else if (choice.equals("seventh")) {
        desktop = null;
        desktop = levels.readLevelFromServer(7);
        indexX = 4;
        indexY = 3;
        initialization();
        viewer.update();
        }

        else if (choice.equals("quit")) {
            exit();
        }
    }


    /**
     * Exit the program
     */
    private void exit() {
        System.exit(0);
    }


}