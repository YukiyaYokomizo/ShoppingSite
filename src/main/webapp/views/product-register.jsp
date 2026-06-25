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
<title>商品登録</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<div class="product-page">

		<h2>商品登録</h2>

		<div class="product-form-card">

			<form action="${pageContext.request.contextPath}/ProductInsertConfirm.action"
				method="post"
				enctype="multipart/form-data">

				<div class="form-row">
					<label for="productName">商品名</label>
					<input type="text" id="productName" name="productName" required>
				</div>

				<div class="form-row">
					<label for="category">カテゴリ</label>
					<select id="category" name="category" required>
						<option value="">選択してください</option>
						<option value="sharp_pen">シャーペン</option>
						<option value="ballpen">ボールペン</option>
						<option value="multi_ballpen">多機能ペン</option>
						<option value="other">その他</option>
					</select>
				</div>

				<div class="form-row">
					<label for="price">価格</label>
					<input type="number" id="price" name="price" min="0" required>
				</div>

				<div class="form-row">
					<label for="stock">在庫</label>
					<input type="number" id="stock" name="stock" min="0" required>
				</div>

				<div class="form-row">
					<label for="description">説明</label>
					<textarea id="description" name="description" rows="5" required></textarea>
				</div>

				<div class="form-row">
					<label for="imageFile">商品画像</label>
					<input type="file" id="imageFile" name="imageFile" accept="image/*">
				</div>

				<div class="form-button-area">
					<a href="${pageContext.request.contextPath}/ProductManage.action">
						戻る
					</a>

					<input type="submit" value="確認へ">
				</div>

			</form>

		</div>

	</div>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>