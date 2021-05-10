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
	
	//DBDTO dbdto = ctx.getBean(DBDTO.class);
	//DBDTO dbdto = new DBDTO(dataSource);
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

		return "dnfdibol";
	}
	
	@RequestMapping("dnftest3")
	public String dnftest3(HttpServletRequest request, Model model) {

		model.addAttribute("server",request.getParameter("server"));
		model.addAttribute("id",request.getParameter("id"));
		model.addAttribute("cid",api.searchcharacterId(request.getParameter("server"), request.getParameter("id")));
		model.addAttribute("timelinedesc", api.searchTimelinedesc(request.getParameter("server"), api.searchcharacterId(request.getParameter("server"), request.getParameter("id"))));
		model.addAttribute("timelinecount",api.tlall(request.getParameter("server"), api.searchcharacterId(request.getParameter("server"), request.getParameter("id"))));
		model.addAttribute("timelinecount3",api.searchTimeline3(request.getParameter("server"), api.searchcharacterId(request.getParameter("server"), request.getParameter("id"))));
		return "dnftest3";
	}

	@RequestMapping("dnfcinsertform")
	public String dnfcinsertform(HttpServletRequest request, Model model) {

		model.addAttribute("cid",request.getParameter("cid"));
		
		
		return "dnfcinsertform";
	}
	
	@RequestMapping("cinsert")
	public String cinsert(HttpServletRequest request, Model model) {

		return "redirect:/dnf/dnfdibol";
	}
	
	@RequestMapping("cdelete")
	public String cdelete(HttpServletRequest request, Model model) {
		delete(request.getParameter("cid"));

		return "redirect:/dnf/dnfrank";
		}
		
	
	
	@RequestMapping("dnfrank")
	public String dnftestrank(HttpServletRequest request, Model model) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM epiccount order by ecount desc, scount desc";
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
		model.addAttribute("nowtime", now_dt);
		return "dnfrank";
	}
	
	
	
	@RequestMapping("dnfinput")
	public String dnfinput(HttpServletRequest request, Model model) {
		
		return "dnfinput";
	}
	
}
