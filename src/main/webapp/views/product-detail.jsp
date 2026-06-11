<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../HeaderFooter/header.jsp"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">

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
					<div class="no-image">
						画像なし
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="product-detail-info">

			<h2>${product.productName}</h2>

			<p class="product-detail-price">
				${product.price}円
			</p>

			<p>
				カテゴリ：${product.category}
			</p>

			<p>
				在庫：${product.stock}個
			</p>

			<p>
				売上数：${product.salesCount}
			</p>

			<p class="product-detail-description">
				${product.description}
			</p>

			<div class="product-detail-button-area">
				<form action="${pageContext.request.contextPath}/CartAdd.action"
					method="post">
					<input type="hidden" name="productId" value="${product.productId}">
					<input type="hidden" name="quantity" value="1">
					<input type="submit" value="カートに入れる">
				</form>

				<a href="${pageContext.request.contextPath}/ProductList.action">
					商品一覧へ戻る
				</a>
			</div>

		</div>

	</div>

</div>

<%@include file="../HeaderFooter/footer.jsp"%>