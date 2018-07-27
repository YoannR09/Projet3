package fr.yoannroche.projet3.plusmoins.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.plusmoins.model.ChallengerPlusMoinsModel;
import fr.yoannroche.projet3.plusmoins.view.ChallengerPlusMoins.SourisListener;
import fr.yoannroche.projet3.plusmoins.view.ChallengerPlusMoins.SourisListener2;

public class DuelPlusMoins extends JFrame{
	
	private JPanel contentPane = new JPanel();
	private JButton retour = new JButton(" Retour ");
	private JButton supprimer = new JButton(" Suppr ");
	private JButton ok = new JButton(" Ok ");
	private static String tentatives;
	private static String bloc;
	private JPanel blocProposition = new JPanel();
	private JPanel blocTest = new JPanel();
	private JLabel tentative = new JLabel();
	private JLabel codeSecret = new JLabel();
	private JLabel tentativeIA =new JLabel();
	static ImageIcon ordi0 = new ImageIcon("images/ordi0.png");
	JLabel image = new JLabel();
	int  [] clavier = {0,1,2,3,4,5,6,7,8,9};
	JButton [] button = new JButton[clavier.length];
	private JTextField proposition = new JTextField();
	private JLabel infosTentative = new JLabel();
	private JButton bouton[];
	private int nombreClick = 0;
	private int nombreCoup = 0;
	Font impact = new Font ("impact", 17,17);
	Font arial = new Font ("arial", 12,12);
	
	
	public DuelPlusMoins() {
	
	this.setTitle("Duel");
	this.setSize(450,400);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setContentPane(contentPane);
	this.setResizable(false);
	contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
	
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	tentatives = reglage.getString("tentatives");
	bloc = reglage.getString("cases");
	
	initRegle();
	initCadreJoueur();
	initVs();
	initCadreOrdi();
	initBlocProposition();
	initBlocTest();
	
	
	}
	
	private void initVs() {
		JLabel vs = new JLabel();
		
		vs.setText(" V.S " );
		vs.setOpaque(true);
		vs.setFont(impact);
		vs.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		vs.setBackground(Color.getHSBColor(0.143f, 0.84f, 0.86f));
		contentPane.add(vs);
		
	}

