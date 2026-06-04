package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;


public class DeleteAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);//セッションを取ってくる false:なくても新しいセッションは開かない
		Users user = (Users) session.getAttribute("user");//セッションの中のuserをとってくる

		String memberId = user.getMemberId();

		UsersDAO dao = new UsersDAO();
		dao.delete(memberId);

		session.invalidate();

		return "redirect:/views/complete-delete.jsp";
	}
}