package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
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
			hboxIdEmployer.setAlignment(Pos.CENTER);
			Label lblIdEmployer = new Label("No. Employé : ");
			TextField tfIdEmployer = new TextField();
			HBox hboxMDPEmployer = new HBox();
			hboxMDPEmployer.setAlignment(Pos.CENTER);
			Label lblPasswordEmployer = new Label("Mot de passe: ");
			PasswordField pfPasswordEmployer = new PasswordField();
			Button btnConnexionEmployer = new Button("Connexion");
			hboxIdEmployer.getChildren().addAll(lblIdEmployer,tfIdEmployer);
			hboxMDPEmployer.getChildren().addAll(lblPasswordEmployer,pfPasswordEmployer);
			vboxMembresPersonnel.getChildren().addAll(hboxIdEmployer,hboxMDPEmployer,btnConnexionEmployer);
			
			//VBox Adhérants
			VBox vboxAdherants = new VBox();
			GridPane gpAdherants = new GridPane();
			Label lblNomEtTelephone = new Label("Nom: ");
			TextField tfNomEtTelephone = new TextField();
			Label lblPrenom = new Label("Prénom: ");
			TextField tfPrenom = new TextField();
			gpAdherants.setAlignment(Pos.CENTER);
			ColumnConstraints col1 = new ColumnConstraints();
			col1.setMinWidth(65);
			gpAdherants.getColumnConstraints().add(col1);
				//Régler l'alignement des labels à droite
			GridPane.setHalignment(lblNomEtTelephone, HPos.RIGHT);
			GridPane.setHalignment(lblPrenom, HPos.RIGHT);
			
			Button btnChangerAuTelephoneOuNom = new Button("Changer le mode de connexion pour le téléphone");
			btnChangerAuTelephoneOuNom.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					NomAfficher = !NomAfficher;
					lblPrenom.setVisible(NomAfficher);
					tfPrenom.setVisible(NomAfficher);
					if(NomAfficher == false) {
						lblNomEtTelephone.setText("Téléphone: ");
					}else {
						lblNomEtTelephone.setText("Nom: ");
					}
				}
			});
			
			Button btnCatalogue = new Button("Temporaire");
			btnCatalogue.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent event) {
			    	Mediatheque media = new Mediatheque();
			    	Stage stage = new Stage();
			    	media.start(stage);
					primaryStage.close();
			    }
			});
			
			//Ajout des VBox dans celle du login
			vboxLogin.getChildren().addAll(vboxMembresPersonnel,vboxAdherants,btnCatalogue);
			
			//Placement des éléments de la GridPane du login adhérants
			vboxAdherants.getChildren().addAll(gpAdherants,btnChangerAuTelephoneOuNom);
			gpAdherants.add(lblNomEtTelephone, 0, 0);
			gpAdherants.add(tfNomEtTelephone, 1, 0);
			gpAdherants.add(lblPrenom, 0, 1);
			gpAdherants.add(tfPrenom, 1, 1);
	} catch(Exception e) {
		e.printStackTrace();
	}
}
}
