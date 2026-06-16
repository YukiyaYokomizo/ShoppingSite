<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form id="user-form" class="login-card"
	action="${pageContext.request.contextPath}/InsertAdmin.action"
	method="post">

	<h2>
	管理者登録
</h2>

	<p class="error-message">${errorMessage}</p>

	<p>
		<label for="id">ID</label>
		<input type="text" id="id" name="id"
			value="${userForm.memberId}">
		<span class="js-error" id="id-error"></span>
	</p>

	<div class="name">
		<p>
			<label for="lastname">名前(姓)</label>
			<input type="text" id="lastname" name="lastname"
				value="${userForm.lastName}">
			<span class="js-error" id="lastname-error"></span>
		</p>

		<p>
			<label for="firstname">名前(名)</label>
			<input type="text" id="firstname" name="firstname"
				value="${userForm.firstName}">
			<span class="js-error" id="firstname-error"></span>
		</p>
	</div>

	<p>
		<label for="password">パスワード</label>
		<input type="password" id="password" name="password"
			value="${userForm.password}">
		<span class="js-error" id="password-error"></span>
	</p>

	<p>
		<label for="passwordConfirm">パスワード（確認用）</label>
		<input type="password" id="passwordConfirm" name="passwordConfirm">
		<span class="js-error" id="passwordConfirm-error"></span>
	</p>

	<p>
		<label for="address">住所</label>
		<input type="text" id="address" name="address"
			value="${userForm.address}">
		<span class="js-error" id="address-error"></span>
	</p>

	<p>
		<label for="mailaddress">メールアドレス</label>
		<input type="text" id="mailaddress" name="mailaddress"
			value="${userForm.mailAddress}">
		<span class="js-error" id="mailaddress-error"></span>
	</p>

	<input type="submit" value="登録">

</form>

<script src="${pageContext.request.contextPath}/js/validation.js"></script>

<%@include file="../footer.html"%>