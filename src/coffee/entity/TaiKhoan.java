package coffee.entity;

import java.util.Objects;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private NhanVien nhanVien;

    // Biểu thức chính quy để kiểm tra tài khoản chỉ chứa chữ cái và số
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
     
        if (isValidUsername(tenDangNhap)) {
            this.tenDangNhap = tenDangNhap;
        } else {
            throw new IllegalArgumentException("Tài khoản chỉ được chứa chữ cái và số.");
        }
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        // Bỏ kiểm tra mật khẩu, có thể là bất kỳ chuỗi nào
        this.matKhau = matKhau;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public TaiKhoan(String tenDangNhap, String matKhau, NhanVien nhanVien) {
        if (isValidUsername(tenDangNhap)) {
            this.tenDangNhap = tenDangNhap;
        } else {
            throw new IllegalArgumentException("Tài khoản chỉ được chứa chữ cái và số.");
        }
        this.matKhau = matKhau; 
        this.nhanVien = nhanVien;
    }

    // Kiểm tra tính hợp lệ của tên tài khoản
    private boolean isValidUsername(String username) {
        // Kiểm tra tên tài khoản có khớp với biểu thức chính quy không
        return username != null && username.matches(USERNAME_PATTERN);
    }

   
    public int hashCode() {
        return Objects.hash(matKhau, tenDangNhap);
    }

 
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TaiKhoan))
            return false;
        TaiKhoan other = (TaiKhoan) obj;
        return Objects.equals(matKhau, other.matKhau) && Objects.equals(tenDangNhap, other.tenDangNhap);
    }


    public String toString() {
        return "TaiKhoan {tenDangNhap: " + tenDangNhap + ", nhanVien: " + nhanVien + "}";
    }
}
