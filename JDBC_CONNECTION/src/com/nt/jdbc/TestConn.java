package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConn {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		if(conn !=null) {
			System.out.println("Established Connection");
		}
		else {
			System.out.println("Connection not established");
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(conn != null)
				conn.close();
		}
	}

}
