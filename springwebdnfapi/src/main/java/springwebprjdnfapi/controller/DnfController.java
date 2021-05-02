package springwebprjdnfapi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.PreparedStatement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import springwebprjdnfapi.main.Config;
import springwebprjdnfapi.main.DBDTO;
import springwebprjdnfapi.main.DnfDTO;
import springwebprjdnfapi.main.MemberRegistRequest;
import springwebprjdnfapi.main.Test;
import springwebprjdnfapi.test.Api;



@Controller
@RequestMapping("/dnf/")
public class DnfController {

	@Autowired
	BasicDataSource dataSource;

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
	Api api = ctx.getBean(Api.class);
	
	Test test20 = ctx.getBean(Test.class);
	MemberRegistRequest mrr = ctx.getBean(MemberRegistRequest.class);
	
	DBDTO dbdto = new DBDTO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//Api api = new Api();

	
	//yangid = {api.searchname("prey", "체이서배메"),api.searchname("prey", "BUFF갑니닷")};
	int[] yangtimeline = {api.searchTimeline("prey", api.searchcharacterId("prey", "체이서배메")),api.searchTimeline("prey", api.searchcharacterId("prey", "Buff갑니닷"))};
	//songid = {api.searchname("prey", "닷지닷지닷지"), api.searchname("prey", "버프닷지")};
	int[] songtimeline = {api.searchTimeline("prey", api.searchcharacterId("prey", "닷지닷지닷지")),api.searchTimeline("prey", api.searchcharacterId("prey", "버프닷지"))};
	
	//zoziid = {api.searchname("prey", "조지조지조지") , api.searchname("prey", "도와조지1") };
	//int[] zozitimeline = {api.tlall("prey", api.searchcharacterId("prey", "조지조지조지")),api.tlall("prey", api.searchcharacterId("prey", "도와조지1"))};
	int[] zozitimeline = {api.searchTimeline("prey", api.searchcharacterId("prey", "조지조지조지")),api.searchTimeline("prey", api.searchcharacterId("prey", "도와조지1"))};
	//sungid = {api.searchname("prey", "중화기따위") , api.searchname("prey", "하읏너무굵어") };
	//int[] sungtimeline = {api.tlall("prey", api.searchcharacterId("prey", "중화기따위")),api.tlall("prey", api.searchcharacterId("prey", "하읏너무굵어"))};
	int[] sungtimeline = {api.searchTimeline("prey", api.searchcharacterId("prey", "중화기따위")),api.searchTimeline("prey", api.searchcharacterId("prey", "하읏너무굵어"))};
	//anid = {api.searchname("prey", "채찍질앗흥♥") , api.searchname("prey", "야생에반젤") };
	//int[] antimeline = {api.tlall("prey", api.searchcharacterId("prey", "채찍질앗흥♥")),api.tlall("prey", api.searchcharacterId("prey", "야생에반젤"))};
	int[] antimeline = {api.searchTimeline("prey", api.searchcharacterId("prey", "채찍질앗흥♥")),api.searchTimeline("prey", api.searchcharacterId("prey", "야생에반젤"))};
	//chaid = {api.searchname("prey", "극한의경지") , api.searchname("prey", "변치않는푸름") };
	//int[] chatimeline = {api.tlall("prey", api.searchcharacterId("prey", "극한의경지")),api.tlall("prey", api.searchcharacterId("prey", "변치않는푸름"))};
	int[] chatimeline = {api.searchTimeline("prey", api.searchcharacterId("prey", "극한의경지")),api.searchTimeline("prey", api.searchcharacterId("prey", "변치않는푸름"))};
	
