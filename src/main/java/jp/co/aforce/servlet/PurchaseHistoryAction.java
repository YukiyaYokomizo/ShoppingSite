package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Order;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.OrdersDAO;
import jp.co.aforce.tool.Action;

public class PurchaseHistoryAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		OrdersDAO dao = new OrdersDAO();

		List<Order> orderList = dao.searchByMemberId(user.getMemberId());

		request.setAttribute("orderList", orderList);

		return "purchase-history.jsp";
	}
}