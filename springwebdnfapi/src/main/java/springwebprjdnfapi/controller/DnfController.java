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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.sql.PreparedStatement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import springwebprjdnfapi.main.Config;
import springwebprjdnfapi.main.DBDTO;
import springwebprjdnfapi.main.DnfDTO;
import springwebprjdnfapi.main.Epiccount;
import springwebprjdnfapi.main.MemberRegistRequest;
import springwebprjdnfapi.main.Test;
import springwebprjdnfapi.test.Api;



@Controller
@RequestMapping("/dnf/")
public class DnfController {

	@Autowired
	ComboPooledDataSource dataSource;
	//BasicDataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public DnfController(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public int update(String cid, int ecount, int scount) {
		return jdbcTemplate.update("update epiccount set ecount = ?,scount=? where cid = ?",ecount,scount,cid);
	}
	public int delete(String cid) {
		return jdbcTemplate.update("delete from epiccount where cid = ?",cid);
	}
	public int cinsert(String cid, int ecount, int scount) {
		return jdbcTemplate.update("insert into epiccount (cid, ecount, scount) values(?,?,?)",cid,ecount,scount);
	}
	public int userjoin(String id, String pw, String name, String gender, String email) {
		return jdbcTemplate.update("insert into user values (?,?,?,?,?)",id,pw,name,gender,email);
	}
	//GenericXmlApplicationContext ctx2 = new GenericXmlApplicationContext("classpath:config.xml");
	
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
	Api api = ctx.getBean(Api.class);
	
	Test test20 = ctx.getBean(Test.class);
	MemberRegistRequest mrr = ctx.getBean(MemberRegistRequest.class);
	
	DBDTO dbdto = ctx.getBean(DBDTO.class);
	//DBDTO dbdto = new DBDTO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	Date now = new Date(); 
	String now_dt = format.format(now);


	
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
	
	@RequestMapping("dnfdibol")
	public String dnfdibol(HttpServletRequest request, Model model) {
		
		int yangsum = 0;
		int songsum = 0;
		int zozisum = 0;
		int sungsum = 0;
		int ansum = 0;
		int chasum = 0;
		for(int i =0; i<10; i++) {
			yangsum += dbdto.yangtimeline[i];
			songsum += dbdto.songtimeline[i];
			zozisum += dbdto.zozitimeline[i];
			sungsum += dbdto.sungtimeline[i];
			ansum += dbdto.antimeline[i];
			chasum += dbdto.chatimeline[i];
		}
		model.addAttribute("nowtime", now_dt);
		model.addAttribute("yangid", dbdto.yangid);
		model.addAttribute("yangtl",dbdto.yangtimeline);
		model.addAttribute("yangsin",dbdto.yangsin);
		
		
		model.addAttribute("songid", dbdto.songid);
		model.addAttribute("songtl",dbdto.songtimeline);
		model.addAttribute("songsin",dbdto.songsin);
		
		model.addAttribute("zoziid", dbdto.zoziid);
		model.addAttribute("zozitl",dbdto.zozitimeline);
		model.addAttribute("zozisin",dbdto.zozisin);
		
		model.addAttribute("sungid", dbdto.sungid);
		model.addAttribute("sungtl",dbdto.sungtimeline);
		model.addAttribute("sungsin",dbdto.sungsin);
		
		model.addAttribute("anid", dbdto.anid);
		model.addAttribute("antl",dbdto.antimeline);
		model.addAttribute("ansin",dbdto.ansin);
		
		model.addAttribute("chaid", dbdto.chaid);
		model.addAttribute("chatl",dbdto.chatimeline);
		model.addAttribute("chasin",dbdto.chasin);
		
		return "dnfdibol";
	}
	
	@RequestMapping("dnftest3")
	public String dnftest3(HttpServletRequest request, Model model) {

		model.addAttribute("server",request.getParameter("server"));
		model.addAttribute("id",request.getParameter("id"));
		model.addAttribute("timelinedesc", api.searchTimelinedesc(request.getParameter("server"), api.searchcharacterId(request.getParameter("server"), request.getParameter("id"))));
		
		return "dnftest3";
	}

	@RequestMapping("dnfcinsertform")
	public String dnfcinsertform(HttpServletRequest request, Model model) {

		model.addAttribute("cid",request.getParameter("cid"));
		
		
		return "dnfcinsertform";
	}
	
	@RequestMapping("cinsert")
	public String cinsert(HttpServletRequest request, Model model) {

		model.addAttribute("cid",request.getParameter("cid"));
		model.addAttribute("id",request.getParameter("id"));
		
		if(request.getParameter("cid").equals("yang")) {
			for(int i =0; i<dbdto.yangid.length; i++) {
				if(dbdto.yangid[i] == null) {
					dbdto.yangid[i] = api.searchname("prey", request.getParameter("id"));
					dbdto.yangtimeline[i] = api.searchTimeline("prey", api.searchcharacterId("prey", request.getParameter("id")));
					dbdto.yangsin[i] = api.searchsin("prey", api.searchcharacterId("prey", request.getParameter("id")));
					break;
				}
			}
		} else if(request.getParameter("cid").equals("song")) {
			for(int i =0; i<dbdto.songid.length; i++) {
				if(dbdto.songid[i] == null) {
					dbdto.songid[i] = api.searchname("prey", request.getParameter("id"));
					dbdto.songtimeline[i] = api.searchTimeline("prey", api.searchcharacterId("prey", request.getParameter("id")));
					dbdto.songsin[i] = api.searchsin("prey", api.searchcharacterId("prey", request.getParameter("id")));
					break;
				}
			}
		} else if(request.getParameter("cid").equals("zozi")) {
			for(int i =0; i<dbdto.zoziid.length; i++) {
				if(dbdto.zoziid[i] == null) {
					dbdto.zoziid[i] = api.searchname("prey", request.getParameter("id"));
					dbdto.zozitimeline[i] = api.searchTimeline("prey", api.searchcharacterId("prey", request.getParameter("id")));
					dbdto.zozisin[i] = api.searchsin("prey", api.searchcharacterId("prey", request.getParameter("id")));
					break;
				}
			}
		} else if(request.getParameter("cid").equals("sung")) {
			for(int i =0; i<dbdto.sungid.length; i++) {
				if(dbdto.sungid[i] == null) {
					dbdto.sungid[i] = api.searchname("prey", request.getParameter("id"));
					dbdto.sungtimeline[i] = api.searchTimeline("prey", api.searchcharacterId("prey", request.getParameter("id")));
					dbdto.sungsin[i] = api.searchsin("prey", api.searchcharacterId("prey", request.getParameter("id")));
					break;
				}
			}
		} else if(request.getParameter("cid").equals("an")) {
			for(int i =0; i<dbdto.anid.length; i++) {
				if(dbdto.anid[i] == null) {
					dbdto.anid[i] = api.searchname("prey", request.getParameter("id"));
					dbdto.antimeline[i] = api.searchTimeline("prey", api.searchcharacterId("prey", request.getParameter("id")));
					dbdto.ansin[i] = api.searchsin("prey", api.searchcharacterId("prey", request.getParameter("id")));
					break;
				}
			}
		} else if(request.getParameter("cid").equals("cha")) {
			for(int i =0; i<dbdto.chaid.length; i++) {
				if(dbdto.chaid[i] == null) {
					dbdto.chaid[i] = api.searchname("prey", request.getParameter("id"));
					dbdto.chatimeline[i] = api.searchTimeline("prey", api.searchcharacterId("prey", request.getParameter("id")));
					dbdto.chasin[i] = api.searchsin("prey", api.searchcharacterId("prey", request.getParameter("id")));
					break;
				}
			}
		} 
		
		return "redirect:/dnf/dnfdibol";
	}
	
	@RequestMapping("cdelete")
	public String cdelete(HttpServletRequest request, Model model) {
		delete(request.getParameter("cid"));
//		if(request.getParameter("cid").equals("yang")) {
//			for(int i=0; i<=10; i++) {
//			if(dbdto.yangid[i].equals(request.getParameter("id"))) {
//				dbdto.yangid[i] = null;
//				dbdto.yangtimeline[i] = 0;
//				dbdto.yangsin[i] = 0;
//				break;
//				}
//				dbdto.yangid[i] = dbdto.yangid[i+1];
//				dbdto.yangtimeline[i] = dbdto.yangtimeline[i+1];
//				dbdto.yangsin[i] = dbdto.yangsin[i+1];
//			}
//			}
//		else if(request.getParameter("cid").equals("song")) {
//			for(int i=0; i<=10; i++) {
//			if(dbdto.songid[i].equals(request.getParameter("id"))) {
//				dbdto.songid[i] = null;
//				dbdto.songtimeline[i] = 0;
//				dbdto.songsin[i] = 0;
//				break;
//				}
//				dbdto.songid[i] = dbdto.songid[i+1];
//				dbdto.songtimeline[i] = dbdto.songtimeline[i+1];
//				dbdto.songsin[i] = dbdto.songsin[i+1];
//			}
//			}
//		else if(request.getParameter("cid").equals("zozi")) {
//			for(int i=0; i<=10; i++) {
//			if(dbdto.zoziid[i].equals(request.getParameter("id"))) {
//				dbdto.zoziid[i] = null;
//				dbdto.zozitimeline[i] = 0;
//				dbdto.zozisin[i] = 0;
//				break;
//				}
//				dbdto.zoziid[i] = dbdto.zoziid[i+1];
//				dbdto.zozitimeline[i] = dbdto.zozitimeline[i+1];
//				dbdto.zozisin[i] = dbdto.zozisin[i+1];
//			}
//			}
//		else if(request.getParameter("cid").equals("sung")) {
//			for(int i=0; i<=10; i++) {
//			if(dbdto.sungid[i].equals(request.getParameter("id"))) {
//				dbdto.sungid[i] = null;
//				dbdto.sungtimeline[i] = 0;
//				dbdto.sungsin[i] = 0;
//				break;
//				}
//				dbdto.sungid[i] = dbdto.sungid[i+1];
//				dbdto.sungtimeline[i] = dbdto.sungtimeline[i+1];
//				dbdto.sungsin[i] = dbdto.sungsin[i+1];
//			}
//			}
//		else if(request.getParameter("cid").equals("an")) {
//			for(int i=0; i<=10; i++) {
//			if(dbdto.anid[i].equals(request.getParameter("id"))) {
//				dbdto.anid[i] = null;
//				dbdto.antimeline[i] = 0;
//				dbdto.ansin[i] = 0;
//				break;
//				}
//				dbdto.anid[i] = dbdto.anid[i+1];
//				dbdto.antimeline[i] = dbdto.antimeline[i+1];
//				dbdto.ansin[i] = dbdto.ansin[i+1];
//			}
//			}
//		else if(request.getParameter("cid").equals("cha")) {
//			for(int i=0; i<=10; i++) {
//			if(dbdto.chaid[i].equals(request.getParameter("id"))) {
//				dbdto.chaid[i] = null;
//				dbdto.chatimeline[i] = 0;
//				dbdto.chasin[i] = 0;
//				break;
//				}
//				dbdto.chaid[i] = dbdto.chaid[i+1];
//				dbdto.chatimeline[i] = dbdto.chatimeline[i+1];
//				dbdto.chasin[i] = dbdto.chasin[i+1];
//			}
//			}
		
		return "redirect:/dnf/dnfrank";
		}
		
	
	
	@RequestMapping("dnfrank")
	public String dnftestrank(HttpServletRequest request, Model model) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM epiccount order by ecount desc";
		List<Epiccount> ec = new ArrayList<Epiccount>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				update(rs.getString("cid"), api.searchTimeline("prey", 
						api.searchcharacterId("prey", rs.getString("cid"))),
						api.searchsin("prey", api.searchcharacterId("prey", rs.getString("cid"))));
				ec.add(new Epiccount(rs.getString("cid"),rs.getInt("ecount"),rs.getInt("scount"),rs.getString("an")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "failure");
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) { e.printStackTrace();}
		}
		model.addAttribute("ec",ec);
		return "dnfrank";
	}
	
	
	
	@RequestMapping("dnfinput")
	public String dnfinput(HttpServletRequest request, Model model) {
		
		return "dnfinput";
	}
	
}
