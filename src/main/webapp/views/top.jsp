<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%
if (request.getAttribute("articlesList") == null) {
	response.sendRedirect(request.getContextPath() + "/Top.action");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
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
	<main>
		<div class="top-slider">

			<div class="top-slider-window">

				<div class="top-slider-track" id="topSliderTrack">

					<div class="top-slide">
						<img
							src="${pageContext.request.contextPath}/image/top-ad/top-ad-1.png"
							alt="トップ広告1">
					</div>

					<div class="top-slide">
						<img
							src="${pageContext.request.contextPath}/image/top-ad/top-ad-2.png"
							alt="トップ広告2">
					</div>

					<div class="top-slide">
						<img
							src="${pageContext.request.contextPath}/image/top-ad/top-ad-3.png"
							alt="トップ広告3">
					</div>

					<div class="top-slide">
						<img
							src="${pageContext.request.contextPath}/image/top-ad/top-ad-4.png"
							alt="トップ広告4">
					</div>

				</div>

			</div>

			<div class="top-slider-dots">
				<button type="button" class="top-slider-dot active" data-index="0"></button>
				<button type="button" class="top-slider-dot" data-index="1"></button>
				<button type="button" class="top-slider-dot" data-index="2"></button>
				<button type="button" class="top-slider-dot" data-index="3"></button>
			</div>

		</div>


		<%@include file="../HeaderFooter/category-nav.jsp"%>

		<section class="top-article-section">
			<h2 class="top-article-heading">おすすめ記事</h2>

			<c:if test="${empty articlesList}">
				<p class="top-article-empty">記事はまだありません。</p>
			</c:if>

			<div class="top-article-list">
				<c:forEach var="article" items="${articlesList}">

					<a class="top-article-card-link"
						href="${pageContext.request.contextPath}/ArticleDetail.action?articleId=${article.articleId}">

						<div class="top-article-card">

							<div class="top-article-image-area">
								<c:choose>
									<c:when test="${not empty article.imagePath}">
										<img class="top-article-image"
											src="${pageContext.request.contextPath}/${article.imagePath}"
											alt="${article.title}">
									</c:when>

									<c:otherwise>
										<div class="top-article-no-image">画像なし</div>
									</c:otherwise>
								</c:choose>
							</div>

							<div class="top-article-body">
								<p class="top-article-category">${article.category}</p>

								<h3 class="top-article-title">${article.title}</h3>

								<p class="top-article-summary">${article.summary}</p>
							</div>

						</div>

					</a>
				</c:forEach>
			</div>
		</section>

		<div>ランキングを貼ります。ページの右側に置きます。</div>


		<div>お知らせの一覧を表示します。</div>
	</main>
	<script>
	const sliderTrack = document.getElementById("topSliderTrack");
	const dots = document.querySelectorAll(".top-slider-dot");

	let currentIndex = 0;
	const slideCount = 4;

	function showSlide(index) {
		currentIndex = index;

		sliderTrack.style.transform = "translateX(-" + (currentIndex * 100) + "%)";

		dots.forEach(dot => {
			dot.classList.remove("active");
		});

		dots[currentIndex].classList.add("active");
	}

	function nextSlide() {
		currentIndex++;

		if (currentIndex >= slideCount) {
			currentIndex = 0;
		}

		showSlide(currentIndex);
	}

	let sliderTimer = setInterval(nextSlide, 4000);

	dots.forEach(dot => {
		dot.addEventListener("click", () => {
			const index = Number(dot.dataset.index);
			showSlide(index);

			clearInterval(sliderTimer);
			sliderTimer = setInterval(nextSlide, 4000);
		});
	});
</script>

	<script>
	const categoryItems = document.querySelectorAll(".top-category-item");

	categoryItems.forEach(item => {
		item.addEventListener("click", () => {
			item.classList.add("clicked");

			setTimeout(() => {
				item.classList.remove("clicked");
			}, 180);
		});
	});
</script>

</body>
</html>