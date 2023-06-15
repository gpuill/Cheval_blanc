package presentation2;



public class Prestation {
	private TypeDePrestation type;
	private String libelle;
	private Float prix_ht; //gérer dans l'ihm les 2 chiffres après la virgule
	
	public Prestation(TypeDePrestation type, String libelle, float prix_ht) throws Exception{
		this.type = type;
		if(libelle != null && libelle.matches("^[a-zA-Z0-9- ]*$")) {
			this.libelle = libelle;
		}
		else {
			ExceptionLibelleIncorrect e = new ExceptionLibelleIncorrect();
			throw e;
		}
		if(prix_ht>0.00 || prix_ht<999.00) {
			this.prix_ht= prix_ht;
		}
		else {
			ExceptionTarifIncorrect e = new ExceptionTarifIncorrect();
			throw e;
		}

	}
	
	// Toute les méthodes concernant le prix Hors taxe.
	
	public TypeDePrestation getType() {
		return type;
	}

	public String getLibelle() {
		return libelle;
	}

	public Float getPrix_ht() {
		return prix_ht;
	}

	public void setType(TypeDePrestation type) {
		this.type = type;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setPrix_ht(Float prix_ht) {
		this.prix_ht = prix_ht;
	}
	
	//ToString
	public String toString() {
		return this.type.toString()+" "+this.libelle.toString()+" "+this.prix_ht;
	}
	


	//Equals
	public boolean equals(Object eq) {
		try {
			if (this.getLibelle().compareTo(((Prestation) eq).getLibelle())==0) {
				return this.getType().equals(((Prestation) eq).getType());
			}else {
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	
	//Hashcode
	public int hashCode() {
		return this.libelle.hashCode()+this.getType().getLibelle().hashCode();
	}
	
	public static void main(String[] args) {
		
	}
	
}
