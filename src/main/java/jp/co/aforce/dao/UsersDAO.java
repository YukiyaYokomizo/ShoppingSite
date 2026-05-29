package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.Users;

public class UsersDAO extends DAO {
	public Users search(String memberId, String password) throws Exception{
		Users user=null;
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("select * from users where MEMBER_ID=? and PASSWORD=?");
		st.setString(1, memberId);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();
		
		while(rs.next()) {
			user=new Users();
			user.setMemberId(rs.getString("MEMBER_ID"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setLastName(rs.getString("LAST_NAME"));
		}
		st.close();
		con.close();
		return user;
	}
	
	
}
