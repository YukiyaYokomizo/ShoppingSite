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
			<a href="${pageContext.request.contextPath}/ProductManage.action"
				class="site-logo-link"> <img
				src="${pageContext.request.contextPath}/image/logo.png" alt="文具図鑑"
				class="site-logo-image">
			</a>
		</c:when>

		<c:otherwise>
			<a href="${pageContext.request.contextPath}/Top.action"
				class="site-logo-link"> <img
				src="${pageContext.request.contextPath}/image/logo.png" alt="文具図鑑"
				class="site-logo-image">
			</a>
		</c:otherwise>
	</c:choose>
</div>
<%-- 検索 --%>
<div class="site-search">

	<c:choose>

		<%-- 管理者は商品管理画面で検索 --%>
		<c:when
			test="${not empty sessionScope.user && sessionScope.user.admin == 1}">
			<form
				action="${pageContext.request.contextPath}/ProductManage.action"
				method="get">

				<input type="text" name="name" value="${param.name}"
					placeholder="商品を検索"> <input type="hidden"
					name="displayStatus" value="all"> <input type="submit"
					value="検索">
			</form>
		</c:when>

		<%-- 一般ユーザー・未ログインはユーザー用検索 --%>
		<c:otherwise>
			<form
				action="${pageContext.request.contextPath}/ProductSearch.action"
				method="get">

				<input type="text" name="name" value="${param.name}"
					placeholder="商品を検索"> <input type="submit" value="検索">
			</form>
		</c:otherwise>

	</c:choose>

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
					変更 </a> <span>|</span> <a
					href="${pageContext.request.contextPath}/Admin.action"> 管理 </a> <span>|</span>
				<a href="${pageContext.request.contextPath}/views/logout-in.jsp">
					ログアウト </a>
			</div>
		</c:when>

		<%-- 一般ユーザー --%>
		<c:otherwise>
			<p class="welcome-text">ようこそ、${sessionScope.user.lastName}さん</p>

			<div class="menu">
				<a href="${pageContext.request.contextPath}/views/cart.jsp"> カート
				</a> <span>|</span> <a
					href="${pageContext.request.contextPath}/PurchaseHistory.action">
					購入履歴 </a> <span>|</span> <a
					href="${pageContext.request.contextPath}/views/update.jsp"> 更新
				</a> <span>|</span> <a
					href="${pageContext.request.contextPath}/views/delete.jsp"> 削除
				</a> <span>|</span> <a
					href="${pageContext.request.contextPath}/views/logout-in.jsp">
					ログアウト </a>
			</div>
		</c:otherwise>
	</c:choose>
</div>










