<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%
if (session.getAttribute("user") == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">

<script>
	window.addEventListener("pageshow", function(event) {
		const nav = performance.getEntriesByType("navigation")[0];

		if (event.persisted || (nav && nav.type === "back_forward")) {
			location.reload();
		}
	});
</script>
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<div class="product-page">

		<c:if test="${not empty name}">
			<p class="admin-search-result">「${name}」の管理者検索結果</p>
		</c:if>

		<c:if test="${param.registered == '1' || param.updated == '1'}">
			<div id="register-message" class="register-toast">
				<c:choose>
					<c:when test="${param.registered == '1'}">
						商品を登録しました。
					</c:when>
					<c:when test="${param.updated == '1'}">
						商品を更新しました。
					</c:when>
				</c:choose>
			</div>
		</c:if>

		<div class="admin-manage-layout">

			<section class="admin-product-area">

				<h2 class="admin-product-title">商品管理</h2>

				<form class="admin-filter-form"
					action="${pageContext.request.contextPath}/ProductManage.action"
					method="get">

					<input type="hidden" name="name" value="${name}"> <input
						type="hidden" name="articleDisplayStatus"
						value="${articleDisplayStatus}"> <label for="category">カテゴリ：</label>
					<select id="category" name="category" onchange="this.form.submit()">
						<option value="" ${empty param.category ? 'selected' : ''}>
							すべて</option>

						<option value="sharp_pen"
							${param.category == 'sharp_pen' ? 'selected' : ''}>
							シャーペン</option>

						<option value="ballpen"
							${param.category == 'ballpen' ? 'selected' : ''}>ボールペン</option>

						<option value="multi_ballpen"
							${param.category == 'multi_ballpen' ? 'selected' : ''}>
							多機能ペン</option>

						<option value="other"
							${param.category == 'other' ? 'selected' : ''}>その他</option>
					</select> <label for="sort">並び替え：</label> <select id="sort" name="sort"
						onchange="this.form.submit()">
						<option value="new"
							${empty param.sort || param.sort == 'new' ? 'selected' : ''}>
							新着順</option>

						<option value="price_asc"
							${param.sort == 'price_asc' ? 'selected' : ''}>価格が安い順</option>

						<option value="price_desc"
							${param.sort == 'price_desc' ? 'selected' : ''}>価格が高い順</option>
					</select> <label for="displayStatus">表示状態：</label> <select
						id="displayStatus" name="displayStatus"
						onchange="this.form.submit()">

						<option value="visible"
							${empty param.displayStatus || param.displayStatus == 'visible' ? 'selected' : ''}>
							表示中のみ</option>

						<option value="hidden"
							${param.displayStatus == 'hidden' ? 'selected' : ''}>
							非表示のみ</option>

						<option value="all"
							${param.displayStatus == 'all' ? 'selected' : ''}>すべて</option>
					</select>

				</form>

				<div class="product-list admin-product-list">

					<a class="product-add-card"
						href="${pageContext.request.contextPath}/ProductRegister.action">
						<div class="product-add-icon">＋</div>
						<div class="product-add-text">商品を追加</div>
					</a>

					<c:forEach var="product" items="${productsList}">

						<div class="product-card admin-product-card">

							<p class="product-name">${product.productName}</p>

							<p class="product-category">
								カテゴリ：
								<c:choose>
									<c:when test="${product.category == 'sharp_pen'}">
										シャーペン
									</c:when>
									<c:when test="${product.category == 'ballpen'}">
										ボールペン
									</c:when>
									<c:when test="${product.category == 'multi_ballpen'}">
										多機能ペン
									</c:when>
									<c:when test="${product.category == 'other'}">
										その他
									</c:when>
									<c:otherwise>
										${product.category}
									</c:otherwise>
								</c:choose>
							</p>

							<p class="product-status">
								状態：
								<c:choose>
									<c:when test="${product.deleteFlag}">
										非表示
									</c:when>
									<c:otherwise>
										表示中
									</c:otherwise>
								</c:choose>
							</p>

							<p class="product-price">${product.price}円</p>
							<p class="product-stock">在庫：${product.stock}個</p>
							<p class="product-sales">売上数：${product.salesCount}個</p>

							<div class="admin-product-button-area">
								<a
									href="${pageContext.request.contextPath}/ProductEdit.action?productId=${product.productId}">
									編集 </a>
							</div>

						</div>

					</c:forEach>

				</div>

			</section>

			<aside class="admin-article-area">

				<h2 class="admin-article-title-main">記事管理</h2>

				<form class="admin-article-filter-form"
					action="${pageContext.request.contextPath}/ProductManage.action"
					method="get">

					<input type="hidden" name="name" value="${name}"> <input
						type="hidden" name="category" value="${category}"> <input
						type="hidden" name="sort" value="${sort}"> <input
						type="hidden" name="displayStatus" value="${displayStatus}">

					<label for="articleDisplayStatus">表示状態：</label> <select
						id="articleDisplayStatus" name="articleDisplayStatus"
						onchange="this.form.submit()">

						<option value="visible"
							${empty articleDisplayStatus || articleDisplayStatus == 'visible' ? 'selected' : ''}>
							表示中のみ</option>

						<option value="hidden"
							${articleDisplayStatus == 'hidden' ? 'selected' : ''}>
							非表示のみ</option>

						<option value="all"
							${articleDisplayStatus == 'all' ? 'selected' : ''}>すべて</option>

					</select>

				</form>

				<div class="admin-article-list">

					<a class="admin-article-add-card"
						href="${pageContext.request.contextPath}/ArticleRegister.action">
						<div class="admin-article-plus">＋</div>
						<div class="admin-article-add-text">記事を追加</div>
					</a>

					<c:if test="${empty adminArticlesList}">
						<p class="admin-article-empty">記事はまだありません。</p>
					</c:if>

					<c:forEach var="article" items="${adminArticlesList}">

						<div class="admin-article-card">

							<p class="admin-article-category">${article.category}</p>

							<h3 class="admin-article-title">${article.title}</h3>

							<p class="admin-article-summary">${article.summary}</p>

							<p class="admin-article-status">
								状態：
								<c:choose>
									<c:when test="${article.deleteFlag}">
							非表示
						</c:when>
									<c:otherwise>
							表示中
						</c:otherwise>
								</c:choose>
							</p>

							<a class="admin-article-edit-button"
								href="${pageContext.request.contextPath}/ArticleEdit.action?articleId=${article.articleId}">
								編集 </a>

						</div>

					</c:forEach>

				</div>

			</aside>
		</div>

	</div>

	<script>
		const registerMessage = document.getElementById("register-message");

		if (registerMessage) {
			setTimeout(() => {
				registerMessage.style.display = "none";
			}, 2000);
		}
	</script>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>