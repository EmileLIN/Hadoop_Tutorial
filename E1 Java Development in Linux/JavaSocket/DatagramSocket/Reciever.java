package Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket so = new DatagramSocket(8888);
			
			byte[] data = new byte[1024];
			
			DatagramPacket dp = new DatagramPacket(data,data.length);
			
			try {
				so.receive(dp);
				System.out.println(new String(dp.getData()));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
