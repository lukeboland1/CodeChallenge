import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient 
{
    public static void main(String[] args) throws IOException 
    {
    	final String HOST = "127.0.0.1";
        final int PORT = 4040;

        Socket socket = new Socket(HOST, PORT);

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

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
                            while ((nextChar = is.read()) != 38) 
                            {
                                message = message + (char) nextChar;
                            }
                            System.out.println(message);
                        }
                    } 
                    catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        read.start();

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
                        os.write((message + "&").getBytes());
                        os.flush();
                    } catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        write.start();
    }
}