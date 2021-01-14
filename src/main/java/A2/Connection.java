package A2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Connection {

    public static void main(String[] args) throws IOException {

        //URL url = new URL("https://jsonplaceholder.typicode.com/users");
        URL url = new URL("http://www.csc.kth.se");
//        BufferedReader bin = new BufferedReader(new InputStreamReader(url.openStream()));

        URLConnection uc = url.openConnection();
        uc.setConnectTimeout(5000); //millisecond
        uc.setReadTimeout(5000);
        InputStream in = uc.getInputStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));

        String line;
        while((line = bin.readLine()) != null){
            System.out.println(line);
        }
        bin.close();

    }
}
