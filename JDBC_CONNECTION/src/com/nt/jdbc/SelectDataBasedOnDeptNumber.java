package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectDataBasedOnDeptNumber {
	public static void main(String[] args){
		Scanner sc=null;
		Connection conn=null;
		Statement st =null;	
		ResultSet rs = null;
		String city1=null;
		String city2=null;
		String city3=null;
		String cond=null;
		boolean flag = false;

		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Citiy Name :");
				city1=sc.next().toLowerCase();//given clerk
				System.out.println("Enter Citiy Name :");
				city2=sc.next().toLowerCase();
				System.out.println("Enter Citiy Name :");
				city3=sc.next().toLowerCase();
			}
			//Frame Condition ('CLERK','MANAGER','SALESMAN')
			cond="('"+city1+"','"+city2+"','"+city3+"')";
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
			String query="select sno, sname,city from Student where city in "+cond+" order by city";
			System.out.println(query);

			//send and execute SQL Query indatabase software
			if(st!=null) 
				rs=st.executeQuery(query);
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
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
