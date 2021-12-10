package com;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class GameServer {
    private HashMap<String, Game> gameMap;
    private ServerSocket serverSocket;

    public GameServer(){
        this.gameMap = new HashMap<>();
    }

    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        while(true){
            Socket socket = serverSocket.accept();
            Request request = Request.decodeRequest(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            String method = request.getMethod();
            String uri = request.getUri();
            String cookie = request.getCookie();

            String message = " ";
            //an existed game
            if (this.gameMap.containsKey(cookie)){
                Game gameInstance = this.gameMap.get(cookie);
                if (method.equals("GET")){
                    if (uri.equals("/game")){
                        message = gameInstance.getLastInput();
                    }
                }
                else if (method.equals("POST")){
                    if (uri.equals("/input")){
                        String inputNumber = request.getMessage();
                        message = gameInstance.inputGuess(inputNumber);
                        if (gameInstance.gameOver){
                            this.gameMap.remove(cookie, gameInstance);
                            gameInstance = null;
                        }
                    }
                    else {
                        message = "this uri is not supported";
                    }
                }
            }

            else {
                if (method.equals("GET")){
                    if (uri.equals("/game")){
                        Game newGameInstance = new Game();
                        cookie = String.valueOf(newGameInstance.hashCode());
                        this.gameMap.put(cookie, newGameInstance);
                        message = newGameInstance.gameBegin();
                    }

                }
                else if (method.equals("POST")){
                    if (uri.equals("/new")){
                        Game newGameInstance = new Game();
                        cookie = String.valueOf(newGameInstance.hashCode());
                        this.gameMap.put(cookie, newGameInstance);
                        message = newGameInstance.gameBegin();
                    }
                    else{
                        message = "no game existing";
                    }
                    
                }
            }

            String response = Response.responseBuilder(request, message, "text/html", cookie);
            printWriter.print(response);
            printWriter.flush();
            socket.close();

        }
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        try {
            gameServer.start(8081);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}