package coffee.entity;

public enum LoaiLichSu {
	DANG_NHAP(0), DANG_XUAT(1), TAO(2), SUA(3), TRA_CUU(4);

	private int value;

	public int getValue() {
		return value;
	}

	private LoaiLichSu(int value) {
		this.value = value;
	}

	public LoaiLichSu from(int value) {
		for (LoaiLichSu loaiLichSu : LoaiLichSu.values()) {
			if (loaiLichSu.getValue() == value) {
				return loaiLichSu;
			}
		}

		throw new IllegalArgumentException("Sai giá trị Loại lịch sử");
	}

}
