package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Article;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ArticlesDAO;
import jp.co.aforce.tool.Action;

public class ArticleEditAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:/Top.action";
		}

		String articleIdText = request.getParameter("articleId");

		if (articleIdText == null || articleIdText.isBlank()) {
			return "redirect:/ProductManage.action";
		}

		int articleId;

		try {
			articleId = Integer.parseInt(articleIdText);
		} catch (NumberFormatException e) {
			return "redirect:/ProductManage.action";
		}

		ArticlesDAO dao = new ArticlesDAO();
		Article article = dao.searchByIdForAdmin(articleId);

		if (article == null) {
			return "redirect:/ProductManage.action";
		}

		request.setAttribute("article", article);

		return "article-edit.jsp";
	}
}