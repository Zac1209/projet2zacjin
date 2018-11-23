package application;

import java.awt.Event;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Mediatheque extends Application {// Remove extends application post
												// testing

	public void start(Stage primaryStage) {
		try {

			ListeDocument listeComplete = ListeDocument.getInstance();

			// +++++++++++++++++++++++++++++ FILE READING +++++++++++++++++++++++++++++++

			// +++++++++++++++++ BASE "ROOT" DE LA FENETRE MEDIATHEQUE ++++++++++++++++++
			HBox root = new HBox();
			primaryStage.setTitle("Médiathèque");
			primaryStage.setScene(new Scene(root, 900, 600));
			primaryStage.setOnCloseRequest((WindowEvent event1) -> {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.getButtonTypes().set(0, new ButtonType("Oui"));
				alert.getButtonTypes().set(1, new ButtonType("Non"));
				alert.setTitle("Quitter");
				alert.setHeaderText(null);
				alert.setContentText("Voulez-vous quitter le programme et sauvegarder?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get().getText().equals("Oui")) {
					// Fermer le programme
					ListeDocument.serializer();
					ListeMembre.serializer();
				} else {
					// ne pas fermer
					event1.consume();
				}
			});

			// +++++++++++++++++ TABPANE POUR AFFICHER LES INFORMATIONS +++++++++++++++++

			// TabPane containing all info on docs for both client and admin
			TabPane tabPane = new TabPane();
			tabPane.setPrefWidth(700);
			tabPane.setMaxWidth(700);
			tabPane.setPadding(new Insets(20));

			Tab tab1 = new Tab();
			tab1.setText("Documents");
			tab1.setClosable(false);

			Tab tab2 = new Tab();
			tab2.setText("Livres");
			tab2.setClosable(false);

			Tab tab3 = new Tab();
			tab3.setText("Périodiques");
			tab3.setClosable(false);

			Tab tab4 = new Tab();
			tab4.setText("DVDs");
			tab4.setClosable(false);

			tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
			
			root.getChildren().add(tabPane);

			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// +++++++++++++++++++++++++++ CATALOGUE ++++++++++++++++++++++++++++++++
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

			// +++++++++++++++++++++++++++ DOCUMENTS ++++++++++++++++++++++++++++++++

			// THE TABLE
			TableView<Document> tabDoc = new TableView<Document>();

			TableColumn<Document, String> colNum = new TableColumn<Document, String>("Numéro Document");
			colNum.setPrefWidth(120);
			colNum.setMaxWidth(120);

			TableColumn<Document, String> colTitre = new TableColumn<Document, String>("Titre");
			colTitre.setPrefWidth(120);
			colTitre.setMaxWidth(120);

			TableColumn<Document, String> colAuteur = new TableColumn<Document, String>("Auteur");
			colAuteur.setPrefWidth(120);
			colAuteur.setPrefWidth(120);

			TableColumn<Document, String> coldispo = new TableColumn<Document, String>("Disponibilité");
			coldispo.setPrefWidth(120);
			coldispo.setPrefWidth(120);

			tabDoc.getColumns().addAll(colNum, colTitre, colAuteur, coldispo);
			tab1.setContent(tabDoc);

			// THE OLIST
			ObservableList<Document> listDoc = FXCollections.observableArrayList();

			colNum.setCellValueFactory(new PropertyValueFactory<>("strNumero"));
			colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
			coldispo.setCellValueFactory(new PropertyValueFactory<>("strDispo"));

			tabDoc.setItems(listDoc);

			// for each document in list document add to listdoc
			for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
				listDoc.add(listeComplete.arListeDoc.get(i));
			}

			// +++++++++++++++++++++++++++++++++++ LIVRE ++++++++++++++++++++++++++++++++++++++

			// THE TABLE
			TableView<Livre> tabLivre = new TableView<Livre>();

			TableColumn<Livre, String> colNumLivre = new TableColumn<Livre, String>("Numéro Livre");
			colNumLivre.setPrefWidth(120);
			colNumLivre.setMaxWidth(120);

			TableColumn<Livre, String> colTitreLivre = new TableColumn<Livre, String>("Titre");
			colTitreLivre.setPrefWidth(120);
			colTitreLivre.setMaxWidth(120);

			TableColumn<Livre, String> colAuteurLivre = new TableColumn<Livre, String>("Auteur");
			colAuteurLivre.setPrefWidth(120);
			colAuteurLivre.setPrefWidth(120);

			tabLivre.getColumns().addAll(colNumLivre, colTitreLivre, colAuteurLivre);
			tab2.setContent(tabLivre);

			// THE OLIST
			ObservableList<Livre> listLivre = FXCollections.observableArrayList();


			colNumLivre.setCellValueFactory(new PropertyValueFactory<>("strNumero"));	
			colTitreLivre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteurLivre.setCellValueFactory(new PropertyValueFactory<>("auteur"));

			tabLivre.setItems(listLivre);

			for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
				if (listeComplete.arListeDoc.get(i) instanceof Livre)
					listLivre.add((Livre) listeComplete.arListeDoc.get(i));
			}

			// +++++++++++++++++++++++++++++++++ PERIODIQUE ++++++++++++++++++++++++++++++++++++++++++

			// THE TABLE
			TableView<Periodique> tabP = new TableView<Periodique>();

			TableColumn<Periodique, String> colNumP = new TableColumn<Periodique, String>("Numéro Périodique");
			colNumP.setPrefWidth(120);
			colNumP.setMaxWidth(120);

			TableColumn<Periodique, String> colTitreP = new TableColumn<Periodique, String>("Titre");
			colTitreP.setPrefWidth(120);
			colTitreP.setMaxWidth(120);

			TableColumn<Periodique, String> colAuteurP = new TableColumn<Periodique, String>("Numero du periodique");
			colAuteurP.setPrefWidth(120);
			colAuteurP.setPrefWidth(120);

			tabP.getColumns().addAll(colNumP, colTitreP, colAuteurP);
			tab3.setContent(tabP);

			// THE OLIST
			ObservableList<Periodique> listP = FXCollections.observableArrayList();

			colNumP.setCellValueFactory(new PropertyValueFactory<>("strNumero"));
			colTitreP.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteurP.setCellValueFactory(new PropertyValueFactory<>("strNumeroPeriodique"));

			tabP.setItems(listP);

			for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
				if (listeComplete.arListeDoc.get(i) instanceof Periodique)
					listP.add((Periodique) listeComplete.arListeDoc.get(i));
			}

			// +++++++++++++++++++++++++++++++++ DVD ++++++++++++++++++++++++++++++++++++++++++

			// THE TABLE
			TableView<DVD> tabD = new TableView<DVD>();

			TableColumn<DVD, String> colNumD = new TableColumn<DVD, String>("Numéro Périodique");
			colNumD.setPrefWidth(120);
			colNumD.setMaxWidth(120);

			TableColumn<DVD, String> colTitreD = new TableColumn<DVD, String>("Titre");
			colTitreD.setPrefWidth(120);
			colTitreD.setMaxWidth(120);

			TableColumn<DVD, String> colAuteurD = new TableColumn<DVD, String>("Auteur");
			colAuteurD.setPrefWidth(120);
			colAuteurD.setPrefWidth(120);

			tabD.getColumns().addAll(colNumD, colTitreD, colAuteurD);
			tab4.setContent(tabD);

			// THE OLIST
			ObservableList<DVD> listD = FXCollections.observableArrayList();

			colNumD.setCellValueFactory(new PropertyValueFactory<>("strNumero"));
			colTitreD.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteurD.setCellValueFactory(new PropertyValueFactory<>("auteur"));

			tabD.setItems(listD);

			for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
				if (listeComplete.arListeDoc.get(i) instanceof DVD)
					listD.add((DVD) listeComplete.arListeDoc.get(i));
			}

			// +++++++++++++++++++++++++++++ OPTIONS A DROITE ++++++++++++++++++++++++++++++++++++++

			VBox rightSide = new VBox();
			rightSide.setPrefSize(200, 600);
			rightSide.setPadding(new Insets(20));
			rightSide.setAlignment(Pos.TOP_CENTER);
			VBox recherche = new VBox();
			Label lblR = new Label("recherche");
			TextField tfR = new TextField();
			
			//RECHERCHER????? REMOVE IF NOT DONE
			EventHandler<KeyEvent> gestionClavier = new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e) {
					// TODO Auto-generated method stub

				}
			};

			tfR.setOnKeyTyped(gestionClavier);
			tfR.setPrefSize(200, 30);

			recherche.getChildren().addAll(lblR, tfR);
			
			root.getChildren().add(rightSide);
			
			if (!Authentification.getBooConnexionViaMembrePersonnel()) {

				Button btnAjoutDoc = new Button("Ajouter un Document");
				btnAjoutDoc.setPrefSize(200, 40);
				VBox.setMargin(btnAjoutDoc, new Insets(10));
				
				
				EventHandler<MouseEvent> eventAjoutDoc = new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						// TODO Auto-generated method stub
						

					}
				};

				btnAjoutDoc.setOnMousePressed(eventAjoutDoc);
				
				
				Button btnSuprDoc = new Button("Supprimer un document");
				btnSuprDoc.setPrefSize(200, 40);
				VBox.setMargin(btnSuprDoc, new Insets(10));

				Button btnGererUsers = new Button("gerer les adherants");
				btnGererUsers.setPrefSize(200, 40);
				VBox.setMargin(btnGererUsers, new Insets(10));

				Button btnPret = new Button("Inscrire un pret");
				btnPret.setPrefSize(200, 40);
				VBox.setMargin(btnPret, new Insets(10));

				Button btnRetour = new Button("Inscrire un retour");
				btnRetour.setPrefSize(200, 40);
				VBox.setMargin(btnRetour, new Insets(10));

				
				rightSide.getChildren().addAll(recherche, btnAjoutDoc, btnSuprDoc, btnGererUsers, btnPret, btnRetour);

			}	
			else// adherant
			{
				Button btnConsulter = new Button("Consulter son compte");
				btnConsulter.setPrefSize(200, 40);
				VBox.setMargin(btnConsulter, new Insets(10));
				rightSide.getChildren().add(btnConsulter);
			}

			
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {// remove main post testing
		launch(args);
	}

}
