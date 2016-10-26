import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


public class connect{
  public static void main(String[] args){

    String serverIP = "localhost";
    int serverPort = 50000;

    Scanner scan = new Scanner(System.in);

    System.out.print("Word: ");
    String input = scan.next();
    System.out.print("\n");

      try {
        // Connect to server
        Socket socket = new Socket(serverIP, serverPort);
        // Create data writer
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Create data reader
        InputStreamReader isr = new InputStreamReader(socket.getInputStream()); BufferedReader in = new BufferedReader(isr);
        // Send message to server
        out.println(input);
        // Read server response
        String serverMessage = in.readLine();
        System.out.println("Message: " + serverMessage);
      } catch (IOException e) {
          e.printStackTrace();
      }







  }




}
