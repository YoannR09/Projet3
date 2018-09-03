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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.yoannroche.projet3.plusmoins.model.DuelPlusMoinsModel;

public class DuelPlusMoins extends JFrame{

	private JPanel contentPane = new JPanel();
	private JLabel attention = new JLabel();
	private JButton retour = new JButton(" Retour ");
	private JButton supprimer = new JButton(" Effacer ");
	private JButton aide = new JButton(" Aide ");
	private JButton ok = new JButton(" Ok ");
	private JButton fin = new JButton(" Ok ");
	private JButton refresh = new JButton("⟲");
	private String bloc;
	private JPanel cadreJ = new JPanel();
	private JPanel cadreOrdi = new JPanel();
	private JPanel blocProposition = new JPanel();
	private JPanel blocTest = new JPanel();
	private JLabel tentative = new JLabel();
	private JLabel codeSecret = new JLabel();
	private JLabel tentativeIA =new JLabel();
	private JTextArea dialog = new JTextArea();
	private int  [] clavier = {0,1,2,3,4,5,6,7,8,9};
	private JButton bouton[];
	private String [] indice = {"+","-","="};
	private JButton indi[];
	private JTextField proposition = new JTextField();
	private JLabel infosTentative = new JLabel();
	private JLabel indiceDev = new JLabel();
	private int nombreClick = 0;
	private int click=0;
	private Font impact = new Font ("impact", 17,17);
	private Font arial = new Font ("arial", 12,12);
	private Font arial2 = new Font ("arial", 10,10);
	private JPanel espaceDev = new JPanel ();
	private int nombreTour=0;
	private JLabel tour = new JLabel();
	
	
	private DuelPlusMoinsModel duel = new DuelPlusMoinsModel();


	public DuelPlusMoins() {

		this.setTitle("Duel");
		this.setSize(460,470);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		reglage.getString("tentatives");
		bloc = reglage.getString("cases");

		initRegle();
		initCadreJoueur();
		initVs();
		initCadreOrdi();
		initBlocProposition();
		initBlocTest();
		initCadreDev();
	}

