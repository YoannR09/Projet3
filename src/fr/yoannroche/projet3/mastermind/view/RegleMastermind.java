package fr.yoannroche.projet3.mastermind.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.yoannroche.projet3.Lancement;

public class RegleMastermind extends JFrame {

	private JPanel		contentPane			= new JPanel();
	private Font		arial				= new Font ("arial", 12,12);
	private Font		impact2				= new Font ("impact", 20,20);
	private JButton		retour				= new JButton("Retour");
	
	
	public RegleMastermind() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(" Regle du MasterMind ");
		this.setSize(355, 410);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		initCadre();
	}


	private void initCadre() {
		
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.setForeground(Color.white);
		retour.setLayout(new GridLayout(3, 50));
		retour.addMouseListener(new SourisListener());
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				Lancement menu = new Lancement();	
				menu.setVisible(true);
			}
		});
		JPanel espace1 = new JPanel();
		espace1.setPreferredSize(new Dimension(30,40));
		espace1.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(30,40));
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		JPanel cadreRegle = new JPanel();
		cadreRegle.setPreferredSize(new Dimension(320,360));
		cadreRegle.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		cadreRegle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		JLabel jeu = new JLabel();
		jeu.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.534f, 0.85f, 0.34f)));
		jeu.setForeground(Color.white);
		jeu.setFont(impact2);
		jeu.setOpaque(true);
		jeu.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		jeu.setText(" Jeu du MasterMind ");
		JTextArea regle = new JTextArea();
		regle.setFont(arial);
		regle.setEditable(false);
		regle.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.534f, 0.85f, 0.54f)));
		regle.setBackground(Color.getHSBColor(0.534f, 0.03f, 0.98f));
		regle.setText( "  Le but : découvrir la combinaison à x chiffres \n"
				+ " de l'adversaire (le défenseur).\n\n"
				+ " Pour ce faire, l'attaquant fait une proposition. \n"
				+ "  Le défenseur indique \n"
				+ " pour chaque chiffre de la combinaison proposée \n "
				+ " si le chiffre de sa combinaison est plus\n"
				+ " grand (+), plus petit (-) ou si c'est le bon \n"
				+ " chiffre (=) \n \n"
				+ "   -Mode challenger- où vous devez trouver\n"
				+ " la combinaison secrète de l'ordinateur\n \n" + 
				"   -Mode défenseur- où c'est à l'ordinateur\n "
				+ " de trouver votre combinaison secrète\n\n" + 
				"   -Mode duel- où l'ordinateur et vous jouez tour à tour,\n "
				+ " le premier à trouver la combinaison \n"
				+ " secrète de l'autre a gagné \n");
		cadreRegle.add(retour);
		cadreRegle.add(espace1);
		cadreRegle.add(jeu);
		cadreRegle.add(espace2);
		cadreRegle.add(regle);
		contentPane.add(cadreRegle);	
	}
	
	class SourisListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
		}
		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==retour) {
				retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
		}
		public void mouseExited(MouseEvent arg0) {
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		}
	}
}

