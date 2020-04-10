package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Proj11_DeleteRecordBasedonCity {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		String query=null;
		int result = 0;
		String city = null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter City name do you want to delete :: ");
				city=sc.next();
				
			}

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null)
				st=con.createStatement();
			if(st!=null)
				
				city="'"+city+"'";
				query ="DELETE STUDENT WHERE CITY="+city+"";
				
			System.out.println(query);
			result=st.executeUpdate(query);
			if(result==0)
				System.out.println("No record Found");
			else
				System.out.println("Record Deleted");
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
