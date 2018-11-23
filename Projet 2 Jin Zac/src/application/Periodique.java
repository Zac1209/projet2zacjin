package application;

public class Periodique extends Document {
	private String strNumeroPeriodique;
	private String strNumeroVolume;

	public Periodique(String strNumero, String titre, String datePublication, String auteur,
			String strNumeroVolume, String strNumeroPeriodique) {
		super(strNumero, titre, auteur, datePublication);
		this.strNumeroPeriodique = strNumeroPeriodique;
		this.strNumeroVolume = strNumeroVolume;
	}

	public String getStrNumeroPeriodique() {
		return strNumeroPeriodique;
	}

	public String getStrNumeroVolume() {
		return strNumeroVolume;
	}


}
