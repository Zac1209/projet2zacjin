package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Authentification {
	private boolean NomAfficher = true;
	private boolean booUtilisationTelephoneConnexion = false;
	private static boolean booConnexionViaMembrePersonnel; // Savoir si on se connecte via un membre du personnel ou
															// adh�rant
	private static String strPrenom;
	private static String strNom;
	private static String strTelephone;
	private static String strNoEmploye;
	private static String strMotDePasse;
	ListeMembre lstMembre = ListeMembre.getInstance();

	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Bienvenue � la m�diath�que");
			primaryStage.setResizable(false);
			root.setPadding(new Insets(10, 10, 10, 10));
			Alerte("Pour Mme Cherief: Connection employ� id 1, mdp 1, adh�rant nom guo pr�nom jin num�ro de tel�phone 4504556749",
					"Information");
			// Cr�ation des pane composant la page
			// VBox globale du login
			VBox vboxLogin = new VBox();
			vboxLogin.setSpacing(10);
			root.getChildren().add(vboxLogin);

			// VBox Membres du personel
			VBox vboxMembresPersonnel = new VBox();
			HBox hboxIdEmployer = new HBox();
			Label lblIdEmployer = new Label("No. Employ� : ");
			TextField tfIdEmployer = new TextField();
			HBox hboxMDPEmployer = new HBox();
			Label lblPasswordEmployer = new Label("Mot de passe: ");
			PasswordField pfPasswordEmployer = new PasswordField();
			Button btnConnexionEmployer = new Button("Connexion");

			vboxMembresPersonnel.setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			vboxMembresPersonnel.setPadding(new Insets(10, 10, 10, 10));
			hboxIdEmployer.setAlignment(Pos.CENTER);
			hboxMDPEmployer.setAlignment(Pos.CENTER);
			btnConnexionEmployer.setAlignment(Pos.CENTER);
			vboxMembresPersonnel.setAlignment(Pos.CENTER);
			vboxMembresPersonnel.setSpacing(7);

			btnConnexionEmployer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					booConnexionViaMembrePersonnel = true;
					strNoEmploye = tfIdEmployer.getText();
					int compteur = 0;
					strMotDePasse = pfPasswordEmployer.getText();
					if (strNoEmploye.trim().equals("") || strMotDePasse.trim().equals("")) {
						Alerte("Veuillez entrer un num�ro d'employ� ET un mot de passe valide", "Erreur");
					} else {
						boolean booTrouve = false;
						for (; compteur < ListeMembre.arListeMembre.size() && booTrouve != true; compteur++) {
							if (strNoEmploye.equals(ListeMembre.arListeMembre.get(compteur).getStrIdPrep())) {
								booTrouve = true;
								if (ListeMembre.arListeMembre.get(compteur).getStrMotDePasse().equals(strMotDePasse)) {
									Mediatheque media = new Mediatheque();
									Stage stage = new Stage();
									media.start(stage);
									primaryStage.close();
								} else {
									Alerte("Mot de passe incorect", "Erreur");
								}

							}

						}
						if (booTrouve == false) {
							Alerte("ID incorrect", "Erreur");
						}
					}
				}

			});

			hboxIdEmployer.getChildren().addAll(lblIdEmployer, tfIdEmployer);
			hboxMDPEmployer.getChildren().addAll(lblPasswordEmployer, pfPasswordEmployer);
			vboxMembresPersonnel.getChildren().addAll(hboxIdEmployer, hboxMDPEmployer, btnConnexionEmployer);

			// VBox Adh�rants
			VBox vboxAdherants = new VBox();
			GridPane gpAdherants = new GridPane();
			Label lblNomEtTelephone = new Label("Nom: ");
			TextField tfNomEtTelephone = new TextField();
			Label lblPrenom = new Label("Pr�nom: ");
			TextField tfPrenom = new TextField();
			Button btnChangerAuTelephoneOuNom = new Button("Changer le mode de connexion pour le t�l�phone");
			Button btnConnexionAdherants = new Button("Connexion Adh�rants");

			vboxAdherants.setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			vboxAdherants.setPadding(new Insets(10, 10, 10, 10));

			btnChangerAuTelephoneOuNom.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					booUtilisationTelephoneConnexion = !booUtilisationTelephoneConnexion;
					NomAfficher = !NomAfficher;
					lblPrenom.setVisible(NomAfficher);
					tfPrenom.setVisible(NomAfficher);
					if (NomAfficher == false) {
						lblNomEtTelephone.setText("T�l�phone: ");
					} else {
						lblNomEtTelephone.setText("Nom: ");
					}
				}
			});

			// D�finir les propri�t� d'alignement de la gridpane adh�rants
			GridPane.setHalignment(lblNomEtTelephone, HPos.RIGHT);
			GridPane.setHalignment(lblPrenom, HPos.RIGHT);
			gpAdherants.setVgap(7);
			gpAdherants.setHgap(7);
			gpAdherants.setAlignment(Pos.CENTER);
			ColumnConstraints col1 = new ColumnConstraints();
			col1.setMinWidth(65);
			ColumnConstraints col2 = new ColumnConstraints();
			col2.setMaxWidth(150);
			gpAdherants.getColumnConstraints().addAll(col1, col2);
			btnChangerAuTelephoneOuNom.wrapTextProperty().setValue(true);
			btnChangerAuTelephoneOuNom.textAlignmentProperty().set(TextAlignment.CENTER);
			btnConnexionAdherants.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					booConnexionViaMembrePersonnel = false;
					boolean booOk = true;
					if (booUtilisationTelephoneConnexion == true) {
						strTelephone = tfNomEtTelephone.getText();
						if (strTelephone.trim().equals("")) {
							booOk = false;
							Alerte("Veuillez entrer un num�ro de t�l�phone", "Erreur");
						}
					} else {
						strPrenom = tfPrenom.getText();
						strNom = tfNomEtTelephone.getText();
						if (strPrenom.trim().equals("") || strNom.trim().equals("")) {
							booOk = false;
							Alerte("Veuillez entrer un nom ET pr�nom", "Erreur");
						}
					}
					if (booOk == true) {
						boolean booTrouve = false;
						int compteur = 0;
						for (; compteur < ListeMembre.arListeMembre.size() && booTrouve != true; compteur++) {
							if (booUtilisationTelephoneConnexion) {
								if (ListeMembre.arListeMembre.get(compteur).getStrNumTel().equals(strTelephone)) {
									booTrouve = true;
								}
							} else {
								if (ListeMembre.arListeMembre.get(compteur).getStrNom().toLowerCase()
										.equals(strNom.toLowerCase().trim())
										&& ListeMembre.arListeMembre.get(compteur).getStrPrenom().toLowerCase()
												.equals(strPrenom.toLowerCase().trim())) {
									booTrouve = true;
								}
							}

						}
						if (booTrouve == false) {
							Alerte("Identifiants incorrects", "Erreur");
						} else {
							Mediatheque media = new Mediatheque();
							Stage stage = new Stage();
							media.start(stage);
							primaryStage.close();
						}
					}

				}
			});

			// Ajout des VBox dans celle du login
			vboxLogin.getChildren().addAll(vboxMembresPersonnel, vboxAdherants);

			// Placement des �l�ments de la GridPane du login adh�rants
			vboxAdherants.getChildren().addAll(gpAdherants);
			gpAdherants.add(lblNomEtTelephone, 0, 0);
			gpAdherants.add(tfNomEtTelephone, 1, 0);
			gpAdherants.add(lblPrenom, 0, 1);
			gpAdherants.add(tfPrenom, 1, 1);
			gpAdherants.add(btnChangerAuTelephoneOuNom, 1, 2, 1, 1);
			gpAdherants.add(btnConnexionAdherants, 1, 3, 1, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Alerte(String strMessage, String strTitre) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(strTitre);
		alert.setHeaderText(null);
		alert.setContentText(strMessage);
		alert.showAndWait();
	}

	public static boolean getBooConnexionViaMembrePersonnel() {
		return booConnexionViaMembrePersonnel;
	}

	public static String getStrPrenom() {
		return strPrenom;
	}

	public static String getStrNom() {
		return strNom;
	}

	public static String getStrTelephone() {
		return strTelephone;
	}

	public static String getStrNoEmploye() {
		return strNoEmploye;
	}
}
