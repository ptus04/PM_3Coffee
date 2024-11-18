package coffee.gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import coffee.constant.Colors;

public class CoffeeScrollPane extends JScrollPane {

	private static final long serialVersionUID = -1745545693733226941L;

	public CoffeeScrollPane(Component view) {
		super(view);

		setBorder(null);
		setBackground(Colors.BACKGROUND);

		getVerticalScrollBar().setUnitIncrement(16);
		getVerticalScrollBar().setBackground(Colors.GRAY_10);
		getVerticalScrollBar().setUI(createScrollBarUI());

		getHorizontalScrollBar().setUnitIncrement(16);
		getHorizontalScrollBar().setBackground(Colors.GRAY_10);
		getHorizontalScrollBar().setUI(createScrollBarUI());
	}

	private ScrollBarUI createScrollBarUI() {
		return new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Colors.GRAY_20;
				this.thumbDarkShadowColor = Colors.GRAY_20;
				this.thumbHighlightColor = Colors.GRAY_20;
				this.thumbLightShadowColor = Colors.GRAY_20;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = new BasicArrowButton(orientation, thumbColor, thumbLightShadowColor,
						thumbDarkShadowColor, thumbHighlightColor);
				button.setBackground(Colors.WHITE);
				return button;
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = new BasicArrowButton(orientation, thumbColor, thumbLightShadowColor,
						thumbDarkShadowColor, thumbHighlightColor);
				button.setBackground(Colors.WHITE);
				return button;
			}
		};
	}

}
