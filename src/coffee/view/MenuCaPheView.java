package coffee.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MenuCaPheView extends JFrame {
    private Map<String, Integer> soLuongSanPham = new HashMap<>();
    private JTextField txtSoLuong;
    private Map<String, String> hinhAnhSanPham = new HashMap<>();
    private String sanPhamDuocChon;

    public MenuCaPheView() {
        setTitle("Quản Lý Menu Cà Phê");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Thêm đường dẫn ảnh cho từng sản phẩm
        hinhAnhSanPham.put("Americano-50.000(VND)", "src/imageCoffee/cafeamericano.jpg");
        hinhAnhSanPham.put("Capuchino-60.000(VND)", "src/imageCoffee/cappuchino.jpg");
        hinhAnhSanPham.put("Caramelmachiato-63.000(VND)", "src/imageCoffee/caramelmachiato.jpg");
        hinhAnhSanPham.put("Cinnamondolce-68.000(VND)", "src/imageCoffee/cinnamondolce.jpg");
        hinhAnhSanPham.put("Espero-45.000(VND)", "src/imageCoffee/espero.jpg");
        hinhAnhSanPham.put("Esprerolatte-75.000(VND)", "src/imageCoffee/esprerolatte.jpg");
        hinhAnhSanPham.put("Flatwhite-85.000(VND)", "src/imageCoffee/flatwhite.jpg");
        hinhAnhSanPham.put("Honeyflatwhite-35.000(VND)", "src/imageCoffee/honeyflatwhite.jpg");
        hinhAnhSanPham.put("Latte-80.000(VND)", "src/imageCoffee/latte.jpg");
        hinhAnhSanPham.put("Miso-40.000(VND)", "src/imageCoffee/misto.jpg");
        hinhAnhSanPham.put("Mocha-73.000(VND)", "src/imageCoffee/mocha.jpg");
        hinhAnhSanPham.put("Roastcoffee-69.000(VND)", "src/imageCoffee/roastcoffee.jpg");
        hinhAnhSanPham.put("Spicelatte-49.000(VND)", "src/imageCoffee/spicelatte.jpg");
        hinhAnhSanPham.put("Whitechocolatelatte-52.000(VND)", "src/imageCoffee/whitechocolatelatte.jpg");

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
            "Americano-50.000(VND)", "Capuchino-60.000(VND)", "Caramelmachiato-63.000(VND)", 
            "Cinnamondolce-68.000(VND)", "Espero-45.000(VND)", "Esprerolatte-75.000(VND)", 
            "Flatwhite-85.000(VND)", "Honeyflatwhite-35.000(VND)", "Latte-80.000(VND)", 
            "Miso-40.000(VND)", "Mocha-73.000(VND)", "Roastcoffee-69.000(VND)", 
            "Spicelatte-49.000(VND)", "Whitechocolatelatte-52.000(VND)"
        };

        for (String sanPham : tenSanPham) {
            soLuongSanPham.put(sanPham, 0);
            JPanel itemPanel = taoPanelSanPham(sanPham);
            sanPhamPanel.add(itemPanel);
        }

        // Panel điều khiển
        JPanel dieuKhienPanel = new JPanel();
        dieuKhienPanel.setLayout(new BoxLayout(dieuKhienPanel, BoxLayout.Y_AXIS));
        dieuKhienPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTieuDe = new JLabel("Quản Lý Đơn Hàng");
        lblTieuDe.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 24));
        lblTieuDe.setForeground(new Color(139, 69, 19));
        lblTieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
        dieuKhienPanel.add(lblTieuDe);

        dieuKhienPanel.add(Box.createVerticalStrut(10));
        dieuKhienPanel.add(taoInputField("Số lượng:", txtSoLuong = new JTextField(30)));

        // Nút điều khiển
        JButton btnThemSanPham = taoNut("Thêm Món", new Color(139, 69, 19));
        JButton btnXoaSanPham = taoNut("Xóa Món", new Color(139, 69, 19));
        JButton btnSuaSanPham = taoNut("Sửa Món", new Color(139, 69, 19));

        btnThemSanPham.addActionListener(e -> themSanPham());
        btnXoaSanPham.addActionListener(e -> xoaSanPham());
        btnSuaSanPham.addActionListener(e -> suaSoLuongSanPham());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.add(btnThemSanPham);
        buttonPanel.add(btnXoaSanPham);
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
                txtSoLuong.setText(String.valueOf(soLuongSanPham.get(sanPhamDuocChon)));
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton btnThem = new JButton("+");
        JButton btnTru = new JButton("-");

        btnThem.addActionListener(e -> capNhatSoLuong(sanPham, 1));
        btnTru.addActionListener(e -> capNhatSoLuong(sanPham, -1));

        buttonPanel.add(btnThem);
        buttonPanel.add(btnTru);
        panel.add(buttonPanel);

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

    private JPanel taoInputField(String label, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(textField);
        textField.setEditable(true);
        return panel;
    }

    private void capNhatSoLuong(String sanPham, int soLuong) {
        int soLuongHienTai = soLuongSanPham.get(sanPham);
        int soLuongMoi = Math.max(0, soLuongHienTai + soLuong);
        soLuongSanPham.put(sanPham, soLuongMoi);
        if (sanPham.equals(sanPhamDuocChon)) {
            txtSoLuong.setText(String.valueOf(soLuongMoi));
        }
    }

    private void themSanPham() {
        if (sanPhamDuocChon != null) {
            try {
                int soLuong = Integer.parseInt(txtSoLuong.getText());
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int soLuongHienTai = soLuongSanPham.getOrDefault(sanPhamDuocChon, 0);
                soLuongSanPham.put(sanPhamDuocChon, soLuongHienTai + soLuong);
                JOptionPane.showMessageDialog(this, "Đã thêm " + soLuong + " " + sanPhamDuocChon + ".");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món để thêm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void xoaSanPham() {
        if (sanPhamDuocChon != null) {
            soLuongSanPham.remove(sanPhamDuocChon);
            txtSoLuong.setText("");
            JOptionPane.showMessageDialog(this, "Đã xóa " + sanPhamDuocChon + " khỏi menu.");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void suaSoLuongSanPham() {
        if (sanPhamDuocChon != null) {
            try {
                int soLuong = Integer.parseInt(txtSoLuong.getText());
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                soLuongSanPham.put(sanPhamDuocChon, soLuong);
                JOptionPane.showMessageDialog(this, "Đã cập nhật số lượng " + sanPhamDuocChon + " thành " + soLuong + ".");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món để sửa số lượng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuCaPheView view = new MenuCaPheView();
            view.setVisible(true);
        });
    }
}
