package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

public class UpdateAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		String oldMemberId = request.getParameter("oldMemberId");
		String memberId = request.getParameter("id");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		String address = request.getParameter("address");
		String mailAddress = request.getParameter("mailaddress");

		UsersDAO dao = new UsersDAO();

		int line = dao.update(oldMemberId, memberId, password, lastName, firstName, address, mailAddress);

		if (line == 1) {
			Users updatedUser = new Users();
			updatedUser.setMemberId(memberId);
			updatedUser.setPassword(password);
			updatedUser.setLastName(lastName);
			updatedUser.setFirstName(firstName);
			updatedUser.setAddress(address);
			updatedUser.setMailAddress(mailAddress);

			HttpSession session = request.getSession();
			session.setAttribute("user", updatedUser);

			return "complete-update.jsp";
		}

		request.setAttribute("errorMessage", "更新対象のユーザーが見つかりません。");
		return "error-update.jsp";
	}
}