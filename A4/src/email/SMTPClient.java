package email;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class SMTPClient {
    public static void main(String[] args) throws Exception {
        //Encode user info
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your username");
        String name = scanner.nextLine();
        String username = Base64.getEncoder().encodeToString(name.getBytes(StandardCharsets.UTF_8));
        System.out.println("Please input your password");
        String password = Base64.getEncoder().encodeToString(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
        //Build Socket
        Socket socket = new Socket("smtp.kth.se", 587);
        //Start the input thread
        Server server = new Server(socket);
        server.start();
        //New a outer
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8)));
        //Send extend-hello and startssl command
        out.println("EHLO localhost\r");
        out.flush();
        Thread.sleep(100);
        out.println("STARTTLS\r");
        out.flush();
        Thread.sleep(100);

        //Build SSL socket
        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket)factory.createSocket(
                socket,
                socket.getInetAddress().getHostAddress(),
                socket.getPort(),
                true);

        sslSocket.startHandshake(); // Optional
        //New a outer for the ssl socket
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream(),StandardCharsets.UTF_8)));
        //start a new receiver.
        server.stop();
        Server sslServer = new Server(sslSocket);
        sslServer.start();
        out.println("EHLO smtp.kth.se\r");
        Thread.sleep(100);

        //System.exit(0);
        out.println("AUTH LOGIN\r");
        out.flush();
        out.println(username + "\r");
        out.flush();
        out.println(password + "\r");
        out.flush();
        Thread.sleep(1500);
        System.out.println("**************************");
        System.out.println("Sending email");
        out.println("MAIL FROM:<"+ name +"@kth.se>" + "\r");
        out.flush();
        Thread.sleep(100);
        System.out.println("The Receiver Email Address:");
        out.println("RCPT TO:<"+ scanner.nextLine() +">" + "\r");
        out.flush();
        out.println("DATA" + "\r");
        out.flush();
        Thread.sleep(200);
        System.out.println("Subject: ");
        var subject = scanner.nextLine();
        out.println(String.format("Subject: %s\n", subject));
        out.flush();
        System.out.println("PLease input the message (finish by .)");
        String message;
        while (true) {
            //ask for user input
            if (scanner.hasNextLine()) {
                message = scanner.nextLine();
                out.print(message + "\r\n");
                out.flush();
//                System.out.println(message);
                if (message.equals("."))
                    break;
            }
        }
        out.println("QUIT" + "\r");
        out.flush();
    }
}