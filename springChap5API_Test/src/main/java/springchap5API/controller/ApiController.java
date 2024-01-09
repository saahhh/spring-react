package springchap5API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class ApiController {
	// Java로 api 값이 잘 들어오는지 확인하기 위해 만들어 실행하고 연결은 Spring으로 진행한다.
	@GetMapping("/api/data")
	public String getApiData() {

		StringBuilder result = new StringBuilder();

		try {
			String apiUrl = "https://api.vworld.kr/req/data";
			String apiKey = "CCA747C4-3530-312C-8A2A-D194EE1C6D08";
			String size = "10"; // api 파일에 있는 값을 그대로 쓴다.
			String page = "1";
			String type = "xml";
			String dissCd = "1";
			String znCd = "11";

			// UTF-8
			// 추가
			String encodedApiKey = URLEncoder.encode(apiKey, "UTF-8");
			String encodedurl = String.format("$s?apiKey=%s&numOfRows=%s&pageNo=%s&type=%s&dissCd=%s&znCd=%s", apiUrl,
					apiKey, size, page, type, dissCd, znCd);

			// HTTP 연결
			URL url = new URL(encodedurl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 주소 연결이 되면 주소에 작성된 값 가지고오기 위해서
			// RequestMethod("GET") 요청
			connection.setRequestMethod("GET");

			// 응답에 대한 값을 전달
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);

			}
			reader.close();
			connection.disconnect();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result.toString();

	}
}

/*
 * @GetMapping("/api/data") public String getApiData() { String apiUrl =
 * "http://시작하는 api 주소 작성"; String apiKey = "api 키"; String numOfRows= "10"; //
 * api 파일에 있는 값을 그대로 쓴다. String pageNo = "1"; String type = "xml"; String dissCd
 * = "1"; String znCd = "11";
 * 
 * String url = String.format(
 * "$s?apiKey=%s&numOfRows=%s&pageNo=%s&type=%s&dissCd=%s&znCd=%s", apiUrl,
 * apiKey, numOfRows, pageNo, type, dissCd, znCd); RestTemplate resTemplate =
 * new RestTemplate(); String response = resTemplate.getForObject(url,
 * String.class);
 * 
 * return response; }
 * 
 */
