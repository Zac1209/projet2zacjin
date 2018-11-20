package application;

import java.util.Date;

public abstract class Document {
	
	String titre;
	String auteur;
	Boolean dispo;
	Date datePub;
	
	public Document(String titre, String auteur, Date dp){
		this.titre = titre;
		this.auteur = auteur;
		datePub = dp;
	}

}
