<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../HeaderFooter/header.jsp"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product-detail-fix.css">
<div class="product-detail-page">

	<%@include file="../HeaderFooter/category-nav.jsp"%>

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
					<c:when test="${product.category == 'sharp_pen'}">シャーペン</c:when>
					<c:when test="${product.category == 'ballpen'}">ボールペン</c:when>
					<c:when test="${product.category == 'multi_ballpen'}">多機能ペン</c:when>
					<c:when test="${product.category == 'other'}">その他</c:when>
					<c:otherwise>${product.category}</c:otherwise>
				</c:choose>
			</p>
			<p>在庫：${product.stock}個</p>

			<p>売上数：${product.salesCount}</p>

			<p class="product-detail-description">${product.description}</p>

			<div class="product-detail-button-area">

				<c:choose>
					<c:when test="${product.stock > 0}">
						<form id="add-cart-form"
						action="${pageContext.request.contextPath}/AddToCart.action"
							method="post">
							<input type="hidden" name="productId"
								value="${product.productId}"> <label for="quantity">数量：</label>
							<input type="number" id="quantity" name="quantity" value="1"
								min="1" max="${product.stock}"> <input type="submit"
								value="カートに入れる">
						</form>
					</c:when>

					<c:otherwise>
						<p class="sold-out">在庫切れです</p>
					</c:otherwise>
				</c:choose>

				<a href="${pageContext.request.contextPath}/ProductList.action">
					商品一覧へ戻る </a>

			</div>

		</div>

	</div>

</div>
<c:if test="${not empty cartMessage}">
	<div id="cart-message" class="cart-message show">
		${cartMessage}
	</div>

	<script>
		setTimeout(function() {
			const cartMessage = document.getElementById("cart-message");
			if (cartMessage != null) {
				cartMessage.classList.remove("show");
			}
		}, 2000);
	</script>
</c:if>
<%@include file="../HeaderFooter/footer.jsp"%>








