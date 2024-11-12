package coffee.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimePicker extends JDialog {
	private int selectedHour = -1;
	private int selectedMinute = -1;

	private JComboBox<Integer> hourCombo;
	private JComboBox<Integer> minuteCombo;
	private JLabel selectedTimeLabel;

	public TimePicker(JFrame parent) {
		super(parent, "Select Time", true);
		setLayout(new BorderLayout());

		// Initialize UI Components
		selectedTimeLabel = new JLabel("No Time Selected");

		// Hour Selection
		hourCombo = new JComboBox<>();
		for (int i = 0; i < 24; i++) {
			hourCombo.addItem(i);
		}

		// Minute Selection
		minuteCombo = new JComboBox<>();
		for (int i = 0; i < 60; i++) {
			minuteCombo.addItem(i);
		}

		// Event Listeners
		ActionListener timeUpdateListener = e -> updateSelectedTime();
		hourCombo.addActionListener(timeUpdateListener);
		minuteCombo.addActionListener(timeUpdateListener);

		// Arrange UI Components
		JPanel controlPanel = new JPanel();
		controlPanel.add(new JLabel("Hour:"));
		controlPanel.add(hourCombo);
		controlPanel.add(new JLabel("Minute:"));
		controlPanel.add(minuteCombo);

		add(controlPanel, BorderLayout.NORTH);
		add(selectedTimeLabel, BorderLayout.SOUTH);

		setSize(200, 150);
		setLocationRelativeTo(parent);
	}

	private void updateSelectedTime() {
		selectedHour = (int) hourCombo.getSelectedItem();
		selectedMinute = (int) minuteCombo.getSelectedItem();
		selectedTimeLabel.setText(String.format("Selected Time: %02d:%02d", selectedHour, selectedMinute));
	}

	public String getSelectedTime() {
		return selectedTimeLabel.getText();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton button = new JButton("Pick a Time");
		JLabel timeLabel = new JLabel("Selected Time:");

		button.addActionListener(e -> {
			TimePicker timePicker = new TimePicker(frame);
			timePicker.setVisible(true);
			timeLabel.setText(timePicker.getSelectedTime());
		});

		frame.setLayout(new FlowLayout());
		frame.add(button);
		frame.add(timeLabel);
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
