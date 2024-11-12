package coffee.gui;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import coffee.constant.Colors;
import coffee.constant.Fonts;

public class CoffeeComboBox extends JComboBox<String> {

	private static final long serialVersionUID = 3556935743732483145L;

	public CoffeeComboBox() {
		super();

		setUI(new CoffeeComboBoxUI());
		setBorder(BorderFactory.createLineBorder(Colors.GRAY_50));
		setFont(Fonts.TEXT);
	}

	public CoffeeComboBox(String[] items) {
		super(items);

		setUI(new CoffeeComboBoxUI());
		setBorder(BorderFactory.createLineBorder(Colors.GRAY_50));
		setFont(Fonts.TEXT);
		setBackground(Colors.BACKGROUND);
	}
}
