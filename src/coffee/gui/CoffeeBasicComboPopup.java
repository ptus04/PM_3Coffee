package coffee.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboPopup;

import coffee.constant.Colors;

public class CoffeeBasicComboPopup extends BasicComboPopup {

	private static final long serialVersionUID = -7841014411791229198L;

	private int borderThickness = 1;

	public CoffeeBasicComboPopup(JComboBox<Object> combo) {
		super(combo);
	}

	@Override
	protected void paintBorder(Graphics g) {
		if (g instanceof Graphics2D g2d) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Colors.GRAY_50);

			int size = borderThickness + borderThickness;
			Shape outer = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
			Shape inner = new Rectangle2D.Float(borderThickness, borderThickness, getWidth() - size,
					getHeight() - size);

			Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
			path.append(outer, false);
			path.append(inner, false);
			g2d.fill(path);
		}
	}

}
