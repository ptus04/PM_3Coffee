package coffee.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coffee.gui.CoffeeTextField;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class ThayDoiSoLuongView extends JFrame{
	private CoffeeTextField txtQuantity;
	private PrimaryButton buttonThayDoi;
	public CoffeeTextField getQuantity() {
		return txtQuantity;
	}
	public PrimaryButton getThayDoiButton() {
		return buttonThayDoi;
	}
	public ThayDoiSoLuongView() {
		setSize(450,350);
		setTitle("Màn hình thay đổi số lượng");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		JPanel pnQuantity = new JPanel();
		JLabel lbQuantity;
		panel.add(lbQuantity = new JLabel("Nhập số lượng: "));
		panel.add(txtQuantity = new CoffeeTextField(20));
		
		
		
		JPanel pnButton = new JPanel();
		pnButton.add(buttonThayDoi = new PrimaryButton("Thay đổi"));
		
		panel.add(pnQuantity);
		panel.add(pnButton);
		add(panel, BorderLayout.CENTER);
	
	}
}
