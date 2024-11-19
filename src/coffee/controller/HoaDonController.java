package coffee.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import coffee.Application;
import coffee.dao.ChiTietDonHangDAO;
import coffee.dao.DonHang_DAO;
import coffee.dao.KhachHangDAO;
import coffee.dao.NhanVienDAO;
import coffee.dao.SanPhamDAO;
import coffee.entity.ChiTietDonHang;
import coffee.entity.DonHang;
import coffee.entity.KhachHang;
import coffee.entity.NhanVien;
import coffee.entity.SanPham;
import coffee.shared.utility.PrinterUtilities;
import coffee.view.ChiTietHoaDonView;
import coffee.view.TraCuuDonHangView;

public class HoaDonController {

	private static HoaDonController instance;

	public static HoaDonController getInstance() {
		if (instance == null)
			instance = new HoaDonController();
		return instance;
	}

	private TraCuuDonHangView view;
	private DefaultTableModel model;

	public TraCuuDonHangView getView() {
		traCuu();
		return view;
	}

	public HoaDonController() {
		this.view = new TraCuuDonHangView();

		model = new DefaultTableModel(
				new String[] { "Mã đơn hàng", "Thời gian tạo", "Nhân viên", "Khách hàng", "Tổng tiền" }, 0);
		view.getTbDonHang().setModel(model);

		view.addBtnTraCuuActionListener(e -> traCuu());
		view.addBtnViewDetailsActionListener(e -> xemChiTiet());
		view.addBtnPrintActionListener(e -> inHoaDon());
	}

