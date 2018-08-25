package fr.yoannroche.projet3.mastermind.model;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceModel {
	
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int tentative = Integer.parseInt(reglage.getString("tentatives"));
	
	public JLabel createJLabel(){
		JLabel jl = new JLabel();
		return jl;
	}
	public JPanel createJPanel(){
		JPanel jp = new JPanel();
		return jp;
	}

	public void blocJeuSize(JPanel blocJeu) {
		if(tentative==4) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(tentative==5) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(tentative==6) {
			blocJeu.setPreferredSize(new Dimension(350,235));
		}
		if(tentative==7) {
			blocJeu.setPreferredSize(new Dimension(350,275));
		}
		if(tentative==8) {
			blocJeu.setPreferredSize(new Dimension(350,315));
		}


	}

}
