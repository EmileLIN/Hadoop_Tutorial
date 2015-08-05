import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileIO {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		File file_in = new File("/home/parallels/Documents/Hadoop_Tutorial/E1/JavaIO/input.txt");
		File file_out = new File("/home/parallels/Documents/Hadoop_Tutorial/E1/JavaIO/output.txt");
		
		if(!file_in.exists()){
			try {
				file_in.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			FileInputStream in = new FileInputStream(file_in);
			FileOutputStream out = new FileOutputStream(file_out);
			
			byte[] arr = new byte[32];
			
			try {
				while(in.read(arr) != -1)
				{
					for(byte c:arr)
					{
						if(c != '\00')
						{
							out.write(c);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
	
		
		
		
	}

}
