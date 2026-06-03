package jp.co.aforce.servlet;

import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

public class InsertAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String memberId = request.getParameter("id");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailaddress");

		UsersDAO dao = new UsersDAO();
		
		try {
			dao.insert(memberId, password, lastName, firstName, address, mailAddress);
			return "complete-register.jsp";

		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("errorMessage", "このIDはすでに使用されています。");
			return "error-register.jsp";
		}
	}
}
