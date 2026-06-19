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
	
	public List<Products> search(String category, String sort) throws Exception {

		List<Products> list = new ArrayList<>();

		String sql = "select * from products where DELETE_FLAG = false";

		if (category != null && !category.isBlank()) {
			sql += " and CATEGORY = ?";
		}

		if ("price_asc".equals(sort)) {
			sql += " order by PRICE asc";
		} else if ("price_desc".equals(sort)) {
			sql += " order by PRICE desc";
		} else {
			sql += " order by PRODUCT_ID desc";
		}

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			if (category != null && !category.isBlank()) {
				st.setString(1, category);
			}

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
	
	
	
	public int insertProduct(String productName, String category, int price, int stock,
			String description, String imagePath) throws Exception {

		String sql = "insert into products "
				+ "(PRODUCT_NAME, CATEGORY, PRICE, STOCK, DESCRIPTION, IMAGE_PATH) "
				+ "values (?, ?, ?, ?, ?, ?)";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			st.setString(1, productName);
			st.setString(2, category);
			st.setInt(3, price);
			st.setInt(4, stock);
			st.setString(5, description);
			st.setString(6, imagePath);

			return st.executeUpdate();
		}

	}
	
	public int updateProduct(int productId, String productName, String category, int price,
			int stock, String description, String imagePath, boolean deleteFlag) throws Exception {

		String sql = "update products set "
				+ "PRODUCT_NAME = ?, "
				+ "CATEGORY = ?, "
				+ "PRICE = ?, "
				+ "STOCK = ?, "
				+ "DESCRIPTION = ?, "
				+ "IMAGE_PATH = ?, "
				+ "DELETE_FLAG = ? "
				+ "where PRODUCT_ID = ?";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			st.setString(1, productName);
			st.setString(2, category);
			st.setInt(3, price);
			st.setInt(4, stock);
			st.setString(5, description);
			st.setString(6, imagePath);
			st.setBoolean(7, deleteFlag);
			st.setInt(8, productId);

			return st.executeUpdate();
		}
	}
	
	public Products searchByIdForAdmin(int productId) throws Exception {

		Products product = null;

		String sql = "select * from products where PRODUCT_ID = ?";

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
	
	public List<Products> searchForAdmin(String category, String sort,
			String displayStatus, String name) throws Exception {

		List<Products> list = new ArrayList<>();

		String sql = "select * from products where 1 = 1";

		if (category != null && !category.isBlank()) {
			sql += " and CATEGORY = ?";
		}

		if (name != null && !name.isBlank()) {
			sql += " and (PRODUCT_NAME like ? or DESCRIPTION like ?)";
		}

		if ("visible".equals(displayStatus)) {
			sql += " and DELETE_FLAG = false";
		} else if ("hidden".equals(displayStatus)) {
			sql += " and DELETE_FLAG = true";
		}

		if ("price_asc".equals(sort)) {
			sql += " order by PRICE asc";
		} else if ("price_desc".equals(sort)) {
			sql += " order by PRICE desc";
		} else {
			sql += " order by PRODUCT_ID desc";
		}

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			int index = 1;

			if (category != null && !category.isBlank()) {
				st.setString(index, category);
				index++;
			}

			if (name != null && !name.isBlank()) {
				String keyword = "%" + name + "%";
				st.setString(index, keyword);
				index++;
				st.setString(index, keyword);
				index++;
			}

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
	
	public List<Products> searchByKeyword(String name, String sort) throws Exception {

		List<Products> list = new ArrayList<>();

		String sql = "select * from products where DELETE_FLAG = false";

		if (name != null && !name.isBlank()) {
			sql += " and (PRODUCT_NAME like ? or DESCRIPTION like ?)";
		}

		if ("price_asc".equals(sort)) {
			sql += " order by PRICE asc";
		} else if ("price_desc".equals(sort)) {
			sql += " order by PRICE desc";
		} else {
			sql += " order by PRODUCT_ID desc";
		}

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			if (name != null && !name.isBlank()) {
				String keyword = "%" + name + "%";
				st.setString(1, keyword);
				st.setString(2, keyword);
			}

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
}








