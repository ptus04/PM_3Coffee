package coffee.view;

import javax.swing.*;

import coffee.gui.CoffeeDateTimeField;
import coffee.gui.CoffeeLabel;
import coffee.gui.CoffeePanel;
import coffee.gui.CoffeeScrollPane;
import coffee.gui.CoffeeTable;
import coffee.gui.CoffeeTextField;
import coffee.gui.DateTimePicker;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class TraCuuDonHangView extends JFrame {

	private static final long serialVersionUID = -5131979068687458884L;
	private JTable tbDonHang;

	private PrimaryButton btnTraCuu;
	private PrimaryButton btnViewDetails;
	private SecondaryButton btnPrint;

	private Box pNorth;
	private Box pMaHoaDon;
	private Box pThoiGian;
	private Box pThoiGianContainer;
	private Box pNhanVien;
	private Box pSanPham;
	private Box pKhuyenMai;
	private Box pKhachHang;
	private Box pThaoTac;
	private Box pThaoTac2;

	private CoffeeTextField tfMaDonHang;
	private CoffeeTextField tfTuLuc;
	private CoffeeTextField tfDenLuc;
	private CoffeeTextField tfMaNhanVien;
	private CoffeeTextField tfMaSanPham;
	private CoffeeTextField tfMaKhuyenMai;
	private CoffeeTextField tfSoDienThoai;

	private CoffeeLabel lMaHoaDon;
	private CoffeeLabel lTuLuc;
	private CoffeeLabel lDenLuc;
	private CoffeeLabel lMaNhanVien;
	private CoffeeLabel lMaSanPham;
	private CoffeeLabel lMaKhuyenMai;
	private CoffeeLabel lSoDienThoai;

	public TraCuuDonHangView() {
		setTitle("Tra cứu hóa đơn");
		setContentPane(new CoffeePanel(new BorderLayout(8, 8), new Insets(16, 16, 16, 16)));

		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(pMaHoaDon = Box.createHorizontalBox());
		pMaHoaDon.add(lMaHoaDon = new CoffeeLabel("Mã hóa đơn:"));
		pMaHoaDon.add(Box.createHorizontalStrut(16));
		pMaHoaDon.add(tfMaDonHang = new CoffeeTextField(20));
		pNorth.add(Box.createVerticalStrut(8));

		pNorth.add(pThoiGian = Box.createHorizontalBox());
		pThoiGian.add(pThoiGianContainer = Box.createHorizontalBox());
		pThoiGianContainer.add(lTuLuc = new CoffeeLabel("Từ lúc:"));
		pThoiGianContainer.add(Box.createHorizontalStrut(16));
		pThoiGianContainer.add(tfTuLuc = new CoffeeDateTimeField(DateTimePicker.DATE_AND_TIME));
		pThoiGianContainer.add(Box.createHorizontalStrut(8));
		pThoiGianContainer.add(lDenLuc = new CoffeeLabel("Đến lúc:"));
		pThoiGianContainer.add(Box.createHorizontalStrut(16));
		pThoiGianContainer.add(tfDenLuc = new CoffeeDateTimeField(DateTimePicker.DATE_AND_TIME));
		pNorth.add(Box.createVerticalStrut(8));

		pNorth.add(pNhanVien = Box.createHorizontalBox());
		pNhanVien.add(lMaNhanVien = new CoffeeLabel("Mã nhân viên:"));
		pNhanVien.add(Box.createHorizontalStrut(16));
		pNhanVien.add(tfMaNhanVien = new CoffeeTextField(20));
		pNorth.add(Box.createVerticalStrut(8));

		pNorth.add(pSanPham = Box.createHorizontalBox());
		pSanPham.add(lMaSanPham = new CoffeeLabel("Mã sản phẩm:"));
		pSanPham.add(Box.createHorizontalStrut(16));
		pSanPham.add(tfMaSanPham = new CoffeeTextField(20));
		pNorth.add(Box.createVerticalStrut(8));

		pNorth.add(pKhuyenMai = Box.createHorizontalBox());
		pKhuyenMai.add(lMaKhuyenMai = new CoffeeLabel("Mã khuyến mãi:"));
		pKhuyenMai.add(Box.createHorizontalStrut(16));
		pKhuyenMai.add(tfMaKhuyenMai = new CoffeeTextField(20));
		pNorth.add(Box.createVerticalStrut(8));

		pNorth.add(pKhachHang = Box.createHorizontalBox());
		pKhachHang.add(lSoDienThoai = new CoffeeLabel("Số điện thoại khách hàng:"));
		pKhachHang.add(Box.createHorizontalStrut(16));
		pKhachHang.add(tfSoDienThoai = new CoffeeTextField(20));
		pNorth.add(Box.createVerticalStrut(8));

		pNorth.add(pThaoTac = Box.createHorizontalBox());
		pThaoTac.add(Box.createHorizontalGlue());
		pThaoTac.add(btnTraCuu = new PrimaryButton("Tra cứu"));

		lMaHoaDon.setPreferredSize(lSoDienThoai.getPreferredSize());
		lTuLuc.setPreferredSize(lSoDienThoai.getPreferredSize());
		lDenLuc.setPreferredSize(lSoDienThoai.getPreferredSize());
		lMaNhanVien.setPreferredSize(lSoDienThoai.getPreferredSize());
		lMaSanPham.setPreferredSize(lSoDienThoai.getPreferredSize());
		lMaKhuyenMai.setPreferredSize(lSoDienThoai.getPreferredSize());
		lSoDienThoai.setPreferredSize(lSoDienThoai.getPreferredSize());
		lMaHoaDon.setPreferredSize(lSoDienThoai.getPreferredSize());

		btnTraCuu.setPreferredSize(btnTraCuu.getPreferredSize());

		add(new CoffeeScrollPane(tbDonHang = new CoffeeTable()), BorderLayout.CENTER);
		tbDonHang.setDefaultEditor(Object.class, null);

		add(pThaoTac2 = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pThaoTac2.add(btnPrint = new SecondaryButton("In hóa đơn"));
		pThaoTac2.add(Box.createHorizontalGlue());
		pThaoTac2.add(btnViewDetails = new PrimaryButton("Xem chi tiết"));

		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public JTable getTbDonHang() {
		return tbDonHang;
	}

	public void addBtnTraCuuActionListener(ActionListener listener) {
		btnTraCuu.addActionListener(listener);
	}

	public void addBtnViewDetailsActionListener(ActionListener listener) {
		btnViewDetails.addActionListener(listener);
	}

	public void addBtnPrintActionListener(ActionListener listener) {
		btnPrint.addActionListener(listener);
	}

	public CoffeeTextField getTfMaDonHang() {
		return tfMaDonHang;
	}

	public CoffeeTextField getTfTuLuc() {
		return tfTuLuc;
	}

	public CoffeeTextField getTfDenLuc() {
		return tfDenLuc;
	}

	public CoffeeTextField getTfMaNhanVien() {
		return tfMaNhanVien;
	}

	public CoffeeTextField getTfMaSanPham() {
		return tfMaSanPham;
	}

	public CoffeeTextField getTfMaKhuyenMai() {
		return tfMaKhuyenMai;
	}

	public CoffeeTextField getTfSoDienThoai() {
		return tfSoDienThoai;
	}

}
