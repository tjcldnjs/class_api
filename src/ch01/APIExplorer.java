package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExplorer {

	public static void main(String[] args) throws IOException {

		// 순수 자바코드로 (클라이언트 측 코딩)
		// 준비물
		// 1. 서버 측 주소 - 경로
		// 빌더와 버퍼의 차이점 단일스레드안정적으로 빌더 <<
		// http://localhost:8080/test?name=홍길동&age=20
		// http://localhost:8080/test?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=nKyBJEZcM3GoWdbn1K8KN4rV8%2FRtim%2F8nwS8DHjEl1ZRpR2y7%2FmV5POyjOTtScj6N4dPCvXPZFzr83TwrqymbA%3D%3D");

		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode("2024", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("itemCode", "UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8"));

		// URL 객체에서 문자열 경로 넣어서 객체 생성
		// url.openConnection() 데이터 요청 보내기 - 설정하고

		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET"); // 서버에게 자원 요청
		conn.setRequestProperty("Content-type", "application/json");
		// 200 , 실패 404, 405
        System.out.println("Response code: " + conn.getResponseCode());
        
        // 100 ~ 500 의미 (약속)
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
    }
} // end of main


