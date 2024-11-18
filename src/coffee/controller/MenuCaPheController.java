package coffee.controller;

import coffee.view.MenuCaPheView;
import coffee.dao.Database;
import coffee.dao.SanPhamDAO;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;


import coffee.shared.utility.PrinterUtilities;



public class MenuCaPheController {
    
    private MenuCaPheView view;
    
    public MenuCaPheController(MenuCaPheView view) {
        this.view = view;
    }

    // Method to handle adding a product
    public void themSanPham(String sanPhamDuocChon) {
        if (sanPhamDuocChon != null) {
            try (Connection conn = Database.getInstance().getConnection()) {
                String sql = "INSERT INTO SanPham (MaSanPham, TenSanPham, LoaiSanPham, DonGia, ThanhPhan, MoTa, Con, LaDoAn, HinhAnh) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                // Split the selected product information
                String[] parts = sanPhamDuocChon.split("-");
                String tenSanPham = parts[0].trim();
                String giaStr = parts[1].replaceAll("\\D+", "").trim();
                int gia = Integer.parseInt(giaStr);

                // Automatically generate product code (SP followed by a unique number)
                String maSanPham = "SP" + System.currentTimeMillis() % 10000; 
                maSanPham = maSanPham.length() > 5 ? maSanPham.substring(0, 5) : maSanPham;

                // Placeholder values for category, ingredient, etc.
                String loaiSanPham = "Cà phê"; 
                String thanhPhan = "Mặc định"; 
                String anh = "src/imageCoffee/" + tenSanPham.replaceAll(" ", "") + ".PNG"; 
                boolean hienThi = true;
                boolean khuyenMai = false;

                ps.setString(1, maSanPham);
                ps.setString(2, tenSanPham);
                ps.setString(3, loaiSanPham);
                ps.setInt(4, gia);
                ps.setString(5, thanhPhan);
                ps.setString(6, anh);
                ps.setBoolean(7, hienThi);
                ps.setBoolean(8, khuyenMai);
                ps.setString(9, tenSanPham.replaceAll(" ", ""));

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(view, "Thêm món " + tenSanPham + " thành công!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Lỗi khi thêm món vào cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn món trước khi thêm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to handle editing product (could be expanded to actually update product details)
    public void suaSanPham(String sanPhamDuocChon) {
        if (sanPhamDuocChon != null) {
            JOptionPane.showMessageDialog(view, "Sửa thông tin món " + sanPhamDuocChon);
            // In the future, you could add a dialog to allow editing product details
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn món trước khi sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
