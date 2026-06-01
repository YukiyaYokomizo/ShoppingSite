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
			user.setAdmin(rs.getInt("flag"));
		}
		st.close();
		con.close();
		return user;
	}

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

}
