<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集確認</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<div class="product-detail-page">

		<h2>商品編集確認</h2>

		<p class="confirm-message">この内容で商品を更新しますか？</p>

		<div class="product-detail-card">

			<div class="product-detail-image-area">
				<c:choose>
					<c:when test="${not empty product.imagePath}">
						<img class="product-detail-image"
							src="${pageContext.request.contextPath}/${product.imagePath}"
							alt="${product.productName}">
					</c:when>

					<c:otherwise>
						<div class="no-image">画像なし</div>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="product-detail-info">

				<h2>${product.productName}</h2>

				<p class="product-detail-price">${product.price}円</p>

				<p>
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

				<p>在庫：${product.stock}個</p>

				<p>売上数：${product.salesCount}個</p>

				<p>
					表示状態：
					<c:choose>
						<c:when test="${product.deleteFlag}">
			非表示
		</c:when>
						<c:otherwise>
			表示中
		</c:otherwise>
					</c:choose>
				</p>

				<p class="product-detail-description">${product.description}</p>

				<div class="product-detail-button-area">

					<button type="button" onclick="history.back();">戻る</button>

					<form
						action="${pageContext.request.contextPath}/ProductUpdate.action"
						method="post">

						<input type="hidden" name="productId" value="${product.productId}">
						<input type="hidden" name="productName"
							value="${product.productName}"> <input type="hidden"
							name="category" value="${product.category}"> <input
							type="hidden" name="price" value="${product.price}"> <input
							type="hidden" name="stock" value="${product.stock}"> <input
							type="hidden" name="description" value="${product.description}">
						<input type="hidden" name="imagePath" value="${product.imagePath}">
						<input type="hidden" name="deleteFlag"
							value="${product.deleteFlag}"> <input type="submit"
							value="更新する">
					</form>

				</div>

			</div>

		</div>

	</div>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>