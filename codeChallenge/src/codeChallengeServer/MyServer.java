package codeChallengeServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
class MyServer 
{  
	public static void main(String args[])throws IOException
	{  
		
		final int PORT = 4040;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = null;
        System.out.println("Server started...");
        System.out.println("Wating for clients...");
            while (true) {
                //setting up player 1
                System.out.println("Waiting for Player 1");
                socket = serverSocket.accept();
                //socket.setKeepAlive(true);
                //socket.setSoTimeout(600);
                Client c1 = new Client(socket);
                System.out.println("Player 1 has joined");
                c1.write("You are player X");
                c1.write("Waiting for Player 2....");
                System.out.println("Waiting for Player 2...");
                
                //setting up player 2
                socket = serverSocket.accept();
                Client c2 = new Client(socket);
                System.out.println("Player 2 joined");
                c2.write("You are player O");
                NewGame game = new NewGame(c1, c2);
                game.run();
            }
        
       
		
	}
} 

