//Controller


import java.io.*;
import java.net.Socket;
import java.lang.*;




public class Controller{


    static FoilMakerView view;
    static Model model;
    static Socket socket;
    static PrintWriter server;
    static BufferedReader in;



    public static void main(String[] args){

        view = new FoilMakerView();




    }


    public Controller(){


        model = new Model();

        try {

            String serverIP = "localhost";
            int serverPort = 50000;
            socket = new Socket(serverIP, serverPort);
            server = new PrintWriter(socket.getOutputStream(), true);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);


        }catch (IOException e){
            e.printStackTrace();
        }

    }







    public boolean isRegistered(){

        String username = view.getUsernameText();
        String password = view.getPasswordText();
        String output = connection("CREATENEWUSER--" + username + "--" + password);


        if(output.equals("RESPONSE--CREATENEWUSER--SUCCESS")){
            this.view.setMessageBox("New User Created");
            return true;
        }else if(output.contains("INVALIDMESSAGEFORMAT")){

            return false;
        }else if(output.contains("INVALIDUSERNAME")){

            return false;
        }else if(output.contains("INVALIDUSERPASSWORD")){

            return false;
        }else if(output.contains("USERALREADYEXISTS")){

        }else{
            System.out.println("No input");
            return false;
        }


        System.out.println("Nothing");
        return false;
    }

    public boolean isLogged() {


        String username = view.getUsernameText();
        String password = view.getPasswordText();
        System.out.println("Username text: " + view.getUsernameText());
        System.out.println("Passwrod text: " + view.getPasswordText());
        String output = connection("LOGIN--" + username + "--" + password);

        if(output.contains("SUCCESS")){
            this.model.setUserUsername(username);
            this.model.setUserPassword(password);
            this.model.setUserToken(output.substring(output.lastIndexOf("-") + 1));
            System.out.println("UserToken: " + this.model.getUserToken());
            return true;
        }else if(output.contains("INVALIDMESSAGEFORMAT")){
            this.view.setMessageBox("Invalid Message Format");
            return false;
        }else if(output.contains("UNKNOWNUSER")){

            return false;
        }else if(output.contains("INVALIDUSERPASSWORD")){
            return false;
        }else if(output.contains("USERALREADYLOGGEDIN")){
            return false;
        }else{
            System.out.println("No input");
            return false;
        }


    }






    public boolean isNewGameStarted(){

        String userToken = this.model.getUserToken();
        String connection = connection("STARTNEWGAME--" + userToken);

        if(connection.contains("SUCCESS")){
            String gameToken = connection.substring(connection.lastIndexOf('-') + 1);
            this.view.setGameKeyText(gameToken);
            this.model.setGameToken(gameToken);

            return true;
        }else if(connection.contains("USERNOTLOGGEDIN")){
            System.out.println("Error isNewGameStarted Invalid User Token");
            return false;
        }else if(connection.contains("FAILURE")){
            System.out.println("Error isNewGameStarted Failre error");
            return false;
        }
        return false;
    }


    public boolean isJoinAGame(){
        return true;
    }


    public boolean isGameStarted(){
        return true;
    }

    public boolean isJoinGame(){

        String userToken = this.model.getUserToken();
        String gameToken = this.view.getCheckKeyValidity();
        if(gameToken == null || gameToken.length() == 0){
            gameToken = "this should not be a game token";
        }
        String output = connection("JOINGAME--" + userToken + "--" + gameToken);

        if(output.contains("SUCCESS")){
            this.model.setGameToken(gameToken);
            System.out.println(output);
            String score = (output.substring(output.lastIndexOf("-") + 1));
            this.view.setMessageBox("Waiting");

            System.out.println(score);
            return true;
        }else if(output.contains("USERNOTLOGGEDIN")){
            System.out.println("Invalid user Token");
            return false;
        }else if(output.contains("GAMEKEYNOTFOUND")){

            System.out.println("Game key not found");
            return false;
        }else if(output.contains("FAILURE")){
            System.out.println("User already playing game");
            return false;
        }
        return false;
    }

    public boolean isSubmitSuggestion(){
        return false;
    }

    public boolean isSubmitOption(){
        return false;
    }

    public boolean isNextRound(){
        return false;
    }






    public static String connection(String input){


        try {
            server.println(input);
            String serverMessage = in.readLine();
            //in.close();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "No return message for some reason";
    }


    public void getPersons(){

        System.out.println("Inside getPersons");

        try{

            System.out.println("Inside Try");

            String line;


            while ((line = in.readLine()) != null) {
                System.out.println(line);
                this.view.setParticipantsTextField(line);
                this.view.setMessageBox("New User Added");

            }


            System.out.println("After Try");

        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }


    }











}
