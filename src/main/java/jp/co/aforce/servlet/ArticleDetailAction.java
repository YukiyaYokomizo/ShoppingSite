package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Article;
import jp.co.aforce.dao.ArticlesDAO;
import jp.co.aforce.tool.Action;

public class ArticleDetailAction extends Action{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String articleIdText = request.getParameter("articleId");
		
		if (articleIdText == null || articleIdText.isBlank()) {
			return "redidect*/Top.action";
		}
		int articleId;
		try {
			articleId = Integer.parseInt(articleIdText);
		}catch(NumberFormatException e) {
			return "redirect:?Top.action";
		}
		
		ArticlesDAO dao = new ArticlesDAO();
		Article article =dao.searchByIdForUser(articleId);
		
		if(article == null) {
			return "redirect:/Top.action";
		}
		
		request.setAttribute("article", article);
		return "article-detail.jsp";
	}
	
}
