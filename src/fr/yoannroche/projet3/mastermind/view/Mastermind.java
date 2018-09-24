package fr.yoannroche.projet3.mastermind.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.mastermind.MastermindMode;
import fr.yoannroche.projet3.mastermind.control.Control;
import fr.yoannroche.projet3.mastermind.model.ChallengerMastermindModel;
import fr.yoannroche.projet3.mastermind.model.DefenseurMastermindModel;
import fr.yoannroche.projet3.mastermind.model.InterfaceModel;
/**
 * Cette class gère l'affichage du jeu Mastermind pour les 3modes de jeu disponible.
 * @author El-ra
 *
 */
public class Mastermind extends JFrame{

	private BeanReglage					bean				;
	private JLabel						regle				= new JLabel();
	private JPanel						contentPane			= new JPanel();
	private JButton						p					= new JButton( " ♢ ");
	private JButton						o					= new JButton(" ♦ ");
	private JButton						fin					= new JButton(" Ok ");
	private JButton						refresh				= new JButton(" ⟲ ");
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
	private ImageIcon					vide				= new ImageIcon("images/couleur/vide.png");
	private IndiceListener				IndicesListener     = new IndiceListener();
	private JLabel						indiceSecret		= new JLabel();
	private JLabel						indice				= new JLabel();
	private JLabel						textProp			= new JLabel();
	private JLabel						vosTentative		= new JLabel();
	private JScrollPane					scrollPane 			=
			new JScrollPane(blocJeu,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private int							placer,changer,nombreClick,placerClick,changerClick									
	= 0;
	final Logger						logger				= LogManager.getLogger();

	/**
	 * Gère l'interface du jeu Mastermind.
	 * @param mode
	 * @param bean 
	 */
	public Mastermind(MastermindMode mode, BeanReglage bean) {

		this.setSize(400, 505);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.bean = bean;
		
		Generateur					gen					= new Generateur(bean);
		int 						couleurs[]			= gen.getCouleurs();
		Control						control				= new Control(bean);
		JLabel						propositionTab[]	= new JLabel[bean.getCases()];
		int							propositionOrdi[]	= new int [bean.getCases()];
		InterfaceModel				model				= new InterfaceModel(bean);
		DefenseurMastermindModel	modelDef			= new DefenseurMastermindModel(bean,propositionTab,propositionOrdi,control,model);
		ChallengerMastermindModel	modelChal			= new ChallengerMastermindModel(bean,control,couleurs,model);
		JLabel						propositionIcon[]	= new JLabel[bean.getCases()]; // Cadre qui affiche les entrées clavier.
		JPanel						blocTentative[]		= new JPanel[bean.getTentatives()]; // Affiche vos tentatives.
		JLabel						blocIndices[]		= new JLabel[bean.getTentatives()]; // Affiche les indices
		
		initRegle(mode);
		initHisto(mode,propositionIcon,blocTentative,blocIndices,model);
		initBlocTenta(mode,propositionIcon,blocTentative,blocIndices,modelChal,model);
		initCadreDev(mode,modelChal,modelDef);

		if(mode.equals(MastermindMode.Challenger)) {
			modeChallenger(model);
		}
		else if(mode.equals(MastermindMode.Defenseur)) {
		    modeDefenseur(mode,propositionIcon,blocTentative,blocIndices,modelDef,model);
		}
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
	}
	/**
	 * Affiche les aides si le mode développeur est actif.
	 * @param mode
	 * @param modelChal 
	 * @param modelDef 
	 */
	private void initCadreDev(MastermindMode mode, ChallengerMastermindModel modelChal, DefenseurMastermindModel modelDef) {

		if(bean.getDev()==1) {
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
				text.setText("Les indices : ");
				espace2.add(indiceSecret);
				indiceSecret.setForeground((Color.WHITE));
			}
		}
	}

