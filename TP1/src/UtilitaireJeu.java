import java.util.Arrays;

import javax.swing.JOptionPane;


/**
 * cette classe fournit des fonctions d'utilité communes concernant les opérations de jeu
 * 
 * @author
 * @version feb 2018
 *
 */
public class UtilitaireJeu {

	/**
	 * cette méthode initialise les statistiques du jeu et réinitialise 
	 * les affichages du jeu en mélangeant les cartes
	 * @param jeuNeuf nouveau jeu
	 * @param cartesAffichees cartes de jeu en jeu
	 * @param gui interface utilisateur
	 * @param etatJeu statistiques de jeu
	 */
	public static void initialiserJeu(Carte[] jeuNeuf, Carte[] cartesAffichees,
			GrilleGui gui, EtatJeu etatJeu) {
		etatJeu.setLongueurSequence(0);
		etatJeu.setNbIndices(Constantes.NB_INDICE_DEPART);

		// Méthode 1: méthodes de brassage de cartes
		UtilitaireTableauCartes.brassageCartes(cartesAffichees);

		// Méthode 2: Méthode par paquets
		// UtilitaireTableauCartes.paquetsMethode(cartesAffichees);

		// Méthode 3: Méthode par position aléatoire
		// UtilitaireTableauCartes.positionAleatoireMethode(cartesAffichees);

		// Afficher les cartes du jeu neuf.
		UtilitaireGrilleGUI.affichageDesCartes(jeuNeuf, gui);

		JOptionPane.showMessageDialog(null,
				"Vous avez quelques secondes pour memoriser les cartes");

		// Copier le jeu de cartes à afficher dans un tableau de cartes
		// temporaire et afficher ce jeu
		Carte[] copieDuJeu = UtilitaireTableauCartes
				.copieDuJeu(cartesAffichees);
		UtilitaireTableauCartes.basculeVisibilité(copieDuJeu, true);

		// Modifier la visibilité des cartes du jeu de cartes à afficher
		UtilitaireTableauCartes.basculeVisibilité(cartesAffichees, false);
		UtilitaireGrilleGUI.affichageDesCartes(copieDuJeu, gui);

		// Faire une pause pour laisser le temps de voir
		gui.pause(Constantes.TEMPS_VISIONNEMENT);

		UtilitaireGrilleGUI.affichageDesCartes(cartesAffichees, gui);
	}

	/**
	 * cette méthode gère l'interaction de l'utilisateur, valide le choix de l'utilisateur et assure le suivi de la génération de la séquence
	 * @param cartes de jeu en jeu
	 * @param gui interface utilisateur
	 * @param stats statistiques des joueurs
	 * @param etatJeu statistiques de jeu
	 */
	public static void effectuerUnTour(Carte[] cartes, GrilleGui gui,
			Stats stats, EtatJeu etatJeu) {
		Coordonnee coordonnee = gui.getPosition();
		int position = coordonnee.getLigne() * Constantes.CARTES_PAR_SORTES
				+ coordonnee.getColonne();
		Carte carte = cartes[position];
		// Afficher un message d'erreur si la carte a déjà été choisie
		if (!carte.isVisible()) {
			gererClicValide(cartes, gui, etatJeu, stats, position);
		} else {
			JOptionPane.showMessageDialog(null,
					"Clic invalide, la carte a déjà été révélée!");
		}
		stats.setNbEssaieActuel(stats.getNbEssaieActuel() + 1);
	}

	/**
	 * 
	 * cette méthode valide le clic et gère la séquence
	 * @param cartes de jeu en jeu
	 * @param gui interface utilisateur
	 * @param stats statistiques des joueurs
	 * @param etatJeu statistiques de jeu
	 * @param position position de l'utilisateur cliquez
	 */
	private static void gererClicValide(Carte[] cartes, GrilleGui gui,
			EtatJeu etatJeu, Stats stats, int position) {
		int longueurSequence = etatJeu.getLongueurSequence();
		cartes[position].setVisible(true);
		// vérifier si la carte sélectionnée est la première en séquence,
		// masquer toutes les cartes en séquence si sa seconde
		if (longueurSequence == 0) {
			UtilitaireGrilleGUI.affichageDesCartes(cartes, gui);
			etatJeu.setTabSequence(new int[] { position });
			etatJeu.setLongueurSequence(longueurSequence + 1);
		} else {
			gererLesSequences(cartes, etatJeu, position, longueurSequence);
			if (!etatJeu.isIlYaSequence()) {
				if (etatJeu.getLongueurSequence() > stats.getGrandeSequence())
					stats.setGrandeSequence(etatJeu.getLongueurSequence());
				JOptionPane.showMessageDialog(null, "bris de séquence");
				etatJeu.setLongueurSequence(0);
			}
			UtilitaireGrilleGUI.affichageDesCartes(cartes, gui);
		}
	}

