package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectnthHighestSal {

	public static void main(String[] args) throws Exception  {
		Scanner sc = null;
		Connection con=null;
		Statement st = null;
		ResultSet rs=null;
		String Query=null;	
		int number=0 ;
		boolean flag=false;

		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Nth number :: ");
				number=sc.nextInt();
			}
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create connection obj
			if(con!=null)
				st=con.createStatement();
			System.out.println("EmpId ENAME SAL JOB");
			Query="SELECT EMPNO, ENAME,SAL,JOB FROM EMP T1 WHERE "+number+"=(SELECT COUNT (SAL) FROM EMP T2 WHERE T2.SAL>T1.SAL)";
			if(st!=null)

				rs=st.executeQuery(Query);
			while(rs.next()) {
				flag=true;
				if(rs!=null)
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			}if(flag== false)
				System.out.println("No Record found ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(st!=null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

}
