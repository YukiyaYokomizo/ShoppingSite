package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Products;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductEditAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:/Top.action";
		}

		String productIdText = request.getParameter("productId");

		if (productIdText == null || productIdText.isBlank()) {
			return "redirect:/ProductManage.action";
		}

		int productId = Integer.parseInt(productIdText);

		ProductsDAO dao = new ProductsDAO();
		Products product = dao.searchByIdForAdmin(productId);

		if (product == null) {
			return "redirect:/ProductManage.action";
		}

		request.setAttribute("product", product);

		return "product-edit.jsp";
	}
}