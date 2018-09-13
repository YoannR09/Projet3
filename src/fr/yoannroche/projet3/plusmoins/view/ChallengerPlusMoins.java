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
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.yoannroche.projet3.BeanReglage;
import fr.yoannroche.projet3.Generateur;
import fr.yoannroche.projet3.plusmoins.model.ChallengerPlusMoinsModel;


/**
 * Class qui gère l'affichage du mode Challenger du Plus ou Moins.
 * @author yoann
 *
 */
public class ChallengerPlusMoins extends JFrame{

	private BeanReglage					bean				;
	private static final Logger			logger				= LogManager.getLogger();
	private int							nombreClick			= 0;
	private JPanel						contentPane			= new JPanel();
	private JTextField					proposition			= new JTextField();
	private JPanel						blocProposition		= new JPanel();
	private JPanel						blocTest			= new JPanel();
	private JLabel						infosTentative		= new JLabel(); // La ou sera écrit + - = 
	private JLabel						tentative			= new JLabel(); // Le texte généré de votre proposition quand vous cliquez sur Ok .
	private JButton						ok					= new JButton(" OK ");
	private JButton						supprimer			= new JButton(" Suppr ");
	private JButton						retour				= new JButton(" Retour ");
	private Font						impact				= new Font ("impact", 17,17);
	private Font						arial				= new Font ("arial", 12,12);
	private Font						arial2				= new Font ("arial", 10,10);
	private int[]						clavier				= {0,1,2,3,4,5,6,7,8,9};
	private int							nbreTentative		= 0;
	private JLabel						nbreTen				= new JLabel();
	private JPanel						espace2				= new JPanel ();
	private JButton						bouton[];

	public ChallengerPlusMoins(BeanReglage bean) {

		this.setTitle("Challenger");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.bean = bean;
		
		Generateur			gen					= new Generateur(bean);
		int 				nombreString		= gen.getNombre();
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));

		ChallengerPlusMoinsModel chal = new ChallengerPlusMoinsModel(bean,nombreString);
		initRegle();
		initTentative();
		initInfos();
		initBlocProposition();
		initBlocTest(chal);
		initCadreDev(chal);
	}

	/**
	 * Méthode qui affiche l'aide du développeur si le mdoe développeur est actif.
	 * @param chal 
	 */
	private void initCadreDev(ChallengerPlusMoinsModel chal) {

	
		if(bean.getDev()==1) {
			JLabel codeSecret = new JLabel();
			codeSecret.setFont(arial2);
			codeSecret.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
			codeSecret.setText("");
			JPanel cadreDev = new JPanel();
			JLabel text = new JLabel();
			text.setText("Code secret : ");
			text.setFont(arial2);
			text.setForeground(Color.WHITE);
			cadreDev.add(codeSecret);
			cadreDev.setPreferredSize(new Dimension(50,15));
			cadreDev.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
			espace2.add(cadreDev);
			espace2.add(text);
			espace2.add(codeSecret);
			chal.dev(codeSecret);
		}

	}

	/**
	 * Méthode qui gère l'affichage des indices après avori donné une proposition.
	 */
	private void initInfos() {
		JPanel infosCadre = new JPanel();
		infosCadre.setPreferredSize(new Dimension(150,50));
		infosCadre.setBackground(Color.getHSBColor(0.134f, 0.15f, 0.94f));
		infosTentative.setOpaque(true);
		infosTentative.setBackground(Color.WHITE);
		infosTentative.setBorder(BorderFactory.createLineBorder(Color.black));
		infosTentative.setPreferredSize(new Dimension(120,40));
		infosTentative.setHorizontalAlignment(JLabel.CENTER);
		infosTentative.setFont(impact);
		infosTentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		infosTentative.setText(" Indices + - ou = ");
		infosCadre.add(infosTentative);
		contentPane.add(infosCadre);

		espace2.setPreferredSize(new Dimension(320,25));
		espace2.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		espace2.add(nbreTen);
		nbreTen.setFont(arial);
		nbreTen.setForeground(Color.white);
		contentPane.add(espace2);

	}
	/**
	 * Méthode qui affiche votre proposition.
	 */
	private void initTentative() {

		JPanel tentativePanel = new JPanel();
		tentativePanel.setPreferredSize(new Dimension(150,50));
		tentativePanel.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		tentative.setOpaque(true);
		tentative.setBackground(Color.white);
		tentative.setPreferredSize(new Dimension(120,40));
		tentative.setBorder(BorderFactory.createLineBorder(Color.black));
		tentative.setHorizontalAlignment(JLabel.CENTER);
		tentative.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentative.setFont(impact);
		tentative.setText(" Votre tentative ");
		tentativePanel.add(tentative);
		contentPane.add(tentativePanel);

		nbreTen.setText("Nombre de tentatives : "+nbreTentative);
		JPanel espace3 = new JPanel ();
		espace3.setPreferredSize(new Dimension(40,15));
		espace3.setBackground(Color.getHSBColor(0.534f, 0.45f, 0.74f));
		espace3.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(espace3);
	}

	/**
	 * Méthode qui gère l'affichage des règles dans la barre du haut.
	 */
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
				FenetreMenuPlusMoins menu = new FenetreMenuPlusMoins(bean);	
				menu.setVisible(true);
			}
		});
		JPanel blocRegle = new JPanel();
		blocRegle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		blocRegle.setPreferredSize(new Dimension(500,40));
		JLabel regle = new JLabel();
		regle.setText("Vous avez "+ bean.getTentatives() +" tentative pour trouver le code secret à "+ bean.getCases() +" chiffres.");
		regle.setFont(arial);
		contentPane.add(retour);
		contentPane.add(espaceRetour);
		blocRegle.add(regle);
		contentPane.add(blocRegle);
		JPanel espace = new JPanel ();
		espace.setPreferredSize(new Dimension(320,15));
		espace.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		contentPane.add(espace);

	}

	/**
	 * Méthode qui gère les boutons ok et supprimer et affiche la proposition que vous êtes en train de faire.
	 * @param chal 
	 */
	private void initBlocTest(ChallengerPlusMoinsModel chal) {

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

				chal.okClick(proposition, tentative, infosTentative,contentPane,blocProposition,nbreTentative);
				logger.info("Récuperation de la proposition :"+proposition.getText());
				++nbreTentative;
				if(tentative.getText().length()!=bean.getCases()) {

					nbreTentative=0;
				}
				nbreTen.setText("Nombre de tentatives : "+nbreTentative);
				chal.chechTentative(nbreTentative,contentPane);
				nombreClick = 0;


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

	/**
	 * Méthode qui gère les boutons des chiffres.
	 */
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
		
		ChallengerPlusMoinsModel chal = new ChallengerPlusMoinsModel(bean,0);

		public void mouseClicked(MouseEvent arg0) {
			char entrer = ((JButton)arg0.getSource()).getText().charAt(0);
			proposition.setText(proposition.getText()+entrer);
			nombreClick++;
			chal.RangeWord(blocProposition,nombreClick);
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
