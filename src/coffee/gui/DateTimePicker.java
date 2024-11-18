package coffee.gui;

import javax.swing.*;

import coffee.constant.Colors;
import coffee.shared.PrimaryButton;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimePicker extends JDialog implements WindowListener {

	private static final long serialVersionUID = -2965832331883716717L;
	public static final int DATE_ONLY = 1;
	public static final int TIME_ONLY = 2;
	public static final int DATE_AND_TIME = 3;

	private int selectedDay = -1;
	private int selectedMonth = -1;
	private int selectedYear = -1;
	private int selectedHour = -1;
	private int selectedMinute = -1;
	private int selectedSecond = -1;

	private Box pNorth;

	private CoffeeComboBox<Integer> cbYear;
	private CoffeeComboBox<String> cbMonth;
	private CoffeeComboBox<String> cbDay;
	private CoffeeComboBox<Integer> hourCombo;
	private CoffeeComboBox<Integer> minuteCombo;
	private CoffeeComboBox<Integer> secondCombo;

	private PrimaryButton btnConfirm;

	private int mode;

	public LocalDate getSelectedDate() {
		if (selectedDay == -1 || selectedMonth == -1 || selectedYear == -1)
			return null;
		return LocalDate.of(selectedYear, selectedMonth + 1, selectedDay + 1);
	}

	public LocalTime getSelectedTime() {
		if (selectedHour == -1 || selectedMinute == -1 || selectedSecond == -1)
			return null;
		return LocalTime.of(selectedHour, selectedMinute, selectedSecond);
	}

	public LocalDateTime getSelectedDateTime() {
		LocalDate selectedDate = getSelectedDate();
		LocalTime selectedTime = getSelectedTime();
		if (selectedDate == null || selectedTime == null)
			return null;
		return LocalDateTime.of(selectedDate, selectedTime);
	}

	public DateTimePicker(JFrame parent, int mode) {
		super(parent, "", true);
		this.mode = mode;
		setLayout(new BorderLayout(8, 8));
		getContentPane().setBackground(Colors.BACKGROUND);
		addWindowListener(this);

		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);

		if (mode == DATE_ONLY) {
			setTitle("Chọn ngày");
			pNorth.add(renderDatePanel());
		} else if (mode == TIME_ONLY) {
			setTitle("Chọn thời gian");
			pNorth.add(renderTimePanel());
		} else if (mode == DATE_AND_TIME) {
			setTitle("Chọn ngày và thời gian");
			pNorth.add(renderDatePanel());
			pNorth.add(Box.createVerticalStrut(8));
			pNorth.add(renderTimePanel());
		}

		add(btnConfirm = new PrimaryButton("Xác nhận"), BorderLayout.SOUTH);
		btnConfirm.addActionListener(this::onConfirmClicked);

		pack();
		setLocationRelativeTo(parent);
	}

	private CoffeePanel renderDatePanel() {
		CoffeePanel datePanel = new CoffeePanel(new FlowLayout(FlowLayout.LEADING));
		datePanel.add(new CoffeeLabel("Năm"));
		datePanel.add(cbYear = new CoffeeComboBox<>());
		datePanel.add(Box.createHorizontalStrut(16));
		datePanel.add(new CoffeeLabel("Tháng"));
		datePanel.add(cbMonth = new CoffeeComboBox<>());
		datePanel.add(Box.createHorizontalStrut(16));
		datePanel.add(new CoffeeLabel("Ngày"));
		datePanel.add(cbDay = new CoffeeComboBox<>());

		for (int i = 0; i < 12; i++)
			cbMonth.addItem("Tháng " + String.valueOf(i + 1));
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = currentYear - 35; i <= currentYear + 10; i++)
			cbYear.addItem(i);

		cbYear.addActionListener(this::updateDayComboBox);
		cbMonth.addActionListener(this::updateDayComboBox);

		cbYear.setSelectedItem(currentYear);
		cbMonth.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
		cbDay.setSelectedIndex(Calendar.getInstance().get(Calendar.DATE) - 1);

		return datePanel;
	}

	private CoffeePanel renderTimePanel() {
		CoffeePanel timePanel = new CoffeePanel(new FlowLayout(FlowLayout.LEADING));
		timePanel.add(new JLabel("Giờ"));
		timePanel.add(hourCombo = new CoffeeComboBox<>());
		timePanel.add(Box.createHorizontalStrut(16));
		timePanel.add(new JLabel("Phút"));
		timePanel.add(minuteCombo = new CoffeeComboBox<>());
		timePanel.add(Box.createHorizontalStrut(16));
		timePanel.add(new JLabel("Giây"));
		timePanel.add(secondCombo = new CoffeeComboBox<>());

		for (int i = 0; i < 24; i++)
			hourCombo.addItem(i);
		for (int i = 0; i < 60; i++)
			minuteCombo.addItem(i);
		for (int i = 0; i < 60; i++)
			secondCombo.addItem(i);

		LocalDateTime now = LocalDateTime.now();
		hourCombo.setSelectedIndex(now.getHour());
		minuteCombo.setSelectedIndex(now.getMinute());
		secondCombo.setSelectedIndex(now.getSecond());

		return timePanel;
	}

	private void updateSelectedTime() {
		if (mode == DATE_ONLY)
			return;
		selectedHour = (int) hourCombo.getSelectedItem();
		selectedMinute = (int) minuteCombo.getSelectedItem();
		selectedSecond = (int) secondCombo.getSelectedItem();
	}

	private void updateSelectedDate() {
		if (mode == TIME_ONLY)
			return;
		selectedDay = cbDay.getSelectedIndex();
		selectedMonth = cbMonth.getSelectedIndex();
		selectedYear = (int) cbYear.getSelectedItem();
	}

	private void updateDayComboBox(ActionEvent e) {
		cbDay.removeAllItems();
		int year = (int) cbYear.getSelectedItem();
		int month = cbMonth.getSelectedIndex();

		Calendar calendar = new GregorianCalendar(year, month, 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= daysInMonth; i++) {
			cbDay.addItem(String.valueOf(i));
		}

	}

	private void onConfirmClicked(ActionEvent e) {
		updateSelectedDate();
		updateSelectedTime();
		this.dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		selectedDay = -1;
		selectedMonth = -1;
		selectedYear = -1;
		selectedHour = -1;
		selectedMinute = -1;
		selectedSecond = -1;
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton button = new JButton("Chọn ngày");
		JLabel dateLabel = new JLabel("Chưa chọn ngày");

		button.addActionListener(e -> {
			DateTimePicker datePicker = new DateTimePicker(frame, DATE_AND_TIME);
			datePicker.setVisible(true);
			LocalDateTime selectedDate = datePicker.getSelectedDateTime();
			dateLabel.setText(selectedDate == null ? "Chưa chọn ngày" : selectedDate.toString());
		});

		frame.setLayout(new FlowLayout());
		frame.add(button);
		frame.add(dateLabel);
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
