package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Article;

public class ArticlesDAO extends DAO{
	
	public List<Article> searchForTop() throws Exception{
		List<Article> list=new ArrayList<>();
		
		String sql="select * from articles where delete_flag=false order by created_at desc";
		
		try(Connection con=getConnection();
				PreparedStatement st=con.prepareStatement(sql);
				ResultSet rs=st.executeQuery()){
			
			while(rs.next()) {
				Article article = new Article();
				
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setTitle(rs.getString("TITLE"));
				article.setCategory(rs.getString("CATEGORY"));
				article.setSummary(rs.getString("SUMMARY"));
				article.setContent(rs.getString("CONTENT"));
				article.setImagePath(rs.getString("IMAGE_PATH"));
				article.setDeleteFlag(rs.getBoolean("DELETE_FLAG"));
				article.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				article.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));
				
				list.add(article);
			}
			
		}
		return list;
	}
	
	public List<Article> searchForAdmin() throws Exception {

		List<Article> list = new ArrayList<>();

		String sql = "select * from articles "
				+ "order by CREATED_AT desc";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				Article article = new Article();

				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setTitle(rs.getString("TITLE"));
				article.setCategory(rs.getString("CATEGORY"));
				article.setSummary(rs.getString("SUMMARY"));
				article.setContent(rs.getString("CONTENT"));
				article.setImagePath(rs.getString("IMAGE_PATH"));
				article.setDeleteFlag(rs.getBoolean("DELETE_FLAG"));
				article.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				article.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));

				list.add(article);
			}
		}

		return list;
	}
	
	public List<Article> searchForAdmin(String articleDisplayStatus) throws Exception {

		List<Article> list = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from articles ");

		if ("visible".equals(articleDisplayStatus)) {
			sql.append("where DELETE_FLAG = false ");
		} else if ("hidden".equals(articleDisplayStatus)) {
			sql.append("where DELETE_FLAG = true ");
		}

		sql.append("order by CREATED_AT desc");

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql.toString());
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				Article article = new Article();

				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setTitle(rs.getString("TITLE"));
				article.setCategory(rs.getString("CATEGORY"));
				article.setSummary(rs.getString("SUMMARY"));
				article.setContent(rs.getString("CONTENT"));
				article.setImagePath(rs.getString("IMAGE_PATH"));
				article.setDeleteFlag(rs.getBoolean("DELETE_FLAG"));
				article.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				article.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));

				list.add(article);
			}
		}

		return list;
	}
	
	
}
