package application;

import java.io.IOException;
import java.util.Date;

public class Livre extends Document{

	
	public Livre(String NumLivre, String titre, String dp ,String auteur){	
		super(NumLivre ,titre, auteur, dp);
	}

	public String Emprunt(String nom, String prenom, String numTel ) {
		
		if(dateEmprunt != null)
		{
			int dejaEmprunter = 0;
			ListeDocument listeComplete;
			try {
				listeComplete = ListeDocument.getInstance();
			
			
			for (int i = 0; i < listeComplete.arListeDoc.size() && dejaEmprunter < 3; i++) {
				if (listeComplete.arListeDoc.get(i) instanceof Livre)
					dejaEmprunter++;
					
			}
			
			if(dejaEmprunter == 3)
			{
				return "Vous avez deja emprunter trois livre";
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
		return "Document deja emprunte"; // erase this shit when done
			
	}

}
