//Controller
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.*;



public class Controller{

    Model player;
    View view;





    public static void main(String[] args){


        Controller controller = new Controller();


        String output = connection("Hello");

    }


    public Controller(){

        player = new Model();
        view = new View();

    }


    public Model getModel(){
        return this.player;
    }

    public View getView(){
        return this.view;
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
