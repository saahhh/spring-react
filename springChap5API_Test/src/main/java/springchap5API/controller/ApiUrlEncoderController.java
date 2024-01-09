package springchap5API.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiUrlEncoderController {
	
	@GetMapping("/api/encoder/data")
	public String allowBasic() {
		StringBuffer result =new StringBuffer();
	
		try {
			StringBuilder urlBuilder = new StringBuilder("api주소");
			urlBuilder.append("?ServiceKey=발급받은서비스키");
			urlBuilder.append("&pageNo=1");
			urlBuilder.append("&numOfRows=");
			urlBuilder.append("&type=json"); //결과로 보여줄 포맷 xml
			
			URL url;
			url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd;
			
			// 만약에 response(응답) 코드가 200보다 크거나 300보다 작을 때 
			// HTTP : 응답 코드가 200 ~ 299 사이는 성공
			//		  응답 코드가 300 ~ 399 사이는 리다이렉션
			
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				//실패했을 때 경우 
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));	
			}
			
			String line;
			while((line = rd.readLine()) != null) {
				result.append(line).append("\n");
			}
			rd.close();
			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
				
	}
}
