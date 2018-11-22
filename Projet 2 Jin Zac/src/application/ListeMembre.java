package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ListeMembre {
	static public ArrayList<Membre> arListeMembre = new ArrayList<Membre>();
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
}
