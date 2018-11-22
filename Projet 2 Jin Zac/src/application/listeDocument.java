package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class listeDocument {
	private static listeDocument instance;
	static public ArrayList<Document> arListeDoc = new ArrayList<Document>();

	private listeDocument() throws IOException {
		boolean fin = false;

		FileInputStream fichier = new FileInputStream("document.ser");

		ObjectInputStream is = new ObjectInputStream(fichier);
		Document doc;

		try {

			while ((doc = (Document) is.readObject()) != null) {
				arListeDoc.add(doc);
			}

		} catch (IOException e) {
			// lecture des données si première fois que le programme est lancé
			System.out.println("Première lecture du programme");
				// Lecture des dvd
			lireFichier("DVD.txt","dvd");
				// Lecture des livres
			lireFichier("Livres.txt","livre");
				// Lectures des périodiques
			lireFichier("Periodiques.txt","periodique");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static listeDocument getInstance() throws IOException {
		if (instance == null)
			instance = new listeDocument();
		return instance;
	}

	private void lireFichier(String strNomFichier, String strType) {
		BufferedReader br = null;
		FileReader fr = null;
		try {

			fr = new FileReader(strNomFichier);
			br = new BufferedReader(fr);

			String strLigne;
			while ((strLigne = br.readLine()) != null) {
				StringTokenizer stLigne = new StringTokenizer(strLigne, ",");
				switch (strType) {
				case "dvd":
					arListeDoc.add(
							new DVD(stLigne.nextToken().trim(), stLigne.nextToken().trim(), stLigne.nextToken().trim(),
									Integer.parseInt(stLigne.nextToken().trim()), stLigne.nextToken().trim()));
					break;
				case "livre":
					arListeDoc.add(new Livre(stLigne.nextToken().trim(), stLigne.nextToken().trim(),
							stLigne.nextToken().trim(), stLigne.nextToken().trim()));
					break;
				case "periodique":
					arListeDoc.add(new Periodique(stLigne.nextToken().trim(), stLigne.nextToken().trim(),
							stLigne.nextToken().trim(), stLigne.nextToken().trim(),
							Integer.parseInt(stLigne.nextToken().trim()),
							Integer.parseInt(stLigne.nextToken().trim())));
					break;
				}
				
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
}
