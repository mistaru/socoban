import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


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
 * Represents the levels of the game
 */
public class Levels {

    private int level;
    public int reset;

    Levels() throws IOException {
        level = 1;
    }


    /**
     * First level
     */
    public int[][] firstLevel() {
        int[][] desktop = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 2, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 2, 0, 0, 0, 2, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        reset = 1;
        return desktop;
    }


    /**
     * Second level
     */
    public int[][] secondLevel() {
        int[][] desktop = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 3, 0, 0, 0, 2, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 3, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 3, 0, 0, 4, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 4, 0, 2},
                {2, 0, 2, 0, 0, 0, 0, 4, 0, 2},
                {2, 0, 3, 0, 0, 0, 0, 4, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        reset = 2;
        return desktop;
    }


    /**
     * Third level
     */
    public int[][] thirdLevel() {
        int[][] desktop = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 2, 2},
                {2, 0, 0, 3, 3, 4, 4, 0, 0, 2},
                {2, 0, 0, 1, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 3, 3, 4, 4, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 2, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        reset = 3;
        return desktop;
    }


    /**
     * Fourth level
     */
    public int[][] fourthLevel() {
        int[][] desktop = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 4, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 2, 0, 0, 2},
                {2, 0, 0, 3, 0, 4, 4, 0, 0, 2},
                {2, 0, 0, 1, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 4, 0, 0, 0, 2},
                {2, 0, 0, 3, 3, 4, 0, 0, 0, 2},
                {2, 0, 3, 0, 2, 0, 3, 0, 0, 2},
                {2, 0, 2, 0, 0, 0, 2, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        reset = 4;
        return desktop;
    }


    /**
     * After a player wins a level,
     * this method throws them to the next level
     */
    public int[][] nextLevel() {
        int[][] desktop = null;


        switch (level) {
            case 1:
                desktop = firstLevel();
                break;
            case 2:
                desktop = secondLevel();
                break;
            case 3:
                desktop = thirdLevel();
                break;
            default:
                desktop = fourthLevel();
                level = 0;
                break;
        }

        level++;

        return desktop;
    }


    /**
     * Reads a file from a folder and
     * moves it to a new level
     */
    public int[][] readLevel(String fileName) {
        String level = "";
        FileReader fr = null;
        if (fileName == null) {
            System.out.println(" file can not find");
            return nextLevel();
        } else {
            try {
                fr = new FileReader(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                level += scan.nextLine();
                level += "\n";
            }
            System.out.println(level);
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            char[] strToArray = level.toCharArray();
            int[][] desctop = new int[11][11];
            int j = 0, k = 0;
            if (strToArray[0] == '2' || strToArray[0] == '0' || strToArray[0] == '1' || strToArray[0] == '3' || strToArray[0] == '4') {
                desctop[j][k] = Character.getNumericValue(strToArray[0]);
                k++;
            }
            for (int i = 1; i < strToArray.length; i++) {
                if (strToArray[i] == '\n' && Character.isDigit(strToArray[i - 1])) {
                    j++;
                    k = 0;
                }
                if (strToArray[i] == '2' || strToArray[i] == '0' || strToArray[i] == '1' || strToArray[i] == '3' || strToArray[i] == '4') {
                    desctop[j][k] = Character.getNumericValue(strToArray[i]);
                    k++;
                }
            }
            return desctop;
        }
    }

    /**
     * The player can restart the level
     */
    public int[][] restartGame() {
        int[][] desktop = null;


        switch (reset) {
            case 1:
                desktop = firstLevel();
                break;
            case 2:
                desktop = secondLevel();
                break;
            case 3:
                desktop = thirdLevel();
                break;
            case 4:
                desktop = fourthLevel();
                break;
            default:
                desktop = desktop;
                break;
        }

        return desktop;
    }

    /**
     * Reads the server file and takes it to a new level
     */
    public int[][] readLevelFromServer(int level) {

        return Server.getLevelFromServer(level);
    }

}
