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
<link rel="stylesheet" type="text/css" href="<c:url value="../resources/css/searchresult.css"><c:param name="dt" value="${nowDate}"/></c:url>"/>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
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
				<li class="nav-item"><a class="nav-link" href="Ytbbs.jsp">게시판</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="versionnote.jsp">버전
						노트</a></li>
				<li class="nav-item"><a class="nav-link" href="gallery.jsp">갤러리</a>
				</li>
			</ul>
		</div>
	</nav>
	
	<div>
	<%-- <h1>서버 : ${server} / 캐릭터이름 : ${id}</h1> --%>
	<p>오늘날기준으로 한달전으로 타임라인이 나옵니다.</p>
	</div>

	<div style="margin:0 auto; width:100%;">
		<div style="float: left; width:400px; height:460px;">
			<img
				src="https://img-api.neople.co.kr/df/servers/${server}/characters/${cid}?zoom=2" />
		</div>
		<div style="float: left; width:400px; height:460px;">
			<h3>캐릭터 이름 : ${id}</h3>
			<span>서버 : ${server}</span>
			<h3>에픽먹은 개수 : ${timelinecount}</h3>
			<h3>총먹은 신화 개수: ${sincount}</h3>
			<h3>에픽먹은 갯수 : ${timelinecount3}</h3>
			<h3>에픽먹은 갯수 : ${timelinecount4}</h3>
			<h3>에픽먹은 갯수 : ${timelinecount5}</h3>
		</div>
	</div>

	<div style="margin:0 auto; width:auto; overflow:hidden">
 	<div class="container" style="width:400px; float:left;">
		<h3>던전드랍
		<span>최근 30일 (최대 20개)</span>
		</h3>
			<div style="height:auto; border:1px solid lightgray; overflow:hidden">
				<div>날짜</div>
					<c:forEach var="test" items="${timelinedesc}" varStatus="status">
					<c:if test="${test.name == '아이템 획득(던전)' }">
					<div style="width:400px; border: 1px solid lightgray;">
					<div style="width:400px; height:30px;">
						<div style="color: rgb(17,85,153); float:left;">${test.name}</div>
						<div style="font-size: 13px; color: rgb(153,153,153); font-weight: 300; float:right;">${test.date}</div>
					</div>
					<div style="width:400px; height:30px; text-align:left;">
						<span>
						<span style="margin-right: 4px; color: rgb(85, 85, 85);"></span>
						${test.dungeonName}에서
						<span style="color: rgb(209,168,19);">${test.itemName}</span>
						<span style="margin-left: 4px;">획득!</span>
						</span>
					</div>
					</div>
					</c:if>
					</c:forEach>
			</div>
	</div> 
	
	<div class="container" style="width: auto; width:auto; float:left;">
		<h3>헬
		<span>최근 30일 (최대 20개)</span>
		</h3>
			<div style="width:610px; height:auto; border:1px solid lightgray; overflow:hidden">
				<div>날짜</div>
					<c:forEach var="test" items="${timelinedesc}" varStatus="status">
					<c:if test="${test.name == '아이템 획득(지옥 파티)' }">
					<div style="width:600px; border: 1px solid lightgray;">
					<div style="width:600px; height:30px;">
						<div style="color: rgb(17,85,153); float:left;">${test.name}</div>
						<div style="font-size: 13px; color: rgb(153,153,153); font-weight: 300; float:right;">${test.date}</div>
					</div>
					<div style="width:600px; height:30px; text-align:left;">
						<span>
						<span style="margin-right: 4px; color: rgb(85, 85, 85);">${test.channelName} ${test.channelNo} 채널에서</span>
						${test.dungeonName}에서
						<span style="color: rgb(209,168,19);">${test.itemName}</span>
						<span style="margin-left: 4px;">획득!</span>
						</span>
					</div>
					</div>
					</c:if>
					</c:forEach>
				</div>
		</div>
	</div> 
	
<%-- 	<div class="container" style="float:left; width:33%;">
		<div class="row">
			<div class="col" >
				<h2>지옥파티에서 습득한 에픽</h2>
				<ul style="width:550px;">
					<li class="list-group-item">먹은 날짜 / 아이템
					<c:forEach var="test" items="${timelinedesc}" varStatus="status">
						<c:if test="${test.name == '아이템 획득(지옥 파티)'}">
						<li class="list-group-item">${test.date} : ${test.itemName}</li>
						</c:if>				
					</c:forEach>	
				</ul>			
			</div>
		</div>
	</div> --%>


<a href="http://developers.neople.co.kr" target="_blank">
<img src="../resources/images/공식표기_color.png" alt="Neople 오픈 API" /> </a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
  </body>
</html>