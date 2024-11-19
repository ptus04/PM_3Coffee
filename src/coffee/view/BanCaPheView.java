package coffee.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import coffee.controller.BanCaPheController;
import coffee.controller.SwitchJPanelController;
import coffee.dao.SanPhamDAO;
import coffee.gui.CoffeeTextField;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanCaPheView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private CoffeeTextField txtTotal;
    private CoffeeTextField txtToTalAfterClickPay;
    private CoffeeTextField txtInputCash;
    private DefaultTableModel modeltable;
    private JPanel drinksPanel;
    private JPanel foodPanel;
    private JPanel calculatorPanel;
    private static List<PrimaryButton> drinkButtons;
    private static List<SecondaryButton> toolButtons;
    private static List<SecondaryButton> toolButtonsPayment;
    private List<JButton> foodButtons;
    private ArrayList<String> idPrd = null;
    private JPanel pnSwitchEastPanel;
    private CardLayout clPayment;
    private JPanel pnSwitchToolButtonsPanel;
    private CardLayout clToolButtons;
    private SecondaryButton btnBack;
    private PrimaryButton btnPay;
    private JTextArea txtGhiChu;
    private SecondaryButton btnInputInfoCustomer;

    public List<PrimaryButton> getDrinkButton() {
        return drinkButtons;
    }

    public DefaultTableModel getTableModel() {
        return modeltable;
    }

    public JTable getTable() {
        return table;
    }
    
    public JTextField getTxtTotal() {
    	return txtTotal;
    }

    public List<SecondaryButton> getToolButtons(){
    	return toolButtons;
    }
    public List<SecondaryButton> getToolButtonsPayment(){
    	return toolButtonsPayment;
    }
    public CardLayout getCardLayoutPayment() {
    	return clPayment;
    }
    public JPanel getSwitchEastPanel() {
    	return pnSwitchEastPanel;
    }
    public CardLayout getCardLayoutToolButtons() {
    	return clToolButtons;
    }
    public JPanel getSwitchToolButtonsPanel() {
    	return pnSwitchToolButtonsPanel;
    }
    public JTextArea getGhiChu() {
    	return txtGhiChu;
    }
    public SecondaryButton getButtonBack() {
    	return btnBack;
    }
    public CoffeeTextField getTotalAfterClickPay() {
    	return txtToTalAfterClickPay;
    }
    public SecondaryButton getInfoCustomer() {
    	return btnInputInfoCustomer;
    }
    public CoffeeTextField getKhachTra() {
    	return txtInputCash;
    }
    public PrimaryButton PayMent() {
    	return btnPay;
    }
    public BanCaPheView() throws SQLException {
        setTitle("POS Interface");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        clToolButtons = new CardLayout();
        pnSwitchToolButtonsPanel = new JPanel(clToolButtons);
        pnSwitchToolButtonsPanel.add(createCalculatorPanel(), "Calculator");
        pnSwitchToolButtonsPanel.add(creatToolPayment(), "Tool Payment Buttons");
        
        
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Khu vực bán hàng"));

        orderPanel.add(createOrderTablePanel(), BorderLayout.CENTER);
        orderPanel.add(pnSwitchToolButtonsPanel, BorderLayout.SOUTH);
        add(orderPanel, BorderLayout.CENTER);

        clPayment = new CardLayout();
        pnSwitchEastPanel = new JPanel(clPayment);
        pnSwitchEastPanel.setBorder(BorderFactory.createTitledBorder("Khu vực thay đổi"));
        pnSwitchEastPanel.add(createProductTabsPanel(), "Panel Product");
        pnSwitchEastPanel.add(createPayment(), "Panel Payment");
        add(pnSwitchEastPanel, BorderLayout.EAST);
        
        

    }

    private JPanel createOrderTablePanel() {
        JLabel lbCol1 = new JLabel("Thứ tự");
        JLabel lbCol2 = new JLabel("Sản phẩm");
        JLabel lbCol3 = new JLabel("Số lượng");
        JLabel lbCol4 = new JLabel("Đơn giá");

        String[] cols = { lbCol1.getText(), lbCol2.getText(), lbCol3.getText(), lbCol4.getText() };
        modeltable = new DefaultTableModel(cols, 0);

        table = new JTable(modeltable);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(scrollPane));

        table.setRowHeight(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        return listPanel;
    }

    private JPanel createProductTabsPanel() throws SQLException {
        JPanel productPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        idPrd = SanPhamDAO.getInstance().getID();
        drinkButtons = new ArrayList<>();
        drinksPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        int i = 1;
        for (String ma : idPrd) {
            PrimaryButton drinkButton = new PrimaryButton("Drink " + i);
            drinkButton.setName(ma);
            drinkButtons.add(drinkButton);
            drinksPanel.add(drinkButton);
            i++;
        }
        JScrollPane drinksScrollPane = new JScrollPane(drinksPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        drinksScrollPane.setPreferredSize(new Dimension(400, 400));
        tabbedPane.addTab("Đồ Uống", drinksScrollPane);

        foodButtons = new ArrayList<>();
        foodPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        for (i = 1; i <= 16; i++) {
            JButton foodButton = new JButton("Food " + i);
            foodButtons.add(foodButton);
            foodPanel.add(foodButton);
        }
        JScrollPane foodScrollPane = new JScrollPane(foodPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        foodScrollPane.setPreferredSize(new Dimension(400, 400));
        tabbedPane.addTab("Đồ Ăn", foodScrollPane);

        productPanel.add(tabbedPane, BorderLayout.CENTER);
        return productPanel;
    }

    private JPanel createCalculatorPanel() {
        calculatorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        calculatorPanel.add(txtTotal = new CoffeeTextField(70), gbc);
        txtTotal.setEditable(false);
        txtTotal.setPreferredSize(new Dimension(50,50));
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        calculatorPanel.add(btnInputInfoCustomer = new SecondaryButton("Nhập thông tin khách hàng"), gbc);
        btnInputInfoCustomer.setPreferredSize(new Dimension(75,75));
        
        gbc.gridwidth = 1;
        
        String[] buttons = { "1", "2", "3", "Delete All", "4", "5", "6", "Delete", "7", "8", "9", "Quantity", "C", "0", "Trở về", "Pay" };

        int x = 0;
        int y = 2;
        toolButtons = new ArrayList<SecondaryButton>();
        for (String text : buttons) {
            SecondaryButton button = new SecondaryButton(text);
            toolButtons.add(button);
            button.setPreferredSize(new Dimension(75, 75));
            gbc.gridx = x;
            gbc.gridy = y;
            calculatorPanel.add(button, gbc);
            x++;
            if (x == 4) {
                x = 0;
                y++;
            }
        }
        return calculatorPanel;
    }
    //////////////////////////////////////////////////////////////////////
    private JPanel creatToolPayment() {
    	JPanel pnTools = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        pnTools.add(txtToTalAfterClickPay = new CoffeeTextField(70), gbc);
        txtToTalAfterClickPay.setEditable(false);
        txtToTalAfterClickPay.setPreferredSize(new Dimension(50,50));
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel lbInputCash = new JLabel("Nhập tiền: ");
        lbInputCash.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pnTools.add(lbInputCash, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        pnTools.add(txtInputCash = new CoffeeTextField(20), gbc);
        txtInputCash.setPreferredSize(new Dimension(50,50));
        
        gbc.gridwidth = 1;
        
        String[] buttons = { "1", "2", "3", "Cash", "4", "5", "6", "Banking", "7", "8", "9", "Voucher", "C", "0", "500", "Enter" };

        int x = 0;
        int y = 2;
        toolButtonsPayment = new ArrayList<SecondaryButton>();
        for (String text : buttons) {
            SecondaryButton button = new SecondaryButton(text);
            toolButtonsPayment.add(button);
            button.setPreferredSize(new Dimension(75, 75));
            gbc.gridx = x;
            gbc.gridy = y;
            pnTools.add(button, gbc);
            x++;
            if (x == 4) {
                x = 0;
                y++;
            }
        }
        return pnTools;
    }

    public JPanel createPayment() {
    	JPanel pnAcouting = new JPanel();
    	pnAcouting.setLayout(new GridBagLayout());
        GridBagConstraints gbcAcouting = new GridBagConstraints();
        
        
        
        JPanel panelGridBaglayoutLeft = new JPanel();
        panelGridBaglayoutLeft.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 2, 2);
        
        String[] text = { "Số lượng sản phẩm", "Tổng tiền", "VAT", "Khách phải trả", "Tiền khách đưa", "Tiền thừa", "Ghi chú"};
        for( int i=0 ; i < text.length ; i++ ) {
        	JLabel lbText = new JLabel(text[i]);
        	lbText.setFont(new Font("Segoe UI", Font.BOLD, 15));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panelGridBaglayoutLeft.add(lbText, gbc);
        }
        
        
        JPanel panelGridBaglayoutRight = new JPanel();
        panelGridBaglayoutRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10, 5, 2, 2);
        
        String[] nulltext = { "null", "null", "null", "null", "null", "null", " "};
        for(int i=0 ; i < nulltext.length ; i++ ) {
        	JLabel lbNullText = new JLabel(nulltext[i]);
        	lbNullText.setFont(new Font("Segoe UI", Font.BOLD, 15));
            gbc2.gridx = 0;
            gbc2.gridy = i;
            gbc2.anchor = GridBagConstraints.LINE_END; 
            gbc2.weightx = 0; 
            gbc2.fill = GridBagConstraints.NONE;
            panelGridBaglayoutRight.add(lbNullText, gbc2);
        }
        
        
        JLabel dashLine = new JLabel("-----------------------------------------------------------------------------------------", JLabel.CENTER);
        dashLine.setFont(new Font( "", Font.BOLD, 12));
        gbcAcouting.gridy = 0;
        gbcAcouting.gridwidth = 2;
        gbcAcouting.anchor = GridBagConstraints.CENTER;
        pnAcouting.add(dashLine, gbcAcouting);
        
        gbcAcouting.gridx = 0;
        gbcAcouting.gridy = 1;
        gbcAcouting.anchor = GridBagConstraints.LINE_START;
        gbcAcouting.weightx = 1.0;  
        pnAcouting.add(panelGridBaglayoutLeft, gbcAcouting);
        
        gbcAcouting.gridx = 1;
        gbcAcouting.gridy = 1;
        gbcAcouting.anchor = GridBagConstraints.LINE_END;
        gbcAcouting.weightx = 0;
        pnAcouting.add(panelGridBaglayoutRight, gbcAcouting);
        
        txtGhiChu = new JTextArea();
        txtGhiChu.setPreferredSize(new Dimension(400, 200));
        txtGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbcAcouting.gridx = 0;
        gbcAcouting.gridy = 7; 
        gbcAcouting.gridwidth = 2;
        gbcAcouting.fill = GridBagConstraints.HORIZONTAL;
        pnAcouting.add(txtGhiChu, gbcAcouting);
        
        JLabel dashLineBelow = new JLabel("-----------------------------------------------------------------------------------------", JLabel.CENTER);
        dashLineBelow.setFont(new Font( "", Font.BOLD, 12));
        gbcAcouting.gridy = 8;
        gbcAcouting.gridwidth = 2;
        gbcAcouting.anchor = GridBagConstraints.CENTER;
        pnAcouting.add(dashLineBelow, gbcAcouting);
        
        JPanel pnButton = new JPanel();
        pnButton.setLayout(new GridLayout(1,2,5,5));
        
        JPanel pnBtnBack = new JPanel();
        btnBack = new SecondaryButton("Trở lại");
        btnBack.setPreferredSize(new Dimension(200,100));
        pnBtnBack.add(btnBack);
        
        JPanel pnBtnPay = new JPanel();
        btnPay = new PrimaryButton("Thanh toán");
        btnPay.setPreferredSize(new Dimension(200,100));
        pnBtnPay.add(btnPay);
        
        pnButton.add(pnBtnBack);
        pnButton.add(pnBtnPay);
        
        
        JPanel pnSetPosition = new JPanel();
        pnSetPosition.setLayout(new BorderLayout());
        pnSetPosition.add(pnAcouting, BorderLayout.NORTH);
        pnSetPosition.add(pnButton, BorderLayout.SOUTH);
        
        return pnSetPosition;
    }

    public static void main(String[] args) {
        try {
            BanCaPheView gui = new BanCaPheView();
            gui.setVisible(true); 
            BanCaPheController controller = new BanCaPheController(gui);
            new SwitchJPanelController(gui);
        } catch (SQLException e) {
            System.err.println("Error initializing POSInterfaceExample or DrinkButtonHandler: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
