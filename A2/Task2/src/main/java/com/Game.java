package com;

import java.util.Random;

public class Game {

    private final int number;
    private String lastNumber;
    private int triedTime;
    public boolean gameOver;

    public Game(){
        Random r = new Random();
        this.number = r.nextInt(99) + 1;
        this.gameOver = false;
    }

    public String inputGuess(String input){

        String num = input.split("&")[0];
        this.lastNumber = num.split("=")[1];
        this.triedTime += 1;
        int inputNumber = Integer.parseInt(lastNumber);

        if (inputNumber > this.number){
            return
                    "<html>"
                            + "<body>"
                            + "Debug: "+ this.number
                            + "<h2>The correct number is any number between 1 and 100.</h2>"
                            + "<h2> Nope, " + inputNumber + " is higher </h2>"
                            + "<h2>You have tried " + this.triedTime + " times</h2>"
                            + "<form method='post' action= '/input'>"
                            + "<label>What's your guess</label>"
                            + "<input name='num' type=\"text\"></input>"
                            + "<br/><input type=\"submit\" name=\"submit\" value='Guess'>"
                            +"</form>"
                            +"</body>"
                            + "</html>"
                    ;
        }
        else if (inputNumber < this.number){
            return
                    "<html>"
                            + "<body>"
                            + "Debug: "+ this.number
                            + "<h2>The correct number is any number between 1 and 100.</h2>"
                            + "<h2> Nah, " + inputNumber + " is lower " + "</h2>"
                            + "<h2>You have tried " + this.triedTime + " times</h2>"
                            + "<form method='post' action= '/input'>"
                            + "<label>What's your guess? </label>"
                            + "<input name='num' type=\"text\"></input>"
                            + "<br/><input type=\"submit\" name=\"submit\" value='Guess'>"
                            +"</form>"
                            +"</body>"
                            + "</html>";
        }
        this.gameOver = true;
        return
                "<html>"
                        + "<body>"
                        + "<form method='post' action= '/new'>"
                        + "<h2> Bingo! </h2>"
                        + "<h2>you have tried " + this.triedTime + " times</h2>"
                        + "<br/><button type=\"submit\" name=\"submit\">New game</button>"
                        +"</form>"
                        +"</body>"
                        + "</html>";
    }

    public String getLastInput(){
        assert lastNumber != null;
        return "<html>"
                + "<body>"
                + "<h2>The correct number is any number between 1 and 100.</h2>"
                + "<h2>you have tried " + this.triedTime + " times</h2>"
                + "<form method='post' action= '/input'>"
                + "<label>What's your guess? </label>"
                + "<input name='num' type=\"text\"></input>"
                + "<br/><input type=\"submit\" name=\"submit\" value='Guess'>"
                +"</form>"
                +"</body>"
                + "</html>";
    }
    public String gameBegin(){
        return
                "<html>"
                        + "<body>"
                        + "Debug: "+ this.number
                        + "<h2>I'm thinking a number between 1 and 100.</h2>"
                        + "<form method='post' action= '/input'>"
                        + "<label>What's your guess? </label>"
                        + "<input name='num' type=\"text\"></input>"
                        + "<br/><input type=\"submit\" name=\"submit\" value='Guess'>"
                        +"</form>"
                        +"</body>"
                        + "</html>";
    }

}