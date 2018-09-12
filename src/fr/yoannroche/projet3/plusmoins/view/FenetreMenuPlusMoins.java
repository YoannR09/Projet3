package fr.yoannroche.projet3.plusmoins.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Lancement;

/**
 * Class qui gère le menu du Plus ou Moins
 * Donne accés à tout les modes.
 * @author Pierrosan
 *
 */
public class FenetreMenuPlusMoins extends JFrame{
	
	private BeanReglage				bean				;
	private JPanel					contentPaneMenu		= new JPanel();
	private JRadioButton 			mode1,mode2,mode3	;
	private JPanel					blocMode			= new JPanel();
	private Font					police				= new Font ("arial black", 12,12);
	private Font					impact				= new Font ("impact", 17,17);
	private Font					impact2				= new Font ("impact", 20,20);
	private Dimension				dim					= new Dimension(150,50);
	private JButton					lancer				= new JButton("Lancer");
	private JButton					retour				= new JButton(" Retour ");
	private SourisListener			sListener			= new SourisListener();
	private JTextArea				regle				= new JTextArea();
	
	public FenetreMenuPlusMoins(BeanReglage bean) {
		this.setTitle("Menu du Plus ou Moins");
		this.setSize(550, 340);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPaneMenu);
		this.setResizable(false);
		this.bean = bean;
		contentPaneMenu.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

		initMenu();
	}

	private void initMenu() {

		JPanel espace1 = new JPanel();
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(210,10));

		mode1 = new JRadioButton("Mode Challenger");
		mode1.setSelected(true);
		mode2= new JRadioButton("Mode Défenseur");
		mode3 = new JRadioButton("Mode Duel");

		ButtonGroup bg = new ButtonGroup();
		bg.add(mode1);
		bg.add(mode2);
		bg.add(mode3);

		blocMode.add(mode1);
		mode1.setFont(impact);
		mode1.setPreferredSize(dim);
		mode1.setForeground((Color.DARK_GRAY));
		mode1.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		mode1.addMouseListener(new SourisListener());

		blocMode.add(mode2);
		mode2.setFont(impact);
		mode2.setPreferredSize(dim);
		mode2.setForeground((Color.DARK_GRAY));
		mode2.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		mode2.addMouseListener(new SourisListener());

		blocMode.add(mode3);
		mode3.setFont(impact);
		mode3.setPreferredSize(dim);
		mode3.setForeground((Color.DARK_GRAY));
		mode3.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		mode3.addMouseListener(new SourisListener());

		blocMode.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.44f));
		blocMode.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));

		regle.setText("   -Mode challenger- où vous devez trouver\r\n la combinaison secrète de l'ordinateur\r\n\n" + 
				"   -Mode défenseur- où c'est à l'ordinateur\r\n de trouver votre combinaison secrète\r\n\n" + 
				"   -Mode duel- où l'ordinateur et vous jouez tour à tour,\r\n le premier à trouver la combinaison secrète de l'autre a gagné");
		regle.setPreferredSize(new Dimension(450,155));
		regle.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		regle.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		regle.setFont(police);
		regle.setEditable(false);

		lancer.setPreferredSize(new Dimension(50,30));
		lancer.setBackground(Color.getHSBColor(0.234f, 0.65f, 0.94f));
		lancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		lancer.addMouseListener(sListener);
		lancer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPaneMenu.getTopLevelAncestor()).dispose() ;
				if(mode1.isSelected()==true) {
					ChallengerPlusMoins chal = new ChallengerPlusMoins(bean);	
					chal.setVisible(true);
				}
				if(mode2.isSelected()==true) {
					DefenseurPlusMoins def = new DefenseurPlusMoins(bean);	
					def.setVisible(true);
				}
				if(mode3.isSelected()==true) {
					DuelPlusMoins duel = new DuelPlusMoins(bean);
					duel.setVisible(true);
				}
				
			}

		});
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.setForeground(Color.white);
		retour.setLayout(new GridLayout(3, 50));
		retour.addMouseListener(sListener);
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPaneMenu.getTopLevelAncestor()).dispose() ;
				Lancement menu = new Lancement(bean);	
				menu.setVisible(true);
			}
		});

		contentPaneMenu.add(retour);
		contentPaneMenu.add(espace1);
		espace1.setPreferredSize(new Dimension(148,20));
		espace1.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		JLabel mastermind = new JLabel();
		contentPaneMenu.add(mastermind);
		mastermind.setText(" Plus ou Moins ");
		mastermind.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.534f, 0.85f, 0.34f)));
		mastermind.setForeground(Color.white);
		mastermind.setFont(impact2);
		mastermind.setOpaque(true);
		mastermind.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		JPanel espaceMaster = new JPanel();
		espaceMaster.setPreferredSize(new Dimension(195,20));
		espaceMaster.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPaneMenu.add(espaceMaster);
		contentPaneMenu.add(regle);
		contentPaneMenu.add(blocMode);
		contentPaneMenu.add(espace2);
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPaneMenu.add(lancer,BorderLayout.SOUTH);
	}

	class SourisListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {

		}

		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==retour) {
				retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
			if(arg0.getSource()==lancer) {
				lancer.setBackground(Color.getHSBColor(0.234f, 0.75f, 0.99f));
				lancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.64f)));
			}
			if(arg0.getSource()==mode1) {
				mode1.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.84f));
			}
			if(arg0.getSource()==mode2) {
				mode2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.84f));
			}
			if(arg0.getSource()==mode3) {
				mode3.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.84f));
			}
		
		}

		public void mouseExited(MouseEvent arg0) {
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			lancer.setBackground(Color.getHSBColor(0.234f, 0.65f, 0.94f));
			lancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			mode1.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
			mode2.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
			mode3.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		}
		public void mousePressed(MouseEvent arg0) {

		}
		public void mouseReleased(MouseEvent arg0) {
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			lancer.setBackground(Color.getHSBColor(0.234f, 0.65f, 0.94f));
			lancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			mode1.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
			mode2.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
			mode3.setBackground(Color.getHSBColor(0.534f, 0.25f, 0.74f));
		}
	}
}


