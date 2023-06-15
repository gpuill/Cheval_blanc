package vue2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation2.Prestation;
import presentation2.Principale;

public class FenListePrestation extends Stage {
	static private ObservableList<Prestation> lesPrestations = FXCollections.observableArrayList();
	// les composants de la fenetre
	private AnchorPane  		racine			= new AnchorPane();
	private TableView<Prestation> 	tablePrestations	= new TableView<Prestation>();
	private Button 				bnAjouter 		= new Button("Ajouter...");
	private Button 				bnModifier 		= new Button("Modifier...");
	private Button 				bnFermer 		= new Button("Fermer");
	private ComboBox<String>	cbFiltre		= new ComboBox<String>();

	
	// constructeur : initialisation de la fenetre
	public FenListePrestation(){
		this.setTitle("Gestion des prestations");
		this.setResizable(true);
		this.setMinWidth(520);
		this.setMinHeight(500);
		this.sizeToScene();
		this.setScene(new Scene(creerContenu()));	
	}
	
	// creation du Scene graph
	private Parent creerContenu() {
		
		TableColumn<Prestation,Integer> colonne1 = new TableColumn<Prestation,Integer>("Type De la prestation");
		colonne1.setCellValueFactory(new PropertyValueFactory<Prestation,Integer>("type"));	
		tablePrestations.getColumns().add(colonne1);
		TableColumn<Prestation, String> colonne2 = new TableColumn<Prestation,String>("Libelle");
		colonne2.setCellValueFactory(new PropertyValueFactory<Prestation, String>("Libelle"));
		tablePrestations.getColumns().add(colonne2);
		TableColumn<Prestation, String> colonne3 = new TableColumn<Prestation,String>("PrixHT");
		colonne3.setCellValueFactory(new PropertyValueFactory<Prestation, String>("prix_ht"));
		tablePrestations.getColumns().add(colonne3);
				
		tablePrestations.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		cbFiltre.setOnAction(e -> Principale.filtrer(cbFiltre.getValue()));
		
		BooleanBinding rien = Bindings.equal(tablePrestations.getSelectionModel().selectedIndexProperty(), -1);
		
		bnAjouter.setPrefWidth(100);
		
		bnModifier.setPrefWidth(100);
		
		cbFiltre.setPrefWidth(100);
		
		tablePrestations.setOnMouseClicked(e->{
			if (e.getClickCount()==2 && e.getButton()==MouseButton.PRIMARY) Principale.ouvrirDetailPrestation(tablePrestations.getSelectionModel().getSelectedItem());
		});
		
		

		bnFermer.setPrefWidth(100);
		bnFermer.setOnAction(e -> System.exit(0));
		
		bnAjouter.setOnAction(e -> Principale.ouvrirNouvelPrestation());
		bnModifier.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
		bnModifier.setOnAction(e -> Principale.ouvrirDetailPrestation(tablePrestations.getSelectionModel().getSelectedItem()));
		
		AnchorPane.setTopAnchor(tablePrestations, 10.0);
		AnchorPane.setLeftAnchor(tablePrestations, 10.0);
		AnchorPane.setRightAnchor(tablePrestations, 120.0);
		AnchorPane.setBottomAnchor(tablePrestations, 10.0);
		AnchorPane.setTopAnchor(bnAjouter, 30.0);
		AnchorPane.setRightAnchor(bnAjouter, 10.0);
		AnchorPane.setTopAnchor(bnModifier, 80.0);
		AnchorPane.setRightAnchor(bnModifier, 10.0);
		AnchorPane.setTopAnchor(cbFiltre, 130.0);
		AnchorPane.setRightAnchor(cbFiltre, 10.0);

		AnchorPane.setBottomAnchor(bnFermer, 10.0);
		AnchorPane.setRightAnchor(bnFermer, 10.0);
		racine.getChildren().addAll(tablePrestations, bnAjouter, bnModifier, bnFermer,cbFiltre);
		return racine;
	}
	
	
	public void init(ArrayList<Prestation> liste) {
		lesPrestations.clear();
		cbFiltre.getItems().clear();
		for (int i=0; i<liste.size() ; i++) {
			lesPrestations.add(liste.get(i));
		}
		tablePrestations.setItems(lesPrestations);
		ArrayList<String> filtre=new ArrayList<String>();
		filtre.add("Pressing");
		filtre.add("Consommation au bar");
		filtre.add("Consommation en chambre");
		this.cbFiltre.getItems().add(0, "Filtrer");
		this.cbFiltre.setValue(cbFiltre.getItems().get(0));
		for(int i=0;i<filtre.size();i++) {
			cbFiltre.getItems().add(""+filtre.get(i));
		}
	}
	
	public void init(ArrayList<Prestation> liste,String fil) {
		lesPrestations.clear();
		for (int i=0; i<liste.size() ; i++) {
			lesPrestations.add(liste.get(i));
		}
		tablePrestations.setItems(lesPrestations);
	}
	
	public void ajouterPrestation(Prestation e) {
		lesPrestations.add(e);
	}
	
	public void modifierPrestation(Prestation e, float prixModifier) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesPrestations.size()) {
			if ((lesPrestations.get(i).equals(e))){
				System.out.println(lesPrestations.get(i).getPrix_ht());
				lesPrestations.get(i).setPrix_ht(prixModifier);
				System.out.println(lesPrestations.get(i).getPrix_ht());
				trouve = true;
			}
			i++;
		}
	}
	
	
	public void supprimerPrestation(Prestation e) {
		lesPrestations.remove(e);
	}
}
