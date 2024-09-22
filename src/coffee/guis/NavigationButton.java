package coffee.guis;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import coffee.Application;

public class NavigationButton extends JButton {

	private static final long serialVersionUID = 501263642401441361L;

	private final ImageIcon originalIcon;
	private final ImageIcon scaledIcon;

	private boolean isSelected = false;

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public NavigationButton(String iconPath, int iconSize, String text, Color foregroundColor) {
		originalIcon = new ImageIcon(getClass().getResource(iconPath));
		scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

		setIcon(scaledIcon);
		setText(text);
		setForeground(foregroundColor);
		setBackground(Application.COLOR_TRANSPARENT);
		setOpaque(false); // FIX TRANSPARENT GLITCH

		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
}
