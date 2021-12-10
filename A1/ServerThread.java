package A1;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    Socket s;
    BufferedReader reader = null;
    PrintWriter writer=null;
    String text="";

    public ServerThread(Socket clientSocket){
        this.s = clientSocket;
    }

    public void run(){
        try {
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            writer = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            System.err.println("[SERVER THREAD] IOError:" + e);
        }

        try {
            while((text = reader.readLine()) != null){
                writer.println(text);
                writer.flush();
                System.out.println("[SERVER THREAD]: received "+text);
            }

            s.close();
            return;

        } catch (IOException e) {
            System.err.println("[SERVER THREAD] IOError:" + e);
            return ;
        } 
    }
}
