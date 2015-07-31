package Contenent;

import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeMap<String,String> tm = new TreeMap<String,String>();
		
		tm.put("b", "banana");
		tm.put("a", "apple");
		tm.put("p", "peach");
		tm.put("l", "lemon");
		tm.put("g", "greep");
		
		Set keys = tm.keySet();
		
		System.out.println(keys.toString());
		
	}

}