	private void initCadreDev() {
		ResourceBundle reglage = ResourceBundle.getBundle("Config");
		String devStatus = reglage.getString("dev");
		int devMode = Integer.parseInt(devStatus);
		if(devMode==1) {
			JLabel codeSecret = new JLabel();
			codeSecret.setFont(arial2);
			codeSecret.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
			codeSecret.setText("");
			JLabel text = new JLabel();
			text.setText("Code secret de l'ordinateur :");
			text.setFont(arial2);
			text.setForeground(Color.WHITE);
			JLabel text2 = new JLabel();
			text2.setText(" Les indices à indiquer sont :");
			text2.setFont(arial2);
			text2.setForeground(Color.WHITE);
			espaceDev.add(text);
			espaceDev.add(codeSecret);
			duel.dev(codeSecret);
			espaceDev.add(text2);
			espaceDev.add(indiceDev);
			indiceDev.setForeground(Color.orange);
			indiceDev.setFont(arial2);			
		}
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
		JScrollPane scrollPane =
			      new JScrollPane(dialog,
	                      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	                      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dialog.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		dialog.setEditable(false);
		dialog.setText(" \n Je suis prêt \n c'est quand vous voulez ! ");
		JPanel tentativePanel = new JPanel();
		tentativePanel.setPreferredSize(new Dimension(150,70));
		tentativePanel.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		tentativePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativeIA.setOpaque(true);
		tentativeIA.setBackground(Color.white);
		tentativeIA.setPreferredSize(new Dimension(130,30));
		tentativeIA.setHorizontalAlignment(JLabel.CENTER);
		tentativeIA.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativeIA.setFont(arial);
		tentativeIA.setText("Entrez les indices");
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(65,5));
		espace2.setBackground(Color.DARK_GRAY);
		espace2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreOrdi.setPreferredSize(new Dimension(180,205));
		cadreOrdi.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreOrdi.add(ordi);
		cadreOrdi.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(160, 80));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreOrdi.add(espace2);
		tentativePanel.add(tentativeIA);
		refresh.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
		refresh.setBorder(BorderFactory.createLineBorder(Color.black));
		refresh.setBackground(Color.DARK_GRAY);
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){ 
				tentativeIA.setText("");
			}  
		});
		refresh.setPreferredSize(new Dimension(20,20));
		cadreOrdi.add(tentativePanel);
		contentPane.add(cadreOrdi);
		this.indi = new JButton[3];
		int i = 0;
		for(@SuppressWarnings("unused") String c : indice){
			this.indi[i] = new JButton(indice[i]);
			indi[i].setPreferredSize(new Dimension(20,20));
			indi[i].setBackground(Color.DARK_GRAY);
			indi[i].setBorder(BorderFactory.createLineBorder(Color.black));
			indi[i].setForeground(Color.white);
			indi[i].addMouseListener(new SourisListener3());
			tentativePanel.add(indi[i]).setEnabled(true);
			i++;
		}
		fin.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		tentativePanel.add(fin);
		tentativePanel.add(refresh);
		espaceDev.setPreferredSize(new Dimension(520,25));
		espaceDev.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espaceDev);
	}

	private void initCadreJoueur() {
		JLabel joueur = new JLabel();
		joueur.setText(" Vous ");
		joueur.setOpaque(true);
		joueur.setFont(arial);
		joueur.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel tentativePanel = new JPanel();
		tentativePanel.setPreferredSize(new Dimension(150,53));
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
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(65,5));
		espace2.setBackground(Color.DARK_GRAY);
		espace2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativePanel.add(tentative);
		JPanel infosCadre = new JPanel();
		infosCadre.setPreferredSize(new Dimension(150,53));
		infosCadre.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		infosCadre.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		infosTentative.setOpaque(true);
		infosTentative.setBackground(Color.WHITE);
		infosTentative.setBorder(BorderFactory.createLineBorder(Color.black));
		infosTentative.setPreferredSize(new Dimension(120,40));
		infosTentative.setHorizontalAlignment(JLabel.CENTER);
		infosTentative.setFont(impact);
		infosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		infosTentative.setText(" Indices + - ou = ");
		JPanel espaceCode = new JPanel();
		espaceCode.setPreferredSize(new Dimension(160,5));
		espaceCode.setBackground(Color.DARK_GRAY);
		espaceCode.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		infosCadre.add(infosTentative);
		JPanel espace = new JPanel();
		espace.setPreferredSize(new Dimension(165,30));
		espace.setBackground(Color.DARK_GRAY);
		espace.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		JLabel codeText = new JLabel();
		codeText.setText("Votre code secret : ");
		codeText.setFont(arial);
		codeText.setForeground(Color.WHITE);
		codeSecret.setText("?");
		codeSecret.setForeground(Color.getHSBColor(0.143f, 0.84f, 0.86f));
		codeSecret.setFont(arial);
		espace.add(codeText);
		espace.add(codeSecret);
		cadreJ.setPreferredSize(new Dimension(180,205));
		cadreJ.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreJ.add(joueur);
		cadreJ.add(tentativePanel);
		cadreJ.add(espace2);
		cadreJ.add(infosCadre);
		cadreJ.add(espaceCode);
		cadreJ.add(espace);
		cadreJ.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
		contentPane.add(cadreJ);
	}

	private void initRegle() {

		JPanel espaceRetour = new JPanel ();
		espaceRetour.setPreferredSize(new Dimension(250,20));
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
		aide.setBackground(Color.getHSBColor(0.134f, 0.85f, 0.94f));
		aide.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		aide.setForeground(Color.DARK_GRAY);
		aide.addMouseListener(new SourisListener());
		aide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				AideDuelPlusMoins aideMenu = new AideDuelPlusMoins();	
				aideMenu.setVisible(true);
			}
		});
		JPanel blocRegle = new JPanel();
		blocRegle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocRegle.setPreferredSize(new Dimension(500,45));
		JLabel regle = new JLabel();
		int casesInt = Integer.parseInt(bloc);
		regle.setText(" Le plus rapide à trouver le code secret à "+ casesInt +" chiffres de l'autre gagne.");
		regle.setFont(arial);
		attention.setFont(arial2);
		attention.setText(" Le cadre jaune indique ou le joueur doit poser son attention ");
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		contentPane.add(aide);
		blocRegle.add(regle);
		blocRegle.add(attention);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		tour.setText(" Nombre de tour : "+" "+0+" ");
		tour.setOpaque(true);
		tour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tour.setBackground(Color.getHSBColor(0.544f, 0.15f, 0.26f));
		tour.setForeground(Color.WHITE);
		espace.add(tour);
		espace.setPreferredSize(new Dimension(520,30));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espace);
	}
	
	private void initBlocTest() {

		JPanel blocBouton = new JPanel();
		blocBouton.setPreferredSize(new Dimension(100,58));
		blocBouton.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocBouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocTest.add(proposition);
		blocTest.setPreferredSize(new Dimension(130,40));
		blocTest.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		blocTest.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		proposition.setForeground(Color.DARK_GRAY);
		proposition.setFont(arial2);
		proposition.setPreferredSize(new Dimension(120,27));
		proposition.setBorder(BorderFactory.createLineBorder(Color.black));
		proposition.setText("Entrez votre code secret");		
		contentPane.add(blocTest);
		ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ok.addMouseListener(new SourisListener());
		ok.addActionListener(new ActionListener(){ // Probleme au deuxieme ajouts , un espace ce place devant le nombre generé.
			public void actionPerformed(ActionEvent event){ 

				ok.setEnabled(false);
				ok.setBackground(Color.getHSBColor(0.345f, 0.38f, 0.58f));
				duel.okClick(proposition,codeSecret,tentative,infosTentative,contentPane,dialog,indiceDev,cadreJ,cadreOrdi,ok,fin);
				blocProposition.setVisible(true);
				nombreClick=0;
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
		fin.setPreferredSize(new Dimension(25,14));
		fin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
		fin.setBackground(Color.getHSBColor(0.154f, 0.45f, 0.44f));
		fin.setEnabled(false);
		fin.addActionListener(new ActionListener(){ // Probleme au deuxieme ajouts , un espace ce place devant le nombre generé.
			public void actionPerformed(ActionEvent event){ 		
				duel.finClick(tentativeIA,ok,indiceDev,fin,cadreJ,cadreOrdi,tour,dialog);
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
			if(arg0.getSource()==aide) {
				aide.setBackground(Color.getHSBColor(0.134f, 0.95f, 0.99f));
				aide.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.55f, 0.34f)));
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
			aide.setBackground(Color.getHSBColor(0.134f, 0.85f, 0.94f));
			aide.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
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
			aide.setBackground(Color.getHSBColor(0.134f, 0.85f, 0.94f));
			aide.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		}
	}

	class SourisListener2 implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			if(nombreClick==0) {
				supprimer.doClick();
			}
			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);
			nombreClick++;
			duel.RangeWord(blocProposition,nombreClick);
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
	class SourisListener3 implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			if(click==0) {
				refresh.doClick();
			}
			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			tentativeIA.setText(tentativeIA.getText()+entrer);
			click++;

		}
		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBackground(Color.GRAY);
		}
		public void mouseExited(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBackground(Color.DARK_GRAY);

		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}

}
