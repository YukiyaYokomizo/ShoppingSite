<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form action="${pageContext.request.contextPath}/Insert.action" method="post">

	<h2>会員登録</h2>

	<p>
		<label for="id">ID</label> <input type="text" id="id" name="id">
	</p>
	<div class="name">
		<p>
			<label for="lastname">名前(姓)</label> <input type="text" id="lastname"
				name="lastname"> <label for="firstname">名前(名)</label> <input
				type="text" id="firstname" name="firstname">
		</p>
	</div>
	<p>
		<label for="password">パスワード</label> <input type="password"
			id="password" name="password">
	</p>
	<p>
		<label for="address">住所</label> <input type="text" id="address"
			name="address">
	</p>
	<p>
		<label for="e-mail">メールアドレス</label> <input type="text" id="e-mail"
			name="e-mail">
	</p>

	<input type="submit" value="次へ">
</form>

<%@include file="../footer.html"%>
