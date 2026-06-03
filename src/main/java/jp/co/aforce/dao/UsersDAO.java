package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.Users;

public class UsersDAO extends DAO {

	public Users search(String memberId, String password) throws Exception {
		Users user = null;

		Connection con = getConnection();

		PreparedStatement st;
		st = con.prepareStatement("select * from users where MEMBER_ID=? and PASSWORD=?");
		st.setString(1, memberId);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			user = new Users();
			user.setMemberId(rs.getString("MEMBER_ID"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setLastName(rs.getString("LAST_NAME"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setAddress(rs.getString("ADDRESS"));
			user.setMailAddress(rs.getString("MAIL_ADDRESS"));
			user.setAdmin(rs.getInt("flag"));
		}
		st.close();
		con.close();
		return user;
	}

//新規追加
	public int insert(String memberId, String password, String lastName, String firstName, String address,
			String mailAddress) throws Exception {

		Connection con = getConnection();

		PreparedStatement st;
		st = con.prepareStatement("insert into users values (?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, memberId);
		st.setString(2, password);
		st.setString(3, lastName);
		st.setString(4, firstName);
		st.setString(5, address);
		st.setString(6, mailAddress);
		st.setInt(7, 0);

		int line = st.executeUpdate();
		return line;
	}
	
	public int update(String memberId, String password, String lastName, String firstName, String address,
			String mailAddress)throws Exception{
		Connection con = getConnection();

		PreparedStatement st;
		st = con.prepareStatement("update users set PASSWORD=?, LAST_NAME=?, FIRST_NAME=?, ADDRESS=?, MAIL_ADDRESS=?, flag=? where MEMBER_ID=?");
		st.setString(1, password);
		st.setString(2, lastName);
		st.setString(3, firstName);
		st.setString(4, address);
		st.setString(5, mailAddress);
		st.setInt(6, 0);
		st.setString(7, memberId);
		
		int line = st.executeUpdate();
		return line;
		
		
	}
	
//重複チェック
	public boolean existsMemberId(String memberId) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
			"select count(*) from users where MEMBER_ID=?"
		);
		st.setString(1, memberId);
		
		ResultSet rs = st.executeQuery();
		boolean exists = false;
		if (rs.next()) {
			exists = rs.getInt(1) > 0;
		}
		
		rs.close();
		st.close();
		con.close();
		
		return exists;
	}
	
	
	public int update(String oldMemberId, String memberId, String password, String lastName, String firstName,
			String address, String mailAddress) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update users set " +
			"MEMBER_ID=?, " +
			"PASSWORD=?, " +
			"LAST_NAME=?, " +
			"FIRST_NAME=?, " +
			"ADDRESS=?, " +
			"MAIL_ADDRESS=?, " +
			"flag=? " +
			"where MEMBER_ID=?"
		);

		st.setString(1, memberId);
		st.setString(2, password);
		st.setString(3, lastName);
		st.setString(4, firstName);
		st.setString(5, address);
		st.setString(6, mailAddress);
		st.setInt(7, 0);
		st.setString(8, oldMemberId);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
	
	public int delete(String memberId)throws Exception{
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement("delete from users where MEMBER_ID=?");
		
		st.setString(1, memberId);
		
		return st.executeUpdate();
	}
}


