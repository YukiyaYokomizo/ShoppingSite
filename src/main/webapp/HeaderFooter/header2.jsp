<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Yomogi&display=swap"
	rel="stylesheet">

<%-- サイトのロゴ --%>
<div class="site-logo">
	<c:choose>
		<c:when
			test="${not empty sessionScope.user && sessionScope.user.admin == 1}">
			<a href="${pageContext.request.contextPath}/ProductManage.action"> ロゴ入れるよ
			</a>
		</c:when>

		<c:otherwise>
			<a href="${pageContext.request.contextPath}/Top.action">
				ロゴ入れるよ </a>
		</c:otherwise>
	</c:choose>
</div>


