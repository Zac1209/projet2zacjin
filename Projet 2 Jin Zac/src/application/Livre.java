package application;

public class Livre extends Document{
	
	int pages; //testing data not part of actual structure
	
	public Livre(String titre, String auteur, int p){
		
		super(titre, auteur);
		pages = p;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
