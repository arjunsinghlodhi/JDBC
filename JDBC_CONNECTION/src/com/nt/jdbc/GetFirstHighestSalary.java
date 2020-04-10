/**Write a JDBC Application Highest salary */
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetFirstHighestSalary {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String Query= null;
		try {
			//Register JDBC Driver
			//Class.forName("oracle:jdbc:driver:OracleDriver");
			// Conection Establish
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create stmt obj
			if(conn!=null)
			
			st=conn.createStatement();
			//prepared query stmt
			if(st!=null)
			Query = "select empno , ename, job, sal from emp where sal=(select  max(sal) from emp)";
		
			System.out.println();
			System.out.println("EMPID ENAME    JOB     SALARY");
			rs=st.executeQuery(Query);
			if(rs!=null) 	
			if(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"   "+rs.getFloat(4));
			}
		}  catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close ResultSet
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			// close stmt
			try {
				if(st!=null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			//close connection
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}//finally
		
	}//main

}//class
