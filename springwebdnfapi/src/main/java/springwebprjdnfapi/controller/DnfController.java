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
import springwebprjdnfapi.main.DnfDTO;
import springwebprjdnfapi.main.HealthDTO;
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

	//Api api = new Api();
	
//	@ModelAttribute("loginTypes")
//	protected List<String> referenceData() throws Exception {
//		List<String> loginTypes = new ArrayList<String>();
//		loginTypes.add("�Ϲ�ȸ��");
//		loginTypes.add("���ȸ��");
//		loginTypes.add("�������ȸ��");
//		return loginTypes;
//	}
	
	Test test20 = ctx.getBean(Test.class);
	MemberRegistRequest mrr = ctx.getBean(MemberRegistRequest.class);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//Api api = new Api();
	
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

		model.addAttribute("testtest", api.searchId(request.getParameter("server"), request.getParameter("id")));
		model.addAttribute("testtest2", api.searchId("prey", "닷지닷지닷지"));
		model.addAttribute("testtest3", api.searchId("prey", "조지조지조지"));
		model.addAttribute("testtest4", api.searchId("prey", "중화기따위"));
		model.addAttribute("testtest5", api.searchId("prey", "채찍질앗흥♥"));
		model.addAttribute("testtest6", api.searchId("prey", "극한의경지"));
		//model.addAttribute("timeline", api.searchTimeline("prey", "e1e49bd4a4e8f9b3a3f7022f1460bd99"));
		model.addAttribute("timeline", api.searchTimeline("prey", "aff735f07862974f704f0543f8e7270a")+api.searchTimeline2("prey", "aff735f07862974f704f0543f8e7270a")+api.searchTimeline3("prey", "aff735f07862974f704f0543f8e7270a")+api.searchTimeline4("prey", "aff735f07862974f704f0543f8e7270a")+api.searchTimeline5("prey", "aff735f07862974f704f0543f8e7270a"));
		model.addAttribute("timeline2", api.searchTimeline("prey", "4caff656a4cd3c1cb174376bbe66a46e")+api.searchTimeline2("prey", "4caff656a4cd3c1cb174376bbe66a46e")+api.searchTimeline3("prey", "4caff656a4cd3c1cb174376bbe66a46e")+api.searchTimeline4("prey", "4caff656a4cd3c1cb174376bbe66a46e")+api.searchTimeline5("prey", "4caff656a4cd3c1cb174376bbe66a46e"));
		model.addAttribute("timeline3", api.searchTimeline("prey", "9a354d1188ffbf722f29cccbb7a1c81c")+api.searchTimeline2("prey", "9a354d1188ffbf722f29cccbb7a1c81c")+api.searchTimeline3("prey", "9a354d1188ffbf722f29cccbb7a1c81c")+api.searchTimeline4("prey", "9a354d1188ffbf722f29cccbb7a1c81c")+api.searchTimeline5("prey", "9a354d1188ffbf722f29cccbb7a1c81c"));
		model.addAttribute("timeline4", api.searchTimeline("prey", "5943d489ee7c1f86482fbeb413b00f44")+api.searchTimeline2("prey", "5943d489ee7c1f86482fbeb413b00f44")+api.searchTimeline3("prey", "5943d489ee7c1f86482fbeb413b00f44")+api.searchTimeline4("prey", "5943d489ee7c1f86482fbeb413b00f44")+api.searchTimeline5("prey", "5943d489ee7c1f86482fbeb413b00f44"));
		model.addAttribute("timeline5", api.searchTimeline("prey", "fc345216453277fe110a6df4113a8a83")+api.searchTimeline2("prey", "fc345216453277fe110a6df4113a8a83")+api.searchTimeline3("prey", "fc345216453277fe110a6df4113a8a83")+api.searchTimeline4("prey", "fc345216453277fe110a6df4113a8a83")+api.searchTimeline5("prey", "fc345216453277fe110a6df4113a8a83"));
		model.addAttribute("timeline6", api.searchTimeline("prey", "44d23031895e97b03ba3625149c98fb5")+api.searchTimeline2("prey", "44d23031895e97b03ba3625149c98fb5")+api.searchTimeline3("prey", "44d23031895e97b03ba3625149c98fb5")+api.searchTimeline4("prey", "44d23031895e97b03ba3625149c98fb5")+api.searchTimeline5("prey", "44d23031895e97b03ba3625149c98fb5"));
		return "dnftest2";
	}

	@RequestMapping("dnftestinput")
	public String dnftestinput(HttpServletRequest request, Model model) {
		
		return "dnftestinput";
	}
	
}
