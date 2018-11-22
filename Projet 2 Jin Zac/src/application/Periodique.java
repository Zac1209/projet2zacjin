package application;

public class Periodique extends Document {
	private String strNumeroPeriodique;
	private int intNumeroVolume;
	private int intNumeroPeriodique;

	public Periodique(String strNumeroPeriodique, String titre, String datePublication, String auteur,
			int intNumeroVolume, int intNumeroPeriodique) {
		super(titre, auteur, datePublication);
		this.strNumeroPeriodique = strNumeroPeriodique;
		this.intNumeroPeriodique = intNumeroPeriodique;
		this.intNumeroVolume = intNumeroVolume;
	}

	public String getStrNumeroPeriodique() {
		return strNumeroPeriodique;
	}

	public int getIntNumeroVolume() {
		return intNumeroVolume;
	}

	public int getIntNumeroPeriodique() {
		return intNumeroPeriodique;
	}

}
