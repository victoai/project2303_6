package com.acorn.open;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverShopSearch {
    public String  search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "QaMZOIzquZ2XCHAFDEMN");
        headers.add("X-Naver-Client-Secret", "GhQcIoHq8g");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" +query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        //System.out.println("Response status: " + status);
         System.out.println(response);
        return response;
    }


    public List<ItemDto> fromJSOntoItems(String result){
    	
        JSONObject rjson  = new JSONObject(result);
        System.out.println( rjson);
        JSONArray items = rjson.getJSONArray("items");

        List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
        for(int i=0; i <items.length(); i++){
            JSONObject itemJson =  items.getJSONObject(i);
            System.out.println( itemJson);
            /*String title = itemJson.getString("title");
            String image = itemJson.getString("image");
            String link =  itemJson.getString("link");
            int lprice = itemJson.getInt("lprice");*/
            ItemDto itemDto =  new ItemDto( itemJson);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;

    }
    
  /*  
     public static void main(String[] args) {
        NaverShopSearch naverShopSearch = new NaverShopSearch();
        String result = naverShopSearch.search("adidas");
        System.out.println("result =" + result);
        List<ItemDto> itemDtoList =naverShopSearch.fromJSOntoItems(result); 
    }*/ 
}
