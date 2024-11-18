package coffee.controller;

import coffee.dao.NhanVienDAO;
import coffee.entity.NhanVien;
import coffee.view.TraCuuNhanVienView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;



public class NhanVienController {
    private final TraCuuNhanVienView view;
    private final NhanVienDAO nhanVienDAO;

    public NhanVienController(TraCuuNhanVienView view) {
        this.view = view;
        this.nhanVienDAO = NhanVienDAO.getInstance();

        // Gắn các hành động từ View tới Controller
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        view.getBtnTimKiem().addActionListener(e -> timKiemNhanVien());
        view.getBtnXoaRong().addActionListener(e -> xoaRongBang());
        view.getBtnXuatCSV().addActionListener(e -> xuatCSV());
    }

    // Hiển thị toàn bộ nhân viên từ cơ sở dữ liệu lên bảng
    private void timKiemNhanVien() {
        String tuKhoa = view.getTxtTimKiem().getText().trim();
        if (tuKhoa.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập mã nhân viên hoặc tên để tìm kiếm.");
            return;
        }

        try {
            List<NhanVien> danhSachNhanVien = nhanVienDAO.timKiemNhanVien(tuKhoa);
            DefaultTableModel model = view.getMoHinhBang();
            model.setRowCount(0);  // Clear data
            DateTimeFormatter dinhDangNgay = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (NhanVien nv : danhSachNhanVien) {
                model.addRow(new Object[] {
                    nv.getMaNhanVien(),
                    nv.getHoTen(),
                    "Nam", 
                    nv.getNgaySinh() != null ? nv.getNgaySinh().format(dinhDangNgay) : "N/A",
                    nv.getSoCanCuoc(),
                    nv.getSoDienThoai(),
                    nv.getDiaChi(),
                    nv.getHeSoLuong(),
                    nv.isLaQuanLy() ? "Có" : "Không",
                    "Còn làm việc", 
                    nv.getHinhAnh()
                });
            }

            if (danhSachNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên nào.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoaRongBang() {
        view.getMoHinhBang().setRowCount(0); 
    }

    private void xuatCSV() {
        try {
            view.xuatCSV();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xuất CSV: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
