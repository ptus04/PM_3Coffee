package coffee.view;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.gui.CoffeeTextField;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class TraCuuCaLamView extends JFrame {

	private static final long serialVersionUID = 740670148745590805L;

	private Box pNorth;
	private Box pCenter;
	private Box pNorth1;
	private Box pNorth2;

	private JLabel lMaCaLam;
	private JLabel lTenCaLam;
	private JLabel lBatDauLuc;
	private JLabel lKetThucLuc;

	private CoffeeTextField tfMaCaLam;
	private CoffeeTextField tfTenCaLam;
	private CoffeeTextField tfBatDauLuc;
	private CoffeeTextField tfKetThucLuc;

	private SecondaryButton btnXoaRong;
	private PrimaryButton btnTimKiem;

	private JScrollPane scTable;
	private JTable tbKetQua;
	private DefaultTableModel tMdlKetQua;

	public TraCuuCaLamView() {
		getContentPane().setBackground(Colors.BACKGROUND);

		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		add(pCenter = Box.createVerticalBox(), BorderLayout.CENTER);

		// NORTH
		pNorth.add(pNorth1 = Box.createHorizontalBox());
		pNorth.add(Box.createVerticalStrut(8));
		pNorth.add(pNorth2 = Box.createHorizontalBox());

		pNorth1.add(lMaCaLam = new JLabel("Mã ca làm:"));
		pNorth1.add(Box.createHorizontalStrut(8));
		pNorth1.add(tfMaCaLam = new CoffeeTextField(20));
		pNorth1.add(Box.createHorizontalStrut(16));
		pNorth1.add(lTenCaLam = new JLabel("Tên ca làm:"));
		pNorth1.add(Box.createHorizontalStrut(8));
		pNorth1.add(tfTenCaLam = new CoffeeTextField(20));
		pNorth1.add(Box.createHorizontalStrut(16));
		pNorth1.add(btnXoaRong = new SecondaryButton("Xóa rỗng"));

		pNorth2.add(lBatDauLuc = new JLabel("Bắt đầu lúc:"));
		pNorth2.add(Box.createHorizontalStrut(8));
		pNorth2.add(tfBatDauLuc = new CoffeeTextField(20));
		pNorth2.add(Box.createHorizontalStrut(16));
		pNorth2.add(lKetThucLuc = new JLabel("Kết thúc lúc:"));
		pNorth2.add(Box.createHorizontalStrut(8));
		pNorth2.add(tfKetThucLuc = new CoffeeTextField(20));
		pNorth2.add(Box.createHorizontalStrut(16));
		pNorth2.add(btnTimKiem = new PrimaryButton("Tìm kiếm"));

		pNorth1.setAlignmentX(LEFT_ALIGNMENT);
		pNorth2.setAlignmentX(LEFT_ALIGNMENT);

		lMaCaLam.setFont(Fonts.TEXT);
		lTenCaLam.setFont(Fonts.TEXT);
		lBatDauLuc.setFont(Fonts.TEXT);
		lKetThucLuc.setFont(Fonts.TEXT);

		lMaCaLam.setMaximumSize(lBatDauLuc.getPreferredSize());
		lTenCaLam.setMaximumSize(lKetThucLuc.getPreferredSize());

		tfMaCaLam.setMaximumSize(tfMaCaLam.getPreferredSize());
		tfTenCaLam.setMaximumSize(tfTenCaLam.getPreferredSize());
		tfBatDauLuc.setMaximumSize(tfBatDauLuc.getPreferredSize());
		tfKetThucLuc.setMaximumSize(tfKetThucLuc.getPreferredSize());

		// CENTER
		pCenter.add(Box.createVerticalStrut(16));
		String[] columnNames = { "Mã ca làm", "Tên ca làm", "Thời gian bắt đầu", "Thời gian kết thúc", "Ghi chú" };
		pCenter.add(
				scTable = new JScrollPane(tbKetQua = new JTable(tMdlKetQua = new DefaultTableModel(columnNames, 0))));
		tbKetQua.setFillsViewportHeight(true);

		// MOCKUP
		tMdlKetQua.addRow(new Object[] { "CA24111101", "Ca sáng", "06:00:00", "10:00:00", null });
	}

	public static void main(String[] args) {
		TraCuuCaLamView traCuuCaLamView = new TraCuuCaLamView();
		traCuuCaLamView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		traCuuCaLamView.setSize(1280, 720);
		traCuuCaLamView.setVisible(true);
	}

}
