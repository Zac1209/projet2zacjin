package application;

import java.io.IOException;
import java.util.Date;

public class Periodique extends Document {
	private String strNumeroPeriodique;
	private String strNumeroVolume;

	public Periodique(String strNumero, String titre, Date datePublication, String auteur, String strNumeroVolume,
			String strNumeroPeriodique) {
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

	public String Emprunt(String nom, String prenom, String numTel) {

		if (dateEmprunt != null) {
			int dejaEmprunter = 0;
			ListeDocument listeComplete;
			try {
				listeComplete = ListeDocument.getInstance();

				for (int i = 0; i < listeComplete.arListeDoc.size() && dejaEmprunter < 1; i++) {
					if (listeComplete.arListeDoc.get(i) instanceof Periodique)
						dejaEmprunter++;

				}

				if (dejaEmprunter == 1) {
					return "Vous avez deja emprunter un periodique";
				} else {
					strNomEmprunteur = nom;
					strPrenomEmprunteur = prenom;
					strNumeroTelephone = numTel;

					return "Emprunt fait";
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "erreur";
			}
		}
		return "Deja emprunte"; // erase this shit when done

	}

	public String toString() {
		return "Numéro de livre: " + getStrNumero() + ", Titre: " + getTitre() + ", Date de publication: "
				+ getDatePublication() + ", Auteur: " + getAuteur() + ",\n Numéro de volume: " + strNumeroVolume
				+ ", Numéro de périodique: " + strNumeroPeriodique + "\n";
	}

}
