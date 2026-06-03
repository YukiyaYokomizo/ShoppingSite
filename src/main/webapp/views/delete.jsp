<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form class="delere-form" action="${pageContext.request.contextPath}/Delete.action" method="post">
	<p class="welcome-text">本当に会員情報を削除してもよろしいですか。</p>
	<div class="menu">
		<input type="button" value="戻る"
		onclick="location.href='${pageContext.request.contextPath}/views/user-menu.jsp'">
		<input type="submit" value="削除">
	</div>
</form>

<%@include file="../footer.html"%>