package application;

public class Membre {
	
	private String strNom;
	private String strPrenom;
	private String strNumTel;//seulement pour les adh�rants
	private String strIdPrep;//seulement pour les pr�pos�s
	private boolean booEstPrepose;
	private int intDette;

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
}
