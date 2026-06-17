package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import jp.co.aforce.beans.CartItem;
import jp.co.aforce.beans.Users;

public class PurchaseDAO extends DAO {

	public void purchase(Users user, List<CartItem> cartList) throws Exception {

		Connection con = getConnection();

		try {
			con.setAutoCommit(false);

			int totalPrice = 0;

			for (CartItem item : cartList) {
				totalPrice += item.getSubtotal();
			}

			String orderSql =
				"insert into orders " +
				"(MEMBER_ID, TOTAL_PRICE) " +
				"values (?, ?)";

			int orderId = 0;

			try (PreparedStatement st = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {

				st.setString(1, user.getMemberId());
				st.setInt(2, totalPrice);

				st.executeUpdate();

				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						orderId = rs.getInt(1);
					}
				}
			}

			if (orderId == 0) {
				throw new Exception("注文IDの取得に失敗しました。");
			}

			String detailSql =
				"insert into order_details " +
				"(ORDER_ID, PRODUCT_ID, PRODUCT_NAME, PRICE, QUANTITY, SUBTOTAL) " +
				"values (?, ?, ?, ?, ?, ?)";

			String updateProductSql =
				"update products " +
				"set STOCK = STOCK - ?, " +
				"SALES_COUNT = SALES_COUNT + ? " +
				"where PRODUCT_ID = ? " +
				"and STOCK >= ? " +
				"and DELETE_FLAG = false";

			for (CartItem item : cartList) {

				try (PreparedStatement st = con.prepareStatement(detailSql)) {
					st.setInt(1, orderId);
					st.setInt(2, item.getProduct().getProductId());
					st.setString(3, item.getProduct().getProductName());
					st.setInt(4, item.getProduct().getPrice());
					st.setInt(5, item.getQuantity());
					st.setInt(6, item.getSubtotal());

					st.executeUpdate();
				}

				try (PreparedStatement st = con.prepareStatement(updateProductSql)) {
					st.setInt(1, item.getQuantity());
					st.setInt(2, item.getQuantity());
					st.setInt(3, item.getProduct().getProductId());
					st.setInt(4, item.getQuantity());

					int line = st.executeUpdate();

					if (line == 0) {
						throw new Exception("在庫が不足しています。");
					}
				}
			}

			con.commit();

		} catch (Exception e) {
			con.rollback();
			throw e;

		} finally {
			con.setAutoCommit(true);
			con.close();
		}
	}
}