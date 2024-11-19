package coffee.controller;

import coffee.dao.KhachHangDAO;
import coffee.view.TraCuuKhachHangView;
import coffee.entity.KhachHang;

import java.sql.SQLException;

public class KhachHangController {

    private static KhachHangController instance; // Singleton instance
    private TraCuuKhachHangView view; // View hiển thị
    private KhachHangDAO khachHangDAO; // DAO xử lý dữ liệu

    // Singleton pattern: Chỉ khởi tạo một instance duy nhất
    public static KhachHangController getInstance() {
        if (instance == null) {
            instance = new KhachHangController();
        }
        return instance;
    }

    // Constructor chính (sử dụng View và DAO)
    public KhachHangController() {
        this.view = new TraCuuKhachHangView();
        this.khachHangDAO = KhachHangDAO.getInstance(); // Đảm bảo lớp DAO có getInstance()
    }

    // Getter cho View
    public TraCuuKhachHangView getView() {
        return view;
    }

    // Hiển thị danh sách khách hàng
    public void displayCustomers() {
        view.displayCustomerData();
    }

    // Tìm kiếm khách hàng
    public void searchCustomer(String searchQuery) {
        view.searchCustomer();
    }

    // Sửa tên khách hàng
    public void editCustomerName(String oldName, String newName, String customerId) {
        if (newName != null && !newName.trim().isEmpty()) {
            try {
                KhachHang customer = khachHangDAO.getById(customerId);
                if (customer != null) {
                    customer.setTenKhachHang(newName);
                    boolean updated = khachHangDAO.capNhat(customer);
                    if (updated) {
                        view.updateTableAfterEdit(customerId, newName);
                    } else {
                        view.showError("Có lỗi khi cập nhật tên khách hàng.");
                    }
                } else {
                    view.showError("Không tìm thấy khách hàng.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                view.showError("Có lỗi khi cập nhật tên trong cơ sở dữ liệu.");
            }
        } else {
            view.showError("Tên không hợp lệ.");
        }
    }
}
