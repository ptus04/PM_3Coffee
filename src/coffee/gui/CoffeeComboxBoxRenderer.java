package coffee.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import coffee.constant.Fonts;
import coffee.shared.utility.ColorUtilities;

public class CoffeeComboxBoxRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1939870245884511987L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		JLabel item = new JLabel((String) value) {

			private static final long serialVersionUID = 1L;

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

		item.setBorder(BorderFactory.createEmptyBorder(7, 14, 7, 14));

		Color bg = ColorUtilities.darken(getBackground());
		if (isSelected) {
			item.setBackground(bg);
			item.setForeground(getForeground());
		} else {
			item.setBackground(getBackground());
			item.setForeground(getForeground());
		}

		return item;
	}

}
