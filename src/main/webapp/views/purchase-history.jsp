<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

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
<title>購入履歴</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<div class="purchase-history-page">

		<h2>購入履歴</h2>

		<c:choose>
			<c:when test="${empty orderList}">
				<p class="purchase-history-empty">
					購入履歴はまだありません。
				</p>
			</c:when>

			<c:otherwise>

				<c:forEach var="order" items="${orderList}">

					<div class="purchase-history-card">

						<div class="purchase-history-header">
							<div>
								<p class="purchase-history-order-id">
									注文番号：${order.orderId}
								</p>

								<p class="purchase-history-date">
									注文日時：
									<fmt:formatDate value="${order.orderDate}"
										pattern="yyyy年MM月dd日 HH:mm" />
								</p>
							</div>

							<p class="purchase-history-total">
								合計：${order.totalPrice}円
							</p>
						</div>

						<table class="purchase-history-table">
							<thead>
								<tr>
									<th>商品名</th>
									<th>単価</th>
									<th>数量</th>
									<th>小計</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="detail" items="${order.detailList}">
									<tr>
										<td>${detail.productName}</td>
										<td>${detail.price}円</td>
										<td>${detail.quantity}個</td>
										<td>${detail.subtotal}円</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>

				</c:forEach>

			</c:otherwise>
		</c:choose>

		<div class="purchase-history-button-area">
			<a href="${pageContext.request.contextPath}/Top.action">
				トップへ戻る
			</a>
		</div>

	</div>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>