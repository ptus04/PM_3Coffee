package coffee.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.shared.utility.ColorUtilities;

public class NavigationButton extends JButton {

	private static final long serialVersionUID = 501263642401441361L;

	private String name;
	private ImageIcon icon;
	private ImageIcon pressedIcon;
	private ImageIcon scaledIcon;
	private ImageIcon scaledPressedIcon;
	private ImageIcon coloredIcon;
	private ImageIcon coloredPressedIcon;
	private boolean isSelected;

	public String getName() {
		return name;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		if (coloredIcon != null) {
			setIcon(isSelected ? coloredPressedIcon : coloredIcon);
		} else {
			setIcon(isSelected ? scaledPressedIcon : scaledIcon);
		}
		repaint();
	}

	public NavigationButton(String name, String iconPath) {
		this.name = name;
		this.icon = new ImageIcon(getClass().getResource(iconPath));
		this.pressedIcon = new ImageIcon(getClass().getResource(iconPath.replace(".png", "-filled.png")));
		this.scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		this.scaledPressedIcon = new ImageIcon(pressedIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));

		setIcon(scaledIcon);
		setRolloverIcon(scaledPressedIcon);
		setPressedIcon(scaledPressedIcon);

		setText(name);
		setFont(Fonts.NAVIGATION);
		setForeground(Colors.PRIMARY);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);

		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public NavigationButton(String name, String iconPath, Color color) {
		this.name = name;
		this.icon = new ImageIcon(getClass().getResource(iconPath));
		this.pressedIcon = new ImageIcon(getClass().getResource(iconPath.replace(".png", "-filled.png")));
		this.scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		this.scaledPressedIcon = new ImageIcon(pressedIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		this.coloredIcon = new ImageIcon(ColorUtilities
				.changeColorToColor(ColorUtilities.convertImageIconToBufferedImage(scaledIcon), Colors.PRIMARY, color));
		this.coloredPressedIcon = new ImageIcon(ColorUtilities.changeColorToColor(
				ColorUtilities.convertImageIconToBufferedImage(scaledPressedIcon), Colors.PRIMARY, color));

		setIcon(coloredIcon);
		setRolloverIcon(coloredPressedIcon);
		setPressedIcon(coloredPressedIcon);

		setText(name);
		setFont(Fonts.NAVIGATION);
		setForeground(color);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);

		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public Insets getInsets() {
		return new Insets(8, 8, 8, 8);
	}

}
