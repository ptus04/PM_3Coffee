package coffee.controller;

import coffee.dao.ChiTietDonHangDAO;
import coffee.dao.DonHang_DAO;
import coffee.dao.KhuyenMaiDAO;
import coffee.dao.SanPhamDAO;
import coffee.entity.ChiTietDonHang;
import coffee.entity.DonHang;
import coffee.entity.KhachHang;
import coffee.entity.KhuyenMai;
import coffee.entity.NhanVien;
import coffee.entity.SanPham;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;
import coffee.view.BanCaPheView;
import coffee.view.NhapThongTinKhachHangView;
import coffee.view.ThayDoiSoLuongView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class BanCaPheController implements ActionListener {
    private BanCaPheView gui;
    private NhapThongTinKhachHangView view;
    private ThayDoiSoLuongView view1;
    private NhapThongTinKhachHangController controller;
    private ProductinCart list = new ProductinCart();
    private SecondaryButton buttonPay;
    private SecondaryButton buttonEnter;
    private SecondaryButton buttonDeleteAll;
    private SecondaryButton buttonDelete;
    private SecondaryButton buttonQuantity;
    private PrimaryButton buttonThayDoi;
    private String maDonHang;
    private DonHang donHang;
    
    public NhapThongTinKhachHangView getNhapThongTinKhachHangView() {
    	return view;
    }
    public String getNhapThongTinKhachHangController() {
    	if(controller != null)
    		return "đã được khởi tạo";
    	else
    		return"chưa được khởi tạo";

    }
    public BanCaPheController(BanCaPheView gui) throws SQLException {
        this.gui = gui;
        addListeners(); 
    }

    private void addListeners() {
        for (PrimaryButton button : gui.getDrinkButton()) {
            button.addActionListener(this);
        }
        for(SecondaryButton button : gui.getToolButtons()) {
        	if(button.getText().equals("Pay")) {
        		buttonPay = button;
        		buttonPay.addActionListener(this);
        	}
        	if(button.getText().equals("Delete All")) {
        		buttonDeleteAll = button;
        		buttonDeleteAll.addActionListener(this);
        	}
        	if(button.getText().equals("Delete")) {
        		buttonDelete = button;
        		buttonDelete.addActionListener(this);
        	}
        	if(button.getText().equals("Quantity")) {
        		buttonQuantity = button;
        		buttonQuantity.addActionListener(this);
        	}

        	
        }
        for(SecondaryButton button : gui.getToolButtonsPayment()) {
        	if(button.getText().equals("Enter")) {
        		buttonEnter = button;
        		buttonEnter.addActionListener(this);
        	}
        		
        }
        gui.PayMent().addActionListener(this);
        gui.getInfoCustomer().addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        for(PrimaryButton btn : gui.getDrinkButton()) {
            if (o.equals(btn)) {
            	addIntoCart(btn);
            	break;
            }
                
        }
        if(o.equals(buttonThayDoi)) {
        	thayDoiActions();
        }
        if(o.equals(buttonDeleteAll)) {
        	deleteAllActions();
        }
        if(o.equals(buttonDelete)) {
        	deleteActions();
        }	
        if(o.equals(buttonPay)) {
        	getTotalAfterClickPay();
        }
        if(o.equals(buttonQuantity)) {
        	changeNumberActions();
        }
        if(o.equals(gui.getInfoCustomer())) {
			getInfoCustomer();
        }
        if(o.equals(gui.PayMent()))
        	XuLyThanhToanHoaDonController();
    }

	private void thayDoiActions() {
		int row = gui.getTable().getSelectedRow();
		int soLuong = Integer.parseInt(view1.getQuantity().getText());
		gui.getTable().getModel().setValueAt( soLuong, row, 2);
		view1.dispose();
		
	}
	private void changeNumberActions() {
		view1 = new ThayDoiSoLuongView();
		view1.setVisible(true);
    	buttonThayDoi = view1.getThayDoiButton();
    	buttonThayDoi.addActionListener(e->thayDoiActions());
	}
	private void deleteActions() {
		int row = gui.getTable().getSelectedRow();
		int soLuong =  (int) gui.getTable().getModel().getValueAt( row, 2); 
		if( soLuong > 0 ) {
			soLuong = soLuong-1;
			gui.getTable().getModel().setValueAt( soLuong, row, 2);
		}else
			gui.getTableModel().removeRow(row);
	}
	private void deleteAllActions() {
		gui.getTableModel().setRowCount(0);
	}
	private void getTotalAfterClickPay() {
		double check = Double.parseDouble(gui.getTxtTotal().getText())*(1+0.08); 
		gui.getTotalAfterClickPay().setText(Double.toString(check)); 
	}
	private void XuLyThanhToanHoaDonController() {
		
		try {
		    LocalDateTime DateNow = LocalDateTime.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		    String formatted = DateNow.format(formatter);
		    String TextMiddleofMaDonHang = formatted.replaceAll("-", "");

		    maDonHang = "DH" + TextMiddleofMaDonHang + "0000";
		    
		    ArrayList<String> listofMaDonHang = DonHang_DAO.getInstance().getID();
		    int i = 1; 
		    while (listofMaDonHang.contains(maDonHang)) {
		        maDonHang = "DH" + TextMiddleofMaDonHang + String.format("%04d", i);
		        i++;
		    }

		    double khachTra = Double.parseDouble(gui.getKhachTra().getText());
		    double thue = 0.08;  
		    LocalDateTime thoiGianTao = LocalDateTime.now();
		    LocalDateTime thoiGianIn = LocalDateTime.now();
		    String ghiChu = gui.getGhiChu().getText();
		    int phuongThucThanhToan = 0;

		    NhanVien nhanVien = new NhanVien("NV241103");
		    KhuyenMai khuyenMai = new KhuyenMai("KM24111401");
		    KhachHang khachHang = controller.getKhachHang();
		    donHang = new DonHang(maDonHang, khachTra, thue, thoiGianTao, thoiGianIn, ghiChu, phuongThucThanhToan, khachHang, nhanVien, khuyenMai);

		    if (DonHang_DAO.getInstance().add(donHang) == 1) {
		        System.out.println("Thêm đơn hàng thành công");
		    } else {
		        System.out.println("Thêm đơn hàng không thành công");
		    }
		    ArrayList<ChiTietDonHang> danhSachChiTiet = null;
		    for (int i1 = 0; i1 < list.getSize(); i1++) {
		    	System.out.println(i1);
		        for (int j = 0; j < gui.getTable().getRowCount(); j++) {
		        	System.out.println(j);
		            int soLuong = (int) gui.getTable().getModel().getValueAt(j, 2);
		            String ghiChuChiTiet = "null";  
		            String tenSanPham = (String) gui.getTable().getModel().getValueAt(j, 1);

		            if (list.getProduct(i1).getTenSanPham().equals(tenSanPham)) {
		            	SanPham sanPham = list.getProduct(i1);
		            	System.out.println(sanPham.getMaSanPham());
		                ChiTietDonHang chitiet = new ChiTietDonHang(soLuong, ghiChuChiTiet, sanPham, donHang);
		                danhSachChiTiet = new ArrayList<ChiTietDonHang>();
		                danhSachChiTiet.add(chitiet);
		                boolean check = ChiTietDonHangDAO.getInstance().them(chitiet);
		                if (check) {
		                    System.out.println("Thêm chi tiết đơn hàng thành công");
		                } else {
		                    System.out.println("Thêm chi tiết đơn hàng không thành công");
		                }
		            }
		        }
		    }
		    donHang.setDanhsach(danhSachChiTiet);

		} catch (Exception e) {
		    e.printStackTrace();
		}

		
	}
	private void getInfoCustomer() {
		view = new NhapThongTinKhachHangView();
		view.setVisible(true);
		controller = new NhapThongTinKhachHangController(view);
	}

	private void addIntoCart(PrimaryButton btn) {
        int i = 1;
        double total = 0;
    	try {
            SanPham product = SanPhamDAO.getInstance().getById(btn.getName());
            if (list.checkInCart(product)==true) {
                Object[] row = { gui.getTable().getRowCount() + i, product.getTenSanPham(), i, product.getDonGia() };
                gui.getTableModel().addRow(row);
            } else {
                for (int j = 0; j < gui.getTable().getRowCount(); j++) {
                    if (gui.getTable().getValueAt( j, 1).equals(product.getTenSanPham())) {
                        int old_quantity = (int) gui.getTable().getValueAt(j, 2);
                        gui.getTableModel().setValueAt(old_quantity+1, j, 2);
                        
                        break;
                    }
                }
            }
        } catch (SQLException e1) {
            System.err.println("Failed to get product by ID: " + e1.getMessage());
            e1.printStackTrace();
        }
    	if(gui.getTable().getRowCount() > 0 ) {
        	for(int j = 0; j < gui.getTable().getRowCount(); j++) {
            	total += (Double) gui.getTable().getValueAt(j, 3) * (int) gui.getTable().getValueAt(j, 2);
            }
        	gui.getTxtTotal().setText(Double.toString(total));
        }
	}

	public BanCaPheView getGui() {
        return gui;
    }
}

