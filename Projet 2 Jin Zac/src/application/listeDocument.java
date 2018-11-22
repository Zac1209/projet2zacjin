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
				// Lecture des dvd
			lireFichierDvd("DVD.txt");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static listeDocument getInstance() throws IOException {
		if (instance == null)
			instance = new listeDocument();
		return instance;
	}

	private void lireFichierDvd(String strNomFichier) {
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<Document> arrayDocuments = new ArrayList<Document>();
		try {

			fr = new FileReader(strNomFichier);
			br = new BufferedReader(fr);

			String strLigne;
			while ((strLigne = br.readLine()) != null) {
				StringTokenizer stLigne = new StringTokenizer(strLigne, ",");
				arrayDocuments
						.add(new DVD(stLigne.nextToken().trim(), stLigne.nextToken().trim(), stLigne.nextToken().trim(),
								Integer.parseInt(stLigne.nextToken().trim()), stLigne.nextToken().trim()));
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
