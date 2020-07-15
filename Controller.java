import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * Controller
 * The controller accepts data and translates it into commands for the model or View.
 * Implements the ActionListener interface for listening to actions from the viewer
 * Implements the KeyListener interface for listening to Key events from the keyboard
 */
public class Controller implements KeyListener, ActionListener {

    private Model model;

    Controller(Viewer viewer) throws IOException {
        model = new Model(viewer);
    }

    public Model getModel() {
        return model;
    }

    public void keyTyped(KeyEvent event) {
    }

    /**
     * In this program we use only keyPressed method, which listens to every key pressed event
     * Then, depending on keyCode controller sends commands to model
     *
     */
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        String direction = null;

        switch (keyCode) {
            case 37:
                direction = "Left";
                break;
            case 38:
                direction = "Up";
                break;
            case 39:
                direction = "Right";
                break;
            case 40:
                direction = "Down";
                break;
            default:
                return;
        }
        model.move(direction);
    }

    /**
     * actionPerformed
     * Listens for actions and sends commands to the model
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        model.menuAction(command);
    }

    public void keyReleased(KeyEvent event) {
    }

}