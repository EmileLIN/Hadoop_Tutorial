package Contenent;

import java.util.HashMap;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,String> hm = new HashMap<String, String>();
		
		hm.put("a","apple");
		hm.put("b","banana");
		hm.put("s","strawberry");
		
		String str = hm.get("a");
		System.out.println(str);
		
		Set keys = hm.keySet();
		System.out.println(keys.toString());
		
	}

}
