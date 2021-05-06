package springwebprjdnfapi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import springwebprjdnfapi.main.Config;
import springwebprjdnfapi.main.Epiccount;
import springwebprjdnfapi.main.Test;
import springwebprjdnfapi.test.Api;

@Controller
@RequestMapping("/db/")
public class DbController {

	@Autowired
	ComboPooledDataSource dataSource;
	//BasicDataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public DbController(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int update(String cid, int ecount, int scount) {
		return jdbcTemplate.update("update epiccount set ecount = ?,scount=? where cid = ?",ecount,scount,cid);
	}
	public int delete(int bbsid) {
		return jdbcTemplate.update("update bbs set bbsav = 0 where bbsid = ?",bbsid);
	}
	public int cinsert(String cid, int ecount, int scount, String an) {
		return jdbcTemplate.update("insert into epiccount (cid, ecount, scount, an) values(?,?,?,?)",cid,ecount,scount,an);
	}
	public int userjoin(String id, String pw, String name, String gender, String email) {
		return jdbcTemplate.update("insert into user values (?,?,?,?,?)",id,pw,name,gender,email);
	}
	
	public List<Epiccount> selectlist(String cid) {
		return jdbcTemplate.query("select cid from epiccount where cid = ?",new Object[] {cid},
				new RowMapper<Epiccount>() {
			@Override
			public Epiccount mapRow(ResultSet rs, int rowNum) throws SQLException {
				Epiccount ec = new Epiccount();
				ec.setCid(rs.getString("cid"));
				ec.setEcount(rs.getInt("ecount"));
				ec.setScount(rs.getInt("scount"));
				return ec;
			}
		}
				);
	}
	public String select(String cid) {
		return jdbcTemplate.queryForObject("select cid from epiccount where cid = ?",new Object[] {cid}, String.class);
	}
	@Autowired
	Test test;
	
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
	Api api = ctx.getBean(Api.class);
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@RequestMapping("userLoginAction")
	public String userLoginAction(HttpServletRequest request, Model model, HttpSession session) {
		String SQL = "SELECT * FROM USER WHERE userID = ?";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, request.getParameter("userID"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("userPassword").equals(request.getParameter("userPassword"))) {
					model.addAttribute("ts1", rs.getString("userPassword"));
					model.addAttribute("testForm",rs.getString("userID"));
					session.setAttribute("sessiontest", rs.getString("userID"));
					//model.addAttribute("msg", "success");
					return "redirect:/index";
				} else {
					//bindingResult.rejectValue("pw","notMatch", "���̵�� ��й�ȣ�� �����ʽ��ϴ�.");
					model.addAttribute("msg", "failure");
					return "userLogin";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "failure");
			return "userLogin";
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return "redirect:/userLogin";
	}
	
	@RequestMapping("userJoinAction")
	public void userJoinAction(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		   String userID = null;
		   String userPassword = null;
		   String userEmail = null;
		   if(request.getParameter("userID") != null) {
			   userID = request.getParameter("userID");
		   }
		   if(request.getParameter("userPassword") != null) {
			   userPassword = request.getParameter("userPassword");
		   }
		   if(request.getParameter("userEmail") != null) {
			   userEmail = request.getParameter("userEmail");
		   }

	}
	
	@RequestMapping("cinsert")
	public String cinsert(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		//String test = select(request.getParameter("id"));
		List<Epiccount> test = selectlist(request.getParameter("id"));
		int epiccount = api.searchTimeline("prey", api.searchcharacterId("prey", request.getParameter("id")));
		int sincount = api.searchsin("prey", api.searchcharacterId("prey", request.getParameter("id")));
		String an = api.searchAdName("prey", api.searchcharacterId("prey", request.getParameter("id")));
		cinsert(request.getParameter("id"), epiccount, sincount,an);

		   return "redirect:/dnf/dnfrank";
	}
	


	@RequestMapping("bbsDeleteAction")
	public String BbsDeleteAction(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {
		String SQL = "UPDATE BBSTEST SET BBSAV = 0 WHERE BBSID = ?";
		
/*		if(session.getId().equals(request.getParameter("bbsid")))
		{*/
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(request.getParameter("bbsid")));
			pstmt.executeUpdate();
			model.addAttribute("msg", request.getParameter("bbsid"));
			return "redirct:/index";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "failure");
			return "redirect:/index";
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) { e.printStackTrace();}
		}
		/*
		 * } return "";
		 */
	}
	
	@RequestMapping("/dbTest.do3")
	public String dbTest3(HttpServletRequest httpServletRequest, Model model) {
		
		return "index";
	}
	
	@RequestMapping("/dbTest.do4")
	public String dbTest4(Model model) {
		String SQL = "SELECT * FROM USER WHERE userID = ?";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "1234");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals("1234")) {
					model.addAttribute("userID", rs.getString("userID"));
					model.addAttribute("userPassword", rs.getString("userPassword"));
				}
			}
			model.addAttribute("ts", "Ȯ��");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return "test";
	}
	
	@RequestMapping("/dbTest.do5")
	public String dbTest5(HttpServletRequest httpServletRequest,HttpSession session, Model model) {
		String SQL = "INSERT INTO BBSTEST (id, title, content) VALUES (?,?,?)";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, (String)session.getAttribute("sessiontest"));
			pstmt.setString(2, httpServletRequest.getParameter("Title"));
			pstmt.setString(3, httpServletRequest.getParameter("Content"));
			pstmt.executeUpdate();

			model.addAttribute("ts1", httpServletRequest.getParameter("Title"));
			model.addAttribute("ts2", httpServletRequest.getParameter("Content"));
			model.addAttribute("ts3", httpServletRequest.getParameter("favoriteOsNames"));
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try { if(rs != null) rs.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return "redirect:/index";
	}
}
