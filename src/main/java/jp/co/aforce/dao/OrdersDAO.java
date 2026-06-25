package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Order;
import jp.co.aforce.beans.OrderDetail;

public class OrdersDAO extends DAO {

	public List<Order> searchByMemberId(String memberId) throws Exception {

		List<Order> orderList = new ArrayList<>();

		String sql = "select * from orders "
				+ "where MEMBER_ID = ? "
				+ "order by ORDER_DATE desc, ORDER_ID desc";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			st.setString(1, memberId);

			try (ResultSet rs = st.executeQuery()) {

				while (rs.next()) {
					Order order = new Order();

					order.setOrderId(rs.getInt("ORDER_ID"));
					order.setMemberId(rs.getString("MEMBER_ID"));
					order.setTotalPrice(rs.getInt("TOTAL_PRICE"));
					order.setOrderDate(rs.getTimestamp("ORDER_DATE"));

					List<OrderDetail> detailList = searchDetailsByOrderId(con, order.getOrderId());
					order.setDetailList(detailList);

					orderList.add(order);
				}
			}
		}

		return orderList;
	}

	private List<OrderDetail> searchDetailsByOrderId(Connection con, int orderId) throws Exception {

		List<OrderDetail> detailList = new ArrayList<>();

		String sql = "select * from order_details "
				+ "where ORDER_ID = ? "
				+ "order by ORDER_DETAIL_ID";

		try (PreparedStatement st = con.prepareStatement(sql)) {

			st.setInt(1, orderId);

			try (ResultSet rs = st.executeQuery()) {

				while (rs.next()) {
					OrderDetail detail = new OrderDetail();

					detail.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
					detail.setOrderId(rs.getInt("ORDER_ID"));
					detail.setProductId(rs.getInt("PRODUCT_ID"));
					detail.setProductName(rs.getString("PRODUCT_NAME"));
					detail.setPrice(rs.getInt("PRICE"));
					detail.setQuantity(rs.getInt("QUANTITY"));
					detail.setSubtotal(rs.getInt("SUBTOTAL"));

					detailList.add(detail);
				}
			}
		}

		return detailList;
	}
}