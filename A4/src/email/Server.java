package email;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server implements Runnable {

    Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    private volatile Thread blinker;

    public void start() {
        blinker = new Thread(this);
        blinker.start();
    }

    public void stop() {
        blinker = null;
    }

    public void run() {
        try {
            Thread thisThread = Thread.currentThread();

            //output the string to the server
            InputStreamReader inputStreamReader = new InputStreamReader( socket.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while (blinker == thisThread) {
                String text = bufferedReader.readLine();
                if (text != null) {
                    System.out.println("SERVER: " + text);
                    thisThread.sleep(100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}