<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form action="${pageContext.request.contextPath}/InsertConfirm.action" method="post">

	<h2>会員登録</h2>
	
	<p>
		<label for="id">ID</label> <input type="text" id="id" name="id" value="${userForm.memberId}">
	</p>
	<div class="name">
		<p>
			<label for="lastname">名前(姓)</label> <input type="text" id="lastname"
				name="lastname" value="${userForm.lastName}"> <label for="firstname">名前(名)</label> <input
				type="text" id="firstname" name="firstname" value="${userForm.firstName}">
		</p>
	</div>
	<p>
		<label for="password">パスワード</label> <input type="password"
			id="password" name="password" value="${userForm.password}">
	</p>
	<p>
		<label for="address">住所</label> <input type="text" id="address"
			name="address" value="${userForm.address}">
	</p>
	<p>
		<label for="mailaddress">メールアドレス</label> <input type="text" id="mailaddress"
			name="mailaddress" value="${userForm.mailAddress}">
	</p>
	
	<p style="color: red;">${errorMessage}</p>
	
	<input type="submit" value="確認へ">
</form>

<%@include file="../footer.html"%>

