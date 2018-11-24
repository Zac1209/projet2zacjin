package application;

import java.awt.Event;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.DateTimeStringConverter;

public class Mediatheque extends Application {// Remove extends application post
												// testing
	Label lblNoDVD;
	Label lblVolume;
	Label lbPeriodique;
	TextField tfNoDvd;
	TextField tfVolume;
	TextField tfPeriodique;

	public void start(Stage primaryStage) {
		try {

			ListeDocument listeComplete = ListeDocument.getInstance();
			ListeMembre lstMembre = ListeMembre.getInstance();

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

			// +++++++++++++++++++++++++++++++++++ LIVRE
			// ++++++++++++++++++++++++++++++++++++++

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

			// +++++++++++++++++++++++++++++++++ PERIODIQUE
			// ++++++++++++++++++++++++++++++++++++++++++

			// THE TABLE
			TableView<Periodique> tabP = new TableView<Periodique>();

			TableColumn<Periodique, String> colNumP = new TableColumn<Periodique, String>("Numéro Périodique");
			colNumP.setPrefWidth(120);
			colNumP.setMaxWidth(120);

			TableColumn<Periodique, String> colTitreP = new TableColumn<Periodique, String>("Titre");
			colTitreP.setPrefWidth(120);

			TableColumn<Periodique, String> colAuteurP = new TableColumn<Periodique, String>("Numero de la périodique");
			colAuteurP.setPrefWidth(150);
			colAuteurP.setMaxWidth(150);

			TableColumn<Periodique, String> colNumV = new TableColumn<Periodique, String>("Numéro du volume");
			colNumV.setPrefWidth(120);
			colNumV.setMaxWidth(120);

			tabP.getColumns().addAll(colNumP, colTitreP, colAuteurP, colNumV);
			tab3.setContent(tabP);

			// THE OLIST
			ObservableList<Periodique> listP = FXCollections.observableArrayList();

			colNumP.setCellValueFactory(new PropertyValueFactory<>("strNumero"));
			colTitreP.setCellValueFactory(new PropertyValueFactory<>("titre"));
			colAuteurP.setCellValueFactory(new PropertyValueFactory<>("strNumeroPeriodique"));
			colNumV.setCellValueFactory(new PropertyValueFactory<>("strNumeroVolume"));

			tabP.setItems(listP);

			for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
				if (listeComplete.arListeDoc.get(i) instanceof Periodique)
					listP.add((Periodique) listeComplete.arListeDoc.get(i));
			}

			// +++++++++++++++++++++++++++++++++ DVD
			// ++++++++++++++++++++++++++++++++++++++++++

			// THE TABLE
			TableView<DVD> tabD = new TableView<DVD>();

			TableColumn<DVD, String> colNumD = new TableColumn<DVD, String>("Nombre de DVD");
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

			// +++++++++++++++++++++++++++++ OPTIONS A DROITE
			// ++++++++++++++++++++++++++++++++++++++

			VBox rightSide = new VBox();
			rightSide.setPrefSize(200, 600);
			rightSide.setPadding(new Insets(20));
			rightSide.setAlignment(Pos.TOP_CENTER);

			root.getChildren().add(rightSide);

			if (Authentification.getBooConnexionViaMembrePersonnel()) {

				Button btnAjoutDoc = new Button("Ajouter un Document");
				btnAjoutDoc.setPrefSize(200, 40);
				VBox.setMargin(btnAjoutDoc, new Insets(10));

				EventHandler<MouseEvent> eventAjoutDoc = new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						// TODO Auto-generated method stub
						VBox root = new VBox();
						Scene scene = new Scene(root, 500, 525);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stage primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.show();
						primaryStage.setTitle("Ajouter un document");
						primaryStage.setResizable(false);

						ComboBox<String> cbTypeDocument = new ComboBox();
						cbTypeDocument.getItems().setAll("Livre", "DVD", "Périodique");
						cbTypeDocument.getSelectionModel().selectFirst();
						TitledPane tpType = new TitledPane("Type de document", cbTypeDocument);

						cbTypeDocument.valueProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue ov, String t, String t1) {
								String strValeurChoisie = cbTypeDocument.getValue();
								switch (strValeurChoisie) {
								case "Livre":
									lblVolume.setVisible(false);
									lbPeriodique.setVisible(false);
									tfVolume.setVisible(false);
									tfPeriodique.setVisible(false);
									lblNoDVD.setVisible(false);
									tfNoDvd.setVisible(false);
									break;
								case "DVD":
									lblNoDVD.setVisible(true);
									tfNoDvd.setVisible(true);

									lblVolume.setVisible(false);
									lbPeriodique.setVisible(false);
									tfVolume.setVisible(false);
									tfPeriodique.setVisible(false);
									break;
								case "Périodique":
									lblVolume.setVisible(true);
									lbPeriodique.setVisible(true);
									tfVolume.setVisible(true);
									tfPeriodique.setVisible(true);

									lblNoDVD.setVisible(false);
									tfNoDvd.setVisible(false);
									break;
								}
							}
						});
						HBox hbSaisieInformation = new HBox();
						VBox vbSaiseLabel = new VBox();
						VBox vbSaisieTextField = new VBox();
						Label lbTitre = new Label("Titre :");
						Label lbAuteur = new Label("Auteur :");
						Label lbDatePub = new Label("Date de publication :");
						lblNoDVD = new Label("Nombre de dvd :"); // Seulement dvd
						lblVolume = new Label("Numéro du volume : "); // seulement périodique
						lbPeriodique = new Label("Numéro de la périodique :");// seulement périodique
						TextField tfTitre = new TextField();
						TextField tfAuteur = new TextField();
						DatePicker tfDatePub = new DatePicker();
						tfNoDvd = new TextField();// Seulement dvd
						tfVolume = new TextField();// seulement périodique
						tfPeriodique = new TextField();// seulement périodique
						vbSaiseLabel.getChildren().addAll(lbTitre, lbAuteur, lbDatePub, lblNoDVD, lblVolume,
								lbPeriodique);
						vbSaisieTextField.getChildren().addAll(tfTitre, tfAuteur, tfDatePub, tfNoDvd, tfVolume,
								tfPeriodique);
						TitledPane tpSaisie = new TitledPane("Saisie d'informations", hbSaisieInformation);
						vbSaiseLabel.setSpacing(10);
						hbSaisieInformation.getChildren().addAll(vbSaiseLabel, vbSaisieTextField);
						HBox hbBouttons = new HBox();
						Button btnAnnuler = new Button("Annuler");
						Button btnConfirmer = new Button("Confirmer");
						hbBouttons.getChildren().addAll(btnAnnuler, btnConfirmer);
						hbBouttons.setSpacing(4);
						hbBouttons.setAlignment(Pos.CENTER_RIGHT);
						root.getChildren().addAll(tpType, tpSaisie, hbBouttons);
						btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								primaryStage.close();
							}
						});
						btnConfirmer.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								String strValeurChoisie = cbTypeDocument.getValue();
								DateTimeStringConverter format = new DateTimeStringConverter(Locale.CANADA,
										"dd-MM-yyyy");
								switch (strValeurChoisie) {
								case "Livre":
									int intNumLivre = 0;
									for (int i = 0; i < ListeDocument.arListeDoc.size(); i++) {
										if (ListeDocument.arListeDoc.get(i) instanceof Livre) {
											if (intNumLivre < Integer.parseInt(
													ListeDocument.arListeDoc.get(i).getStrNumero().substring(3))) {
												intNumLivre = Integer.parseInt(
														ListeDocument.arListeDoc.get(i).getStrNumero().substring(3));
											}
										}
									}
									Livre livre = new Livre("Liv" + (intNumLivre + 1), tfTitre.getText().trim(),
											Date.from(tfDatePub.getValue().atStartOfDay().atZone(ZoneId.systemDefault())
													.toInstant()),
											tfAuteur.getText().trim());
									ListeDocument.arListeDoc.add(livre);
									break;
								case "DVD":
									int intNumDvd = 0;
									for (int i = 0; i < ListeDocument.arListeDoc.size(); i++) {
										if (ListeDocument.arListeDoc.get(i) instanceof DVD) {
											if (intNumDvd < Integer.parseInt(
													ListeDocument.arListeDoc.get(i).getStrNumero().substring(3))) {
												intNumDvd = Integer.parseInt(
														ListeDocument.arListeDoc.get(i).getStrNumero().substring(3));
											}
										}
									}
									try {
										DVD dvd = new DVD("DVD" + (intNumDvd + 1), tfTitre.getText().trim(),
												Date.from(tfDatePub.getValue().atStartOfDay()
														.atZone(ZoneId.systemDefault()).toInstant()),
												Integer.parseInt(tfNoDvd.getText().trim()), tfAuteur.getText().trim());
										ListeDocument.arListeDoc.add(dvd);
									} catch (Exception e) {
										System.out.println("Veuillez entrer un chiffre comme nombre de DVD");
									}

									break;
								case "Périodique":
									int intNumPer = 0;
									for (int i = 0; i < ListeDocument.arListeDoc.size(); i++) {
										if (ListeDocument.arListeDoc.get(i) instanceof DVD) {
											if (intNumPer < Integer.parseInt(
													ListeDocument.arListeDoc.get(i).getStrNumero().substring(3))) {
												intNumPer = Integer.parseInt(
														ListeDocument.arListeDoc.get(i).getStrNumero().substring(3));
											}
										}
									}
									Periodique per = new Periodique("Per" + (intNumPer + 1), tfTitre.getText().trim(),
											Date.from(tfDatePub.getValue().atStartOfDay().atZone(ZoneId.systemDefault())
													.toInstant()),
											tfAuteur.getText().trim(), tfVolume.getText().trim(),
											tfPeriodique.getText().trim());
									ListeDocument.arListeDoc.add(per);
									break;
								}
								primaryStage.close();
							}
						});

						lblNoDVD.setVisible(false);
						lblVolume.setVisible(false);
						lbPeriodique.setVisible(false);
						tfNoDvd.setVisible(false);
						tfVolume.setVisible(false);
						tfPeriodique.setVisible(false);

					}
				};

				btnAjoutDoc.setOnMousePressed(eventAjoutDoc);

				Button btnSuprDoc = new Button("Supprimer un document");
				btnSuprDoc.setPrefSize(200, 40);
				VBox.setMargin(btnSuprDoc, new Insets(10));

				Button btnGererUsers = new Button("gerer les adherants");
				btnGererUsers.setPrefSize(200, 40);
				VBox.setMargin(btnGererUsers, new Insets(10));
				btnGererUsers.addEventHandler(MouseEvent.MOUSE_CLICKED, gestionAdherant);

				Button btnPret = new Button("Inscrire un pret");
				btnPret.setPrefSize(200, 40);
				VBox.setMargin(btnPret, new Insets(10));

				EventHandler<MouseEvent> eventPret = new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						// TODO Auto-generated method stub
						VBox root = new VBox();
						Scene scene = new Scene(root, 500, 500);
						HBox hb = new HBox();
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stage primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.setTitle("Ajouter un document");
						primaryStage.setResizable(false);
						primaryStage.show();
						Button btn1 = new Button("Emprunter");
						btn1.setPrefSize(300, 100);

						// ++++++++++++++++++++++++++++++ lst doc
						// +++++++++++++++++++++++++++++++++++++++
						final ObservableList<String> lstDoc = FXCollections.observableArrayList();

						for (int i = 0; i < listeComplete.arListeDoc.size(); i++)
							lstDoc.add(listeComplete.arListeDoc.get(i).getTitre());

						final ListView<String> listeViewDoc = new ListView(lstDoc);
						// ------------------------------------------------------------------------------

						// +++++++++++++++++++++++++++++ lst membre
						// +++++++++++++++++++++++++++++++++++++
						final ObservableList<String> lstMbr = FXCollections.observableArrayList();
						// No membre
						for (int i = 0; i < lstMembre.arListeMembre.size(); i++) {
							lstMbr.add(lstMembre.arListeMembre.get(i).toString());
							System.out.println(lstMembre.arListeMembre.get(i).toString()); // print à garder

						}

						final ListView<String> lvMembre = new ListView(lstMbr);

						// ------------------------------------------------------------------------------
						hb.getChildren().addAll(listeViewDoc, lvMembre);

						btn1.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								String titreChoisi = listeViewDoc.getSelectionModel().getSelectedItem();
								Membre membreChoisi = lstMembre.arListeMembre
										.get((lvMembre.getSelectionModel().getSelectedIndex()));

								for (int i = 0; i < listeComplete.arListeDoc.size() && listeComplete.arListeDoc.get(i)
										.getTitre().compareTo(titreChoisi) != 0; i++) {
									lstDoc.add(listeComplete.arListeDoc.get(i).getTitre());
									if (listeComplete.arListeDoc.get(i).getTitre().compareTo(titreChoisi) == 0) {
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Emprunt");
										alert.setHeaderText("+++++++++++");
										alert.setContentText(
												listeComplete.arListeDoc.get(i).Emprunt(membreChoisi.getStrNom(),
														membreChoisi.getStrPrenom(), membreChoisi.getStrNumTel()));
										alert.showAndWait();
										// Alert pr afficher le message de succes/echec de emprunt
									}
								}

							}
						});

						root.getChildren().addAll(hb, btn1);

					}
				};

				btnPret.setOnMouseClicked(eventPret);

				Button btnRetour = new Button("Inscrire un retour");
				btnRetour.setPrefSize(200, 40);
				VBox.setMargin(btnRetour, new Insets(10));

				btnSuprDoc.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						VBox root = new VBox();
						Scene scene = new Scene(root, 400, 200);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stage primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.setTitle("Supprimer un document");
						primaryStage.setResizable(false);
						primaryStage.show();
						ComboBox cb = new ComboBox();
						Button btnSupp = new Button("Supprimer");
						Button btnAnnuler = new Button("Annuler");
						HBox hBButton = new HBox();

						btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								primaryStage.close();
							}
						});
						btnSupp.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								ListeDocument.arListeDoc.remove(cb.getSelectionModel().getSelectedIndex());
								primaryStage.close();
							}
						});

						for (int i = 0; i < ListeDocument.arListeDoc.size(); i++) {
							cb.getItems().add(ListeDocument.arListeDoc.get(i));
						}
						cb.getSelectionModel().selectFirst();
						hBButton.getChildren().addAll(btnAnnuler, btnSupp);
						root.getChildren().addAll(cb, hBButton);
					}
				});

				btnRetour.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						VBox root = new VBox();
						Scene scene = new Scene(root, 500, 500);
						HBox hb = new HBox();
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stage primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.setTitle("Ajouter un document");
						primaryStage.setResizable(false);
						primaryStage.show();
						Button btn1 = new Button("Retourner");
						btn1.setPrefSize(300, 100);

						// ++++++++++++++++++++++++++++++ lst doc
						// +++++++++++++++++++++++++++++++++++++++
						final ObservableList<String> lstDoc = FXCollections.observableArrayList();

						for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
							if (listeComplete.arListeDoc.get(i).getNomEmp() != null)
								lstDoc.add(listeComplete.arListeDoc.get(i).getTitre());
						}

						final ListView<String> listeViewDoc = new ListView(lstDoc);

						btn1.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								String titreChoisi = listeViewDoc.getSelectionModel().getSelectedItem();

								for (int i = 0; i < listeComplete.arListeDoc.size() && titreChoisi
										.compareTo(listeComplete.arListeDoc.get(i).getTitre()) != 0; i++) {
									if (titreChoisi.compareTo(listeComplete.arListeDoc.get(i).getTitre()) == 0) {
										listeComplete.arListeDoc.get(i).setNomEmp(null);
										listeComplete.arListeDoc.get(i).setPrenomEmp(null);
										listeComplete.arListeDoc.get(i).setDateEmprunt(null);
										listeComplete.arListeDoc.get(i).setNumTel(null);
									}

								}

							}
						});

						root.getChildren().addAll(listeViewDoc, btn1);

					}
				});

				rightSide.getChildren().addAll(btnAjoutDoc, btnSuprDoc, btnGererUsers, btnPret, btnRetour);

			} else// adherant
			{
				Button btnConsulter = new Button("Consulter son compte");
				btnConsulter.setPrefSize(200, 40);
				VBox.setMargin(btnConsulter, new Insets(10));
				rightSide.getChildren().add(btnConsulter);

				EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						// TODO Auto-generated method stub
						int docEmp = 0;
						int dette = 0;

						for (int i = 0; i < listeComplete.arListeDoc.size(); i++) {
							if (Authentification.getStrNom() == listeComplete.arListeDoc.get(i).getNomEmp()
									&& Authentification.getStrPrenom() == listeComplete.arListeDoc.get(i).getPrenomEmp()) {
								double frais = 0;
								int gap;
								docEmp++;
								Date live = new Date();
								gap = live.compareTo(listeComplete.arListeDoc.get(i).getDateEmprunt());
								if (listeComplete.arListeDoc.get(i) instanceof Livre && gap > 14)
									frais = (gap - 14) * 0.5;
								if (listeComplete.arListeDoc.get(i) instanceof Periodique && gap > 3)
									frais = (gap - 3) * 0.5;
								if (listeComplete.arListeDoc.get(i) instanceof DVD && gap > 7)
									frais = (gap - 7) * 0.5;
								
							}
						}

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information");
						alert.setHeaderText("Etat du compte");
						alert.setContentText(
								"Vous avez : " + docEmp + " Documents emprunte\n" + " et " + dette + "$ de dette");

						alert.showAndWait();

					}
				};

				btnConsulter.setOnMouseClicked(click);
			}

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	EventHandler<MouseEvent> gestionAdherant = new EventHandler<MouseEvent>() {
		
		

		@Override
		public void handle(MouseEvent event) {
			
			try {
				
			ListeDocument listeComplete = ListeDocument.getInstance();
			ListeMembre lstMembre = ListeMembre.getInstance();
			
			// TODO Auto-generated method stub
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1000, 525);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("gestion des Adhérents");
			primaryStage.setResizable(false);

			Button btnAjouterAdherent = new Button("Ajouter");
			Button btnModifierAdherent = new Button("Modifier");
			Button btnFermer = new Button("Fermer");
			Button btnPayerSolde = new Button("Payer un solde");
			Button btnSupprimerAdherent = new Button("Supprimer");

			btnFermer.setAlignment(Pos.CENTER_LEFT);
			btnFermer.setMinWidth(100);
			btnPayerSolde.setMinWidth(100);
			btnModifierAdherent.setMinWidth(100);
			btnAjouterAdherent.setMinWidth(100);
			btnSupprimerAdherent.setMinWidth(100);
			btnPayerSolde.setAlignment(Pos.CENTER_RIGHT);
			btnModifierAdherent.setAlignment(Pos.CENTER_RIGHT);
			btnAjouterAdherent.setAlignment(Pos.CENTER_RIGHT);
			btnSupprimerAdherent.setAlignment(Pos.CENTER_RIGHT);

			HBox hbAction = new HBox();
			hbAction.getChildren().addAll(btnFermer, btnPayerSolde, btnAjouterAdherent, btnModifierAdherent,
					btnSupprimerAdherent);
			TitledPane tpAction = new TitledPane("Actions", hbAction);
			root.getChildren().addAll(hbAction);
			
			btnFermer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.close();
				}

			});
			
			btnSupprimerAdherent.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					VBox root = new VBox();
					Scene scene = new Scene(root, 300, 500);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage primaryStage = new Stage();
					primaryStage.setScene(scene);
					primaryStage.setTitle("supprimer un adherant");
					primaryStage.setResizable(false);
					primaryStage.show();
					
					final ObservableList<String> lstMbr = FXCollections.observableArrayList();
					
					for (int i = 0; i < lstMembre.arListeMembre.size(); i++) {
						if(!lstMembre.arListeMembre.get(i).isBooEstPrepose())
						lstMbr.add(lstMembre.arListeMembre.get(i).toString());
						System.out.println(lstMembre.arListeMembre.get(i).toString()); // print à garder

					}

					final ListView<String> lvMembre = new ListView(lstMbr);
					
					Button btnSup = new Button("Supprimer");
					
					root.getChildren().addAll(lvMembre,btnSup);
					
					btnSup.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							String membreChoisi = lvMembre.getSelectionModel().getSelectedItem();
							
							for(int i =0 ; i < lstMembre.arListeMembre.size() && lstMembre.arListeMembre.get(i).toString().compareTo(membreChoisi)!=0;i++)
							{
								if(lstMembre.arListeMembre.get(i).toString().compareTo(membreChoisi)== 0)
								{
									lstMembre.arListeMembre.remove(i);
								}
									
							}

							

						}
					});
				}

			});
			
			btnModifierAdherent.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					VBox root = new VBox();
					Scene scene = new Scene(root, 300, 500);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage primaryStage = new Stage();
					primaryStage.setScene(scene);
					primaryStage.setTitle("Modifier un adherant");
					primaryStage.setResizable(false);
					primaryStage.show();
					
					final ObservableList<String> lstMbr = FXCollections.observableArrayList();
					
					for (int i = 0; i < lstMembre.arListeMembre.size(); i++) {
						if(!lstMembre.arListeMembre.get(i).isBooEstPrepose())
						lstMbr.add(lstMembre.arListeMembre.get(i).toString());
						System.out.println(lstMembre.arListeMembre.get(i).toString()); // print à garder

					}

					final ListView<String> lvMembre = new ListView(lstMbr);
					
					
					Button btnMod = new Button("Appliquer les modifications");
					
					
					Label lblAdresse = new Label("addresse");
					TextField tAdresse = new TextField();
					HBox hAdresse = new HBox();
					hAdresse.getChildren().addAll(lblAdresse,tAdresse);
					
					Label lblTel = new Label("addresse");
					TextField tTel = new TextField();
					HBox hTel = new HBox();
					hAdresse.getChildren().addAll(lblTel,tTel);
					
					root.getChildren().addAll(lvMembre, btnMod, hAdresse, hTel);
					
					
					btnMod.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							String membreChoisi = lvMembre.getSelectionModel().getSelectedItem();
							
							for(int i =0 ; i < lstMembre.arListeMembre.size() && lstMembre.arListeMembre.get(i).toString().compareTo(membreChoisi)!=0;i++)
							{
								if(lstMembre.arListeMembre.get(i).toString().compareTo(membreChoisi)== 0)
								{
									lstMembre.arListeMembre.get(i).setAddress(tAdresse.getText());
									lstMembre.arListeMembre.get(i).setStrNumTel(tTel.getText());
								}
									
							}

							

						}
					});
				}

			});
			
			btnAjouterAdherent.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					VBox root = new VBox();
					Scene scene = new Scene(root, 300, 300);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage primaryStage = new Stage();
					primaryStage.setScene(scene);
					primaryStage.setTitle("Ajouter adherant");
					primaryStage.setResizable(false);
					primaryStage.show();
					
					HBox hnom = new HBox();
					HBox hprenom = new HBox();
					HBox hnumero = new HBox();
					
					Label lnom = new Label("nom");
					Label lprenom = new Label("prenom");
					Label lnum = new Label("Numero de telephone");
					
					TextField tnom = new TextField();
					TextField tprenom = new TextField();
					TextField tnum = new TextField();
					
					Button btn1 = new Button("ajouter");
					
					root.getChildren().addAll(hnom,hprenom,hnumero,btn1);
					hnom.getChildren().addAll(lnom,tnom);
					hprenom.getChildren().addAll(lprenom,tprenom);
					hnumero.getChildren().addAll(lnum,tnum);
					
					btn1.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
						
							if(tnom == null || tprenom == null)
							{
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Information");
								alert.setHeaderText("++++++++++");
								alert.setContentText("Le nom et le prenom sont obligatoire");
								alert.showAndWait();
							}
							else
							{
								Membre ajout = new Membre(tnom.getText(), tprenom.getText(), tnum.getText(), null, false, null);
								ListeMembre.arListeMembre.add(ajout);
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Information");
								alert.setHeaderText("++++++++++");
								alert.setContentText(tnom.getText() + " " + tprenom.getText() + " a ete ajouter");
								alert.showAndWait();
							}
							
							
						}

					});
					
					
				}

			});
			
			btnPayerSolde.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
						//this is not done
					
					VBox root = new VBox();
					Scene scene = new Scene(root, 500, 500);
					HBox hb = new HBox();
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage primaryStage = new Stage();
					primaryStage.setScene(scene);
					primaryStage.setTitle("Payer une solde");
					primaryStage.setResizable(false);
					primaryStage.show();
					
					Button btnPay = new Button("Payer les frais");
					int dette = 0;
					
					final ObservableList<String> lstMbr = FXCollections.observableArrayList();
					
					for (int i = 0; i < lstMembre.arListeMembre.size(); i++) {
						if(!lstMembre.arListeMembre.get(i).isBooEstPrepose() )
						lstMbr.add(lstMembre.arListeMembre.get(i).toString());

					}

					final ListView<String> lvMembre = new ListView(lstMbr);
					
					root.getChildren().addAll(lvMembre, btnPay);
					
					btnPay.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Boolean trouver = false;
							String Choisi = lvMembre.getSelectionModel().getSelectedItem();
							for (int i = 0; i < lstMembre.arListeMembre.size()&& !trouver; i++) {
								if(lstMembre.arListeMembre.get(i).toString().compareTo(Choisi)==0 )
								{
									trouver = true;
									lstMembre.arListeMembre.get(i).setIntDette(0);
								}

							}
						}

					});
				
					
					
				}

			});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
}
