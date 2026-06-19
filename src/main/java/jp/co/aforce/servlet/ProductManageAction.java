package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Products;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductManageAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:top.jsp";
		}

		String category = request.getParameter("category");
		String sort = request.getParameter("sort");
		String name = request.getParameter("name");

		ProductsDAO dao = new ProductsDAO();
		String displayStatus = request.getParameter("displayStatus");

		if (displayStatus == null || displayStatus.isBlank()) {
			displayStatus = "visible";
		}

		List<Products> productsList = dao.searchForAdmin(category, sort, displayStatus, name);

		request.setAttribute("displayStatus", displayStatus);
		request.setAttribute("name", name);
		request.setAttribute("productsList", productsList);
		request.setAttribute("category", category);
		request.setAttribute("sort", sort);

		return "admin-menu.jsp";
	}
}