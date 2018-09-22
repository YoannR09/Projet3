package fr.yoannroche.projet3.mastermind.model;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.BeanReglage;

public class InterfaceModel {
	
	private BeanReglage			bean			;
	
	public InterfaceModel(BeanReglage bean) {
		this.bean = bean;
	}
	
	public JLabel createJLabel(){
		JLabel jl = new JLabel();
		return jl;
	}
	public JPanel createJPanel(){
		JPanel jp = new JPanel();
		return jp;
	}

	public void blocJeuSize(JPanel blocJeu, JPanel contentPane) {
		if(bean.getTentatives()==4) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(bean.getTentatives()==5) {
			blocJeu.setPreferredSize(new Dimension(350,190));
		}
		if(bean.getTentatives()==6) {
			blocJeu.setPreferredSize(new Dimension(350,235));
		}
		if(bean.getTentatives()==7) {
			blocJeu.setPreferredSize(new Dimension(350,275));
		}
		if(bean.getTentatives()==8) {
			blocJeu.setPreferredSize(new Dimension(350,315));
		}
		if(bean.getTentatives()==10) {
			blocJeu.setPreferredSize(new Dimension(350,410));
		}
		if(bean.getTentatives()==20) {
			blocJeu.setPreferredSize(new Dimension(350,775));
		}
	}
}
