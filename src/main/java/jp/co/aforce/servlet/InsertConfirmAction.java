package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

public class InsertConfirmAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

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

		// 空欄チェック
		if (isBlank(memberId)
				|| isBlank(password)
				|| isBlank(lastName)
				|| isBlank(firstName)
				|| isBlank(address)
				|| isBlank(mailAddress)) {

			request.setAttribute("errorMessage", "未入力の項目があります。すべて入力してください。");
			request.setAttribute("userForm", userForm);

			return "new-registration.jsp";
		}

		UsersDAO dao = new UsersDAO();

		if (dao.existsMemberId(memberId)) {
			request.setAttribute("errorMessage", "このIDはすでに使用されています。");
			request.setAttribute("userForm", userForm);
			
			return "new-registration.jsp";
		}

		request.setAttribute("userForm", userForm);
		
		return "registration-confirm.jsp";
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}