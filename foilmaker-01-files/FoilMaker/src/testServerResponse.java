import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by willborland on 11/1/16.
 */
public class testServerResponse {



    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        String input = null;

        //System.out.println("Enter phrase: ");
        //input = scan.nextLine();

        System.out.println("Output: " + isRegistered());
    }



    public static boolean isRegistered(){


        String username = "h";
        String password = "a";

        String input = "CREATENEWUSER--" + username + "--" + password;
        System.out.println(input);
        String output = connection(input);
        System.out.println("Output varible is: " + output);



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
