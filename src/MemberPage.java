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
	
	JTextArea txtOut = new JTextArea("��ȸ�� ��ȭ�� �����ϴ�.");
	JTextField txtInput = new JTextField(10);
	JButton btnGoTicketing = new JButton("�����ϱ�");
	
	JTextField txtUserName = new JTextField(10);
	JTextField txtSearchMovie = new  JTextField(10);
    JTextField txtSearchDirector = new  JTextField(10);
    JTextField txtSearchActor = new  JTextField(10);
    JTextField txtSearchGenre = new  JTextField(10);
    
	MemberPage(){
		super("��� ȭ��"); //Ÿ��Ʋ
		connect();
		
        JPanel panel = new JPanel();
        JButton btnSearchMovie = new JButton("                 ��  ȸ                 ");
        JButton btnMyTicketing = new JButton("               ���� Ȯ��               ");
        
        panel.add(new JLabel("================== ��ȭ �˻� =================="));
        panel.add(new JLabel("��ȭ �̸� ------------"));
        panel.add(txtSearchMovie);
        panel.add(new JLabel("���� �̸� ------------"));
        panel.add(txtSearchDirector);
        panel.add(new JLabel("��� �̸� -------------"));
        panel.add(txtSearchActor);
        panel.add(new JLabel("�帣 �̸� -------------"));
        panel.add(txtSearchGenre);
        panel.add(btnSearchMovie);
        panel.add(new JLabel("================ ��ȸ�� ��ȭ ��� ================"));
        panel.add(txtOut);
        panel.add(new JLabel("------------------ ��ȭ ���� ------------------"));
        panel.add(new JLabel("��ȭ ������ �Է��ϼ���"));
        panel.add(txtInput);
        panel.add(btnGoTicketing);
        panel.add(new JLabel("================ �� ���� Ȯ���ϱ� ================"));
        panel.add(new JLabel("�̸�  --------------  "));
        panel.add(txtUserName);
        panel.add(btnMyTicketing);
        
        btnSearchMovie.addActionListener(new ActionListnerSearchMovie());
        btnMyTicketing.addActionListener(new ActionListnerMyTicketing());
        
        txtOut.setFocusable(false);
        
        add(panel);

        setSize(330, 800); 
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
	        setSize(700, 500);
	        setLocationRelativeTo(null);
	       
	        
		}
	}
	
	
	class ActionListnerSearchMovie implements ActionListener{
		
		@Override
	    public void actionPerformed(ActionEvent e) {
			try {
			String text1 = txtSearchMovie.getText();
			String text2 = txtSearchDirector.getText();
			String text3 = txtSearchActor.getText();
			String text4 = txtSearchGenre.getText();
			
			String str="";
		
			int cnt =0;
			
			if(!text1.equals(""))
				cnt++;
			if(!text2.equals(""))
				cnt++;
			if(!text3.equals(""))
				cnt++;
			if(!text4.equals(""))
				cnt++;
			cnt--;
			
			
			String sql = "select movie_name from movie where ";
			
			if(!text1.equals("")) {
				sql = sql+"movie_name = " + '\"' + text1 + '\"';
				if(cnt>0) {
					sql = sql+"AND ";
					cnt--;
				}
			}
			if(!text2.equals("")) {
				sql = sql+"director_name = " + '\"' + text2 + '\"' ;
				if(cnt>0) {
					sql = sql+"AND ";
					cnt--;
				}
			}
			
			if(!text3.equals("")) {
				sql = sql+"actor_name = " +'\"' + text3 + '\"';
				if(cnt>0) {
					sql = sql+"AND";
					cnt--;
				}
			}
				
			if(!text4.equals("")) {
				sql = sql+"genre = " + '\"' + text4 + '\"';
				if(cnt>0) {
					sql = sql+"AND";
					cnt--;
				}
			}
		
			 pstmt = connection.prepareStatement(sql);
			 System.out.println(sql);
			rs = pstmt.executeQuery();
			txtOut.setText("");
			
			txtOut.setText("");
			while (rs.next())
			{
				str = rs.getString(1);
				System.out.println(str);	
				txtOut.append(str);
				txtOut.append(" / ");
				
			}
				// ����
				
			
			}catch (Exception e9) {
	        	  JOptionPane.showMessageDialog(null, "�˻��� ��ȭ�� �����ϴ�.");
	        	  System.out.print(e9);
	        }
			
			txtOut.setVisible(true);
	    }	
	}
	 	

	
	class ActionListnerMyTicketing implements ActionListener{
		String userId = ""; 
		JPanel subPanel = new JPanel();
		JTextField txtTicketNumber = new JTextField(5);
		JButton btnShowAll = new JButton("���� ��");
		JButton btnDelete = new JButton("���� ��ȭ ����");
		JButton btnChangeMovie = new JButton("���� ��ȭ ����");
		JButton btnChangeTime = new JButton("�� ���� ����");
		JTextArea movieList = new JTextArea();
		@Override
	    public void actionPerformed(ActionEvent e) {
			try{
				
				String sql = "select user_id from user where user_name= \"";
				sql = sql + txtUserName.getText() +"\" ;";
				pstmt = connection.prepareStatement(sql);
				System.out.println(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next())
				{
					userId = rs.getString(1);
					System.out.println(userId);	
					
				}
				
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, "�ش��ϴ� ����� �̸��� �������� �ʽ��ϴ�!");
				System.out.println("���� ���� : ");
			      System.out.println(e1.getMessage());
			}
			
			
			if(userId.equals(""))
				return;
			
			new subFrame().add(subPanel).setVisible(true);
			
			subPanel.add(new JLabel("Ƽ�Ϲ�ȣ"));
			subPanel.add(txtTicketNumber);
			subPanel.add(btnShowAll);
			subPanel.add(btnDelete);
			subPanel.add(btnChangeMovie);
			subPanel.add(btnChangeTime);
			subPanel.add(movieList);
			
			movieList.setText("");
			movieList.setEditable(false);
			makeMyTicketList();
			
			btnShowAll.addActionListener(new ActionListnerShowAll());
			btnDelete.addActionListener(new ActionListnerDelete());
			btnChangeMovie.addActionListener(new ActionListnerChangeMovie());
			btnChangeTime.addActionListener(new ActionListnerChangeTime());
		}
		
		void makeMyTicketList() {
			
			try {
		         movieList.setText("");
		         movieList.append("\n" + "Ƽ�Ϲ�ȣ" + "\t" +"��ȭ��" + "\t" + "����" + "\t" + "�󿵰���ȣ" + "\t" + "�¼���ȣ" + "\t" + "�ǸŰ��� " + "\n");
	  
		         String sql = "select ticket.tichet_number, movie.movie_name, sched.screening_start_date, ticket.theater_number, ticket.seat_number, book.payment_amount\r\n"
		         		+ "from booking_info as book\r\n"
		         		+ "left join ticket\r\n"
		         		+ "on book.booking_number = ticket.booking_number\r\n"
		         		+ "left join screening_schedule as sched\r\n"
		         		+ "on sched.screening_schedule_number = ticket.screening_schedule_number\r\n"
		         		+ "left join movie\r\n"
		         		+ "on movie.movie_number = sched.movie_number\r\n"
		         		+ "where user_id =";
		         sql = sql + " \"" + userId + "\";";
		         System.out.println(sql);
		         stmt = connection.createStatement();
		         rs = stmt.executeQuery(sql);
		         
		         while (rs.next()) {
		            String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDate(3) + "\t" + rs.getInt(4) 
		            + "\t" + rs.getInt(5) + "\t" + rs.getInt(6) + "\n";
		            System.out.println(str);
		            movieList.append(str);
		         }
		         
		      }catch (Exception e2) {
		         System.out.println("���� ���� : ");
		         System.out.println(e2.getMessage());
		      }
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





