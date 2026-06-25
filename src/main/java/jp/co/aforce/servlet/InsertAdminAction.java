package jp.co.aforce.servlet;

import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

public class InsertAdminAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users loginUser = (Users) session.getAttribute("user");

		if (loginUser.getAdmin() != 1) {
			return "redirect:/Top.action";
		}

		String memberId = request.getParameter("id");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailaddress");

		UsersDAO dao = new UsersDAO();

		try {
			dao.insertAdmin(memberId, password, lastName, firstName, address, mailAddress);

			return "redirect:/ProductManage.action";

		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("errorMessage", "このIDはすでに使用されています。");
			return "error-register.jsp";
		}
	}
}