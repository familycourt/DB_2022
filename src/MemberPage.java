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
		super("��� ȭ��"); //Ÿ��Ʋ
        JPanel panel = new JPanel();
        JTextField txtSearchMovie = new  JTextField(" ��ȭ �̸��� �Է����ּ��� ");
        JTextField txtSearchDirector = new  JTextField(" ���� �̸��� �Է����ּ��� ");
        JTextField txtSearchActor = new  JTextField(" ��� �̸��� �Է����ּ��� ");
        JTextField txtSearchJenre = new  JTextField("     �帣�� �Է����ּ���     ");
        JButton btnSearchMovie = new JButton("��ȸ");
        JButton btnSearchDirector = new JButton("��ȸ");
        JButton btnSearchActor = new JButton("��ȸ");
        JButton btnSearchJenre = new JButton("��ȸ");
        
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
