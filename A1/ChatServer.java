package A1;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import A1.ServerThread;

public class ChatServer{

    public static void main(String[] args) throws Exception{
        ServerSocket ss;
        Socket s;
        try {
            // 1. create a server socket
            ss = new ServerSocket(8083);
            // 2. new thread for a client
            while(true){
                System.out.println("[SERVER] waiting for connection ...");
                s = ss.accept();
                System.out.println("[SERVER] Connection received from " + s.getInetAddress().getHostName() + ":" + s.getPort());
                new ServerThread(s).start();
            }

        } catch (IOException e) {
            System.err.println("[SERVER] IOError: " + e);
        }

    }
}
