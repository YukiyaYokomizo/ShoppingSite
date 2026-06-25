package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Article;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ArticlesDAO;
import jp.co.aforce.tool.Action;

public class ArticleRegisterExecuteAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:/Top.action";
		}

		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		String imagePath = request.getParameter("imagePath");
		String deleteFlagText = request.getParameter("deleteFlag");

		if (title == null || title.isBlank()
				|| category == null || category.isBlank()
				|| content == null || content.isBlank()) {

			request.setAttribute("errorMessage", "タイトル、カテゴリ、本文は必須です。");

			request.setAttribute("title", title);
			request.setAttribute("category", category);
			request.setAttribute("summary", summary);
			request.setAttribute("content", content);
			request.setAttribute("imagePath", imagePath);
			request.setAttribute("deleteFlag", deleteFlagText);

			return "article-register.jsp";
		}

		Article article = new Article();

		article.setTitle(title);
		article.setCategory(category);
		article.setSummary(summary);
		article.setContent(content);
		article.setImagePath(imagePath);
		article.setDeleteFlag("true".equals(deleteFlagText));

		ArticlesDAO dao = new ArticlesDAO();

		int count = dao.insert(article);

		if (count != 1) {
			request.setAttribute("errorMessage", "記事の登録に失敗しました。");
			return "article-register.jsp";
		}

		return "redirect:/ProductManage.action?articleRegistered=1";
	}
}