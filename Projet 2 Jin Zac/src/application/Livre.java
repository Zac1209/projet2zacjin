package application;

import java.util.Date;

public class Livre extends Document{
	
	int pages; //testing data not part of actual structure
	
	public Livre(String titre, String auteur, Date dp , int p){
		
		super(titre, auteur, dp);
		pages = p;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
