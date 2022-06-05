import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AdminPage extends JFrame  implements ActionListener{
	Connection connection;
    Statement stmt;
    String query;
    ResultSet rs;
	   
	public static void main(String[] args) {
		 new AdminPage();
	}
	
	String[] tables = {null ,"��ȭ","������","�󿵰�", "Ƽ��", "�¼�", "ȸ����", "��������"};
	JTextArea txtArea = new JTextArea("�ȳ�");
	JPanel resultPanel = new JPanel();
	JPanel editPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JComboBox tableBox = new JComboBox(tables);
	JButton btnInput = new JButton("�Է�");
	JButton btnResetInput = new JButton("�ٽ��Է�");
	
	JTextField txt1 = new JTextField(10);
	JTextField txt2 = new JTextField(10);
	JTextField txt3 = new JTextField(10);
	JTextField txt4 = new JTextField(10);
	JTextField txt5 = new JTextField(10);
	JTextField txt6 = new JTextField(10);
	JTextField txt7 = new JTextField(10);
	JTextField txt8 = new JTextField(10);
	JTextField txt9 = new JTextField(10);
	
	AdminPage(){
		super("������ ȭ��"); //Ÿ��Ʋ
		
		connect();
		
		JPanel panel = new JPanel();
		JButton btnInit = new JButton("�ʱ�ȭ");
		JButton btnShow = new JButton("��ü ���̺� ����");
		JButton btnDelete = new JButton("����");
		JButton btnChange = new JButton("����");
		JButton btnEnter = new JButton("Ȯ��");
		JScrollPane resultPane = new JScrollPane(txtArea);
		JTextField txtEdit = new JTextField(20);
		
		txtArea.setEditable(false);
		
		panel.add(btnInit);
		panel.add(btnShow);
		panel.add(tableBox);
		panel.add(btnInput);
		panel.add(btnResetInput);
		panel.add(btnDelete);
		panel.add(btnChange);
		panel.add(resultPane);
		editPanel.add(txtEdit);
		editPanel.add(btnEnter);
		resultPanel.add(txtArea);
		
		add(panel, BorderLayout.NORTH);
		add(resultPanel, BorderLayout.CENTER);
		add(editPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.CENTER);
		resultPanel.setVisible(false);
		editPanel.setVisible(true);
		inputPanel.setVisible(false);
		btnResetInput.setVisible(false);

		tableBox.addActionListener(this);
		btnInit.addActionListener(new ActionListenerInit());
		btnShow.addActionListener(new ActionListenerShow());
		btnInput.addActionListener(new ActionListenerInput());
		btnResetInput.addActionListener(new ActionListenerInputReset());
		btnDelete.addActionListener(new ActionListenerDelete());
		btnChange.addActionListener(new ActionListenerChange());
		
        setSize(840, 630); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void connect() {
	      String Driver = "";
	      String url = "jdbc:mysql://localhost:3306/mydb?user=root&serverTimezone=Asia/Seoul&useSSL=false";
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

	
	class ActionListenerInit implements ActionListener{

		@Override
        public void actionPerformed(ActionEvent e1) {
			InitDB();
        }	
		
	}
	
	public void InitDB() {
	      
	      try {
	         stmt = connection.createStatement();
	         
	         // madang �ʱ�ȭ
	         System.out.println("madang �ʱ�ȭ");
	         stmt.executeUpdate("DROP TABLE IF EXISTS `madang`.`screening_schedule`;");
	         stmt.executeUpdate("DROP TABLE IF EXISTS `madang`.`ticket`;");
	         stmt.executeUpdate("DROP TABLE IF EXISTS `madang`.`seat`;");
	         stmt.executeUpdate("DROP TABLE IF EXISTS `madang`.`movie`;");
	         stmt.executeUpdate("DROP TABLE IF EXISTS `madang`.`theaters`;");
	         stmt.executeUpdate("DROP TABLE IF EXISTS `madang`.`booking_info`;");  
	         stmt.executeUpdate("DROP TABLE IF EXISTS `madang`.`user`;");
	             
	         
	         stmt.executeUpdate("USE `madang` ;");
	         
	         
	         // Table `madang`.'movie'

	         
	         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`movie` (\r\n" + 
	               "  `movie_number` INT(11) NOT NULL,\r\n" + 
	               "  `movie_name` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `movie_time` INT(11) NULL DEFAULT NULL,\r\n" + 
	               "  `movie_rating` INT(11) NULL DEFAULT NULL,\r\n" + 
	               "  `director_name` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `actor_name` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `genre` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `movie_introduction` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `movie_start_date` DATE NULL DEFAULT NULL,\r\n" + 
	               "  PRIMARY KEY (`movie_number`))");
	               
	         System.out.println("Table `madang`.`movie` CREATE �Ϸ�");
	         

	         // Table `madang`.`theaters`
	                  
	         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`theaters` (\r\n" + 
	               "  `theater_number` INT(11) NOT NULL,\r\n" + 
	               "  `seat_count` INT(11) NULL DEFAULT NULL,\r\n" + 
	               "  `theater_use_status` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  PRIMARY KEY (`theater_number`))");   
	               
	         
	         System.out.println("Table `madang`.`theaters` CREATE �Ϸ�");
	         
	         
	         // Table `madang`.`screening_schedule`
	        
	         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`screening_schedule` (\r\n" + 
	               "  `screening_schedule_number` INT(11) NOT NULL,\r\n" + 
	               "  `movie_number` INT(11) NOT NULL,\r\n" + 
	               "  `theater_number` INT(11) NOT NULL,\r\n" + 
	               "  `screening_start_date` DATE NULL DEFAULT NULL,\r\n" + 
	               "  `screening_day` VARCHAR(10) NULL DEFAULT NULL,\r\n" + 
	               "  `screening_count` INT(11) NULL DEFAULT NULL,\r\n" + 
	               "  `screening_start_time` TIME NULL DEFAULT NULL,\r\n" + //���̺� �ȿ� ���� �ܷ�Ű ��� ��Ű?
	               "  PRIMARY KEY (`screening_schedule_number`),\r\n" +       
	               "  CONSTRAINT `fk_movie_movie_number`\r\n" + 
	               "    FOREIGN KEY (`movie_number`)\r\n" + 
	               "    REFERENCES `madang`.`movie` (`movie_number`)\r\n" + 
	               "    ON DELETE NO ACTION\r\n" + 
	               "    ON UPDATE NO ACTION,\r\n" + 
	               "  CONSTRAINT `fk_theaters_theater_number`\r\n" + 
	               "    FOREIGN KEY (`theater_number`)\r\n" + 
	               "    REFERENCES `madang`.`theaters` (`theater_number`)\r\n" + 
	               "    ON DELETE NO ACTION\r\n" + 
	               "    ON UPDATE NO ACTION)");
	         
	         System.out.println("Table `madang`.`screening_schedule` CREATE �Ϸ�");
	         
	         // Table `madang`.'seat`
	         // ��Ű ���� ��
	                  
	         //����
	         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`seat` (\r\n" + 
	               "  `seat_number` INT(11) NOT NULL,\r\n" + // null?
	               "  `theater_number` INT(11) NOT NULL,\r\n" + 
	               "  `seat_use_status` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	              // "  CONSTRAINT pk_seat`\r\n" + 
	               "  PRIMARY KEY (`seat_number`))" );
//	               "  CONSTRAINT `fk_theaters_theater_number`\r\n" + 
//	               "    FOREIGN KEY (`theater_number`)\r\n" + 
//	               "    REFERENCES `madang`.`theaters` (`theater_number`)\r\n" + 
//	               "    ON DELETE NO ACTION\r\n" + 
//	               "    ON UPDATE NO ACTION)"); 
	         
	         
	         System.out.println("Table `madang`.`seat` CREATE �Ϸ�");
	         
	         // Table `madang`.`ticket`

	         // ����
	         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`ticket` (\r\n" + 
	               "  `tichet_number` INT(11) NOT NULL,\r\n" + 
	               "  `screenig_schedule_number` INT(11) NOT NULL,\r\n" + 
	               "  `theater_number` INT(11) NOT NULL,\r\n" + 
	               "  `seat_number` INT(11) NULL DEFAULT NULL,\r\n" + // Ű ���� �ʿ�    
	               "  `booking_number` INT(11) NOT NULL,\r\n" + 
	               "  `ticketing_status` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `standard_price` INT(11) NULL DEFAULT NULL,\r\n" + 
	               "  `selling_price` INT(11) NULL DEFAULT NULL,\r\n" + 
	               "  PRIMARY KEY (`tichet_number`),\r\n" +
	               "  CONSTRAINT `fk_theaters_theater_number_ticket`\r\n" + 
	               "    FOREIGN KEY (`theater_number`)\r\n" + 
	               "    REFERENCES `madang`.`theaters` (`theater_number`)\r\n" + 
	               "    ON DELETE NO ACTION\r\n" + 
	               "    ON UPDATE NO ACTION,\r\n" + 
	               "  CONSTRAINT `fk_seat_seat_number`\r\n" + 
	               "    FOREIGN KEY (`seat_number`)\r\n" + 
	               "    REFERENCES `madang`.`seat` (`seat_number`)\r\n" + 
	               "    ON DELETE NO ACTION\r\n" + 
	               "    ON UPDATE NO ACTION)");
	               
	         
	         
	         System.out.println("Table `madang`.`ticket` CREATE �Ϸ�");
	         
	        
	         
	         // Table `madang`.`user`

	                  
	         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`user` (\r\n" + 
	               "  `user_id` VARCHAR(45) NOT NULL,\r\n" + 
	               "  `user_name` VARCHAR(45)  NULL DEFAULT NULL,\r\n" + 
	               "  `user_phone_number` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `user_email_address` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  PRIMARY KEY (`user_id`))");   
	         System.out.println("Table `madang`.`user` CREATE �Ϸ�");
	         
	         // Table `madang`.`booking_info`
	         
	                  
	         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`booking_info` (\r\n" + 
	               "  `booking_number` INT(11) NOT NULL,\r\n" + 
	               "  `payment_method` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `payment_status` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `payment_amount` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
	               "  `user_id` VARCHAR(45) NOT NULL,\r\n" + 
	               "  `payment_date` DATE NULL DEFAULT NULL,\r\n" + 
	               "  PRIMARY KEY (`booking_number`),\r\n" +
	               "  CONSTRAINT `fk_user_user_id`\r\n" + 
	               "    FOREIGN KEY (`user_id`)\r\n" + 
	               "    REFERENCES `madang`.`user` (`user_id`)\r\n" + 
	               "    ON DELETE NO ACTION\r\n" + 
	               "    ON UPDATE NO ACTION)"); 
	         
	         System.out.println("Table `madang`.`booking_info` CREATE �Ϸ�");
	         
	         // INSERT INTO movie
	         System.out.println("INSERT INTO movie");   // �������� �����ʿ�  
	         stmt.executeUpdate("INSERT INTO movie VALUES(1, '���˵���2', '106', 19, '�̻��', '�ռ���', '����', '�ռ��������ߴ�', '2021-01-02');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(2, '���� ��Ʈ������', '126', 15,  '�� ���̹�', '���׵�Ʈ �Ĺ���ġ', '����', '���� ��ȭ�̴�', '2021-02-03');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(3, '�״밡 ����', '124', 15, '�̽���', '������', '��ť���͸�', '��ť���͸� ��ȭ�̴�', '2021-03-01');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(4, '�Ǵ� ������ ���ϴ�', '90', 12, '����', '������', '�׼�', '�׼ǿ�ȭ�̴�', '2021-04-01');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(5, '�ȳ��ϼ���', '118', 12, '������', '��ȯ��', '���', '��� ��ȭ�̴�', '2021-05-01');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(6, '���Ŀ', '129', 15, '������ ����ī��', '�۰�ȣ', '���', '��� ��ȭ�̴�', '2021-06-01');");      
	         stmt.executeUpdate("INSERT INTO movie VALUES(7, '��ġ�� �뷡', '113', 15, '����', '������', '��ť���͸�', '��ť���͸� ��ȭ�̴�', '2021-07-01');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(8, '������', '108', 18,  '�ż���', '������', '���', '��� ��ȭ�̴�', '2021-08-01');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(9, '���� ����: ���̴Ͼ�', '147', 12, '�ݸ� Ʈ�����ο�', 'ũ���� ����', '�׼�', '�׼ǿ�ȭ�̴�', '2021-09-01');");
	         stmt.executeUpdate("INSERT INTO movie VALUES(10, '������ ������ �������', '95', 12, '�ǿ��� �ǳ��', 'īƮ�� ����', '���', '��� ��ȭ�̴�', '2021-10-01');");
	         
	         // INSERT INTO theaters
	         System.out.println("INSERT INTO theaters");   
	         stmt.executeUpdate("INSERT INTO theaters VALUES(1, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(2, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(3, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(4, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(5, 2, 'o');");                                                                                                                                                                                         
	         stmt.executeUpdate("INSERT INTO theaters VALUES(6, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(7, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(8, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(9, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO theaters VALUES(10, 2, 'o');");
	         
	         // INSERT INTO screening_schedule
	         System.out.println("INSERT INTO screening_schedule");   
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(1, 1, 1, STR_TO_DATE('2022-05-01','%Y-%m-%d'), '�Ͽ���', 1, TIME_FORMAT('08:00', '%H:%i'));");         
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(2, 2, 2, STR_TO_DATE('2022-05-02','%Y-%m-%d'), '������', 1, TIME_FORMAT('08:00', '%H:%i'));");      
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(3, 3, 3, STR_TO_DATE('2022-05-03','%Y-%m-%d'), 'ȭ����', 1, TIME_FORMAT('08:00', '%H:%i'));");      
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(4, 4, 4, STR_TO_DATE('2022-05-04','%Y-%m-%d'), '������', 1, TIME_FORMAT('08:00', '%H:%i'));");      
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(5, 5, 5, STR_TO_DATE('2022-05-05','%Y-%m-%d'), '�����', 1, TIME_FORMAT('08:00', '%H:%i'));");
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(6, 6, 6, STR_TO_DATE('2022-05-06','%Y-%m-%d'), '�ݿ���', 1, TIME_FORMAT('08:00', '%H:%i'));");      
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(7, 7, 7, STR_TO_DATE('2022-05-07','%Y-%m-%d'), '�����', 1, TIME_FORMAT('08:00', '%H:%i'));");      
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(8, 8, 8, STR_TO_DATE('2022-05-08','%Y-%m-%d'), '�Ͽ���', 1, TIME_FORMAT('08:00', '%H:%i'));");      
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(9, 9, 9, STR_TO_DATE('2022-05-09','%Y-%m-%d'), '������', 1, TIME_FORMAT('08:00', '%H:%i'));");      
	         stmt.executeUpdate("INSERT INTO screening_schedule VALUES(10, 10, 10, STR_TO_DATE('2022-05-10','%Y-%m-%d'), 'ȭ����', 1, TIME_FORMAT('08:00', '%H:%i'));");      


	         // INSERT INTO seat
	         System.out.println("INSERT INTO seat");   
	         stmt.executeUpdate("INSERT INTO seat VALUES(1, 1, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(2, 1, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(3, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(4, 2, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(5, 3, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(6, 3, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(7, 4, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(8, 4, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(9, 5, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(10, 5, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(11, 6, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(12, 6, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(13, 7, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(14, 7, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(15, 8, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(16, 8, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(17, 9, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(18, 9, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(19, 10, 'o');");
	         stmt.executeUpdate("INSERT INTO seat VALUES(20, 10, 'o');");
	         
	         // INSERT INTO ticket
	         System.out.println("INSERT INTO ticket");   
	         stmt.executeUpdate("INSERT INTO ticket VALUES(1, 1, 1, 1, 1, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(2, 1, 1, 2, 2, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(3, 2, 2, 3, 3, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(4, 2, 2, 4, 4, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(5, 3, 3, 5, 5, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(6, 3, 3, 6, 6, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(7, 4, 4, 7, 7, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(8, 4, 4, 8, 8, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(9, 5, 5, 9, 9, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(10, 5, 5, 10, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(11, 6, 5, 11, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(12, 6, 5, 12, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(13, 7, 5, 13, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(14, 7, 5, 14, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(15, 8, 5, 15, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(16, 8, 5, 16, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(17, 9, 5, 17, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(18, 9, 5, 18, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(19, 10, 5, 19, 10, 'x', '14000', '10000');");
	         stmt.executeUpdate("INSERT INTO ticket VALUES(20, 10, 5, 20, 10, 'x', '14000', '10000');");
	         
	         
	         

	         // INSERT INTO user
	         System.out.println("INSERT INTO user");   
	         stmt.executeUpdate("INSERT INTO user VALUES('newid1', 'ȫ�浿', '010-0001-0001', 'email1@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid2', '����ȭ', '010-0002-0002', 'email2@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid3', '��ȭ��', '010-0003-0003', 'email3@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid4', '������', '010-0004-0004', 'email4@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid5', '�����', '010-0005-0005', 'email5@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid6', '�����', '010-0006-0006', 'email6@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid7', '������', '010-0007-0007', 'email7@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid8', '���ӽ�', '010-0008-0008', 'email8@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid9', 'ȭ����', '010-0009-0009', 'email9@naver.com');");
	         stmt.executeUpdate("INSERT INTO user VALUES('newid10', '�浿ȫ', '010-0010-0010', 'email10@naver.com');");
	         
	         // INSERT INTO booking_info
	         System.out.println("INSERT INTO booking_info");   
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(1, '�������Ա�', '����', '7000', 'newid1', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(2, '�������Ա�', '����', '7000', 'newid2', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(3, '�������Ա�', '����', '7000', 'newid3', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(4, '�������Ա�', '����', '7000', 'newid4', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(5, '�������Ա�', '����', '7000', 'newid5', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(6, '�������Ա�', '����', '7000', 'newid6', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(7, '�������Ա�', '����', '7000', 'newid7', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(8, '�������Ա�', '����', '7000', 'newid8', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(9, '�������Ա�', '����', '7000', 'newid9', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         stmt.executeUpdate("INSERT INTO booking_info VALUES(10, '�������Ա�', '����', '7000', 'newid10', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
	         
	         
	         System.out.println("Table �ʱ�ȭ ����");   
	      }catch(SQLException e10){
	    	  e10.printStackTrace();
	      }
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == tableBox) {
			switch((String)tableBox.getSelectedItem()) {
			case "��ȭ": 
				makeInputMovie();
				break;
			case "������": 
				makeInputTimetable();
				break;
			case "�󿵰�": 
				makeInputTheater();
				break;
			case "Ƽ��": 
				makeInputTicket();
				break;
			case "�¼�": 
				makeInputSeat();
				break;
			case "ȸ����": 
				makeInputUser();
				break;
			case "��������": 
				makeInputTicketinginfo();
				break;
			}
		}
	}
	
	void setBlockTableBox() {
		btnInput.setVisible(true);
		btnResetInput.setVisible(false);
		inputPanel.setVisible(false);
		tableBox.setEnabled(true);
	}
	
	void makeInputMovie() {
		inputPanel.removeAll();
		JButton btnInputMovie = new JButton("Ȯ��");
		inputPanel.add(new JTextArea("��ȭ��ȣ")).setFocusable(false);
		inputPanel.add(txt1);
		inputPanel.add(new JTextArea("��ȭ��")).setFocusable(false);
		inputPanel.add(txt2);
		inputPanel.add(new JTextArea("�󿵽ð�")).setFocusable(false);
		inputPanel.add(txt3);
		inputPanel.add(new JTextArea("�󿵵��")).setFocusable(false);
		inputPanel.add(txt4);
		inputPanel.add(new JTextArea("������")).setFocusable(false);
		inputPanel.add(txt5);
		inputPanel.add(new JTextArea("����")).setFocusable(false);
		inputPanel.add(txt6);
		inputPanel.add(new JTextArea("�帣")).setFocusable(false);
		inputPanel.add(txt7);
		inputPanel.add(new JTextArea("��ȭ�Ұ�")).setFocusable(false);
		inputPanel.add(txt8);
		inputPanel.add(new JTextArea("������ ����")).setFocusable(false);
		inputPanel.add(txt9);
		inputPanel.add(btnInputMovie);	
		btnInputMovie.addActionListener(new ActionListnerInputMovie());
	}
	
	class ActionListnerInputMovie implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e) {
			setBlockTableBox();
        }	
	}
	
	void makeInputTimetable() {
		inputPanel.removeAll();
		JButton btnInputTimetable = new JButton("Ȯ��");
		inputPanel.add(new JTextArea("��������ȣ")).setFocusable(false);
		inputPanel.add(txt1);
		inputPanel.add(new JTextArea("��ȭ��ȣ")).setFocusable(false);
		inputPanel.add(txt2);
		inputPanel.add(new JTextArea("�󿵰� ��ȣ")).setFocusable(false);
		inputPanel.add(txt3);
		inputPanel.add(new JTextArea("�󿵽�����")).setFocusable(false);
		inputPanel.add(txt4);
		inputPanel.add(new JTextArea("�󿵿���")).setFocusable(false);
		inputPanel.add(txt5);
		inputPanel.add(new JTextArea("��ȸ��")).setFocusable(false);
		inputPanel.add(txt6);
		inputPanel.add(new JTextArea("�󿵽�������")).setFocusable(false);
		inputPanel.add(txt7);
		inputPanel.add(btnInputTimetable);	
		btnInputTimetable.addActionListener(new ActionListnerInputTimetable());
	}
	
	class ActionListnerInputTimetable implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e) {
			setBlockTableBox();
        }	
	}
	
	void makeInputTheater() {
		inputPanel.removeAll();
		JButton btnInputTheater = new JButton("Ȯ��");
		inputPanel.add(new JTextArea("�󿵰���ȣ")).setFocusable(false);
		inputPanel.add(txt1);
		inputPanel.add(new JTextArea("�¼���")).setFocusable(false);
		inputPanel.add(txt2);
		inputPanel.add(new JTextArea("�󿵰���뿩��")).setFocusable(false);
		inputPanel.add(txt3);
		inputPanel.add(btnInputTheater);	
		btnInputTheater.addActionListener(new ActionListnerInputTheater());
	}
	
	class ActionListnerInputTheater implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e) {
			setBlockTableBox();
        }	
	}
	
	void makeInputTicket(){
		inputPanel.removeAll();
		JButton btnInputTicket = new JButton("Ȯ��");
		inputPanel.add(new JTextArea("Ƽ�Ϲ�ȣ")).setFocusable(false);
		inputPanel.add(txt1);
		inputPanel.add(new JTextArea("��������ȣ")).setFocusable(false);
		inputPanel.add(txt2);
		inputPanel.add(new JTextArea("�󿵰���ȣ")).setFocusable(false);
		inputPanel.add(txt3);
		inputPanel.add(new JTextArea("�¼���ȣ")).setFocusable(false);
		inputPanel.add(txt4);
		inputPanel.add(new JTextArea("���Ź�ȣ")).setFocusable(false);
		inputPanel.add(txt5);
		inputPanel.add(new JTextArea("�߱ǿ���")).setFocusable(false);
		inputPanel.add(txt6);
		inputPanel.add(new JTextArea("ǥ�ذ���")).setFocusable(false);
		inputPanel.add(txt7);
		inputPanel.add(new JTextArea("�ǸŰ�������")).setFocusable(false);
		inputPanel.add(txt8);
		inputPanel.add(btnInputTicket);	
		btnInputTicket.addActionListener(new ActionListnerInputTicket());
	}
	
	class ActionListnerInputTicket implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e) {
			setBlockTableBox();
        }	
	}
	
	void makeInputSeat() {
		inputPanel.removeAll();
		JButton btnInputSeat = new JButton("Ȯ��");
		inputPanel.add(new JTextArea("�¼���ȣ")).setFocusable(false);
		inputPanel.add(txt1);
		inputPanel.add(new JTextArea("�󿵰���ȣ")).setFocusable(false);
		inputPanel.add(txt2);
		inputPanel.add(new JTextArea("�¼���뿩��")).setFocusable(false);
		inputPanel.add(txt3);
		inputPanel.add(btnInputSeat);	
		btnInputSeat.addActionListener(new ActionListnerInputSeat());
	}
	
	class ActionListnerInputSeat implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e) {
			setBlockTableBox();
        }	
	}
	
	void makeInputUser() {
		inputPanel.removeAll();
		JButton btnInputUser = new JButton("Ȯ��");
		inputPanel.add(new JTextArea("ȸ�����̵�")).setFocusable(false);
		inputPanel.add(txt1);
		inputPanel.add(new JTextArea("����")).setFocusable(false);
		inputPanel.add(txt2);
		inputPanel.add(new JTextArea("�޴�����ȣ")).setFocusable(false);
		inputPanel.add(txt3);
		inputPanel.add(new JTextArea("���ڸ����ּ�")).setFocusable(false);
		inputPanel.add(txt4);
		inputPanel.add(btnInputUser);	
		btnInputUser.addActionListener(new ActionListnerInputUser());
	}
	
	class ActionListnerInputUser implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e) {
			setBlockTableBox();
        }	
	}
	
	void makeInputTicketinginfo() {
		inputPanel.removeAll();
		JButton btnInputTicketinginfo = new JButton("Ȯ��");
		inputPanel.add(new JTextArea("���Ź�ȣ")).setFocusable(false);
		inputPanel.add(txt1);
		inputPanel.add(new JTextArea("�������")).setFocusable(false);
		inputPanel.add(txt2);
		inputPanel.add(new JTextArea("��������")).setFocusable(false);
		inputPanel.add(txt3);
		inputPanel.add(new JTextArea("�����ݾ�")).setFocusable(false);
		inputPanel.add(txt4);
		inputPanel.add(new JTextArea("ȸ�����̵�")).setFocusable(false);
		inputPanel.add(txt5);
		inputPanel.add(new JTextArea("��������")).setFocusable(false);
		inputPanel.add(txt6);
		inputPanel.add(btnInputTicketinginfo);	
		btnInputTicketinginfo.addActionListener(new ActionListnerInputTicketinginfo());
	}
	
	class ActionListnerInputTicketinginfo implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e) {
			setBlockTableBox();
        }	
	}
	
	class ActionListenerShow implements ActionListener{

		@Override
        public void actionPerformed(ActionEvent e2) {
			editPanel.setVisible(false);
			inputPanel.setVisible(false);
			resultPanel.setVisible(true);
        }	
		
	}
	
	class ActionListenerInput implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e3) {
			btnInput.setVisible(false);
			btnResetInput.setVisible(true);
			tableBox.setEnabled(false);
			resultPanel.setVisible(false);
			inputPanel.setVisible(true);
			editPanel.setVisible(false);
        }	
	}
	
	class ActionListenerInputReset implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e3) {
			setBlockTableBox();
			resultPanel.setVisible(false);
			editPanel.setVisible(false);
        }	
	}
	
	class ActionListenerDelete implements ActionListener{
		
		@Override
        public void actionPerformed(ActionEvent e4) {
			resultPanel.setVisible(false);
			inputPanel.setVisible(false);
			editPanel.setVisible(true);
        }	
		
	}
	
	class ActionListenerChange implements ActionListener{

		@Override
        public void actionPerformed(ActionEvent e5) {
			resultPanel.setVisible(false);
			inputPanel.setVisible(false);
			editPanel.setVisible(true);
        }	
		
	}
	
	
	
}
