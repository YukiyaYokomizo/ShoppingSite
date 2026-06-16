package jp.co.aforce.servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Users;
import jp.co.aforce.tool.Action;

public class PurchaseConfirmAction extends Action{
	
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		
		if(session==null || session.getAttribute("user")==null) {
			return "redirect:login-in.jsp";
		}
		
		Users user=(Users) session.getAttribute("user");
		
		if(user.getAdmin() != 0) {
			return "redirect:admin-menu.jsp";
		}
		
		List<CartItem> cartList=(List<CartItem>)session.getAttribute("cartList");
		
		if (cartList==null || cartList.isEmpty()) {
			return "redirect:cart.jsp";
		}
		
		return "purchase-confirm.jsp";
		
		
	}
	
	
	
	
	
}
