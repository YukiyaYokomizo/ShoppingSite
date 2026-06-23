package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Products;
import jp.co.aforce.beans.Users;
import jp.co.aforce.tool.Action;

public class ProductInsertConfirmAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:/Top.action";
		}

		String productName = request.getParameter("productName");
		String category = request.getParameter("category");
		String priceText = request.getParameter("price");
		String stockText = request.getParameter("stock");
		String description = request.getParameter("description");
		String imagePath = request.getParameter("imagePath");

		int price = Integer.parseInt(priceText);
		int stock = Integer.parseInt(stockText);
		
		Products product = new Products();

		product.setProductName(productName);
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);
		product.setDescription(description);
		product.setImagePath(imagePath);

		request.setAttribute("product", product);

		return "product-insert-confirm.jsp";
	}
}