import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient 
{
	public static boolean finish = false;
    public static void main(String[] args) throws IOException 
    {
    	final String HOST = "127.0.0.1";
        final int PORT = 4040;

        Socket socket = new Socket(HOST, PORT);

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        
        //thread to read messages from the server
        Thread read = new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                while (true) 
                {
                    try 
                    {
                        if (is.available() > 0) 
                        {
                            int nextChar = 0;
                            String message = "";
                            //checks for the & symbol that denotes the end of a message
                            while ((nextChar = is.read()) != 38) 
                            {
                                message = message + (char) nextChar;
                            }
                            System.out.println(message);
                            //checks for the EXIT message that denotes the end of a session
                            if(message.contains("EXIT"))
                            {
                            	System.out.println("Press Enter to exit the application");
                            	finish = true;
                            }
                        }
                    } 
                    catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                    
                    if(finish)
                    {
                    	break;
                    }
                }
            }
        });
        read.start();
        //thread to write messages to the server
        Thread write = new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                Scanner sc = new Scanner(System.in);
                while (true) 
                {
                    String message = sc.nextLine();
                    try 
                    {
                    	//writes the message and the & symbol to denote the end of the message
                        os.write((message + "&").getBytes());
                        os.flush();
                    } catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                 if(finish)
                 {
                	 break;
                 }
                }
            }
        });
        write.start();
    }
}
