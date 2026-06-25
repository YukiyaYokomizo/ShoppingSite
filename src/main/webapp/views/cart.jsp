<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../HeaderFooter/header.jsp"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cart.css">

<div class="cart-page">

	<h2 class="cart-title">カート</h2>

	<c:if test="${not empty errorMessage}">
		<p class="cart-error-message">${errorMessage}</p>
	</c:if>

	<c:choose>

		<c:when test="${empty sessionScope.cartList}">
			<div class="cart-empty">
				<p>カートに商品はありません。</p>

				<a href="${pageContext.request.contextPath}/ProductList.action">
					商品一覧へ戻る </a>
			</div>
		</c:when>

		<c:otherwise>

			<c:set var="total" value="0" />

			<div class="cart-card">

				<table class="cart-table">
					<tr>
						<th>商品名</th>
						<th>価格</th>
						<th>数量</th>
						<th>小計</th>
						<th>操作</th>
					</tr>

					<c:forEach var="item" items="${sessionScope.cartList}">
						<tr>
							<td>${item.product.productName}</td>

							<td>${item.product.price}円</td>

							<td>
								<form class="cart-stepper-form"
									action="${pageContext.request.contextPath}/CartQuantityUpdate.action"
									method="post">

									<input type="hidden" name="productId"
										value="${item.product.productId}">

									<button type="submit" name="mode" value="minus"
										class="cart-stepper-btn"
										${item.quantity <= 1 ? 'disabled' : ''}>－</button>

									<span class="cart-quantity-value"> ${item.quantity} </span>

									<button type="submit" name="mode" value="plus"
										class="cart-stepper-btn"
										${item.quantity >= item.product.stock ? 'disabled' : ''}>
										＋</button>
								</form>
							</td>

							<td>${item.subtotal}円</td>

							<td>
								<form
									action="${pageContext.request.contextPath}/CartRemove.action"
									method="post" onsubmit="return confirm('この商品をカートから削除しますか？');">

									<input type="hidden" name="productId"
										value="${item.product.productId}"> <input
										type="submit" value="削除" class="cart-remove-button">
								</form>
							</td>
						</tr>

						<c:set var="total" value="${total + item.subtotal}" />
					</c:forEach>

					<tr class="cart-total-row">
						<th colspan="3">合計</th>
						<td>${total}円</td>
						<td></td>
					</tr>
				</table>

				<div class="cart-button-area">

					<a href="${pageContext.request.contextPath}/ProductList.action">
						商品一覧へ戻る </a>

					<c:choose>
						<c:when test="${empty sessionScope.user}">
							<a href="${pageContext.request.contextPath}/views/login-in.jsp">
								ログインして購入手続きへ </a>
						</c:when>

						<c:otherwise>
							<a
								href="${pageContext.request.contextPath}/PurchaseConfirm.action">
								購入手続きへ </a>
						</c:otherwise>
					</c:choose>

				</div>

			</div>

		</c:otherwise>

	</c:choose>

</div>

<%@include file="../HeaderFooter/footer.jsp"%>