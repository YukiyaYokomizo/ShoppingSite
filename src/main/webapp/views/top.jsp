<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form class="login-form">
	<div class="menu">
		<a href="${pageContext.request.contextPath}/views/login-in.jsp">ログイン</a>
		|
		<a href="${pageContext.request.contextPath}/views/new-registration.jsp">新規会員登録</a>
	</div>
</form>

<%@include file="../footer.html"%>