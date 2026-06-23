package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Products;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductUpdateConfirmAction extends Action {

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

		// 管理者用なので、非表示の商品も取得できるメソッドを使う
		Products oldProduct = dao.searchByIdForAdmin(productId);

		if (oldProduct == null) {
			return "redirect:/ProductManage.action";
		}

		Products product = new Products();

		product.setProductId(productId);
		product.setProductName(productName);
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);
		product.setDescription(description);
		product.setImagePath(imagePath);
		product.setDeleteFlag(deleteFlag);

		// 売上数は編集対象ではないので、元の商品情報から引き継ぐ
		product.setSalesCount(oldProduct.getSalesCount());

		request.setAttribute("product", product);

		return "product-update-confirm.jsp";
	}
}