package vue2;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import presentation2.TypeDePrestation;
import presentation2.ExceptionLibelleIncorrect;
import presentation2.ExceptionTarifIncorrect;
import presentation2.Prestation;
import presentation2.Principale;
import java.util.ArrayList;

import javafx.beans.binding.*;

public class FenNouvellePrestation extends Stage {
	private ArrayList<Prestation> listePrestation = new ArrayList<Prestation>();
	// les composants du Scene Graph
	private GridPane			racine			= new GridPane();
	private HBox				zoneBoutons		= new HBox();
	private Label 				lblNom			= new Label("Libelle (*) : ");
	private Label 				lblPrixHT		= new Label("Tarif HT (*) : ");
	private Label 				lblType			= new Label("Type de prestation : ");
	private Label				lblErreur		= new Label("");
	private TextField			txtNom 			= new TextField();
	private TextField			txtPrixHT		= new TextField();
	private ComboBox<String>	cbTypes			= new ComboBox<String>();
	private Button 				bnOK 			= new Button("OK");
	private Button 				bnAnnuler 		= new Button("Annuler");
	private Alert				alert			= new Alert(AlertType.ERROR);
	
	
	// constructeur : initialisation de la fenetre
	public FenNouvellePrestation(){	
		bnOK.setOnAction(e-> {

			try {
		        double prix = Double.valueOf(txtPrixHT.getText());
	        	String prixText = String.format("%.2f", prix);
	        	prixText = prixText.replace(",", ".");
		        if (prix <= 999 && prix > 0) {
					float f1 = Float.parseFloat(prixText);
					try {
						Prestation p1 = new Prestation(new TypeDePrestation(cbTypes.getSelectionModel().getSelectedItem()), txtNom.getText(), f1);
						Principale.ajouterPrestation(p1);
		                System.out.println(f1);
		                this.close();
					} catch (ExceptionTarifIncorrect e1) {
						alert.setTitle("Erreur valeur prix");
		                alert.setContentText("Veuillez saisir un prix entre 0 et 999 euros.");
			            alert.showAndWait();
					} catch (ExceptionLibelleIncorrect e2) {
						alert.setTitle("Erreur nom");
		                alert.setContentText("Seul les caractères alphanumérique sont autorisés.");
			            alert.showAndWait();
					} catch (Exception e3) {
						
						lblErreur.setText("Erreur : INCONNU");
						lblErreur.isVisible();
					}
		       } else {
		    	   	alert.setTitle("Erreur valeur prix");
	                alert.setContentText("Veuillez saisir un prix entre 0 et 999 euros.");
		            alert.showAndWait();
		       }
		    } catch(NumberFormatException e9) {
		    	alert.setTitle("Erreur format prix");
	            alert.setContentText("Veuillez saisir uniquement des chiffres pour le tarif.");
	            alert.showAndWait();
		    }
			

		});
		
		bnAnnuler.setOnMouseClicked(e -> {
			this.close();
		});
		
		this.setTitle("Nouvel Prestation");
		this.sizeToScene();
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));	
	}
	
	// creation du contenu de la fenetre
	private Parent creerContenu() {

		BooleanBinding manque = Bindings.or(Bindings.or(txtNom.textProperty().isEmpty(), txtPrixHT.textProperty().isEmpty()),
				Bindings.equal(cbTypes.getSelectionModel().selectedIndexProperty(), -1)) ;

		bnOK.disableProperty().bind(Bindings.when(manque).then(true).otherwise(false));;
		
		bnOK.setPrefWidth(100);
		
		bnAnnuler.setPrefWidth(100);
		
		
		// construction du Scene Graph
		zoneBoutons.getChildren().addAll(bnOK, bnAnnuler);
		zoneBoutons.setSpacing(10);
		racine.addRow(0,  lblNom, txtNom);
		racine.addRow(1,  lblPrixHT, txtPrixHT);
		racine.addRow(2,  lblType, cbTypes);
		racine.add(zoneBoutons, 1, 10);
		racine.setHgap(10);
		racine.setVgap(15);
		racine.setPadding(new Insets(10));
		return racine;
	}
	
	public ArrayList<Prestation> getListePrestation() {
		return listePrestation;
	}

	public void setListePrestation(ArrayList<Prestation> listePrestation) {
		this.listePrestation = listePrestation;
	}

	public void init(ArrayList<String> lesTypes, ArrayList<Prestation> lesPrestations) {
		// initialise tous les champs du formulaire	
		txtNom.clear();
		txtPrixHT.clear();
		this.setListePrestation(lesPrestations);
		this.cbTypes.getItems().clear();
		for (int i=0; i<lesTypes.size() ; i++) {
			this.cbTypes.getItems().add(""+lesTypes.get(i));
		}
		
		
	}

	
}



