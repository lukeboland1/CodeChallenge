package codeChallengeServer;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class NewGame implements Runnable {

    
    Client client1;
    Client client2;
    BoardState board;

    public NewGame(Client client1, Client client2) throws IOException {
        this.client1 = client1;
        this.client2 = client2;
        //creating a 6 x 9 board
        board = new BoardState(6,9);
    }

    @Override
    public void run() {
    	
        client1.write("Board state \n" + board.toString());
        client2.write("Board state\n" + board.toString());
        client2.write("Wating on Player 1s move....");

        boolean exit = false;
        boolean player1Turn = true;
        String player1Mark = "x";
        String player2Mark = "o";

        while (!exit) {
            
            if (player1Turn) 
            {
            	//make turn for player 1
                exit = play(client1, client2, player1Mark);
                player1Turn = false;
            }

            
            else 
            {
            	//make turn for player 2
                exit = play(client2, client1, player2Mark);
                player1Turn = true;
            }
            
            if(exit)
            {
            	//ask both players if they wish to rematch
            	client1.write("Play again? Enter y or n: ");
            	client2.write("Awaiting player ones option for rematch");
                String message = client1.read();
                if(message.contains("y"))
                {
                	client2.write("Play again? Enter y or n: ");
                    client1.write("Awaiting player twos response for rematch");
                    String message2 = client2.read();
                    if(message2.contains("y"))
                    {
                    	client1.write("Rematch accepted: New game beginning...");
                    	client2.write("Rematch accepted: New game beginning...waiting on Player 1s move");
                    	//reset board for the new game
                    	board = new BoardState(6,9);
                    	client1.write(board.toString());
                        client2.write(board.toString());
                		player1Turn = true;
                    	exit = false;
                    	//exit remains false so game logic continues and rematch starts
                    }
                    else
                    {
                    	client1.write("Rematch declined, session over");
                    }
                }
                else
                {
                	client2.write("Rematch declined, session over");
                }
                
            	
            }

        }
        //writing EXITING to denote the session is over
        client1.write("EXITING....");
        client2.write("EXITING....");
        client1.close();
        client2.close();
    }

    private boolean play(Client c1, Client c2, String mark) {
        String message;
        try {
            boolean marked = false;
            boolean winningMove = false;
            while (!marked) {
                try 
                {
                    c1.write("It's your turn " + c1.getName() + ", Please enter column  1-9: ");
                    message = c1.read();
                    //check if you can parse input as an integer, if not exception is thrown
                    int column = Integer.parseInt(message);
                    //check if integer is 1-9
                    if (column > 0 && column < 10)
                    {
                    	//makes the turn using the column entered by user and their mark (x or o)
                    	marked = board.makeTurn(column-1, mark);
                    	//checks if turn was successfully made, if column was full then it asks for a new column
                    	if(!marked)
                        {
                        	c1.write("Column is full please choose another:");
                        }
                        
                    }
                    else
                    {
                    	c1.write("Column is not between 1-9, please choose another:");
                    }
                    
                }
                
                catch(NumberFormatException er){
                	c1.write("Invalid input, please enter an integer 1-10");
                }
                
    
                
            }
            
            
            //send current state to the players
            c1.write(board.toString());
            c2.write(board.toString());
            
            //checks if the player making the move has won
            winningMove = board.checkWin();
            if(winningMove)
            {
            	c1.write("Game over - You win!! ");
                c2.write("Game over - You lose");
            	return true;
            }
            //checks if the board is now full hence a draw
            boolean draw = board.checkDraw();
            if(draw)
            {
            	c1.write("Game over - Draw as all squares are filled with no win");
                c2.write("Game over - Draw as all squares are filled with no win");
                return true;
            }
            //otherwise continues to next move
            c1.write("Wait for opponents move.........");
            c2.write("Your move!!!...........");
        }
        catch(Exception E)
        {
        	
        }
        
        
        return false;
    }

}