package coffee.gui;

import javax.swing.JLabel;

import coffee.constant.Fonts;

public class CoffeeLabel extends JLabel {

	private static final long serialVersionUID = -3001991112016835702L;

	public CoffeeLabel(String text) {
		super(text);
		setFont(Fonts.TEXT);
	}

	public CoffeeLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		setFont(Fonts.TEXT);
	}

	public CoffeeLabel(String text, int horizontalAlignment, int alignmentX) {
		super(text, horizontalAlignment);
		setFont(Fonts.TEXT);
		setAlignmentX(alignmentX);
	}

}
