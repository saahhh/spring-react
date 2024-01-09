package springchap5API.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @GetMapping(value="/map", produces = "application/json;charset=UTF-8")
    public String getKakaoApplicationFromAddress(@RequestParam("address") String roadFullAddr) {
    	String apiUrl = "http://dapi.kakao.com/v2/local/search/address.json";
    	String apiKey="055c0b47b89c99ddb22be1896a688442";
    	String jsonString = null;
    	
    	try {
    		roadFullAddr = URLEncoder.encode(roadFullAddr, "UTF-8");
    		String addr = apiUrl + "?query=" + roadFullAddr;
    		URL url = new URL(addr);
    		URLConnection conn = url.openConnection();
    		conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);
    		BufferedReader rd = null;
    		rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    		
    		StringBuffer docJson = new StringBuffer();
    		String line;
    		
    		while((line=rd.readLine()) != null) {
    			docJson.append(line);
    		}
    		jsonString = docJson.toString();
    		rd.close();
    		
    	} catch(UnsupportedEncodingException e) {
    		e.printStackTrace();
  
    	} catch(MalformedURLException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return jsonString;
    		
    	
    }
}