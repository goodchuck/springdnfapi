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
				<li class="nav-item"><a class="nav-link" href="Ytbbs.jsp">게시판</a></li>
				<li class="nav-item"><a class="nav-link" href="/springwebprjdnfapi/versionnote">버전 노트</a></li>
				<li class="nav-item"><a class="nav-link" href="/springwebprjdnfapi/gallery">갤러리</a></li>
				<li class="nav-item"><a class="nav-link" href="/springwebprjdnfapi/dnf/dnfrank">기린랭킹</a></li>
			</ul>
		</div>
	</nav>

  <h1>캐릭터 추가하기 : ${cid}</h1>
  <form method="get" action="/springwebprjdnfapi/db/cinsert" class="form-inline mt-3">
<%-- <form method="get" action="/springwebprjdnfapi/dnf/cinsert" class="form-inline mt-3"> --%>
 		<select name="cid" class="form-control mx-1 mt-2">
				<option value="yang">양티</option>
				<option value="song">지원</option>
				<option value="zozi">조지</option>
				<option value="sung">성진</option>
				<option value="an">차니</option>
				<option value="cha">혀누</option>
		</select>
 		<input type="text" name="id" class="form-control mx-1 mt-2" placeholder="내용을 입력하세요.">
 		<button type="submit" class="btn btn-primary mx-1 mt-2">추가하기</button>
 	</form>




<a href="http://developers.neople.co.kr" target="_blank">
<img src="../images/공식표기_color.png" alt="Neople 오픈 API" /> </a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
  </body>
</html>