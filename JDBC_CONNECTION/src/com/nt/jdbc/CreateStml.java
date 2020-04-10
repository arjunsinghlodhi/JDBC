package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateStml {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st =null;
		ResultSet rs =null;		
		try {
			//establsh connection
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create stmt
			 st = conn.createStatement();
			//SetRusultSet
			rs=st.executeQuery("select * from emp where empno >= 7654 AND empno <=7900");
			while(rs.next()!=false) {
				System.out.println("EMPID   "+rs.getString(1)+"      EMPNAME  "+rs.getString(2)+"   DEPATMENT "+rs.getString(3)+"      SAL   "+rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
