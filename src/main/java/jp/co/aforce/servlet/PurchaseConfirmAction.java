package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Products;
import jp.co.aforce.beans.Users;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class PurchaseConfirmAction extends Action {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 0) {
			return "redirect:/ProductManage.action";
		}

		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");

		if (cartList == null || cartList.isEmpty()) {
			return "redirect:cart.jsp";
		}

		ProductsDAO productsDao = new ProductsDAO();

		for (int i = 0; i < cartList.size(); i++) {

			CartItem item = cartList.get(i);

			int productId = item.getProduct().getProductId();
			int quantity = item.getQuantity();

			Products latestProduct = productsDao.searchById(productId);

			if (latestProduct == null) {
				cartList.remove(i);
				session.setAttribute("cartList", cartList);

				request.setAttribute("errorMessage",
						"販売終了した商品があったため、カートから削除しました。");

				return "cart.jsp";
			}

			if (latestProduct.getStock() <= 0) {
				cartList.remove(i);
				session.setAttribute("cartList", cartList);

				request.setAttribute("errorMessage",
						latestProduct.getProductName() + " は在庫切れのため、カートから削除しました。");

				return "cart.jsp";
			}

			if (quantity > latestProduct.getStock()) {
				item.setProduct(latestProduct);
				item.setQuantity(latestProduct.getStock());

				session.setAttribute("cartList", cartList);

				request.setAttribute("errorMessage",
						latestProduct.getProductName() + " の在庫が不足しています。現在の在庫は"
								+ latestProduct.getStock() + "個です。数量を最大在庫数に変更しました。");

				return "cart.jsp";
			}

			item.setProduct(latestProduct);
		}

		session.setAttribute("cartList", cartList);

		return "purchase-confirm.jsp";
	}
}