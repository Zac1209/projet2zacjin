package application;

public class DVD extends Document {
	private int intNbDVD;

	public DVD(String numeroDvd, String titre, String datePublication, int intNbDVD, String auteur) {
		super( numeroDvd, titre , auteur, datePublication );
		this.intNbDVD = intNbDVD;
	}


	public int getIntNbDVD() {
		return intNbDVD;
	}

}
