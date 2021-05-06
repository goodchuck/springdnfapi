package springwebprjdnfapi.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import springwebprjdnfapi.main.DnfDTO2;

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
			//System.out.println("테스트 1: " + response.toString());
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
	
	public String searchname(String reserverId, String recharacterName){
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
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jArray = myResponse.getJSONArray("rows");
			
			String parsecharacterName = jArray.getJSONObject(0).getString("characterName");
			return parsecharacterName;
		} catch(Exception e) {
			System.out.println("오류" + e);
		}
		return "";
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
			//System.out.println("결과 : " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jArray = myResponse.getJSONArray("equipment");
			
			String parseitemName = jArray.getJSONObject(0).getString("itemName");
			
			
			return new String[] {parseitemName};
		} catch (Exception e) {
			
		}
		return new String[] {"ㅇㅅㅇ"};
	}
	
	public String searchcharacterId(String reserverId, String recharacterName){
		String returnstring = "";
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
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jArray = myResponse.getJSONArray("rows");
			
			String parsecharacterId = jArray.getJSONObject(0).getString("characterId");
			returnstring = parsecharacterId;
		} catch(Exception e) {
			System.out.println("오류" + e);
		}
		return returnstring;
	}
	
	public ArrayList<DnfDTO2> searchTimelinedesc(String reserverId, String recharacterId) {
		ArrayList<DnfDTO2> hd = new ArrayList<DnfDTO2>();
		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			//System.out.println("테스트 2:" + test.toString());
			for (int i = 0; i<20; i++ ) {
				String name = test.getJSONObject(i).getString("name");
				String date = test.getJSONObject(i).getString("date");
				String itemName = test.getJSONObject(i).getJSONObject("data").getString("itemName");
				hd.add(new DnfDTO2(name, date, itemName));
				//String parseitemName3 = test.getJSONObject(0).getString("name");	
			}
			//System.out.println("테스트 3 : " + parseitemName2);
		} catch (Exception e) {
			
		}
		
		return hd;
	}
	
	
	public int searchTimeline(String reserverId, String recharacterId) {
		int tl = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			//System.out.println("테스트 2:" + test.toString());
			
			
//			for(int i=0; i<=100; i++) {
//			String parseitemName2 = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
//			
//			if(parseitemName2.equals("신화"))
//			System.out.println(parseitemName2);
//			
//			}
			
			tl = test.length();
			
			
			//System.out.println("테스트 3 : " + parseitemName2);
		} catch (Exception e) {
			
		}
	
		return tl;
	}
	
	public int searchsin(String reserverId, String recharacterId) {
		int ts = 0;
		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			
			
			for(int i=0; i<=100; i++) {
			String parseitemName2 = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
			if(parseitemName2.equals("신화")) {
			ts++;
			System.out.println(parseitemName2);
			}
			}
			
			//System.out.println("테스트 3 : " + parseitemName2);
		} catch (Exception e) {
			
		}
	
		return ts;
	}
	
	public String searchsinname(String reserverId, String recharacterId) {
		String itemName = "";
		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			
			
			for(int i=0; i<=100; i++) {
			String itemRarity = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
			if(itemRarity.equals("신화")) {
			itemName = test.getJSONObject(i).getJSONObject("data").getString("itemName");
			System.out.println(itemName);
			}
			}
			
			
			//System.out.println("테스트 3 : " + parseitemName2);
		} catch (Exception e) {
			
		}
	
		return itemName;
	}
	
	public String searchAdName(String reserverId, String recharacterId) {
		String an = "";

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			an = myResponse.getString("adventureName");
			System.out.println(an);
			
		} catch (Exception e) {
			
		}
	
		return an;
	}
	public int searchTimeline2(String reserverId, String recharacterId) {
		int tl2 = 0;
		int tl22 = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20200601T0000&endDate=20200830T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			
			JSONObject next = myResponse.getJSONObject("next");
			String parsenext = next.getString("next");
			
			if(parsenext != null) {
				
				String htmlUrl2 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&next="+ parsenext+"&startDate=20200601T0000&endDate=20200830T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
				HttpURLConnection conn2 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
				conn2.setRequestMethod("GET");
				
				BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				String inputLine2;
				StringBuffer response2 = new StringBuffer();
				while((inputLine2= in.readLine()) != null){
					response2.append(inputLine2);
				}
				in2.close();
				JSONObject myResponse2 = new JSONObject(response2.toString());
				JSONArray test2 = myResponse.getJSONObject("timeline").getJSONArray("rows");
				tl22 = test2.length();
			}
			
			//System.out.println("테스트 2:" + test.toString());
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl2 = test.length() + tl22;
			//System.out.println(tl2);
			//System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		

		return tl2;
	}
	
	public int searchTimeline3(String reserverId, String recharacterId) {

		int tl3 = 0;


		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20200831T0000&endDate=20201129T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270atimeline?limit=100&code=505,504,513,510&startDate=20200831T0000&endDate=20201129T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			//System.out.println("테스트 2:" + test.toString());
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl3 = test.length();
			//System.out.println(tl3);
			//System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		
		return tl3;
	}
	
	public int searchTimeline4(String reserverId, String recharacterId) {

		int tl4 = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20201130T0000&endDate=20210228T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&startDate=20201130T0000&endDate=20210228T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			//System.out.println("테스트 2:" + test.toString());
			String parseitemName2 = test.getJSONObject(0).getString("name");
			tl4 = test.length();
			//System.out.println(tl4);
			//System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		
		return tl4;
	}
	
	public int searchTimeline5(String reserverId, String recharacterId) {
		int tl5 = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20210301T0000&endDate=20210427T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//System.out.println("테스트 1: " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			
			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");
			//System.out.println("테스트 2:" + test.toString());
			//String parseitemName2 = test.getJSONObject(0).getString("name");
			tl5 = test.length();
			//System.out.println(tl5);
			//System.out.println("테스트 3 : " + parseitemName2);	
			
		} catch (Exception e) {
			
		}
		
		return tl5;
	}
	
	public int tlall(String reserverId, String recharacterId) {
		int tlall = 
		searchTimeline2(reserverId,recharacterId)
		+searchTimeline3(reserverId,recharacterId)
		+searchTimeline4(reserverId,recharacterId)
		+searchTimeline5(reserverId,recharacterId);
		return tlall;
	}
	

}
