<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<form class="login-card"
	action="${pageContext.request.contextPath}/Login.action" method="post">

	<h2>ログイン</h2>

	<p>
		<label for="id">ID</label> <input type="text" id="id" name="id">
	</p>

	<p>
		<label for="password">パスワード</label> <input type="password"
			id="password" name="password">
	</p>

	<p class="button-area">
		<input type="submit" value="ログイン">
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/views/forget-pass.jsp">パスワードを忘れた方はコチラ</a>
	</p>
</form>

<div class="chatbot">
    <button class="chatbot-button" onclick="toggleChat()">💬</button>

    <div class="chatbot-window" id="chatbotWindow">
        <div class="chatbot-header">
            チャットサポート
        </div>

        <div class="chatbot-body">
            <p>こんにちは！ご用件をお選びください！</p>
            <button>ログインについて</button>
            <button>会員登録について</button>
            <button>退会について</button>
        </div>
    </div>
</div>

<script>
function toggleChat() {
    const window = document.getElementById("chatbotWindow");
    window.classList.toggle("show");
}
</script>

<%@include file="../footer.html"%>
