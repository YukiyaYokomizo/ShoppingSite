package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

public class UpdateConfirmAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		String oldMemberId = request.getParameter("oldMemberId");
		String memberId = request.getParameter("id");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailaddress");

		Users userForm = new Users();
		userForm.setMemberId(memberId);
		userForm.setPassword(password);
		userForm.setLastName(lastName);
		userForm.setFirstName(firstName);
		userForm.setAddress(address);
		userForm.setMailAddress(mailAddress);

		UsersDAO dao = new UsersDAO();

		if (!memberId.equals(oldMemberId) && dao.existsMemberId(memberId)) {
			request.setAttribute("errorMessage", "このIDはすでに使用されています。");
			request.setAttribute("user", userForm);
			return "update.jsp";
		}

		request.setAttribute("userForm", userForm);
		request.setAttribute("oldMemberId", oldMemberId);

		return "update-confirm.jsp";
	}
}