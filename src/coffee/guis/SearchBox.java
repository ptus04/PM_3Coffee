package coffee.guis;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBox extends JPanel {

	private static final long serialVersionUID = 277920614726816838L;
	private JTextField searchTf;
	private JButton searchBtn;

	public SearchBox() {
		setBorder(BorderFactory.createLineBorder(Color.RED));

		render();
	}

	private void render() {
		add(searchTf = new JTextField(20));
		add(searchBtn = new JButton("TIM"));
	}
}
