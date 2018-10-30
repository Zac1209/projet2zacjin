package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Authentification {

	public void start(Stage primaryStage) {
	try {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Bienvenue à la médiathèque");
		//Création des pane composant la page
			// VBox globale du login
			VBox vboxLogin = new VBox();
			root.getChildren().add(vboxLogin);
			// VBox Membres du personel
			VBox vboxMembresPersonnel = new VBox();
			//HBox Adhérants
			HBox hboxAdherants = new HBox();
			hboxAdherants.setSpacing(20);
			Button btnCatalogue = new Button("Catalogue");
			
			Button btnMonDossier = new Button("Mon Dossier");
			hboxAdherants.getChildren().addAll(btnCatalogue,btnMonDossier);
			//Ajout des VBox dans celle du login
		vboxLogin.getChildren().addAll(vboxMembresPersonnel,hboxAdherants);
	} catch(Exception e) {
		e.printStackTrace();
	}
}
}
