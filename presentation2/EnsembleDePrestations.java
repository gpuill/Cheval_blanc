																	package presentation2;



import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EnsembleDePrestations {

	private ObservableList<Prestation> ensemblePrestations;
	
	public EnsembleDePrestations() {
		this.ensemblePrestations = FXCollections.observableArrayList();;
	}

	public ObservableList<Prestation> ensemblePrestationsProperty() {
		return this.ensemblePrestations;
	}
	
	public Object[] getEnsemblePrestations() {
		return this.ensemblePrestations.toArray();
	}

	public String toString() {
		return "EnsembleDePrestations [ensemblePrestations=" + ensemblePrestations + "]";
	}

	public int hashCode() {
		return Objects.hash(ensemblePrestations);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnsembleDePrestations other = (EnsembleDePrestations) obj;
		return Objects.equals(ensemblePrestations, other.ensemblePrestations);
	}

	public void ajouterPrestation(Prestation prestation) {
		try {
			this.ensemblePrestations.add(prestation);
		} catch (Exception e) {
			System.err.println("La Prestations est déjà présente dans la liste, elle n'a donc pas été aajouté.\n");
		}
	}
	
	public void modifierPrestation(Prestation prestation) {
		
	}
	
}
