//Controller
import java.io.*;
import java.net.Socket;
import java.util.*;



public class Controller{


  public Controller(){

  }

  public static void main(String[] args){

    Controller player = new Controller();


    System.out.println("First token: " + player.getGameToken);
    player.setGameToken("Hello");
    System.out.println("Second token: " + player.getGameToken);

  }



  public void Connect(String input){
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
