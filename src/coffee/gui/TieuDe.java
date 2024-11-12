package coffee.gui;

import java.awt.Font;

import javax.swing.JLabel;

import coffee.constant.Colors;
import coffee.constant.Fonts;

public class TieuDe extends JLabel {

	private static final long serialVersionUID = -4642950248528301177L;

	public TieuDe(String value) {
		this(value, Fonts.HEADING_5);
	}

	public TieuDe(String value, Font font) {
		setText(value);
		setFont(font);
		setForeground(Colors.PRIMARY);
	}

}
