<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%
if (request.getAttribute("article") == null) {
	response.sendRedirect(request.getContextPath() + "/Top.action");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title}</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/top.css">
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<main class="article-detail-page">

		<article class="article-detail-card">

			<p class="article-detail-category">${article.category}</p>

			<h1 class="article-detail-title">${article.title}</h1>
		
			<div class="article-detail-content">
				${article.content}
			</div>

			<div class="article-detail-button-area">
				<a href="${pageContext.request.contextPath}/Top.action">
					トップへ戻る
				</a>
			</div>

		</article>

	</main>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>