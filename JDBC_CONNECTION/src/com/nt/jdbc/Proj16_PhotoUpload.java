package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//C:\\Users\\ARJUN\\Desktop\\usernamepassword.png
public class Proj16_PhotoUpload {
	private static final String INSERT_BLOB_QUERY=("INSERT INTO STU_PHOTO(?,?,?,?)");
	public static void main(String[] args) {

		Scanner sc =null;
		int no = 0;
		String name=null,photoPath=null;
		float sal = 0.f;
		File file=null;
		long length =0;
		InputStream is = null;
		Connection con = null;
		PreparedStatement ps =null;
		int result = 0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter no ::");
				no=sc.nextInt();
				System.out.println("Enter Name :: ");
				name=sc.next();
				System.out.println("Enter Salary ::");
				sal=sc.nextFloat();
				System.out.println("Enter photo path :: ");
				photoPath=sc.next().trim();
			}//if

			//locate the file
			file = new File(photoPath);
			//get the lenght of the file
			if(file!=null)
				length=file.length();
			//create input stream pointin to photo
			if(file!=null)
				is=new FileInputStream(file);
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connecton
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create jdbc prepareStatement
			if(con!=null)
				ps=con.prepareStatement(INSERT_BLOB_QUERY);
			if(ps!=null) {
				ps.setInt(1,no);
				ps.setString(2,name);
				ps.setFloat(3,sal);
				ps.setBinaryStream(4,is,length);
				result=ps.executeUpdate();
			}
			if(result==0)
				System.out.println("DATA NOT INSERTED");
			else
				System.out.println("RECORD INSERTED");
		}//try 
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
			System.out.println("RECORD INSERTION FAILED");
		}
		catch (SQLException se) {
			se.printStackTrace();
			System.out.println("RECORD INSERTION FAILED");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("RECORD INSERTION FAILED");
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
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
