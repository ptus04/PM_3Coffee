package coffee.entity;

public enum TrangThaiLamViec {
	CON_LAM_VIEC(1), TAM_NGHI_VIEC(2), DA_NGHI_VIEC(3);

	private int value;

	public int getValue() {
		return value;
	}

	private TrangThaiLamViec(int value) {
		this.value = value;
	}

	public static TrangThaiLamViec fromValue(int value) {
		for (TrangThaiLamViec trangThai : TrangThaiLamViec.values()) {
			if (trangThai.getValue() == value) {
				return trangThai;
			}
		}
		return null;
	}

}
