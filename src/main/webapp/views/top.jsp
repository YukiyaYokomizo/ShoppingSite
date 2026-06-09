<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>
	<main>
	<div>
	でかでかとした画像を置きます。右から左へ流れます。
	</div>
	<%@include file="../HeaderFooter/category-nav.jsp"%>
	<div>
	記事を貼ります。
	</div>
	<div>
	ランキングを貼ります。ページの右側に置きます。
	</div>
	<div>
	お知らせの一覧を表示します。
	</div>
	</main>
</body>
</html>