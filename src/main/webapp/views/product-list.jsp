<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../HeaderFooter/header.jsp"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">

<div class="product-page">

	<%@include file="../HeaderFooter/category-nav.jsp"%>

	<h2>商品一覧</h2>

	<div class="product-list">
		<c:forEach var="product" items="${productsList}">

			<a class="product-card-link"
				href="${pageContext.request.contextPath}/ProductDetail.action?productId=${product.productId}">

				<div class="product-card">
					<p class="product-name">${product.productName}</p>
					<p class="product-price">${product.price}円</p>
					<p class="product-stock">在庫：${product.stock}個</p>
					<p class="product-description">${product.description}</p>
				</div>

			</a>

		</c:forEach>
	</div>

</div>

<%@include file="../HeaderFooter/footer.jsp"%>