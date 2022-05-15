import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {
    public static boolean isRunning=true;
    public static void main (String[] args)  {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port

            try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())) ) {
            // Send a request to the server
                while (isRunning) {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Input command: ");
                    String request = scan.nextLine();
                    //System.out.println(request);
                    out.println(request);
                    // Wait the response from the server
                    String response = in.readLine();
                    System.out.println(response);
                    if (request.substring(0).equals("exit")) {
                        isRunning = false;
                        socket.close();
                    }
                }

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
        catch   (IOException e){
            //System.err.println("IOException " + e);
           System.out.println("The server is closed or you got timed out for inactivity.");
        }
    }
}