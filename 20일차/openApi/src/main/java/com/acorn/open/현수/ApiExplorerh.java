package com.acorn.open.현수;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

 @Component
public class ApiExplorerh {
	public String getApiTest() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=mWZ7ImGJU3yvsfDjy3z2KByK%2BNUSWGsOvnSwAkLAd9wUHt%2BUqyfdBs99HrHnZwK4Z%2BI1mXWhJvPNsx4K31Bc3w%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* xml 또는 json */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수(조회 날짜로 검색 시 사용 안함) */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* 페이지번호(조회 날짜로 검색 시 사용 안함) */
		urlBuilder.append("&" + URLEncoder.encode("searchDate", "UTF-8") + "="
				+ URLEncoder.encode("2023-10-10", "UTF-8")); /* 통보시간 검색(조회 날짜 입력이 없을 경우 한달동안 예보통보 발령 날짜의 리스트 정보를 확인) */
		urlBuilder.append("&" + URLEncoder.encode("InformCode", "UTF-8") + "="
				+ URLEncoder.encode("PM10", "UTF-8")); /* 통보코드검색(PM10, PM25, O3) */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();

	}

	public ArrayList<ItemDTO> fromJsonToItems(String result) {
		JSONObject rjson = new JSONObject(result);
		// System.out.println(rjson);
		JSONObject response = rjson.getJSONObject("response");
		// System.out.println(response);
		JSONObject body = response.getJSONObject("body");
		// System.out.println(body);
		JSONArray items = body.getJSONArray("items");
		

		ArrayList<ItemDTO> list = new ArrayList<>();

		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.getJSONObject(i);
			
			String imageUrl1 =  String.valueOf(item.get("imageUrl1"));  
			String imageUrl2 =  String.valueOf(item.get("imageUrl2"));  
			String imageUrl3 =  String.valueOf(item.get("imageUrl3"));  
			String imageUrl4 =  String.valueOf(item.get("imageUrl4"));  
			String imageUrl5 =  String.valueOf(item.get("imageUrl5"));  
			String imageUrl6 =  String.valueOf(item.get("imageUrl6"));  
	
		
			String informData = item.getString("informData");
			String informGrade = item.getString("informGrade");
			String informCause = item.getString("informCause");

			ItemDTO itemdto = new ItemDTO();
			itemdto.setImageUrl1(imageUrl1);
			itemdto.setImageUrl2(imageUrl2);
			itemdto.setImageUrl3(imageUrl3);
			itemdto.setImageUrl4(imageUrl4);
			itemdto.setImageUrl5(imageUrl5);
			itemdto.setImageUrl6(imageUrl6);
			itemdto.setInformCause(informCause);
			itemdto.setInformData(informData);
			itemdto.setInformGrade(informGrade);

			list.add(itemdto);

		}

		System.out.println("ArrayList==>");
		return list;
	}

	public static void main(String[] args) throws IOException {
		ApiExplorerh api = new ApiExplorerh();
		String result = api.getApiTest();
		System.out.println(api.fromJsonToItems(result));

	}

}