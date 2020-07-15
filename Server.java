import java.io.*;
import java.net.Socket;

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


public class Server {
   
    public synchronized static int[][] getLevelFromServer(int level) {
        try {
            Socket echoSocket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;

            echoSocket = new Socket("157.245.219.46", 4469);

            outputStream = new ObjectOutputStream(new BufferedOutputStream(echoSocket.getOutputStream()));
            outputStream.writeInt(level);

            outputStream.flush();

            inputStream = new ObjectInputStream(new BufferedInputStream(echoSocket.getInputStream()));
            int[][] myMessageArray = (int[][]) inputStream.readObject();

            inputStream.close();
            outputStream.close();
            echoSocket.close();
            return myMessageArray;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
