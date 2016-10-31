//Model
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


public class Model {


    private String gameToken;           //Users game token that is assigned when logged in
    private String userUsername;        //Username the player submits
    private String userPassword;        //Password the player submits
    private String databaseUsername;    //Username in the database
    private String databasePassword;    //Password in the database




    /*
        Getter and setter methods
    */
    public void setGameToken(String token){
        this.gameToken = token;
    }

    public String getGameToken(){
        return this.gameToken;
    }

    public void setUserUsername(String username){
        this.userUsername = username;
    }

    public String getUserUsername(){
        return this.userUsername;
    }

    public void setUserPassword(String password){
        this.userPassword = password;
    }

    public String getDatabaseUsername(){
        return this.databaseUsername;
    }

    public String getDatabasePassword(){
        return this.databasePassword;
    }






    /*
        This method should get the users input for username and password and check to see if
        they already have an account. If they do not then it should return a string that tells the
        controller that they are not in the database and should update the view to represent that.
     */
    public String checkLogIn(String username, String password){





        return " ";
    }








    /*                                      Can Ignore for now, moved this to Controller
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
    */


}
