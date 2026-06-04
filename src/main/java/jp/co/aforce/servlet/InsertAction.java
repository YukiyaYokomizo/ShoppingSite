package jp.co.aforce.servlet;

import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
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
			Users user = new Users();
			user.setMemberId(memberId);
			user.setPassword(password);
			user.setLastName(lastName);
			user.setFirstName(firstName);
			user.setAddress(address);
			user.setMailAddress(mailAddress);
			user.setAdmin(0);

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "complete-register.jsp";

		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("errorMessage", "このIDはすでに使用されています。");
			return "error-register.jsp";
		}
	}
}
