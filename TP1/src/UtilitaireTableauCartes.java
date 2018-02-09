import java.util.Arrays;

/**
 * 
 * cette classe fournit des fonctions utilitaires communes pour maintenir la table de jeu
 * 
 * @author
 * @version feb 2018
 *
 */
public class UtilitaireTableauCartes {

	/**
	 * cette méthode crée une copie de la table des cartes donnée
	 * @param jeuNeuf nouveau jeu
	 * @return copie de cartes données
	 */
	public static Carte[] copieDuJeu(Carte[] jeuNeuf) {
		Carte[] cartesAffichees = new Carte[jeuNeuf.length];
		for(int i=0; i<jeuNeuf.length; i++) {
			cartesAffichees[i] = new Carte(jeuNeuf[i]);
		}
		return cartesAffichees;
	}

	
	/**
	 * cette méthode vérifie si toutes les cartes sont ouvertes par l'utilisateur ou non
	 * @param cartesAffichees à vérifier
	 * @return true si toutes les cartes sont tournées, sinon retourne false
	 */
	public static boolean toutesLesCartesSontTournee(Carte[] cartesAffichees) {
		for (Carte carte : cartesAffichees) {
			if (!carte.isVisible()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * cette méthode vérifie l'ordre de deux cartes d'entrée
	 * @param carte1 à vérifier
	 * @param carte2 à vérifier
	 * @return true si les deux cartes sont en ordre, sinon retourne false
	 */
	public static boolean vérifierLesCartesOrdre(Carte carte1, Carte carte2) {
		return (carte1.getCouleur() == carte2.getCouleur()
				&& carte2.getNumero() - carte1.getNumero() == 1);
	}

	/**
	 * cette méthode active la visibilité des cartes de jeu
	 * @param cartesTableau à mettre à jour
	 * @param estVisible visibilité attendue
	 */
	public static void basculeVisibilité(Carte[] cartesTableau,
			boolean estVisible) {
		for (Carte carte : cartesTableau) {
			carte.setVisible(estVisible);
		}
	}

	/**
	 * Methode 1::
	 * cette méthode met en œuvre une méthode de brassage pour mélanger des cartes de jeu. Il y a 3 étapes impliquées dans ce processus:
     * 1. mélanger les cartes
     * 2. séparer les cartes
     * 3. Fusionner les cartes
     * Voir le document d'assignation pour plus de détails
	 * @param cartesTableau être mélangé
	 */
	public static void brassageCartes(Carte[] cartesTableau) {
		int alea = UtilitaireFonction.alea(Constantes.REPETER_BRASSAGE_MIN,
				Constantes.REPETER_BRASSAGE_MAX);
		for (int i = 0; i < alea; i++) {
			melangerCartes(cartesTableau);
			separezEtFusionnezCartes(cartesTableau);
		}
	}

	/**
	 * cette méthode mélange les cartes en prenant quelques cartes de la fin et les met au début
	 * @param cartesTableau être mélangé
	 */
	private static void melangerCartes(Carte[] cartesTableau) {
		int alea = UtilitaireFonction.alea(Constantes.MELANGER_BRASSAGE_MIN,
				Constantes.MELANGER_BRASSAGE_MAX);
		for (int i = 0; i < alea; i++) {
			int noCartesDeplacer = UtilitaireFonction.alea(
					Constantes.DEPLACER_BRASSAGE_MIN,
					Constantes.DEPLACER_BRASSAGE_MAX);
			deplacerCartes(cartesTableau, noCartesDeplacer);
		}
	}

	/**
	 * cette méthode déplace le nombre donné de cartes de bout en bout du paquet
	 * @param cartesTableau être déplacé
	 * @param noCartesDeplacer nombre de cartes à déplacer
	 */
	private static void deplacerCartes(Carte[] cartesTableau,
			int noCartesDeplacer) {
		for (int i = 0; i < noCartesDeplacer; i++) {
			for (int j = cartesTableau.length - 1; j > 0; j--) {
				Carte carte = cartesTableau[j];
				cartesTableau[j] = cartesTableau[j - 1];
				cartesTableau[j - 1] = carte;
			}
		}
	}

	/**
	 * cette méthode sépare le jeu de cartes en deux jeux au hasard
	 * @param cartesTableau être séparé
	 */
	private static void separezEtFusionnezCartes(Carte[] cartesTableau) {
		int noCartesPaquet1 = UtilitaireFonction.alea(
				Constantes.SEPARER_BRASSAGE_MIN,
				Constantes.SEPARER_BRASSAGE_MAX);
		int noCartesPaquet2 = Constantes.NB_CARTES - noCartesPaquet1;
		Carte[] paquet1 = new Carte[noCartesPaquet1];
		Carte[] paquet2 = new Carte[noCartesPaquet2];
		System.arraycopy(cartesTableau, 0, paquet1, 0, noCartesPaquet1);
		System.arraycopy(cartesTableau, noCartesPaquet1, paquet2, 0,
				noCartesPaquet2);

		// fusionner deux jeux de cartes en un en utilisant une méthode d'altération aléatoire
		fusionnerCartes(paquet1, paquet2, cartesTableau);

	}

	/**
	 * cette méthode fusionne deux jeux de cartes par un mécanisme pair impair
	 * @param pack1 jeu de cartes 1
	 * @param pack2 jeu de cartes 2
	 * @param cartesTableau jeu de cartes final
	 */
	private static void fusionnerCartes(Carte[] pack1, Carte[] pack2,
			Carte[] cartesTableau) {
		int pack1Compter = 0, pack2Compter = 0, finalPaquetCompter = 0;
		int alea = UtilitaireFonction.alea(0, 1);
		while (pack1Compter < pack1.length || pack2Compter < pack2.length) {
			if (alea % 2 == 0) {
				if (pack1Compter < pack1.length) {
					int noCartesCopier = copierCartes(pack1, cartesTableau,
							pack1Compter, finalPaquetCompter);
					pack1Compter += noCartesCopier;
					finalPaquetCompter += noCartesCopier;
				}
				alea = 1;
			} else {
				if (pack2Compter < pack2.length) {
					int noCartesCopier = copierCartes(pack2, cartesTableau,
							pack2Compter, finalPaquetCompter);
					pack2Compter += noCartesCopier;
					finalPaquetCompter += noCartesCopier;
				}
				alea = 0;
			}
		}
	}

	/**
	 * cette méthode copie certaines cartes au jeu de cartes final alternativement
	 * @param paquet être copié de
	 * @param finalPaquet être copié sur
	 * @param paquetCompteur position de départ de la première carte
	 * @param finalPaquetCompter position de départ de la première carte
	 * @return
	 */
	private static int copierCartes(Carte[] paquet, Carte[] finalPaquet,
			int paquetCompteur, int finalPaquetCompter) {
		int noCartesCopier = UtilitaireFonction.alea(1,
				paquet.length - paquetCompteur);
		System.arraycopy(paquet, paquetCompteur, finalPaquet,
				finalPaquetCompter, noCartesCopier);
		return noCartesCopier;
	}
	
	/**
	 * Methode 2:: Méthode de paquet
	 * cette méthode divise les cartes en plusieurs paquets au hasard et distribue un par un
	 * @param cartesTableau être mélangé
	 */
	public static void paquetsMethode(Carte[] cartesTableau) {
		int paquets = UtilitaireFonction.alea(Constantes.PAQUETS_MIN,
				Constantes.PAQUETS_MAX);
		Carte[][] pontCartesDivise = pontCartesDivise(cartesTableau, paquets);
		distribuerCarte(pontCartesDivise);
		copieArray(pontCartesDivise[paquets], cartesTableau);
	}

	/**
	 * cette méthode distribue des cartes à chaque pont créé jusqu'à ce qu'il n'en reste qu'une
	 * @param pontCartesDivise ensemble de petites cartes
	 */
	private static void distribuerCarte(Carte[][] pontCartesDivise) {
		int totaleNoPaquets = pontCartesDivise.length - 1;
		int paquetsRestants = totaleNoPaquets;
		// distribuer jusqu'à ce qu'un seul jeu de cartes à gauche
		while (true) {
			int paquetAleatoire = selectionnezPaquetAleatoire(pontCartesDivise);
			pontCartesDivise[totaleNoPaquets] = pontCartesDivise[paquetAleatoire];
			pontCartesDivise[paquetAleatoire] = null;
			if(paquetsRestants == 1) break;
			Carte[] cartes = pontCartesDivise[totaleNoPaquets];
			int i = 0;
			// prenez le dernier paquet de cartes et distribuez ses cartes sur les autres ponts
			for (int j=0; j<cartes.length;) {
				if (pontCartesDivise[i] != null) {
					int length = pontCartesDivise[i].length;
					pontCartesDivise[i]  = Arrays.copyOf(pontCartesDivise[i], length + 1);
					pontCartesDivise[i][length] = cartes[j];
					j++;
				}
				if(i == totaleNoPaquets - 1) i=0; else i++;
			}
			paquetsRestants--;
		}
	}

	/**
	 * cette méthode choisit le paquet de cartes au hasard pour la prochaine distribution
	 * @param pontCartesDivise
	 * @return
	 */
	private static int selectionnezPaquetAleatoire(Carte[][] pontCartesDivise) {
		while (true) {
			int pontChoisi = UtilitaireFonction.alea(0, pontCartesDivise.length - 2);
			if (pontCartesDivise[pontChoisi] != null) {
				return pontChoisi;
			}
		}
	}

	/**
	 * cette méthode divise le jeu de cartes principal en plusieurs petites cartes
	 * @param cartesTableau être divisé
	 * @param paquets nombre de ponts à créer
	 * @return tables de cartes divisées
	 */
	private static Carte[][] pontCartesDivise(Carte[] cartesTableau, int paquets) {
		Carte[][] jeuPlateau = new Carte[paquets + 1][]; 
		
		int cartesParDeck = (int)Math.ceil((double)cartesTableau.length/paquets);
		for(int i=0; i<paquets; i++) {
			int longueur = Math.min(cartesParDeck, cartesTableau.length - i * cartesParDeck);
			Carte[] deck = new Carte[longueur];
			System.arraycopy(cartesTableau, i * cartesParDeck , deck, 0, longueur);
			jeuPlateau[i] = deck;
		}
		return jeuPlateau;
	}

	/**
	 * Methode 3:: Méthode de position aléatoire
	 * cette méthode simule la prise de chaque carte au hasard et la construction de la table de jeu de cartes
	 * @param cartesAffichees être mélangé
	 */
	public static void positionAleatoireMethode(Carte[] cartesAffichees) {
		Carte[] tempCartesAffichees = new Carte[Constantes.NB_CARTES];
		for (int j=0; j<cartesAffichees.length;) {
			int pontChoisi = UtilitaireFonction.alea(0, tempCartesAffichees.length - 1);
			if (tempCartesAffichees[pontChoisi] == null) {
				tempCartesAffichees[pontChoisi] = cartesAffichees[j];
				j++;
			}
		}
		copieArray(tempCartesAffichees, cartesAffichees);
	}
	
	/**
	 * cette méthode copie une table de cartes à d'autres
	 * @param jeuNeuf
	 */
	private static void copieArray(Carte[] source, Carte[] target) {
		for(int i=0; i<source.length; i++) {
			target[i] = source[i];
		}
	}
}
