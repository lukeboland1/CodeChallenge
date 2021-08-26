package codeChallengeServer;

import java.io.IOException;

public class NewGame implements Runnable {

    
    Client client1;
    Client client2;
    BoardState board;

    public NewGame(Client client1, Client client2) throws IOException {
        this.client1 = client1;
        this.client2 = client2;
        board = new BoardState(6,9);
    }

    @Override
    public void run() {

        client1.write("Board state \n" + board.toString());
        client2.write("Board state\n" + board.toString());

        boolean exit = false;
        boolean player1Turn = true;
        String player1Mark = "x";
        String player2Mark = "o";

        while (!exit) {
            
            if (player1Turn) {
                exit = play(client1, client2, player1Mark);
                player1Turn = false;
            }

            
            else {
                exit = play(client2, client1, player2Mark);
                player1Turn = true;
            }
            
            if(exit)
            {
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
                    	board = new BoardState(6,9);
                    	client1.write(board.toString());
                        client2.write(board.toString());
                		player1Turn = true;
                    	exit = false;	
                    }
                }
                
            	
            }

        }
        
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
                    int column = Integer.parseInt(message);
                    if (column > 0 && column < 10)
                    {
                    	marked = board.makeTurn(column-1, mark);
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
            
            winningMove = board.checkWin();
            if(winningMove)
            {
            	c1.write("Game over - You win!! ");
                c2.write("Game over - You lose");
            	return true;
            }
            boolean draw = board.checkDraw();
            if(draw)
            {
            	c1.write("Game over - Draw as all squares are filled with no win");
                c2.write("Game over - Draw as all squares are filled with no win");
                return true;
            }
            c1.write("Wait for opponents move.........");
            c2.write("Your move!!!...........");
        }
        catch(Exception E)
        {
        	
        }
        
        
        return false;
    }

}