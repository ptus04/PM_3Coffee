package coffee.gui;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import coffee.constant.Colors;
import coffee.constant.Fonts;

public class CoffeeComboBox<T> extends JComboBox<T> {

	private static final long serialVersionUID = 3556935743732483145L;

	public CoffeeComboBox() {
		this(new DefaultComboBoxModel<>());
	}

	public CoffeeComboBox(T[] items) {
		this(new DefaultComboBoxModel<>(items));
	}

	public CoffeeComboBox(DefaultComboBoxModel<T> model) {
		super(model);

		setUI(new CoffeeComboBoxUI());
		setBorder(BorderFactory.createLineBorder(Colors.GRAY_50));
		setFont(Fonts.TEXT);
		setBackground(Colors.BACKGROUND);
	}

}
