package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Products;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductListAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String category = request.getParameter("category");
		String sort = request.getParameter("sort");

		ProductsDAO dao = new ProductsDAO();

		List<Products> productsList = dao.search(category, sort);

		request.setAttribute("productsList", productsList);
		request.setAttribute("category", category);
		request.setAttribute("sort", sort);

		return "product-list.jsp";
	}
}