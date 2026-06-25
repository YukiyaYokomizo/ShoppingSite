package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Article;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ArticlesDAO;
import jp.co.aforce.tool.Action;

public class ArticleUpdateExecuteAction extends Action {

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

		String articleIdText = request.getParameter("articleId");
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		String imagePath = request.getParameter("imagePath");
		String deleteFlagText = request.getParameter("deleteFlag");

		if (articleIdText == null || articleIdText.isBlank()
				|| title == null || title.isBlank()
				|| category == null || category.isBlank()
				|| content == null || content.isBlank()) {
			return "redirect:/ProductManage.action";
		}

		int articleId;

		try {
			articleId = Integer.parseInt(articleIdText);
		} catch (NumberFormatException e) {
			return "redirect:/ProductManage.action";
		}

		Article article = new Article();

		article.setArticleId(articleId);
		article.setTitle(title);
		article.setCategory(category);
		article.setSummary(summary);
		article.setContent(content);
		article.setImagePath(imagePath);
		article.setDeleteFlag("true".equals(deleteFlagText));

		ArticlesDAO dao = new ArticlesDAO();

		int count = dao.update(article);

		if (count != 1) {
			return "redirect:/ProductManage.action";
		}

		return "redirect:/ProductManage.action?articleUpdated=1";
	}
}