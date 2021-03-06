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
public class DB_2022_project extends JFrame{
	Connection connection;
	Statement stmt;
	String query;
	ResultSet rs;
	
	public DB_2022_project() {
		super("18011547 최예린 18011481 송인제");
		
		JPanel panel = new JPanel();
		JButton btnAdmin = new JButton("관리자");
		JButton btnMember = new JButton("회원");
		
        panel.add(btnAdmin);
        panel.add(btnMember);
        
        add(panel);
        
        setVisible(true);
        setSize(300, 200); //창 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPage(); 
                
            }
        });
        
        btnMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemberPage();// 창 안보이게 하기 
               
            }
        });
		
	}
	
	public static void main(String[] args) {
		new DB_2022_project();
	}
}






