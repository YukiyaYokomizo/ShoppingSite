package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Products;

public class ProductsDAO extends DAO {

	public List<Products> searchAll() throws Exception {
		List<Products> list = new ArrayList<>();
		String sql = "select * from products where delete_flag=false order by product_id";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				Products product = new Products();

				product.setProductId(rs.getInt("PRODUCT_ID"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setCategory(rs.getString("CATEGORY"));
				product.setPrice(rs.getInt("PRICE"));
				product.setStock(rs.getInt("STOCK"));
				product.setDescription(rs.getString("DESCRIPTION"));
				product.setImagePath(rs.getString("IMAGE_PATH"));
				product.setSalesCount(rs.getInt("SALES_COUNT"));
				product.setDeleteFlag(rs.getBoolean("DELETE_FLAG"));

				list.add(product);
			}
		}
		return list;
	}

	public List<Products> searchByCategory(String category) throws Exception {
		List<Products> list = new ArrayList<>();
		String sql = "select * from products where category=? and delete_flag=false order by product_id";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, category);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Products product = new Products();

					product.setProductId(rs.getInt("PRODUCT_ID"));
					product.setProductName(rs.getString("PRODUCT_NAME"));
					product.setCategory(rs.getString("CATEGORY"));
					product.setPrice(rs.getInt("PRICE"));
					product.setStock(rs.getInt("STOCK"));
					product.setDescription(rs.getString("DESCRIPTION"));
					product.setImagePath(rs.getString("IMAGE_PATH"));
					product.setSalesCount(rs.getInt("SALES_COUNT"));
					product.setDeleteFlag(rs.getBoolean("DELETE_FLAG"));

					list.add(product);
				}
			}
		}
		return list;
	}

	public Products searchById(int productId)throws Exception{
		Products product=null;
		
		String sql="select * from products where PRODUCT_ID=? and DELETE_FLAG=false";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			st.setInt(1, productId);

			try (ResultSet rs = st.executeQuery()) {

				if (rs.next()) {
					product = new Products();

					product.setProductId(rs.getInt("PRODUCT_ID"));
					product.setProductName(rs.getString("PRODUCT_NAME"));
					product.setCategory(rs.getString("CATEGORY"));
					product.setPrice(rs.getInt("PRICE"));
					product.setStock(rs.getInt("STOCK"));
					product.setDescription(rs.getString("DESCRIPTION"));
					product.setImagePath(rs.getString("IMAGE_PATH"));
					product.setSalesCount(rs.getInt("SALES_COUNT"));
					product.setDeleteFlag(rs.getBoolean("DELETE_FLAG"));
				}
			}
		}

		return product;
	}
	

}
