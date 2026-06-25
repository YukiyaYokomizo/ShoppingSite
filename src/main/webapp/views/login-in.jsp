<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/top.css">


<header class="header">
	<%@include file="../HeaderFooter/header2.jsp"%>
</header>

<form class="login-card"
	action="${pageContext.request.contextPath}/Login.action" method="post">

	<h2>ログイン</h2>

	<p>
		<label for="id">ID</label> <input type="text" id="id" name="id">
	</p>

	<p>
		<label for="password">パスワード</label> <input type="password"
			id="password" name="password">
	</p>

	<p class="button-area">
		<input type="submit" value="ログイン">
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/views/forget-pass.jsp">パスワードを忘れた方はコチラ</a>
	</p>
</form>


<%@include file="../footer.html"%>
