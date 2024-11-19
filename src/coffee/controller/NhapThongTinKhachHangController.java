package coffee.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import coffee.dao.KhachHangDAO;
import coffee.entity.KhachHang;
import coffee.view.NhapThongTinKhachHangView;

public class NhapThongTinKhachHangController implements ActionListener {
    private static NhapThongTinKhachHangView gui;
    private KhachHang khachHang;
    public KhachHang getKhachHang() {
    	return khachHang;
    }
    public NhapThongTinKhachHangController(NhapThongTinKhachHangView gui) {
        this.gui = gui;

        this.gui.getButtonComplete().addActionListener(this);
        this.gui.getButtonRegister().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(gui.getButtonComplete())) {
            completeAction();
        } else if (source.equals(gui.getButtonRegister())) {
            registerAction();
        }
    }

    private void completeAction() {
        String numberPhone = gui.getNumberPhone().getText();
        try {
            this.khachHang = KhachHangDAO.getInstance().getById(numberPhone);
            gui.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog( gui, "Số điện thoại chưa đăng ký!");
            gui.getNameCustomer().requestFocus();
            gui.getNameCustomer().selectAll();
        }
    }

    private void registerAction() {
        try {
            String numberPhone = gui.getNumberPhone().getText();
            String nameCustomer = gui.getNameCustomer().getText();

            if (numberPhone.isEmpty() || nameCustomer.isEmpty()) {
                JOptionPane.showMessageDialog(gui, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            this.khachHang = new KhachHang(numberPhone, nameCustomer);

            if (KhachHangDAO.getInstance().them(khachHang) == true) {
                JOptionPane.showMessageDialog(gui, "Đăng ký thành công");
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }

}
