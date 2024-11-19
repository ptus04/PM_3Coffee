package coffee.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coffee.controller.NhapThongTinKhachHangController;
import coffee.gui.CoffeeTextField;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class NhapThongTinKhachHangView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CoffeeTextField txtNumberPhone;
	private CoffeeTextField txtNameCustomer;
	private PrimaryButton btnComplete;
	private SecondaryButton btnRegister;
	private static NhapThongTinKhachHangController controller; 
		
	public NhapThongTinKhachHangController getNhapThongTinKhachHangController() {
		return controller;
	}
	public CoffeeTextField getNumberPhone() {
		return txtNumberPhone;
	}
	public CoffeeTextField getNameCustomer() {
		return txtNameCustomer;
	}
	public PrimaryButton getButtonComplete() {
		return btnComplete;
	}
	public SecondaryButton getButtonRegister() {
		return btnRegister;
	}
	public NhapThongTinKhachHangView() {
		setSize(450,350);
		setTitle("Màn hình nhập thông tin khách hàng");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel pnInputInfoCustomer = new JPanel();
		JPanel pnNumberPhone = new JPanel();
		JLabel lbNumberPhone;
		pnNumberPhone.add(lbNumberPhone = new JLabel("Nhập số điện thoại: "));
		pnNumberPhone.add(txtNumberPhone = new CoffeeTextField(20));
		
		JPanel pnNameCustomer = new JPanel();
		JLabel lbNameCustomer;
		pnNameCustomer.add(lbNameCustomer = new JLabel("Nhập tên khách: "));
		pnNameCustomer.add(txtNameCustomer = new CoffeeTextField(20));
		
		JPanel pnButton = new JPanel();
		pnButton.add(btnComplete = new PrimaryButton("Hoàn tất"));
		pnButton.add(btnRegister = new SecondaryButton("Đăng ký"));
		
		pnInputInfoCustomer.add(pnNumberPhone);
		pnInputInfoCustomer.add(pnNameCustomer);
		pnInputInfoCustomer.add(pnButton);
		add(pnInputInfoCustomer, BorderLayout.CENTER);
		
		lbNameCustomer.setPreferredSize(new Dimension(120,100));
		lbNumberPhone.setPreferredSize(new Dimension(120,100));
	
		
	}
//	public static void main(String[] args) {
//		NhapThongTinKhachHangView gui = new NhapThongTinKhachHangView();
//		controller = new NhapThongTinKhachHangController(gui);
//	}
	
}
