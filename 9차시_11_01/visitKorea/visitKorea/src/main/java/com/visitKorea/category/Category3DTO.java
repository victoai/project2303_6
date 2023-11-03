package com.visitKorea.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 매개변수 있는 생성자
public class Category3DTO {
	
	String cat3code;
	String name;
	String cat1code;
	String cat2code;
	
}
