package coffee.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TraCuuKhachHangView extends JFrame {

    private JTextField txtSearch;
    private JButton btnSearch, btnEditName;
    private JTable table;
    private DefaultTableModel tableModel;

    public TraCuuKhachHangView() {
        // Cấu hình JFrame
        setTitle("Quản Lý  Khách Hàng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Thêm màu nền cho giao diện
        getContentPane().setBackground(new Color(139, 69, 19)); // Màu nâu đất

        // Layout chính của panel
        setLayout(new BorderLayout());

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(new Color(139, 69, 19)); // Màu nâu đất cho panel

        JLabel lblSearch = new JLabel("Nhập mã khách hàng hoặc tên:");
        lblSearch.setForeground(Color.WHITE); // Màu chữ trắng
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        btnEditName = new JButton("Sửa tên");

        // Lắng nghe sự kiện khi người dùng nhấn nút Tìm kiếm
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
            }
        });

        // Lắng nghe sự kiện khi người dùng nhấn nút Sửa tên
        btnEditName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCustomerName();
            }
        });

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnEditName);

        // Tạo bảng để hiển thị kết quả tìm kiếm
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã Khách Hàng", "Họ Tên", "Số Điện Thoại", "Địa Chỉ"});
        table = new JTable(tableModel);
        table.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2));  // Màu cà phê cho khung

        // Thay đổi màu nền của bảng và viền của các ô
        table.setBackground(new Color(222, 184, 135)); // Màu nền cà phê nhạt cho bảng
        table.setGridColor(new Color(139, 69, 19)); // Màu viền của các ô (màu cà phê)

        // Thêm bảng vào JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Thêm các thành phần vào panel chính
        add(searchPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Hiển thị dữ liệu mẫu vào bảng khi khởi tạo
        displaySampleData();
    }

    // Phương thức hiển thị dữ liệu mẫu vào bảng
    private void displaySampleData() {
        List<Object[]> resultList = getSampleData();

        // Hiển thị dữ liệu vào bảng
        for (Object[] customer : resultList) {
            tableModel.addRow(customer);
        }
    }

    // Phương thức lấy dữ liệu mẫu
    private List<Object[]> getSampleData() {
        List<Object[]> resultList = new ArrayList<>();
        resultList.add(new Object[]{"KH000001", "Nguyễn Thị Mai", "0901234567", "Hà Nội"});
        resultList.add(new Object[]{"KH000002", "Lê Minh Tuấn", "0987654321", "Đà Nẵng"});
        resultList.add(new Object[]{"KH000003", "Trần Thị Lan", "0912345678", "Hồ Chí Minh"});
        return resultList;
    }

    // Phương thức tìm kiếm khách hàng
    private void searchCustomer() {
        String searchQuery = txtSearch.getText().trim();
        if (searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Xóa dữ liệu cũ trong bảng trước khi hiển thị kết quả mới
        tableModel.setRowCount(0);

        List<Object[]> resultList = getSampleData(); // Lấy dữ liệu mẫu

        // Lọc kết quả tìm kiếm theo mã khách hàng hoặc tên
        List<Object[]> filteredList = new ArrayList<>();
        for (Object[] customer : resultList) {
            if (customer[0].toString().contains(searchQuery) || customer[1].toString().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredList.add(customer);
            }
        }

        // Hiển thị kết quả tìm kiếm lên bảng
        if (!filteredList.isEmpty()) {
            for (Object[] customer : filteredList) {
                tableModel.addRow(customer);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Phương thức sửa tên khách hàng
    private void editCustomerName() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String oldName = tableModel.getValueAt(selectedRow, 1).toString();
            String newName = JOptionPane.showInputDialog(this, "Nhập tên mới cho khách hàng:", oldName);

            if (newName != null && !newName.trim().isEmpty()) {
                tableModel.setValueAt(newName, selectedRow, 1); // Cập nhật tên mới vào bảng
                JOptionPane.showMessageDialog(this, "Tên khách hàng đã được cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Tên không hợp lệ.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để sửa tên.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TraCuuKhachHangView frame = new TraCuuKhachHangView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
