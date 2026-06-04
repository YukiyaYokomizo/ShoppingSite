package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.Action;

public class AdminAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsersDAO dao = new UsersDAO();

		List<Users> usersList = dao.searchAll();

		request.setAttribute("usersList", usersList);

		return "administration.jsp";
	}
}
