package application;

public class listeDocument {
	private static listeDocument instance;
	
	private listeDocument() {
		  //Le constructeur to be done
	}
	
	public static listeDocument getInstance() {
		  if (instance == null) 
			  instance = new listeDocument();
		  return instance; 
	}
}
