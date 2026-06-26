<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>

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

		<h2>検索結果</h2>

		<c:choose>
			<c:when test="${empty name}">
				<p class="search-result-message">検索キーワードが入力されていません。</p>
			</c:when>

			<c:otherwise>
				<p class="search-result-message">「${name}」の検索結果</p>
			</c:otherwise>
		</c:choose>

		<form class="sort-form"
			action="${pageContext.request.contextPath}/ProductSearch.action"
			method="get">

			<input type="hidden" name="name" value="${name}"> <label
				for="sort">並べ替え：</label> <select id="sort" name="sort"
				onchange="this.form.submit()">
				<option value="new" ${empty sort || sort == 'new' ? 'selected' : ''}>
					新着順</option>

				<option value="price_asc" ${sort == 'price_asc' ? 'selected' : ''}>
					価格が安い順</option>

				<option value="price_desc" ${sort == 'price_desc' ? 'selected' : ''}>
					価格が高い順</option>
			</select>

		</form>

		<c:choose>
			<c:when test="${empty productsList}">
				<p class="search-result-empty">該当する商品はありません。</p>
			</c:when>

			<c:otherwise>

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

			</c:otherwise>
		</c:choose>

	</div>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>