	/**
	 * Ecran du jeu qui affiche les tentatives/indices du joueur qui cherche le code secret.
	 * Affiche le clavier pour entrer les propositions.
	 * @param mode
	 * @param blocIndices 
	 * @param blocTentative 
	 * @param propositionIcon 
	 * @param modelChal 
	 * @param model 
	 */
	private void initBlocTenta(MastermindMode mode, JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices, ChallengerMastermindModel modelChal, InterfaceModel model) {

		scrollPane.setPreferredSize(new Dimension(350,205));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocJeu.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		for(int i=0;i<bean.getTentatives();i++) {
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
				for(int i =0;i<bean.getCases();i++) {
					propositionIcon[i].setIcon(vide);
				}
				nombreClick=0;
			}
		});
		JPanel clavier = new JPanel();
		clavier.setBackground(Color.DARK_GRAY);
		this.bouton = new JButton[bean.getCouleurs()];
		for(int i=0;i<bean.getCouleurs();i++){
			this.bouton[i] = new JButton();
			bouton[i].setPreferredSize(new Dimension(20,20));
			bouton[i].setBackground(Color.DARK_GRAY);
			bouton[i].setBorder(BorderFactory.createLineBorder(Color.black));
			bouton[i].setForeground(Color.white);
			bouton[i].addMouseListener(new SourisListener());
			bouton[i].setIcon(new ImageIcon("images/couleur/"+i+".png"));
			bouton[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(nombreClick<bean.getCases()) {
						propositionIcon[nombreClick].setIcon(((JButton)event.getSource()).getIcon());
						propositionIcon[nombreClick].setText("");
						++nombreClick;
						if(nombreClick==bean.getCases()) {
							ok.setEnabled(true);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Vous êtes au maximum de cases possible", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			clavier.add(bouton[i]).setEnabled(true);
		}
		blocTenta.add(clavier);
		JPanel boutonCadre = new JPanel();
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
					modelChal.okClick(blocTentative,propositionIcon,blocIndices,changer,placer,contentPane,bean);
					for(int i =0;i<bean.getCases();i++) {
						propositionIcon[i].setIcon(vide);
					}
				}
				else if(mode.equals(MastermindMode.Defenseur)) {
					vosTentative.setText("  Entrez les indices  ");
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
	 * @param blocIndices 
	 * @param blocTentative 
	 * @param propositionIcon 
	 * @param model 
	 */
	private void initHisto(MastermindMode mode, JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices, InterfaceModel model) {

		votreProp.add(textProp);
		votreProp.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		votreProp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		for(int i=0;i<bean.getCases();i++) {
			propositionIcon[i]= model.createJLabel();// on crée les JLabel et on met dans tab
			propositionIcon[i].setIcon(vide);
			votreProp.add(propositionIcon[i]);
		}
		contentPane.add(scrollPane);
		espace2.setPreferredSize(new Dimension(400,25));
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
	}
	/**
	 * Affiche le clavier pour donner des indices en mode Defenseur.
	 * @param mode
	 * @param blocIndices 
	 * @param blocTentative 
	 * @param propositionIcon 
	 * @param modelDef 
	 */
	private void initBlocIndices(MastermindMode mode, JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices, DefenseurMastermindModel modelDef) {
	
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
				modelDef.finClick(propositionIcon,blocTentative,blocIndices,changerClick, placerClick,contentPane,indice,bean,indiceSecret);
			}
		});
		blocIndice.add(refresh);
		refresh.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		refresh.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		refresh.addActionListener(IndicesListener);
		blocIndice.setVisible(false);
		blocIndice.setPreferredSize(new Dimension(160,65));
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
		espaceRetour.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		retour.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.44f));
		retour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		retour.setForeground(Color.white);
		retour.addMouseListener(new SourisListener());
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((JFrame) contentPane.getTopLevelAncestor()).dispose() ;
				FenetreMenuMaster menu = new FenetreMenuMaster(bean);	
				menu.setVisible(true);
			}
		});
		JPanel blocRegle = new JPanel();
		blocRegle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocRegle.setPreferredSize(new Dimension(500,50));
		regle.setFont(impact);
		JLabel infos = new JLabel();
		infos.setText("♦ : nombre de couleur bien placée.    ♢ : nombre de couleur mal placée.");
		Font comic = new Font("New York",10,10);
		infos.setFont(comic);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		blocRegle.add(infos);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(320,25));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
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
	
	public void modeChallenger(InterfaceModel model) {
		this.setTitle("Challenger");
		textProp.setText("Votre proposition : ");
		regle.setText("Vous avez "+bean.getTentatives()+" tentatives pour trouver le code secret à "+bean.getCases()+" couleurs.");
		model.blocJeuSize(blocJeu,contentPane);
		logger.info("Vous êtes dans le mode challenger du jeu Mastermind");
	}
	public void modeDefenseur(MastermindMode mode, JLabel[] propositionIcon, JPanel[] blocTentative, JLabel[] blocIndices, DefenseurMastermindModel modelDef, InterfaceModel model) {
		this.setTitle("Defenseur");
		initBlocIndices(mode,propositionIcon,blocTentative,blocIndices,modelDef);
		textProp.setText("Votre code secret : ");
		vosTentative.setText("  Entrez votre code secret  ");
		regle.setText(" L'ordinateur à "+bean.getTentatives()+" tentatives pour trouver le code secret à "+bean.getCases()+" couleurs.");
		model.blocJeuSize(blocJeu,contentPane);
		logger.info("Vous êtes dans le mode défenseur du jeu Mastermind");
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


