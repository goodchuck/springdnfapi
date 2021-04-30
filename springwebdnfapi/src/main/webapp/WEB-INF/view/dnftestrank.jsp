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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  </head>
  <body>
  	<h1>가장 에픽 많이 먹은 얘</h1>
  
<div class="container">
	<div class="row">
    	<div class="col">
    		<ul class="list-group">
<%-- 				<c:forEach var="test" items="${testtest}" varStatus="status">
					<li class="list-group-item">${test[1]}</li>
				</c:forEach> --%>
				
				<li class="list-group-item">${testtest[1]} / 먹은 에픽 수 : ${timelineall}</li>
					
				<c:choose>
				<c:when test = "${timeline > timeline2}">
				</c:when>
				<c:when test = "${timeline < timeline2}">
				</c:when>
				</c:choose>
			</ul>
		</div>
		<div class="col">
    		<ul class="list-group">
				<li class="list-group-item">${testtest2[1]} / 먹은 에픽 수 : ${timelineall2}</li>
			</ul>
		</div>
		<div class="col">
    		<ul class="list-group">
				<li class="list-group-item">${testtest3[1]} / 먹은 에픽 수 : ${timelineall3}</li>
			</ul>
		</div>
		<div class="w-100"></div>
		<div class="col">
    		<ul class="list-group">
				<li class="list-group-item">${testtest4[1]} / 먹은 에픽 수 : ${timelineall4}</li>
			</ul>
		</div>	
		<div class="col">
    		<ul class="list-group">
				<li class="list-group-item">${testtest5[1]} / 먹은 에픽 수 : ${timelineall5}</li>
			</ul>
		</div>
		<div class="col">
    		<ul class="list-group">
				<li class="list-group-item">${testtest6[1]} / 먹은 에픽 수 : ${timelineall6}</li>
			</ul>
		</div>	
	</div>
</div>
	<div>
		<h1>제일 에픽 많이 먹은얘 : ${maxepic}</h1>
	</div>


<%-- <c:forEach var="test2" items="${dd}" varStatus="status">
<div class="container">
  <div class="row">
    <div class="col">
    	<ul class="list-group">
  			<li class="list-group-item active">${test2.serverId}</li>
  			<li class="list-group-item">${test2.characterName}</li>
  			<li class="list-group-item">${test2.jobName }</li>
  			<li class="list-group-item">${test2.jobGrowName }</li>
		</ul>
    </div>
    <div class="col">
        <ul class="list-group">
  			<li class="list-group-item active"></li>
  			<li class="list-group-item"></li>
  			<li class="list-group-item"></li>
  			<li class="list-group-item"></li>
		</ul>
    </div>
    <div class="w-100"></div>
    <div class="col">
        <ul class="list-group">
  			<li class="list-group-item active"></li>
  			<li class="list-group-item"></li>
  			<li class="list-group-item"></li>
  			<li class="list-group-item"></li>
		</ul>
    </div>
    <div class="col">
        <ul class="list-group">
  			<li class="list-group-item active"></li>
  			<li class="list-group-item"></li>
  			<li class="list-group-item"></li>
  			<li class="list-group-item"></li>
		</ul>
    </div>
  </div>
</div>
</c:forEach> --%>

<a href="http://developers.neople.co.kr" target="_blank">
<img src="로고 이미지 위치" alt="Neople 오픈 API" / > </a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
  </body>
</html>