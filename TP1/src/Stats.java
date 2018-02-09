/*
 * Cet enregistrement repr�sente les statistiques 
 * que nous maintenons dans le jeu  memSuite tp1 INF111 (voir �nonc� fourni).
 * 
 * Auteur: Pierre B�lisle
 * Version : copyright H2018
 * R�vision : Fr�d�rick Simard et Simon Pichette 
 */
public class Stats {
	
		
		// La longueur de la plus grande s�quence que l'utilisateur a eu.
		public int grandeSequence;
		
		// Depuis le dernier d�marrage (aucune sauvegarde).
		public int nbReussites;

		// Un essai == 1 clic.
		public int nbEssaieActuel;
		public double nbEssaiesTotal;
		
		/**
		 * @return the grandeSequence
		 */
		public int getGrandeSequence() {
			return grandeSequence;
		}
		/**
		 * @param grandeSequence the grandeSequence to set
		 */
		public void setGrandeSequence(int grandeSequence) {
			this.grandeSequence = grandeSequence;
		}
		/**
		 * @return the nbReussites
		 */
		public int getNbReussites() {
			return nbReussites;
		}
		/**
		 * @param nbReussites the nbReussites to set
		 */
		public void setNbReussites(int nbReussites) {
			this.nbReussites = nbReussites;
		}
		/**
		 * @return the nbEssaieActuel
		 */
		public int getNbEssaieActuel() {
			return nbEssaieActuel;
		}
		/**
		 * @param nbEssaieActuel the nbEssaieActuel to set
		 */
		public void setNbEssaieActuel(int nbEssaieActuel) {
			this.nbEssaieActuel = nbEssaieActuel;
		}
		/**
		 * @return the nbEssaiesTotal
		 */
		public double getNbEssaiesTotal() {
			return nbEssaiesTotal;
		}
		/**
		 * @param nbEssaiesTotal the nbEssaiesTotal to set
		 */
		public void setNbEssaiesTotal(double nbEssaiesTotal) {
			this.nbEssaiesTotal = nbEssaiesTotal;
		}
}
