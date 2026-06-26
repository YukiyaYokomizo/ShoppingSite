<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト確認エラー</title>

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
				!
			</div>

			<h2 class="message-title">
				すでにログアウトしています
			</h2>

			<p class="message-text">
				ログイン状態ではありません。<br>
				もう一度利用する場合はログインしてください。
			</p>

			<div class="message-button-area">

				<a class="message-button message-button-main"
					href="${pageContext.request.contextPath}/Top.action">
					トップへ戻る
				</a>

				<a class="message-button message-button-sub"
					href="${pageContext.request.contextPath}/views/login-in.jsp">
					ログイン画面へ
				</a>

			</div>

		</div>

	</main>

	<%@include file="../HeaderFooter/footer.jsp"%>

</body>
</html>