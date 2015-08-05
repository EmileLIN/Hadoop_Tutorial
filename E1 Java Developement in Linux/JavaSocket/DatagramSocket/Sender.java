package Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket so = new DatagramSocket();
			
			String str = new String("Hello,my friend!");
			
			try {
				DatagramPacket dp = new DatagramPacket(str.getBytes(),str.length(),InetAddress.getByName("127.0.0.1"),8888);
				try {
					so.send(dp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
