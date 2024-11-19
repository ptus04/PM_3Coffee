CREATE TABLE NhanVien(
	maNhanVien CHAR(8) PRIMARY KEY CHECK(maNhanVien LIKE 'NV[0-9][0-9][0-9][0-9][0-9][0-9]'), -- NV[YYMM][##]
	hoTen NVARCHAR(32) NOT NULL,
	gioiTinh BIT NOT NULL DEFAULT 0, -- 0: Nam, 1: Nữ
	ngaySinh DATE NOT NULL CHECK(DATEDIFF(YEAR, ngaySinh, CURRENT_TIMESTAMP) >= 18 AND DATEDIFF(YEAR, ngaySinh, CURRENT_TIMESTAMP) <= 30), -- Từ 18 đến 30 tuổi
	soCanCuoc VARCHAR(12) NOT NULL CHECK(soCanCuoc LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- 12 số
	soDienThoai VARCHAR(10) NOT NULL CHECK(soDienThoai LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- 10 số
	diaChi NVARCHAR(255) NOT NULL,
	heSoLuong FLOAT NOT NULL DEFAULT 1.0,
	laQuanLy BIT NOT NULL DEFAULT 0,
	trangThaiLamViec INT NOT NULL DEFAULT 0 CHECK (trangThaiLamViec IN (0, 1, 2)),
	hinhAnh NVARCHAR(128)
)

CREATE TABLE TaiKhoan(
	tenDangNhap CHAR(8) PRIMARY KEY,
	matKhau VARCHAR(255) NOT NULL,
	-- FK
	maNhanVien CHAR(8) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNhanVien)
)

CREATE TABLE CaLam(
	maCaLam CHAR(10) PRIMARY KEY CHECK(maCaLam LIKE 'CA[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- CA[YYMMDD][##] YYMMDD là thời gian bắt đầu
	tenCaLam NVARCHAR(128) NOT NULL,
	thoiGianBatDau TIME(7) NOT NULL,
	thoiGianKetThuc TIME(7) NOT NULL,
	ghiChu NVARCHAR(255),
	-- CHECK
	CHECK(thoiGianBatDau < thoiGianKetThuc),
	CHECK(thoiGianKetThuc > thoiGianBatDau)
)

CREATE TABLE ChiTietCaLamViec(
	-- FK
	maNhanVien CHAR(8) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNhanVien),
	maCaLam CHAR(10) NOT NULL FOREIGN KEY REFERENCES CaLam(maCaLam),
	-- PK
	PRIMARY KEY(maNhanVien, maCaLam)
)

CREATE TABLE LichSuHoatDong(
	maLichSu CHAR(12) PRIMARY KEY CHECK(maLichSu LIKE 'LS[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- LS[YYMMDD][####]
	thoiGian DATETIME2 NOT NULL DEFAULT GETDATE(),
	noiDung NVARCHAR(255) NOT NULL,
	soTienBanGiao MONEY,
	loaiLichSu INT NOT NULL CHECK (loaiLichSu IN (0, 1, 2, 3, 4)),
	-- FK
	maNhanVien CHAR(8) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNhanVien)
)

CREATE TABLE SanPham(
	maSanPham CHAR(5) PRIMARY KEY CHECK(maSanPham LIKE 'SP[0-9][0-9][0-9]'), -- SP[###]
	tenSanPham NVARCHAR(128) NOT NULL,
	loaiSanPham NVARCHAR(128),
	donGia DECIMAL NOT NULL CHECK(donGia > 0),
	thanhPhan NVARCHAR(255),
	moTa NVARCHAR(255),
	con BIT NOT NULL DEFAULT 0,
	laDoAn BIT NOT NULL DEFAULT 0,
	hinhAnh NVARCHAR(128)
)

CREATE TABLE KhachHang(
	soDienThoai CHAR(10) PRIMARY KEY CHECK(soDienThoai LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	tenKhachHang NVARCHAR(128) NOT NULL DEFAULT N'Khách vãng lai'
)

CREATE TABLE KhuyenMai(
	maKhuyenMai CHAR(10) PRIMARY KEY CHECK(maKhuyenMai LIKE 'KM[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- KM[YYMMDD][##] YYMMDD là ngày hiệu lực
	triGia DECIMAL NOT NULL CHECK(triGia > 0),
	ngayHieuLuc DATETIME2 NOT NULL DEFAULT GETDATE() CHECK(ngayHieuLuc >= GETDATE()),
	ngayHetHan DATETIME2 NOT NULL,
	ghiChu NVARCHAR(255),
	-- CHECK
	CHECK(ngayHetHan > ngayHieuLuc)
)

CREATE TABLE ChiTietKhuyenMai(
	muaToiThieu INT NOT NULL,
	-- FK
	maKhuyenMai CHAR(10) FOREIGN KEY REFERENCES KhuyenMai(maKhuyenMai),
	maSanPham CHAR(5) FOREIGN KEY REFERENCES SanPham(maSanPham),
	-- PK
	PRIMARY KEY(maKhuyenMai, maSanPham)
)

CREATE TABLE DonHang(
	maDonHang CHAR(12) PRIMARY KEY CHECK(maDonHang LIKE 'DH[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- DH[YYMMDD][####] YYMMDD là thời gian tạo
	khachTra MONEY NOT NULL CHECK(khachTra > 0),
	thue DECIMAL NOT NULL,
	thoiGianTao DATETIME2 NOT NULL DEFAULT CURRENT_TIMESTAMP,
	thoiGianIn DATETIME2 NOT NULL DEFAULT CURRENT_TIMESTAMP,
	ghiChu NVARCHAR(255),
	phuongThucThanhToan INT NOT NULL DEFAULT 0 CHECK (phuongThucThanhToan IN(0, 1)),
	-- FK
	maNhanVien CHAR(8) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNhanVien),
	soDienThoai CHAR(10) FOREIGN KEY REFERENCES KhachHang(soDienThoai) DEFAULT 0000000000,
	maKhuyenMai CHAR(10) FOREIGN KEY REFERENCES KhuyenMai(maKhuyenMai),
)

CREATE TABLE ChiTietDonHang(
	soLuong INT NOT NULL DEFAULT 1 CHECK(soLuong > 0),
	ghiChu NVARCHAR(255),
	-- FK
	maDonHang CHAR(12) NOT NULL FOREIGN KEY REFERENCES DonHang(maDonHang),
	maSanPham CHAR(5) NOT NULL FOREIGN KEY REFERENCES SanPham(maSanPham),
)

-- DROP TABLE ChiTietDonHang, DonHang, ChiTietKhuyenMai, KhuyenMai, KhachHang, SanPham, LichSuHoatDong, ChiTietCaLamViec, CaLam, TaiKhoan, NhanVien
