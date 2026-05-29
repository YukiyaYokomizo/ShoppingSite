<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form class="login-form"
	action="${pageContext.request.contextPath}/Login.action" method="post">
	<p>
		ID : <input type="text" name="id">
	</p>
	<p>
		パスワード : <input type="password" name="password">
	</p>
	<p>
		<input class type="submit" value="ログイン">
		<button class="new-registration" type="button"
			onclick="location.href='${pageContext.request.contextPath}/views/user-in.jsp'">
			新規会員登録</button>
	</p>
</form>

<%@include file="../footer.html"%>


