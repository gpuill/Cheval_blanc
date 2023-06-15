package presentation2;



public class ExceptionLibelleIncorrect extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8431609111904451045L;
	public ExceptionLibelleIncorrect() {
		System.out.println("Le nom contient des caractère incorrect.");
	}
	public String toString() {
		return "ExceptionLibelleIncorrect";
	}
}