	@RequestMapping("dnftest")
	public String dnftest(HttpServletRequest request, Model model) {
		try {
			//String serverId = reserverId;
			//String characterName = recharacterName;
			String serverId = "prey";
			String characterName = "%ec%9e%98%ed%95%a0%ea%b2%8c%ec%97%ac";
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
			//String[] test = new String[2];
			System.out.println("�׽�Ʈ? : " + response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray jArray = myResponse.getJSONArray("rows");
			
			String parseserverId = jArray.getJSONObject(0).getString("serverId");
			String parsecharacterName = jArray.getJSONObject(0).getString("characterName");
			String parsejobName = jArray.getJSONObject(0).getString("jobName");
			String parsejobGrowName = jArray.getJSONObject(0).getString("jobGrowName");
			String parsecharacterId = jArray.getJSONObject(0).getString("characterId");
			//System.out.println("serverId : " + serverId);
			//System.out.println("characterName : " + charactername);
			ArrayList<DnfDTO> dd = new ArrayList<DnfDTO>();
			dd.add(new DnfDTO(parseserverId, parsecharacterName, parsejobName, parsejobGrowName, parsecharacterId));
			model.addAttribute("dd", dd);
			String[] ts500 = new String[] {parseserverId, parsecharacterName, parsejobName, parsejobGrowName, parsecharacterId};
			//System.out.println("�׽�Ʈ2 :" + ts500[0]);
			model.addAttribute("ts500", ts500);
			return "dnftest";
		} catch(Exception e) {
			System.out.println("?���? : " + e);
		}

		return "dnftest";
	}
	
	@RequestMapping("dnftest2")
	public String dnftest2(HttpServletRequest request, Model model) {
		
		//int[] yangtimeline = {api.tlall("prey", api.searchcharacterId("prey", "체이서배메")),api.tlall("prey", api.searchcharacterId("prey", "BUFF갑니닷"))};
		//yangid[yangid.length] = api.searchname("prey", "잘할게여");
		//yangtimeline[yangtimeline.length] = api.searchTimeline("prey", api.searchcharacterId("prey", "잘할게여"));
		/*
		 * for(int i = 0; i<dbdto.yangid.length; i++) {
		 * model.addAttribute("yangid"+i+"",dbdto.yangid[i]);
		 * //model.addAttribute("yangtimeline"+i+"",yangtimeline[i]);
		 * System.out.println(dbdto.yangid[i]); //System.out.println(yangtimeline[i]); }
		 */
		model.addAttribute("yangid", dbdto.yangid);
		model.addAttribute("yangtl",dbdto.yangtimeline);
		
		model.addAttribute("songid", dbdto.songid);
		model.addAttribute("songtl",dbdto.songtimeline);
		
		model.addAttribute("zoziid", dbdto.zoziid);
		model.addAttribute("zozitl",dbdto.zozitimeline);
		
		model.addAttribute("sungid", dbdto.sungid);
		model.addAttribute("sungtl",dbdto.sungtimeline);
		
		model.addAttribute("anid", dbdto.anid);
		model.addAttribute("antl",dbdto.antimeline);
		
		model.addAttribute("chaid", dbdto.chaid);
		model.addAttribute("chatl",dbdto.chatimeline);
		//int[] songtimeline = {api.tlall("prey", api.searchcharacterId("prey", "닷지닷지닷지")),api.tlall("prey", api.searchcharacterId("prey", "버프닷지"))};
		
		/*
		 * for(int i = 0; i<dbdto.songid.length; i++) {
		 * model.addAttribute("songid"+i+"",dbdto.songid[i]);
		 * //model.addAttribute("songtimeline"+i+"",songtimeline[i]); }
		 */
		
		
		/*
		 * for(int i = 0; i<dbdto.zoziid.length; i++) {
		 * model.addAttribute("zoziid"+i+"",dbdto.zoziid[i]);
		 * //model.addAttribute("zozitimeline"+i+"",zozitimeline[i]); }
		 */
		
		
		/*
		 * for(int i = 0; i<dbdto.sungid.length; i++) {
		 * model.addAttribute("sungid"+i+"",dbdto.sungid[i]);
		 * //model.addAttribute("sungtimeline"+i+"",sungtimeline[i]); }
		 */
		
		
		/*
		 * for(int i = 0; i<dbdto.anid.length; i++) {
		 * model.addAttribute("anid"+i+"",dbdto.anid[i]);
		 * //model.addAttribute("antimeline"+i+"",antimeline[i]); }
		 */
		
		
		/*
		 * for(int i = 0; i<dbdto.chaid.length; i++) {
		 * model.addAttribute("chaid"+i+"",dbdto.chaid[i]);
		 * //model.addAttribute("chatimeline"+i+"",chatimeline[i]); }
		 */
		
		return "dnftest2";
	}
	
	@RequestMapping("dnftest3")
	public String dnftest3(HttpServletRequest request, Model model) {

		model.addAttribute("server",request.getParameter("server"));
		model.addAttribute("id",request.getParameter("id"));
		model.addAttribute("timelinedesc", api.searchTimelinedesc(request.getParameter("server"), api.searchcharacterId(request.getParameter("server"), request.getParameter("id"))));
		
		return "dnftest3";
	}

	@RequestMapping("dnftest4")
	public String dnftest4(HttpServletRequest request, Model model) {

		model.addAttribute("cid",request.getParameter("cid"));
		
		
		return "dnftest4";
	}
	
	@RequestMapping("dnftest5")
	public String dnftest5(HttpServletRequest request, Model model) {

		model.addAttribute("cid",request.getParameter("cid"));
		model.addAttribute("id",request.getParameter("id"));
		
		if(request.getParameter("cid").equals("yang")) {
			for(int i =0; i<dbdto.yangid.length; i++) {
				if(dbdto.yangid[i] == null) {
					dbdto.yangid[i] = api.searchname("prey", request.getParameter("id"));
					dbdto.yangtimeline[i] = api.tlall("prey", api.searchcharacterId("prey", request.getParameter("id")));
					break;
				}
			}
		}
		
		return "redirct:/dnftest2";
	}
	
	@RequestMapping("dnftestrank")
	public String dnftestrank(HttpServletRequest request, Model model) {

		//model.addAttribute("testtest", api.searchId(request.getParameter("server"), request.getParameter("id")));
		model.addAttribute("testtest", api.searchId("prey", "체이서배메"));
		model.addAttribute("testtest2", api.searchId("prey", "닷지닷지닷지"));
		model.addAttribute("testtest3", api.searchId("prey", "조지조지조지"));
		model.addAttribute("testtest4", api.searchId("prey", "중화기따위"));
		model.addAttribute("testtest5", api.searchId("prey", "채찍질앗흥♥"));
		model.addAttribute("testtest6", api.searchId("prey", "극한의경지"));
		
		model.addAttribute("timelineall", api.tlall("prey", "e1e49bd4a4e8f9b3a3f7022f1460bd99"));
		model.addAttribute("timelineall2", api.tlall("prey", "4caff656a4cd3c1cb174376bbe66a46e"));
		model.addAttribute("timelineall3", api.tlall("prey", "9a354d1188ffbf722f29cccbb7a1c81c"));
		model.addAttribute("timelineall4", api.tlall("prey", "5943d489ee7c1f86482fbeb413b00f44"));
		model.addAttribute("timelineall5", api.tlall("prey", "fc345216453277fe110a6df4113a8a83"));
		model.addAttribute("timelineall6", api.tlall("prey", "44d23031895e97b03ba3625149c98fb5"));
		
		int array[] = {api.tlall("prey", "e1e49bd4a4e8f9b3a3f7022f1460bd99"), api.tlall("prey", "4caff656a4cd3c1cb174376bbe66a46e"), api.tlall("prey", "9a354d1188ffbf722f29cccbb7a1c81c"),api.tlall("prey", "5943d489ee7c1f86482fbeb413b00f44"),api.tlall("prey", "fc345216453277fe110a6df4113a8a83"),api.tlall("prey", "44d23031895e97b03ba3625149c98fb5")};
		int max = array[0];
		for(int i =0; i<array.length; i++) {
			if(max<array[i]) {
				max = array[i];
			}
		}
		model.addAttribute("maxepic", max);
		return "dnftestrank";
	}
	@RequestMapping("dnftestinput")
	public String dnftestinput(HttpServletRequest request, Model model) {
		
		return "dnftestinput";
	}
	
}
