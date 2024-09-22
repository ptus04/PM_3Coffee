package coffee.guis;

import java.awt.Color;

import javax.swing.JButton;

import coffee.Application;

public class ButtonFactory {
	
	public static JButton createNavigationButton(String iconPath, int iconSize, String text) {
		return createNavigationButton(iconPath, iconSize, text, Application.COLOR_PRIMARY);
	}
	
	public static JButton createNavigationButton(String iconPath, int iconSize, String text, Color foregroundColor) {
		return new NavigationButton(iconPath, iconSize, text, foregroundColor);
	}
	
	public static JButton createPrimaryButton(String text) {
		return new PrimaryButton(text);
	}
}
