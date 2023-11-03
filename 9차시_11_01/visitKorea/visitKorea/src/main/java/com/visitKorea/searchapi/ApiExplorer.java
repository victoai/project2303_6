//package com.visitKorea.searchapi;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ApiExplorer {
//
//	// 공공데이터에서 제공하는 데이터
//	// 대분류 데이터
//	public String getCategory1API() throws IOException {
//		
//		StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/categoryCode1"); /* URL */
//		urlBuilder.append("?" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); 
//		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("category1", "UTF-8")); 
//		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); 
//		urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=sk0fN5MZZJs6cMbwwsAn%2FpTZblp1SHKLXTLub%2B12Crs2MRm%2FUoq480WTck8TWAFE9V2kQQMOMGfhOiyo4MBw%2BA%3D%3D"); /*Service Key*/
//	
//		URL url = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
//		BufferedReader rd;
//		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//		rd.close();
//		conn.disconnect();
//		System.out.println(sb.toString());
//
//		String result = sb.toString();
//	
//		return result;
//	}
//	
//	// 중분류 데이터
//	public String getCategory2API() throws IOException {
//		
//		StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/categoryCode1"); /* URL */
//		urlBuilder.append("?" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); 
//		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("category1", "UTF-8")); 
//		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); 
//		urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=sk0fN5MZZJs6cMbwwsAn%2FpTZblp1SHKLXTLub%2B12Crs2MRm%2FUoq480WTck8TWAFE9V2kQQMOMGfhOiyo4MBw%2BA%3D%3D"); /*Service Key*/
//	
//		URL url = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
//		BufferedReader rd;
//		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//		rd.close();
//		conn.disconnect();
//		System.out.println(sb.toString());
//
//		String result = sb.toString();
//	
//		return result;
//	}
//	
//	// 그 데이터를 변환
//	public ArrayList<Category1DTO> fromJSONoItems(String result) {
//		JSONObject rjson = new JSONObject(result);
//		System.out.println(rjson);
//
//		JSONObject jsonResult = new JSONObject(result);
//		JSONObject response = jsonResult.getJSONObject("response");
//		JSONObject body = response.getJSONObject("body");
//		JSONObject items = body.getJSONObject("items");
//		JSONArray item = items.getJSONArray("item");
//
//		ArrayList<Category1DTO> list = new ArrayList<>();
//
//		for (int i = 0; i < item.length(); i++) {
//			JSONObject tem = item.getJSONObject(i);
//			System.out.println(tem);
//			
//			String cat1code = tem.getString("code") ;
//			String name = tem.getString("name");
//			
//			Category1DTO itemdto = new Category1DTO();
//			itemdto.setCat1code(cat1code);
//			itemdto.setName(name);
//			
//			list.add(itemdto);
//		}
//		return list;
//	}
//}
