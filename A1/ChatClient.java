package A1;

import java.io.*;
import java.net.Socket;

public class ChatClient{
    public static void main(String[] args) throws Exception{
        Socket s = new Socket("localhost",8083);

        PrintStream writer = new PrintStream(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text;
        System.out.print("[CLIENT]: Enter text to send: ");
        while( (text = reader.readLine()) != null){
            if(text.compareTo("QUIT") == 0){
                reader.close();
                writer.close();
                s.close();
            }
            writer.println(text);
            writer.flush();
            System.out.print("[CLIENT]: Enter text to send: ");
        }
    }
}