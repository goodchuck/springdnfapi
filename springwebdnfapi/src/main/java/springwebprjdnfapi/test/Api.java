package springwebprjdnfapi.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import springwebprjdnfapi.main.CDTO;
import springwebprjdnfapi.main.DnfDTO2;
import springwebprjdnfapi.main.TDTO;

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
	public ArrayList<CDTO> searchItem(String reserverId, String recharacterId) {
		String itemId;
		String itemName;
		ArrayList<CDTO> hd = new ArrayList<CDTO>();
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

			for(int i =0; i<jArray.length(); i++) {
				itemId = jArray.getJSONObject(i).getString("itemId");
				itemName = jArray.getJSONObject(i).getString("itemName");
				hd.add(new CDTO(itemId,itemName));
			}
		} catch (Exception e) {

		}
		return hd;
	}
	public String searchItemId(String itemName) { //아이템 이름 넣어서 id반환하는 함수
		String returnstring;
		try {
			String encodeResult = URLEncoder.encode(itemName, "UTF-8");
			String enitemName = encodeResult;
			
			String htmlUrl = "https://api.neople.co.kr/df/items?itemName="+enitemName+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			
			HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrl).openConnection();
			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine= in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			conn.disconnect();
			//System.out.println("결과 : " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jArray = myResponse.getJSONArray("rows");
			String parseitemId = jArray.getJSONObject(0).getString("itemId");
			returnstring = parseitemId;
			System.out.println("검색된 아이템 :"+returnstring);
			return returnstring;
		} catch (Exception e) {

		}
		return "";
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
		String name,date,itemName,itemRarity,dungeonName,channelName="";
		int channelNo = 0;
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

			for (int i = 0; i<10; i++ ) {
				if(test.getJSONObject(i).getString("name").equals("아이템 획득(지옥 파티)")) {
					name = test.getJSONObject(i).getString("name");
					date = test.getJSONObject(i).getString("date");
					itemName = test.getJSONObject(i).getJSONObject("data").getString("itemName");
					itemRarity = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
					channelName = test.getJSONObject(i).getJSONObject("data").getString("channelName");
					channelNo = test.getJSONObject(i).getJSONObject("data").getInt("channelNo");
					dungeonName = test.getJSONObject(i).getJSONObject("data").getString("dungeonName");
				} else if(test.getJSONObject(i).getString("name").equals("아이템 획득(항아리)")){
					name = test.getJSONObject(i).getString("name");
					date = test.getJSONObject(i).getString("date");
					itemName = test.getJSONObject(i).getJSONObject("data").getString("itemName");
					itemRarity = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
					channelName = test.getJSONObject(i).getJSONObject("data").getString("channelName");
					channelNo = test.getJSONObject(i).getJSONObject("data").getInt("channelNo");
					dungeonName = "";
				} else {
					name = test.getJSONObject(i).getString("name");
					date = test.getJSONObject(i).getString("date");
					itemName = test.getJSONObject(i).getJSONObject("data").getString("itemName");
					itemRarity = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
					channelName ="";
					channelNo = 0;
					dungeonName = test.getJSONObject(i).getJSONObject("data").getString("dungeonName");
				}
				hd.add(new DnfDTO2(name, date, itemName,itemRarity,channelName,channelNo,dungeonName));
			}
		} catch (Exception e) {

		}

		return hd;
	}


	public int searchTimeline(String reserverId, String recharacterId) { //오늘날 기준 30일전까지 불러오는 함수
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

	public int searchsin(String reserverId, String recharacterId) { //신화 가져옴
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
	public ArrayList<TDTO> searchTimeline2020(String reserverId, String recharacterId) {
		int tl2[] = {0,0,0,0,0};
		int tl22[] = {0,0,0,0,0};
		int tl222[] = {0,0,0,0,0};
		int tlall[] = {0,0,0,0,0};
		int tlall2 = 0;
		int ts[] = {0,0,0,0,0};
		int tsall = 0;
		ArrayList<String> sinarray = new ArrayList<String>();
		ArrayList<TDTO> returnarray = new ArrayList<TDTO>();
		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl1 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20200101T0000&endDate=20200331T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl2 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20200401T0000&endDate=20200630T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl3 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20200701T0000&endDate=20200929T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl4 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20200930T0000&endDate=20201229T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl5 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20201230T0000&endDate=20201231T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&startDate=20201230T0000&endDate=20201231T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String[] htmlUrlall = {htmlUrl1,htmlUrl2,htmlUrl3,htmlUrl4,htmlUrl5};
			for(int i =0; i<=4; i++) {
				HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrlall[i]).openConnection();
				conn.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while((inputLine= in.readLine()) != null){
					response.append(inputLine);
				}
				in.close();
				conn.disconnect();
				JSONObject myResponse = new JSONObject(response.toString());

				JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");

				for(int j=0; j<test.length(); j++) {
					String parseitemName2 = test.getJSONObject(j).getJSONObject("data").getString("itemRarity");
					if(parseitemName2.equals("신화")) {
						sinarray.add(test.getJSONObject(j).getJSONObject("data").getString("itemName"));
						System.out.println(test.getJSONObject(j).getJSONObject("data").getString("itemName"));
						ts[i]++;
						//System.out.println(parseitemName2);
					}
				}
				tl2[i] = test.length();
				System.out.println("테스트1 :" +tl2[i]);

				if(!myResponse.getJSONObject("timeline").isNull("next")) { //2번째 검색
					String next = myResponse.getJSONObject("timeline").getString("next");
					System.out.println(next);
					String htmlUrl11 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
					HttpURLConnection conn2 = (HttpURLConnection) new URL(htmlUrl11).openConnection();
					conn2.setRequestMethod("GET");

					BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
					String inputLine2;
					StringBuffer response2 = new StringBuffer();
					while((inputLine2= in2.readLine()) != null){
						response2.append(inputLine2);
					}
					in2.close();
					conn2.disconnect();
					JSONObject myResponse2 = new JSONObject(response2.toString());
					JSONArray test2 = myResponse2.getJSONObject("timeline").getJSONArray("rows");

					for(int j=0; j<test2.length(); j++) {
						String parseitemName2 = test2.getJSONObject(j).getJSONObject("data").getString("itemRarity");
						if(parseitemName2.equals("신화")) {
							sinarray.add(test2.getJSONObject(j).getJSONObject("data").getString("itemName"));
							System.out.println(test2.getJSONObject(j).getJSONObject("data").getString("itemName"));
							ts[i]++;
							//System.out.println(parseitemName2);
						}
					}

					tl22[i] = test2.length();
					System.out.println("테스트2 :" +tl22[i]);

					if(!myResponse2.getJSONObject("timeline").isNull("next")) { //3번째 검색
						String next2 = myResponse2.getJSONObject("timeline").getString("next");
						System.out.println(next2);
						String htmlUrl33 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next2+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
						HttpURLConnection conn3 = (HttpURLConnection) new URL(htmlUrl33).openConnection();
						conn3.setRequestMethod("GET");

						BufferedReader in3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
						String inputLine3;
						StringBuffer response3 = new StringBuffer();
						while((inputLine3= in3.readLine()) != null){
							response3.append(inputLine3);
						}
						in3.close();
						conn3.disconnect();
						JSONObject myResponse3 = new JSONObject(response3.toString());
						JSONArray test3 = myResponse3.getJSONObject("timeline").getJSONArray("rows");

						for(int j=0; j<test3.length(); j++) {
							String parseitemName2 = test3.getJSONObject(j).getJSONObject("data").getString("itemRarity");
							if(parseitemName2.equals("신화")) {
								sinarray.add(test3.getJSONObject(j).getJSONObject("data").getString("itemName"));
								System.out.println(test3.getJSONObject(j).getJSONObject("data").getString("itemName"));
								ts[i]++;
								//System.out.println(parseitemName2);
							}
						}
						tl222[i] = test3.length();
						System.out.println("테스트3 :" +tl222[i]);
					} //3번째 검색
				} //2번째 검색
			tlall[i] = tl2[i] + tl22[i] + tl222[i];
			System.out.println("tlall은 :" +tlall[i]);
			}

		}
		catch (Exception e) {

		}
		tlall2 = tlall[0] + tlall[1] + tlall[2]+tlall[3]+tlall[4];
		tsall = ts[0] + ts[1] + ts[2] + ts[3]+ ts[4];
		System.out.println("tlall2는 "+tlall2);
		System.out.println("sinarray :" + sinarray);

		returnarray.add(new TDTO(tlall2,tsall,sinarray));
		int[] arr = {tlall2, tsall};
		return returnarray;
	}
	
	public ArrayList<TDTO> searchTimeline2021(String reserverId, String recharacterId) {
		int tl2[] = {0,0,0,0,0};
		int tl22[] = {0,0,0,0,0};
		int tl222[] = {0,0,0,0,0};
		int tlall[] = {0,0,0,0,0};
		int tlall2 = 0;
		int ts[] = {0,0,0,0,0};
		int tsall = 0;
		ArrayList<String> sinarray = new ArrayList<String>();
		ArrayList<TDTO> returnarray = new ArrayList<TDTO>();
		String nowdate="";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		if(month < 10) {
		nowdate = ""+year+"0"+month+""+day+"T"+hour+""+min+"";
		} else {
			nowdate = ""+year+""+month+""+day+"T"+hour+""+min+"";	
		}
		System.out.println("검색된 시간은 :"+nowdate);
		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl1 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20210101T0000&endDate=20210331T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl2 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20210401T0000&endDate="+nowdate+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl3 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20210701T0000&endDate=20210929T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl4 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20210930T0000&endDate=20211229T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrl5 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20211230T0000&endDate=20211231T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";

			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&startDate=20210101T0000&endDate=20210331T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String[] htmlUrlall = {htmlUrl1,htmlUrl2,htmlUrl3,htmlUrl4,htmlUrl5};
			for(int i =0; i<=4; i++) {
				HttpURLConnection conn = (HttpURLConnection) new URL(htmlUrlall[i]).openConnection();
				conn.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while((inputLine= in.readLine()) != null){
					response.append(inputLine);
				}
				in.close();
				conn.disconnect();
				JSONObject myResponse = new JSONObject(response.toString());

				JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");

				for(int j=0; j<test.length(); j++) {
					String parseitemName2 = test.getJSONObject(j).getJSONObject("data").getString("itemRarity");
					if(parseitemName2.equals("신화")) {
						sinarray.add(test.getJSONObject(j).getJSONObject("data").getString("itemName"));
						System.out.println(test.getJSONObject(j).getJSONObject("data").getString("itemName"));
						ts[i]++;
						//System.out.println(parseitemName2);
					}
				}
				tl2[i] = test.length();
				System.out.println("테스트1 :" +tl2[i]);

				if(!myResponse.getJSONObject("timeline").isNull("next")) { //2번째 검색
					String next = myResponse.getJSONObject("timeline").getString("next");
					System.out.println(next);
					String htmlUrl11 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
					HttpURLConnection conn2 = (HttpURLConnection) new URL(htmlUrl11).openConnection();
					conn2.setRequestMethod("GET");

					BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
					String inputLine2;
					StringBuffer response2 = new StringBuffer();
					while((inputLine2= in2.readLine()) != null){
						response2.append(inputLine2);
					}
					in2.close();
					conn2.disconnect();
					JSONObject myResponse2 = new JSONObject(response2.toString());
					JSONArray test2 = myResponse2.getJSONObject("timeline").getJSONArray("rows");

					for(int j=0; j<test2.length(); j++) {
						String parseitemName2 = test2.getJSONObject(j).getJSONObject("data").getString("itemRarity");
						if(parseitemName2.equals("신화")) {
							sinarray.add(test2.getJSONObject(j).getJSONObject("data").getString("itemName"));
							System.out.println(test2.getJSONObject(j).getJSONObject("data").getString("itemName"));
							ts[i]++;
							//System.out.println(parseitemName2);
						}
					}

					tl22[i] = test2.length();
					System.out.println("테스트2 :" +tl22[i]);

					if(!myResponse2.getJSONObject("timeline").isNull("next")) { //3번째 검색
						String next2 = myResponse2.getJSONObject("timeline").getString("next");
						System.out.println(next2);
						String htmlUrl33 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next2+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
						HttpURLConnection conn3 = (HttpURLConnection) new URL(htmlUrl33).openConnection();
						conn3.setRequestMethod("GET");

						BufferedReader in3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
						String inputLine3;
						StringBuffer response3 = new StringBuffer();
						while((inputLine3= in3.readLine()) != null){
							response3.append(inputLine3);
						}
						in3.close();
						conn3.disconnect();
						JSONObject myResponse3 = new JSONObject(response3.toString());
						JSONArray test3 = myResponse3.getJSONObject("timeline").getJSONArray("rows");

						for(int j=0; j<test3.length(); j++) {
							String parseitemName2 = test3.getJSONObject(j).getJSONObject("data").getString("itemRarity");
							if(parseitemName2.equals("신화")) {
								sinarray.add(test3.getJSONObject(j).getJSONObject("data").getString("itemName"));
								System.out.println(test3.getJSONObject(j).getJSONObject("data").getString("itemName"));
								ts[i]++;
								//System.out.println(parseitemName2);
							}
						}
						tl222[i] = test3.length();
						System.out.println("테스트3 :" +tl222[i]);
					} //3번째 검색
				} //2번째 검색
			tlall[i] = tl2[i] + tl22[i] + tl222[i];
			System.out.println("tlall은 :" +tlall[i]);
			}

		}
		catch (Exception e) {

		}
		tlall2 = tlall[0] + tlall[1] + tlall[2] + tlall[3] + tlall[4];
		tsall = ts[0] + ts[1]+ts[2]+ts[3]+ts[4];
		System.out.println("tlall2는 "+tlall2);
		System.out.println("sinarray :" + sinarray);
		returnarray.add(new TDTO(tlall2,tsall,sinarray));
		int[] arr = {tlall2, tsall};
		return returnarray;
	}
	
	
	public int[] searchTimeline2(String reserverId, String recharacterId) {
		int tl2 = 0;
		int tl22 = 0;
		int tl222 = 0;
		int ts = 0;

		try {
			String serverId = reserverId;
			String characterId = recharacterId;
			String htmlUrl = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?limit=100&code=505,504,513,510&startDate=20200601T0000&endDate=20200830T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
			String htmlUrltest = "https://api.neople.co.kr/df/servers/prey/characters/aff735f07862974f704f0543f8e7270a/timeline?limit=100&code=505,504,513,510&startDate=20200601T0000&endDate=20200830T2359&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";

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

			for(int i=0; i<test.length(); i++) {
				String parseitemName2 = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
				if(parseitemName2.equals("신화")) {

					ts++;
					System.out.println(parseitemName2);
				}
			}

			if(!myResponse.getJSONObject("timeline").isNull("next")) {
				String next = myResponse.getJSONObject("timeline").getString("next");
				System.out.println(next);
				String htmlUrl2 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
				HttpURLConnection conn2 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
				conn2.setRequestMethod("GET");

				BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				String inputLine2;
				StringBuffer response2 = new StringBuffer();
				while((inputLine2= in2.readLine()) != null){
					response2.append(inputLine2);
				}
				in2.close();
				JSONObject myResponse2 = new JSONObject(response2.toString());
				JSONArray test2 = myResponse2.getJSONObject("timeline").getJSONArray("rows");

				for(int i=0; i<test2.length(); i++) {
					String parseitemName2 = test2.getJSONObject(i).getJSONObject("data").getString("itemRarity");
					if(parseitemName2.equals("신화")) {

						ts++;
						System.out.println(parseitemName2);
					}
				}

				tl22 = test2.length();
				System.out.println(tl22);
				conn2.disconnect();

				if(!myResponse2.getJSONObject("timeline").isNull("next")) {
					String next2 = myResponse2.getJSONObject("timeline").getString("next");
					System.out.println(next2);
					String htmlUrl3 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
					HttpURLConnection conn3 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
					conn3.setRequestMethod("GET");

					BufferedReader in3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
					String inputLine3;
					StringBuffer response3 = new StringBuffer();
					while((inputLine3= in3.readLine()) != null){
						response3.append(inputLine3);
					}
					in3.close();
					JSONObject myResponse3 = new JSONObject(response3.toString());
					JSONArray test3 = myResponse3.getJSONObject("timeline").getJSONArray("rows");

					for(int i=0; i<test3.length(); i++) {
						String parseitemName2 = test3.getJSONObject(i).getJSONObject("data").getString("itemRarity");
						if(parseitemName2.equals("신화")) {

							ts++;
							System.out.println(parseitemName2);
						}
					}
					tl222 = test3.length();
					System.out.println(tl22);
					conn3.disconnect();
				}
			}


			tl2 = test.length()+ tl22 + tl222;
			conn.disconnect();
		} catch (Exception e) {

		}

		int[] arr = {tl2, ts};
		return arr;
	}

	public int[] searchTimeline3(String reserverId, String recharacterId) {

		int tl3 = 0;
		int tl33 = 0;
		int tl333 = 0;
		int ts = 0;
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
			JSONObject myResponse = new JSONObject(response.toString());

			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");

			for(int i=0; i<test.length(); i++) {
				String parseitemName2 = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
				if(parseitemName2.equals("신화")) {

					ts++;
					System.out.println(parseitemName2);
				}
			}

			if(!myResponse.getJSONObject("timeline").isNull("next")) {
				String next = myResponse.getJSONObject("timeline").getString("next");
				System.out.println(next);
				String htmlUrl2 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
				HttpURLConnection conn2 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
				conn2.setRequestMethod("GET");

				BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				String inputLine2;
				StringBuffer response2 = new StringBuffer();
				while((inputLine2= in2.readLine()) != null){
					response2.append(inputLine2);
				}
				in2.close();
				JSONObject myResponse2 = new JSONObject(response2.toString());
				JSONArray test2 = myResponse2.getJSONObject("timeline").getJSONArray("rows");

				for(int i=0; i<test2.length(); i++) {
					String parseitemName2 = test2.getJSONObject(i).getJSONObject("data").getString("itemRarity");
					if(parseitemName2.equals("신화")) {

						ts++;
						System.out.println(parseitemName2);
					}
				}

				tl33 = test2.length();
				System.out.println(tl33);
				conn2.disconnect();

				if(!myResponse2.getJSONObject("timeline").isNull("next")) {
					String next2 = myResponse2.getJSONObject("timeline").getString("next");
					System.out.println(next2);
					String htmlUrl3 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
					HttpURLConnection conn3 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
					conn3.setRequestMethod("GET");

					BufferedReader in3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
					String inputLine3;
					StringBuffer response3 = new StringBuffer();
					while((inputLine3= in3.readLine()) != null){
						response3.append(inputLine3);
					}
					in3.close();
					JSONObject myResponse3 = new JSONObject(response3.toString());
					JSONArray test3 = myResponse3.getJSONObject("timeline").getJSONArray("rows");

					for(int i=0; i<test3.length(); i++) {
						String parseitemName2 = test3.getJSONObject(i).getJSONObject("data").getString("itemRarity");
						if(parseitemName2.equals("신화")) {

							ts++;
							System.out.println(parseitemName2);
						}
					}
					tl333 = test3.length();
					System.out.println(tl333);
					conn3.disconnect();
				}
			}
			tl3 = test.length()+ tl33 + tl333;
			conn.disconnect();
		} catch (Exception e) {

		}

		int[] arr = {tl3, ts};
		return arr;
	}

	public int[] searchTimeline4(String reserverId, String recharacterId) {

		int tl4 = 0;
		int tl44 = 0;
		int tl444 = 0;
		int ts = 0;
		int arr[] = new int[2];
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
			JSONObject myResponse = new JSONObject(response.toString());

			JSONArray test = myResponse.getJSONObject("timeline").getJSONArray("rows");

			for(int i=0; i<test.length(); i++) {
				String parseitemName2 = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
				if(parseitemName2.equals("신화")) {

					ts++;
					System.out.println(parseitemName2);
				}
			}

			if(!myResponse.getJSONObject("timeline").isNull("next")) {
				String next = myResponse.getJSONObject("timeline").getString("next");
				System.out.println(next);
				String htmlUrl2 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
				HttpURLConnection conn2 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
				conn2.setRequestMethod("GET");

				BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				String inputLine2;
				StringBuffer response2 = new StringBuffer();
				while((inputLine2= in2.readLine()) != null){
					response2.append(inputLine2);
				}
				in2.close();
				JSONObject myResponse2 = new JSONObject(response2.toString());
				JSONArray test2 = myResponse2.getJSONObject("timeline").getJSONArray("rows");

				for(int i=0; i<test2.length(); i++) {
					String parseitemName2 = test2.getJSONObject(i).getJSONObject("data").getString("itemRarity");

					if(parseitemName2.equals("신화")) {

						ts++;
						System.out.println(parseitemName2);
					}
				}

				tl44 = test2.length();
				System.out.println(tl44);
				conn2.disconnect();

				if(!myResponse2.getJSONObject("timeline").isNull("next")) {
					String next2 = myResponse2.getJSONObject("timeline").getString("next");
					System.out.println(next2);
					String htmlUrl3 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next2+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
					HttpURLConnection conn3 = (HttpURLConnection) new URL(htmlUrl3).openConnection();
					conn3.setRequestMethod("GET");

					BufferedReader in3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
					String inputLine3;
					StringBuffer response3 = new StringBuffer();
					while((inputLine3= in3.readLine()) != null){
						response3.append(inputLine3);
					}
					in3.close();
					JSONObject myResponse3 = new JSONObject(response3.toString());
					JSONArray test3 = myResponse3.getJSONObject("timeline").getJSONArray("rows");

					for(int i=0; i<test3.length(); i++) {
						String parseitemName2 = test3.getJSONObject(i).getJSONObject("data").getString("itemRarity");
						if(parseitemName2.equals("신화")) {

							ts++;
							System.out.println(parseitemName2);
						}
					}

					tl444 = test3.length();
					System.out.println(tl444);
					conn3.disconnect();

				}
			}
			tl4 = test.length()+ tl44 + tl444;
			conn.disconnect();
			arr[0] = tl4;
			arr[1] = ts;
		} catch (Exception e) {

		}
		return arr;
	}

	public int[] searchTimeline5(String reserverId, String recharacterId) {
		int tl5 = 0;
		int tl55 = 0;
		int tl555 = 0;
		int ts = 0;
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

			for(int i=0; i<test.length(); i++) {
				String parseitemName2 = test.getJSONObject(i).getJSONObject("data").getString("itemRarity");
				if(parseitemName2.equals("신화")) {

					ts++;
					System.out.println(parseitemName2);
				}
			}

			if(!myResponse.getJSONObject("timeline").isNull("next")) {
				String next = myResponse.getJSONObject("timeline").getString("next");
				System.out.println(next);
				String htmlUrl2 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
				HttpURLConnection conn2 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
				conn2.setRequestMethod("GET");

				BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				String inputLine2;
				StringBuffer response2 = new StringBuffer();
				while((inputLine2= in2.readLine()) != null){
					response2.append(inputLine2);
				}
				in2.close();
				JSONObject myResponse2 = new JSONObject(response2.toString());
				JSONArray test2 = myResponse2.getJSONObject("timeline").getJSONArray("rows");

				for(int i=0; i<test2.length(); i++) {
					String parseitemName2 = test2.getJSONObject(i).getJSONObject("data").getString("itemRarity");
					if(parseitemName2.equals("신화")) {

						ts++;
						System.out.println(parseitemName2);
					}
				}

				tl55 = test2.length();
				System.out.println(tl55);
				conn2.disconnect();

				if(!myResponse2.getJSONObject("timeline").isNull("next")) {
					String next2 = myResponse2.getJSONObject("timeline").getString("next");
					System.out.println(next2);
					String htmlUrl3 = "https://api.neople.co.kr/df/servers/"+serverId+"/characters/"+characterId+"/timeline?next="+next+"&apikey=oMDk2YvEtfIzJG8SfXLWDZ3km3J1pKu6";
					HttpURLConnection conn3 = (HttpURLConnection) new URL(htmlUrl2).openConnection();
					conn3.setRequestMethod("GET");

					BufferedReader in3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
					String inputLine3;
					StringBuffer response3 = new StringBuffer();
					while((inputLine3= in3.readLine()) != null){
						response3.append(inputLine3);
					}
					in3.close();
					JSONObject myResponse3 = new JSONObject(response3.toString());
					JSONArray test3 = myResponse3.getJSONObject("timeline").getJSONArray("rows");

					for(int i=0; i<test3.length(); i++) {
						String parseitemName2 = test3.getJSONObject(i).getJSONObject("data").getString("itemRarity");
						if(parseitemName2.equals("신화")) {

							ts++;
							System.out.println(parseitemName2);
						}
					}

					tl555 = test3.length();
					System.out.println(tl555);
					conn3.disconnect();
				}
			}
			tl5 = test.length()+ tl55 + tl555;
			conn.disconnect();


		} catch (Exception e) {

		}
		int[] arr = {tl5, ts};
		return arr;
	}


}
