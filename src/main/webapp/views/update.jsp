

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../header.html"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form id="user-form"
	class="login-card"
	action="${pageContext.request.contextPath}/UpdateConfirm.action"
	method="post">

	<h2>更新</h2>

	<input type="hidden" name="oldMemberId"
		value="${empty oldMemberId ? sessionScope.user.memberId : oldMemberId}">

	<p>
		<label for="id">ID</label>
		<input type="text" id="id" name="id" value="${user.memberId}">
		<span id="id-error" class="js-error"></span>
	</p>

	<p>
		<label for="password">パスワード</label>
		<input type="password" id="password" name="password" value="${user.password}">
		<span id="password-error" class="js-error"></span>
	</p>

	<div class="name">
		<p>
			<label for="lastname">名前(姓)</label>
			<input type="text" id="lastname" name="lastname" value="${user.lastName}">
			<span id="lastname-error" class="js-error"></span>
		</p>
		<p>
			<label for="firstname">名前(名)</label>
			<input type="text" id="firstname" name="firstname" value="${user.firstName}">
			<span id="firstname-error" class="js-error"></span>
		</p>
	</div>

	<p>
		<label for="address">住所</label>
		<input type="text" id="address" name="address" value="${user.address}">
		<span id="address-error" class="js-error"></span>
	</p>

	<p>
		<label for="mailaddress">メールアドレス</label>
		<input type="text" id="mailaddress" name="mailaddress" value="${user.mailAddress}">
		<span id="mailaddress-error" class="js-error"></span>
	</p>

	<p style="color: red;">${errorMessage}</p>

	<input type="submit" value="確認へ">

</form>

<script src="${pageContext.request.contextPath}/js/validation.js"></script>

<%@include file="../footer.html"%>
