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
			System.out.println("����̺� �ε� ����");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("����̺� ���� �غ�...");
			connection=DriverManager.getConnection(url, userid, pwd);
			System.out.println("����̺� ���� ����");
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
	}
}
