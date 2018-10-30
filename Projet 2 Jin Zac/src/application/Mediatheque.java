package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mediatheque {
	public void start(Stage primaryStage) {
		try {
			BorderPane root1 = new BorderPane();
			primaryStage.setTitle("Médiathèque");
			primaryStage.setScene(new Scene(root1, 450, 450));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
