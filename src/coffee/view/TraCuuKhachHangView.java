package coffee.view;

import coffee.controller.KhachHangController;
import coffee.dao.KhachHangDAO;
import coffee.entity.KhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TraCuuKhachHangView extends JFrame {

    private JTextField txtSearch;
    private JButton btnSearch, btnEditName;
    private JTable table;
    private DefaultTableModel tableModel;
    private KhachHangDAO khachHangDAO;

    public TraCuuKhachHangView() {
        setTitle("Quản Lý Khách Hàng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        khachHangDAO = KhachHangDAO.getInstance();

        getContentPane().setBackground(new Color(139, 69, 19));

        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(new Color(139, 69, 19));

        JLabel lblSearch = new JLabel("Nhập mã khách hàng hoặc tên:");
        lblSearch.setForeground(Color.WHITE);
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        btnEditName = new JButton("Sửa tên");

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
            }
        });

        btnEditName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCustomerName();
            }
        });

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnEditName);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã Khách Hàng", "Họ Tên", "Số Điện Thoại", "Địa Chỉ"});
        table = new JTable(tableModel);
        table.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2));

        table.setBackground(new Color(222, 184, 135));
        table.setGridColor(new Color(139, 69, 19));

        JScrollPane tableScrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        displayCustomerData();
    }
 // Phương thức để hiển thị lại dữ liệu sau khi sửa
    public void updateTableAfterEdit(String customerId, String newName) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String id = tableModel.getValueAt(i, 0).toString();
            if (id.equals(customerId)) {
                tableModel.setValueAt(newName, i, 1);
                JOptionPane.showMessageDialog(this, "Tên khách hàng đã được cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    // Phương thức hiển thị thông báo lỗi
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }


    public void displayCustomerData() {
        try {
            List<KhachHang> resultList = khachHangDAO.getAll();
            for (KhachHang customer : resultList) {
                tableModel.addRow(new Object[]{
                    customer.getSoDienThoai(),
                    customer.getTenKhachHang(),
                    customer.getSoDienThoai(),
                    "Địa chỉ không có sẵn"
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể tải dữ liệu từ cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void searchCustomer() {
        String searchQuery = txtSearch.getText().trim();
        if (searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.setRowCount(0);

        try {
            List<KhachHang> filteredList = khachHangDAO.getById(searchQuery) != null ? List.of(khachHangDAO.getById(searchQuery)) : List.of();
            if (!filteredList.isEmpty()) {
                for (KhachHang customer : filteredList) {
                    tableModel.addRow(new Object[]{
                        customer.getSoDienThoai(),
                        customer.getTenKhachHang(),
                        customer.getSoDienThoai(),
                        "Địa chỉ không có sẵn"
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi khi tìm kiếm trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCustomerName() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String oldName = tableModel.getValueAt(selectedRow, 1).toString();
            String newName = JOptionPane.showInputDialog(this, "Nhập tên mới cho khách hàng:", oldName);

            if (newName != null && !newName.trim().isEmpty()) {
                String customerId = tableModel.getValueAt(selectedRow, 0).toString();

                try {
                    KhachHang customer = khachHangDAO.getById(customerId);
                    if (customer != null) {
                        customer.setTenKhachHang(newName);
                        khachHangDAO.capNhat(customer);
                        tableModel.setValueAt(newName, selectedRow, 1);
                        JOptionPane.showMessageDialog(this, "Tên khách hàng đã được cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Có lỗi khi cập nhật tên trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
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
                    KhachHangController controller = new KhachHangController(frame); // Tạo đối tượng controller
                    frame.setVisible(true);
                    
                    // Kết nối sự kiện với controller
                    frame.btnSearch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            controller.searchCustomer(frame.txtSearch.getText().trim());
                        }
                    });

                    frame.btnEditName.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int selectedRow = frame.table.getSelectedRow();
                            if (selectedRow != -1) {
                                String oldName = frame.tableModel.getValueAt(selectedRow, 1).toString();
                                String customerId = frame.tableModel.getValueAt(selectedRow, 0).toString();
                                String newName = JOptionPane.showInputDialog(frame, "Nhập tên mới cho khách hàng:", oldName);
                                if (newName != null) {
                                    controller.editCustomerName(oldName, newName, customerId);
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame, "Vui lòng chọn một khách hàng để sửa tên.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
