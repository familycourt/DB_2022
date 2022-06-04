import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MemberPage extends JFrame{
	MemberPage(){
		super("멤버 화면"); //타이틀
        JPanel panel = new JPanel();
        JTextField txtSearchMovie = new  JTextField(" 영화 이름을 입력해주세요 ");
        JTextField txtSearchDirector = new  JTextField(" 감독 이름을 입력해주세요 ");
        JTextField txtSearchActor = new  JTextField(" 배우 이름을 입력해주세요 ");
        JTextField txtSearchJenre = new  JTextField("     장르를 입력해주세요     ");
        JButton btnSearchMovie = new JButton("조회");
        JButton btnSearchDirector = new JButton("조회");
        JButton btnSearchActor = new JButton("조회");
        JButton btnSearchJenre = new JButton("조회");
        JButton btnMyTicketing = new JButton("               예매 확인               ");
        
        panel.add(new JLabel("               영화 검색               "));
        panel.add(txtSearchMovie);
        panel.add(btnSearchMovie);
        panel.add(txtSearchDirector);
        panel.add(btnSearchDirector);
        panel.add(txtSearchActor);
        panel.add(btnSearchActor);
        panel.add(txtSearchJenre);
        panel.add(btnSearchJenre);
        panel.add(btnMyTicketing);
        
        btnSearchMovie.addActionListener(new ActionListnerSearchMovie());
        btnSearchDirector.addActionListener(new ActionListnerSearchDirector());
        btnSearchActor.addActionListener(new ActionListnerSearchActor());
        btnSearchJenre.addActionListener(new ActionListnerSearchJenre());
        btnMyTicketing.addActionListener(new ActionListnerMyTicketing());
        
        add(panel);

        setSize(300, 300); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	class subFrame extends JFrame	{
		public subFrame(){
			setVisible(true);
	        setSize(1200, 800);
	        setLocationRelativeTo(null);
	       
	        
		}
	}
	
	
	class ActionListnerSearchMovie implements ActionListener{
		JPanel subPanel = new JPanel();
		JTextField[] movieList = new JTextField[30];
		int i;
		
		@Override
	    public void actionPerformed(ActionEvent e) {
			new subFrame().add(subPanel).setVisible(true);
			for(i=0;i<10;i++) {
				movieList[i] = new JTextField();
				JTextField movieContent = new JTextField();
				movieList[i] = movieContent;
				
				subPanel.add(movieList[i]);
			}
	    }	
	}
	
	class ActionListnerSearchDirector implements ActionListener{
			
			@Override
		    public void actionPerformed(ActionEvent e1) {
				new subFrame();
		    }	
		}
	
	class ActionListnerSearchActor implements ActionListener{
		
		@Override
	    public void actionPerformed(ActionEvent e2) {
			new subFrame();
	    }	
	}
	
	class ActionListnerSearchJenre implements ActionListener{
		
		@Override
	    public void actionPerformed(ActionEvent e3) {
			new subFrame();
		
	    }	
	}
	
	class ActionListnerMyTicketing implements ActionListener{
		JPanel subPanel = new JPanel();
		JTextField txtTicketNumber = new JTextField(5);
		JButton btnShowAll = new JButton("예매 상세");
		JButton btnDelete = new JButton("동일 영화 정보");
		JButton btnChangeMovie = new JButton("예매 영화 변경");
		JButton btnChangeTime = new JButton("상영 일자 변경");
		JTextField movieList = new JTextField(100);
		@Override
	    public void actionPerformed(ActionEvent e) {
			new subFrame().add(subPanel).setVisible(true);
			
			subPanel.add(new JLabel("티켓번호"));
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





