<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">


<form class="login-form">
<p>ようこそ${user.lastName}さん</p>
<div class="menu">
<p><a href="${pageContext.request.contextPath}/views/update.jsp">変更</p>
<!-- <p><input type="submit" value="変更"></p> -->
<p><a href="${pageContext.request.contextPath}/views/update.jsp">削除</p>
<!-- <p><input type="submit" value="削除"></p> -->
<p><a href="${pageContext.request.contextPath}/views/logout-in.jsp">ログアウト</p>
</div>
</form>

<%@include file="../footer.html"%>