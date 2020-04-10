package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectData {

	public static void main(String[] args)throws SQLException {
		Scanner sc =new Scanner(System.in);
		System.out.println("enter initial empid ");
		int starts=sc.nextInt();
		System.out.println("enter last empid ");
		int ends=sc.nextInt();
		Connection conn =null;
		Statement st = null;
		ResultSet rs = null;
		boolean flag=false;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			st=conn.createStatement();
			rs=st.executeQuery("SELECT * FROM EMP WHERE EMPNO >="+starts+" AND EMPNO <="+ends);
			while(rs.next()!=false) {
				flag=true;
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
				
			}
			if(flag==false)
				System.out.println("Class Not Found ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if (rs!=null) {
				st.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
	}

}
