package coffee.guis;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import coffee.Application;

public class PrimaryButton extends JButton {

	private static final long serialVersionUID = -3508422995847442449L;

	public PrimaryButton(String text) {
		setText(text);
		setBackground(Application.COLOR_PRIMARY);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)));
	}
}
