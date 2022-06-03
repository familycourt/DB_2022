import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.sql.*;

@SuppressWarnings("serial")
public class DB_2022_project extends JFrame {
	public DB_2022_project() {
		super("18011547 �ֿ��� 18011481 ������");
		
		JPanel panel = new JPanel();
		JButton btnAdmin = new JButton("������");
		JButton btnMember = new JButton("ȸ��");
		
        panel.add(btnAdmin);
        panel.add(btnMember);
        
        add(panel);
        
        setVisible(true);
        setSize(300, 200); //â ũ�� ����
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPage();
                setVisible(false); // â �Ⱥ��̰� �ϱ� 
            }
        });
        
        btnMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemberPage();
                setVisible(false); // â �Ⱥ��̰� �ϱ� 
            }
        });
		
	}
	
	public static void main(String[] args) {
		DBConnection con = new DBConnection();
		con.connect();
		
		new DB_2022_project();
	}
}

class DBConnection{
	Connection connection;
	Statement stmt;
	String query;
	ResultSet rs;
	
	public void connect() {
		String Driver="";
		String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
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



