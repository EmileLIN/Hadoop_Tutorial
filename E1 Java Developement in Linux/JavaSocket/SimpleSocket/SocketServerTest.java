package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


//Server
public class SocketTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket so = new ServerSocket(8888);
			
			while(true)
			{
				Socket sock = so.accept();
				InputStream in = sock.getInputStream();
				DataInputStream din = new DataInputStream(in);
				
			
				
				String str = din.readUTF();
				
				System.out.println("Someone is connecting");
				System.out.println("The client says: "+ str);
				
				OutputStream out = sock.getOutputStream();
				DataOutputStream dout = new DataOutputStream(out);
				
				if(str.equals("Hello,Server"))
				{
					dout.writeUTF("Hello,Client");
				}
				else{
					dout.writeUTF("What do you want to say ??");
				}
				
				din.close();
				in.close();
				dout.close();
				out.close();
				so.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
