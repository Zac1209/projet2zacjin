package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Document {
	
	private String strNumero;
	private String strTitre;
	private String strAuteur;
	private Boolean booDispo;
	private String strDatePublication;
	
	private Date dateEmprunt;
	private String strNomEmprunteur;
	private String strPrenomEmprunteur;
	private String strNumeroTelephone;
	
	public Document(String num ,String titre, String auteur, String datePublication){
		strNumero = num;
		strTitre = titre;
		strAuteur = auteur;
		strDatePublication = datePublication;
	}

	public String Emprunt(String nom, String prenom, int numTel ) {
		
		if(dateEmprunt != null)
		{
			//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			dateEmprunt = date; //2016/11/16 12:08:43
		}
		return null; // erase this shit when done
			
	}

	public String retour(){

		dateEmprunt = null;
		strNomEmprunteur = null;
		strPrenomEmprunteur = null;
		strNumeroTelephone = null;
		
		return "Retour completer";
	}
	@Override
	public String toString() {
		return "Document [titre=" + strTitre + ", auteur=" + strAuteur + "]";
	}
	
	public String getStrNumero() {
		return strNumero;
	}

	public void setStrNumero(String strNumero) {
		this.strNumero = strNumero;
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
		return strNomEmprunteur;
	}

	public void setNomEmp(String nomEmp) {
		this.strNomEmprunteur = nomEmp;
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
