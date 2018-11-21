package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class listeDocument {
	private static listeDocument instance;
	static public ArrayList<Document> arListeDoc = new ArrayList<Document>();
	
	private listeDocument() throws IOException {
		boolean fin = false; 
		 
		 
		FileInputStream fichier = new FileInputStream("document.ser"); 
		    
		ObjectInputStream is = new ObjectInputStream(fichier); 
		Document doc;
		
		try {
			  
		while(	(doc = (Document) is.readObject())!=null){
			arListeDoc.add(doc);
		 
		}
		}
			catch (IOException e) { 
				
			   // fin de fichier ou fichier introuvable.  
			    
			   
			} 
	   
			catch (ClassNotFoundException e) { 
		     
			e.printStackTrace(); 
			   
			} 
		
	}
	
	public static listeDocument getInstance() throws IOException {
		  if (instance == null) 
			  instance = new listeDocument();
		  return instance; 
	}
}
