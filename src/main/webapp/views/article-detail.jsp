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
	href="${pageContext.request.contextPath}/css/article-detail.css">

</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<main class="article-page">

		<div class="article-container">

			<div class="article-breadcrumb">
				<a href="${pageContext.request.contextPath}/Top.action">トップ</a> <span>›</span>
				<a
					href="${pageContext.request.contextPath}/Top.action#recommended-articles">
					おすすめ記事 </a> <span>›</span> <span class="article-breadcrumb-current">${article.title}</span>
			</div>
			<article class="article-card">

				<section class="article-hero">
					<div class="article-hero-inner">
						<h1 class="article-title">${article.title}</h1>

						<c:if test="${not empty article.summary}">
							<p class="article-summary">${article.summary}</p>
						</c:if>

					</div>
				</section>

				<section class="article-content-section">

					<div class="article-point-box">
						<div class="article-point-label">POINT</div>
						<div class="article-point-text">
							文房具選びに迷ったときに、すぐ参考にできる読みやすいガイドです。</div>
					</div>

					<div class="article-body-card">
						<h2 class="article-section-title">記事本文</h2>
						<div class="article-content-text">${article.content}</div>
					</div>

				</section>

				<div class="article-back-area">
					<a class="article-back-button"
						href="${pageContext.request.contextPath}/Top.action"> トップへ戻る </a>
				</div>

			</article>

		</div>

	</main>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>