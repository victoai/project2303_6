package com.acorn.open.uis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.XML;
/*
 * 	<dependency>
			<groupId>org.json</groupId>
			<artifactId>json</artifactId>
			<version>20180130</version>
		</dependency>
	
 */
public class 여행 {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/TravelSpecialWarningService/getTravelSpecialWarningList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WVaojr6%2F9K3vAhtm%2B%2FZ4IZ10LVr5zebBt0Hl6HJ%2BA4sDH0Pvmw55tG1dHMOV%2BO0H%2Bsf%2BYH0RCpGSfD1nbLV4bQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
         urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("300", "UTF-8")); /*한 페이지 결과 수*/
       //  urlBuilder.append("&" + URLEncoder.encode("countryName","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
       //  urlBuilder.append("&" + URLEncoder.encode("countryEnName","UTF-8") + "=" + URLEncoder.encode("Ghana", "UTF-8")); /*영문 국가명*/
      //  urlBuilder.append("&" + URLEncoder.encode("isoCode1","UTF-8") + "=" + URLEncoder.encode("GHA", "UTF-8")); /*ISO 국가코드(세자리 국가코드)*/
      //  urlBuilder.append("&" + URLEncoder.encode("isoCode2","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
      //  urlBuilder.append("&" + URLEncoder.encode("isoCode3","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
      //  urlBuilder.append("&" + URLEncoder.encode("isoCode4","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
     //   urlBuilder.append("&" + URLEncoder.encode("isoCode5","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
      //  urlBuilder.append("&" + URLEncoder.encode("isoCode6","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
      //  urlBuilder.append("&" + URLEncoder.encode("isoCode7","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
       // urlBuilder.append("&" + URLEncoder.encode("isoCode8","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
      //  urlBuilder.append("&" + URLEncoder.encode("isoCode9","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
       // urlBuilder.append("&" + URLEncoder.encode("isoCode10","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*ISO 국가코드*/
       
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        
        JSONObject  resultJSON = XML.toJSONObject(sb.toString());
        System.out.println( resultJSON);
        
        
    }
}
