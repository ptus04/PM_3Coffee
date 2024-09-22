package coffee;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Application {

	public static final Color COLOR_PRIMARY = new Color(139, 69, 19);
	public static final Color COLOR_DANGER = new Color(238, 17, 17);
	public static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
	public static final Border BORDER_INPUT = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
			BorderFactory.createEmptyBorder(2, 4, 2, 4));

	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new MainPage().setVisible(true);
//		new LoginPage().setVisible(true);
	}

}
