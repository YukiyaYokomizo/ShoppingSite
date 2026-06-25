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
<title>記事追加</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<div class="product-page article-form-page">

		<h2>記事追加</h2>

		<c:if test="${not empty errorMessage}">
			<p class="article-form-error">${errorMessage}</p>
		</c:if>

		<form class="article-form"
			action="${pageContext.request.contextPath}/ArticleRegisterConfirm.action"
			method="post">

			<div class="article-form-row">
				<label for="title">タイトル</label>
				<input type="text" id="title" name="title" value="${title}" required>
			</div>

			<div class="article-form-row">
				<label for="category">カテゴリ</label>
				<input type="text" id="category" name="category" value="${category}"
					placeholder="例：guide / recommend / news" required>
			</div>

			<div class="article-form-row">
				<label for="summary">概要</label>
				<textarea id="summary" name="summary" rows="3">${summary}</textarea>
			</div>

			<div class="article-form-row">
				<label for="imagePath">画像パス</label>
				<input type="text" id="imagePath" name="imagePath"
					value="${imagePath}" placeholder="例：image/articles/sample.png">
			</div>

			<div class="article-form-row">
				<label for="content">本文</label>
				<textarea id="content" name="content" rows="10" required>${content}</textarea>
			</div>

			<div class="article-form-check-row">
				<label>
					<input type="checkbox" name="deleteFlag" value="true"
						${deleteFlag == 'true' ? 'checked' : ''}>
					非表示で登録する
				</label>
			</div>

			<div class="article-form-button-area">
				<a href="${pageContext.request.contextPath}/ProductManage.action">
					戻る
				</a>

				<input type="submit" value="登録する">
			</div>

		</form>

	</div>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>