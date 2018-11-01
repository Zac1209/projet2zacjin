package application;

public abstract class Document {
	
	String titre;
	String auteur;
	
	public Document(String titre, String auteur){
		this.titre = titre;
		this.auteur = auteur;
	}

}
