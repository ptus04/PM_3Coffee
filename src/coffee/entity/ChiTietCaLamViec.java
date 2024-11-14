package coffee.entity;

public class ChiTietCaLamViec {

    private NhanVien nhanvien;
    private CaLamViec calam;

    public NhanVien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(NhanVien nhanvien) {
        // Kiểm tra mã nhân viên phải bắt đầu bằng "NV" và có 6 chữ số
        if (nhanvien.getMaNhanVien().matches("NV\\d{6}")) {
            this.nhanvien = nhanvien;
        } else {
            throw new IllegalArgumentException("Mã nhân viên không hợp lệ. Định dạng phải là 'NV' theo sau là 8 chữ số.");
        }
    }

    public CaLamViec getCalam() {
        return calam;
    }

    public void setCalam(CaLamViec calam) {
        // Kiểm tra mã ca làm phải bắt đầu bằng "CA" và có 8 chữ số
        if (calam.getMaCaLam().matches("CA\\d{8}")) {
            this.calam = calam;
        } else {
            throw new IllegalArgumentException("Mã ca làm không hợp lệ. Định dạng phải là 'CA' theo sau là 8 chữ số.");
        }
    }

    public ChiTietCaLamViec(NhanVien nhanvien, CaLamViec calam) {
        this.setNhanvien(nhanvien);  // Gọi setter để kiểm tra mã nhân viên
        this.setCalam(calam);         // Gọi setter để kiểm tra mã ca làm
    }
}
