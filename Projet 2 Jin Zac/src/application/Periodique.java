package application;

import java.util.Date;

public class Periodique extends Document {
	private String strNumeroPeriodique;
	private String strNumeroVolume;

	public Periodique(String strNumero, String titre, String datePublication, String auteur,
			String strNumeroVolume, String strNumeroPeriodique) {
		super(strNumero, titre, auteur, datePublication);
		this.strNumeroPeriodique = strNumeroPeriodique;
		this.strNumeroVolume = strNumeroVolume;
	}

	public String getStrNumeroPeriodique() {
		return strNumeroPeriodique;
	}

	public String getStrNumeroVolume() {
		return strNumeroVolume;
	}
	
	public String Emprunt(String nom, String prenom, int numTel ) {
		
		if(dateEmprunt != null)
		{
			int dejaEmprunter = 0;
			ListeDocument listeComplete = ListeDocument.getInstance();
			
			for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
				if (listeComplete.arListeDoc.get(i) instanceof Periodique)
					
			}
		}
		return "Deja emprunte"; // erase this shit when done
			
	}


}
