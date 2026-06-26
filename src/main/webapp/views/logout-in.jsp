<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト確認</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/message.css">
</head>

<body>

	<header class="header">
		<%@include file="../HeaderFooter/header.jsp"%>
	</header>

	<main class="message-page">

		<div class="message-card">

			<div class="message-icon message-icon-warning">
				?
			</div>

			<h2 class="message-title">
				ログアウトしますか？
			</h2>

			<p class="message-text">
				現在のアカウントからログアウトします。
			</p>

			<div class="message-button-area">

				<a class="message-button message-button-danger"
					href="${pageContext.request.contextPath}/Logout.action">
					ログアウト
				</a>

				<a class="message-button message-button-sub"
					href="${pageContext.request.contextPath}/Top.action">
					トップへ戻る
				</a>

			</div>

		</div>

	</main>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>