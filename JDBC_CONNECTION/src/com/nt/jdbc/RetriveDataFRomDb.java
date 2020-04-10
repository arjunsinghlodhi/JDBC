package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RetriveDataFRomDb {
	public static void main(String[] args){
		Scanner sc=null;
		Connection conn=null;
		Statement st =null;	
		ResultSet rs = null;
		String desg1=null;
		String desg2=null;
		String desg3=null;
		String cond=null;
		boolean flag = false;

		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Desg1 :");
				desg1=sc.next().toUpperCase();//given clerk
				System.out.println("Enter Desg2 :");
				desg2=sc.next().toUpperCase();
				System.out.println("Enter Desg3 :");
				desg3=sc.next().toUpperCase();
			}
			//Frame Condition ('CLERK','MANAGER','SALESMAN')
			cond="('"+desg1+"','"+desg2+"','"+desg3+"')";
			//Given clerk, manager, salesman
			//Register JDBC driver Software

			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Create statement Object
			if(conn!=null)
				st=conn.createStatement();
			//frame the SQL Query
			//String query="select empno, ename,job,sal from emp where job in ('clerk','manager','salesman') order by job";
			String query="select empno, ename,job,sal from emp where job in "+cond+" order by job";
			System.out.println(query);

			//send and execute SQL Query indatabase software
			if(st!=null) 
				rs=st.executeQuery(query);
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+""+rs.getString(4));
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
