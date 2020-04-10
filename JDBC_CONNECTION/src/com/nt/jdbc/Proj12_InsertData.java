package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Proj12_InsertData {
public static void main(String[] args) {
	Scanner sc = null;
	Connection con = null;
	Statement st = null;
	int result = 0;
	int sno = 0;
	String sname=null;
	String city=null;
	String query=null;
	try {
		sc= new Scanner(System.in);
		if(sc!=null) {
			System.out.println("Enter SNO :: ");
			sno=sc.nextInt();
			System.out.println("Enter Student name ::");
			sname=sc.next().toUpperCase();
			System.out.println("Enter City Name::");
			city=sc.next().toUpperCase();
		}
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		if(con!=null)
			st=con.createStatement();
		if(st!=null)
		{
			sname="'"+sname+"'";
			city="'"+city+"'";
		query = "INSERT INTO STUDENT VALUES(" +sno+ ","+sname+","+city+")";
		System.out.println(query);
		result=st.executeUpdate(query);
		}
		if(result==0) {
			System.out.println("Record Insertion Failed ");
		}
		else
			System.out.println("Record Inserted Succeded");
	} catch (SQLException se) {
		se.printStackTrace();
		System.out.println("Record Insertion Failed ");
	}
	
	catch (Exception e) {
		e.printStackTrace();
		System.out.println("Record Insertion Failed ");
	}
	finally {
		try {
			if(st!=null)
				st.close();
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Record Insertion Failed ");
		}
		
		try {
			if(con!=null)
				con.close();
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Record Insertion Failed ");
		}
		
		try {
			if(sc!=null)
				sc.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Record Insertion Failed ");
		}
	}
}
}
