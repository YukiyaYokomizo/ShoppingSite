<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<div class="login-card users-card">

	<h2>会員一覧</h2>

	<table class="users-table">
		<tr>
			<th>ID</th>
			<th>パスワード</th>
			<th>姓</th>
			<th>名</th>
			<th>住所</th>
			<th>メールアドレス</th>
			<th>権限</th>
		</tr>

		<c:forEach var="user" items="${usersList}">
			<tr>
				<td>${user.memberId}</td>
				<td>${user.password}</td>
				<td>${user.lastName}</td>
				<td>${user.firstName}</td>
				<td>${user.address}</td>
				<td>${user.mailAddress}</td>
				<td>${user.admin}</td>
			</tr>
		</c:forEach>
	</table>

	<form action="" method="post">
		<p>
			<a href="${pageContext.request.contextPath}/ProductManage.action">
				メニューへ戻る </a> <span>|</span> <a
				href="${pageContext.request.contextPath}/views/new-admin-registration.jsp">
				管理者を追加 </a>
		</p>
	</form>

</div>

<%@include file="../footer.html"%>