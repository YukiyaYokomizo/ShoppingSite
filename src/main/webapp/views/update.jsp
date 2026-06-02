<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form class="login-card"
	action="${pageContext.request.contextPath}/UpdateConfirm.action"
	method="post">

	<h2>更新</h2>

	<input type="hidden" name="oldMemberId"
		value="${sessionScope.user.memberId}">

	<p>
		ID <input type="text" name="id" value="${user.memberId}">
	</p>

	<p>
		パスワード <input type="password" name="password" value="${user.password}">
	</p>

	<p>
		名前(姓) <input type="text" name="lastname" value="${user.lastName}">
	</p>

	<p>
		名前(名) <input type="text" name="firstname" value="${user.firstName}">
	</p>

	<p>
		住所 <input type="text" name="address" value="${user.address}">
	</p>

	<p>
		メールアドレス <input type="text" name="mailaddress"
			value="${user.mailAddress}">
	</p>

	<p style="color: red;">${errorMessage}</p>

	<input type="submit" value="確認へ">

</form>
<%@include file="../footer.html"%>

