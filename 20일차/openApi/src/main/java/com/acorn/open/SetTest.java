package com.acorn.open;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		 
		//중복허용안함,  순서보장하지 않음 
		Set<String> set = new  HashSet<>();		
		set.add("포도");
		set.add("포도");
		set.add("포도");		
		
		for( String str:  set ) {
			System.out.println( str);
		}
		
		set.forEach(  str ->System.out.println(str));
		
		
		Iterator<String> ir = set.iterator();		
		while( ir.hasNext()) {
			System.out.println(  ir.next());
		}
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
