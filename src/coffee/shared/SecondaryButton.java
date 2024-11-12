package coffee.shared;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import coffee.constant.Colors;
import coffee.shared.utility.ColorUtilities;

public class SecondaryButton extends RoundedButton {

	private static final long serialVersionUID = -6386152828233821838L;

	private int borderThickness;

	public int getBorderThickness() {
		return borderThickness;
	}

	public void setBorderThickness(int borderThickness) {
		this.borderThickness = borderThickness;
	}

	public void setIconSize(int width, int height) {
		this.iconWidth = width;
		this.iconHeight = height;

		BufferedImage bufferedIcon = ColorUtilities.convertImageIconToBufferedImage(icon);
		BufferedImage changedColorBufferedIcon = ColorUtilities.changeColorToColor(bufferedIcon, Color.WHITE,
				getForeground());

		Image scaledIcon = changedColorBufferedIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaledIcon));
	}

	public SecondaryButton(String label) {
		super(label, Colors.PRIMARY);

		this.borderThickness = 2;
		this.setForeground(Colors.PRIMARY);
	}

	public SecondaryButton(String label, String iconPath) {
		super(label, iconPath, Colors.PRIMARY);

		this.borderThickness = 2;
		this.setForeground(Colors.PRIMARY);

		BufferedImage bufferedIcon = ColorUtilities.convertImageIconToBufferedImage(icon);
		BufferedImage changedColorBufferedIcon = ColorUtilities.changeColorToColor(bufferedIcon, Color.WHITE,
				getForeground());

		setIcon(new ImageIcon(changedColorBufferedIcon));
		setIconTextGap(8);

	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(getBackground());

		Shape outer = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
		Shape inner = new RoundRectangle2D.Float(borderThickness, borderThickness, getWidth() - borderThickness * 2,
				getHeight() - borderThickness * 2, borderRadius / 2, borderRadius / 2);

		Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		path.append(outer, false);
		path.append(inner, false);
		g2d.fill(path);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setBackground(pressedColor);
		setForeground(pressedColor);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setBackground(normalColor);
		setForeground(normalColor);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBackground(hoveredColor);
		setForeground(hoveredColor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(normalColor);
		setForeground(normalColor);
	}

}
