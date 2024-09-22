package coffee.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class NhanVien {

	private String maNhanVien;
	private String ten;
	private String hoDem;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String soCanCuoc;
	private String soDienThoai;
	private String diaChi;
	private byte[] hinhAnh;
	private List<CaLamViec> listCaLamViec;

}
