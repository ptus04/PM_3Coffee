package coffee.controller;

import coffee.dao.KhachHangDAO;
import coffee.view.TraCuuKhachHangView;
import coffee.entity.KhachHang;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;



public class KhachHangController {
    private TraCuuKhachHangView view;
    private KhachHangDAO khachHangDAO;

    public KhachHangController(TraCuuKhachHangView view) {
        this.view = view;
        this.khachHangDAO = KhachHangDAO.getInstance();
    }

    // Hàm gọi để hiển thị danh sách khách hàng
    public void displayCustomers() {
        view.displayCustomerData();
    }

    // Hàm gọi để tìm kiếm khách hàng theo mã hoặc tên
    public void searchCustomer(String searchQuery) {
        view.searchCustomer();
    }

    // Hàm gọi để sửa tên khách hàng
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
