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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.yoannroche.projet3.mastermind.model.DuelMastermindModel;
import fr.yoannroche.projet3.mastermind.model.InterfaceModel;

public class MastermindDuel extends JFrame{

	private DuelMastermindModel			model						= new DuelMastermindModel();
	private InterfaceModel				modelInterface				= new InterfaceModel();
	private IndiceListener				IndicesListener     		= new IndiceListener();
	private ResourceBundle				reglage						= ResourceBundle.getBundle("Config");
	private int							cases						= Integer.parseInt(reglage.getString("cases"));
	private int							tentative					= Integer.parseInt(reglage.getString("tentatives"));
	private int							couleur						= Integer.parseInt(reglage.getString("couleur"));
	private int							modeDev						= Integer.parseInt(reglage.getString("dev"));
	private JPanel                      contentPane					= new JPanel();
	private JButton						ok							= new JButton(" OK ");
	private JButton						supprimer					= new JButton(" ❌ ");
	private JButton						retour						= new JButton(" Retour ");
	private JButton						p							= new JButton( " ♢ ");
	private JButton						o							= new JButton(" ♦ ");
	private JButton						fin							= new JButton(" Ok ");
	private JButton						refresh						= new JButton(" ⟲ ");
	private Font						arial						= new Font ("arial", 14,14);
	private Font						impact						= new Font ("impact", 12,12);
	private Font						arial2						= new Font ("arial", 10,10);
	private JPanel						blocJoueur					= new JPanel();
	private JPanel						blocOrdi					= new JPanel();
	private JPanel						cadreTentative				= new JPanel();
	private JPanel						cadreTentativeOrdi			= new JPanel();
	private JPanel						blocTentative[]				= new JPanel[tentative]; // Affiche vos tentatives.
	private JLabel						blocIndices[]				= new JLabel[tentative]; 
	private JPanel						blocTentativeOrdi[]			= new JPanel[tentative]; // Affiche vos tentatives.
	private JLabel						blocIndicesOrdi[]			= new JLabel[tentative]; 
	private JLabel						propositionIcon[]			= new JLabel[cases]; // Cadre qui affiche les entrées clavier.
	private JLabel						propositionIconOrdi[]		= new JLabel[cases];
	private JLabel						indice						= new JLabel();
	private JButton						bouton[];
	private JButton						boutonOrdi[];
	private ImageIcon					vide						= new ImageIcon("images/couleur/vide.png");
	private JPanel						votreProp					= new JPanel();
	private JPanel						ordiProp					= new JPanel();
	private JPanel						clavierBouton				= new JPanel();
	JLabel entrerProp = new JLabel();
	JLabel vosTentative = new JLabel();
	JLabel entrerIndi = new JLabel();
	private boolean						codeSecretOk				= false;
	private boolean						indiceOk					= true;
	private JScrollPane					scrollPane 					=
			new JScrollPane(cadreTentative,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private int							placer,changer,nombreClick,placerClick,changerClick,changerOrdi,placerOrdi								
	= 0;

	public MastermindDuel() {
		this.setSize(800,580);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setTitle(" Duel ");
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

		initRegle();
		initBlocJoueur();
		initBlocOrdi();
		contentPane.add(clavierBouton);
		initBlocIndices();
	}

	private void initBlocOrdi() {
		JScrollPane	scrollPaneOrdi=
				new JScrollPane(cadreTentativeOrdi,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JLabel ordi = new JLabel();
		ordi.setText(" Ordinateur ");
		ordi.setBackground(Color.DARK_GRAY);
		ordi.setForeground(Color.white);
		ordi.setOpaque(true);
		JLabel	textProp = new JLabel();
		modelInterface.blocJeuSize(cadreTentativeOrdi,contentPane);
		scrollPaneOrdi.setPreferredSize(new Dimension(350,205));
		scrollPaneOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreTentativeOrdi.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.95f));
		cadreTentativeOrdi.setPreferredSize(new Dimension(375,305));
		for(int i=0;i<tentative;i++) {
			blocTentativeOrdi[i]= modelInterface.createJPanel();// on crée les JLabel et on met dans tab
			blocTentativeOrdi[i].setBackground(Color.DARK_GRAY);
			blocTentativeOrdi[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			JLabel tenta = new JLabel();
			tenta.setText(" Tentative de l'IA : "+(i+1));
			tenta.setForeground(Color.WHITE);
			tenta.setFont(arial);
			blocTentativeOrdi[i].add(tenta);
			cadreTentativeOrdi.add(blocTentativeOrdi[i]);
			blocIndicesOrdi[i]= modelInterface.createJLabel();// on crée les JLabel et on met dans tab
			blocIndicesOrdi[i].setText(" ♦ : ?    ♢ : ? ");
			blocIndicesOrdi[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			blocIndicesOrdi[i].setOpaque(true);
			blocIndicesOrdi[i].setPreferredSize(new Dimension(118,25));
			blocIndicesOrdi[i].setHorizontalAlignment(JLabel.CENTER);
			cadreTentativeOrdi.add(blocIndicesOrdi[i]);
		}
		ordiProp.add(textProp);
		textProp.setText(" Votre code secret : ");
		ordiProp.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		ordiProp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		for(int i=0;i<cases;i++) {
			propositionIconOrdi[i]= modelInterface.createJLabel();// on crée les JLabel et on met dans tab
			propositionIconOrdi[i].setIcon(vide);
			ordiProp.add(propositionIconOrdi[i]);
		}
		blocOrdi.add(ordi);
		blocOrdi.add(scrollPaneOrdi);
		blocOrdi.add(ordiProp);
		blocOrdi.setPreferredSize(new Dimension(375,280));
		blocOrdi.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocOrdi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(blocOrdi);
	}

	private void initBlocJoueur() {
		JLabel joueur = new JLabel();
		joueur.setText(" Joueur ");
		joueur.setBackground(Color.DARK_GRAY);
		joueur.setForeground(Color.white);
		joueur.setOpaque(true);
		JLabel	textProp = new JLabel();
		modelInterface.blocJeuSize(cadreTentative,contentPane);
		scrollPane.setPreferredSize(new Dimension(350,205));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreTentative.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.95f));
		blocJoueur.setPreferredSize(new Dimension(375,280));
		blocJoueur.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		blocJoueur.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
		for(int i=0;i<tentative;i++) {
			blocTentative[i]= modelInterface.createJPanel();// on crée les JLabel et on met dans tab
			blocTentative[i].setBackground(Color.DARK_GRAY);
			blocTentative[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			JLabel tenta = new JLabel();
			tenta.setText(" Votre tentative : "+(i+1));
			tenta.setForeground(Color.WHITE);
			tenta.setFont(arial);
			blocTentative[i].add(tenta);
			cadreTentative.add(blocTentative[i]);
			blocIndices[i]= modelInterface.createJLabel();// on crée les JLabel et on met dans tab
			blocIndices[i].setText(" ♦ : ?    ♢ : ? ");
			blocIndices[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
			blocIndices[i].setOpaque(true);
			blocIndices[i].setPreferredSize(new Dimension(118,25));
			blocIndices[i].setHorizontalAlignment(JLabel.CENTER);
			cadreTentative.add(blocIndices[i]);
		}
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
					if(nombreClick<cases & codeSecretOk==true) {
						propositionIcon[nombreClick].setIcon(((JButton)event.getSource()).getIcon());
						propositionIcon[nombreClick].setText("");
						++nombreClick;
						if(nombreClick==cases) {
							ok.setEnabled(true);
						}
					}
					else if(nombreClick<cases & !codeSecretOk) {
						propositionIconOrdi[nombreClick].setIcon(((JButton)event.getSource()).getIcon());
						propositionIconOrdi[nombreClick].setText("");
						++nombreClick;
						if(nombreClick==cases) {
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
		JPanel boutonCadre = new JPanel();
		boutonCadre.setPreferredSize(new Dimension(60,60));
		boutonCadre.setBackground(Color.DARK_GRAY);
		boutonCadre.add(supprimer);
		boutonCadre.add(ok);
		blocJoueur.setBackground(Color.getHSBColor(0.534f, 0.15f, 0.84f));
		ok.setBackground(Color.getHSBColor(0.345f, 0.48f, 0.78f));
		ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		ok.addMouseListener(new SourisListener());
		ok.setEnabled(false);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
					if(codeSecretOk==true) {
						model.status(entrerProp,vosTentative,entrerIndi,codeSecretOk);
						model.okClick(blocTentative,propositionIcon,blocIndices,changer,placer,contentPane);
						model.finClick(propositionIconOrdi, blocTentativeOrdi, blocIndicesOrdi, changerClick, placerClick, boutonCadre, textProp,indiceOk);
						fin.setEnabled(true);
						
					}
					if(!codeSecretOk) {
						codeSecretOk=true;
						model.status(entrerProp ,vosTentative,entrerIndi,codeSecretOk);
					}
					ok.setEnabled(false);
					nombreClick=0;
					for(int i =0;i<cases;i++) {
						propositionIcon[i].setIcon(vide);
					}
			}
		});
		votreProp.add(textProp);
		textProp.setText(" Votre proposition : ");
		votreProp.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		votreProp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		for(int i=0;i<cases;i++) {
			propositionIcon[i]= modelInterface.createJLabel();// on crée les JLabel et on met dans tab
			propositionIcon[i].setIcon(vide);
			votreProp.add(propositionIcon[i]);
		}
		clavierBouton.add(clavier);
		clavierBouton.add(boutonCadre);
		clavierBouton.setBackground(Color.LIGHT_GRAY);
		clavierBouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		blocJoueur.add(joueur);
		blocJoueur.add(scrollPane);
		blocJoueur.add(votreProp);
		contentPane.add(blocJoueur);

	}

	private void initRegle() {

		JPanel espaceRetour = new JPanel ();
		espaceRetour.setPreferredSize(new Dimension(720,20));
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
		blocRegle.setPreferredSize(new Dimension(800,50));
		JLabel regle = new JLabel();
		regle.setText("Vous jouez contre l'ordinateur, le plus rapide à trouver le code secret à "+couleur+" couleurs gagne la partie.");
		regle.setFont(impact);
		JLabel infos = new JLabel();
		infos.setText("♦ : signifie le nombre de couleur bien placé.    ♢ : signifie le nombre de couleur qui si trouve à un emplacement différent.");
		Font comic = new Font("New York",11,11);
		infos.setFont(comic);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		blocRegle.add(infos);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(720,25));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));	
		vosTentative.setText("  Entrez votre code secret  ");
		vosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.65f, 0.64f)));
		vosTentative.setOpaque(true);
		vosTentative.setBackground(Color.getHSBColor(0.134f, 0.25f, 0.95f));
		vosTentative.setForeground(Color.BLACK);
		vosTentative.setFont(arial2);
		vosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		espace.add(vosTentative);
		entrerProp.setText("  Entrez votre proposition  ");
		entrerProp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.65f, 0.64f)));
		entrerProp.setOpaque(true);
		entrerProp.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.45f));
		entrerProp.setForeground(Color.BLACK);
		entrerProp.setFont(arial2);
		entrerProp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		espace.add(entrerProp);
		entrerIndi.setText("  Entrez les indices  ");
		entrerIndi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.65f, 0.64f)));
		entrerIndi.setOpaque(true);
		entrerIndi.setBackground(Color.getHSBColor(0.134f, 0.05f, 0.45f));
		entrerIndi.setForeground(Color.BLACK);
		entrerIndi.setFont(arial2);
		entrerIndi.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		espace.add(entrerIndi);
		contentPane.add(espace);
	}
	private void initBlocIndices() {

		JPanel	blocIndice	= new JPanel();
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
		fin.setEnabled(false);
		fin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				model.checkIndice(placerClick,changerClick,blocIndicesOrdi,fin);
				model.status(entrerProp,vosTentative,entrerIndi,codeSecretOk);
			}
		});
		blocIndice.add(refresh);
		refresh.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		refresh.setBackground(Color.getHSBColor(0.534f, 0.55f, 0.74f));
		refresh.addActionListener(IndicesListener);
		blocIndice.setPreferredSize(new Dimension(160,70));
		blocIndice.setBackground(Color.LIGHT_GRAY);
		blocIndice.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.24f)));
		JLabel nombreTourAffichage = new JLabel();
		nombreTourAffichage.setOpaque(true);
		nombreTourAffichage.setText("  Nombre de tour : 0  ");
		nombreTourAffichage.setFont(impact);
		contentPane.add(nombreTourAffichage);
		contentPane.add(blocIndice);

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