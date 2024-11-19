package coffee.view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.gui.CoffeePanel;
import coffee.gui.CoffeePasswordField;
import coffee.gui.CoffeeTextField;
import coffee.shared.PrimaryButton;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DangNhapView extends JFrame {

	private static final long serialVersionUID = 494958162374815687L;

	private JPanel pSide;
	private JLabel logo;
	private JPanel form;
	private JLabel lUsername;
	private JLabel lPassword;
	private CoffeeTextField tfUsername;
	private CoffeePasswordField tfPassword;
	private PrimaryButton btnDangNhap;
	private GridBagConstraints c = new GridBagConstraints();

	public CoffeeTextField getTfUsername() {
		return tfUsername;
	}

	public JPasswordField getTfPassword() {
		return tfPassword;
	}

	public PrimaryButton getBtnDangNhap() {
		return btnDangNhap;
	}
	
	public DangNhapView() {
		setTitle("3Coffee");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new CoffeePanel(new BorderLayout(), "/image/background.jpg"));

		add(pSide = new CoffeePanel(new GridLayout(2, 1), new Color(255, 255, 255, 200)), BorderLayout.WEST);

		pSide.add(logo = new JLabel("3Coffee"));
		logo.setFont(Fonts.HEADING_1B);
		logo.setForeground(Colors.PRIMARY);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.BOTTOM);

		pSide.add(form = new JPanel(new GridBagLayout()));
		form.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		form.setOpaque(false);

		c.insets = new Insets(8, 8, 8, 8);
		c.anchor = GridBagConstraints.EAST;

		lUsername = new JLabel("Tên đăng nhập:");
		lPassword = new JLabel("Mật khẩu:");
		tfUsername = new CoffeeTextField(20);
		tfPassword = new CoffeePasswordField(20);
		btnDangNhap = new PrimaryButton("Đăng nhập");

		lUsername.setFont(Fonts.TEXT);
		lPassword.setFont(Fonts.TEXT);

		form.add(lUsername, c);
		c.gridx = 1;
		form.add(tfUsername);
		c.gridx = 0;
		c.gridy = 1;
		form.add(lPassword, c);
		c.gridx = 1;
		form.add(tfPassword, c);
		c.gridx = 1;
		c.gridy = 2;
		form.add(btnDangNhap, c);

		addEventHandlers();

		setSize(1280, 720);
		setLocationRelativeTo(null);
	}

	private void addEventHandlers() {
		InputMap imBtnDangNhap = btnDangNhap.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		imBtnDangNhap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");

		ActionMap amBtnDangNhap = btnDangNhap.getActionMap();
		amBtnDangNhap.put("ENTER", onDangNhap);
	}

	private AbstractAction onDangNhap = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			btnDangNhap.doClick();
		}
	};

	public void resetView() {
		tfUsername.setText("");
		tfUsername.requestFocus();
		tfPassword.setText("");
	}
}
