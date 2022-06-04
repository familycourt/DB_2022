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
	static DBConnection con = new DBConnection();
	public static void main(String[] args) {
		 con.connect();
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
		JPanel panel = new JPanel();
		JButton btnInit = new JButton("초기화");
		JButton btnShow = new JButton("전체 테이블 보기");
		JButton btnDelete = new JButton("삭제");
		JButton btnChange = new JButton("변경");
		JButton btnEnter = new JButton("확인");
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
	
	class ActionListenerInit implements ActionListener{

		@Override
        public void actionPerformed(ActionEvent e1) {
			
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
