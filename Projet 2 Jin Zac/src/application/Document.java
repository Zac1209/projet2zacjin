package application;

import java.util.Date;

public abstract class Document {
	
	String titre;
	String auteur;
	Boolean dispo;
	Date datePub;
	
	Date dateEmprunt;
	String nomEmp;
	String prenomEmp;
	String numTel;
	
	public Document(String titre, String auteur, Date dp){
		this.titre = titre;
		this.auteur = auteur;
		datePub = dp;
	}

	public String Emprunt(Date emprunt, String nom, int numTel ) {
		
		//if(dateEmprunt != null) rendu la
		return null; // erase this shit when done
			
	}
}
