package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


//Client
public class MyServer {
	public static void main(String args[])
	{
		try {
			Socket so = new Socket("127.0.0.1",8888);
			
			OutputStream out = so.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
			
			
			
			dout.writeUTF("Hello,Server");
			
			InputStream in = so.getInputStream();
			DataInputStream din = new DataInputStream(in);
			
			String str = din.readUTF();
			
			System.out.println("The server says: "+str);
			
			dout.close();
			out.close();
			
			din.close();
			in.close();
			so.close();
			
		
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
