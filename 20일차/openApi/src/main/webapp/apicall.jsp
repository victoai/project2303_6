<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img{
width:100px;
height: 100px;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	function search(){
		alert("dkfkfkf");
		
		$.ajax(  {
			type:"GET" ,
			url: "/open/naver2"  ,
			success: function( data ){
				alert( data);	
				let result  = toHtml( data);
				/*
								
				
				 */				
				
				$("#wrap").html(result); 	  // div 태그 사이의 내용 가져오거나 변경할 때		
				
				console.log( data);
			} ,
			error: function(err){
				console.log( err);
			}			
		});  		
	}
	
	 	
	function toHtml( data){  // json data   =>  원하는 형식의 html으로 변환 			
		console.log( data);
	    let str="";
	    /*
	    for( let i=0;  i< data.length ;  i++){
	    	 let item  = data[i];
	    	 str +="<div>" +  item.id +  item.name  +  "</div>";
	    }	
	    */	    
	    /*
	    data.forEach( function( item,  index){
	    	 str +="<div>" +  item.title +  item.link  +  "</div>";
	    });	
	    */	    
	   /* data.forEach(  (item, index) =>{
	    	console.log( item);
	    	str +="<div>" +  item.title +  item.link  +  "<img src=" + item.image  +">" +  item.lprice + "</div>";
	    });
  */
  
    data.forEach(  (item, index) =>{
	console.log( item);
	str +="<div>" +  item.title +   "<a href=' "+ item.link+"' >  <img src=" + item.image  + "> </a>" +  item.lprice + "</div>";
	});
 	    
	   return str;		
	} 
</script>

</head>

<body>
<button onclick="search()">조회</button>
<div id="wrap"></div>

</body>
</html>