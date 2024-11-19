package coffee.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import coffee.shared.SecondaryButton;
import coffee.view.BanCaPheView;

public class SwitchJPanelController implements ActionListener{
	private BanCaPheView gui;
	private SecondaryButton buttonTemp;
	private List<SecondaryButton> toolButtons;
	public SwitchJPanelController(BanCaPheView gui) {
		this.gui = gui;
		addListners();
	}
	
	private void addListners() {
		for(SecondaryButton button : gui.getToolButtons()) {
			if(button.getText().equals("Pay")) {
				buttonTemp = button;
				buttonTemp.addActionListener(this::actionPerformed);
				break;
			}
        }
		gui.getButtonBack().addActionListener(this::actionPerformed);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(buttonTemp)) {
			switchPanel();
		}
		if(o.equals(gui.getButtonBack())) {
			getBackPanel();
		}
	}

	private void getBackPanel() {
		// TODO Auto-generated method stub
		gui.getCardLayoutPayment().next(gui.getSwitchEastPanel());
		gui.getCardLayoutToolButtons().next(gui.getSwitchToolButtonsPanel());
	}

	private void switchPanel() {
		// TODO Auto-generated method stub
		gui.getCardLayoutPayment().next(gui.getSwitchEastPanel());
		gui.getCardLayoutToolButtons().next(gui.getSwitchToolButtonsPanel());
	}
	

}
