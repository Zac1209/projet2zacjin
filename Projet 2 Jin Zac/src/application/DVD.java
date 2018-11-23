package application;

import java.io.IOException;

public class DVD extends Document {
	private int intNbDVD;

	public DVD(String numeroDvd, String titre, String datePublication, int intNbDVD, String auteur) {
		super( numeroDvd, titre , auteur, datePublication );
		this.intNbDVD = intNbDVD;
	}


	public int getIntNbDVD() {
		return intNbDVD;
	}

	public String Emprunt(String nom, String prenom, String numTel ) {
		
		if(dateEmprunt != null)
		{
			int dejaEmprunter = 0;
			ListeDocument listeComplete;
			try {
				listeComplete = ListeDocument.getInstance();
			
			
			for (int i = 0; i < listeComplete.arListeDoc.size() && dejaEmprunter < 2; i++) {
				if (listeComplete.arListeDoc.get(i) instanceof Periodique)
					dejaEmprunter++;
					
			}
			
			if(dejaEmprunter == 2)
			{
				return "Vous avez deja emprunter deux dvds";
			}
			else
			{
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

}
