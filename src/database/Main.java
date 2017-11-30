package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		createTable();
//		post();
//		get();
	}
	public static ArrayList<String> get() throws Exception{
		final String sql="SELECT * FROM tablename";
		try {
			Connection con=getConnection();
			PreparedStatement statement=con.prepareStatement(sql);
			ResultSet result=statement.executeQuery();
			
			ArrayList<String> array= new ArrayList<String>();
			while(result.next()) {
				System.out.print(result.getString("first"));
				System.out.print("-");
				System.out.println(result.getString("last"));
				
				array.add(result.getString("last"));
			}
			
			System.out.println("All records have been seleted.");
			return array;
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			System.out.println("Function selected completed");
		}
		return null;
		
	}
	public static void post() throws Exception{
		final String var1="John";
		final String var2="Miller";
		String sql="INSERT INTO tablename (first,last)"
				+ "VALUES ('"+var1+"', '"+var2+"')";
		
		try {
			Connection con=getConnection();
			PreparedStatement posted=con.prepareStatement(sql);
			posted.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			System.out.println("Function Insert Completed.");
		}
	}
	public static void createTable() throws Exception{
		
			Connection conn=getConnection();
			if (conn==null) {
				System.out.println("Erro no db");
			}else {
				try {				
					String sql="CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL AUTO_INCREMENT,"
							+ "first varchar(255),last varchar(255),"
							+ "PRIMARY KEY(id))";
					System.out.println(sql);
					PreparedStatement create=conn.prepareStatement(sql);
					create.executeUpdate();
					System.out.println("table created.");
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}finally {
					System.out.println("function create completed.");
				}	
			}
			
	}
	public static Connection getConnection() throws Exception{
		try {
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/portal";
			String userName="root";
			String password="123456";
			Class.forName(driver);
			
			Connection conn=DriverManager.getConnection(url, userName, password);
			System.out.println("Connected.");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		return null;
		
	}

}
