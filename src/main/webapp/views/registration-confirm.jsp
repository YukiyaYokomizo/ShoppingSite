<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<div class="login-card">

	<h2>登録内容確認</h2>

	<p>ID：${userForm.memberId}</p>
	<p>パスワード：********</p>
	<p>名前(姓)：${userForm.lastName}</p>
	<p>名前(名)：${userForm.firstName}</p>
	<p>住所：${userForm.address}</p>
	<p>メールアドレス：${userForm.mailAddress}</p>

	<form action="${pageContext.request.contextPath}/Insert.action"
		method="post">

		<input type="hidden" name="id" value="${userForm.memberId}"> <input
			type="hidden" name="password" value="${userForm.password}"> <input
			type="hidden" name="lastname" value="${userForm.lastName}"> <input
			type="hidden" name="firstname" value="${userForm.firstName}">
		<input type="hidden" name="address" value="${userForm.address}">
		<input type="hidden" name="mailaddress"
			value="${userForm.mailAddress}"> <input type="submit"
			value="登録する">

	</form>

	<form
		action="${pageContext.request.contextPath}/views/new-registration.jsp"
		method="post">
		<input type="button" value="戻る" onclick="history.back()">
	</form>

</div>

<%@include file="../footer.html"%>