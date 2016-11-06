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



        PrintWriter out;
        BufferedReader in;



    public testServerResponse(){



        String serverIP = "localhost";
        int serverPort = 50000;

        try{


            Socket socket = new Socket(serverIP,serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);

        }catch (IOException e){
            e.printStackTrace();
        }


    }




    public static void main(String[] args) throws IOException{


        testServerResponse work = new testServerResponse();

        Scanner scan = new Scanner(System.in);









        do{

            String input = scan.nextLine();

            if(input.equals("test")){
                break;
            }


            String connection = work.connect(input);

            System.out.println("Server output is: " + connection);
            //System.out.println(connection.lastIndexOf("-"));
            //System.out.println(connection.substring(connection.lastIndexOf("-") + 1));




        }while(true);





    }


    public String connect(String input){

        String output = "Didn't work";

        try{

            this.out.println(input);
            output = this.in.readLine();


        }catch (IOException e){
            e.printStackTrace();
        }

        return output;

    }



}