	private boolean validate() {
		String maDonHang = view.getTfMaDonHang().getText();
		String maNhanVien = view.getTfMaNhanVien().getText();
		String maSanPham = view.getTfMaSanPham().getText();
		String maKhuyenMai = view.getTfMaKhuyenMai().getText();
		String soDienThoai = view.getTfSoDienThoai().getText();

		if (!maDonHang.isBlank() && !maDonHang.matches("DH\\d{10}")) {
			JOptionPane.showMessageDialog(view, "Mã đơn hàng phải có dạng DH<10 chữ số>", "Lỗi định dạng",
					JOptionPane.ERROR_MESSAGE);
			view.getTfMaDonHang().requestFocus();
			return false;
		}

		if (!maNhanVien.isBlank() && !maNhanVien.matches("NV\\d{6}")) {
			JOptionPane.showMessageDialog(view, "Mã nhân viên không hợp lệ. Định dạng phải là NV[YYMM][##]",
					"Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
			view.getTfMaNhanVien().requestFocus();
			return false;
		}

		if (!maSanPham.isBlank() && !maSanPham.matches("SP\\d{3}")) {
			JOptionPane.showMessageDialog(view, "Mã sản phẩm phải bắt đầu bằng SP, theo sau là 3 ký số",
					"Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
			view.getTfMaSanPham().requestFocus();
			return false;
		}

		if (!maKhuyenMai.isBlank() && !maKhuyenMai.matches("KM\\d{8}")) {
			JOptionPane.showMessageDialog(view, "Mã khuyến mãi phải bắt đầu bằng KM, theo sau là 8 ký số",
					"Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
			view.getTfMaKhuyenMai().requestFocus();
			return false;
		}

		if (!soDienThoai.isBlank() && !soDienThoai.matches("\\d{10}")) {
			JOptionPane.showMessageDialog(view, "Số điện thoại phải có 10 chữ số", "Lỗi định dạng",
					JOptionPane.ERROR_MESSAGE);
			view.getTfSoDienThoai().requestFocus();
			return false;
		}

		return true;
	}

	private void traCuu() {
		if (!validate())
			return;

		String maDonHang = view.getTfMaDonHang().getText();
		if (maDonHang.isBlank()) {
			reloadTable();
		} else {
			try {
				model.setRowCount(0);
				DonHang donHang = DonHang_DAO.getInstance().selectById(new DonHang(maDonHang));
				if (donHang == null) {
					JOptionPane.showMessageDialog(view, "Không tìm thấy đơn hàng " + maDonHang, "Không tìm thấy",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				NhanVien nhanVien = NhanVienDAO.getInstance().getByNhanVien(donHang.getNhanvien());
				model.addRow(new Object[] { donHang.getMaDonHang(), donHang.getThoiGianTao(), nhanVien.getHoTen(),
						donHang.getKhachHang().getTenKhachHang(),
						Application.FMT_CURRENCY.format(donHang.tinhTongTien()) });
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi truy vấn dữ liệu", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void xemChiTiet() {
		int row = view.getTbDonHang().getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn một đơn hàng để xem chi tiết", "Chưa chọn đơn hàng",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String maDonHang = (String) model.getValueAt(row, 0);
		try {
			DonHang donHang = DonHang_DAO.getInstance().selectById(new DonHang(maDonHang));
			donHang.setKhachHang(KhachHangDAO.getInstance().getById(donHang.getKhachHang().getSoDienThoai()));
			donHang.setNhanvien(NhanVienDAO.getInstance().getByNhanVien(donHang.getNhanvien()));
			List<ChiTietDonHang> chiTiet = ChiTietDonHangDAO.getInstance().getByMaDonHang(maDonHang);
			chiTiet = chiTiet.stream().map(x -> {
				try {
					x.setSanPham(SanPhamDAO.getInstance().getById(x.getSanPham().getMaSanPham()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return x;
			}).toList();
			donHang.setDanhsach(chiTiet);
			ChiTietHoaDonView chiTietDonHangView = new ChiTietHoaDonView(donHang, chiTiet);
			chiTietDonHangView.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi truy vấn dữ liệu", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void inHoaDon() {
		int row = view.getTbDonHang().getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn một đơn hàng để xem chi tiết", "Chưa chọn đơn hàng",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String maDonHang = (String) model.getValueAt(row, 0);
		try {
			DonHang donHang = DonHang_DAO.getInstance().selectById(new DonHang(maDonHang));
			donHang.setKhachHang(KhachHangDAO.getInstance().getById(donHang.getKhachHang().getSoDienThoai()));
			donHang.setNhanvien(NhanVienDAO.getInstance().getByNhanVien(donHang.getNhanvien()));
			donHang.setThoiGianIn(LocalDateTime.now());
			List<ChiTietDonHang> chiTiet = ChiTietDonHangDAO.getInstance().getByMaDonHang(maDonHang);
			chiTiet = chiTiet.stream().map(x -> {
				try {
					x.setSanPham(SanPhamDAO.getInstance().getById(x.getSanPham().getMaSanPham()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return x;
			}).toList();
			donHang.setDanhsach(chiTiet);
			ChiTietHoaDonView chiTietDonHangView = new ChiTietHoaDonView(donHang, chiTiet);
			PrinterUtilities.getInstance().print(chiTietDonHangView, "In Hóa đơn " + maDonHang);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi truy vấn dữ liệu", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void reloadTable() {
		try {
			Map<String, String> mapNhanVien = NhanVienDAO.getInstance().getAllNhanVien().stream()
					.collect(Collectors.toMap(NhanVien::getMaNhanVien, NhanVien::getHoTen));
			Map<String, String> mapKhachHang = KhachHangDAO.getInstance().getAll().stream()
					.collect(Collectors.toMap(KhachHang::getSoDienThoai, KhachHang::getTenKhachHang));

			List<DonHang> donHangList = DonHang_DAO.getInstance().selectAll();

			Stream<DonHang> stream = donHangList.stream();
			if (!view.getTfTuLuc().getText().equals("Chưa chọn")) {
				LocalDateTime x = LocalDateTime.parse(view.getTfTuLuc().getText());
				stream = stream.filter(p -> p.getThoiGianTao().isAfter(x) || p.getThoiGianTao().isEqual(x));
			}
			if (!view.getTfDenLuc().getText().equals("Chưa chọn")) {
				LocalDateTime x = LocalDateTime.parse(view.getTfDenLuc().getText());
				stream = stream.filter(p -> p.getThoiGianTao().isBefore(x) || p.getThoiGianTao().isEqual(x));
			}
			if (!view.getTfMaNhanVien().getText().isBlank()) {
				String x = view.getTfMaNhanVien().getText();
				stream = stream.filter(p -> p.getNhanvien().getMaNhanVien().equalsIgnoreCase(x));
			}
			if (!view.getTfMaSanPham().getText().isBlank()) {
				String x = view.getTfMaSanPham().getText();
				try {
					List<ChiTietDonHang> chiTietList = ChiTietDonHangDAO.getInstance().getAll();
					List<ChiTietDonHang> ct = chiTietList.stream()
							.filter(p -> p.getSanPham().getMaSanPham().equalsIgnoreCase(x)).toList();
					stream = stream.filter(
							p -> ct.stream().anyMatch(q -> q.getDonHang().getMaDonHang().equals(p.getMaDonHang())));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (!view.getTfMaKhuyenMai().getText().isBlank()) {
				String x = view.getTfMaKhuyenMai().getText();
				stream = stream.filter(p -> {
					if (p.getKhuyenmai() == null)
						return false;
					return p.getKhuyenmai().getMaKhuyenMai().equalsIgnoreCase(x);
				});
			}
			if (!view.getTfSoDienThoai().getText().isBlank()) {
				String x = view.getTfSoDienThoai().getText();
				stream = stream.filter(p -> p.getKhachHang().getSoDienThoai().equalsIgnoreCase(x));
			}

			model.setRowCount(0);
			stream.forEach(x -> {
				try {
					List<ChiTietDonHang> chiTiet = ChiTietDonHangDAO.getInstance().getByMaDonHang(x.getMaDonHang());
					Map<String, SanPham> mapSanPham = SanPhamDAO.getInstance().getAll().stream()
							.collect(Collectors.toMap(SanPham::getMaSanPham, p -> p));
					chiTiet = chiTiet.stream().map(p -> {
						p.setSanPham(mapSanPham.get(p.getSanPham().getMaSanPham()));
						return p;
					}).toList();
					x.setDanhsach(chiTiet);
					model.addRow(new Object[] { x.getMaDonHang(), x.getThoiGianTao().format(Application.FMT_DATE_TIME),
							mapNhanVien.get(x.getNhanvien().getMaNhanVien()),
							mapKhachHang.get(x.getKhachHang().getSoDienThoai()),
							Application.FMT_CURRENCY.format(x.tinhTongTien()) });
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi truy vấn dữ liệu", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

}
