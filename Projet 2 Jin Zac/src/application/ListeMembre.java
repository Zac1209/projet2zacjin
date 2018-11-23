package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListeMembre {
	static public ArrayList<Membre> arListeMembre = new ArrayList<Membre>();
	private static ListeMembre instance;

	private ListeMembre() throws IOException {
		boolean fin = false;

		FileInputStream fichier = new FileInputStream("membre.ser");

		ObjectInputStream is = new ObjectInputStream(fichier);
		Membre membre;

		try {

			while ((membre = (Membre) is.readObject()) != null) {
				arListeMembre.add(membre);
			}

		} catch (IOException e) {
			// fichier inexistant
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ListeMembre getInstance() {
		if (instance == null)
			try {
				instance = new ListeMembre();
			} catch (IOException e) {
			}
		return instance;
	}

	static public void serializer() {
		try {

			FileOutputStream fichier = new FileOutputStream("membre.ser");

			ObjectOutputStream os = new ObjectOutputStream(fichier);

			for (Membre membre : arListeMembre) {
				os.writeObject(membre);
			}

			os.close();

		}

		catch (IOException e) {

			e.printStackTrace();

		}
	}
}
