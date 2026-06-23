package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Article;
import jp.co.aforce.dao.ArticlesDAO;
import jp.co.aforce.tool.Action;

public class TopAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArticlesDAO dao = new ArticlesDAO();

		List<Article> articlesList = dao.searchForTop();
		
		request.setAttribute("articlesList", articlesList);

		return "top.jsp";
	}
}