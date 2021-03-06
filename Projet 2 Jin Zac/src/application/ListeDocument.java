package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import javafx.util.converter.DateTimeStringConverter;

public class ListeDocument {
	private static ListeDocument instance;
	static public ArrayList<Document> arListeDoc = new ArrayList<Document>();

	private ListeDocument() throws IOException {
		boolean fin = false;

		try {
			FileInputStream fichier = new FileInputStream("document.ser");
			ObjectInputStream is = new ObjectInputStream(fichier);
			Document doc;
			try {
				while ((doc = (Document) is.readObject()) != null) {
					arListeDoc.add(doc);
				}
			} catch (IOException e) {
			}

		} catch (IOException e) {
			// lecture des donn�es si premi�re fois que le programme est lanc�
			System.out.println("Premi�re lecture du programme!");
			// Lecture des dvd
			lireFichier("DVD.txt", "dvd");
			// Lecture des livres
			lireFichier("Livres.txt", "livre");
			// Lectures des p�riodiques
			lireFichier("Periodiques.txt", "periodique");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			DateTimeStringConverter format = new DateTimeStringConverter(Locale.CANADA, "dd-MM-yyyy");
			while ((strLigne = br.readLine()) != null) {
				StringTokenizer stLigne = new StringTokenizer(strLigne, ",");
				switch (strType) {
				case "dvd":
					arListeDoc.add(new DVD(stLigne.nextToken().trim(), stLigne.nextToken().trim(),
							format.fromString(stLigne.nextToken().trim()), Integer.parseInt(stLigne.nextToken().trim()),
							stLigne.nextToken().trim()));
					break;
				case "livre":
					arListeDoc.add(new Livre(stLigne.nextToken().trim(), stLigne.nextToken().trim(),
							format.fromString(stLigne.nextToken().trim()), stLigne.nextToken().trim()));
					break;
				case "periodique":
					arListeDoc.add(new Periodique(stLigne.nextToken().trim(), stLigne.nextToken().trim(),
							format.fromString(stLigne.nextToken().trim()), null, stLigne.nextToken().trim(),
							stLigne.nextToken().trim()));
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

		}

		catch (IOException e) {

			e.printStackTrace();

		}
	}

}
