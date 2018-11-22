package application;

public class DVD extends Document {
	private String strNumeroDVD;
	private int intNbDVD;

	public DVD(String numeroDvd, String titre, String datePublication, int intNbDVD, String auteur) {
		super(titre, auteur, datePublication);
		this.strNumeroDVD = numeroDvd;
		this.intNbDVD = intNbDVD;
	}

	public String getStrNumeroDVD() {
		return strNumeroDVD;
	}

	public int getIntNbDVD() {
		return intNbDVD;
	}

}
