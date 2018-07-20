package fr.yoannroche.projet3.mastermind.model;

import java.util.ArrayList;

public class CodeCacher {

	private int nbre ;
	private int tentative = 0;
	private int nombreCellules = 4;
	private ArrayList<Integer> tabInt = new ArrayList<Integer>();
	private ArrayList<String> tabString = new ArrayList<String>();
	
	public CodeCacher() {
		initCodeCacher();
	}

	private void initCodeCacher() {
		
		for(int c = 0 ; c<nombreCellules;c++) {
			int i = (int)(Math.random()*(9));
			tabInt.add(i);
		}
		
		
	}
	public void setTableauChiffre(ArrayList<Integer> tabInt) {
		this.tabInt = tabInt;
	}
	public ArrayList<Integer> getTableauChiffre() {
		return this.tabInt;
	}
}
