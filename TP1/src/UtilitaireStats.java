import javax.swing.JOptionPane;
/**
 * cette classe fournit des fonctions d'utilité communes concernant les statistiques de jeu
 * @author 
 * @version feb 2018
 *
 */
public class UtilitaireStats {

	/**
	 * cette méthode réinitialise les statistiques de jeu pour le nouveau jeu
	 * @param stats être réinitialisé
	 */
	public static void ajusterStatsNouvellePartie(Stats stats) {
		stats.setGrandeSequence(0);
		stats.setNbEssaieActuel(0);
		stats.setNbReussites(0);
		stats.setNbEssaiesTotal(0d);
	}

	/**
	 * cette méthode affiche les statistiques du jeu pour le jeu en cours
	 * @param stats être affiché
	 */
	public static void montrerStats(Stats stats) {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre d'essai(s) actuel: "+stats.getNbEssaieActuel() +" carte(s)\n");
		sb.append("La plus grande sequence: "+stats.getGrandeSequence() +" carte(s)\n");
		sb.append("Nombre de reussites: "+stats.getNbReussites() +" partie(s) consecutive(s)\n");
		sb.append("Nombre d'essai(s) en moyenne par parties: "+stats.getNbEssaiesTotal());
		JOptionPane.showMessageDialog(null, sb.toString());
	}

}