	/**
	 * cette méthode vérifie si la séquence est valide ou non
	 * @param cartes de jeu en jeu
	 * @param etatJeu statistiques de jeu
	 * @param position position de l'utilisateur cliquez
	 * @param longueurSequence longueur de la séquence
	 */
	private static void gererLesSequences(Carte[] cartes, EtatJeu etatJeu,
			int position, int longueurSequence) {
		int[] tabSequence = etatJeu.getTabSequence();
		// vérifier si les cartes sélectionnées sont en ordre
		if (UtilitaireTableauCartes.vérifierLesCartesOrdre(
				cartes[tabSequence[tabSequence.length - 1]],
				cartes[position])) {
			tabSequence = Arrays.copyOf(tabSequence, tabSequence.length + 1);
			tabSequence[tabSequence.length - 1] = position;
			etatJeu.setIlYaSequence(true);
			etatJeu.setLongueurSequence(longueurSequence + 1);
			etatJeu.setTabSequence(tabSequence);
		} else {
			etatJeu.setIlYaSequence(false);
			if (longueurSequence == 1) {
				cartes[tabSequence[0]].setVisible(false);
			}
			cartes[position].setVisible(false);
		}
	}

	/**
	 * Afficher / masquer toutes les cartes
	 * @param cartes de jeu en jeu
	 * @param gui interface utilisateur
	 * @param etatJeu statistiques de jeu
	 */
	public static void montrerLesCartes(Carte[] cartes, GrilleGui gui,
			EtatJeu etatJeu) {
		UtilitaireTableauCartes.basculeVisibilité(cartes, true);
		UtilitaireGrilleGUI.affichageDesCartes(cartes, gui);
		UtilitaireTableauCartes.basculeVisibilité(cartes, false);
		gui.pause(Constantes.TEMPS_VISIONNEMENT);
		UtilitaireGrilleGUI.affichageDesCartes(cartes, gui);
	}

	/**
	 * cette méthode gère les astuces pendant le jeu
	 * @param cartes  de jeu en jeu
	 * @param gui interface utilisateur
	 * @param etatJeu statistiques de jeu
	 */
	public static void montrerIndices(Carte[] cartes, GrilleGui gui,
			EtatJeu etatJeu) {
		int nbIndices = etatJeu.getNbIndices();
		// vérifier si l'utilisateur a déjà utilisé tous les indices
		if(nbIndices == 0) {
			JOptionPane.showMessageDialog(null, "il n'y a plus d'indices");
		}else {
			if (etatJeu.isIlYaSequence()) {
				int[] tabSequence = etatJeu.getTabSequence();
				Carte carte = cartes[tabSequence[tabSequence.length - 1]];
				if (carte.getNumero() == 13) {
					JOptionPane.showMessageDialog(null,
							"Désolé, il n'y a pas de prochaine carte dans la séquence");
				}else {
					// montre la carte suivante dans l'ordre comme indice
					afficherMasquerIndice(cartes, gui, trouverPositionCarteSuivante(cartes, carte));
					--nbIndices;
				}
			}else{
				// montrer la plus petite carte dans l'ordre comme indice
				afficherMasquerIndice(cartes, gui, trouverPositionPetiteCarte(cartes));
				--nbIndices;
			}
			etatJeu.setNbIndices(nbIndices);
		}
	}

	/**
	 * trouver la plus petite carte comme indice quelle que soit sa couleur
	 * @param cartes de jeu en jeu
	 * @return position de la carte d'indice
	 */
	private static int trouverPositionPetiteCarte(Carte[] cartes) {
		int petiteCarte = cartes[0].getNumero();
		int suivante = 0;
		for(int i=1; i<cartes.length; i++) {
			if(cartes[i].getNumero() < petiteCarte && !cartes[i].isVisible()) {
				petiteCarte = cartes[i].getNumero();
				suivante = i;
			}
		}
		return suivante;
	}

	/**
	 * trouver la carte suivante comme indice basé sur la dernière carte dans la séquence actuelle
	 * @param cartes de jeu en jeu
	 * @param carte dernière carte en séquence
	 * @return position de la carte d'indice
	 */
	private static int trouverPositionCarteSuivante(Carte[] cartes, Carte carte) {
		for(int i=0; i<cartes.length; i++) {
			if(carte.getCouleur() == cartes[i].getCouleur() && cartes[i].getNumero() == carte.getNumero() + 1) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * cette méthode afficher, puis masquer la carte d'indice à l'utilisateur
	 * @param cartes de jeu en jeu
	 * @param gui interface utilisateur
	 * @param suivante position de la carte de conseil à afficher
	 */
	private static void afficherMasquerIndice(Carte[] cartes, GrilleGui gui, int suivante) {
		cartes[suivante].setVisible(true);
		UtilitaireGrilleGUI.affichageDesCartes(cartes, gui);
		cartes[suivante].setVisible(false);
		gui.pause(Constantes.TEMPS_INDICE);
		UtilitaireGrilleGUI.affichageDesCartes(cartes, gui);
	}

}
