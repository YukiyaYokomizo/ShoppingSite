package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Products;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductDetailAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String productIdText = request.getParameter("productId");

		if (productIdText == null || productIdText.isBlank()) {
			return "redirect:/ProductList.action";
		}

		int productId = Integer.parseInt(productIdText);

		ProductsDAO dao = new ProductsDAO();

		Products product = dao.searchById(productId);

		if (product == null) {
			return "redirect:/ProductList.action";
		}

		request.setAttribute("product", product);

		return "product-detail.jsp";
	}
}
