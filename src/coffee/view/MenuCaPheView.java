package coffee.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import coffee.dao.Database;
import java.sql.*;
import coffee.controller.MenuCaPheController;
public class MenuCaPheView extends JFrame {
    private Map<String, String> hinhAnhSanPham = new HashMap<>();
    private String sanPhamDuocChon;

    public MenuCaPheView() {
        setTitle("Quản Lý Menu Cà Phê");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Thêm đường dẫn ảnh cho từng sản phẩm
        hinhAnhSanPham.put("Americano-50.000(VND)", "src/imageCoffee/CAFE AMERICANO.PNG");
        hinhAnhSanPham.put("Miso-40.000(VND)", "src/imageCoffee/CAFE MISTO.PNG");
        hinhAnhSanPham.put("Mocha-73.000(VND)", "src/imageCoffee/CAFE MOCHA.PNG");
        hinhAnhSanPham.put("Latte-80.000(VND)", "src/imageCoffee/CAFFE LATTE.PNG");
        hinhAnhSanPham.put("Capuchino-60.000(VND)", "src/imageCoffee/CAPPUCCINO.PNG");
        hinhAnhSanPham.put("Caramelmachiato-63.000(VND)", "src/imageCoffee/CARAMEL MACCHIATO.PNG");
        hinhAnhSanPham.put("Cinnamondolce-68.000(VND)", "src/imageCoffee/CINNAMON DOLCE LATTE.PNG");
        hinhAnhSanPham.put("EsperoMacchiato-100.000(VND)", "src/imageCoffee/ESPRESO  MACCHIATO.PNG");
        hinhAnhSanPham.put("EsperoConPanna-100.000(VND)", "src/imageCoffee/ESPRESO CON PANNA.PNG");
        hinhAnhSanPham.put("Espero-45.000(VND)", "src/imageCoffee/ESPRESO.PNG");
       
       
        hinhAnhSanPham.put("Flatwhite-85.000(VND)", "src/imageCoffee/FLAT WHITE.PNG");
        hinhAnhSanPham.put("Honeyflatwhite-35.000(VND)", "src/imageCoffee/HONEY ALMONDMILK FLAT WHITE.PNG");
        hinhAnhSanPham.put("PumpkinSpiceLatte-100.000(VND)", "src/imageCoffee/PUMPKIN SPICE LATTE.PNG");
        
      
     
       
        hinhAnhSanPham.put("Roastcoffee-69.000(VND)", "src/imageCoffee/ROAST COFFEE.PNG");
        
        hinhAnhSanPham.put("Whitechocolatemocha-52.000(VND)", "src/imageCoffee/WHITE CHOCOLATE MOCHA.PNG");

        // Tạo panel tiêu đề
        JPanel tieuDePanel = new JPanel();
        tieuDePanel.setBackground(new Color(92, 64, 51));
        JLabel tieuDeLabel = new JLabel("MENU COFFEE OPTION - 3COFFEE");
        tieuDeLabel.setForeground(Color.WHITE);
        tieuDeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        tieuDePanel.add(tieuDeLabel);
        add(tieuDePanel, BorderLayout.NORTH);

        // Panel các sản phẩm
        JPanel sanPhamPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        sanPhamPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        String[] tenSanPham = {
            "Americano-50.000(VND)", "Miso-40.000(VND)", "Mocha-73.000(VND)", 
            "Latte-80.000(VND)","Capuchino-60.000(VND)", "Caramelmachiato-63.000(VND)", 
            "Cinnamondolce-68.000(VND)", "EsperoMacchiato-100.000(VND)", "EsperoConPanna-100.000(VND)", 
            "Espero-45.000(VND)", "Flatwhite-85.000(VND)","Honeyflatwhite-35.000(VND)", 
            "PumpkinSpiceLatte-100.000(VND)", "Roastcoffee-69.000(VND)","Whitechocolatemocha-52.000(VND)"
        };

        for (String sanPham : tenSanPham) {
            JPanel itemPanel = taoPanelSanPham(sanPham);
            sanPhamPanel.add(itemPanel);
        }

        // Panel điều khiển
        JPanel dieuKhienPanel = new JPanel();
        dieuKhienPanel.setLayout(new BoxLayout(dieuKhienPanel, BoxLayout.Y_AXIS));
        dieuKhienPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTieuDe = new JLabel("Coffee Management");
        lblTieuDe.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 24));
        lblTieuDe.setForeground(new Color(139, 69, 19));
        lblTieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
        dieuKhienPanel.add(lblTieuDe);

        // Nút điều khiển
        JButton btnThemSanPham = taoNut("Thêm Món", new Color(139, 69, 19));
        JButton btnSuaSanPham = taoNut("Sửa Món", new Color(139, 69, 19));

        btnThemSanPham.addActionListener(e -> themSanPham());
        btnSuaSanPham.addActionListener(e -> suaSanPham());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(btnThemSanPham);
        buttonPanel.add(btnSuaSanPham);
        dieuKhienPanel.add(buttonPanel);

        add(sanPhamPanel, BorderLayout.CENTER);
        add(dieuKhienPanel, BorderLayout.EAST);
    }

    private JPanel taoPanelSanPham(String sanPham) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(sanPham));
        panel.setPreferredSize(new Dimension(180, 220));

        String duongDanAnh = hinhAnhSanPham.get(sanPham);
        if (duongDanAnh != null) {
            ImageIcon iconSanPham = new ImageIcon(new ImageIcon(duongDanAnh).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JLabel lblAnh = new JLabel(iconSanPham);
            lblAnh.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(lblAnh);
        } else {
            JLabel lblLoi = new JLabel("Ảnh không có sẵn");
            lblLoi.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(lblLoi);
        }

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanPhamDuocChon = sanPham;
            }
        });

        return panel;
    }

    private JButton taoNut(String text, Color color) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void themSanPham() {
        if (sanPhamDuocChon != null) {
            try (Connection conn = Database.getInstance().getConnection()) {
                String sql = "INSERT INTO SanPham (MaSanPham, TenSanPham, LoaiSanPham, DonGia, ThanhPhan, MoTa, Con, LaDoAn, HinhAnh) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                // Tách thông tin sản phẩm từ sanPhamDuocChon
                String[] parts = sanPhamDuocChon.split("-");
                String tenSanPham = parts[0].trim();
                String giaStr = parts[1].replaceAll("\\D+", "").trim();
                int gia = Integer.parseInt(giaStr);

                // Mã sản phẩm tự động với độ dài tối đa là 5 ký tự
                String maSanPham = "SP" + System.currentTimeMillis() % 10000; // Sinh mã tự động
                maSanPham = maSanPham.length() > 5 ? maSanPham.substring(0, 5) : maSanPham; // Giới hạn độ dài mã sản phẩm không quá 5 ký tự
                String loaiSanPham = "Cà phê"; // Có thể lấy từ giao diện
                String thanhPhan = "Mặc định"; // Có thể nhập từ giao diện
                String anh = hinhAnhSanPham.get(sanPhamDuocChon);
                boolean hienThi = true;
                boolean khuyenMai = false;
                String maVietTat = tenSanPham.replaceAll(" ", "");

                ps.setString(1, maSanPham);
                ps.setString(2, tenSanPham);
                ps.setString(3, loaiSanPham);
                ps.setInt(4, gia);
                ps.setString(5, thanhPhan);
                ps.setString(6, anh);
                ps.setBoolean(7, hienThi);
                ps.setBoolean(8, khuyenMai);
                ps.setString(9, maVietTat);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm món " + tenSanPham + " thành công!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm món vào cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món trước khi thêm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    



    private void suaSanPham() {
        if (sanPhamDuocChon != null) {
            JOptionPane.showMessageDialog(this, "Sửa thông tin món " + sanPhamDuocChon);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món trước khi sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuCaPheView().setVisible(true));
    }
}
