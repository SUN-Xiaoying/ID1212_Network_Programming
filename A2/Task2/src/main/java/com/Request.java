package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Request {
    private String method;
    private String uri;
    private String httpVersion;
    private HashMap<String, String> headers;
    private String message;

    public void setMethod(String method) {this.method = method;}
    public void setUri(String uri){this.uri = uri;}
    public void setHttpVersion(String httpVersion){this.httpVersion = httpVersion;}
    public void setHeaders(HashMap<String, String> headers){this.headers = headers;}

    public String getCookie(){return this.headers.get("Cookie");}
    public String getMethod(){return this.method;}
    public String getUri(){return this.uri;}
    public String getMessage(){return this.message;}
    public String getHttpVersion(){return this.httpVersion;}

    public static void decodeRequestLine(BufferedReader requestReader, Request request) throws IOException {
        String[] strs = requestReader.readLine().split(" ");
        assert strs.length == 3;
        request.setMethod(strs[0]);
        request.setUri(strs[1]);
        request.setHttpVersion(strs[2]);
    }

    public static void decodeRequestHeader(BufferedReader requestReader, Request request) throws IOException {
        String line = "";
        String[] kv;
        HashMap<String, String> headers = new HashMap<>();
        while(!(line = requestReader.readLine()).equals("")){
            kv = line.split(": ");
//            for (String str : kv){
//                System.out.println(str);
//            }
            assert kv.length == 2;
            headers.put(kv[0].trim(), kv[1].trim());
        }
        request.setHeaders(headers);
    }

    public static void decodeRequestMessage(BufferedReader requestReader, Request request) throws IOException {
        int contentLen = Integer.parseInt(request.headers.getOrDefault("Content-Length", "0"));
        if (contentLen == 0) return;
        char[] message = new char[contentLen];
        requestReader.read(message);
        request.message = new String(message);
    }

    public static Request decodeRequest(InputStream inputStream) throws IOException {
        BufferedReader requestReader = new BufferedReader(new InputStreamReader(inputStream));
        Request request = new Request();
        decodeRequestLine(requestReader, request);
        decodeRequestHeader(requestReader, request);
        decodeRequestMessage(requestReader, request);
        return request;
    }
}
