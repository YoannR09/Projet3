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

import fr.yoannroche.projet3.mastermind.MastermindMode;
import fr.yoannroche.projet3.mastermind.model.ChallengerMastermindModel;
import fr.yoannroche.projet3.mastermind.model.DefenseurMastermindModel;
import fr.yoannroche.projet3.mastermind.model.InterfaceModel;
/**
 * Cette class gère l'affichage du jeu Mastermind pour les 3modes de jeu disponible.
 * @author El-ra
 *
 */
public class Mastermind extends JFrame{

	private DefenseurMastermindModel	modelDef			= new DefenseurMastermindModel();
	private ChallengerMastermindModel	modelChal			= new ChallengerMastermindModel();
	private ResourceBundle				reglage				= ResourceBundle.getBundle("Config");
	private int							cases				= Integer.parseInt(reglage.getString("cases"));
	private int							tentative			= Integer.parseInt(reglage.getString("tentatives"));
	private int							couleur				= Integer.parseInt(reglage.getString("couleur"));
	private int							modeDev				= Integer.parseInt(reglage.getString("dev"));
	private JPanel						contentPane			= new JPanel();
	private JButton						p					= new JButton( " ♢ ");
	private JButton						o					= new JButton(" ♦ ");
	private JButton						r					= new JButton(" R ");
	private JButton						fin					= new JButton(" Ok ");
	private JButton						refresh				= new JButton(" ⟲ ");
	private int							placer				= 0;
	private int							changer				= 0;
	private JPanel						blocIndice			= new JPanel();
	private JPanel						espace2				= new JPanel();
	private JPanel						votreProp			= new JPanel();
	private JButton						ok					= new JButton(" OK ");
	private JButton						supprimer			= new JButton(" ❌ ");
	private JButton						retour				= new JButton(" Retour ");
	private Font						arial				= new Font ("arial", 14,14);
	private Font						impact				= new Font ("impact", 12,12);
	private Font						arial2				= new Font ("arial", 10,10);
	private JButton						bouton[];
	private JPanel						blocJeu				= new JPanel();
	private JLabel						propositionIcon[]	= new JLabel[cases]; // Cadre qui affiche les entrées clavier.
	private JPanel						blocTentative[]		= new JPanel[tentative]; // Affiche vos tentatives.
	private JLabel						blocIndices[]		= new JLabel[tentative]; // Affiche les indices
	private ImageIcon					vide				= new ImageIcon("images/couleur/vide.png");
	private int							nombreClick			= 0;
	private IndiceListener				IndicesListener     = new IndiceListener();
	private int							placerClick			= 0;
	private int							changerClick		= 0;
	private JLabel						indice				= new JLabel();
	private JScrollPane					scrollPane 			=
			new JScrollPane(blocJeu,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	/**
	 * Gère l'interface du jeu Mastermind.
	 * @param mode
	 */
	public Mastermind(MastermindMode mode) {

		this.setSize(400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);

		initRegle(mode);
		initHisto(mode);
		initBlocTenta(mode);
		initCadreDev(mode);

		if(mode.equals(MastermindMode.Challenger)) {
			this.setTitle("Challenger");		
		}
		else if(mode.equals(MastermindMode.Defenseur)) {
			this.setTitle("Defenseur");
			initBlocIndices(mode);
		}
		contentPane.setBackground(Color.getHSBColor(0.504f, 0.54f, 0.55f));
	}
	/**
	 * Affiche les aides si le mode développeur est actif.
	 * @param mode
	 */
	private void initCadreDev(MastermindMode mode) {

		if(modeDev==1) {
			JLabel codeSecret = new JLabel();
			codeSecret.setFont(arial2);
			codeSecret.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
			codeSecret.setText("");
			JLabel text = new JLabel();
			text.setFont(arial2);
			text.setForeground(Color.WHITE);
			espace2.add(text);
			espace2.add(codeSecret);
			if(mode.equals(MastermindMode.Challenger)) {
				text.setText("Code secret : ");
				modelChal.dev(espace2);
			}
			else if(mode.equals(MastermindMode.Defenseur)) {
				text.setText("Code secret : ");
			}
		}
	}

	/**
	 * Ecran du jeu qui affiche les tentatives/indices du joueur qui cherche le code secret.
	 * Affiche le clavier pour entrer les propositions.
	 * @param mode
	 */
	private void initBlocTenta(MastermindMode mode) {

		InterfaceModel model = new InterfaceModel();
		scrollPane.setPreferredSize(new Dimension(350,205));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		model.blocJeuSize(blocJeu);
		blocJeu.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		for(int i=0;i<tentative;i++) {
			blocTentative[i]= model.createJPanel();// on crée les JLabel et on met dans tab
			blocTentative[i].setBackground(Color.DARK_GRAY);
			blocTentative[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			JLabel tenta = new JLabel();
			if(mode.equals(MastermindMode.Challenger)) {
				tenta.setText(" Votre tentative : "+(i+1));
			}
			else if(mode.equals(MastermindMode.Defenseur)) {
				tenta.setText(" Tentative de l'IA : "+(i+1));
			}
			tenta.setForeground(Color.WHITE);
			tenta.setFont(arial);
			blocTentative[i].add(tenta);
			blocJeu.add(blocTentative[i]);
			blocIndices[i]= model.createJLabel();// on crée les JLabel et on met dans tab
			blocIndices[i].setText(" ♦ : ?    ♢ : ? ");
			blocIndices[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			blocIndices[i].setOpaque(true);
			blocIndices[i].setPreferredSize(new Dimension(118,25));
			blocIndices[i].setHorizontalAlignment(JLabel.CENTER);
			blocJeu.add(blocIndices[i]);
		}
		JPanel blocTenta = new JPanel();
		supprimer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		supprimer.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		supprimer.addMouseListener(new SourisListener());
		supprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				for(int i =0;i<cases;i++) {
					propositionIcon[i].setIcon(vide);
				}
				nombreClick=0;
			}
		});
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
					if(nombreClick<cases) {
						propositionIcon[nombreClick].setIcon(((JButton)event.getSource()).getIcon());
						propositionIcon[nombreClick].setText("");
						++nombreClick;
						if(nombreClick==cases) {
							ok.setEnabled(true);
						}
					}
					else {
						System.out.println("trop long");
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
		blocTenta.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ok.addMouseListener(new SourisListener());
		ok.setEnabled(false);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(mode.equals(MastermindMode.Challenger)) {
					modelChal.okClick(blocTentative,propositionIcon,blocIndices,changer,placer,contentPane);
					for(int i =0;i<cases;i++) {
						propositionIcon[i].setIcon(vide);
					}
				}
				else if(mode.equals(MastermindMode.Defenseur)) {
					blocTenta.setVisible(false);
					blocIndice.setVisible(true);
					indice.setText(" ♦ : "+placerClick+"    ♢ : "+changerClick);
					fin.doClick();
				}
				ok.setEnabled(false);
				nombreClick=0;
			}
		});
		contentPane.add(espace2);
		contentPane.add(votreProp);
		contentPane.add(blocTenta);	
	}

	/**
	 * Affiche votre proposition.
	 * @param mode
	 */
	private void initHisto(MastermindMode mode) {

		InterfaceModel model = new InterfaceModel();
		JLabel textProp = new JLabel();
		if(mode.equals(MastermindMode.Challenger)) {
			textProp.setText("Votre proposition : ");
		}
		else if(mode.equals(MastermindMode.Defenseur)) {
			textProp.setText("Votre code secret : ");
		}	
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
		espace2.setBackground(Color.getHSBColor(0.504f, 0.54f, 0.55f));
	}
	/**
	 * Affiche le clavier pour donner des indices en mode Defenseur.
	 * @param mode
	 */
	private void initBlocIndices(MastermindMode mode) {
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
		o.addActionListener(IndicesListener);
		blocTouche.add(p);
		p.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		p.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		p.addActionListener(IndicesListener);
		blocIndice.add(fin);
		fin.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		fin.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		fin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				modelDef.finClick(propositionIcon,blocTentative,blocIndices, changerClick, placerClick);
			}
		});
		blocIndice.add(refresh);
		refresh.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		refresh.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		refresh.addActionListener(IndicesListener);
		blocIndice.setVisible(false);
		blocIndice.setPreferredSize(new Dimension(160,70));
		blocIndice.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocIndice.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		contentPane.add(blocIndice);
	}

	/**
	 * Affiche un résumé des règles dans une barre.
	 * @param mode
	 */
	private void initRegle(MastermindMode mode) {

		JPanel espaceRetour = new JPanel ();
		espaceRetour.setPreferredSize(new Dimension(320,20));
		espaceRetour.setBackground(Color.getHSBColor(0.504f, 0.54f, 0.55f));
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
		regle.setFont(impact);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(320,25));
		espace.setBackground(Color.getHSBColor(0.504f, 0.54f, 0.55f));
		JLabel vosTentative = new JLabel();
		vosTentative.setText("  Entrez vos propositions  ");
		vosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.65f, 0.64f)));
		vosTentative.setOpaque(true);
		vosTentative.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.95f));
		vosTentative.setForeground(Color.BLACK);
		vosTentative.setFont(arial2);
		vosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		espace.add(vosTentative);
		contentPane.add(espace);
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

	class IndiceListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource()==o) {
				++placerClick;
			}
			else if(arg0.getSource()==p) {
				++changerClick;
			}
			else if(arg0.getSource()==refresh) {
				placerClick=0;
				changerClick=0;
			}
			indice.setText(" ♦ : "+placerClick+"    ♢ : "+changerClick);
		}

	}
}


