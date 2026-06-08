package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

public class ForgetPassAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		String mailAddress = request.getParameter("mailaddress");

		if (mailAddress == null || mailAddress.isBlank()) {
			request.setAttribute("errorMessage", "メールアドレスを入力してください。");
			return "forget-pass.jsp";
		}

		UsersDAO dao = new UsersDAO();
		Users user = dao.searchByMailAddress(mailAddress);

		if (user == null) {
			request.setAttribute("errorMessage", "入力されたメールアドレスは登録されていません。");
			return "forget-pass.jsp";
		}

		String subject = "パスワードのお知らせ";

		String body = user.getLastName() + " " + user.getFirstName() + " 様\n\n"
				+ "登録されているパスワードは以下の通りです。\n\n"
				+ "パスワード：" + user.getPassword() + "\n\n"
				+ "※このメールは研修用アプリから送信されています。";

		// まずは本物メールを送らず、コンソールで確認
		System.out.println("===== パスワード通知メール =====");
		System.out.println("宛先：" + user.getMailAddress());
		System.out.println("件名：" + subject);
		System.out.println("本文：");
		System.out.println(body);
		System.out.println("============================");

		return "redirect:/complete-forget-pass.jsp";
	}
}