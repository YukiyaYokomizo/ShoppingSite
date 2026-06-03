<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
if (session.getAttribute("user") == null) {
	response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
	return;
}
%>

<%@include file="../header.html"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">

<script>
window.addEventListener("pageshow", function(event) {
    const nav = performance.getEntriesByType("navigation")[0];

    if (event.persisted || (nav && nav.type === "back_forward")) {
        location.reload();
    }
});
</script>

<form class="login-form">
	<p class="welcome-text">ようこそ、管理者${user.lastName}さん</p>
	<div class="menu">
		<a href="${pageContext.request.contextPath}/views/update.jsp">変更</a>
		<a href="${pageContext.request.contextPath}/views/administration.jsp">管理</a>
		<a href="${pageContext.request.contextPath}/views/logout-in.jsp">ログアウト</a>
	</div>
</form>

<%@include file="../footer.html"%>