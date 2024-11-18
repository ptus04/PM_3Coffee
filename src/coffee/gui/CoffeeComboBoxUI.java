package coffee.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.shared.utility.ColorUtilities;

public class CoffeeComboBoxUI extends BasicComboBoxUI {

	private String iconPath = "/image/chevron-down.png";
	private BufferedImage image;
	private ImageIcon icon;

	@Override
	protected JButton createArrowButton() {
		if (icon == null) {
			try {
				image = ImageIO.read(getClass().getResource(iconPath));
				int height = this.getDisplaySize().height;
				int width = this.getDisplaySize().height;
				icon = new ImageIcon(image.getScaledInstance(height, width, Image.SCALE_SMOOTH));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		JButton btnArrow = new JButton(icon);
		btnArrow.setBorder(null);
		btnArrow.setContentAreaFilled(false);

		return btnArrow;
	}

	@Override
	protected ComboPopup createPopup() {
		return new BasicComboPopup(comboBox) {

			private static final long serialVersionUID = -7841014411791229198L;
			private int borderThickness = 1;

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
		};

	}

	@Override
	protected ListCellRenderer<Object> createRenderer() {
		return new DefaultListCellRenderer() {

			private static final long serialVersionUID = 1939870245884511987L;

			@Override
			public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
					boolean isSelected, boolean cellHasFocus) {

				JLabel item = new JLabel(value.toString()) {

					private static final long serialVersionUID = -354828650152341122L;

					{
						setOpaque(true);
						setHorizontalAlignment(LEFT);
						setFont(Fonts.TEXT);
					}

					@Override
					protected void paintComponent(Graphics g) {
						if (g instanceof Graphics2D g2d) {
							g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
							g2d.setColor(getBackground());
							g2d.fillRect(0, 0, getWidth(), getHeight());
							super.paintComponent(g);
						}

					}
				};

				item.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

				Color bg = ColorUtilities.darken(getBackground());
				if (isSelected) {
					item.setBackground(bg);
					item.setForeground(getForeground());
				} else if (cellHasFocus) {
					item.setBackground(Colors.ACCENT);
					item.setForeground(getForeground());
				} else {
					item.setBackground(getBackground());
					item.setForeground(getForeground());
				}

				return item;
			}
		};
	}

}
