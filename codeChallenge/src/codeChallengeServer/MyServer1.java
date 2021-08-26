package codeChallengeServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
class MyServer 
{  
	public static void main(String args[])throws Exception
	{  
		
		BoardState board = new BoardState(6, 9);
		final int PORT = 4040;
        ServerSocket serverSocket = new ServerSocket(PORT);
        
        System.out.println("Server started...");
        System.out.println("Wating for clients...");
        
        while (true) {
            Socket firstPlayer = serverSocket.accept();
            System.out.print("Player 1 joined session. Player 1's IP address " + firstPlayer.getInetAddress().getHostAddress() + "\n");
            System.out.println("Waiting for Player 2....");
            Socket secondPlayer = serverSocket.accept();
            System.out.print("Player 2 joined session. Player 2's IP address " + secondPlayer.getInetAddress().getHostAddress() + "\n");
            Thread t = new Thread() {
                public void run() {
                    try (
                        PrintWriter playerOneOut = new PrintWriter(firstPlayer.getOutputStream(), true);
                        Scanner playerOneIn = new Scanner(firstPlayer.getInputStream());
                    		
                    	PrintWriter playerTwoOut = new PrintWriter(secondPlayer.getOutputStream(), true);
                        Scanner playerTwoIn = new Scanner(secondPlayer.getInputStream());
                    ) 
                    {
                    	boolean exit = false;
                    	boolean player1turn = true;
                        while (!exit) {
                        	//if(player1turn)
                        	{
                        		//exit = player1Turn(1);
                        		System.out.println(board.toString());
                        		playerOneOut.println("Player 1 enter the column for your move:");
                                /*String input = playerOneIn.nextLine();
                                playerOneOut.println(board.toString());
                                if (input.equalsIgnoreCase("exit")) {
                                    exit = true;
                                }
                                System.out.println("Received move from client 1: " + input);
                                board.makeTurn(Integer.parseInt(input));
                                playerOneOut.println(board.toString());
                                playerOneOut.println("exit");
                                player1turn = false;*/
                        	}
                        	/*else
                        	{
                        		 System.out.println(board.toString());
                                 playerTwoOut.println("Player 2 enter the column for your move:");
                                 String input2 = playerTwoIn.nextLine();
                                 if (input2.equalsIgnoreCase("exit")) {
                                	 exit = true;
                                 }
                                 System.out.println("Received move from client 2: " + input2);
                                 board.makeTurn(Integer.parseInt(input2));
                                 playerTwoOut.println(board.toString());
                                 playerTwoOut.println("exit");
                                 player1turn = true;
                        		//exit = playerTurn(2);
                        	}*/
                        	
                            
                            
                           
                            
                        }
                    } catch (IOException e) { }
                }
            };
            t.start();
        }
		
		
		
		/*System.out.println(board.makeTurn(0));
		System.out.println(board.makeTurn(1));
		System.out.println(board.makeTurn(0));
		System.out.println(board.makeTurn(1));
		System.out.println(board.makeTurn(0));
		System.out.println(board.makeTurn(1));
		System.out.println(board.makeTurn(0));
		boolean win = board.checkWin();
		
		if (win)
		{
			System.out.print("Player " + board.getLastTurn() + " wins");
		}*/
		
		
	}
} 