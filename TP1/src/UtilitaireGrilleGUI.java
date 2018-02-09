/**
 * cette classe fournit des fonctions utilitaires communes concernant le 
 * fonctionnement de l'interface graphique
 * 
 * @author 
 * @version feb 2018
 *
 */
public class UtilitaireGrilleGUI {

	/**
	 * cette méthode affiche les cartes données à l'interface utilisateur; 
	 * cache également les cartes si nécessaire
	 * @param cartesAffichees être affiché
	 * @param gui interface utilisateur
	 */
	public static void affichageDesCartes(Carte[] cartesAffichees,
			GrilleGui gui) {
		if (cartesAffichees != null && gui != null) {
			int compteur = 0;
			for(int i = 0; i < gui.getNbLignes();i++) {
				for(int j = 0; j <gui.getNbColonnes();j++){
					Carte carte = cartesAffichees[compteur];
					if (carte.isVisible()) {
						gui.setImage(i, j, carte.getImage());
					}else {
						gui.setImage(i, j, null);
					}
					compteur++;
				}	
			}
		}
	}
}
