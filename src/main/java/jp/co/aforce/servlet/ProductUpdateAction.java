package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductUpdateAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:top.jsp";
		}

		String productIdText = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String category = request.getParameter("category");
		String priceText = request.getParameter("price");
		String stockText = request.getParameter("stock");
		String description = request.getParameter("description");
		String imagePath = request.getParameter("imagePath");
		String deleteFlagText = request.getParameter("deleteFlag");

		int productId = Integer.parseInt(productIdText);
		int price = Integer.parseInt(priceText);
		int stock = Integer.parseInt(stockText);
		boolean deleteFlag = Boolean.parseBoolean(deleteFlagText);

		ProductsDAO dao = new ProductsDAO();

		int result = dao.updateProduct(productId, productName, category, price,
				stock, description, imagePath, deleteFlag);

		if (result == 1) {
			return "redirect:/ProductManage.action?updated=1";
		}

		request.setAttribute("errorMessage", "商品の更新に失敗しました。");
		return "product-update-confirm.jsp";
	}
}