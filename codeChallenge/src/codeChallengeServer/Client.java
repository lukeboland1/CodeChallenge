package codeChallengeServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    Socket socket;
    InputStream is;
    OutputStream os;
    String name;

    Client(Socket socket) throws IOException {
    	//setting up input and output streams for the client
        this.socket = socket;
        is = socket.getInputStream();
        os = socket.getOutputStream();
        //socket.setSoTimeout(60000);
        initialSetup();
        
    }

    private void initialSetup() {     
        write("Welcome to the game. Line up 5 pieces in a row to win!");
        write("Please enter your name: ");
        name = read();
        System.out.println("Players name is " + name);
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String read()
    {
        String message = "";
        boolean exit = false;
        while (!exit)
        {
            try 
            {
                if (is.available() > 0) 
                {
                	//checks if next character is a & symbol. & symbol is used to denote the end of a message
                    int nextChar;
                    while ((nextChar = is.read()) != 38) 
                    {
                    	message = message + (char) nextChar;
                    }
                    exit = true;
                }
            } 
            catch (IOException e) 
            {
                System.out.println("Error: Could not read input");
            }
        }
        return message;
    }

    public void write(String message)
    {
        try 
        {
        	//writes the message with & at the end to denote the end of the message
            os.write((message+"&").getBytes());
            os.flush();
        } 
        catch (IOException e) 
        {
            System.out.println("Error: Could not write output");
        }
    }

    public void close() 
    {
        try 
        {
        	//closing sockets and streams
            socket.close();
            os.close();
            is.close();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}