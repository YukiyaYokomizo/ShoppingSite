<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../HeaderFooter/header.jsp"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
<title>商品一覧</title>
<div class="product-page">

	<%@include file="../HeaderFooter/category-nav.jsp"%>

	<h2>
		<c:choose>
			<c:when test="${param.category == 'sharp_pen'}">シャーペン</c:when>
			<c:when test="${param.category == 'ballpen'}">ボールペン</c:when>
			<c:when test="${param.category == 'multi_ballpen'}">多機能ペン</c:when>
			<c:when test="${param.category == 'other'}">その他</c:when>
			<c:otherwise>商品一覧</c:otherwise>
		</c:choose>
	</h2>

	<form class="sort-form"
		action="${pageContext.request.contextPath}/ProductList.action"
		method="get">

		<c:if test="${not empty param.category}">
			<input type="hidden" name="category" value="${param.category}">
		</c:if>

		<label for="sort">並べ替え：</label> <select id="sort" name="sort"
			onchange="this.form.submit()">
			<option value="new"
				${empty param.sort || param.sort == 'new' ? 'selected' : ''}>
				新着順</option>

			<option value="price_asc"
				${param.sort == 'price_asc' ? 'selected' : ''}>価格が安い順</option>

			<option value="price_desc"
				${param.sort == 'price_desc' ? 'selected' : ''}>価格が高い順</option>
		</select>

	</form>

	<div class="product-list">
		<c:forEach var="product" items="${productsList}">

			<a class="product-card-link"
				href="${pageContext.request.contextPath}/ProductDetail.action?productId=${product.productId}">

				<div class="product-card">

					<div class="product-card-image-area">
						<c:choose>
							<c:when test="${not empty product.imagePath}">
								<img class="product-card-image"
									src="${pageContext.request.contextPath}/${product.imagePath}"
									alt="${product.productName}">
							</c:when>

							<c:otherwise>
								<div class="product-card-no-image">画像なし</div>
							</c:otherwise>
						</c:choose>
					</div>

					<p class="product-name">${product.productName}</p>

					<p class="product-price">${product.price}円</p>

					<p class="product-stock">在庫：${product.stock}個</p>

				</div>
			</a>

		</c:forEach>
	</div>

</div>

<%@include file="../HeaderFooter/footer.jsp"%>