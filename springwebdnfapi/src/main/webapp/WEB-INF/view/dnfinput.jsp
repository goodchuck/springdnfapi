<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="today" class="java.util.Date" />
<fmt:formatDate value="${today}" pattern="yyyyMMdd" var="nowDate"/>
<%@ page import="java.util.*"%>
<%@ page import="java.io.PrintWriter" %> <!-- 한글 깨짐 방지를 위한 초반 작업 -->
<%
	request.setCharacterEncoding("UTF-8");
%> <!-- 건너오는 모든데이터를 utf-8로 받게끔 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>양티의 던파 api 연습홈페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 부트스트랩 -->
<link rel="stylesheet" type="text/css" href="<c:url value="resources/css/yang.css"><c:param name="dt" value="${nowDate}"/></c:url>"/>
<!-- <link rel="stylesheet" type="text/css" href="resources/css/yang.css?version=1"> -->
	
	<link rel="stylesheet" href="http://poiemaweb.com/assets/css/ajax.css">
    <!-- 부트스트랩 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
  <body>
  
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">양티</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/springwebprjdnfapi/index">메인
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/springwebprjdnfapi/versionnote">버전 노트</a></li>
				<li class="nav-item"><a class="nav-link" href="/springwebprjdnfapi/gallery">갤러리</a></li>
				<li class="nav-item"><a class="nav-link" href="/springwebprjdnfapi/dnf/dnfrank">기린랭킹</a></li>
			</ul>
		</div>
	</nav>

	<div class="mid">
  		<h1>양티의 연습 던파 사이트!</h1>
	</div>

	<div class="mid">
 	<form method="get" action="/springwebprjdnfapi/dnf/dnftest3" class="form-inline mt-3">
 		<select name="server" class="form-control mx-1 mt-2">
				<option value="anton">안톤</option>
				<option value="bakal">바칼</option>
				<option value="cain">카인</option>
				<option value="casillas">카시야스</option>
				<option value="diregie">디레지에</option>
				<option value="hilder">힐더</option>
				<option value="prey" selected>프레이</option>
				<option value="siroco">시로코</option>
		</select>
 		<input type="text" name="id" class="form-control mx-1 mt-2" placeholder="내용을 입력하세요.">
 		<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
 	</form>
	</div>
	
	<p> </p>
	<div class="mid">
		<img src="resources/images/신화구슬정지.PNG" />
		<img src="resources/images/스펀저.gif" />
	</div>
	
	<div class="mid">
		<div>
			<table class="table">
				<thead class="thead-light">
					<tr class="text-center">
						<th scope="col">공지사항</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>21/05/12 네오플API를 이용한 사이트로써 개인 포트폴리오용 사이트입니다.</td>
					</tr>
				</tbody>
			</table>	
		</div>
	</div>
	
	<div class="mid">
		<!-- <h1><a href="/springwebprjdnfapi/dnf/dnfdibol">우리 캐릭터들 기린력 확인</a></h1> -->
		
		<a href="http://developers.neople.co.kr" target="_blank">
		<img src="resources/images/공식표기_color.png" alt="Neople 오픈 API" /> </a>
	</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
  </body>
</html>