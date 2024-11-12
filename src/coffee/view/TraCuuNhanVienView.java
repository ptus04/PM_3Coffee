package coffee.view;

import coffee.entity.NhanVien;
import coffee.entity.TaiKhoan;
import coffee.entity.TrangThaiLamViec;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TraCuuNhanVienView extends JFrame {

    private JTextField txtSearch;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public TraCuuNhanVienView() {
        // Thiết lập JFrame
        setTitle("Tra Cứu Nhân Viên");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cấu hình nền và layout
        getContentPane().setBackground(new Color(139, 69, 19)); // Màu nền nâu
        setLayout(new BorderLayout());

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(139, 69, 19)); // Màu nền cho panel tìm kiếm

        JLabel lblSearch = new JLabel("Nhập mã nhân viên hoặc tên:");
        lblSearch.setForeground(Color.WHITE);
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");

        // Sự kiện tìm kiếm
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchEmployee();
            }
        });

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Tạo bảng để hiển thị dữ liệu
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {
                "Mã Nhân Viên", "Họ và Tên", "Giới Tính", "Ngày Sinh", "Số Căn Cước",
                "Số Điện Thoại", "Địa Chỉ", "Hệ Số Lương", "Trạng Thái"
        });
        table = new JTable(tableModel);
        table.setBackground(new Color(222, 184, 135));
        table.setGridColor(new Color(139, 69, 19));

        // Thêm bảng vào JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Thêm các thành phần vào JFrame
        add(searchPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Hiển thị dữ liệu mẫu
        displaySampleData();
    }

    private void displaySampleData() {
        List<NhanVien> resultList = getSampleData();

        // Định dạng ngày sinh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (NhanVien emp : resultList) {
            // Đảm bảo các thuộc tính HoTen và DiaChi có dữ liệu hợp lệ
            String hoTen = emp.getHoTen() != null ? emp.getHoTen() : "Chưa có tên";
            String diaChi = emp.getDiaChi() != null ? emp.getDiaChi() : "Chưa có địa chỉ";

            tableModel.addRow(new Object[] {
                    emp.getMaNhanVien(),
                    hoTen,  // Hiển thị họ tên
                    emp.isGioiTinh() ? "Nam" : "Nữ",
                    emp.getNgaySinh() != null ? emp.getNgaySinh().format(formatter) : "N/A",
                    emp.getSoCanCuoc(),
                    emp.getSoDienThoai(),
                    diaChi,  // Hiển thị địa chỉ
                    emp.getHeSoLuong(),
                    emp.getTrangThaiLamViec() == TrangThaiLamViec.CON_LAM_VIEC ? "Còn làm việc" : "Nghỉ việc"
            });
        }
    }

    private List<NhanVien> getSampleData() {
        List<NhanVien> resultList = new ArrayList<>();
        NhanVien nv = new NhanVien("NV040301", "Phùng Văn Tú", true, LocalDate.of(2004, 3, 3),
                "012345678912", "0987654321", "Hồ Chí Minh", 1.0f, true, "image.jpg",
                new TaiKhoan("tuphung", "1234", null), TrangThaiLamViec.CON_LAM_VIEC);

        System.out.println("HoTen: " + nv.getHoTen());
        System.out.println("DiaChi: " + nv.getDiaChi());

        resultList.add(nv);
        return resultList;
    }

    private void searchEmployee() {
        String searchQuery = txtSearch.getText().trim();
        if (searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.setRowCount(0); // Reset bảng

        List<NhanVien> resultList = getSampleData();
        List<NhanVien> filteredList = new ArrayList<>();
        for (NhanVien nv : resultList) {
            // Kiểm tra tên và mã nhân viên trong kết quả tìm kiếm
            if (nv.getMaNhanVien().contains(searchQuery) || nv.getHoTen().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredList.add(nv);
            }
        }

        if (!filteredList.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (NhanVien emp : filteredList) {
                String hoTen = emp.getHoTen() != null ? emp.getHoTen() : "Chưa có tên";
                String diaChi = emp.getDiaChi() != null ? emp.getDiaChi() : "Chưa có địa chỉ";

                tableModel.addRow(new Object[] {
                        emp.getMaNhanVien(),
                        hoTen,  // Hiển thị họ tên
                        emp.isGioiTinh() ? "Nam" : "Nữ",
                        emp.getNgaySinh() != null ? emp.getNgaySinh().format(formatter) : "N/A",
                        emp.getSoCanCuoc(),
                        emp.getSoDienThoai(),
                        diaChi,  // Hiển thị địa chỉ
                        emp.getHeSoLuong(),
                        emp.getTrangThaiLamViec() == TrangThaiLamViec.CON_LAM_VIEC ? "Còn làm việc" : "Nghỉ việc"
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TraCuuNhanVienView frame = new TraCuuNhanVienView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
