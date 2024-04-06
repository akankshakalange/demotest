package com.pojo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {

	public static Connection getConnectionWithDb() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection co=null;
		try {
		co=DriverManager.getConnection("jdbc:mysql://localhost:3306/kbplibrary", "root", "akshu123");
		//	co=DriverManager.getConnection("jdbc:mysql://localhost:3306/exampleDB", "root", "password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
		
	}

}
