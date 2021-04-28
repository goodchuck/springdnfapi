package springwebprjdnfapi.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;


public class Api {

	public String[] searchId(String reserverId, String recharacterName){
		try {
			String serverId = reserverId;
			String encodeResult = URLEncoder.encode(recharacterName, "UTF-8");
			String characterName = encodeResult;
			

			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId +"/characters?characterName="+characterName+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jArray = myResponse.getJSONArray("rows");
			
			String parseserverId = jArray.getJSONObject(0).getString("serverId");
			String parsecharacterName = jArray.getJSONObject(0).getString("characterName");
			String parsejobName = jArray.getJSONObject(0).getString("jobName");
			String parsejobGrowName = jArray.getJSONObject(0).getString("jobGrowName");
			String parsecharacterId = jArray.getJSONObject(0).getString("characterId");
			return new String[] {parseserverId, parsecharacterName, parsejobName, parsejobGrowName, parsecharacterId};
		} catch(Exception e) {
			System.out.println("오류" + e);
		}
		return new String[] {"오","류","입","니","다"};
	}
	
	public String[] searchItem(String reserverId, String recharacterId) {
		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/equip/equipment?apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//String[] test = new String[2];
			System.out.println("결과 : " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jArray = myResponse.getJSONArray("equipment");
			
			String parseitemName = jArray.getJSONObject(0).getString("itemName");
			
			
			return new String[] {parseitemName};
		} catch (Exception e) {
			
		}
		return new String[] {"ㅇㅅㅇ"};
	}
	
//	public String[] searchTimeline(String reserverId, String recharacterId) {
//		try {
//			String serverId = reserverId;
//			String characterId = recharacterId;
//			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
//			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
//			conn.setRequestMethod("GET");
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while((inputLine= in.readLine()) != null){
//				response.append(inputLine);
//			}
//			in.close();
//			System.out.println("테스트 1: " + response.toString());
//			JSONObject myResponse = new JSONObject(response.toString());
//			
//			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
//			System.out.println("테스트 2:" + test.toString());
//
//			
//			String parseitemName2 = test.getJSONObject(0).getString("name");
//			int tl = test.length();
//			
//			
//			System.out.println("테스트 3 : " + parseitemName2);
//
//			return new String[] {parseitemName2};
//		} catch (Exception e) {
//			
//		}
//		return new String[] {"ㅇㅅㅇ"};
//	}
	
	public int searchTimeline(String reserverId, String recharacterId) {
		int tl = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			System.out.println("테스트 2:" + test.toString());
			
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl = test.length();
			
			
			System.out.println("테스트 3 : " + parseitemName2);
		} catch (Exception e) {
			
		}
//		try {
//			String serverId = reserverId;
//			String characterId = recharacterId;
//			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20200601T0000&endDate=20200830T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
//			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
//			conn.setRequestMethod("GET");
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while((inputLine= in.readLine()) != null){
//				response.append(inputLine);
//			}
//			in.close();
//			conn.disconnect();
//			System.out.println("테스트 1: " + response.toString());
//			JSONObject myResponse = new JSONObject(response.toString());
//			
//			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
//			System.out.println("테스트 2:" + test.toString());
//			String parseitemName2 = test.getJSONObject(0).getString("name");
//			tl2 = test.length();
//			
//			System.out.println("테스트 3 : " + parseitemName2);
//			
//			
//			
//		} catch (Exception e) {
//			
//		}
//		
//		try {
//			String serverId = reserverId;
//			String characterId = recharacterId;
//			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20200831T0000&endDate=20201129T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
//			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
//			conn.setRequestMethod("GET");
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while((inputLine= in.readLine()) != null){
//				response.append(inputLine);
//			}
//			in.close();
//			conn.disconnect();
//			System.out.println("테스트 1: " + response.toString());
//			JSONObject myResponse = new JSONObject(response.toString());
//			
//			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
//			System.out.println("테스트 2:" + test.toString());
//			
//			String parseitemName2 = test.getJSONObject(0).getString("name");
//			tl3 = test.length();
//			
//			
//			System.out.println("테스트 3 : " + parseitemName2);
//		} catch (Exception e) {
//			
//		}
//		
//		try {
//			String serverId = reserverId;
//			String characterId = recharacterId;
//			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20201130T0000&endDate=20210228T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
//			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
//			conn.setRequestMethod("GET");
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while((inputLine= in.readLine()) != null){
//				response.append(inputLine);
//			}
//			in.close();
//			conn.disconnect();
//			System.out.println("테스트 1: " + response.toString());
//			JSONObject myResponse = new JSONObject(response.toString());
//			
//			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
//			System.out.println("테스트 2:" + test.toString());
//			
//			String parseitemName2 = test.getJSONObject(0).getString("name");
//			tl4 = test.length();
//			
//			
//			System.out.println("테스트 3 : " + parseitemName2);
//		} catch (Exception e) {
//			
//		}
//		
//		try {
//			String serverId = reserverId;
//			String characterId = recharacterId;
//			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20210301T0000&endDate=20210427T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
//			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
//			conn.setRequestMethod("GET");
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while((inputLine= in.readLine()) != null){
//				response.append(inputLine);
//			}
//			in.close();
//			conn.disconnect();
//			System.out.println("테스트 1: " + response.toString());
//			JSONObject myResponse = new JSONObject(response.toString());
//			
//			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
//			System.out.println("테스트 2:" + test.toString());
//			String parseitemName2 = test.getJSONObject(0).getString("name");
//			tl5 = test.length();
//			
//			
//			System.out.println("테스트 3 : " + parseitemName2);
//		} catch (Exception e) {
//			
//		}
		
		return tl;
	}
	
	public int searchTimeline2(String reserverId, String recharacterId) {
		int tl2 = 0;


		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20200601T0000&endDate=20200830T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			System.out.println("테스트 2:" + test.toString());
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl2 = test.length();
			
			System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		

		return tl2;
	}
	
	public int searchTimeline3(String reserverId, String recharacterId) {

		int tl3 = 0;


		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20200831T0000&endDate=20201129T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			System.out.println("테스트 2:" + test.toString());
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl3 = test.length();
			
			System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		
		return tl3;
	}
	
	public int searchTimeline4(String reserverId, String recharacterId) {

		int tl4 = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20201130T0000&endDate=20210228T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			System.out.println("테스트 2:" + test.toString());
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl4 = test.length();
			
			System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		
		return tl4;
	}
	
	public int searchTimeline5(String reserverId, String recharacterId) {
		int tl5 = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,513&startDate=20210301T0000&endDate=20210427T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			System.out.println("테스트 2:" + test.toString());
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl5 = test.length();
			
			System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		
		return tl5;
	}
}