	private void initCadreOrdi() {
		JLabel ordi = new JLabel();
		ordi.setText(" Ordinateur ");
		ordi.setOpaque(true);
		ordi.setFont(arial);
		ordi.setBorder(BorderFactory.createLineBorder(Color.black));
		JTextArea dialog = new JTextArea();
		dialog.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		dialog.setPreferredSize(new Dimension(150,80));
		dialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		dialog.setEditable(false);
		dialog.setText(" \n Je suis prêt \n c'est quand vous voulez ! ");
		JPanel tentativePanel = new JPanel();
		tentativePanel.setPreferredSize(new Dimension(150,55));
		tentativePanel.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		tentativePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativeIA.setOpaque(true);
		tentativeIA.setBackground(Color.white);
		tentativeIA.setPreferredSize(new Dimension(120,40));
		tentativeIA.setHorizontalAlignment(JLabel.CENTER);
		tentativeIA.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativeIA.setFont(impact);
		tentativeIA.setText(" Tentative IA ");
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(65,5));
		espace2.setBackground(Color.DARK_GRAY);
		espace2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		
		JPanel cadreOrdi = new JPanel();
		cadreOrdi.setPreferredSize(new Dimension(180,185));
		cadreOrdi.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreOrdi.add(ordi);
		cadreOrdi.add(dialog);
		cadreOrdi.add(espace2);
		tentativePanel.add(tentativeIA);
		cadreOrdi.add(tentativePanel);
		contentPane.add(cadreOrdi);
		
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(520,5));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espace);
		
	}

	private void initCadreJoueur() {
		JLabel joueur = new JLabel();
		joueur.setText(" Vous ");
		joueur.setOpaque(true);
		joueur.setFont(arial);
		joueur.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel tentativePanel = new JPanel();
		tentativePanel.setPreferredSize(new Dimension(150,55));
		tentativePanel.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		tentativePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentative.setOpaque(true);
		tentative.setBackground(Color.white);
		tentative.setPreferredSize(new Dimension(120,40));
		tentative.setBorder(BorderFactory.createLineBorder(Color.black));
		tentative.setHorizontalAlignment(JLabel.CENTER);
		tentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentative.setFont(impact);
		tentative.setText(" Votre tentative ");
		tentativePanel.add(tentative);
		JPanel infosCadre = new JPanel();
		infosCadre.setPreferredSize(new Dimension(150,55));
		infosCadre.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		infosCadre.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		infosTentative.setOpaque(true);
		infosTentative.setBackground(Color.WHITE);
		infosTentative.setBorder(BorderFactory.createLineBorder(Color.black));
		infosTentative.setPreferredSize(new Dimension(120,40));
		infosTentative.setHorizontalAlignment(JLabel.CENTER);
		infosTentative.setFont(impact);
		infosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		infosTentative.setText(" Les + - ou = ");
		infosCadre.add(infosTentative);
		JPanel espace = new JPanel();
		espace.setPreferredSize(new Dimension(165,30));
		espace.setBackground(Color.DARK_GRAY);
		espace.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		JLabel codeText = new JLabel();
		codeText.setText("Votre code secret : ");
		codeText.setFont(arial);
		codeText.setForeground(Color.WHITE);
		codeSecret.setText("????");
		codeSecret.setForeground(Color.getHSBColor(0.143f, 0.84f, 0.86f));
		codeSecret.setFont(arial);
		
		espace.add(codeText);
		espace.add(codeSecret);
		JPanel cadreJ = new JPanel();
		cadreJ.setPreferredSize(new Dimension(180,185));
		cadreJ.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreJ.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreJ.add(joueur);
		cadreJ.add(tentativePanel);
		cadreJ.add(infosCadre);
		cadreJ.add(espace);
		contentPane.add(cadreJ);
		
	}

	private void initRegle() {

		JPanel espaceRetour = new JPanel ();
		espaceRetour.setPreferredSize(new Dimension(320,20));
		espaceRetour.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.setForeground(Color.white);
		retour.addMouseListener(new SourisListener());
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				FenetreMenuPlusMoins menu = new FenetreMenuPlusMoins();	
				menu.setVisible(true);
			}
		});
		JPanel blocRegle = new JPanel();
		blocRegle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocRegle.setPreferredSize(new Dimension(500,40));
		JLabel regle = new JLabel();
		int tentativeInt = Integer.parseInt(tentatives);
		int casesInt = Integer.parseInt(bloc);
		regle.setText("Vous avez "+ tentativeInt +" tentative pour trouver le code secret à "+ casesInt +" chiffres.");
		regle.setFont(arial);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(520,5));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espace);

	}
	private void initBlocTest() {

		blocTest.add(proposition);
		blocTest.setPreferredSize(new Dimension(130,40));
		blocTest.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		blocTest.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		proposition.setForeground(Color.DARK_GRAY);
		proposition.setFont(impact);
		proposition.setPreferredSize(new Dimension(120,27));
		proposition.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(blocTest);
		ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ok.addMouseListener(new SourisListener());
		ok.addActionListener(new ActionListener(){ // Probleme au deuxieme ajouts , un espace ce place devant le nombre generé.
			public void actionPerformed(ActionEvent event){ 
				try {
					
					
				}catch(Exception e) {
					JOptionPane joperreur = new JOptionPane(); // Si la personne décide de ne pas utiliser le clavier et dépasse la longueur du code secret
					joperreur.showMessageDialog(null,"Votre code est trop long !!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}  
		});
		supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		supprimer.addMouseListener(new SourisListener());
		supprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				proposition.setText("");
				nombreClick = 0;
				blocProposition.setVisible(true);
			}  
		});
		contentPane.add(supprimer);
		contentPane.add(ok);

	}
	private void initBlocProposition() {

		blocProposition.setPreferredSize(new Dimension(160,65));
		blocProposition.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocProposition.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		this.bouton = new JButton[10];
		int i = 0;
		for(int c : clavier){
			this.bouton[i] = new JButton(String.valueOf(c).toUpperCase());
			bouton[i].setPreferredSize(new Dimension(23,23));
			bouton[i].setBackground(Color.DARK_GRAY);
			bouton[i].setBorder(BorderFactory.createLineBorder(Color.black));
			bouton[i].setForeground(Color.white);
			bouton[i].addMouseListener(new SourisListener2());
			blocProposition.add(bouton[i]).setEnabled(true);
			i++;
		}
		contentPane.add(blocProposition);
	}
	
	class SourisListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {

			if(arg0.getSource()==ok) {
				ok.setBackground(Color.getHSBColor(0.345f, 0.58f, 0.88f));
				ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.64f)));
			}
			if(arg0.getSource()==supprimer) {
				supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.64f)));
				supprimer.setBackground(Color.getHSBColor(0.534f, 0.65f, 0.84f));
			}
			if(arg0.getSource()==retour) {
				retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
		}

		public void mouseExited(MouseEvent arg0) {

			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			retour.setForeground(Color.white);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {

			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			retour.setForeground(Color.white);
			ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
			ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		}
	}
	
	class SourisListener2 implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);
			nombreClick++;
			ChallengerPlusMoinsModel.RangeWord(blocProposition,nombreClick);
		}
		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.white));
		}
		public void mouseExited(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.black));
			if(((JButton)arg0.getSource()).isEnabled()==false) {
				((JButton)arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.orange));
			}
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}

}
