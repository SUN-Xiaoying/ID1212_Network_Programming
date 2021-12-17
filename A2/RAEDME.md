# Guessing game
Your task is to write a guessing game with sockets where the dialogue will be according to the following (when you connect with your webbrowser):

Requirements of the program: It should consist of at least two classes, a serverclass and a guessclass where the former handles the requests from and the responses to the server and the latter handles the gamelogic.

## Note:

- Each new client connecting should lead to a new instance of the game with a new guessclass-object and a "Set-Cookie" field added in the http-response.
- There is no requirement to use separate threads (but you are allowed to).
- The browser creates an additional request for the bookmark-icon "favicon.ico" (browser dependant, test & try)
- A new browser window (but not tab) usually creates a new session (browser dependant, test & try)
- You should only use Java SE in the solution and no Java EE.

**Extra assignment**: Use the java.net.HttpURLConnection (Links to an external site.) class to simulate a browser and play the game 100 times and present the average number of guesses.

### Note:

- If you are using JDK 11 or above you may use the java.net.http.HttpClient (Links to an external site.) class instead of HttpURLConnection.
- There is no requirement to make a multi-threaded solution (but you may do so).
