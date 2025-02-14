package com.rsmm.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {
	String url = "jdbc:mysql://localhost:3306/rsmm";
	String username = "root";
	String password = "";

	public void loginAt(String host, String port, String uname) {
		try {
			String sql = "insert into login values (NOW(),\"" + host + "\"," + port + ",\"" + uname + "\")";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean check(String uname, String pass) {
		try {
			String sql = "select * from credentials where user=? and pass=?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uname);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
