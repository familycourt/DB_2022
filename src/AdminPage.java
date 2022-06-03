import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AdminPage extends JFrame{
	AdminPage(){
		super("관리자 화면"); //타이틀
		JPanel panel = new JPanel();
		
        add(panel);

        setSize(840, 630); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
}