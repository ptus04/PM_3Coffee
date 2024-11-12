package coffee.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class DatePicker extends JDialog {

	private static final long serialVersionUID = -2965832331883716717L;

	private int selectedDay = -1;
	private int selectedMonth = -1;
	private int selectedYear = -1;

	private JComboBox<Integer> yearCombo;
	private JComboBox<String> monthCombo;
	private JTable dayTable;
	private JLabel selectedDateLabel;

	private Map<String, String> monthMap = Map.ofEntries(Map.entry("January", "Tháng 1"),
			Map.entry("February", "Tháng 2"), Map.entry("March", "Tháng 3"), Map.entry("April", "Tháng 4"),
			Map.entry("May", "Tháng 5"), Map.entry("June", "Tháng 6"), Map.entry("July", "Tháng 7"),
			Map.entry("August", "Tháng 8"), Map.entry("September", "Tháng 9"), Map.entry("October", "Tháng 10"),
			Map.entry("November", "Tháng 11"), Map.entry("December", "Tháng 12"));

	public String getSelectedDate() {
		return selectedDateLabel.getText();
	}

	public DatePicker(JFrame parent) {
		super(parent, "Chọn ngày", true);
		setLayout(new BorderLayout());

		selectedDateLabel = new JLabel("Chưa có ngày được chọn");

		yearCombo = new JComboBox<>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = currentYear - 50; i <= currentYear + 50; i++) {
			yearCombo.addItem(i);
		}

		monthCombo = new JComboBox<>(monthMap.values().toArray(String[]::new));

		dayTable = new JTable();
		dayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dayTable.setRowSelectionAllowed(false);
		dayTable.setRowHeight(32);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		dayTable.setDefaultRenderer(Object.class, centerRenderer);
		dayTable.setDefaultEditor(Object.class, null);
		updateCalendarTable();

		yearCombo.setSelectedItem(currentYear);
		monthCombo.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));

		yearCombo.addActionListener(e -> updateCalendarTable());
		monthCombo.addActionListener(e -> updateCalendarTable());

		dayTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = dayTable.getSelectedRow();
				int col = dayTable.getSelectedColumn();
				Object value = dayTable.getValueAt(row, col);

				if (value != null) {
					selectedDay = (int) value;
					selectedMonth = monthCombo.getSelectedIndex();
					selectedYear = (int) yearCombo.getSelectedItem();

					selectedDateLabel
							.setText("Ngày đã chọn: " + selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
				}
			}
		});

		JPanel controlPanel = new JPanel();
		controlPanel.add(new JLabel("Year:"));
		controlPanel.add(yearCombo);
		controlPanel.add(Box.createHorizontalStrut(16));
		controlPanel.add(new JLabel("Month:"));
		controlPanel.add(monthCombo);

		add(controlPanel, BorderLayout.NORTH);
		add(new JScrollPane(dayTable), BorderLayout.CENTER);
		add(selectedDateLabel, BorderLayout.SOUTH);

		setSize(300, 300);
		setLocationRelativeTo(parent);
	}

	private void updateCalendarTable() {
		int year = (int) yearCombo.getSelectedItem();
		int month = monthCombo.getSelectedIndex();

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnCount(7);

		String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		model.setColumnIdentifiers(headers);

		Calendar calendar = new GregorianCalendar(year, month, 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int startDay = calendar.get(Calendar.DAY_OF_WEEK);

		int row = 0;
		model.addRow(new Object[7]);
		for (int i = 1; i < startDay; i++) {
			model.setValueAt(null, row, i - 1);
		}

		for (int day = 1; day <= daysInMonth; day++) {
			int column = (startDay - 1 + day - 1) % 7;
			if (column == 0 && day > 1) {
				row++;
				model.addRow(new Object[7]);
			}
			model.setValueAt(day, row, column);
		}

		dayTable.setModel(model);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton button = new JButton("Pick a Date");
		JLabel dateLabel = new JLabel("Selected Date:");

		button.addActionListener(e -> {
			DatePicker datePicker = new DatePicker(frame);
			datePicker.setVisible(true);
			dateLabel.setText(datePicker.getSelectedDate());
		});

		frame.setLayout(new FlowLayout());
		frame.add(button);
		frame.add(dateLabel);
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
