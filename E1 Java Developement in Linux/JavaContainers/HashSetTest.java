package Contenent;

import java.util.HashSet;

public class HashSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet hs = new HashSet();
		
		hs.add(new String("a"));
		hs.add(new String("a"));
		hs.add(new String("b"));
		hs.add(new String("c"));
		hs.add(new String("d"));
		hs.add(new String("e"));
		
		System.out.println(hs.toString());
	}

}
