<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="today" class="java.util.Date" />
<fmt:formatDate value="${today}" pattern="yyyyMMdd" var="nowDate" />
<%@ page import="java.util.*"%>
<%@ page import="java.io.PrintWriter"%>
<!-- 한글 깨짐 방지를 위한 초반 작업 -->
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 건너오는 모든데이터를 utf-8로 받게끔 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>양티의 던파 api 연습홈페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 부트스트랩 -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="../resources/css/searchresult.css"><c:param name="dt" value="${nowDate}"/></c:url>" />
<!-- <link rel="stylesheet" type="text/css" href="resources/css/yang.css?version=1"> -->

<link rel="stylesheet" href="http://poiemaweb.com/assets/css/ajax.css">
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
	.img {
		background-image:url('../resources/images/background.png');
		background-repeat: no-repeat;
		background-size : cover;
	}

</style>
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">양티</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="/springwebprjdnfapi/index">메인 <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="versionnote.jsp">버전
						노트</a></li>
				<li class="nav-item"><a class="nav-link" href="gallery.jsp">갤러리</a>
				</li>
			</ul>
			<form method="get" action="/springwebprjdnfapi/dnf/dnftest3"
				class="form-inline mt-3">
				<select name="server" class="form-control mx-1 mt-2">
					<option value="anton">안톤</option>
					<option value="bakal">바칼</option>
					<option value="cain">카인</option>
					<option value="casillas">카시야스</option>
					<option value="diregie">디레지에</option>
					<option value="hilder">힐더</option>
					<option value="prey" selected>프레이</option>
					<option value="siroco">시로코</option>
				</select> <input type="text" name="id" class="form-control mx-1 mt-2"
					placeholder="내용을 입력하세요.">
				<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
			</form>
		</div>

	</nav>

	<div>
		<%-- <h1>서버 : ${server} / 캐릭터이름 : ${id}</h1> --%>
		<p>오늘날기준으로 한달전으로 타임라인이 나옵니다.</p>
	</div>

	<div class="img"
		style="width: auto; height: auto; border: 1px solid red; float: left;">
		<div style="float: left; width: auto; height: auto;">
			<img
				src="https://img-api.neople.co.kr/df/servers/${server}/characters/${cid}?zoom=1" />
		</div>
		<div style="float: left; width: auto; height: auto;">
			<h3>캐릭터 이름 : ${id}</h3>
			<span>서버 : ${server}</span>
			<h3>총 먹은 에픽 개수 : ${testtest2}</h3>
			<h3>총 먹은 신화 개수 : ${testtest3}</h3>
			
			<p>장착 장비</p>
			<c:forEach var="test" items="${searchItem}" varStatus="status">
				<img src="https://img-api.neople.co.kr/df/items/${test.itemId}" />
			</c:forEach>
			
			<p>먹은신화들</p>
			<c:forEach var="test" items="${sin2020}" varStatus="status">
			<c:if test="${not empty test}">
			<div style="background:url(https://img-api.neople.co.kr/df/items/${test}); width:28px; height:28px; float:left;">
				<div style="background:url(https://dunfamoa.com/ora_myth.png); width:28px; height:28px;" ></div>
			</div>
				
			</c:if>
			</c:forEach>
			<c:forEach var="test" items="${sin2021}" varStatus="status">
			<c:if test="${not empty test}">
<%-- 				<img src="https://img-api.neople.co.kr/df/items/${test}" />
				<!-- <div style="position:releative; top: 0px; left : 0px;">
				<div style="position:absolute;"><img src="https://dunfamoa.com/ora_myth.png"/></div>
				</div> --> --%>
			<div style="background:url(https://img-api.neople.co.kr/df/items/${test}); width:28px; height:28px; float:left;">
				<div style="background:url(https://dunfamoa.com/ora_myth.png); width:28px; height:28px;" ></div>
			</div>
			</c:if>
			</c:forEach>

		</div>
	</div>

	<div></div>


	<div
		style="margin: 0 auto; width: auto; overflow: hidden; float: left;">
		<div class="container" style="width: auto; float: left;">
			<p>
				던전드랍 <span>최근 30일 (최대 20개)</span>
			</p>
			<div
				style="height: auto; border: 1px solid lightgray; overflow: hidden">

				<c:forEach var="test" items="${timelinedesc}" varStatus="status">
					<c:choose>
						<c:when test="${test.name == '아이템 획득(던전)' }">
							<div style="width: auto; border: 1px solid lightgray;">
								<div style="width: auto; height: 30px;">
									<div style="color: rgb(17, 85, 153); float: left;">${test.name}</div>
									<div
										style="font-size: 14px; color: rgb(153, 153, 153); font-weight: 300; float: right;">${test.date}</div>
								</div>
								<div style="width: auto; height: 30px; text-align: left;">
									<span> <span
										style="margin-right: 4px; color: rgb(85, 85, 85);"></span>
										${test.dungeonName}에서 <c:if test="${test.itemRarity == '에픽' }">
											<span style="color: rgb(209, 168, 19);">${test.itemName}</span>
										</c:if> <c:if test="${test.itemRarity == '신화' }">
											<span style="color: #CC2EFA;">${test.itemName}</span>
										</c:if> <span style="margin-left: 4px;">획득!</span>
									</span>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>

		<div class="container" style="width: auto; width: auto; float: left;">
			<p>
				헬 <span>최근 30일 (최대 20개)</span>
			</p>
			<div
				style="width: auto; height: auto; border: 1px solid lightgray; overflow: hidden">
				<div>날짜</div>
				<c:forEach var="test" items="${timelinedesc}" varStatus="status">
					<c:choose>
						<c:when test="${test.name == '아이템 획득(지옥 파티)' || test.name == '아이템 획득(항아리)' }">
							<div style="width: auto; border: 1px solid lightgray;">
								<div style="width: auto; height: 30px;">
									<div style="color: rgb(17, 85, 153); float: left;">${test.name}
										<span style="margin-right: 4px; color: rgb(85, 85, 85);">[${test.channelName}
											${test.channelNo} 채널]</span>
									</div>
									<div
										style="font-size: 13px; color: rgb(153, 153, 153); font-weight: 300; float: right;">${test.date}</div>
								</div>
								<div style="width: auto; height: 30px; text-align: left;">
									<span> ${test.dungeonName}에서 <c:if
											test="${test.itemRarity == '에픽' }">
											<span style="color: rgb(209, 168, 19);">${test.itemName}</span>
										</c:if> <c:if test="${test.itemRarity == '신화' }">
											<span style="color: #CC2EFA;">${test.itemName}</span>
										</c:if> <span style="margin-left: 4px;">획득!</span>
									</span>
								</div>
							</div>
						</c:when>
					</c:choose>
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


	<a href="http://developers.neople.co.kr" target="_blank"> <img
		src="../resources/images/공식표기_color.png" alt="Neople 오픈 API" />
	</a>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
		crossorigin="anonymous"></script>
</body>
</html>