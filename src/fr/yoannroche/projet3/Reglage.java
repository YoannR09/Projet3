package fr.yoannroche.projet3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Class qui gère le réglage du fichier properties
 * Les réglages sont modifiable dans l'application.
 * @author El-ra
 *
 */
public class Reglage extends JFrame {


	private BeanReglage			bean			;
	private JPanel				contentPane		= new JPanel();
	private JPanel				espace1			= new JPanel();
	private ButtonGroup			groupCase		= new ButtonGroup();
	private ButtonGroup			groupCoul		= new ButtonGroup();
	private ButtonGroup			groupTenta		= new ButtonGroup();
	private JCheckBox			check			= new JCheckBox(" Mode développeur ");
	private JButton				retour			= new JButton (" Retour ");
	private JButton				sauv			= new JButton(" Sauvergarder ");
	private Font				arial			= new Font ("arial", 11,11);
	private Font				impact			= new Font ("impact", 17,15);
	private int[]				clavier			= {4,5,6,7,8};
	private JRadioButton[]		bouton;
	private int[]				tenta			= {4,5,6,7,8};
	private int[]				coul			= {4,5,6,7,8,9,10};
	private JRadioButton[]		boutonTentative	;
	private JRadioButton[]		boutonCouleur	;


	public Reglage(BeanReglage bean) {

		this.setTitle(" Reglages ");
		this.setSize(340, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.bean = bean;

		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

		initRetour();
		initCases();
		initTentative();
		initDev();
		initCouleur();
		initSauv();
	}

	/**
	 * Méthode qui gère le cadre pour choisir le nombre de couleur possible
	 */
	private void initCouleur() {
		
		JPanel toutJeu = new JPanel();
		JLabel textJeu = new JLabel();
		textJeu.setText(" Pour le Mastermind ");
		textJeu.setFont(impact);
		toutJeu.setPreferredSize(new Dimension(300,25));
		toutJeu.add(textJeu);
		contentPane.add(toutJeu);
	
		JPanel cadreCouleur = new JPanel();
		JLabel couleur =new JLabel();
		cadreCouleur.setPreferredSize(new Dimension(330,60));
		cadreCouleur.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreCouleur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreCouleur.add(couleur);
		contentPane.add(cadreCouleur);
		couleur.setOpaque(true);
		couleur.setPreferredSize(new Dimension(320,20));
		couleur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		couleur.setBackground(Color.DARK_GRAY);
		couleur.setForeground(Color.white);
		couleur.setFont(arial);
		couleur.setText("  Nombres de couleurs utilisables ");
		this.boutonCouleur = new JRadioButton[7];
		int i = 0;
		for(int c : coul){
			this.boutonCouleur[i] = new JRadioButton(String.valueOf(c).toUpperCase());
			groupCoul.add(boutonCouleur[i]);
			boutonCouleur[i].setPreferredSize(new Dimension(36,20));
			boutonCouleur[i].setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
			boutonCouleur[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			boutonCouleur[i].setForeground(Color.white);
			cadreCouleur.add(boutonCouleur[i]).setEnabled(true);
			i++;
		}		
	}

	/**
	 * Méthode qui gère le bouton sauvergarde.
	 * @param temp 
	 */
	private void initSauv() {
		sauv.setBackground(Color.getHSBColor(0.434f, 0.55f, 0.64f));
		sauv.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		sauv.setForeground(Color.white);
		sauv.addMouseListener(new SourisListener());
		sauv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				sauvegardeReglage();
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				Lancement menu = new Lancement(bean);
				menu.setVisible(true);

			}
		});
		JPanel espace2 = new JPanel();
		contentPane.add(espace2);
		contentPane.add(sauv);
		espace2.setPreferredSize(new Dimension(150,20));
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

	}
	/**
	 * Méthode qui gère le bouton retour.
	 */
	private void initRetour() {
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.setForeground(Color.white);
		retour.addMouseListener(new SourisListener());
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				Lancement menu = new Lancement(bean);	
				menu.setVisible(true);
			}
		});
		contentPane.add(retour);
		contentPane.add(espace1);
		espace1.setPreferredSize(new Dimension(200,20));
		espace1.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		
		JPanel toutJeu = new JPanel();
		JLabel textJeu = new JLabel();
		textJeu.setText(" Pour tout les jeux ");
		textJeu.setFont(impact);
		toutJeu.setPreferredSize(new Dimension(300,25));
		toutJeu.add(textJeu);
		contentPane.add(toutJeu);

	}
	/**
	 * Méthode qui gère le bouton développeur, si il est coché alors le mode développeur sera actif.
	 */
	private void initDev() {
		
		JPanel cadreDev = new JPanel();
		JLabel dev =new JLabel();
		dev.setOpaque(true);
		dev.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		dev.setBackground(Color.DARK_GRAY);
		dev.setForeground(Color.white);
		dev.setFont(arial);
		dev.setText("  Acive le mode développeur pour voir toutes les solutions ");
		dev.setPreferredSize(new Dimension(320,20));
		cadreDev.setPreferredSize(new Dimension(330,60));
		cadreDev.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreDev.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		check.setForeground(Color.WHITE);
		check.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		cadreDev.add(dev);
		cadreDev.add(check);
		contentPane.add(cadreDev);
	}
	/**
	 * Méthode qui gère le nombre de tentatives que vous vouelz avoir.
	 */
	private void initTentative() {
		JPanel cadreTentative = new JPanel();
		JLabel tentative =new JLabel();
		cadreTentative.setPreferredSize(new Dimension(330,60));
		cadreTentative.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreTentative.add(tentative);
		contentPane.add(cadreTentative);
		tentative.setOpaque(true);
		tentative.setPreferredSize(new Dimension(320,20));
		tentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentative.setBackground(Color.DARK_GRAY);
		tentative.setForeground(Color.white);
		tentative.setFont(arial);
		tentative.setText("  Nombres de tentatives pour trouver le code ");

		this.boutonTentative = new JRadioButton[5];
		int i = 0;
		for(int c : tenta){
			this.boutonTentative[i] = new JRadioButton(String.valueOf(c).toUpperCase());
			groupTenta.add(boutonTentative[i]);
			boutonTentative[i].setPreferredSize(new Dimension(34,20));
			boutonTentative[i].setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
			boutonTentative[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			boutonTentative[i].setForeground(Color.white);
			cadreTentative.add(boutonTentative[i]).setEnabled(true);
			i++;
		}

		JPanel espace = new JPanel();
		espace.setPreferredSize(new Dimension(150,5));
		espace.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		espace.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(espace);
	}
	/**
	 * Méthode qui gère le nombre de cases que vous voulez avoir.
	 */
	private void initCases() {
		
		JLabel cases =new JLabel();
		JPanel cadreCases = new JPanel();
		cadreCases.setPreferredSize(new Dimension(330,60));
		cadreCases.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreCases.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreCases.add(cases);
		contentPane.add(cadreCases);
		cases.setOpaque(true);
		cases.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cases.setBackground(Color.DARK_GRAY);
		cases.setForeground(Color.white);
		cases.setPreferredSize(new Dimension(320,20));
		cases.setFont(arial);
		cases.setText("  Nombres de chiffres ou couleurs à trouver ");
		this.bouton = new JRadioButton[8];
		int i = 0;
		for(int c : clavier){
			this.bouton[i] = new JRadioButton(String.valueOf(c).toUpperCase());
			groupCase.add(bouton[i]);
			bouton[i].setPreferredSize(new Dimension(34,20));
			bouton[i].setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
			bouton[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			bouton[i].setForeground(Color.white);
			cadreCases.add(bouton[i]).setEnabled(true);
			i++;
		}
		JPanel espace = new JPanel();
		espace.setPreferredSize(new Dimension(150,5));
		espace.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		espace.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(espace);
	}

	class SourisListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==retour) {
				retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.54f));
				retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
			if(arg0.getSource()==sauv) {
				sauv.setBackground(Color.getHSBColor(0.434f, 0.65f, 0.74f));
				sauv.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
			}
		}

		public void mouseExited(MouseEvent arg0) {
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			sauv.setBackground(Color.getHSBColor(0.434f, 0.55f, 0.64f));
			sauv.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
			retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
			retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
			sauv.setBackground(Color.getHSBColor(0.434f, 0.55f, 0.64f));
			sauv.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.34f)));
		}
	}

	/**
	 * Méthode qui gére le changement des variables dans le fichier Config.properties
	 * @param temp 
	 */
	public void sauvegardeReglage() {

		try {
			Properties prop = new Properties() ;
			File fProp = new File("ressources/Config.properties") ;
			FileInputStream stream = new FileInputStream(fProp) ;
			prop.load(stream) ;

			if(check.isSelected()==true) {	
				prop.setProperty("dev", "1");
				bean.setDev(1);
			}
			else{
				prop.setProperty("dev", "0");
				bean.setDev(0);
			}

			for(int i=0;i<clavier.length;i++) {
				if(bouton[i].isSelected()==true) {
					int cases=(i+4);
					bean.setCases(cases);
					prop.setProperty("cases",Integer.toString(cases));
				}
			}
			for(int i=0;i<tenta.length;i++) {
				if(boutonTentative[i].isSelected()==true) {
					int tentative=(i+4);
					bean.setTentatives(tentative);
					prop.setProperty("tentatives",Integer.toString(tentative));
				}
			}
			for(int i=0;i<coul.length;i++) {
				if(boutonCouleur[i].isSelected()==true) {
					int couleur=(i+4);
					bean.setCouleurs(couleur);
					prop.setProperty("couleur",Integer.toString(couleur));
				}
			}

			FileOutputStream oStream = new FileOutputStream(fProp) ;
			prop.store(oStream,"Reglage") ;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


