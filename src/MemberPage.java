import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MemberPage extends JFrame{
	Connection connection;
    Statement stmt;
    String query;
    ResultSet rs;

    PreparedStatement pstmt;
	
	JTextArea txtOut = new JTextArea();
	JButton btnGoTicketing = new JButton("��ȭ �ٷ� �����ϱ�");
	
	JTextField txtSearchMovie = new  JTextField(" ��ȭ �̸��� �Է����ּ��� ");
    JTextField txtSearchDirector = new  JTextField(" ���� �̸��� �Է����ּ��� ");
    JTextField txtSearchActor = new  JTextField(" ��� �̸��� �Է����ּ��� ");
    JTextField txtSearchGenre = new  JTextField("     �帣�� �Է����ּ���     ");
    
	MemberPage(){
		super("��� ȭ��"); //Ÿ��Ʋ
		connect();
		
        JPanel panel = new JPanel();
        JButton btnSearchMovie = new JButton("��ȸ");
        JButton btnSearchDirector = new JButton("��ȸ");
        JButton btnSearchActor = new JButton("��ȸ");
        JButton btnSearchGenre = new JButton("��ȸ");
        JButton btnMyTicketing = new JButton("               ���� Ȯ��               ");
        
        panel.add(new JLabel("               ��ȭ �˻�               "));
        panel.add(txtSearchMovie);
        panel.add(btnSearchMovie);
        panel.add(txtSearchDirector);
        panel.add(btnSearchDirector);
        panel.add(txtSearchActor);
        panel.add(btnSearchActor);
        panel.add(txtSearchGenre);
        panel.add(btnSearchGenre);
        panel.add(btnGoTicketing);
        panel.add(txtOut);
        panel.add(btnMyTicketing);
        
        btnSearchMovie.addActionListener(new ActionListnerSearchMovie());
        btnSearchDirector.addActionListener(new ActionListnerSearchDirector());
        btnSearchActor.addActionListener(new ActionListnerSearchActor());
        btnSearchGenre.addActionListener(new ActionListnerSearchGenre());
        btnMyTicketing.addActionListener(new ActionListnerMyTicketing());
        
        txtOut.setVisible(false);
        btnGoTicketing.setVisible(false);
        
        add(panel);

        setSize(300, 300); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void connect() {
        String Driver = "";
        String url = "jdbc:mysql://localhost:3306/madang?user=root&serverTimezone=Asia/Seoul&useSSL=false";
        String userid = "root";
        String pwd = "0907";

        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("����̺� �ε� ����");
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        }

        try {
           System.out.println("����̺� ���� �غ�...");
           connection = DriverManager.getConnection(url, userid, pwd);
           System.out.println("����̺� ���� ����");
        } catch (SQLException e) {
           e.printStackTrace();

        }
  }
	
	class subFrame extends JFrame	{
		public subFrame(){
			setVisible(true);
	        setSize(1200, 800);
	        setLocationRelativeTo(null);
	       
	        
		}
	}
	
	
	class ActionListnerSearchMovie implements ActionListener{
		
		@Override
	    public void actionPerformed(ActionEvent e) {
			try {
			String text = txtSearchMovie.getText();
			
			String str="";
			String sql = "select movie_name from movie where movie_name=?;";
	        
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, text);
			
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				str = rs.getString(1);
				System.out.println(str);				
			}
			
			 txtOut.setText(str);
			}catch (Exception e9) {
	        	  JOptionPane.showMessageDialog(null, "�˻��� ��ȭ�� �����ϴ�.");
	        	  System.out.print(e9);
	        }
			
			txtOut.setVisible(true);
	        btnGoTicketing.setVisible(true);
	    }	
	}
	
	class ActionListnerSearchDirector implements ActionListener{
			
			@Override
		    public void actionPerformed(ActionEvent e1) {
				try {
					String text = txtSearchDirector.getText();
					
					String str="";
					String sql = "select movie_name from movie where director_name=?;";
			        
					pstmt = connection.prepareStatement(sql);
					
					pstmt.setString(1, text);
					
					rs = pstmt.executeQuery();

					while (rs.next())
					{
						str = rs.getString(1);
						System.out.println(str);				
					}
					
					 txtOut.setText(str);
					}catch (Exception e9) {
			        	  JOptionPane.showMessageDialog(null, "�˻��� ��ȭ�� �����ϴ�.");
			        	  System.out.print(e9);
			        }
				txtOut.setVisible(true);
		        btnGoTicketing.setVisible(true);
		    }	
		}
	
	class ActionListnerSearchActor implements ActionListener{
		
		@Override
	    public void actionPerformed(ActionEvent e2) {
			try {
				String text = txtSearchMovie.getText();
				
				String str="";
				String sql = "select movie_name from movie where actor_name=?;";
		        
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, text);
				
				rs = pstmt.executeQuery();

				while (rs.next())
				{
					str = rs.getString(1);
					System.out.println(str);				
				}
				
				 txtOut.setText(str);
				}catch (Exception e9) {
		        	  JOptionPane.showMessageDialog(null, "�˻��� ��ȭ�� �����ϴ�.");
		        	  System.out.print(e9);
		        }
			txtOut.setVisible(true);
	        btnGoTicketing.setVisible(true);
	    }	
	}
	
	class ActionListnerSearchGenre implements ActionListener{
		
		@Override
	    public void actionPerformed(ActionEvent e3) {
			try {
				String text = txtSearchMovie.getText();
				
				String str="";
				String sql = "select movie_name from movie where genre=?;";
		        
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, text);
				
				rs = pstmt.executeQuery();

				while (rs.next())
				{
					str = rs.getString(1);
					System.out.println(str);				
				}
				
				 txtOut.setText(str);
				}catch (Exception e9) {
		        	  JOptionPane.showMessageDialog(null, "�˻��� ��ȭ�� �����ϴ�.");
		        	  System.out.print(e9);
		        }
			txtOut.setVisible(true);
	        btnGoTicketing.setVisible(true);
		
	    }	
	}
	
	class ActionListnerMyTicketing implements ActionListener{
		JPanel subPanel = new JPanel();
		JTextField txtTicketNumber = new JTextField(5);
		JButton btnShowAll = new JButton("���� ��");
		JButton btnDelete = new JButton("���� ��ȭ ����");
		JButton btnChangeMovie = new JButton("���� ��ȭ ����");
		JButton btnChangeTime = new JButton("�� ���� ����");
		JTextField movieList = new JTextField(100);
		@Override
	    public void actionPerformed(ActionEvent e) {
			new subFrame().add(subPanel).setVisible(true);
			
			subPanel.add(new JLabel("Ƽ�Ϲ�ȣ"));
			subPanel.add(txtTicketNumber);
			subPanel.add(btnShowAll);
			subPanel.add(btnDelete);
			subPanel.add(btnChangeMovie);
			subPanel.add(btnChangeTime);
			subPanel.add(movieList);
			
			movieList.setEditable(false);
			
			btnShowAll.addActionListener(new ActionListnerShowAll());
			btnDelete.addActionListener(new ActionListnerDelete());
			btnChangeMovie.addActionListener(new ActionListnerChangeMovie());
			btnChangeTime.addActionListener(new ActionListnerChangeTime());
		}
		
		class ActionListnerShowAll implements ActionListener{
			
			@Override
		    public void actionPerformed(ActionEvent e5) {
				new subFrame();
			
		    }	
		}
		
		class ActionListnerDelete implements ActionListener{
					
					@Override
				public void actionPerformed(ActionEvent e6) {
						
					
				}
		}
		
		class ActionListnerChangeMovie implements ActionListener{
			
			@Override
		    public void actionPerformed(ActionEvent e7) {
				
			
		    }	
		}
		
		class ActionListnerChangeTime implements ActionListener{
			
			@Override
		    public void actionPerformed(ActionEvent e8) {
				
			
		    }	
		}
		
	}
}





