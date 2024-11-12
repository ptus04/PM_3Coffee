package coffee.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.FlowLayout;
import java.util.List;

public class EditableComboBox extends JComboBox<String> {

	private static final long serialVersionUID = 8708640556971300475L;

	private Timer filterTimer;
	private JTextField textField;
	private List<String> items;

	public EditableComboBox(List<String> items) {
		super(items.toArray(String[]::new));
		throw new UnsupportedOperationException("Not implemented yet!");

//		this.items = items;
//		this.textField = (JTextField) getEditor().getEditorComponent();
//		this.filterTimer = new Timer(300, e -> filter());
//
//		setEditable(true);
//		filterTimer.setRepeats(false);
//
//		textField.getDocument().addDocumentListener(new DocumentListener() {
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				System.out.println("insertUpdate");
//				filterTimer.restart();
//			}
//
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				System.out.println("removeUpdate");
//				filterTimer.restart();
//			}
//
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				System.out.println("changedUpdate");
//				filterTimer.restart();
//			}
//		});
	}

	private void filter() {

		String input = textField.getText();
		removeAllItems();

		for (String item : items) {
			if (item.toLowerCase().contains(input.toLowerCase())) {
				addItem(item);
			}
		}

		getEditor().setItem(input);
		setMaximumRowCount(getItemCount());
		showPopup();
	}

	private void createAndShowGUI() {
		JFrame frame = new JFrame("Editable ComboBox Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());

		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities
				.invokeLater(() -> new EditableComboBox(List.of("Apple", "Banana", "Cherry", "Date", "Grape", "Orange"))
						.createAndShowGUI());
	}

}
