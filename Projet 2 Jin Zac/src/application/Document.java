package application;

import java.util.Date;

public class Document {
	
	private String titre;
	private String auteur;
	private Boolean dispo;
	private String datePub;
	
	private Date dateEmprunt;
	private String nomEmp;
	private String prenomEmp;
	private String numTel;
	
	public Document(String titre, String auteur, String dp){
		this.titre = titre;
		this.auteur = auteur;
		datePub = dp;
	}

	public String Emprunt(Date emprunt, String nom, int numTel ) {
		
		//if(dateEmprunt != null) rendu la
		return null; // erase this shit when done
			
	}

	@Override
	public String toString() {
		return "Document [titre=" + titre + ", auteur=" + auteur + "]";
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Boolean getDispo() {
		return dispo;
	}

	public void setDispo(Boolean dispo) {
		this.dispo = dispo;
	}

	public String getDatePub() {
		return datePub;
	}

	public void setDatePub(String datePub) {
		this.datePub = datePub;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public String getNomEmp() {
		return nomEmp;
	}

	public void setNomEmp(String nomEmp) {
		this.nomEmp = nomEmp;
	}

	public String getPrenomEmp() {
		return prenomEmp;
	}

	public void setPrenomEmp(String prenomEmp) {
		this.prenomEmp = prenomEmp;
	}

	public String getNumTel() {
		return numTel;
	}

	
	
}
