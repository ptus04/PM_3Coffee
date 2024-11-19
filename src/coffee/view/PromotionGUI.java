package coffee.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class PromotionGUI extends JFrame {
    JTextField txtIdPrd, txtNamePrd, txtPrice, txtIdPrmt, txtNgayHieuLuc,
    		txtNgayHetHan, txtTriGia, txtGhiChu, txtFind, txtTimTriGia;
    JComboBox<String> cbTypePrd;
    JButton btnAdd, btnEdit, btnDelete;
    JTable table;
    DefaultTableModel modelTable;
    
    public PromotionGUI() {
        JPanel pnProduct = new JPanel();
        pnProduct.setLayout(new BorderLayout());
        pnProduct = createPrmtArea();
        pnProduct.setBorder(BorderFactory.createTitledBorder("Khu vực sản phẩm"));

        JPanel pnManageProduct = new JPanel();
        pnManageProduct.setLayout(new BorderLayout());

        JLabel lbHeadingofManagePrd = new JLabel("Quản lý khuyến mãi");
        lbHeadingofManagePrd.setFont(new Font("Arial", Font.BOLD, 20));
        
        JPanel headingPanel = new JPanel();
        headingPanel.add(lbHeadingofManagePrd);
        
        pnManageProduct.add(headingPanel, BorderLayout.NORTH);
        
        pnManageProduct.add(createInputInfoPrmt(), BorderLayout.CENTER);

        pnManageProduct.setBorder(BorderFactory.createTitledBorder("Khu vực quản lý khuyến mãi"));
        
        add(pnProduct, BorderLayout.CENTER);
        add(pnManageProduct, BorderLayout.EAST);

        setTitle("Promotion GUI");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private JPanel createPrmtArea() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        JLabel lbFind = new JLabel("Tìm mã khuyến mãi: ");
        txtFind = new JTextField(50);
        txtFind.setPreferredSize(new Dimension(20, 30));
        JLabel lbTriGia = new JLabel("Tìm theo trị giá: ");
        txtTimTriGia = new JTextField(50);
        txtTimTriGia.setPreferredSize(new Dimension(20, 30));
        
        searchPanel.setLayout(new GridLayout(1,2,0,0));
        searchPanel.add(lbFind);
        searchPanel.add(txtFind);
        searchPanel.add(lbTriGia);
        searchPanel.add(txtTimTriGia);

        panel.add(searchPanel, BorderLayout.NORTH);

        String[] colName = "Mã khuyến mãi;Trị giá;Ngày hiệu hạn;Ngày hết hạn;Ghi chú".split(";");
        modelTable = new DefaultTableModel(colName, 0);

        table = new JTable(modelTable);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1000, 400));

        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createInputInfoPrmt() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Thêm khoảng cách giữa các phần tử

        // Mã sản phẩm
        JLabel lbIdPrd = new JLabel("Mã sản phẩm: ");
        txtIdPrd = new JTextField(20);
        lbIdPrd.setPreferredSize(new Dimension(120, 30));
        txtIdPrd.setPreferredSize(new Dimension(240, 30));
        txtIdPrd.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lbIdPrd, gbc);
        gbc.gridx = 1;
        panel.add(txtIdPrd, gbc);

        // Tên sản phẩm
        JLabel lbNamePrd = new JLabel("Tên sản phẩm: ");
        txtNamePrd = new JTextField(20);
        lbNamePrd.setPreferredSize(new Dimension(120, 30));
        txtNamePrd.setPreferredSize(new Dimension(240, 30));
        txtNamePrd.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lbNamePrd, gbc);
        gbc.gridx = 1;
        panel.add(txtNamePrd, gbc);

        // Loại sản phẩm
        JLabel lbTypePrd = new JLabel("Loại sản phẩm: ");
        String[] nameTypePrd = {"Cà phê", "Đồ uống"};
        cbTypePrd = new JComboBox<>(nameTypePrd);
        lbTypePrd.setPreferredSize(new Dimension(120, 30));
        cbTypePrd.setPreferredSize(new Dimension(240, 30));
        cbTypePrd.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lbTypePrd, gbc);
        gbc.gridx = 1;
        panel.add(cbTypePrd, gbc);

        // Đơn giá
        JLabel lbPrice = new JLabel("Đơn giá: ");
        txtPrice = new JTextField(20);
        lbPrice.setPreferredSize(new Dimension(120, 30));
        txtPrice.setPreferredSize(new Dimension(240, 30));
        txtPrice.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lbPrice, gbc);
        gbc.gridx = 1;
        panel.add(txtPrice, gbc);

        // Mã khuyến mãi
        JLabel lbIdPrmt = new JLabel("Mã khuyến mãi: ");
        txtIdPrmt = new JTextField(20);
        lbIdPrmt.setPreferredSize(new Dimension(120, 30));
        txtIdPrmt.setPreferredSize(new Dimension(240, 30));
        txtIdPrmt.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lbIdPrmt, gbc);
        gbc.gridx = 1;
        panel.add(txtIdPrmt, gbc);

        // Ngày hiệu lực
        JLabel lbNgayHieuLuc = new JLabel("Ngày hiệu lực: ");
        txtNgayHieuLuc = new JTextField(20);
        lbNgayHieuLuc.setPreferredSize(new Dimension(120, 30));
        txtNgayHieuLuc.setPreferredSize(new Dimension(240, 30));
        txtNgayHieuLuc.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lbNgayHieuLuc, gbc);
        gbc.gridx = 1;
        panel.add(txtNgayHieuLuc, gbc);

        // Ngày hết hạn
        JLabel lbNgayHetHan = new JLabel("Ngày hết hạn: ");
        txtNgayHetHan = new JTextField(20);
        lbNgayHetHan.setPreferredSize(new Dimension(120, 30));
        txtNgayHetHan.setPreferredSize(new Dimension(240, 30));
        txtNgayHetHan.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(lbNgayHetHan, gbc);
        gbc.gridx = 1;
        panel.add(txtNgayHetHan, gbc);

        // Trị giá
        JLabel lbTriGia = new JLabel("Trị Giá: ");
        txtTriGia = new JTextField(20);
        lbTriGia.setPreferredSize(new Dimension(120, 30));
        txtTriGia.setPreferredSize(new Dimension(240, 30));
        txtTriGia.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(lbTriGia, gbc);
        gbc.gridx = 1;
        panel.add(txtTriGia, gbc);

        // Ghi chú
        JLabel lbGhiChu = new JLabel("Ghi chú: ");
        txtGhiChu = new JTextField(20);
        lbGhiChu.setPreferredSize(new Dimension(120, 30));
        txtGhiChu.setPreferredSize(new Dimension(240, 30));
        txtGhiChu.setMaximumSize(new Dimension(240, 30)); // Đảm bảo không bị co lại
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(lbGhiChu, gbc);
        gbc.gridx = 1;
        panel.add(txtGhiChu, gbc);

        // Tạo JPanel chứa các nút Thêm, Sửa, Xóa
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Canh giữa với khoảng cách giữa các nút

        // Tạo các nút
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");

        // Thêm các nút vào panel
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        // Thêm panel nút vào cuối của JPanel
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2; // Chiếm toàn bộ chiều ngang
        panel.add(btnPanel, gbc);

        return panel;
    }



    public static void main(String[] args) {
        new PromotionGUI().setVisible(true);
    }
}
