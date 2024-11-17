package coffee.constant;

import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderers {

	public static final DefaultTableCellRenderer CENTER_RENDERER = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = -2420423184205131334L;

		{
			setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		}
	};

	public static final DefaultTableCellRenderer RIGHT_RENDERER = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = 3998842424661108366L;

		{
			setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		}
	};
}
