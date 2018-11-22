package application;

public class Membre {
	
	private String strNom;
	private String strPrenom;
	private String strNumTel;//seulement pour les adhérants
	private String strIdPrep;//seulement pour les préposés
	private boolean booEstPrepose;

	public Membre(String nom, String prenom, String numTel, String idPrep, boolean estPrepose) {
		this.strNom = nom;
		this.strPrenom = prenom;
		if(estPrepose == true) {
			this.strIdPrep = idPrep;
		}else {
			this.strNumTel = numTel;
		}
		this.booEstPrepose = estPrepose;
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
}
