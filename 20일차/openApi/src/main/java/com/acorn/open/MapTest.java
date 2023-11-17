package com.acorn.open;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		 
		
		// Map 키:값 형태의 데이터를 저장하는 자료 구조 
		

		//Map<String, String > map  = new HashMap<String, String>();
		Map<String, String > map  = new HashMap< >();  //java version 
		
		
		map.put("html", "tag - a태그(하이퍼텍스트) - 블럭, 인라인, 인라인블럭 ");
		map.put("css", "style - 선택자 -태그, 클래스, id");
		map.put("javascript", "dom 제어 , 선택자 querySelector...");
		map.put("java", "class - 변수, 함수");
		map.put( "웹프로그래밍" ,"서블릿, jsp , mvc2 , spring - 요청/응답 - 문서, 데이터:json , xml");
		
	
		map.get("html");
		//Entry 
		// map요소 한 개를  Entry 
		System.out.println("====1");
		Set<Entry<String, String>>  set =  map.entrySet();		
		for(  Map.Entry<String, String> e : set ) {
			System.out.println( e.getKey() + " , " + e.getValue());			
		}
		
		System.out.println("====2");
		set.forEach(  item -> System.out.println(  item.getKey() +"," + item.getValue()));
		 
		System.out.println("====3");
		map.forEach( (key, value) -> System.out.println( key +"," + value));
		
		
		
		
		
	 
		
		
		
		
		
		
	}

}
