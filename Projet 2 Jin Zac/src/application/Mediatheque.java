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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mediatheque extends Application{//Remove extends application post testing
	
	public void start(Stage primaryStage) {
		try {		
			
			//+++++++++++++++++ BASE "ROOT" DE LA FENETRE MEDIATHEQUE ++++++++++++++++++
			HBox root = new HBox();
			primaryStage.setTitle("Médiathèque");
			primaryStage.setScene(new Scene(root, 450, 450));
			
			
			//+++++++++++++++++ TABPANE POUR AFFICHER LES INFORMATIONS +++++++++++++++++
			 TabPane tabPane = new TabPane();
			 
			 Tab tab = new Tab();
			 tab.setText("tab1");
			 tab.setClosable(false);
			 //tab.setContent();
			 
			 //---------------------- TEMPORARY TESTING DATA ---------------------------
			 ObservableList<String> strTests = FXCollections.observableArrayList();
			 
			 
			 tabPane.getTabs().add(tab);
			 		 
			 root.getChildren().add(tabPane);
			
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {//remove main post testing
		launch(args);
	}
	
}
