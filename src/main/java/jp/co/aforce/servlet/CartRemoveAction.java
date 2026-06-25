package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.tool.Action;

public class CartRemoveAction extends Action {

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

		if (productIdText == null || productIdText.isBlank()) {
			return "redirect:cart.jsp";
		}

		int productId = Integer.parseInt(productIdText);

		for (int i = 0; i < cartList.size(); i++) {
			CartItem item = cartList.get(i);

			if (item.getProduct().getProductId() == productId) {
				cartList.remove(i);
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