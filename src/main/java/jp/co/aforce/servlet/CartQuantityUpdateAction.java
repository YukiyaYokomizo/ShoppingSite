package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Products;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class CartQuantityUpdateAction extends Action {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null) {
			return "redirect:cart.jsp";
		}

		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");

		if (cartList == null || cartList.isEmpty()) {
			return "redirect:cart.jsp";
		}

		String productIdText = request.getParameter("productId");
		String mode = request.getParameter("mode");

		if (productIdText == null || productIdText.isBlank()) {
			return "redirect:cart.jsp";
		}

		int productId = Integer.parseInt(productIdText);

		ProductsDAO dao = new ProductsDAO();
		Products latestProduct = dao.searchById(productId);

		for (int i = 0; i < cartList.size(); i++) {

			CartItem item = cartList.get(i);

			if (item.getProduct().getProductId() == productId) {

				if (latestProduct == null) {
					cartList.remove(i);
					request.setAttribute("errorMessage", "商品が見つからないため、カートから削除しました。");
					break;
				}

				int quantity = item.getQuantity();

				if ("plus".equals(mode)) {

					if (quantity < latestProduct.getStock()) {
						quantity++;
					} else {
						request.setAttribute("errorMessage",
								latestProduct.getProductName() + " は在庫数までしか追加できません。");
					}

				} else if ("minus".equals(mode)) {

					if (quantity > 1) {
						quantity--;
					}
				}

				item.setProduct(latestProduct);
				item.setQuantity(quantity);

				break;
			}
		}

		if (cartList.isEmpty()) {
			session.removeAttribute("cartList");
		} else {
			session.setAttribute("cartList", cartList);
		}

		return "cart.jsp";
	}
}