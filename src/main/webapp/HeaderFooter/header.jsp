<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Yomogi&display=swap"
	rel="stylesheet">

<%-- サイトのロゴ --%>
<div class="site-logo">
	<c:choose>
		<c:when
			test="${not empty sessionScope.user && sessionScope.user.admin == 1}">
			<a href="${pageContext.request.contextPath}/views/admin-menu.jsp"> ロゴ入れるよ
			</a>
		</c:when>

		<c:otherwise>
			<a href="${pageContext.request.contextPath}/views/top.jsp">
				ロゴ入れるよ </a>
		</c:otherwise>
	</c:choose>
</div>

<%-- 検索 (formのactionは一旦空欄) --%>
<div class="site-search">
	<form action="" method="post">
		<div>
			<div>
				<input type="text" name="name">
			</div>

			<div>
				<input type="submit" value="検索">
			</div>
		</div>
	</form>
</div>

<%-- 右上のメニュー(分岐あり) --%>
<div class="site-menu">
	<c:choose>

		<%-- 未ログイン --%>
		<c:when test="${empty sessionScope.user}">
			<div>
				<a href="${pageContext.request.contextPath}/views/cart.jsp"> カート
				</a><span>|</span> <a
					href="${pageContext.request.contextPath}/views/login-in.jsp">
					ログイン </a> <span>|</span> <a
					href="${pageContext.request.contextPath}/views/new-registration.jsp">
					新規会員登録 </a>
			</div>
		</c:when>

		<%-- 管理者 --%>
		<c:when test="${sessionScope.user.admin == 1}">
			<p class="welcome-text">ようこそ、管理者${sessionScope.user.lastName}さん</p>

			<div class="menu">
				<a href="${pageContext.request.contextPath}/views/update.jsp">
					変更 </a> <span>|</span> <a href="${pageContext.request.contextPath}/Admin.action">
					管理 </a> <span>|</span> <a
					href="${pageContext.request.contextPath}/views/logout-in.jsp">
					ログアウト </a>
			</div>
		</c:when>

		<%-- 一般ユーザー --%>
		<c:otherwise>
			<p class="welcome-text">ようこそ、${sessionScope.user.lastName}さん</p>
			<div class="menu">
			<a href="${pageContext.request.contextPath}/views/cart.jsp"> カート
			</a>
			<span>|</span>
				<a href="${pageContext.request.contextPath}/views/update.jsp">
					更新 </a> <span>|</span> <a href="${pageContext.request.contextPath}/views/delete.jsp">
					削除 </a> <span>|</span> <a
					href="${pageContext.request.contextPath}/views/logout-in.jsp">
					ログアウト </a>
			</div>
		</c:otherwise>

	</c:choose>
</div>










