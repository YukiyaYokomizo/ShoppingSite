package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.tool.Action;

public class ArticleRegisterAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:/Top.action";
		}

		return "article-register.jsp";
	}
}