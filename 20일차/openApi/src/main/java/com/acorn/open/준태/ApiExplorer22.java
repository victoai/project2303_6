package com.acorn.open.준태;

/* Java 1.8 샘플 코드 */

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
public class ApiExplorer22 {

	public static void main(String[] args) throws IOException {
		ApiExplorer22 api = new ApiExplorer22();
		String result = api.testapi();
		ArrayList<PMClass> list = api.fromJSONtoItems(result);
		System.out.println(list);
	}

	public ArrayList<PMClass> fromJSONtoItems(String result) {

		JSONObject rjson = new JSONObject(result);

		JSONObject response = rjson.getJSONObject("response");
		JSONObject body = response.getJSONObject("body");
		JSONArray items = body.getJSONArray("items");

		ArrayList<PMClass> list = new ArrayList<>();
		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.getJSONObject(i);
			//System.out.println(item);
		
			String imageUrl1 = String.valueOf(item.get("imageUrl1"));
			String imageUrl2 = String.valueOf(item.get("imageUrl2"));
			String imageUrl3 = String.valueOf(item.get("imageUrl3"));
			String imageUrl4 = String.valueOf(item.get("imageUrl4"));
			String imageUrl5 = String.valueOf(item.get("imageUrl5"));
			String imageUrl6 = String.valueOf(item.get("imageUrl6"));
			String informCode = item.getString("informCode");
			String informCause = item.getString("informCause");
			String informOverall = item.getString("informOverall");
			String informGrade = item.getString("informGrade");
			String dataTime = item.getString("dataTime");
			String informData = item.getString("informData");
			
			
				
			



			PMClass pm = new PMClass();
			 pm.setImageUrl1( imageUrl1);
			pm.setImageUrl2(imageUrl2);
			pm.setImageUrl3(imageUrl3);    // Object  obj = null , Object  o="stsring"
			pm.setImageUrl4(imageUrl4);    //   .get  
			pm.setImageUrl5(imageUrl5);
			pm.setImageUrl6(imageUrl6);
			pm.setDataTime(dataTime);
			pm.setInformCause(informCause);
			pm.setInformCode(informCode);
			pm.setInformData(informData);
			pm.setInformGrade(informGrade);
			pm.setInformOverall(informOverall);
			list.add(pm);
			
		}
		//System.out.println("ArrayList ==>" + list);
		return list;

	}

	public String testapi() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=tvPBkKwR%2BfcpAYm5Euu5tZpkDl9KNpsIR7JRYrWJAUuZTe329AfOcfTNUV02jN1hEIV3UF9HmHz7ogBOT93bxQ%3D%3D"); /*
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
		System.out.println(sb.toString());
		return sb.toString();
	}
}