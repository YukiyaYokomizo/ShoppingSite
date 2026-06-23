<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--<nav class="product-category-nav">-->
<!--	<a href="${pageContext.request.contextPath}/ProductList.action?category=sharp_pen">-->
<!--		シャーペン-->
<!--	</a>-->

<!--	<a href="${pageContext.request.contextPath}/ProductList.action?category=ballpen">-->
<!--		ボールペン-->
<!--	</a>-->

<!--	<a href="${pageContext.request.contextPath}/ProductList.action?category=multi_ballpen">-->
<!--		多機能ペン-->
<!--	</a>-->

<!--	<a href="${pageContext.request.contextPath}/ProductList.action?category=other">-->
<!--		その他-->
<!--	</a>-->
	
<!--</nav>-->


<nav class="category-nav">

	<a class="category-nav-item"
		href="${pageContext.request.contextPath}/ProductList.action?category=sharp_pen">
		<span class="category-nav-icon">✏️</span>
		<span class="category-nav-text">シャーペン</span>
		<span class="category-nav-arrow">›</span>
	</a>

	<a class="category-nav-item"
		href="${pageContext.request.contextPath}/ProductList.action?category=ballpen">
		<span class="category-nav-icon">🖊️</span>
		<span class="category-nav-text">ボールペン</span>
		<span class="category-nav-arrow">›</span>
	</a>

	<a class="category-nav-item"
		href="${pageContext.request.contextPath}/ProductList.action?category=multi_ballpen">
		<span class="category-nav-icon">🌈</span>
		<span class="category-nav-text">多機能ペン</span>
		<span class="category-nav-arrow">›</span>
	</a>

	<a class="category-nav-item"
		href="${pageContext.request.contextPath}/ProductList.action?category=other">
		<span class="category-nav-icon">📎</span>
		<span class="category-nav-text">その他</span>
		<span class="category-nav-arrow">›</span>
	</a>

</nav>