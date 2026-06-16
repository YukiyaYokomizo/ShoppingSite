<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../HeaderFooter/header.jsp"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">

<div class="cart-page">

	<h2 class="cart-title">購入確認</h2>

	<c:set var="total" value="0" />

	<div class="cart-card">

		<h3>購入者情報</h3>

		<p>氏名：${sessionScope.user.lastName} ${sessionScope.user.firstName}</p>
		<p>住所：${sessionScope.user.address}</p>
		<p>メールアドレス：${sessionScope.user.mailAddress}</p>

		<table class="cart-table">
			<tr>
				<th>商品名</th>
				<th>価格</th>
				<th>数量</th>
				<th>小計</th>
			</tr>

			<c:forEach var="item" items="${sessionScope.cartList}">
				<tr>
					<td>${item.product.productName}</td>
					<td>${item.product.price}円</td>
					<td>${item.quantity}個</td>
					<td>${item.subtotal}円</td>
				</tr>

				<c:set var="total" value="${total + item.subtotal}" />
			</c:forEach>

			<tr class="cart-total-row">
				<th colspan="3">合計</th>
				<td>${total}円</td>
			</tr>
		</table>

		<div class="cart-button-area">

			<a href="${pageContext.request.contextPath}/views/cart.jsp">
				カートへ戻る
			</a>

			<form action="${pageContext.request.contextPath}/ConfirmPurchase.action"
				method="post">
				<input type="submit" value="購入を確定する">
			</form>

		</div>

	</div>

</div>

<%@include file="../HeaderFooter/footer.jsp"%>