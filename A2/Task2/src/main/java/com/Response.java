package com;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private String version;
    private int code;
    private String status;
    private HashMap<String, String> headers;
    private String message;

    public static String responseBuilder(Request request, String message, String contentType, String cookie){
        Response httpResponse = new Response();

        httpResponse.code = 200;
        httpResponse.status = "ok";
        httpResponse.version = request.getHttpVersion();

        httpResponse.headers = new HashMap<>();
        httpResponse.headers.put("Content-Type", contentType);
        httpResponse.headers.put("Set-Cookie", cookie);
        httpResponse.headers.put("Content-Length", String.valueOf(message.getBytes().length));

        httpResponse.message = message;

        StringBuilder builder = new StringBuilder();
        buildResponseLine(httpResponse, builder);
        builderResponseHeader(httpResponse, builder);
        buildMessage(httpResponse, builder);
        return builder.toString();
    }

    private static void buildResponseLine(Response httpResponse, StringBuilder stringBuilder){
        stringBuilder.append(httpResponse.version).append(" ").append(httpResponse.code).append(" ").append(httpResponse.status).append("\n");
    }

    private static void builderResponseHeader(Response httpResponse, StringBuilder stringBuilder){
        for (Map.Entry<String, String> entry : httpResponse.headers.entrySet()){
            stringBuilder.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        stringBuilder.append("\n");
    }

    private static void buildMessage(Response httpResponse, StringBuilder stringBuilder){
        stringBuilder.append(httpResponse.message);
    }
}
