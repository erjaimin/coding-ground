import javax.swing.Icon;

/*
 * Repr�sente une carte � jouer.
 * 
 * Sp�cial : Contient l'image correspondante � afficher.
 * 
 * Auteur: Pierre B�lisle
 * Version : copyright H2018
 * R�vision : Fr�d�rick Simard et Simon Pichette 
 */
public class Carte {
	
	public int numero;  // Le num�ro de carte de Constantes.AS � Constantes.ROI.
	
	public Constantes.Sorte couleur;  // COEUR � PIQUE.
	
	public Icon image; // L'image de la carte � montrer.
	
	public boolean visible = true; // Vrai si la carte est tourn�e vers le haut.
	
	public Carte() {
	}
	
	/**
	 * 
	 * @param numero
	 * @param couleur
	 * @param image
	 * @param visible
	 */
	public Carte(int numero, Constantes.Sorte couleur, Icon image, boolean visible) {
		super();
		this.numero = numero;
		this.couleur = couleur;
		this.image = image;
		this.visible = visible;
	}
	
	public Carte(Carte carte) {
		this(carte.getNumero(), carte.getCouleur(), carte.getImage(), carte.isVisible());
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the couleur
	 */
	public Constantes.Sorte getCouleur() {
		return couleur;
	}

	/**
	 * @param couleur the couleur to set
	 */
	public void setCouleur(Constantes.Sorte couleur) {
		this.couleur = couleur;
	}

	/**
	 * @return the image
	 */
	public Icon getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Icon image) {
		this.image = image;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// DEBUG
	public String toString() {
		return numero + image.toString() + couleur.toString();
	}
}
