/*
 * Durant la partie, les diff�rents sous-programmes doivent maintenir ces 
 * attributs qui donnent l'�tat du jeu.
 * 
 * Auteur: Pierre B�lisle
 * Version : copyright H2018
 * R�vision : Fr�d�rick Simard et Simon Pichette 
 */
public class EtatJeu{
	
     	// Mis � true si l'utilisateur clique sur le bouton pour quitter.
		public boolean partieTerminee;
		
		// Permet de retenir entre les sous-programme s'il y a une s�quence de 
		// cartes s�lectionn�es par l'utilisateur (plus de 2 cartes).
		public boolean ilYaSequence;

		// On retient toutes les positions de cartes qui font partie d'une 
		// s�quence.
		public int[] tabSequence;
		public int longueurSequence;
		
		// Le pointage depuis le d�marrage du jeu.
		public int pointage;
		
		
		// Le nombre de fois que le joueur peut demander de voir la prochaine 
		// carte de la s�quence.
		public int nbIndices =  Constantes.NB_INDICE_DEPART;


		/**
		 * @return the partieTerminee
		 */
		public boolean isPartieTerminee() {
			return partieTerminee;
		}


		/**
		 * @param partieTerminee the partieTerminee to set
		 */
		public void setPartieTerminee(boolean partieTerminee) {
			this.partieTerminee = partieTerminee;
		}


		/**
		 * @return the ilYaSequence
		 */
		public boolean isIlYaSequence() {
			return ilYaSequence;
		}


		/**
		 * @param ilYaSequence the ilYaSequence to set
		 */
		public void setIlYaSequence(boolean ilYaSequence) {
			this.ilYaSequence = ilYaSequence;
		}


		/**
		 * @return the tabSequence
		 */
		public int[] getTabSequence() {
			return tabSequence;
		}


		/**
		 * @param tabSequence the tabSequence to set
		 */
		public void setTabSequence(int[] tabSequence) {
			this.tabSequence = tabSequence;
		}


		/**
		 * @return the longueurSequence
		 */
		public int getLongueurSequence() {
			return longueurSequence;
		}


		/**
		 * @param longueurSequence the longueurSequence to set
		 */
		public void setLongueurSequence(int longueurSequence) {
			this.longueurSequence = longueurSequence;
		}


		/**
		 * @return the pointage
		 */
		public int getPointage() {
			return pointage;
		}


		/**
		 * @param pointage the pointage to set
		 */
		public void setPointage(int pointage) {
			this.pointage = pointage;
		}


		/**
		 * @return the nbIndices
		 */
		public int getNbIndices() {
			return nbIndices;
		}


		/**
		 * @param nbIndices the nbIndices to set
		 */
		public void setNbIndices(int nbIndices) {
			this.nbIndices = nbIndices;
		}
		

}
	
