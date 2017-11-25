import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * I N F 2 1 2 0 Groupe 30
 * Travail Pratique 1
 * Lounis Adjissa et Oussama Ben Miloud
 * ADJL20069504 et BENO23109206
 * Adjissa.lounis@laposte.net & obenmiloud@icloud.com 
 * 
 * Ce programme prend en entree une sequence ARN de codons qui doit respecter plusieurs conditions,
 * calcule des metriques en relation a cette sequence, et resout une requete de recherche de sous-sequence ou acide amine.
 */
public class Tp1INF2120 {

	/**
	 * Scanner est utilise pour l'entree Utilisateur, et on importe aussi la listeARN qui est notre dictionnaire pour les combinaisons de sequences
	 */
	private static Scanner scanner;
	private static ListeARN listeARN;
	
	/**
	 * Methode principale.
	 * @param args
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		listeARN = new ListeARN();
		
		// Prend l'entree de la methode entreeChaine et la valide par rapport a nos conditions
		entreeChaine();
		boolean isValidated = listeARN.validation();
		
		// Si validee, affichage des metriques de la chaine ARN
		if(isValidated){
			listeARN.affichageMetriques();
		}else{
			System.err.println ("Chaine d'ARN invalide");
			exit();
		}
		
		System.out.print("Entrez votre requete :");
		String rechercheString = scanner.nextLine();
	    boolean validationboolAA = listeARN.validationAcideAmine(rechercheString);
	    boolean validationboolIUPAC = validationboolAA ? false : listeARN.validationIUPAC(rechercheString);
	    List<Integer> listeResultat = null;
	    if(validationboolAA){
	    		listeResultat = listeARN.getResultatsAA(rechercheString);
	    		//Met les resultats en ordre croissant plutot qu'en ordre de recherche
	    		Collections.sort(listeResultat);
	    		System.out.println("resultat : "+(listeResultat.isEmpty() ? "aucun element trouve." : listeResultat));
	    }else if(validationboolIUPAC){
		    	listeResultat = listeARN.getResultatsIUPAC(rechercheString);
		    	Collections.sort(listeResultat);
	    		System.out.println("resultat : "+(listeResultat.isEmpty() ? "aucun element trouve." : listeResultat));
	    }else{
	    		System.err.println ("Erreur: Requete invalide");
			exit();
	    }
		
		if (scanner != null) {
			scanner.close();
		}
	}

	/**
	 * Methode d'entree de la chaine ARN
	 */
	private static void entreeChaine() {
		System.out.print("Entrez votre ARN : ");
		String chaineARN = scanner.nextLine();
		// ajoute la chaine a la liste
	    listeARN.add(chaineARN);
	}
	
	/**
	 * Methode pour quitter le programme
	 */
	private static void exit() {
		System.exit(1);
	}
	
}
