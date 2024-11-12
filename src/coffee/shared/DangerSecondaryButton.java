package coffee.shared;

import coffee.constant.Colors;

public class DangerSecondaryButton extends SecondaryButton {

	private static final long serialVersionUID = 2901534715784654811L;

	public DangerSecondaryButton(String label) {
		super(label);
		setForeground(Colors.DANGER);
	}

	public DangerSecondaryButton(String label, String iconPath) {
		super(label, iconPath);
		setForeground(Colors.DANGER);
	}

}
