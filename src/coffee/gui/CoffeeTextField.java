package coffee.gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

import coffee.constant.Colors;
import coffee.constant.Fonts;

public class CoffeeTextField extends JTextField {

	private static final long serialVersionUID = 7722205402595246992L;

	private int borderRadius = 8;
	private int borderThickness = 1;
	private Insets insets = new Insets(7, 14, 7, 14);

	public int getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(int borderRadius) {
		this.borderRadius = borderRadius;
	}

	public int getBorderThickness() {
		return borderThickness;
	}

	public void setBorderThickness(int borderThickness) {
		this.borderThickness = borderThickness;
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}

	public CoffeeTextField() {
		super();
		initialize();
	}

	public CoffeeTextField(int columns) {
		super(columns);
		initialize();
	}

	public CoffeeTextField(String text) {
		super(text);
		initialize();
	}

	private void initialize() {
		setFont(Fonts.TEXT);
		setCursor(new Cursor(Cursor.TEXT_CURSOR));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Colors.WHITE);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Colors.GRAY_50);

		int size = borderThickness + borderThickness;
		Shape outer = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
		Shape inner = new RoundRectangle2D.Float(borderThickness, borderThickness, getWidth() - size,
				getHeight() - size, borderRadius, borderRadius);

		Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		path.append(outer, false);
		path.append(inner, false);
		g2d.fill(path);
	}

	@Override
	public Insets getInsets() {
		return insets;
	}

	@Override
	public boolean isOpaque() {
		return false;
	}

}
