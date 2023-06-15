package modele2;

import java.util.ArrayList;
import presentation2.*;

public class AccesDonnees {
	static private ArrayList<TypeDePrestation> 	lesTypesDePrestations = new ArrayList<TypeDePrestation>();
	static private ArrayList<Prestation> lesPrestations = new ArrayList<Prestation>();
	static private ArrayList<Prestation> pressing = new ArrayList<Prestation>();
	static private ArrayList<Prestation> bar=new ArrayList<Prestation>();
	static private ArrayList<Prestation> chambre = new ArrayList<Prestation>();
	
	static public void connexion() throws Exception { 
		
		lesTypesDePrestations.add(new TypeDePrestation("Pressing"));
		lesTypesDePrestations.add(new TypeDePrestation("Consommation au bar"));
		lesTypesDePrestations.add(new TypeDePrestation("Consommation en chambre"));
		
		try {
			lesPrestations.add(new Prestation(lesTypesDePrestations.get(0), "Chemise", (float)20.20));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			lesPrestations.add(new Prestation(lesTypesDePrestations.get(1), "Apero au bar", (float)911.0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			lesPrestations.add(new Prestation(lesTypesDePrestations.get(2), "Repas Vegan", (float)420.0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			lesPrestations.add(new Prestation(lesTypesDePrestations.get(2), "Entre-cote", (float)63.12));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			lesPrestations.add(new Prestation(lesTypesDePrestations.get(1), "Champagne", (float)99.12));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<lesPrestations.size();i++) {
			if(lesPrestations.get(i).getType().getLibelle().equals("Pressing")) {
				pressing.add(lesPrestations.get(i));
			}
		}
		for(int i=0;i<lesPrestations.size();i++) {
			if(lesPrestations.get(i).getType().getLibelle().equals("Consommation en chambre")) {
				chambre.add(lesPrestations.get(i));
			}
		}
		for(int i=0;i<lesPrestations.size();i++) {
			if(lesPrestations.get(i).getType().getLibelle().equals("Consommation au bar")) {
				bar.add(lesPrestations.get(i));
			}
		}
		
	}

	// méthodes de consultation : elles fournissent des listes de données
	static public ArrayList<String> getLesTypes() {
		// fournit la liste des numéros de départements
		ArrayList<String> lesTypesPossibles = new ArrayList<String>();
		for(int i=0 ; i<lesTypesDePrestations.size() ; i++) {
			lesTypesPossibles.add(lesTypesDePrestations.get(i).getLibelle());
		}
		return lesTypesPossibles;
	}
	
	static public ArrayList<Prestation> getLesPrestations() {
		return lesPrestations;
	}

	//FILTRES
	static public ArrayList<Prestation> getFiltre(String filtre){
		if (filtre != null) {
			if(filtre.equals("Pressing")) {          //Cette méthode permet de donner les listes de prestations à afficher lors du filtre
				return pressing;
			}
			else if(filtre.equals("Consommation au bar")) {
				return bar;
			}
			else if(filtre.equals("Consommation en chambre")) {
				return chambre;
			}
			else {
				return lesPrestations;
			}
		}else {
			return lesPrestations;
		}
		
	}
	
	// méthodes de mise à jour
	static public void ajouterPrestation(Prestation e) {
		lesPrestations.add(e);
		if(e.getType().getLibelle().equals("Pressing")) {
			pressing.add(e);
		}
		else if(e.getType().getLibelle().equals("Consommation en chambre")) {
			chambre.add(e);
		}
		else if(e.getType().getLibelle().equals("Consommation au bar")) {
			bar.add(e);
		}
	}
	
	static public void supprimerPrestation(Prestation e) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesPrestations.size()) {
			if ((lesPrestations.get(i).getLibelle()==e.getLibelle())&&(lesPrestations.get(i).getType()==e.getType())){
				lesPrestations.remove(i);
				trouve = true;
			}
			i++;
		}
	}
	static public void modifierPrestation(Prestation e, float prixModifier) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesPrestations.size()) {
			if ((lesPrestations.get(i).getLibelle()==e.getLibelle())&&(lesPrestations.get(i).getType()==e.getType())){
				lesPrestations.get(i).setPrix_ht(prixModifier);
				trouve = true;
			}
			i++;
		}
	}
}
