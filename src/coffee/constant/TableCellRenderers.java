package coffee.constant;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderers {

	public static final DefaultTableCellRenderer LEFT_CELL_RENDERER = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = 5279019910792958666L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			label.setBorder(new EmptyBorder(4, 8, 4, 8));
			label.setBackground(isSelected ? Colors.ACCENT : Colors.BACKGROUND);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			setFont(Fonts.TEXT);
			return label;
		}
	};

	public static final DefaultTableCellRenderer RIGHT_CELL_RENDERER = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = 5279019910792958666L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			label.setBorder(new EmptyBorder(4, 8, 4, 8));
			label.setBackground(isSelected ? Colors.ACCENT : Colors.BACKGROUND);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			setFont(Fonts.TEXT);
			return label;
		}
	};

	public static final DefaultTableCellRenderer CENTER_CELL_RENDERER = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = 5279019910792958666L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			label.setBorder(new EmptyBorder(4, 8, 4, 8));
			label.setBackground(isSelected ? Colors.ACCENT : Colors.BACKGROUND);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			setFont(Fonts.TEXT);
			return label;
		}
	};

	public static final DefaultTableCellRenderer HEADER_RENDERER = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = 8318409247037044850L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			label.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Colors.GRAY_60));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBackground(Colors.BACKGROUND);
			label.setFont(Fonts.TEXT_B);
			return label;
		}
	};
}
