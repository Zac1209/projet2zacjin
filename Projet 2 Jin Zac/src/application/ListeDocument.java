package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.StringTokenizer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ListeDocument {
	private static ListeDocument instance;
	static public ArrayList<Document> arListeDoc = new ArrayList<Document>();

	private ListeDocument() throws IOException {
		boolean fin = false;
		ObjectInputStream is = null;
		try {
			FileInputStream fichier = new FileInputStream("document.ser");
			is = new ObjectInputStream(fichier);
			Document doc;

			try {

				while ((doc = (Document) is.readObject()) != null) {
					arListeDoc.add(doc);
				}
			} catch (IOException e) {
			}

			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("premiere lecture");
			// lecture des données si première fois que le programme est lancé
			System.out.println("Première lecture du programme");
			// Lecture des dvd
			lireFichier("DVD.txt", "dvd");
			// Lecture des livres
			lireFichier("Livres.txt", "livre");
			// Lectures des périodiques
			lireFichier("Periodiques.txt", "periodique");
		}
	}

	public static ListeDocument getInstance() throws IOException {
		if (instance == null)
			instance = new ListeDocument();
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
							stLigne.nextToken().trim(), null, stLigne.nextToken().trim(), stLigne.nextToken().trim()));
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

	static public void serializer() {
		try {
			FileOutputStream fichier = new FileOutputStream("document.ser");
			ObjectOutputStream os = new ObjectOutputStream(fichier);

			for (Document doc : arListeDoc) {
				os.writeObject(doc);
			}
			os.close();
			System.out.println("Documents sauvegardés");
		}

		catch (IOException e) {
			e.printStackTrace();

		}
	}

}
