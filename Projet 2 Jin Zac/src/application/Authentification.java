package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Authentification {
	boolean NomAfficher = true;
	public void start(Stage primaryStage) {
	try {
		VBox root = new VBox();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Login");
		//Création des pane composant la page
			// VBox globale du login
			VBox vboxLogin = new VBox();
			root.getChildren().add(vboxLogin);
			
			// VBox Membres du personel
			VBox vboxMembresPersonnel = new VBox();
			HBox hboxIdEmployer = new HBox();
			Label lblIdEmployer = new Label("No. Employé : ");
			TextField tfIdEmployer = new TextField();
			HBox hboxMDPEmployer = new HBox();
			Label lblPasswordEmployer = new Label("Mot de passe: ");
			PasswordField pfPasswordEmployer = new PasswordField();
			Button btnConnexionEmployer = new Button("Connexion");
			hboxIdEmployer.getChildren().addAll(lblIdEmployer,tfIdEmployer);
			hboxMDPEmployer.getChildren().addAll(lblPasswordEmployer,pfPasswordEmployer);
			vboxMembresPersonnel.getChildren().addAll(hboxIdEmployer,hboxMDPEmployer,btnConnexionEmployer);
			
			//VBox Adhérants
			VBox vboxAdherants = new VBox();
			HBox hboxNomEtTelephone = new HBox();
			Label lblNomEtTelephone = new Label("Nom: ");
			TextField tfNomEtTelephone = new TextField();
			HBox hboxPrenom = new HBox();
			Label lblPrenom = new Label("Prénom: ");
			TextField tfPrenom = new TextField();
			Button btnChangerAuTelephoneOuNom = new Button("Changer le mode de connexion pour le téléphone");
			btnChangerAuTelephoneOuNom.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					NomAfficher = !NomAfficher;
					hboxPrenom.setVisible(NomAfficher);
					if(NomAfficher == false) {
						lblNomEtTelephone.setText("Téléphone: ");
					}else {
						lblNomEtTelephone.setText("Nom: ");
					}
				}
			});
			hboxNomEtTelephone.getChildren().addAll(lblNomEtTelephone,tfNomEtTelephone);
			hboxPrenom.getChildren().addAll(lblPrenom,tfPrenom);
			vboxAdherants.getChildren().addAll(hboxNomEtTelephone,hboxPrenom,btnChangerAuTelephoneOuNom);
			
			
			Button btnCatalogue = new Button("Temporaire");
			btnCatalogue.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent event) {
			    	Mediatheque media = new Mediatheque();
			    	Stage stage = new Stage();
			    	media.start(stage);
					primaryStage.close();
			    }
			});
			
			vboxAdherants.getChildren().addAll(btnCatalogue);
			//Ajout des VBox dans celle du login
		vboxLogin.getChildren().addAll(vboxMembresPersonnel,vboxAdherants);
	} catch(Exception e) {
		e.printStackTrace();
	}
}
}
