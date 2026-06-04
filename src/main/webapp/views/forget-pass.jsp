<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form class="login-card"
	action="${pageContext.request.contextPath}/ForgetPass.action"
	method="post">

	<h2>パスワード確認</h2>

	<p class="error-message">${errorMessage}</p>

	<p>
		<label for="mailaddress">メールアドレスを入力してください</label> <input type="text"
			id="mailaddress" name="mailaddress">
	</p>

	<input type="submit" value="送信">

	<p>
		<a href="${pageContext.request.contextPath}/views/login-in.jsp">ログイン画面へ戻る</a>
	</p>

</form>

<%@include file="../footer.html"%>