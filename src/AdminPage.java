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
   
   String[] tables = {null ,"영화","상영일정","상영관", "티켓", "좌석", "회원고객", "예매정보"};
   JTextArea txtArea = new JTextArea("안녕");
   JPanel resultPanel = new JPanel();
   JPanel editPanel = new JPanel();
   JPanel inputPanel = new JPanel();
   JComboBox tableBox = new JComboBox(tables);
   JButton btnInput = new JButton("입력");
   JButton btnResetInput = new JButton("다시입력");
   
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
      super("관리자 화면"); //타이틀
      
      connect();
      
      JPanel panel = new JPanel();
      JButton btnInit = new JButton("초기화");
      JButton btnShow = new JButton("전체 테이블 보기");
      JButton btnDelete = new JButton("삭제");
      JButton btnChange = new JButton("변경");
      JButton btnEnter = new JButton("확인");
      JScrollPane resultPane = new JScrollPane(txtArea);
      JTextField txtEdit = new JTextField(20);
      
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
      resultPanel.add(resultPane);
      
      add(panel, BorderLayout.NORTH);
      add(resultPanel, BorderLayout.CENTER);
      add(editPanel, BorderLayout.CENTER);
      add(inputPanel, BorderLayout.CENTER);
      resultPanel.setVisible(false);
      editPanel.setVisible(false);
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
            System.out.println("드라이브 로드 성공");
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }

         try {
            System.out.println("드라이브 연결 준비...");
            connection = DriverManager.getConnection(url, userid, pwd);
            System.out.println("드라이브 연결 성공");
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

   
   class ActionListenerInit implements ActionListener{

      @Override
        public void actionPerformed(ActionEvent e1) {
         InitDB();
        }   
      
   }
   
   public void InitDB() {
         
         try {
            stmt = connection.createStatement();
            
            // madang 초기화
            System.out.println("madang 초기화");
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
                  
            System.out.println("Table `madang`.`movie` CREATE 완료");
            

            // Table `madang`.`theaters`
                     
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`theaters` (\r\n" + 
                  "  `theater_number` INT(11) NOT NULL,\r\n" + 
                  "  `seat_count` INT(11) NULL DEFAULT NULL,\r\n" + 
                  "  `theater_use_status` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
                  "  PRIMARY KEY (`theater_number`))");   
                  
            
            System.out.println("Table `madang`.`theaters` CREATE 완료");
            
            
            // Table `madang`.`screening_schedule`
           
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`screening_schedule` (\r\n" + 
                  "  `screening_schedule_number` INT(11) NOT NULL,\r\n" + 
                  "  `movie_number` INT(11) NOT NULL,\r\n" + 
                  "  `theater_number` INT(11) NOT NULL,\r\n" + 
                  "  `screening_start_date` DATE NULL DEFAULT NULL,\r\n" + 
                  "  `screening_day` VARCHAR(10) NULL DEFAULT NULL,\r\n" + 
                  "  `screening_count` INT(11) NULL DEFAULT NULL,\r\n" + 
                  "  `screening_start_time` TIME NULL DEFAULT NULL,\r\n" + //테이블 안에 들어가는 외래키 모두 주키?
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
            
            System.out.println("Table `madang`.`screening_schedule` CREATE 완료");
            
            // Table `madang`.'seat`
            // 주키 설정 팔
                     
            //여기
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`seat` (\r\n" + 
                  "  `seat_number` INT(11) NOT NULL,\r\n" + // null?
                  "  `theater_number` INT(11) NOT NULL,\r\n" + 
                  "  `seat_use_status` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
                 // "  CONSTRAINT pk_seat`\r\n" + 
                  "  PRIMARY KEY (`seat_number`))" );
//                  "  CONSTRAINT `fk_theaters_theater_number`\r\n" + 
//                  "    FOREIGN KEY (`theater_number`)\r\n" + 
//                  "    REFERENCES `madang`.`theaters` (`theater_number`)\r\n" + 
//                  "    ON DELETE NO ACTION\r\n" + 
//                  "    ON UPDATE NO ACTION)"); 
            
            
            System.out.println("Table `madang`.`seat` CREATE 완료");
            
            // Table `madang`.`ticket`

            // 여기
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`ticket` (\r\n" + 
                  "  `tichet_number` INT(11) NOT NULL,\r\n" + 
                  "  `screenig_schedule_number` INT(11) NOT NULL,\r\n" + 
                  "  `theater_number` INT(11) NOT NULL,\r\n" + 
                  "  `seat_number` INT(11) NULL DEFAULT NULL,\r\n" + // 키 설정 필요    
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
                  
            
            
            System.out.println("Table `madang`.`ticket` CREATE 완료");
            
           
            
            // Table `madang`.`user`

                     
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `madang`.`user` (\r\n" + 
                  "  `user_id` VARCHAR(45) NOT NULL,\r\n" + 
                  "  `user_name` VARCHAR(45)  NULL DEFAULT NULL,\r\n" + 
                  "  `user_phone_number` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
                  "  `user_email_address` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
                  "  PRIMARY KEY (`user_id`))");   
            System.out.println("Table `madang`.`user` CREATE 완료");
            
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
            
            System.out.println("Table `madang`.`booking_info` CREATE 완료");
            
            // INSERT INTO movie
            System.out.println("INSERT INTO movie");   // 개봉일정 수정필요  
            stmt.executeUpdate("INSERT INTO movie VALUES(1, '범죄도시2', '106', 19, '이상용', '손석구', '범죄', '손석구가다했다', '2021-01-02');");
            stmt.executeUpdate("INSERT INTO movie VALUES(2, '닥터 스트레인지', '126', 15,  '샘 레이미', '베네딕트 컴버배치', '마블', '마블 영화이다', '2021-02-03');");
            stmt.executeUpdate("INSERT INTO movie VALUES(3, '그대가 조국', '124', 15, '이승준', '감병석', '다큐멘터리', '다큐멘터리 영화이다', '2021-03-01');");
            stmt.executeUpdate("INSERT INTO movie VALUES(4, '피는 물보다 진하다', '90', 12, '김희성', '조동혁', '액션', '액션영화이다', '2021-04-01');");
            stmt.executeUpdate("INSERT INTO movie VALUES(5, '안녕하세요', '118', 12, '차봉주', '김환희', '드라마', '드라마 영화이다', '2021-05-01');");
            stmt.executeUpdate("INSERT INTO movie VALUES(6, '브로커', '129', 15, '고레에다 히로카즈', '송강호', '드라마', '드라마 영화이다', '2021-06-01');");      
            stmt.executeUpdate("INSERT INTO movie VALUES(7, '아치의 노래', '113', 15, '고영재', '정태춘', '다큐멘터리', '다큐멘터리 영화이다', '2021-07-01');");
            stmt.executeUpdate("INSERT INTO movie VALUES(8, '오마주', '108', 18,  '신수원', '이정은', '드라마', '드라마 영화이다', '2021-08-01');");
            stmt.executeUpdate("INSERT INTO movie VALUES(9, '쥬라기 월드: 도미니언', '147', 12, '콜린 트레보로우', '크리스 프랫', '액션', '액션영화이다', '2021-09-01');");
            stmt.executeUpdate("INSERT INTO movie VALUES(10, '베르네 부인의 장미정원', '95', 12, '피에르 피노드', '카트린 프로', '드라마', '드라마 영화이다', '2021-10-01');");
            
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
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(1, 1, 1, STR_TO_DATE('2022-05-01','%Y-%m-%d'), '일요일', 1, TIME_FORMAT('08:00', '%H:%i'));");         
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(2, 2, 2, STR_TO_DATE('2022-05-02','%Y-%m-%d'), '월요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(3, 3, 3, STR_TO_DATE('2022-05-03','%Y-%m-%d'), '화요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(4, 4, 4, STR_TO_DATE('2022-05-04','%Y-%m-%d'), '수요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(5, 5, 5, STR_TO_DATE('2022-05-05','%Y-%m-%d'), '목요일', 1, TIME_FORMAT('08:00', '%H:%i'));");
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(6, 6, 6, STR_TO_DATE('2022-05-06','%Y-%m-%d'), '금요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(7, 7, 7, STR_TO_DATE('2022-05-07','%Y-%m-%d'), '토요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(8, 8, 8, STR_TO_DATE('2022-05-08','%Y-%m-%d'), '일요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(9, 9, 9, STR_TO_DATE('2022-05-09','%Y-%m-%d'), '월요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      
            stmt.executeUpdate("INSERT INTO screening_schedule VALUES(10, 10, 10, STR_TO_DATE('2022-05-10','%Y-%m-%d'), '화요일', 1, TIME_FORMAT('08:00', '%H:%i'));");      


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
            stmt.executeUpdate("INSERT INTO user VALUES('newid1', '홍길동', '010-0001-0001', 'email1@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid2', '변영화', '010-0002-0002', 'email2@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid3', '영화변', '010-0003-0003', 'email3@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid4', '변무비', '010-0004-0004', 'email4@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid5', '정재경', '010-0005-0005', 'email5@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid6', '재경정', '010-0006-0006', 'email6@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid7', '경정재', '010-0007-0007', 'email7@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid8', '제임스', '010-0008-0008', 'email8@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid9', '화변영', '010-0009-0009', 'email9@naver.com');");
            stmt.executeUpdate("INSERT INTO user VALUES('newid10', '길동홍', '010-0010-0010', 'email10@naver.com');");
            
            // INSERT INTO booking_info
            System.out.println("INSERT INTO booking_info");   
            stmt.executeUpdate("INSERT INTO booking_info VALUES(1, '무통장입금', '정상', '7000', 'newid1', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(2, '무통장입금', '정상', '7000', 'newid2', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(3, '무통장입금', '정상', '7000', 'newid3', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(4, '무통장입금', '정상', '7000', 'newid4', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(5, '무통장입금', '정상', '7000', 'newid5', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(6, '무통장입금', '정상', '7000', 'newid6', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(7, '무통장입금', '정상', '7000', 'newid7', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(8, '무통장입금', '정상', '7000', 'newid8', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(9, '무통장입금', '정상', '7000', 'newid9', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            stmt.executeUpdate("INSERT INTO booking_info VALUES(10, '무통장입금', '정상', '7000', 'newid10', STR_TO_DATE('2022-05-04','%Y-%m-%d'));");
            
            
            System.out.println("Table 초기화 종료");   
         }catch(SQLException e10){
            e10.printStackTrace();
         }
   }
   
   
   public void actionPerformed(ActionEvent e) {
      
      if(e.getSource() == tableBox) {
         switch((String)tableBox.getSelectedItem()) {
         case "영화": 
            makeInputMovie();
            break;
         case "상영일정": 
            makeInputTimetable();
            break;
         case "상영관": 
            makeInputTheater();
            break;
         case "티켓": 
            makeInputTicket();
            break;
         case "좌석": 
            makeInputSeat();
            break;
         case "회원고객": 
            makeInputUser();
            break;
         case "예매정보": 
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
      JButton btnInputMovie = new JButton("확인");
      inputPanel.add(new JTextArea("영화번호")).setFocusable(false);
      inputPanel.add(txt1);
      inputPanel.add(new JTextArea("영화명")).setFocusable(false);
      inputPanel.add(txt2);
      inputPanel.add(new JTextArea("상영시간")).setFocusable(false);
      inputPanel.add(txt3);
      inputPanel.add(new JTextArea("상영등급")).setFocusable(false);
      inputPanel.add(txt4);
      inputPanel.add(new JTextArea("감독명")).setFocusable(false);
      inputPanel.add(txt5);
      inputPanel.add(new JTextArea("배우명")).setFocusable(false);
      inputPanel.add(txt6);
      inputPanel.add(new JTextArea("장르")).setFocusable(false);
      inputPanel.add(txt7);
      inputPanel.add(new JTextArea("영화소개")).setFocusable(false);
      inputPanel.add(txt8);
      inputPanel.add(new JTextArea("개봉일 정보")).setFocusable(false);
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
      JButton btnInputTimetable = new JButton("확인");
      inputPanel.add(new JTextArea("상영일정번호")).setFocusable(false);
      inputPanel.add(txt1);
      inputPanel.add(new JTextArea("영화번호")).setFocusable(false);
      inputPanel.add(txt2);
      inputPanel.add(new JTextArea("상영관 번호")).setFocusable(false);
      inputPanel.add(txt3);
      inputPanel.add(new JTextArea("상영시작일")).setFocusable(false);
      inputPanel.add(txt4);
      inputPanel.add(new JTextArea("상영요일")).setFocusable(false);
      inputPanel.add(txt5);
      inputPanel.add(new JTextArea("상영회차")).setFocusable(false);
      inputPanel.add(txt6);
      inputPanel.add(new JTextArea("상영시작정보")).setFocusable(false);
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
      JButton btnInputTheater = new JButton("확인");
      inputPanel.add(new JTextArea("상영관번호")).setFocusable(false);
      inputPanel.add(txt1);
      inputPanel.add(new JTextArea("좌석수")).setFocusable(false);
      inputPanel.add(txt2);
      inputPanel.add(new JTextArea("상영관사용여부")).setFocusable(false);
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
      JButton btnInputTicket = new JButton("확인");
      inputPanel.add(new JTextArea("티켓번호")).setFocusable(false);
      inputPanel.add(txt1);
      inputPanel.add(new JTextArea("상영일정번호")).setFocusable(false);
      inputPanel.add(txt2);
      inputPanel.add(new JTextArea("상영관번호")).setFocusable(false);
      inputPanel.add(txt3);
      inputPanel.add(new JTextArea("좌석번호")).setFocusable(false);
      inputPanel.add(txt4);
      inputPanel.add(new JTextArea("예매번호")).setFocusable(false);
      inputPanel.add(txt5);
      inputPanel.add(new JTextArea("발권여부")).setFocusable(false);
      inputPanel.add(txt6);
      inputPanel.add(new JTextArea("표준가격")).setFocusable(false);
      inputPanel.add(txt7);
      inputPanel.add(new JTextArea("판매가격정보")).setFocusable(false);
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
      JButton btnInputSeat = new JButton("확인");
      inputPanel.add(new JTextArea("좌석번호")).setFocusable(false);
      inputPanel.add(txt1);
      inputPanel.add(new JTextArea("상영관번호")).setFocusable(false);
      inputPanel.add(txt2);
      inputPanel.add(new JTextArea("좌석사용여부")).setFocusable(false);
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
      JButton btnInputUser = new JButton("확인");
      inputPanel.add(new JTextArea("회원아이디")).setFocusable(false);
      inputPanel.add(txt1);
      inputPanel.add(new JTextArea("고객명")).setFocusable(false);
      inputPanel.add(txt2);
      inputPanel.add(new JTextArea("휴대폰번호")).setFocusable(false);
      inputPanel.add(txt3);
      inputPanel.add(new JTextArea("전자메일주소")).setFocusable(false);
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
      JButton btnInputTicketinginfo = new JButton("확인");
      inputPanel.add(new JTextArea("예매번호")).setFocusable(false);
      inputPanel.add(txt1);
      inputPanel.add(new JTextArea("결제방법")).setFocusable(false);
      inputPanel.add(txt2);
      inputPanel.add(new JTextArea("결제상태")).setFocusable(false);
      inputPanel.add(txt3);
      inputPanel.add(new JTextArea("결제금액")).setFocusable(false);
      inputPanel.add(txt4);
      inputPanel.add(new JTextArea("회원아이디")).setFocusable(false);
      inputPanel.add(txt5);
      inputPanel.add(new JTextArea("결제일자")).setFocusable(false);
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
         
         showDB();
        }   
      
   }
   
   void showDB() {
	   JPanel subPanel = new JPanel();
	   new subFrame().add(subPanel);
	   JTextArea txtOut = new JTextArea();
	   JScrollPane scroll = new JScrollPane(txtOut);
	   subPanel.add(scroll);
	   txtOut.setCaretPosition(txtOut.getDocument().getLength());

	   txtOut.setEditable(false);	
	     
	   // 1. 영화 테이블 출력
	   try {
         String sql = "select * from movie;";
         
         stmt = connection.createStatement();
         rs = stmt.executeQuery(sql);
         txtOut.setText("");
         txtOut.setText("영화번호" + "\t" + "영화명" + "\t" + "상영시간" + "\t" + "상영등급" + "\t" + "감독명" + "\t" + "배우명" + "\t" + "장르"
               + "\t" + "영화소개" + "\t" + "개봉일"  + "\n");
        
         while (rs.next()) {
            String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
                  + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t"
                  + rs.getString(8) + "\t" + rs.getDate(9)+ "\n";
            System.out.println(str);
            txtOut.append(str);
         }
         
      }catch (Exception e2) {
         System.out.println("실패 이유 : ");
         System.out.println(e2.getMessage());
      }
	   
	// 2. 상영일정 테이블 출력
      try {

         txtOut.append("\n" + "상영일정번호" + "\t" + "영화번호" + "\t" + "상영관번호" + "\t" + "상영시작일" + "\t" + "상영요일" + "\t" + "상영회차"
         + "\t" + "상영시작시간"  + "\n");

         String sql = "select * from screening_schedule;";
         stmt = connection.createStatement();
         rs = stmt.executeQuery(sql);
         while (rs.next()) {
            String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getDate(4)
                  + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getTime(7)+ "\n";
            System.out.println(str);
            txtOut.append(str);
         }
	         
	      }catch (Exception e2) {
	         System.out.println("실패 이유 : ");
	         System.out.println(e2.getMessage());
	      }
	   
   	

   
   // 3. 상영관 테이블 출력
	   try {
		   
	      txtOut.append("\n"  + "상영관번호" + "\t" + "좌석수" + "\t" + "상영관사용여부" + "\n");
	      String sql = "select * from theaters;";
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	         String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3)+ "\n";
	         System.out.println(str);
	         txtOut.append(str);
	      }
	      
	   }catch (Exception e2) {
	      System.out.println("실패 이유 : ");
	      System.out.println(e2.getMessage());
	   }
	   
	// 4. 티켓 테이블 출력
	      try {
	         txtOut.append("\n"  + "티켓번호" + "\t" + "상영일정번호" + "\t" + "상영관번호" + "\t" + "좌석번호" + "\t" + "예매번호" + "\t" + "발권여부"
	         + "\t" + "표준가격" + "\t" + "판매가격"  + "\n");

	         String sql = "select * from ticket;";
	         stmt = connection.createStatement();
	         rs = stmt.executeQuery(sql);
	         while (rs.next()) {
	            String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
	            + "\t" + rs.getInt(5) + "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t" + rs.getInt(8) + "\n";
	            System.out.println(str);
	            txtOut.append(str);
	         }
	         
	      }catch (Exception e2) {
	         System.out.println("실패 이유 : ");
	         System.out.println(e2.getMessage());
	      }
	      
	      // 5. 좌석 테이블 출력
	      try {
	    	 txtOut.append("\n" + "좌석번호" + "\t" + "상영관번호" + "\t" + "좌석사용여부" + "\n");

	         String sql = "select * from seat;";
	         stmt = connection.createStatement();
	         rs = stmt.executeQuery(sql);
	         while (rs.next()) {
	            String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3)+ "\n";
	            System.out.println(str);
	            txtOut.append(str);
	         }
	         
	      }catch (Exception e2) {
	         System.out.println("실패 이유 : ");
	         System.out.println(e2.getMessage());
	      }
	      
	      // 6. 회원고객 테이블 출력
	      try {
	         String sql = "select * from user;";
	         
	         stmt = connection.createStatement();
	         rs = stmt.executeQuery(sql);
	         txtOut.append("\n" + "회원아이디" + "\t" + "고객명" + "\t" + "휴대폰번호" + "\t" + "전자메일주소" + "\n");
	         
	         while (rs.next()) {
	            String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
	                  + "\n";
	            System.out.println(str);
	            txtOut.append(str);
	         }
	         
	      }catch (Exception e2) {
	         System.out.println("실패 이유 : ");
	         System.out.println(e2.getMessage());
	      }
	      
	      // 7. 예매정보 테이블 출력
	      try {
	         String sql = "select * from booking_info;";
	         stmt = connection.createStatement();
	         rs = stmt.executeQuery(sql);
	         txtOut.append("예매번호" + "\t" + "결제방법" + "\t" + "결제상태" + "\t" + "결제금액" + "\t" + "회원아이디" + "\t" + "결제일자" + "\n");
  
	         while (rs.next()) {
	            String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
	                  + "\t" + rs.getString(5) + "\t" + rs.getDate(6)
	                  + "\n";
	            System.out.println(str);
	            txtOut.append(str);
	         }
	         
	      }catch (Exception e2) {
	         System.out.println("실패 이유 : ");
	         System.out.println(e2.getMessage());
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