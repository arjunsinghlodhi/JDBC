package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.sun.org.glassfish.external.statistics.annotations.Reset;

public class Proj14_InsertDateWIthPS {

	public static void main(String[] args) {
		Scanner sc = null;
		//int eid =0;
		String name=null;
		Float sal =0.f;
		Connection con=null;
		PreparedStatement ps = null;
		int result =0;
		Statement st = null;
		ResultSet rs =null;
		boolean flag=false;
		sc=new Scanner(System.in);
		if(sc!=null) {
			/*
			 * System.out.println("Enter Empid :: "); eid=sc.nextInt();
			 */
			System.out.println("Enter nama :: ");
			name=sc.next().toUpperCase();
			System.out.println("Enter Sal :: ");
			sal=sc.nextFloat();
		}
		
		try {
			//register JDBC object class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//established the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system" ,"manager");
			if(con!=null)
			ps=con.prepareStatement("insert into EMPTABVAL values (GENERATOR.NEXTVAL,?,?)");
			if(ps!=null) {
				//ps.setInt(1,eid);
				ps.setString(1,name);
				ps.setFloat(2,sal);
				result=ps.executeUpdate();
			}
			if(result==0)
			System.out.println("DATA NOT INSERTED");
			else
			{
				st=con.createStatement();
				rs=st.executeQuery("SELECT no,name,sal from EMPTABVAL");
				while(rs.next()!=false) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					
				}	
			}
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				if(ps!=null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
