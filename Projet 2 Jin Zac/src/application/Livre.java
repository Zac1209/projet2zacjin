package application;

import java.util.Date;

public class Livre extends Document{
	private String strNumeroLivre;
	
	public Livre(String NumLivre, String titre, String dp ,String auteur){	
		super(titre, auteur, dp);
		this.strNumeroLivre = NumLivre;
	}

	public String getStrNumeroLivre() {
		return strNumeroLivre;
	}

}
