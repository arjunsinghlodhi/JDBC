package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Proj14_LoginApp {
	public static void main(String[] args) {
		Scanner scanner = null;
		String user=null;
		String pwd = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet =null;
		String query=null;
		int count=0;
		try {
			scanner = new Scanner(System.in);

			System.out.println("ENTER USER NAME :: ");
			user=scanner.next().toUpperCase();
			user="'"+user+"'";
			System.out.println("ENTER USER PASSWORD :: ");
			pwd=scanner.next();
			pwd="'"+pwd+"'";
			if(scanner!=null) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			}

			if (connection!=null) {
				statement=connection.createStatement();
			}

			if(statement!=null) {
				//query="SELECT COUNT(USERNAME) FROM LOGINTAB WHERE USERNAME='ARJUN' AND PASSWORD='Arjun12@'";

				query="SELECT COUNT(USERNAME) FROM LOGINTAB WHERE USERNAME="+user+" AND PASSWORD="+pwd+"";


				resultSet=statement.executeQuery(query);
				if(resultSet!=null) {
					if(resultSet.next())
						count=resultSet.getInt(1);
					//System.out.println(count);
				}
				if(count==0)
					System.out.println("INVALID USERNAME & PASSWORD");
				else
					System.out.println("VALID USERNAME AND PASSWORD");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {

			try {
				if(resultSet!=null)
					resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if(statement!=null)
					statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if(connection!=null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}


			try {
				if(scanner!=null)
					scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
