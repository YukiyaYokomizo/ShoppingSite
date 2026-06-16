package jp.co.aforce.servlet;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Products;
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class AddToCartAction extends Action {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		String productIdText = request.getParameter("productId");
		String quantityText = request.getParameter("quantity");

		if (productIdText == null || productIdText.isBlank()) {
			return "redirect:cart.jsp";
		}

		int productId = Integer.parseInt(productIdText);

		ProductsDAO dao = new ProductsDAO();
		Products product = dao.searchById(productId);

		if (product == null) {
			return "redirect:cart.jsp";
		}

		if (product.getStock() <= 0) {
			return "redirect:cart.jsp";
		}

		int quantity = 1;

		if (quantityText != null && !quantityText.isBlank()) {
			quantity = Integer.parseInt(quantityText);
		}

		if (quantity < 1) {
			quantity = 1;
		}

		if (quantity > product.getStock()) {
			quantity = product.getStock();
		}

		HttpSession session = request.getSession();

		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");

		if (cartList == null) {
			cartList = new ArrayList<>();
		}

		boolean exists = false;
		String cartMessage = "カートに追加しました";

		for (CartItem item : cartList) {
			if (item.getProduct().getProductId() == productId) {

				int currentQuantity = item.getQuantity();
				int newQuantity = currentQuantity + quantity;

				if (currentQuantity >= product.getStock()) {
					cartMessage = "この商品は在庫数分すでにカートに入っています。";
				} else if (newQuantity > product.getStock()) {
					item.setQuantity(product.getStock());
					cartMessage = "在庫数を超えるため、最大数まで追加しました。";
				} else {
					item.setQuantity(newQuantity);
					cartMessage = "カートに追加しました。";
				}

				exists = true;
				break;
			}
		}

		if (!exists) {
			CartItem item = new CartItem();
			item.setProduct(product);
			item.setQuantity(quantity);
			cartList.add(item);

			cartMessage = "カートに追加しました。";
		}

		session.setAttribute("cartList", cartList);

		request.setAttribute("product", product);
		request.setAttribute("cartMessage", cartMessage);

		return "product-detail.jsp";
	}
}