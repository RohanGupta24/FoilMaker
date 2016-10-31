//Model
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


public class Model {


    private String gameToken;


    public void setGameToken(String token){
        this.gameToken = token;
    }

    public String getGameToken(){
        return this.gameToken;
    }







    public void connection(String input){
        String serverIP = "localhost";
        int serverPort = 50000;
        Socket socket = null;
        PrintWriter server = null;
        BufferedReader in = null;
        try {

            socket = new Socket(serverIP, serverPort);
            server = new PrintWriter(socket.getOutputStream(), true);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
            server.println(input);

            String serverMessage = in.readLine();
            System.out.println("Message: " + serverMessage + ":end");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }



}
