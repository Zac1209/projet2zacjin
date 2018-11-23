package application;

import java.io.Serializable;

public class Membre implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6813029036270644603L;
	private String strNom;
	private String strPrenom;
	private String strNumTel = null;//seulement pour les adhérants
	private String strIdPrep = null;//seulement pour les préposés
	private String strMotDePasse = null;//seulement pour les préposés
	private boolean booEstPrepose;
	private int intDette;

	public Membre(String nom, String prenom, String numTel, String idPrep, boolean estPrepose, String mdp) {
		this.strNom = nom;
		this.strPrenom = prenom;
		if(estPrepose == true) {
			this.strIdPrep = idPrep;
			this.strMotDePasse = mdp;
		}else {
			this.strNumTel = numTel;
		}
		this.booEstPrepose = estPrepose;
	}
	
	

	@Override
	public String toString() {
		return "Membre " + strNom + ", " + strPrenom + ", " + strNumTel;
	}



	public String getStrNom() {
		return strNom;
	}

	public String getStrPrenom() {
		return strPrenom;
	}

	public String getStrNumTel() {
		return strNumTel;
	}

	public String getStrIdPrep() {
		return strIdPrep;
	}

	public boolean isBooEstPrepose() {
		return booEstPrepose;
	}



	public String getStrMotDePasse() {
		return strMotDePasse;
	}
}
