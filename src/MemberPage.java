import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MemberPage extends JFrame{
	MemberPage(){
		super("��� ȭ��"); //Ÿ��Ʋ
        JPanel panel = new JPanel();

        add(panel);

        setSize(840, 630); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
}