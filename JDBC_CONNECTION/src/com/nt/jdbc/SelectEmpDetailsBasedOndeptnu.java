package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectEmpDetailsBasedOndeptnu {
	public static void main(String[] args){
		Scanner sc=null;
		Connection conn=null;
		Statement st =null;	
		ResultSet rs = null;
		int Deptn = 0;
		
		boolean flag = false;

		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Employee department number  :");
				Deptn=sc.nextInt();//given clerk
				
			}
		
			//Register JDBC driver Software

			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Create statement Object
			if(conn!=null)
				st=conn.createStatement();
			
			String query="select * from emp where deptno = "+Deptn+"";
			System.out.println(query);

			//send and execute SQL Query in database software
			if(st!=null) 
				rs=st.executeQuery(query);
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
				}//while
				if(flag== false)
					System.out.println("No Record found ...");
			}//if


		} //try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}

			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}

			try {
				if(conn!=null)
					conn.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}

	}
}
