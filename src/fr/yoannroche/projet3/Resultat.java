package fr.yoannroche.projet3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.yoannroche.projet3.mastermind.MastermindMode;
import fr.yoannroche.projet3.mastermind.control.Control;
import fr.yoannroche.projet3.mastermind.view.Mastermind;
import fr.yoannroche.projet3.mastermind.view.MastermindDuel;
import fr.yoannroche.projet3.plusmoins.view.ChallengerPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.DefenseurPlusMoins;
import fr.yoannroche.projet3.plusmoins.view.DuelPlusMoins;

/**
 * Cette class affiche le résultat en cas de défaite ou victoire.
 * @author yoann
 *
 */
public class Resultat extends JFrame {

	private BeanReglage		bean			;
	private JPanel			contentPane		= new JPanel();
	private JPanel			contentPane2    = new JPanel();
	private JPanel			cadreText		= new JPanel();
	private JButton			relancer		= new JButton(" Relancer ");
	private JButton			home			= new JButton(" Menu ");
	private JButton			quitter			= new JButton(" Quitter ");
	private Font			arial			= new Font ("arial", 12,12);
	private JLabel			text			= new JLabel();
	private int				jeu				= 0;


	public Resultat(JFrame parent, String title,String nombreString, JPanel contentPane2, int jeu, BeanReglage bean){

		super(title);

		this.setSize(400, 110);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.getContentPane().add(contentPane);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		this.contentPane2 = contentPane2;
		this.jeu=jeu;
		this.bean = bean;
		this.setTitle(" Resultat ");

		initText();
		initCadre();
	}

	/**
	 * Affiche le text en victoire/défaite.
	 */
	private void initText() {
		cadreText.setPreferredSize(new Dimension(370,28));
		cadreText.setBackground(Color.DARK_GRAY);
		text.setFont(arial);
		text.setForeground(Color.white);
		cadreText.add(text);
		contentPane.add(cadreText);
	}

	/**
	 * Méthode qui gère l'affichage du cadre.
	 * Gère aussi les boutons et leurs actions.
	 */
	private void initCadre() {
		JPanel cadreBouton = new JPanel();
		cadreBouton.setPreferredSize(new Dimension(230,35));
		cadreBouton.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreBouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		cadreBouton.add(relancer);
		relancer.addMouseListener(new SourisListener());
		relancer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				((JFrame) contentPane2.getTopLevelAncestor()).setVisible(false);
				if(jeu==1) {
					ChallengerPlusMoins chal = new ChallengerPlusMoins(bean);
					chal.setVisible(true);
				}
				if(jeu==2) {
					DefenseurPlusMoins def = new DefenseurPlusMoins(bean);
					def.setVisible(true);
				}
				if(jeu==3) {
					DuelPlusMoins duel = new DuelPlusMoins(bean);
					duel.setVisible(true);
				}
				if(jeu==4) {
					Mastermind master = new Mastermind(MastermindMode.Challenger,bean);
					master.setVisible(true);
				}
				if(jeu==5) {
					Mastermind master = new Mastermind(MastermindMode.Defenseur,bean);
					master.setVisible(true);
				}
				if(jeu==6) {
					MastermindDuel master = new MastermindDuel(bean);
					master.setVisible(true);
				}
			}
		});
		relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		relancer.setForeground(Color.white);
		cadreBouton.add(home);
		home.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				((JFrame) contentPane2.getTopLevelAncestor()).dispose();
				Lancement menu = new Lancement(bean);	
				menu.setVisible(true);
			}
		});
		home.addMouseListener(new SourisListener());
		home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		home.setForeground(Color.white);

		cadreBouton.add(quitter);
		quitter.addMouseListener(new SourisListener());
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				((JFrame) contentPane2.getTopLevelAncestor()).dispose();
			}
		});
		quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		quitter.setForeground(Color.white);
		contentPane.add(cadreBouton);
	}
	/**
	 * Méthode appelée en cas de victoire.
	 */
	public void gagner(){
		text.setText(" Vous avez gagnés ! ");
	}

	/**
	 * Méthode appelée en cas de défaite.
	 * @param couleurs 
	 * @param control 
	 */
	public void perdu(int[] couleurs,Control control,int jeu, String nombre){
		
		text.setText(" Vous avez perdu !");
		if(jeu == 4 || jeu==6) {
			text.setText(text.getText()+" La réponse était : ");
			JLabel [] indice = new JLabel[bean.getCases()];
			for(int i=0;i<bean.getCases();i++) {
				control.chiffreCouleur(couleurs[i],cadreText,indice[i]);
			}
		}
		else if(jeu ==1 || jeu==3) {
			text.setText(text.getText()+" La réponse était : "+nombre);
		}
	}

	class SourisListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
		}
		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==relancer) {
				relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
			if(arg0.getSource()==home) {
				home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
				home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
			}
			if(arg0.getSource()==quitter) {
				quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
		}
		public void mouseExited(MouseEvent arg0) {
			relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			relancer.setForeground(Color.white);
			home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
			relancer.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			relancer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			relancer.setForeground(Color.white);
			home.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			home.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			quitter.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		}
	}
}
