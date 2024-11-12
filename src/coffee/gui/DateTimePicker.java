package coffee.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DateTimePicker extends JDialog {
	private DatePicker datePicker;
	private TimePicker timePicker;
	private JLabel selectedDateTimeLabel;

	public DateTimePicker(JFrame parent) {
		super(parent, "Select Date and Time", true);
		setLayout(new BorderLayout());

		// Initialize UI Components
		datePicker = new DatePicker(parent);
		timePicker = new TimePicker(parent);
		selectedDateTimeLabel = new JLabel("No DateTime Selected");

		JButton selectButton = new JButton("Select Date and Time");
		selectButton.addActionListener(e -> updateSelectedDateTime());

		add(datePicker.getContentPane(), BorderLayout.NORTH);
		add(timePicker.getContentPane(), BorderLayout.CENTER);
		add(selectedDateTimeLabel, BorderLayout.SOUTH);
		add(selectButton, BorderLayout.PAGE_END);

		setSize(300, 500);
		setLocationRelativeTo(parent);
	}

	private void updateSelectedDateTime() {
		String selectedDate = datePicker.getSelectedDate().replace("Selected Date: ", "");
		String selectedTime = timePicker.getSelectedTime().replace("Selected Time: ", "");
		selectedDateTimeLabel.setText("Selected DateTime: " + selectedDate + " " + selectedTime);
	}

	public String getSelectedDateTime() {
		return selectedDateTimeLabel.getText();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton button = new JButton("Pick a Date and Time");
		JLabel dateTimeLabel = new JLabel("Selected DateTime:");

		button.addActionListener(e -> {
			DateTimePicker dateTimePicker = new DateTimePicker(frame);
			dateTimePicker.setVisible(true);
			dateTimeLabel.setText(dateTimePicker.getSelectedDateTime());
		});

		frame.setLayout(new FlowLayout());
		frame.add(button);
		frame.add(dateTimeLabel);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
