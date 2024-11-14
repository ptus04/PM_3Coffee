package coffee.view;

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

        // Các nút chức năng
//        btnThem = new JButton("Thêm Nhân Viên");
//        btnSua = new JButton("Chỉnh Sửa");
//        btnXoa = new JButton("Xóa");
        btnXuatCSV = new JButton("Xuất CSV");
//        btnTinhLuong = new JButton("Tính Lương");
//        btnXuatExcel = new JButton("Xuất Excel");
//        btnIn = new JButton("In Danh Sách");

        // Nút "Xóa Rỗng"
        btnXoaRong = new JButton("Xóa Rỗng");
        btnXoaRong.addActionListener(e -> xoaRongBANG());

        btnXuatCSV.addActionListener(e -> xuatCSV()); // Thêm sự kiện cho nút Xuất CSV

//        panelTimKiem.add(btnThem);
//        panelTimKiem.add(btnSua);
//        panelTimKiem.add(btnXoa);
        panelTimKiem.add(btnXuatCSV);
//        panelTimKiem.add(btnTinhLuong);
//        panelTimKiem.add(btnXuatExcel);
//        panelTimKiem.add(btnIn);
        panelTimKiem.add(btnXoaRong); // Thêm nút "Xóa Rỗng" vào giao diện

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
                "Nam", // Chỉnh sửa để luôn hiển thị là "Nam"
                nv.getNgaySinh() != null ? nv.getNgaySinh().format(dinhDangNgay) : "N/A",
                nv.getSoCanCuoc(),
                nv.getSoDienThoai(),
                nv.getDiaChi(),
                nv.getHeSoLuong(),
                nv.isLaQuanLy() ? "Có" : "Không",
                "Còn làm việc", // Chỉnh sửa để luôn hiển thị là "Còn làm việc"
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
            // Lấy danh sách nhân viên từ cơ sở dữ liệu
            List<NhanVien> danhSachNhanVien = nhanVienDAO.timKiemNhanVien(tuKhoa);

            // Xóa dữ liệu cũ trong bảng
            moHinhBang.setRowCount(0);

            // Định dạng ngày sinh
            DateTimeFormatter dinhDangNgay = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Hiển thị danh sách nhân viên tìm được
            for (NhanVien nv : danhSachNhanVien) {
                moHinhBang.addRow(new Object[] {
                    nv.getMaNhanVien(),
                    nv.getHoTen(),
                    "Nam", // Chỉnh sửa để luôn hiển thị là "Nam"
                    nv.getNgaySinh() != null ? nv.getNgaySinh().format(dinhDangNgay) : "N/A",
                    nv.getSoCanCuoc(),
                    nv.getSoDienThoai(),
                    nv.getDiaChi(),
                    nv.getHeSoLuong(),
                    nv.isLaQuanLy() ? "Có" : "Không",
                    "Còn làm việc", // Chỉnh sửa để luôn hiển thị là "Còn làm việc"
                    nv.getHinhAnh()
                });
            }

            // Nếu không tìm thấy nhân viên nào
            if (danhSachNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
//    
    // Phương thức xóa tất cả dữ liệu trong bảng
    private void xoaRongBANG() {
        moHinhBang.setRowCount(0); // Xóa hết tất cả các dòng trong bảng
    }

    // Phương thức xuất dữ liệu ra file CSV
    private void xuatCSV() {
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
        SwingUtilities.invokeLater(() -> new TraCuuNhanVienView().setVisible(true));
    }
}
