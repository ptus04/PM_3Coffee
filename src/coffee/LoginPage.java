package coffee;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import coffee.guis.ButtonFactory;

import java.awt.Insets;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 494958162374815687L;

	private JPanel sidePanel;
	private JLabel logo;
	private JPanel formPanel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton loginButton;
	private GridBagConstraints c;

	public LoginPage() {
		setTitle("3Coffee");

		setContentPane(new JPanel(new BorderLayout()) {

			private static final long serialVersionUID = 6923009636167227556L;
			private Image backgroundImage;
			{
				try {
					backgroundImage = ImageIO.read(getClass().getResource("/image/background.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backgroundImage != null) {
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		});

		add(sidePanel = new JPanel(new GridLayout(2, 1)), BorderLayout.WEST);
		sidePanel.setBackground(new Color(255, 255, 255, 200));

		sidePanel.add(logo = new JLabel("3Coffee"));
		logo.setFont(new Font("Arial", Font.BOLD, 50));
		logo.setForeground(Application.COLOR_PRIMARY);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.BOTTOM);

		sidePanel.add(formPanel = new JPanel(new GridBagLayout()));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		formPanel.setOpaque(false);

		c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.anchor = GridBagConstraints.EAST;
		formPanel.add(usernameLabel = new JLabel("Tên đăng nhập:"), c);
		c.gridy = 1;
		formPanel.add(passwordLabel = new JLabel("Mật khẩu:"), c);
		c.gridx = 1;
		c.gridy = 0;
		formPanel.add(usernameInput = new JTextField(20), c);
		usernameInput.setBorder(Application.BORDER_INPUT);
		c.gridx = 1;
		c.gridy = 1;
		formPanel.add(passwordInput = new JPasswordField(20), c);
		passwordInput.setBorder(Application.BORDER_INPUT);
		c.gridy = 2;
		formPanel.add(loginButton = ButtonFactory.createPrimaryButton("Đăng nhập"), c);
		loginButton.addActionListener(e-> {
			new MainPage().setVisible(true);
			this.dispose();
		});

		setSize(1280, 720);
	}
}
