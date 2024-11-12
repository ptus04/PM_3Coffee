package coffee.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MenuCaPheView extends JFrame {
    private Map<String, Integer> productQuantities = new HashMap<>();
    private JTextField txtQuantity;
    private Map<String, String> productImages = new HashMap<>();

    public MenuCaPheView() {
        setTitle("Quản Lý Menu Cà Phê");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Thêm đường dẫn ảnh cho từng sản phẩm
        productImages.put("Americano-50.000(VND)", "src/imageCoffee/cafeamericano.jpg");
        productImages.put("Capuchino-60.000(VND)", "src/imageCoffee/cappuchino.jpg");
        productImages.put("Caramelmachiato-63.000(VND)", "src/imageCoffee/caramelmachiato.jpg");
        productImages.put("Cinnamondolce-68.000(VND)", "src/imageCoffee/cinnamondolce.jpg");
        productImages.put("Espero-45.000(VND)", "src/imageCoffee/espero.jpg");
        productImages.put("Esprerolatte-75.000(VND)", "src/imageCoffee/esprerolatte.jpg");
        productImages.put("Flatwhite-85.000(VND)", "src/imageCoffee/flatwhite.jpg");
        productImages.put("Honeyflatwhite-35.000(VND)", "src/imageCoffee/honeyflatwhite.jpg");
        productImages.put("Latte-80.000(VND)", "src/imageCoffee/latte.jpg");
        productImages.put("Miso-40.000(VND)", "src/imageCoffee/misto.jpg");
        productImages.put("Mocha-73.000(VND)", "src/imageCoffee/mocha.jpg");
        productImages.put("Roastcoffee-69.000(VND)", "src/imageCoffee/roastcoffee.jpg");
        productImages.put("Spicelatte-49.000(VND)", "src/imageCoffee/spicelatte.jpg");
        productImages.put("Whitechocolatelatte-52.000(VND)", "src/imageCoffee/whitechocolatelatte.jpg");

        // Tạo panel tiêu đề với màu nền nâu đậm và font chữ lớn
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(92, 64, 51));
        JLabel titleLabel = new JLabel("MENU COFFEE OPTION - 3COFFEE");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Panel các sản phẩm
        JPanel productPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        productPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        String[] productNames = {
            "Americano-50.000(VND)", "Capuchino-60.000(VND)", "Caramelmachiato-63.000(VND)", 
            "Cinnamondolce-68.000(VND)", "Espero-45.000(VND)", "Esprerolatte-75.000(VND)", 
            "Flatwhite-85.000(VND)", "Honeyflatwhite-35.000(VND)", "Latte-80.000(VND)", 
            "Miso-40.000(VND)", "Mocha-73.000(VND)", "Roastcoffee-69.000(VND)", 
            "Spicelatte-49.000(VND)", "Whitechocolatelatte-52.000(VND)"
        };

        for (String productName : productNames) {
            productQuantities.put(productName, 0);
            JPanel itemPanel = createProductPanel(productName);
            productPanel.add(itemPanel);
        }
        
        // Panel thanh toán và điều khiển
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("Mangement Order");
        lblTitle.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 24));
        lblTitle.setForeground(new Color(139, 69, 19));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(lblTitle);

        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(createInputField("Số lượng:", txtQuantity = new JTextField(30)));
        
        
        


        // Nút điều khiển được cải thiện
        JButton btnAddProduct = createStyledButton("Thêm Món", new Color(139, 69, 19));
        JButton btnRemoveProduct = createStyledButton("Xóa Món", new Color(139, 69, 19));
        JButton btnUpdateProduct = createStyledButton("Sửa Món", new Color(139, 69, 19));



        btnAddProduct.addActionListener(e -> addProduct());
        btnRemoveProduct.addActionListener(e -> removeProduct());
        btnUpdateProduct.addActionListener(e -> updateProductQuantity());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.add(btnAddProduct);
        buttonPanel.add(btnRemoveProduct);
        buttonPanel.add(btnUpdateProduct);
        controlPanel.add(buttonPanel);

        add(productPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);
    }

    private JPanel createProductPanel(String productName) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(productName));
        panel.setPreferredSize(new Dimension(180, 220));

        String imagePath = productImages.get(productName);
        if (imagePath != null) {
            ImageIcon productIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JLabel lblImage = new JLabel(productIcon);
            lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(lblImage);
        } else {
            JLabel lblError = new JLabel("Ảnh không có sẵn");
            lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(lblError);
        }

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("+");
        JButton btnSubtract = new JButton("-");

        btnAdd.addActionListener(e -> updateQuantity(productName, 1));
        btnSubtract.addActionListener(e -> updateQuantity(productName, -1));

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSubtract);
        panel.add(buttonPanel);

        return panel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private JPanel createInputField(String label, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(textField);
        textField.setEditable(true);
        return panel;
    }

    private void updateQuantity(String productName, int amount) {
        int currentQuantity = productQuantities.get(productName);
        int newQuantity = Math.max(0, currentQuantity + amount);
        productQuantities.put(productName, newQuantity);
        txtQuantity.setText(String.valueOf(newQuantity));
    }

    private void addProduct() {
        // Mã cho nút Thêm Món
    }

    private void removeProduct() {
        // Mã cho nút Xóa Món
    }

    private void updateProductQuantity() {
        // Mã cho nút Sửa Số Lượng
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuCaPheView frame = new MenuCaPheView();
            frame.setVisible(true);
        });
    }
}
