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
			
			//TabPane containing all info on docs for both client and admin
			TabPane tabPane = new TabPane();
			tabPane.setPrefWidth(600);
			tabPane.setMaxWidth(600);

			Tab tab1 = new Tab();
			tab1.setText("Documents");
			tab1.setClosable(false);
			
			
			Tab tab2 = new Tab();
			tab2.setText("Livres");
			tab2.setClosable(false);
			
			Tab tab3 = new Tab();
			tab2.setText("Périodiques");
			tab2.setClosable(false);
			
			Tab tab4 = new Tab();
			tab2.setText("DVDs");
			tab2.setClosable(false);
			
			
			tabPane.getTabs().addAll(tab1,tab2,tab3,tab4);
			
			// +++++++++++++++++++++++++++ DOCUMENTS ++++++++++++++++++++++++++++++++
			
			
			
			//THE TABLE
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
			
			tabDoc.getColumns().addAll(colTitre,colAuteur,coldispo,colNum);
			tab1.setContent(tabDoc);
			
			//THE OLIST
			ObservableList<Document> listDoc = FXCollections.observableArrayList();
			
			colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colTitre.setCellValueFactory(new PropertyValueFactory<>("auteur"));
			
			
			
			// ----------------------------- LIVRE ------------------------------------      @ here
			
			//THE TABLE
			TableView<Livre> tabLivre = new TableView<Livre>();
			
			TableColumn<Livre, String> colNumLivre = new TableColumn<Livre, String>("Numéro Document");
			colNumLivre.setPrefWidth(120);
			colNumLivre.setMaxWidth(120);
			
			TableColumn<Livre, String> colTitreLivre = new TableColumn<Livre, String>("Titre");
			colTitreLivre.setPrefWidth(120);
			colTitreLivre.setMaxWidth(120);
			
			TableColumn<Livre, String> colAuteurLivre = new TableColumn<Livre, String>("Auteur");
			colAuteurLivre.setPrefWidth(120);
			colAuteurLivre.setPrefWidth(120);
			
			tabLivre.getColumns().addAll(colTitreLivre,colAuteurLivre);
			tab2.setContent(tabLivre);
			
			//THE OLIST
			ObservableList<Document> listLivre = FXCollections.observableArrayList();
			
			colTitreLivre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteurLivre.setCellValueFactory(new PropertyValueFactory<>("auteur"));
			
			
			
			//+++++++++++++++++++++++++++++++++ PERIODIQUE +++++++++++++++++++++++++++++++++++++
			//CHANGE THESE FOR CLASS PERIODIQUE
			
			//THE TABLE
			TableView<Livre> tabP = new TableView<Livre>();
			
			TableColumn<Livre, String> colNumP = new TableColumn<Livre, String>("Numéro Document");
			colNumP.setPrefWidth(120);
			colNumP.setMaxWidth(120);
			
			TableColumn<Livre, String> colTitreP = new TableColumn<Livre, String>("Titre");
			colTitreP.setPrefWidth(120);
			colTitreP.setMaxWidth(120);
			
			TableColumn<Livre, String> colAuteurP = new TableColumn<Livre, String>("Auteur");
			colAuteurP.setPrefWidth(120);
			colAuteurP.setPrefWidth(120);
			
			tabP.getColumns().addAll(colNumP,colTitreLivre,colAuteurLivre);
			tab2.setContent(tabLivre);
			
			//THE OLIST
			ObservableList<Document> listP = FXCollections.observableArrayList();
			
			colTitreP.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteurP.setCellValueFactory(new PropertyValueFactory<>("auteur"));

			
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
