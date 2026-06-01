package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.tool.Action;

public class LogoutAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session=request.getSession(false);
		String url;
		
		if(session == null || session.getAttribute("user")==null){
			url="logout-error.jsp";
		}else{
			session.invalidate();
			url="logout-out.jsp";
		}
		return url;
	}
}
