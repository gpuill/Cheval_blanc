package presentation2;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeDePrestation {
	private StringProperty libelle;

	public TypeDePrestation(String libelle) {
		this.libelle = new SimpleStringProperty(libelle);
	}

	public String getLibelle() {
		return libelle.get();
	}
	
	public StringProperty libelleProperty() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = new SimpleStringProperty(libelle);
	}
	
	public String toString() {
		return libelle.get();
	}
	
	public boolean equals(Object eq) {
		try { 
			if (this.getLibelle().compareTo(((TypeDePrestation) eq).getLibelle())==0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}
	
	public int hashCode() {
		return this.libelle.hashCode();
	}
	
}
