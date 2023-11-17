package com.acorn.open;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CollectionTest {

	public static void main(String[] args) {
		 
		// Collection  (인터페이스)   - iterator
		//  -List, Set ( 인터페이스)
		//   List구현체  : ArrayList  , LinkedList 
		//   Set구현체 : HashSet , TreeSet
		
		
		//순서보장, 동일한것 허용
		ArrayList<String> list  = new ArrayList<>();
		list.add("딸기");
		list.add("포도");
		list.add("망고");
		
		for( int i=0 ;  i< list.size() ; i++ ) {
			System.out.println( list.get(i));
		}
		
		
		for( String s :  list) {
			System.out.println( s);
		}		
		
		list.forEach( s -> System.out.println( s));		
		Iterator<String> ir  = list.iterator();		
		
		while( ir.hasNext()) {
			System.out.println( ir.next());
		}
		
		
		
		
		Set<String> set  = new HashSet<>();
		set.add("딸기");
		set.add("포도");
		set.add("망고");
				
		
		Collection<String> c  = list;
		
		Iterator<String> ir2 = c.iterator();
		while( ir2.hasNext()) {
			System.out.println( ir2.next());
		}
		
		c= set;
		Iterator<String>  ir3 = c.iterator();
		while( ir3.hasNext()) {
			System.out.println(  ir.next());
		}
		
		
	 
		
		
		

	}

}
