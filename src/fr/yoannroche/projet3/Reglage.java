package fr.yoannroche.projet3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * 
 * @author El-ra
 *
 */
public class Reglage extends JFrame{
	Reglage reglage;
	private JPanel contentPane= new JPanel();
	private JPanel cadreCases = new JPanel();
	private JPanel cadreTentative = new JPanel();
	private JPanel cadreDev = new JPanel();
	private JPanel espace1 = new JPanel();
	ButtonGroup groupCase = new ButtonGroup();
	ButtonGroup groupTenta = new ButtonGroup();
	JCheckBox check = new JCheckBox(" Mode développeur ");
	JButton retour = new JButton (" Retour ");
	JButton sauv = new JButton(" Sauvergarder ");
	Font arial = new Font ("arial", 11,11);
	int  [] clavier = {4,5,6,7,8};
	JRadioButton [] button = new JRadioButton[clavier.length];
	private JRadioButton bouton[];
	int  [] tenta = {4,5,6,7,8};
	JRadioButton [] buttonTen = new JRadioButton[tenta.length];
	private JRadioButton boutonTentative[];
	private String bloc;
	private int cases;

	private int tentatives;
	private int dev;
	private int couleurs;

	int tentative;

	public Reglage() {

		this.setTitle(" Reglages ");
		this.setSize(330, 310);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

		initRetour();
		initCases();
		initTentative();
		initDev();
		initSauv();
	}

	private void initSauv() {
		sauv.setBackground(Color.getHSBColor(0.434f, 0.55f, 0.64f));
		sauv.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		sauv.setForeground(Color.white);
		sauv.setLayout(new GridLayout(3, 50));
		sauv.addMouseListener(new SourisListener());
		sauv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				sauvegardeReglage();
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				Lancement menu = new Lancement();
				menu.setVisible(true);

			}
		});
		JPanel espace2 = new JPanel();
		contentPane.add(espace2);
		contentPane.add(sauv);
		espace2.setPreferredSize(new Dimension(150,20));
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

	}

	private void initRetour() {
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
		contentPane.add(retour);
		contentPane.add(espace1);
		espace1.setPreferredSize(new Dimension(200,20));
		espace1.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

	}

	private void initDev() {
		JLabel dev =new JLabel();
		dev.setOpaque(true);
		dev.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		dev.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		dev.setForeground(Color.orange);
		dev.setFont(arial);
		dev.setText(" Acive le mode développeur pour voir toutes les solutions ");
		cadreDev.setPreferredSize(new Dimension(300,60));
		cadreDev.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreDev.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		check.setForeground(Color.WHITE);
		check.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		cadreDev.add(dev);
		cadreDev.add(check);
		contentPane.add(cadreDev);

	}

	private void initTentative() {
		JLabel tentative =new JLabel();
		cadreTentative.setPreferredSize(new Dimension(280,60));
		cadreTentative.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreTentative.add(tentative);
		contentPane.add(cadreTentative);
		tentative.setOpaque(true);
		tentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentative.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		tentative.setForeground(Color.orange);
		tentative.setFont(arial);
		tentative.setText("    Nombres de tentative pour trouver le code    ");

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

	private void initCases() {
		JLabel cases =new JLabel();

		cadreCases.setPreferredSize(new Dimension(280,60));
		cadreCases.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		cadreCases.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreCases.add(cases);
		contentPane.add(cadreCases);
		cases.setOpaque(true);
		cases.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cases.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		cases.setForeground(Color.orange);
		cases.setFont(arial);
		cases.setText("    Nombres de chiffres ou couleurs à trouver    ");

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
	 */
	public void sauvegardeReglage() {
		FileInputStream stream = null;
		try {
			Properties prop = new Properties() ;
			File fProp = new File("ressources/Config.properties") ;
			stream = new FileInputStream(fProp) ;
			prop.load(stream) ;

			if(check.isSelected()==true) {
				String newDev=Integer.toString(1) ;
				prop.setProperty("dev",newDev);
				setDev(1);
			}
			else{
				String newDev=Integer.toString(0) ;
				prop.setProperty("dev",newDev) ;
				setDev(0);
			}

			for(int i=0;i<clavier.length;i++) {
				if(bouton[i].isSelected()==true) {
					cases=(i+4);
					String newCases=Integer.toString(cases) ;
					prop.setProperty("cases",newCases) ;
					setCases(cases);

				}

			}

			for(int i=0;i<tenta.length;i++) {

				if(boutonTentative[i].isSelected()==true) {
					tentative=(i+4);
					String newTenta=Integer.toString(tentative) ;
					prop.setProperty("tentatives",newTenta) ;
					setTentatives(tentative);
				}
			}
			
			
			FileOutputStream oStream = new FileOutputStream(fProp) ;
			prop.store(oStream,"Reglage") ;
			stream.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getCases() {
		return cases;
	}

	public void setCases(int cases) {
		this.cases = cases;
	}

	public int getTentatives() {
		return tentatives;
	}

	public void setTentatives(int tentatives) {
		this.tentatives = tentatives;
	}

	public int getDev() {
		return dev;
	}

	public void setDev(int dev) {
		this.dev = dev;
	}

	public int getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(int couleurs) {
		this.couleurs = couleurs;
	}
}

