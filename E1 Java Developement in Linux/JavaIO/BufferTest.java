package BufferStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("/home/parallels/Documents/Hadoop_Tutorial/E1/JavaIO/BufferTest.txt");
		File file2 = new File("/home/parallels/Documents/Hadoop_Tutorial/E1/JavaIO/BufferTestOutput.txt");
		BufferedWriter bw = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				bw  = new BufferedWriter(new FileWriter(file2));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				String str = null; 
				while((str = br.readLine()) != null)
				{
					bw.write(str);
					bw.newLine();
				}
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
