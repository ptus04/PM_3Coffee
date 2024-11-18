package coffee.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JFrame;

public class CoffeeDateTimeField extends CoffeeTextField implements MouseListener {

	private static final long serialVersionUID = -6810252889347180808L;
	private int mode;
	private LocalDateTime selectedDateTime;

	public LocalDateTime getSelectedDateTime() {
		return selectedDateTime;
	}

	public CoffeeDateTimeField(int mode) {
		this.mode = mode;

		setEditable(false);
		setText("Chưa chọn");
		addMouseListener(this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CoffeeDateTimeField field = new CoffeeDateTimeField(DateTimePicker.DATE_AND_TIME);
		frame.add(field, BorderLayout.NORTH);

		System.out.println(field.getText());

		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DateTimePicker picker = new DateTimePicker(null, mode);
		picker.setLocationRelativeTo(this);
		picker.setVisible(true);
		if (mode == DateTimePicker.DATE_ONLY) {
			if (picker.getSelectedDate() == null) {
				setText("Chưa chọn");
				return;
			}
			selectedDateTime = LocalDateTime.of(picker.getSelectedDate(), LocalTime.MIN);
			setText(selectedDateTime.toString());
		} else if (mode == DateTimePicker.TIME_ONLY) {
			if (picker.getSelectedTime() == null) {
				setText("Chưa chọn");
				return;
			}
			selectedDateTime = LocalDateTime.of(LocalDate.MIN, picker.getSelectedTime());
			setText(selectedDateTime.toString());
		} else {
			if (picker.getSelectedDateTime() == null) {
				setText("Chưa chọn");
				return;
			}
			selectedDateTime = picker.getSelectedDateTime();
			setText(selectedDateTime.toString());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
