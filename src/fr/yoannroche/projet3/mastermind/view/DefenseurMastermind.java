package fr.yoannroche.projet3.mastermind.view;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.yoannroche.projet3.mastermind.control.Control;
import fr.yoannroche.projet3.mastermind.model.DefenseurMastermindModel;

public class DefenseurMastermind extends JFrame{
	
	Control control = new Control();
	DefenseurMastermindModel model = new DefenseurMastermindModel();
	ResourceBundle reglage = ResourceBundle.getBundle("Config");
	private int cases = Integer.parseInt(reglage.getString("cases"));
	private int couleur = Integer.parseInt(reglage.getString("couleur"));
	private int tentative = Integer.parseInt(reglage.getString("tentatives"));
	private int modeDev = Integer.parseInt(reglage.getString("dev"));
	private JPanel contentPane = new JPanel();
	private int nombreTour=0;
	JPanel blocIndice = new JPanel();
	JPanel espace2 = new JPanel();
	JPanel votreProp = new JPanel();
	JButton ok = new JButton(" OK ");
	JButton supprimer = new JButton(" ❌ ");
	JButton retour = new JButton(" Retour ");
	JButton p = new JButton( " P ");
	JButton o = new JButton(" O ");
	JButton r = new JButton(" R ");
	private JButton fin = new JButton(" Ok ");
	private JButton refresh = new JButton(" ⟲ ");
	Font arial = new Font ("arial", 14,14);
	Font impact = new Font ("impact", 15,15);
	Font arial2 = new Font ("arial", 10,10);
	JPanel prop = new JPanel();
	JButton [] button = new JButton[couleur];
	private JButton bouton[];
	JPanel blocJeu = new JPanel();
	JLabel propositionIcon [] = new JLabel[cases];
	JPanel blocTentative [] = new JPanel[tentative];
	JLabel blocIndices [] = new JLabel[tentative];
	ImageIcon vide = new ImageIcon("images/couleur/vide.png");
	int nombreClick =0;
	private JScrollPane scrollPane =
		      new JScrollPane(blocJeu,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public DefenseurMastermind() {
	
	this.setTitle("Défenseur");
	this.setSize(400, 500);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setContentPane(contentPane);
	this.setResizable(false);
	contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
	
	initRegle();
	initHisto();
	initBlocTenta();
	initBlocIndices();
	
	}
	
	private void initBlocIndices() {
		JLabel indice = new JLabel();
		indice.setOpaque(true);
		indice.setBackground(Color.WHITE);
		indice.setPreferredSize(new Dimension(150,20));
		indice.setText(" Entrez les indices ");
		indice.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.35f, 0.34f)));
		blocIndice.add(indice);
		JPanel blocTouche = new JPanel();
		blocTouche.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocIndice.add(blocTouche);
		blocTouche.add(o);
		o.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		o.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		blocTouche.add(p);
		p.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		p.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		blocTouche.add(r);
		r.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		r.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		blocIndice.add(fin);
		fin.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		fin.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		fin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			  model.finClick(propositionIcon,blocTentative);
			}
		});
		blocIndice.add(refresh);
		refresh.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		refresh.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		blocIndice.setVisible(false);
		blocIndice.setPreferredSize(new Dimension(160,60));
		blocIndice.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		contentPane.add(blocIndice);
	}
	
	private void initCadreDev() {
		
		if(modeDev==1) {
			JLabel codeSecret = new JLabel();
			codeSecret.setFont(arial2);
			codeSecret.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
			codeSecret.setText("");
			JPanel cadreDev = new JPanel();
			JLabel text = new JLabel();
			text.setText("Les indices : ");
			text.setFont(arial2);
			text.setForeground(Color.WHITE);
			cadreDev.add(codeSecret);
			cadreDev.setPreferredSize(new Dimension(50,15));
			cadreDev.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
			espace2.add(cadreDev);
			espace2.add(text);
			espace2.add(codeSecret);
			model.dev(espace2);
		}
		}
	
	private void initBlocTenta() {
			
		scrollPane.setPreferredSize(new Dimension(350,205));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		model.blocJeuSize(blocJeu);
		blocJeu.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		for(int i=0;i<tentative;i++) {
			 blocTentative[i]= model.createJPanel();// on crée les JLabel et on met dans tab
			 blocTentative[i].setBackground(Color.DARK_GRAY);
			 blocTentative[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			 JLabel tenta = new JLabel();
	         tenta.setText(" Tentative de l'ordinateur : " + (i+1));
	         tenta.setForeground(Color.WHITE);
	         tenta.setFont(arial);
			 blocTentative[i].add(tenta);
			 blocIndices[i]= model.createJLabel();// on crée les JLabel et on met dans tab
	         blocIndices[i].setText(" Ok : 0   Pl : 0 ");
	         blocIndices[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
	         blocIndices[i].setOpaque(true);
	         blocIndices[i].setPreferredSize(new Dimension(118,25));
	         blocIndices[i].setHorizontalAlignment(JLabel.CENTER);
			 blocJeu.add(blocTentative[i]);
			 blocJeu.add(blocIndices[i]);
			 
			 
	         
		}
	    JPanel blocTenta = new JPanel();
	    supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		supprimer.addMouseListener(new SourisListener());
	    JPanel clavier = new JPanel();
	    clavier.setBackground(Color.DARK_GRAY);
	    this.bouton = new JButton[couleur];
	    for(int i=0;i<couleur;i++){
			this.bouton[i] = new JButton();
			bouton[i].setPreferredSize(new Dimension(20,20));
			bouton[i].setBackground(Color.DARK_GRAY);
			bouton[i].setBorder(BorderFactory.createLineBorder(Color.black));
			bouton[i].setForeground(Color.white);
			bouton[i].addMouseListener(new SourisListener());
            bouton[i].setIcon(new ImageIcon("images/couleur/"+i+".png"));
            bouton[i].addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent event){
    				propositionIcon[nombreClick].setIcon(((JButton)event.getSource()).getIcon());
    				propositionIcon[nombreClick].setText("");
    				++nombreClick;
    				if(nombreClick==cases) {
    					ok.setEnabled(true);
    				}
    			}
    		});
			clavier.add(bouton[i]).setEnabled(true);
		}
	    blocTenta.add(clavier);
	    JPanel boutonCadre = new JPanel();
	    boutonCadre.setPreferredSize(new Dimension(60,60));
	    boutonCadre.setBackground(Color.DARK_GRAY);
	    boutonCadre.add(supprimer);
	    boutonCadre.add(ok);
	    blocTenta.add(boutonCadre);
	    blocTenta.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
	    blocTenta.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
	    ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ok.addMouseListener(new SourisListener());
		ok.setEnabled(false);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				blocIndice.setVisible(true);
				model.okClick(propositionIcon, blocTentative);
				blocTenta.setVisible(false);
			}
		});
	    contentPane.add(votreProp);
	    contentPane.add(blocTenta);	
	}

	private void initHisto() {
		JLabel textProp = new JLabel();
		textProp.setText("Votre code secret : ");
		votreProp.add(textProp);
		votreProp.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		votreProp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		for(int i=0;i<cases;i++) {
			 propositionIcon[i]= model.createJLabel();// on crée les JLabel et on met dans tab
	         propositionIcon[i].setIcon(vide);
	         votreProp.add(propositionIcon[i]);
		}
		
		contentPane.add(scrollPane);
		espace2.setPreferredSize(new Dimension(400,25));
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espace2);
	}

	private void initRegle() {

		JPanel espaceRetour = new JPanel ();
		espaceRetour.setPreferredSize(new Dimension(270,20));
		espaceRetour.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.setForeground(Color.white);
		retour.addMouseListener(new SourisListener());
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				FenetreMenuMaster menu = new FenetreMenuMaster();	
				menu.setVisible(true);
			}
		});
		JPanel blocRegle = new JPanel();
		blocRegle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocRegle.setPreferredSize(new Dimension(500,40));
		JLabel regle = new JLabel();
		regle.setText("Vous avez "+tentative+" tentatives pour trouver le code secret à "+cases+" couleurs.");
		regle.setFont(arial2);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(320,25));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		JLabel vosTentative = new JLabel();
		vosTentative.setText("  Entrez votre code secret, ensuite les indices  ");
		vosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.65f, 0.64f)));
		vosTentative.setOpaque(true);
		vosTentative.setBackground(Color.DARK_GRAY);
		vosTentative.setForeground(Color.white);
		vosTentative.setFont(arial);
		espace.add(vosTentative);
		contentPane.add(espace);
	}
	
	class SourisListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource()==supprimer) {
				for(int i =0;i<cases;i++) {
					propositionIcon[i].setIcon(vide);
				}
				nombreClick=0;
			}
			if(arg0.getSource()==ok) {
				
				for(int i =0;i<cases;i++) {
					propositionIcon[i].setIcon(vide);
				}
				nombreClick=0;
			}
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
}
