package coffee.gui;

import javax.swing.JTable;

import coffee.constant.TableCellRenderers;

public class CoffeeTable extends JTable {

	private static final long serialVersionUID = 8925085347768010393L;

	public CoffeeTable() {
		super();
		setDefaultRenderer(Object.class, TableCellRenderers.LEFT_CELL_RENDERER);
		getTableHeader().setDefaultRenderer(TableCellRenderers.HEADER_RENDERER);
		setRowHeight(32);
	}
}
