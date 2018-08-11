package fr.yoannroche.projet3.plusmoins.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AideDuelPlusMoins extends JFrame{
	
	private JPanel contentPane = new JPanel();
	private JButton suivant = new JButton(" Suivant ");
	private JButton retourner = new JButton(" Retourner au jeu ");
	private JLabel tentative = new JLabel();
	private Font impact = new Font ("impact", 17,17);
	private JLabel infosTentative = new JLabel();
	private JPanel cadreJ = new JPanel();
	private JLabel codeSecret = new JLabel();
	private Font arial = new Font ("arial", 12,12);
	private JTextArea aideText1 = new JTextArea();
	private JPanel espace = new JPanel();
	private int nombreClick = 0;
	private JLabel tentativeIA =new JLabel();
	private JTextArea dialog = new JTextArea();
	private String [] indice = {"+","-","="};
	private JButton indi[];
	private JButton fin = new JButton(" Ok ");
	private JButton refresh = new JButton("⟲");
	private JPanel cadreOrdi = new JPanel();
	private JPanel tentativePanel = new JPanel();
	public AideDuelPlusMoins() {
		
		this.setTitle("Duel");
		this.setSize(460,280);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
		this.setResizable(false);
		contentPane.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		
		initAide1();
		
	}


	private void initAide1() {
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
		
		espace.setPreferredSize(new Dimension(165,30));
		espace.setBackground(Color.DARK_GRAY);
		espace.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
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
		cadreJ.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		contentPane.add(cadreJ);
		aideText1.setEditable(false);
		aideText1.setFont(arial);
		aideText1.setPreferredSize(new Dimension(240,150));
		aideText1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
	    aideText1.setText("\n Au début de la partie \n"
	    		+ " il vous sera demandés \n"
	    		+ " de donner un code secret. \n"
	    		+ " Votre code secret s'affichera dans \n"
	    		+ " le cadre entouré en jaune. \n");
		contentPane.add(aideText1);
		suivant.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		suivant.setBackground(Color.getHSBColor(0.534f, 0.36f, 0.76f));
		suivant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(nombreClick==0) {
					aide2();
				}
				if(nombreClick==1) {
					aide3();
				}
				if(nombreClick==2) {
					aide4();
				}
				++nombreClick;
			}
		});
		contentPane.add(suivant);
		
	}

	public void aide2() {
		espace.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		cadreJ.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
		aideText1.setText("\n Vous allez devoir entrer votre \n"
				+ " proposition. \n"
				+ " Celle-ci s'affichera dans le haut du \n"
				+ " cadre entouré en jaune. \n"
				+ " La partie en-dessous de celle-ci \n"
				+ " affichera les indices donnés par l'ordinateur \n"
				+ " pour vous aidez à trouver le code. \n");
	}
	public void aide3() {
		cadreJ.setVisible(false);
		aideText1.setText("\n Une fois que vous avez donnés votre \n"
				+ " proposition \n"
				+ " L'ordinateur donnera la sienne \n"
				+ " Celle-ci s'affichera dans le cadre entouré \n"
				+ " en jaune. \n");
		
		JLabel ordi = new JLabel();
		ordi.setText(" Ordinateur ");
		ordi.setOpaque(true);
		ordi.setFont(arial);
		ordi.setBorder(BorderFactory.createLineBorder(Color.black));
		dialog.setBackground(Color.getHSBColor(0.534f, 0.05f, 0.94f));
		dialog.setPreferredSize(new Dimension(150,80));
		dialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
		dialog.setEditable(false);
		dialog.setText(" \n Je suis prêt \n c'est quand vous voulez ! ");
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
		cadreOrdi.add(dialog);
		cadreOrdi.add(espace2);
		tentativePanel.add(tentativeIA);
		refresh.setForeground((Color.getHSBColor(0.141f, 0.84f, 0.97f)));
		refresh.setBorder(BorderFactory.createLineBorder(Color.black));
		refresh.setBackground(Color.DARK_GRAY);
		refresh.setPreferredSize(new Dimension(20,20));
		fin.setPreferredSize(new Dimension(25,14));
		fin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
		fin.setBackground(Color.getHSBColor(0.154f, 0.45f, 0.44f));
		fin.setEnabled(false);
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
			tentativePanel.add(indi[i]).setEnabled(true);
			i++;
		}
		fin.setBackground(Color.getHSBColor(0.534f, 0.35f, 0.34f));
		tentativePanel.add(fin);
		tentativePanel.add(refresh);
		contentPane.add(aideText1);
		contentPane.add(suivant);
	}
	public void aide4() {
		aideText1.setText("\n Vous allez devoir à votre tour \n"
				+ " donner des incides à l'ordinateur \n"
				+ " dans le cadre entouré en jaune. \n"
				+ " Une fois les bons indices indiqués \n"
				+ " quand vous cliquerez sur <ok> \n"
				+ " Le tour sera finit, il vous faudra donc \n"
				+ " entrer votre proposition ainsi de suite... \n");
		dialog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		tentativePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.154f, 0.85f, 0.94f)));
		suivant.setVisible(false);
		contentPane.add(retourner);
		retourner.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,Color.getHSBColor(0.534f, 0.45f, 0.44f)));
		retourner.setBackground(Color.getHSBColor(0.534f, 0.36f, 0.76f));
		retourner.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				DuelPlusMoins duel = new DuelPlusMoins();
				duel.setVisible(true);
			}
		});
	}
	
}
