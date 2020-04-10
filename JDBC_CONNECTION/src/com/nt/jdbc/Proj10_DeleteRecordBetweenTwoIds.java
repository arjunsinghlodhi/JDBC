package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Proj10_DeleteRecordBetweenTwoIds {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		String query=null;
		int result = 0;
		int val1 = 0;
		int val2=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter  first student id ");
				val1=sc.nextInt();
				System.out.println("Enter  Second student id ");
				val2=sc.nextInt();
			}

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null)
				st=con.createStatement();
			if(st!=null)
				//query="update emp set comm = comm-200 where comm > 0";
				query ="DELETE STUDENT WHERE SNO BETWEEN "+val1+ " AND " +val2+"";
			System.out.println(query);
			result=st.executeUpdate(query);
			if(result==0)
				System.out.println("No record Found");
			else
				System.out.println("Data Deleteds");
		} catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {

			try {
				if(st!=null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			if(sc!=null)
				sc.close();
		}
	}
}
