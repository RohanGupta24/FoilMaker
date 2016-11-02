//Controller
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.*;



public class Controller{


    static FoilMakerView view = new FoilMakerView();



    public static void main(String[] args){


        Controller controller = new Controller();


        view.loginPage();

    }


    public Controller(){


    }





    public boolean isRegistered(){

        String username = view.getUsernameText();
        String password = view.getPasswordText();
        String output = connection("CREATENEWUSER--" + username + "--" + password);


        if(output.equals("RESPONSE--CREATENEWUSER--SUCCESS")){
            return true;
        }else if(output.equals("RESPONSE--CREATENEWUSER--INVALIDMESSAGEFORMAT--CREATENEWUSER--" + username + "--" +
                password)){
            view.setTitle("Invalid Message Format: Try again");
            return false;
        }else if(output.equals("RESPONSE--CREATENEWUSER--INVALIDUSERNAME--CREATENEWUSER--" + username + "--" +
                password)){
            view.setTitle("Username empty");
            return false;
        }else if(output.equals("RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--CREATENEWUSER--" + username + "--" +
                password)){
            view.setTitle("Password empty");
            return false;
        }else if(output.equals("RESPONSE--CREATENEWUSER--USERALREADYEXISTS--CREATENEWUSER--" + username + "--" + password)){
            view.setTitle("User already exists");
        }

        System.out.println("isRegistered Error");
        return false;
    }








    public boolean isNewGameStarted(){

        return false;
    }


    public boolean isJoinAGame(){
        return false;
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

            return serverMessage;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "No return message for some reason";
    }











}
