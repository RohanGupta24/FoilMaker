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

    static boolean keepGoing = true;

    static String roundResult = "";
    static String overallResult = "";



    public static void main(String[] args){

        view = new FoilMakerView();


    }

    public void setKeepGoing(boolean keep){
        this.keepGoing = keep;
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
            this.view.setMessageBox("You are now logged in!");
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

        this.view.setMessageBox("Welcome!");
        String userToken = this.model.getUserToken();
        String connection = connection("STARTNEWGAME--" + userToken);

        String question = "Not a queston!";



        if(connection.contains("SUCCESS")){
            String gameToken = connection.substring(connection.lastIndexOf('-') + 1);



            this.view.setGameKeyText(gameToken);
            this.model.setGameToken(gameToken);
            this.view.setMessageBox("You started a game");
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
<<<<<<< HEAD

        String userToken = this.model.getUserToken();
        String gameToken = this.model.getGameToken();

        String connection = connection("ALLPARTICIPANTSHAVEJOINED--" + userToken + "--" + gameToken);
        System.out.println(connection);

        this.view.setWordClueText(connection.substring(connection.indexOf('-') + 2, connection.lastIndexOf('-') - 1));

        this.model.setRightAnswer(connection.substring(connection.lastIndexOf('-') + 1));
        System.out.println("LOOK HERE :::::" + this.model.getRightAnswer());



        if(connection.contains("USERNOTLOGGEDIN")){
            this.view.setMessageBox("User not logged in");
            return false;
        }else if(connection.contains("INVALIDGAMETOKEN")){
            this.view.setMessageBox("Invalid game token");
            return false;
        }else if(connection.contains("USERNOTGAMELEADER")){
            this.view.setMessageBox("User already playing game");
            return false;
        }



=======
        this.view.setMessageBox("Press <Start Game> to start game");
>>>>>>> origin/master
        return true;
    }

    public boolean isJoinGame(){

        this.view.setMessageBox("Welcome");
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
            this.view.setMessageBox("Joined game: waiting for leader");

            System.out.println("Score: "+ score);
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
<<<<<<< HEAD



        if(this.view.getUserSuggestion().equals(this.model.getRightAnswer())){
            this.view.setMessageBox("Choose a Different Answer");
            return false;
        }


        String input = this.view.getUserSuggestion();
        System.out.println(input);
        String connection = connection("PLAYERSUGGESTION--" + this.model.getUserToken() + "--" + this.model
                .getGameToken() + "--" + input);

        if(connection.contains("USERNOTLOGGEDIN")){
            this.view.setMessageBox("User not logged in");
            return false;
        }else if(connection.contains("INVALIDGAMETOKEN")){
            this.view.setMessageBox("Invalid Game Token");
            return false;
        }else if(connection.contains("UNEXPECTEDMESSAGETYPE")){
            this.view.setMessageBox("A different message should be sent");
            return false;
        }else if(connection.contains("INVALIDMESSAGEFORMAT")){
            this.view.setMessageBox("Invalid message format");
            return false;
        }

        String[] split;

        split = connection.split("--");


        for(int i = 0; i < split.length; i++){
            if(split[i].contains("ROUNDOPTIONS")){
                continue;
            }

                this.view.addOption(split[i]);


        }



        return true;
    }

    public boolean isSubmitOption(){

        String userToken = this.model.getUserToken();
        String gameToken = this.model.getGameToken();

        String connection = connection("PLAYERCHOICE--" + userToken + "--" + gameToken + "--" + this.view
                .getUserChoice());

        System.out.println(connection);


        String[] split;
        split = connection.split("--");


        for(int i = 0; i < split.length;i++){

            if(split[i].equals(this.model.getUserUsername())){

                roundResult = split[i + 1];

                overallResult += this.model.getUserUsername() + " -> Score: " + split[i+2] + " | Fooled: " +
                        split[i+3] + " players(s) | Fooled by: " + split[i+4] + " players(s)\n";


            }

        }



        System.out.println(connection);


        Thread waitForQuit = new Thread(){

            public void run(){

                String quit = "";
                while(true){

                    try{

                        quit = in.readLine();
                        if(quit.length() > 0){
                            System.out.println("Message: " + quit);
                            break;
                        }

                    }catch (IOException e){
                        e.getStackTrace();
                    }



                }

                if(quit.contains("QUIT")){

                    setOver(false);

                }


            }
        };

        waitForQuit.start();




        this.view.setWordClueText(connection.substring(connection.indexOf('-') + 1, connection.lastIndexOf('-') - 1));
        this.view.setSuggestionBox("");
        this.view.setOptionsList();




        return true;
=======
        this.view.setMessageBox("Enter your suggestion");
        return false;
    }

    public boolean isSubmitOption(){
        this.view.setMessageBox("Pick your choice")
        return false;
>>>>>>> origin/master
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

        String output = "";
        try{

            String serverResponse;

            while((serverResponse = in.readLine()) != null){
                System.out.println("Get Persons Response: " + serverResponse);
                output = serverResponse;
                if(output.length() > 1){
                    this.view.setParticipantsTextField(output.substring((output.indexOf('-') + 2)));

                    System.out.println("Added Person: " + output);
                    this.view.setMessageBox("Player Added");
                    return;
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }




    public void getLeaderQuestion(){

        System.out.println("Inside getLeaderQuestion");

        String output = " ";
        try{

            String serverResponse = in.readLine();
            System.out.println("ServerLeaderResponse" + serverResponse);
            return;


        }catch (IOException e){
            e.printStackTrace();
        }


        System.out.println("Leaving server response for getLeaderQuestion");
        return;




    }





    public String getServerResponse(){

        String output = "";
        try{

            String serverResponse;

            while((serverResponse = in.readLine()) != null){
                    System.out.println("Server Response: " + serverResponse);
                    output = serverResponse;
                        if(output.length() > 1){
                            this.model.setWordGuess(output.substring(output.indexOf("-") + 1, output.lastIndexOf("-")
                            - 1));

                            System.out.println("Word gueess" + this.model.getWordGuess());
                            return output;
                        }

            }
        }catch (IOException e){
            e.printStackTrace();
        }




        return output;
    }



    public String getRoundResult(){
        return roundResult;
    }

    public String getOverallResult(){
        return overallResult;
    }

    public String getRightAnswer(){
        return this.model.getRightAnswer();
    }


    public void setOver(boolean over){
        this.view.setOver(over);
    }



}
