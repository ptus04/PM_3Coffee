package coffee.shared;

import coffee.constant.Colors;

public class DangerPrimaryButton extends RoundedButton {

	private static final long serialVersionUID = 7549327916357325624L;

	public DangerPrimaryButton(String label) {
		super(label, Colors.DANGER);
	}

	public DangerPrimaryButton(String label, String iconPath) {
		super(label, iconPath, Colors.DANGER);
	}

}
