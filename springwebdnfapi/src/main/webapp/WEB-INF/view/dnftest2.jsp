<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %> <!-- 한글 깨짐 방지를 위한 초반 작업 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%> <!-- 건너오는 모든데이터를 utf-8로 받게끔 -->
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>양티 홈페이지</title>
    
	
	<link rel="stylesheet" href="http://poiemaweb.com/assets/css/ajax.css">
    <!-- 부트스트랩 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<%
  		String userID = null;
  	  	if (session.getAttribute("userID") != null) {
  	  		userID = (String) session.getAttribute("userID");
  	  	}
  	%>
  
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">양티</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="main.jsp">메인
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="Ytbbs.jsp">게시판</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="versionnote.jsp">버전
						노트</a></li>
				<li class="nav-item"><a class="nav-link" href="gallery.jsp">갤러리</a>
				</li>
				<li class="nav-item active"><a class="nav-link" href="/springwebprj/dnf/dnftestinput">던파 연습</a>
			</ul>
		</div>
  <%
  	if(userID ==null) {
  %>
        <div class= "nav-item dropdown" style="float: right;">
    
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          접속하기
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="Ytlogin.jsp">로그인</a>
          <a class="dropdown-item" href="Ytjoin.jsp">회원가입</a>
        </div>
      </div>
  <%
  	} else {
  %>
        <div class= "nav-item dropdown" style="float: right;">
    
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          회원관리
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="YtlogoutAction.jsp">로그아웃</a>
        </div>
      </div>
	<%
		}
	%>
	</nav>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


  <h1>한달동안 우리 길드 캐릭의 기린력 </h1>
  <a href="/springwebprjdnfapi/dnf/dnftest4">캐릭터 추가하기</a>
<div class="container">
	<div class="row">
    	<div class="col">
    	<h3>양티네</h3>
    		<ul class="list-group">
<%-- 				<c:forEach var="test" items="${dnfname}" varStatus="status">
					<li class="list-group-item">${test}</li>
				</c:forEach> --%>
				<c:forEach var="test" items="${yangid}" varStatus="status">
				<c:if test="${not empty yangid[status.index]}">
				<li class="list-group-item">${test} / 먹은 에픽 수 : ${yangtl[status.index]}</li>
				</c:if>
				</c:forEach>
			</ul>
		</div>
		<div class="col">
		<h3>지원이네</h3>
    		<ul class="list-group">
<%-- 				<c:forEach var="test" items="${dnfname2}" varStatus="status">
					<li class="list-group-item">${test}</li>
				</c:forEach> --%>
				<li class="list-group-item">${songid0} / 먹은 에픽 수 : ${songtimeline0}</li>
				<li class="list-group-item">${songid1} / 먹은 에픽 수 : ${songtimeline1}</li>
			</ul>
		</div>
		<div class="col">
		<h3>조지네</h3>
    		<ul class="list-group">
<%-- 				<c:forEach var="test" items="${dnfname3}" varStatus="status">
					<li class="list-group-item">${test}</li>
				</c:forEach> --%>
				<li class="list-group-item">${zoziid0} / 먹은 에픽 수 : ${zozitimeline0}</li>
				<li class="list-group-item">${zoziid1} / 먹은 에픽 수 : ${zozitimeline1}</li>
			</ul>
		</div>
		<div class="w-100"></div>
		<div class="col">
		<h3>성진이네</h3>
    		<ul class="list-group">
<%-- 				<c:forEach var="test" items="${dnfname4}" varStatus="status">
					<li class="list-group-item">${test}</li>
				</c:forEach> --%>
				<li class="list-group-item">${sungid0} / 먹은 에픽 수 : ${sungtimeline0}</li>
				<li class="list-group-item">${sungid1} / 먹은 에픽 수 : ${sungtimeline1}</li>
			</ul>
		</div>
		<div class="col">
		<h3>차니네</h3>
    		<ul class="list-group">
<%-- 				<c:forEach var="test" items="${dnfname5}" varStatus="status">
					<li class="list-group-item">${test}</li>
				</c:forEach> --%>
				<li class="list-group-item">${anid0} / 먹은 에픽 수 : ${antimeline0}</li>
				<li class="list-group-item">${anid1} / 먹은 에픽 수 : ${antimeline1}</li>
			</ul>
		</div>
		<div class="col">
		<h3>현우네</h3>
    		<ul class="list-group">
<%-- 				<c:forEach var="test" items="${dnfname6}" varStatus="status">
					<li class="list-group-item">${test}</li>
				</c:forEach> --%>
				<li class="list-group-item">${chaid0} / 먹은 에픽 수 : ${chatimeline0}</li>
				<li class="list-group-item">${chaid1} / 먹은 에픽 수 : ${chatimeline1}</li>
			</ul>
		</div>
		
		<h2>테스트 </h2>
		<p>${dnfnametest0 }</p>
		<p>${dnfnametest1}</p>
	</div>
</div>




<a href="http://developers.neople.co.kr" target="_blank">
<img src="로고 이미지 위치" alt="Neople 오픈 API" / > </a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
  </body>
</html>