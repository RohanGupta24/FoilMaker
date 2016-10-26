import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;


public class Connect {        public static void main(String[] args){
    Connect con = new Connect();  Scanner scan = new Scanner(System.in); System.out.print("Word: ");
    String input1 = scan.nextLine();

    con.connection(input1);
    while (true) { }
  }


  public void connection(String input){ String serverIP = "localhost"; int serverPort = 50000; Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    try {
      // Connect to server
      socket = new Socket(serverIP, serverPort);
      // Create data writer
      out = new PrintWriter(socket.getOutputStream(), true);
      // Create data reader
      InputStreamReader isr = new InputStreamReader(socket.getInputStream());
      in = new BufferedReader(isr);
      // Send message to server
      out.println(input);
      // Read server response
      String serverMessage = in.readLine();
      System.out.println("Message: " + serverMessage + ":end");

    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println("END");
  }
}
