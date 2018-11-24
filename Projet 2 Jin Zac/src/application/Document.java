package application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7664109942692871359L;
	private String strNumero;
	private String strTitre;
	private String strAuteur;
	private String strDispo;
	private Date dateDatePublication;

	protected Date dateEmprunt;
	protected String strNomEmprunteur;
	protected String strPrenomEmprunteur;
	protected String strNumeroTelephone;

	public Document(String num, String titre, String auteur, Date datePublication) {
		strNumero = num;
		strTitre = titre;
		strAuteur = auteur;
		dateDatePublication = datePublication;

		strDispo = "oui";
	}

	public String Emprunt(String nom, String prenom, String numTel) {

		if (dateEmprunt != null) {
			// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			dateEmprunt = date; // 2016/11/16 12:08:43
		}
		return null; // erase this shit when done

	}

	public String retour() {

		dateEmprunt = null;
		strNomEmprunteur = null;
		strPrenomEmprunteur = null;
		strNumeroTelephone = null;

		return "Retour completer";
	}

	@Override
	public String toString() {
		return "Document [Identification=" + strNumero + "titre=" + strTitre + ", auteur=" + strAuteur + "]";
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

	public String getDispo() {
		return strDispo;
	}

	public void setDispo(String dispo) {
		this.strDispo = dispo;
	}

	public Date getDatePublication() {
		return dateDatePublication;
	}

	public void setDatePub(Date datePub) {
		this.dateDatePublication = datePub;
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
