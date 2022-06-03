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
