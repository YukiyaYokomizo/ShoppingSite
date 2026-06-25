<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%
if (session.getAttribute("user") == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}

if (request.getAttribute("article") == null) {
	response.sendRedirect(request.getContextPath() + "/ProductManage.action");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>記事更新確認</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/top.css">
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<div class="product-page article-form-page">

		<h2>記事更新確認</h2>

		<p class="article-confirm-lead">
			この内容で記事を更新します。
		</p>

		<div class="article-confirm-preview-area">

			<h3 class="article-confirm-heading">トップ画面での表示イメージ</h3>

			<div class="top-article-list article-confirm-card-list">

				<div class="top-article-card">

					<div class="top-article-image-area">
						<c:choose>
							<c:when test="${not empty article.imagePath}">
								<img class="top-article-image"
									src="${pageContext.request.contextPath}/${article.imagePath}"
									alt="${article.title}">
							</c:when>

							<c:otherwise>
								<div class="top-article-no-image">画像なし</div>
							</c:otherwise>
						</c:choose>
					</div>

					<div class="top-article-body">
						<p class="top-article-category">${article.category}</p>

						<h3 class="top-article-title">${article.title}</h3>

						<p class="top-article-summary">${article.summary}</p>
					</div>

				</div>

			</div>

		</div>

		<div class="article-confirm-detail-area">

			<h3 class="article-confirm-heading">記事詳細での表示イメージ</h3>

			<article class="article-detail-card article-confirm-detail-card">

				<p class="article-detail-category">${article.category}</p>

				<h1 class="article-detail-title">${article.title}</h1>

				<c:if test="${not empty article.summary}">
					<p class="article-detail-summary">${article.summary}</p>
				</c:if>

				<div class="article-detail-content">
					${article.content}
				</div>

			</article>

		</div>

		<form class="article-confirm-form"
			action="${pageContext.request.contextPath}/ArticleUpdateExecute.action"
			method="post">

			<input type="hidden" name="articleId" value="${article.articleId}">
			<input type="hidden" name="title" value="${article.title}">
			<input type="hidden" name="category" value="${article.category}">
			<input type="hidden" name="summary" value="${article.summary}">
			<input type="hidden" name="content" value="${article.content}">
			<input type="hidden" name="imagePath" value="${article.imagePath}">

			<c:if test="${article.deleteFlag}">
				<input type="hidden" name="deleteFlag" value="true">
			</c:if>

			<div class="article-form-button-area">
				<button type="button" class="article-back-button"
					onclick="history.back();">
					修正する
				</button>

				<input type="submit" value="更新する">
			</div>

		</form>

	</div>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>