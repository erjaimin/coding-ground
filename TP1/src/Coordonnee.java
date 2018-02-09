/*
 * Cet enregistrement repr�sente les coordonn�es 
 * possibles dans diff�rents jeux de grille.
 * 
  * Auteur: Pierre B�lisle
 * Version : copyright H2018
 * R�vision : Fr�d�rick Simard et Simon Pichette 
 */
public class Coordonnee {

	/*
	 * Les items choisis par l'utilisateur.
	 */
	private int ligne;
	private int colonne;
	
	/**
	 * @return the ligne
	 */
	public int getLigne() {
		return ligne;
	}
	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	/**
	 * @return the colonne
	 */
	public int getColonne() {
		return colonne;
	}
	/**
	 * @param colonne the colonne to set
	 */
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
}
