package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerializtion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("/home/parallels/Documents/Hadoop_Tutorial/E1/JavaSerialization/output.txt");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			ois = new ObjectInputStream(new FileInputStream(file));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			oos.writeObject(new Person("Emile",23));
			
			Person per2 = null;
			try {
				per2 = (Person) ois.readObject();
				
				System.out.println("The name is "+ per2.getName());
				System.out.println("The age is "+per2.getAge());
				
				oos.close();
				ois.close();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

}
