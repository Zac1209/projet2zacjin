package application;

import java.util.Date;

public class Document {
	
	private String strTitre;
	private String strAuteur;
	private Boolean booDispo;
	private String strDatePublication;
	
	private Date dateEmprunt;
	private String srtNomEmprunteur;
	private String strPrenomEmprunteur;
	private String strNumeroTelephone;
	
	public Document(String titre, String auteur, String datePublication){
		this.strTitre = titre;
		this.strAuteur = auteur;
		datePublication = datePublication;
	}

	public String Emprunt(Date emprunt, String nom, int numTel ) {
		
		//if(dateEmprunt != null) rendu la
		return null; // erase this shit when done
			
	}

	@Override
	public String toString() {
		return "Document [titre=" + strTitre + ", auteur=" + strAuteur + "]";
	}

	public String getTitre() {
		return strTitre;
	}

	public void setTitre(String titre) {
		this.strTitre = titre;
	}

	public String getAuteur() {
		return strAuteur;
	}

	public void setAuteur(String auteur) {
		this.strAuteur = auteur;
	}

	public Boolean getDispo() {
		return booDispo;
	}

	public void setDispo(Boolean dispo) {
		this.booDispo = dispo;
	}

	public String getDatePublication() {
		return strDatePublication;
	}

	public void setDatePub(String datePub) {
		this.strDatePublication = datePub;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public String getNomEmp() {
		return srtNomEmprunteur;
	}

	public void setNomEmp(String nomEmp) {
		this.srtNomEmprunteur = nomEmp;
	}

	public String getPrenomEmp() {
		return strPrenomEmprunteur;
	}

	public void setPrenomEmp(String prenomEmp) {
		this.strPrenomEmprunteur = prenomEmp;
	}

	public String getNumTel() {
		return strNumeroTelephone;
	}

	public void setNumTel(String numTel) {
		this.strNumeroTelephone = numTel;
	}
	
	
}
