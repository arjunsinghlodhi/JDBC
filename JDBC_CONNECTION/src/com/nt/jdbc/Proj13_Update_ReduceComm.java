package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Proj13_Update_ReduceComm {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		int result =0;
		int amount =0;
		String query =null;
		try {
			sc= new  Scanner(System.in); 
			
			if(sc!=null)
				System.out.println("Enter Amount these Reduce Comm :: ");
				amount = sc.nextInt();
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null)
				st=con.createStatement();
			if(st!=null)
				//query = "UPDATE EMP SET COMM=COMM+500 WHERE COMM <0";
				query ="UPDATE EMP SET COMM = COMM-"+amount+" WHERE COMM >"+amount+"";
			System.out.println(query);
			result=st.executeUpdate(query);
			if(result==0)
				System.out.println("Record Not Updateed");
			else
				System.out.println("Record Found to  Updated ");
		} catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st!=null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
