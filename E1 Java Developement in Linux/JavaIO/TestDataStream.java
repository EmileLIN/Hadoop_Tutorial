package DataStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("/home/parallels/Documents/Hadoop_Tutorial/E1/JavaIO/output.txt");
		try {
			FileOutputStream out = new FileOutputStream(file);
			DataOutputStream dOut = new DataOutputStream(out);
			FileInputStream in = new FileInputStream(file);
			DataInputStream dIn = new DataInputStream(in);
			
			dOut.writeInt(32);
			dOut.writeDouble(3.33);
			dOut.flush();
			dOut.close();
			
			int a = dIn.readInt();
			System.out.println(a);
			System.out.print(dIn.readDouble());
			dIn.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
