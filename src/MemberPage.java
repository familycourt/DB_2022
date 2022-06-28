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
        panel.add(new JLabel("------------------------- ��ȭ ���� -------------------------"));
        panel.add(new JLabel("��ȭ ������ �Է��ϼ���"));
        panel.add(txtInput);
        panel.add(btnGoTicketing);
        panel.add(new JLabel("================ �� ���� Ȯ���ϱ� ================"));
        panel.add(new JLabel("�̸�  --------------  "));
        panel.add(txtUserName);
        panel.add(btnMyTicketing);
        
        btnSearchMovie.addActionListener(new ActionListnerSearchMovie());
        btnMyTicketing.addActionListener(new ActionListnerMyTicketing());
        btnGoTicketing.addActionListener(new ActionListenerExecuteTicketing());
        
        txtOut.setFocusable(false);
        
        add(panel);

        setSize(330, 800); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void connect() {
        String Driver = "";
        String url = "jdbc:mysql://localhost:3306/madang?user=root&serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
        String userid = "madang";
        String pwd = "madang";

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
	        setSize(1000, 500);
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
	        String condition = "";
			
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
			
			while (rs.next())
			{
				str = rs.getString(1);
				System.out.println(str);	
				txtOut.append(str);
				txtOut.append(" / ");
				
			}
			}catch (Exception e9) {
	        	  JOptionPane.showMessageDialog(null, "�˻��� ��ȭ�� �����ϴ�.");
	        	  System.out.print(e9);
	        }
			
			if(txtOut.getText().equals(""))
				JOptionPane.showMessageDialog(null, "�˻��� ����� �����ϴ�.");
			txtOut.setVisible(true);
	    }	
	}
	 	
	class ActionListenerExecuteTicketing implements ActionListener {
	      
	      private void bookTicket(int movie_number, String movie_name) {
	         try {
	            String query = "SELECT * FROM screening_schedule WHERE movie_number=" + Integer.toString(movie_number);
	            
	            pstmt = connection.prepareStatement(query);
	            rs = pstmt.executeQuery();
	            System.out.println(query);
	            rs.next();
	            
	            int screening_schedule_number = rs.getInt(1);
	            int theater_number = rs.getInt(3);
	            
	            query = "SELECT COUNT(*) FROM seat WHERE theater_number=" + Integer.toString(theater_number) + " AND seat_use_status=\"x\"";
	            pstmt = connection.prepareStatement(query);
	            rs = pstmt.executeQuery();
	            System.out.println(query);
	            rs.next();
	            
	            if (rs.getInt(1) == 0) {
	               JOptionPane.showMessageDialog(null, "���� �¼��� �����ϴ�.");
	               return ;
	            }
	            
	            query = "SELECT * FROM seat WHERE theater_number=" + Integer.toString(theater_number) + " AND seat_use_status=\"x\"";
	            pstmt = connection.prepareStatement(query);
	            rs = pstmt.executeQuery();
	            System.out.println(query);
	            rs.next();
	            
	            int seat_number = rs.getInt(1);
	         
	            pstmt.executeUpdate("INSERT INTO booking_info (payment_method, payment_status, payment_amount, user_id, payment_date) VALUES (\"�������Ա�\", \"����\", \"10000\", \"newid1\", CURDATE());");
	            
	            query = "SELECT * FROM booking_info ORDER BY booking_number DESC LIMIT 1";
	            pstmt = connection.prepareStatement(query);
	            rs = pstmt.executeQuery();
	            System.out.println(query);
	            rs.next();
	            int booking_number = rs.getInt(1);
	            
	   
	            pstmt.executeUpdate(String.format("INSERT INTO ticket (screening_schedule_number, theater_number, seat_number, booking_number, ticketing_status, standard_price, selling_price) VALUES(%d, %d, %d, %d, \"o\", 14000, 10000)", screening_schedule_number, theater_number, seat_number, booking_number));
	            
	            JOptionPane.showMessageDialog(null, "���� �����߽��ϴ�!");
	         } catch (Exception e) {
	            System.out.println(e);
	         }
	         
	      }
	      
	      @Override
	       public void actionPerformed(ActionEvent e) {
	         try {
	            String search = txtInput.getText();
	            if (!search.isEmpty()) {
	               
	               String cnt_query = "SELECT COUNT(*) FROM movie WHERE movie_name=\"" + search + "\"";
	               pstmt = connection.prepareStatement(cnt_query);
	               rs = pstmt.executeQuery();
	               rs.next();
	               int count = rs.getInt(1);
	               
	               if (count == 0)
	                  JOptionPane.showMessageDialog(null, "��ȭ���� Ȯ�����ּ���.");
	               else if (count > 1)
	                  JOptionPane.showMessageDialog(null, "�˻��� ��ȭ�� 1�� �̻��Դϴ�.");
	               else
	               {
	                  String query = "SELECT * FROM movie WHERE movie_name=\"" + search + "\"";
	                  pstmt = connection.prepareStatement(query);
	                  System.out.println(query);
	                  rs = pstmt.executeQuery();
	                  rs.next();
	                  bookTicket(rs.getInt(1), rs.getString(2));
	               }
	                  
	            }
	            else
	                   JOptionPane.showMessageDialog(null, "���� �Է����ּ���.");
	         }
	         catch (Exception error) {
	            System.out.print(error);   
	         }
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
				
				if(userId.equals("")) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���!");
					return;
				}
				
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, "�ش��ϴ� ����� �̸��� �������� �ʽ��ϴ�!");
				System.out.println("���� ���� : ");
			      System.out.println(e1.getMessage());
			}
			
			
			
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
				JPanel subPanel = new JPanel();
				new subFrame().add(subPanel);
				subPanel.setVisible(true);
				JTextArea output = new JTextArea();
				subPanel.add(output);
				try {
					output.setText("");
					output.append("\n" + "�󿵽��۳�¥" + "\t" +"�󿵿���" + "\t" + "��Ƚ��" + "\t" + "�󿵽��۽ð�" + "\t" + "�󿵹�ȣ" + "\t" + "�¼���ȣ" + "\t"
					+ "�¼���" + "\t" + "�߱ǿ��� " +"\t" + "ǥ�ذ��� "+"\t" + "�ǸŰ��� " + "\n");
					
					String sql = "select sched.screening_start_date, sched.screening_day, sched.screening_count, sched.screening_start_time,\r\n"
							+ "ticket.screening_schedule_number, ticket.seat_number, theaters.seat_count, ticket.ticketing_status, ticket.standard_price, ticket.selling_price\r\n"
							+ "from ticket\r\n"
							+ "left join screening_schedule as sched\r\n"
							+ "on ticket.screening_schedule_number = sched.screening_schedule_number\r\n"
							+ "left join theaters\r\n"
							+ "on ticket.theater_number = theaters.theater_number\r\n"
							+ "where ticket.tichet_number= ";
					sql = sql + txtTicketNumber.getText()  + ";";
					
					System.out.println(sql);
			         stmt = connection.createStatement();
			         rs = stmt.executeQuery(sql);
			         
			         while (rs.next()) {
				            String str = rs.getDate(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getTime(4) + "\t" + rs.getInt(5) 
				            + "\t" + rs.getInt(6) + "\t" + rs.getInt(7) + "\t" + rs.getString(8)+  "\t" + rs.getInt(9) + "\t" + rs.getInt(10) + "\n";
				            System.out.println(str);
				            output.append(str);
				         }
				}catch(Exception e3){
					System.out.println("���� ���� : ");
			         System.out.println(e3.getMessage());
				}
				
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





