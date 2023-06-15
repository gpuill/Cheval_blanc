package presentation2;



public class ExceptionTarifIncorrect extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 622509455090371691L;
	public ExceptionTarifIncorrect() {
		System.out.println("Le tarif est incorrect. Il doit être entre 0 et 999 euro.");
	}
	public String toString() {
		return "ExceptionTarifIncorrect";
	}
}
