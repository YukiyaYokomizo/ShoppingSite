<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<div>記事を貼ります。</div>
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
</body>
</html>