package coffee.gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;

public class CoffeeComboBoxUI extends BasicComboBoxUI {

	private String iconPath = "/image/chevron-down.png";
	private ImageIcon icon;
	private Image scaledImage;
	private ImageIcon scaledIcon;

	@Override
	protected JButton createArrowButton() {
		if (icon == null || scaledImage == null || scaledIcon == null) {
			icon = new ImageIcon(getClass().getResource(iconPath));
			scaledImage = icon.getImage().getScaledInstance(this.getDisplaySize().height, this.getDisplaySize().height,
					Image.SCALE_SMOOTH);
			scaledIcon = new ImageIcon(scaledImage);
		}

		JButton arrowButton = new JButton(scaledIcon);
		arrowButton.setBorder(null);
		arrowButton.setContentAreaFilled(false);

		return arrowButton;
	}

	@Override
	protected ComboPopup createPopup() {
		return new CoffeeBasicComboPopup(comboBox);
	}

	@Override
	protected ListCellRenderer<Object> createRenderer() {
		return new CoffeeComboxBoxRenderer();
	}

}
