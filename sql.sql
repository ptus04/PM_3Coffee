USE [master]
GO
/****** Object:  Database [3Coffee]    Script Date: 11/19/2024 1:41:42 PM ******/
CREATE DATABASE [3Coffee]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'3Coffee', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\3Coffee.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'3Coffee_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\3Coffee_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [3Coffee] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [3Coffee].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [3Coffee] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [3Coffee] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [3Coffee] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [3Coffee] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [3Coffee] SET ARITHABORT OFF 
GO
ALTER DATABASE [3Coffee] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [3Coffee] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [3Coffee] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [3Coffee] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [3Coffee] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [3Coffee] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [3Coffee] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [3Coffee] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [3Coffee] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [3Coffee] SET  DISABLE_BROKER 
GO
ALTER DATABASE [3Coffee] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [3Coffee] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [3Coffee] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [3Coffee] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [3Coffee] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [3Coffee] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [3Coffee] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [3Coffee] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [3Coffee] SET  MULTI_USER 
GO
ALTER DATABASE [3Coffee] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [3Coffee] SET DB_CHAINING OFF 
GO
ALTER DATABASE [3Coffee] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [3Coffee] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [3Coffee] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [3Coffee] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [3Coffee] SET QUERY_STORE = ON
GO
ALTER DATABASE [3Coffee] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [3Coffee]
GO
/****** Object:  Table [dbo].[CaLam]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLam](
	[maCaLam] [char](10) NOT NULL,
	[tenCaLam] [nvarchar](128) NOT NULL,
	[thoiGianBatDau] [time](7) NULL,
	[thoiGianKetThuc] [time](7) NULL,
	[ghiChu] [nvarchar](255) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietCaLamViec]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietCaLamViec](
	[maNhanVien] [char](8) NOT NULL,
	[maCaLam] [char](10) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietDonHang]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDonHang](
	[soLuong] [int] NOT NULL,
	[ghiChu] [nvarchar](255) NULL,
	[maDonHang] [char](12) NOT NULL,
	[maSanPham] [char](5) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietKhuyenMai]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietKhuyenMai](
	[muaToiThieu] [int] NOT NULL,
	[maKhuyenMai] [char](10) NOT NULL,
	[maSanPham] [char](5) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonHang]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonHang](
	[maDonHang] [char](12) NOT NULL,
	[khachTra] [money] NOT NULL,
	[thue] [decimal](3, 2) NULL,
	[thoiGianTao] [datetime2](7) NOT NULL,
	[thoiGianIn] [datetime2](7) NOT NULL,
	[ghiChu] [nvarchar](255) NULL,
	[phuongThucThanhToan] [int] NOT NULL,
	[maNhanVien] [char](8) NOT NULL,
	[soDienThoai] [char](10) NULL,
	[maKhuyenMai] [char](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[soDienThoai] [char](10) NOT NULL,
	[tenKhachHang] [nvarchar](128) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[maKhuyenMai] [char](10) NOT NULL,
	[triGia] [decimal](18, 0) NOT NULL,
	[ngayHieuLuc] [datetime2](7) NOT NULL,
	[ngayHetHan] [datetime2](7) NOT NULL,
	[ghiChu] [nvarchar](255) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichSuHoatDong]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichSuHoatDong](
	[maLichSu] [char](12) NOT NULL,
	[thoiGian] [datetime2](7) NOT NULL,
	[noiDung] [nvarchar](255) NOT NULL,
	[soTienBanGiao] [money] NULL,
	[loaiLichSu] [int] NOT NULL,
	[maNhanVien] [char](8) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [char](8) NOT NULL,
	[hoTen] [nvarchar](32) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[soCanCuoc] [varchar](12) NOT NULL,
	[soDienThoai] [varchar](10) NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[heSoLuong] [float] NOT NULL,
	[laQuanLy] [bit] NOT NULL,
	[trangThaiLamViec] [int] NOT NULL,
	[hinhAnh] [nvarchar](128) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSanPham] [char](5) NOT NULL,
	[tenSanPham] [nvarchar](128) NOT NULL,
	[loaiSanPham] [nvarchar](128) NULL,
	[donGia] [decimal](18, 0) NOT NULL,
	[thanhPhan] [nvarchar](255) NULL,
	[moTa] [nvarchar](255) NULL,
	[con] [bit] NOT NULL,
	[laDoAn] [bit] NOT NULL,
	[hinhAnh] [nvarchar](128) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/19/2024 1:41:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[tenDangNhap] [char](8) NOT NULL,
	[matKhau] [varchar](255) NOT NULL,
	[maNhanVien] [char](8) NOT NULL
) ON [PRIMARY]
GO
INSERT [dbo].[CaLam] ([maCaLam], [tenCaLam], [thoiGianBatDau], [thoiGianKetThuc], [ghiChu]) VALUES (N'CA24111101', N'Ca Sáng', CAST(N'06:00:00' AS Time), CAST(N'10:00:00' AS Time), NULL)
GO
INSERT [dbo].[CaLam] ([maCaLam], [tenCaLam], [thoiGianBatDau], [thoiGianKetThuc], [ghiChu]) VALUES (N'CA24111102', N'Ca Trưa', CAST(N'10:00:00' AS Time), CAST(N'14:00:00' AS Time), NULL)
GO
INSERT [dbo].[CaLam] ([maCaLam], [tenCaLam], [thoiGianBatDau], [thoiGianKetThuc], [ghiChu]) VALUES (N'CA24111103', N'Ca Chiều', CAST(N'14:00:00' AS Time), CAST(N'18:00:00' AS Time), NULL)
GO
INSERT [dbo].[CaLam] ([maCaLam], [tenCaLam], [thoiGianBatDau], [thoiGianKetThuc], [ghiChu]) VALUES (N'CA24111104', N'Ca Tối', CAST(N'18:00:00' AS Time), CAST(N'22:00:00' AS Time), NULL)
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241101', N'CA24111101')
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241101', N'CA24111102')
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241101', N'CA24111103')
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241101', N'CA24111104')
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241102', N'CA24111101')
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241102', N'CA24111103')
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241103', N'CA24111102')
GO
INSERT [dbo].[ChiTietCaLamViec] ([maNhanVien], [maCaLam]) VALUES (N'NV241103', N'CA24111104')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140001', N'SP002')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140001', N'SP009')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140001', N'SP005')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140002', N'SP011')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (3, NULL, N'DH2411140002', N'SP014')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (3, NULL, N'DH2411140001', N'SP001')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140001', N'SP002')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (4, NULL, N'DH2411140002', N'SP004')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140002', N'SP005')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140002', N'SP009')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (5, NULL, N'DH2411140003', N'SP010')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140003', N'SP011')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (3, NULL, N'DH2411140004', N'SP012')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140005', N'SP013')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (4, NULL, N'DH2411140005', N'SP014')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140006', N'SP001')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140006', N'SP005')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (3, NULL, N'DH2411140006', N'SP006')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140006', N'SP009')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (4, NULL, N'DH2411140007', N'SP010')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140007', N'SP011')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (5, NULL, N'DH2411140007', N'SP012')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140007', N'SP013')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140008', N'SP014')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (3, NULL, N'DH2411140008', N'SP015')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (5, NULL, N'DH2411140009', N'SP002')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, NULL, N'DH2411140010', N'SP004')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (3, NULL, N'DH2411140010', N'SP005')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (4, NULL, N'DH2411140011', N'SP006')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (2, NULL, N'DH2411140011', N'SP009')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (3, NULL, N'DH2411140011', N'SP010')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (5, NULL, N'DH2411140001', N'SP001')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, N'null', N'DH2411190017', N'SP001')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, N'null', N'DH2411190017', N'SP002')
GO
INSERT [dbo].[ChiTietDonHang] ([soLuong], [ghiChu], [maDonHang], [maSanPham]) VALUES (1, N'null', N'DH2411190017', N'SP005')
GO
INSERT [dbo].[ChiTietKhuyenMai] ([muaToiThieu], [maKhuyenMai], [maSanPham]) VALUES (5, N'KM24111401', N'SP013')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140001', 313200.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-14T07:15:00.0000000' AS DateTime2), CAST(N'2024-11-14T07:15:00.0000000' AS DateTime2), NULL, 0, N'NV241102', N'0000000000', NULL)
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140002', 108000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-14T07:18:00.0000000' AS DateTime2), CAST(N'2024-11-14T07:18:00.0000000' AS DateTime2), NULL, 0, N'NV241102', N'0000000000', NULL)
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140003', 150000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-15T09:00:00.0000000' AS DateTime2), CAST(N'2024-11-14T09:05:00.0000000' AS DateTime2), NULL, 0, N'NV241102', N'0000000000', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140004', 250000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-15T09:15:00.0000000' AS DateTime2), CAST(N'2024-11-14T09:25:00.0000000' AS DateTime2), NULL, 1, N'NV241102', N'0868675208', NULL)
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140005', 320000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-15T10:00:00.0000000' AS DateTime2), CAST(N'2024-11-14T10:10:00.0000000' AS DateTime2), NULL, 0, N'NV241103', N'0000000000', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140006', 180000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-16T10:30:00.0000000' AS DateTime2), CAST(N'2024-11-14T10:40:00.0000000' AS DateTime2), NULL, 1, N'NV241103', N'0963584250', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140007', 400000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-16T11:00:00.0000000' AS DateTime2), CAST(N'2024-11-14T11:15:00.0000000' AS DateTime2), NULL, 0, N'NV241103', N'0000000000', NULL)
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140008', 275000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-16T11:30:00.0000000' AS DateTime2), CAST(N'2024-11-14T11:40:00.0000000' AS DateTime2), NULL, 1, N'NV241103', N'0868675208', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140009', 310000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-16T12:00:00.0000000' AS DateTime2), CAST(N'2024-11-14T15:05:00.0000000' AS DateTime2), NULL, 0, N'NV241102', N'0963525864', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140010', 150000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-16T12:30:00.0000000' AS DateTime2), CAST(N'2024-11-14T20:35:00.0000000' AS DateTime2), NULL, 1, N'NV241103', N'0000000000', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411140011', 220000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-16T13:00:00.0000000' AS DateTime2), CAST(N'2024-11-14T21:10:00.0000000' AS DateTime2), NULL, 0, N'NV241103', N'0963525864', NULL)
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190000', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:06:20.0156962' AS DateTime2), CAST(N'2024-11-19T07:06:20.0156962' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190001', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:31:10.0248890' AS DateTime2), CAST(N'2024-11-19T07:31:10.0248890' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190002', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:33:15.3353568' AS DateTime2), CAST(N'2024-11-19T07:33:15.3353568' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190003', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:36:11.5022816' AS DateTime2), CAST(N'2024-11-19T07:36:11.5022816' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190004', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:40:28.3666649' AS DateTime2), CAST(N'2024-11-19T07:40:28.3666649' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190005', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:41:08.3217423' AS DateTime2), CAST(N'2024-11-19T07:41:08.3217423' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190006', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:45:26.1771839' AS DateTime2), CAST(N'2024-11-19T07:45:26.1771839' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190007', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:50:22.4135553' AS DateTime2), CAST(N'2024-11-19T07:50:22.4135553' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190008', 1.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T07:51:50.6887165' AS DateTime2), CAST(N'2024-11-19T07:51:50.6887165' AS DateTime2), N'', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190009', 5000000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T08:14:19.5005054' AS DateTime2), CAST(N'2024-11-19T08:14:19.5005054' AS DateTime2), N'không có gì', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190010', 5000000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T08:14:27.1701677' AS DateTime2), CAST(N'2024-11-19T08:14:27.1701677' AS DateTime2), N'không có gì', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190011', 5000000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T08:18:24.2044522' AS DateTime2), CAST(N'2024-11-19T08:18:24.2044522' AS DateTime2), N'adfadfadf', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190012', 5000000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T08:21:29.3082450' AS DateTime2), CAST(N'2024-11-19T08:21:29.3082450' AS DateTime2), N'l', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190013', 52000000.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T08:23:17.5269160' AS DateTime2), CAST(N'2024-11-19T08:23:17.5269160' AS DateTime2), N'a', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190014', 8915616545.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T08:26:48.2385004' AS DateTime2), CAST(N'2024-11-19T08:26:48.2385004' AS DateTime2), N'q', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190015', 8825154654646.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T08:38:46.2426206' AS DateTime2), CAST(N'2024-11-19T08:38:46.2426206' AS DateTime2), N'adfadsfasf', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190016', 5065568.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T09:14:47.8811021' AS DateTime2), CAST(N'2024-11-19T09:14:47.8811021' AS DateTime2), N'dfadsfadfs', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[DonHang] ([maDonHang], [khachTra], [thue], [thoiGianTao], [thoiGianIn], [ghiChu], [phuongThucThanhToan], [maNhanVien], [soDienThoai], [maKhuyenMai]) VALUES (N'DH2411190017', 55248625858.0000, CAST(0.08 AS Decimal(3, 2)), CAST(N'2024-11-19T09:16:13.4978167' AS DateTime2), CAST(N'2024-11-19T09:16:13.4978167' AS DateTime2), N'adfadfadf', 0, N'NV241103', N'0123456789', N'KM24111401')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0000000000', N'Khách vãng lai')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0123456789', N'cúc')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0234567891', N'a')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0345678912', N'phong')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0369821457', N'Đạt')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0789456123', N'bi')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0868675208', N'Bùi Minh Tuấn')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0963525864', N'Nguyễn Thị Kim Ngân')
GO
INSERT [dbo].[KhachHang] ([soDienThoai], [tenKhachHang]) VALUES (N'0963584250', N'Trần Ngọc Ân')
GO
INSERT [dbo].[KhuyenMai] ([maKhuyenMai], [triGia], [ngayHieuLuc], [ngayHetHan], [ghiChu]) VALUES (N'KM24111401', CAST(100 AS Decimal(18, 0)), CAST(N'2024-11-15T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-30T00:00:00.0000000' AS DateTime2), N'Mua 5 tặng 1 cho Cafe Mocha')
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [gioiTinh], [ngaySinh], [soCanCuoc], [soDienThoai], [diaChi], [heSoLuong], [laQuanLy], [trangThaiLamViec], [hinhAnh]) VALUES (N'NV241101', N'Phùng Văn Tú', 0, CAST(N'2004-03-03' AS Date), N'000000000000', N'0868635209', N'58 đường An Nhơn Tây, xã An Nhơn Tây, huyện Củ Chi, TP. Hồ Chí Minh', 1, 1, 0, NULL)
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [gioiTinh], [ngaySinh], [soCanCuoc], [soDienThoai], [diaChi], [heSoLuong], [laQuanLy], [trangThaiLamViec], [hinhAnh]) VALUES (N'NV241102', N'Võ Huỳnh Đạt', 0, CAST(N'2004-01-01' AS Date), N'000000000000', N'0000000000', N'', 1, 0, 0, NULL)
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [hoTen], [gioiTinh], [ngaySinh], [soCanCuoc], [soDienThoai], [diaChi], [heSoLuong], [laQuanLy], [trangThaiLamViec], [hinhAnh]) VALUES (N'NV241103', N'Lê Nguyễn Thế Phong', 0, CAST(N'2004-01-01' AS Date), N'000000000000', N'0000000000', N'', 1, 0, 0, NULL)
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP001', N'White Chocolate Mocha', N'Cà phê', CAST(52000 AS Decimal(18, 0)), N'Espresso, sốt sô cô la trắng, sữa hấp, kem tươi', NULL, 1, 0, N'WhiteChocolateMocha')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP002', N'Roast Coffee', N'Cà phê', CAST(69000 AS Decimal(18, 0)), N'Hạt cà phê Arabica, Cà phê rang', NULL, 1, 0, N'RoastCoffee')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP003', N'Pumpkin Spice Latte', N'Cà phê sữa', CAST(100000 AS Decimal(18, 0)), N'Espresso, Sữa hấp, Siro bí ngô, Kem tươi, Topping bí ngô', NULL, 0, 0, N'PumpkinSpiceLatte')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP004', N'Honey Almondmilk Flat White', N'Cà phê sữa', CAST(35000 AS Decimal(18, 0)), N'Espresso, Sữa hạnh nhân hấp, Mật ong', NULL, 1, 0, N'Honey AlmondmilkFlatWhite')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP005', N'Flat White', N'Cà phê sữa', CAST(85000 AS Decimal(18, 0)), N'Espresso, Sữa hấp, Bọt nhỏ', NULL, 1, 0, N'FlatWhite')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP006', N'Espresso', N'Espresso', CAST(45000 AS Decimal(18, 0)), N'Espresso', NULL, 1, 0, N'Espresso')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP007', N'Espresso Con Panna', N'Espresso', CAST(100000 AS Decimal(18, 0)), N'Espresso, kem tươi', NULL, 0, 0, N'EspressoConPanna')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP008', N'Espresso Macchiato', N'Espresso', CAST(100000 AS Decimal(18, 0)), N'Espresso, bọt sữa', NULL, 0, 0, N'EspressoMacchiato')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP009', N'Cinnamon Dolce Latte', N'Cà phê sữa', CAST(68000 AS Decimal(18, 0)), N'Espresso, Sữa hấp, Siro quế Dolce, Kem tươi', NULL, 1, 0, N'CinnamonDolceLatte')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP010', N'Caramel Macchiato', N'Cà phê sữa', CAST(63000 AS Decimal(18, 0)), N'Espresso, Sữa hấp, Siro Caramel', NULL, 1, 0, N'CaramelMacchiato')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP011', N'Cappuccino', N'Cà phê', CAST(60000 AS Decimal(18, 0)), N'Espresso, Sữa hấp, Bọt', NULL, 1, 0, N'Cappuccino')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP012', N'Cafe Latte', N'Cà phê sữa', CAST(80000 AS Decimal(18, 0)), N'Espresso, Sữa hấp', NULL, 1, 0, N'CafeLatte')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP013', N'Cafe Mocha', N'Cà phê', CAST(73000 AS Decimal(18, 0)), N'Espresso, Sữa hấp, Siro sô cô la, Kem tươi', NULL, 1, 0, N'CafeMocha')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP014', N'Cafe Misto', N'Cà phê', CAST(40000 AS Decimal(18, 0)), N'Espresso, Sữa hấp', NULL, 1, 0, N'CafeMisto')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP015', N'Cafe Americano', N'Cà phê', CAST(50000 AS Decimal(18, 0)), N'Espresso, Nước nóng', NULL, 1, 0, N'CafeAmericano')
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [loaiSanPham], [donGia], [thanhPhan], [moTa], [con], [laDoAn], [hinhAnh]) VALUES (N'SP243', N'Americano', N'Cà phê', CAST(50000 AS Decimal(18, 0)), N'Mặc định', N'src/imageCoffee/CAFE AMERICANO.PNG', 1, 0, N'Americano')
GO
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien]) VALUES (N'huynhdat', N'0qg/KevStXbBa5MJE6eqkQ==', N'NV241102')
GO
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien]) VALUES (N'thephong', N'0qg/KevStXbBa5MJE6eqkQ==', N'NV241103')
GO
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien]) VALUES (N'vantu   ', N'0qg/KevStXbBa5MJE6eqkQ==', N'NV241101')
GO
USE [master]
GO
ALTER DATABASE [3Coffee] SET  READ_WRITE 
GO
