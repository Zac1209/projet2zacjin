package application;

import java.util.Date;

public class Livre extends Document{
	private String strPage;
	
	public Livre(String NumLivre, String titre, String dp ,String auteur,String page){	
		super(NumLivre ,titre, auteur, dp);
		strPage = page;
	}

	public String getStrNumeroLivre() {
		return strPage;
	}

}
