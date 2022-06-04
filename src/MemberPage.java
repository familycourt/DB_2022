import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        
        panel.add(txtSearchMovie);
        panel.add(btnSearchMovie);
        panel.add(txtSearchDirector);
        panel.add(btnSearchDirector);
        panel.add(txtSearchActor);
        panel.add(btnSearchActor);
        panel.add(txtSearchJenre);
        panel.add(btnSearchJenre);
        
        
        
        
        
        
        add(panel);

        setSize(300, 630); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	

}
