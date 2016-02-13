package ihs.snake;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Chen on 2/13/2016.
 */
public class GameManager {

    final Object boardLock = new Object();
    int[][] board;
    int xHead;
    int yHead;

    InputStream in;
    OutputStream out;


    public GameManager(int x, int y, int xStart, int yStart, InputStream in, OutputStream out) {
        board = new int[x][y];
        xHead = xStart;
        yHead = yStart;
        this.in = in;
        this.out = out;

        byte[] test = {1, 1, 1, 1};
        Log.d("Testing", convertToInt(convertToByteArray(2034), 0) + "");
    }

    private class GetBoard implements Runnable {

        @Override
        public void run() {
            byte[] buffer = new byte[board.length * board[0].length * 4];
            try {
                while (in.read(buffer) != -1) {
                    synchronized (boardLock){
                        for(int i = 0; i < board.length; i++){
                            for(int j = 0; j < board[0].length; j++){
                                board[i][j] = convertToInt(buffer, i * board[0].length + j);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Start is inclusive
     *
     * @param a
     * @param start
     * @return
     */
    private static int convertToInt(byte[] a, int start) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value += ((a[i] & 0x00000FF) << (i * 8));
        }
        return value;
    }

    private static byte[] convertToByteArray(int x) {
        byte[] ret = new byte[4];
        ret[0] = (byte) (x & 0xFF);
        ret[1] = (byte) ((x >> 8) & 0xFF);
        ret[2] = (byte) ((x >> 16) & 0xFF);
        ret[3] = (byte) ((x >> 24) & 0xFF);

        return ret;
    }


}
