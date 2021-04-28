package springwebprjdnfapi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import springwebprjdnfapi.main.Config;
import springwebprjdnfapi.main.HealthDTO;
import springwebprjdnfapi.main.MemberRegistRequest;
import springwebprjdnfapi.main.Test;
import springwebprjdnfapi.test.Cm;


@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	BasicDataSource dataSource;

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

	
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
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) throws UnsupportedEncodingException, IOException {
		

		return "dnftestinput";
	}

	@RequestMapping("Ytbbs")
	public void Ytbbs() {

	}

	@RequestMapping("versionnote")
	public void versionnote() {

	}

	@RequestMapping("gallery")
	public void gallery() {

	}

	@RequestMapping("userJoin")
	public void userJoin() {

	}
	
	@RequestMapping("userLogin")
	public void userLogin() {

	}
	
	@RequestMapping("userLogout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
}
