package Contenent;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList arr = new ArrayList();
		arr.add(new String("a"));
		arr.add(new String("b"));
		arr.add(new String("c"));
		
		System.out.println(arr.isEmpty());
		System.out.println(arr.size());
		System.out.println(arr.toString());
		
		arr.remove(0);
		System.out.println(arr.toString());
		
		//Iterator
		Iterator it = arr.iterator();
		
		while(it.hasNext())
		{
			String str = (String)it.next();
			System.out.println(str);
		}
		
		
	}

}
