<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集</title>

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

		<h2>商品編集</h2>

		<div class="product-form-card">

			<form
				action="${pageContext.request.contextPath}/ProductUpdateConfirm.action"
				method="post">

				<input type="hidden" name="productId" value="${product.productId}">

				<div class="form-row">
					<label for="productName">商品名</label> <input type="text"
						id="productName" name="productName" value="${product.productName}"
						required>
				</div>

				<div class="form-row">
					<label for="category">カテゴリ</label> <select id="category"
						name="category" required>
						<option value="">選択してください</option>

						<option value="sharp_pen"
							${product.category == 'sharp_pen' ? 'selected' : ''}>
							シャーペン</option>

						<option value="ballpen"
							${product.category == 'ballpen' ? 'selected' : ''}>
							ボールペン</option>

						<option value="multi_ballpen"
							${product.category == 'multi_ballpen' ? 'selected' : ''}>
							多機能ペン</option>

						<option value="other"
							${product.category == 'other' ? 'selected' : ''}>その他</option>
					</select>
				</div>

				<div class="form-row">
					<label for="price">価格</label> <input type="number" id="price"
						name="price" value="${product.price}" min="0" required>
				</div>

				<div class="form-row">
					<label for="stock">在庫</label> <input type="number" id="stock"
						name="stock" value="${product.stock}" min="0" required>
				</div>

				<div class="form-row">
					<label for="description">説明</label>
					<textarea id="description" name="description" rows="5" required>${product.description}</textarea>
				</div>

				<div class="form-row">
					<label for="imagePath">画像パス</label> <input type="text"
						id="imagePath" name="imagePath" value="${product.imagePath}"
						placeholder="例：image/products/sample.png">
				</div>

				<div class="form-row">
					<label>表示状態</label>

					<div class="switch-row">
						<span class="switch-text">非表示</span> <label class="switch">
							<input type="checkbox" id="displaySwitch"
							${!product.deleteFlag ? 'checked' : ''}> <span
							class="slider"></span>
						</label> <span class="switch-text">表示中</span>
					</div>

					<input type="hidden" id="deleteFlag" name="deleteFlag"
						value="${product.deleteFlag}">
				</div>

				<div class="form-button-area">
					<a href="${pageContext.request.contextPath}/ProductManage.action">
						戻る </a> <input type="submit" value="確認へ">
				</div>

			</form>

		</div>

	</div>

	<script>
	const displaySwitch = document.getElementById("displaySwitch");
	const deleteFlag = document.getElementById("deleteFlag");

	if (displaySwitch && deleteFlag) {
		displaySwitch.addEventListener("change", () => {
			if (displaySwitch.checked) {
				deleteFlag.value = "false"; // 表示中
			} else {
				deleteFlag.value = "true";  // 非表示
			}
		});
	}
</script>

	<%@include file="../HeaderFooter/footer.jsp"%>
</body>
</html>