//Controller


import java.io.*;
import java.net.Socket;




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
            this.model.setGameToken(output.substring(output.lastIndexOf("-") + 1));
            return true;
        }else if(output.contains("INVALIDMESSAGEFORMAT")){
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

        return true;

    }


    public boolean isJoinAGame(){
        return true;
    }


    public boolean isGameStarted(){
        return false;
    }

    public boolean isJoinGame(){
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
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "No return message for some reason";
    }











}
