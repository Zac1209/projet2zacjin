package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mediatheque extends Application {// Remove extends application post testing

	public void start(Stage primaryStage) {
		try {

			// +++++++++++++++++ BASE "ROOT" DE LA FENETRE MEDIATHEQUE ++++++++++++++++++
			HBox root = new HBox();
			primaryStage.setTitle("Médiathèque");
			primaryStage.setScene(new Scene(root, 900, 600));

			// +++++++++++++++++ TABPANE POUR AFFICHER LES INFORMATIONS +++++++++++++++++
			
			TabPane tabPane = new TabPane();
			tabPane.setPrefWidth(600);
			tabPane.setMaxWidth(600);

			Tab tab1 = new Tab();
			tab1.setText("Document");
			tab1.setClosable(false);
			
			
			Tab tab2 = new Tab();
			tab2.setText("Livre");
			tab2.setClosable(false);
			
			tabPane.getTabs().addAll(tab1,tab2);
			
			// +++++++++++++++++++++++++++ DOCUMENTS ++++++++++++++++++++++++++++++++
			
			
			
			//THE TABLE
			TableView<Document> tabDoc = new TableView<Document>();
			
			TableColumn<Document, String> colTitre = new TableColumn<Document, String>("Titre");
			colTitre.setPrefWidth(120);
			colTitre.setMaxWidth(120);
			
			TableColumn<Document, String> colAuteur = new TableColumn<Document, String>("Auteur");
			colAuteur.setPrefWidth(120);
			colAuteur.setPrefWidth(120);
			
			TableColumn<Document, String> coldispo = new TableColumn<Document, String>("Disponibilité");
			coldispo.setPrefWidth(120);
			coldispo.setPrefWidth(120);
			
			tabDoc.getColumns().addAll(colTitre,colAuteur,coldispo);
			tab1.setContent(tabDoc);
			//TABLE
			
			//THE OLIST
			ObservableList<Document> listDoc = FXCollections.observableArrayList();
			
			colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colTitre.setCellValueFactory(new PropertyValueFactory<>("auteur"));
			//OLIST
			
			
			
			// ----------------------------- LIVRE ------------------------------------
			
			//THE TABLE
			TableView<Livre> tabLivre = new TableView<Livre>();
			
			TableColumn<Livre, String> colTitreLivre = new TableColumn<Livre, String>("Titre");
			colTitreLivre.setPrefWidth(120);
			colTitreLivre.setMaxWidth(120);
			
			TableColumn<Livre, String> colAuteurLivre = new TableColumn<Livre, String>("Auteur");
			colAuteurLivre.setPrefWidth(120);
			colAuteurLivre.setPrefWidth(120);
			
			tabLivre.getColumns().addAll(colTitreLivre,colAuteurLivre);
			tab2.setContent(tabLivre);
			//TABLE
			
			//THE OLIST
			ObservableList<Document> listLivre = FXCollections.observableArrayList();
			
			colTitreLivre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteurLivre.setCellValueFactory(new PropertyValueFactory<>("auteur"));
			//OLIST
			
			
			// ---------------------- TEMPORARY TESTING DATA ---------------------------

			//Document test = new Livre("titre x", "par auteur", 2);
			//listDoc.add(test);

			

			root.getChildren().add(tabPane);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {// remove main post testing
		launch(args);
	}

}
