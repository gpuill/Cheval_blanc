package presentation2;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele2.*;
import vue2.*;

public class Principale extends Application{
	static private FenNouvellePrestation fNouvEmp = new FenNouvellePrestation();
	static private FenDetailPrestation fDetailEmp = new FenDetailPrestation();
	static private FenListePrestation fListeEmp = new FenListePrestation();
	
	public void start(Stage f) throws Exception {
		AccesDonnees.connexion();
		fNouvEmp.initModality(Modality.APPLICATION_MODAL);
		fDetailEmp.initModality(Modality.APPLICATION_MODAL);
		fListeEmp.init(AccesDonnees.getLesPrestations());
		fListeEmp.show();
	} 
	
	static public void main(String[] args) { 
		Application.launch(args); 
	}
	
	static public void filtrer(String filtre) { //initialise la liste afficher avec un filtre
		fListeEmp.init(AccesDonnees.getFiltre(filtre),filtre);
	}
	
	// gestion des fenêtres
	static public void ouvrirNouvelPrestation() {
		fNouvEmp.init(AccesDonnees.getLesTypes(), AccesDonnees.getLesPrestations());
		fNouvEmp.show();
	}
	
	
	static public void ouvrirDetailPrestation(Prestation e) {
		fDetailEmp.init(e);
		fDetailEmp.show();
	}
	
	// gestion des données : les modifications
	static public void ajouterPrestation(Prestation e) {
		// actualiser l'ObservableList dans la fenetre liste des Prestations
		fListeEmp.ajouterPrestation(e);
		// enregistrer l'ajout
		AccesDonnees.ajouterPrestation(e);	
		fListeEmp.init(AccesDonnees.getLesPrestations());

	}
	static public void modifierPrestation(Prestation e, float prixModifier) {
		// actualiser l'ObservableList dans la fenetre liste des Prestations
		fListeEmp.modifierPrestation(e, prixModifier);
		// enregistrer la modif
		AccesDonnees.modifierPrestation(e, prixModifier);
		fListeEmp.init(AccesDonnees.getLesPrestations());
	}
	static public void supprimerPrestation(Prestation e) {
		// actualiser l'ObservableList dans la fenetre liste des Prestations
		fListeEmp.supprimerPrestation(e);
		// enregistrer la suppression
		AccesDonnees.supprimerPrestation(e);
	}


}
