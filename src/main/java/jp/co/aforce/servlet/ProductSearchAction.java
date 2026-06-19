package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Products;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductSearchAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String sort = request.getParameter("sort");

		ProductsDAO dao = new ProductsDAO();

		List<Products> productsList = dao.searchByKeyword(name, sort);

		request.setAttribute("productsList", productsList);
		request.setAttribute("name", name);
		request.setAttribute("sort", sort);

		return "product-search.jsp";
	}
}