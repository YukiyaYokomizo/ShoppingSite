package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

/**
 * Servlet implementation class LoginAction
 */
public class LoginAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		HttpSession session=request.getSession();
		
		String memberId=request.getParameter("id");
		String password=request.getParameter("password");
		UsersDAO dao=new UsersDAO();
		Users user=dao.search(memberId, password);
		
		if(user != null) {
			session.setAttribute("user", user);
			return "user-menu.jsp";
		}
		return "login-error.jsp";
	}
}
