package coffee.view;

import coffee.controller.NhanVienController;
import coffee.dao.NhanVienDAO;
import coffee.entity.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TraCuuNhanVienView extends JFrame {

    private JTextField txtTimKiem;
    private JButton btnTimKiem, btnThem, btnSua, btnXoa, btnXoaRong, btnXuatCSV, btnTinhLuong, btnXuatExcel, btnIn;
    private JTable bangNhanVien;
    private DefaultTableModel moHinhBang;
    private NhanVienDAO nhanVienDAO;

    public TraCuuNhanVienView() {
        nhanVienDAO = NhanVienDAO.getInstance();

        setTitle("Tra Cứu Nhân Viên");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(139, 69, 19));
        setLayout(new BorderLayout());

        JPanel panelTimKiem = new JPanel(new FlowLayout());
        panelTimKiem.setBackground(new Color(139, 69, 19));

        JLabel lblTimKiem = new JLabel("Nhập mã nhân viên hoặc tên:");
        lblTimKiem.setForeground(Color.WHITE);
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm kiếm");

        btnTimKiem.addActionListener(e -> timKiemNhanVien());

        panelTimKiem.add(lblTimKiem);
        panelTimKiem.add(txtTimKiem);
        panelTimKiem.add(btnTimKiem);

        btnXuatCSV = new JButton("Xuất CSV");
        btnXoaRong = new JButton("Xóa Rỗng");
        btnXoaRong.addActionListener(e -> xoaRongBANG());

        btnXuatCSV.addActionListener(e -> xuatCSV()); 

        panelTimKiem.add(btnXuatCSV);
        panelTimKiem.add(btnXoaRong); 

        moHinhBang = new DefaultTableModel();
        moHinhBang.setColumnIdentifiers(new String[] {
            "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "Số CCCD", 
            "SĐT", "Địa Chỉ", "Hệ Số Lương", "Là Quản Lý", "Trạng Thái", "Hình Ảnh"
        });
        bangNhanVien = new JTable(moHinhBang);
        bangNhanVien.setBackground(new Color(222, 184, 135));
        bangNhanVien.setGridColor(new Color(139, 69, 19));

        JScrollPane cuonBang = new JScrollPane(bangNhanVien);

        add(panelTimKiem, BorderLayout.NORTH);
        add(cuonBang, BorderLayout.CENTER);

        try {
            hienThiDuLieuTuSQL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hienThiDuLieuTuSQL() throws SQLException {
        moHinhBang.setRowCount(0);
        List<NhanVien> danhSachNhanVien = nhanVienDAO.getAllNhanVien();
        DateTimeFormatter dinhDangNgay = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (NhanVien nv : danhSachNhanVien) {
            moHinhBang.addRow(new Object[] {
                nv.getMaNhanVien(),
                nv.getHoTen(),
                "Nam",
                nv.getNgaySinh() != null ? nv.getNgaySinh().format(dinhDangNgay) : "N/A",
                nv.getSoCanCuoc(),
                nv.getSoDienThoai(),
                nv.getDiaChi(),
                nv.getHeSoLuong(),
                nv.isLaQuanLy() ? "Có" : "Không",
                "Còn làm việc", 
                nv.getHinhAnh()
            });
        }
    }

    private void timKiemNhanVien() {
        String tuKhoa = txtTimKiem.getText().trim();
        if (tuKhoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên hoặc tên để tìm kiếm.");
            return;
        }

        try {
            List<NhanVien> danhSachNhanVien = nhanVienDAO.timKiemNhanVien(tuKhoa);

            moHinhBang.setRowCount(0);
            DateTimeFormatter dinhDangNgay = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (NhanVien nv : danhSachNhanVien) {
                moHinhBang.addRow(new Object[] {
                    nv.getMaNhanVien(),
                    nv.getHoTen(),
                    "Nam", 
                    nv.getNgaySinh() != null ? nv.getNgaySinh().format(dinhDangNgay) : "N/A",
                    nv.getSoCanCuoc(),
                    nv.getSoDienThoai(),
                    nv.getDiaChi(),
                    nv.getHeSoLuong(),
                    nv.isLaQuanLy() ? "Có" : "Không",
                    "Còn làm việc", 
                    nv.getHinhAnh()
                });
            }

            if (danhSachNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoaRongBANG() {
        moHinhBang.setRowCount(0); 
    }

    public void xuatCSV() {  // Chỉnh sửa phạm vi truy cập thành public
        try (FileWriter writer = new FileWriter("nhanvien.csv")) {
            writer.write("Mã NV,Họ Tên,Giới Tính,Ngày Sinh,Số CCCD,SĐT,Địa Chỉ,Hệ Số Lương,Là Quản Lý,Trạng Thái,Hình Ảnh\n");

            for (int i = 0; i < moHinhBang.getRowCount(); i++) {
                writer.write(moHinhBang.getValueAt(i, 0) + ","
                        + moHinhBang.getValueAt(i, 1) + ","
                        + moHinhBang.getValueAt(i, 2) + ","
                        + moHinhBang.getValueAt(i, 3) + ","
                        + moHinhBang.getValueAt(i, 4) + ","
                        + moHinhBang.getValueAt(i, 5) + ","
                        + moHinhBang.getValueAt(i, 6) + ","
                        + moHinhBang.getValueAt(i, 7) + ","
                        + moHinhBang.getValueAt(i, 8) + ","
                        + moHinhBang.getValueAt(i, 9) + ","
                        + moHinhBang.getValueAt(i, 10) + "\n");
            }
            JOptionPane.showMessageDialog(this, "Xuất CSV thành công.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất CSV: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TraCuuNhanVienView view = new TraCuuNhanVienView();
            new NhanVienController();  // Khởi tạo controller
            view.setVisible(true);
        });
    }

    public JButton getBtnTimKiem() {
        return btnTimKiem;
    }

    public JButton getBtnXoaRong() {
        return btnXoaRong;
    }

    public JButton getBtnXuatCSV() {
        return btnXuatCSV;
    }

    public JTextField getTxtTimKiem() {
        return txtTimKiem;
    }

    public DefaultTableModel getMoHinhBang() {
        return moHinhBang;
    }
}
