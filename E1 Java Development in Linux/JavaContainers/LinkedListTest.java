package Contenent;

import java.util.LinkedList;

public class LinkedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList link = new LinkedList();
		
		link.add(new String("a"));
		link.add(new String("b"));
		
		System.out.println(link.toString());
		link.add(1, new String("c"));
		System.out.println(link.toString());
	}

}
