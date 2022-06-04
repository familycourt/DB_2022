import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection{
	Connection connection;
	Statement stmt;
	String query;
	ResultSet rs;
	
	public void connect() {
		String Driver="";
		String url="jdbc:mysql://localhost:3306/?user=root&serverTimezone=Asia/Seoul&useSSL=false";
		String userid="root";
		String pwd = "0907";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이브 로드 성공");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("드라이브 연결 준비...");
			connection=DriverManager.getConnection(url, userid, pwd);
			System.out.println("드라이브 연결 성공");
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
	}
}
