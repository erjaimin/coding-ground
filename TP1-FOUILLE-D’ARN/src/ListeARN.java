import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sound.midi.ControllerEventListener;

/**
 * I N F 2 1 2 0 Groupe 30
 * Travail Pratique 1
 * Lounis Adjissa et Oussama Ben Miloud
 * ADJL20069504 et BENO23109206
 * Adjissa.lounis@laposte.net & obenmiloud@icloud.com 
 * 
 * Cette classe herite de ArrayList et contient les donnees pour les AA et les codes IUPAC.
 * Contient les methodes qui valident et font la recherche pour la classe Tp1INF2120 puis retourne les resultats.
 */
public class ListeARN extends ArrayList<String> {

	/**
	 * Variables de la classe ListeARN
	 */
	private static final long serialVersionUID = -8153162505976967457L;
	private static final DecimalFormat formatteur = new DecimalFormat("#0.00");  
	private static final List<String> CARACS_VALIDE = new ArrayList<>(Arrays.asList("A", "C", "G", "U"));
	private static final Map<String, List<String>> codonCarte;
	private static final Map<String, List<String>> codesIUPAC;
	private static final Map<String, Integer> carteCarbone;
	private static final Map<String, Integer> carteHydrogene;
	private static final Map<String, Integer> carteAzote;
	private static final Map<String, Integer> carteOxygene;
	private static final Map<String, Double> carteMasse;
	private static String arn;
	private List<String> resultat = new ArrayList<>();
	
	static{
		carteCarbone = new HashMap<>();
		carteHydrogene = new HashMap<>();
		carteAzote = new HashMap<>();
		carteOxygene = new HashMap<>();
		carteMasse = new HashMap<>();
		codonCarte = new HashMap<>();
		codesIUPAC = new HashMap<>();
		carteCarbone.put("A", 5);
		carteCarbone.put("C", 4);
		carteCarbone.put("G", 5);
		carteCarbone.put("U", 4);
		carteHydrogene.put("A", 5);
		carteHydrogene.put("C", 5);
		carteHydrogene.put("G", 5);
		carteHydrogene.put("U", 4);
		carteAzote.put("A", 5);
		carteAzote.put("C", 3);
		carteAzote.put("G", 5);
		carteAzote.put("U", 2);
		carteOxygene.put("A", 0);
		carteOxygene.put("C", 1);
		carteOxygene.put("G", 1);
		carteOxygene.put("U", 2);
		carteMasse.put("A", 135.13);
		carteMasse.put("C", 111.10);
		carteMasse.put("G", 151.13);
		carteMasse.put("U", 112.09);
		remplirCartes();
	}

	/**
	 * constructeur de liste sans arguments
	 */
	public ListeARN() {
		super();
	}
	
	/**
	 * constructeur de liste avec capacite initiale
	 * @param capaciteInitiale
	 */
	public ListeARN(int capaciteInitiale){
		super(capaciteInitiale);
	}
	
	/**
	 * @param chaineARN Methode pour ajouter l'entree utilisateur
	 */
	@Override
	public boolean add(String chaineARN) {
		arn = chaineARN;
		if(chaineARN != null && !chaineARN.isEmpty()){
			for(char ch : chaineARN.toCharArray()){
				super.add(Character.toString(ch));
			}
		}
		return true;
	}

	/**
	 * Methode de validation de l'entree utilisateur
	 * @return retourne vrai si la chaine ARN est valide, faux sinon
	 */
	public boolean validation() {
		boolean estValide = true;
		if (this.isEmpty() || (this.size() < 6) || (this.size() % 3 != 0) || this.contains(" ")
				|| this.retainAll(CARACS_VALIDE) || verifierCase()) {
			estValide = false;
		}
		return estValide;
	}

	/**
	 * Methode de verification de la case.
	 * @return retourne vrai si toute l'entree est en Majuscule, faux sinon
	 */
	private boolean verifierCase() {
		boolean estValide = false;
		ListIterator<String> iterateurListe = this.listIterator();
		while (iterateurListe.hasNext()) {
			String codon = iterateurListe.next();
			if (codon.equals(codon.toLowerCase())) {
				estValide = true;
			}
		}
		return estValide;
	}

	/**
	 * Methode d'affichage des metriques pour la chaine ARN
	 */
	public void affichageMetriques() {
		int compteA = getCompteCodon("A");
		int compteC = getCompteCodon("C");
		int compteG = getCompteCodon("G");
		int compteU = getCompteCodon("U");
		System.out.println("Nombre d'hydrogene : "+calculerHydrogene(compteA, compteC, compteG, compteU));
		System.out.println("Nombre de carbone  : "+calculerCarbone(compteA, compteC, compteG, compteU));
		System.out.println("Nombre d'azote     : "+calculerAzote(compteA, compteC, compteG, compteU));
		System.out.println("Nombre d'oxygene   : "+calculerOxygene(compteA, compteC, compteG, compteU));
		System.out.println("Masse molaire      : "+calculerMasseTotale(compteA, compteC, compteG, compteU));
	}

	/**
	 * Methode de comptage de codons dans la chaine ARN
	 * @param codon a compter
	 * @return
	 */
	private int getCompteCodon(String codon) {
		int compte = 0;
		ListIterator<String> iterateurListe = this.listIterator();
		while(iterateurListe.hasNext()){
			if(codon.equals(iterateurListe.next())){
				compte++;
			}
		}
		return compte;
	}

	/**
	 * Methode de calcul du nombre total de carbones
	 * @param compteA
	 * @param compteC
	 * @param compteG
	 * @param compteU
	 * @return nombre total de carbones
	 */
	private int calculerCarbone(int compteA, int compteC, int compteG, int compteU) {
		int totalCarbone = 0;
		totalCarbone += compteA * carteCarbone.get("A");
		totalCarbone += compteC * carteCarbone.get("C");
		totalCarbone += compteG * carteCarbone.get("G");
		totalCarbone += compteU * carteCarbone.get("U");
		return totalCarbone;
	}
	
	/**
	 * Methode de calcul du nombre total d'oxygene
	 * @param compteA
	 * @param compteC
	 * @param compteG
	 * @param compteU
	 * @return nombre total d'oxygene
	 */
	private int calculerOxygene(int compteA, int compteC, int compteG, int compteU) {
		int totalOxygene = 0;
		totalOxygene += compteA * carteOxygene.get("A");
		totalOxygene += compteC * carteOxygene.get("C");
		totalOxygene += compteG * carteOxygene.get("G");
		totalOxygene += compteU * carteOxygene.get("U");
		return totalOxygene;
	}

	/**
	 * Methode de calcul du nombre total d'azote
	 * @param compteA
	 * @param compteC
	 * @param compteG
	 * @param compteU
	 * @return nombre total d'azote
	 */
	private int calculerAzote(int compteA, int compteC, int compteG, int compteU) {
		int totalAzote = 0;
		totalAzote += compteA * carteAzote.get("A");
		totalAzote += compteC * carteAzote.get("C");
		totalAzote += compteG * carteAzote.get("G");
		totalAzote += compteU * carteAzote.get("U");
		return totalAzote;
		
	}

	/**
	 * Methode de calcul du nombre total d'hydrogene
	 * @param compteA
	 * @param compteC
	 * @param compteG
	 * @param compteU
	 * @return nombre total d'hydrogene
	 */
	private int calculerHydrogene(int compteA, int compteC, int compteG, int compteU) {
		int totalHydrogene = 0;
		totalHydrogene += compteA * carteHydrogene.get("A");
		totalHydrogene += compteC * carteHydrogene.get("C");
		totalHydrogene += compteG * carteHydrogene.get("G");
		totalHydrogene += compteU * carteHydrogene.get("U");
		return totalHydrogene;
	}
	
	/**
	 * Methode de calcul de la masse totale
	 * @param compteA
	 * @param compteC
	 * @param compteG
	 * @param compteU
	 * @return la masse totale de la chaine
	 */
	private String calculerMasseTotale(int compteA, int compteC, int compteG, int compteU) {
		double masseTotale = 0.0; 
		masseTotale += compteA * carteMasse.get("A");
		masseTotale += compteC * carteMasse.get("C");
		masseTotale += compteG * carteMasse.get("G");
		masseTotale += compteU * carteMasse.get("U");
		return formatteur.format(masseTotale) + " g/mol";
	}

	/**
	 * Methode de validation pour la requete Acides Amines
	 * @param abbreviation
	 * @return vrai si valide, faux sinon
	 */
	public boolean validationAcideAmine(String abbreviation) {
		return codonCarte.keySet().contains(abbreviation);
	}
	

	/**
	 * Methode de validation pour la requete de Sous-Sequence
	 * @param soussequence
	 * @return vrai si valide, faux sinon
	 */
	public boolean validationIUPAC(String soussequence) {
		boolean estValide = true;
		Set<String> keySet = codesIUPAC.keySet();
		char[] caracsArray = soussequence.toCharArray();
		if(caracsArray.length <= 0 || (caracsArray.length % 3 != 0)){
			estValide = false;
		}
		for(char ch : caracsArray){
			if(Character.isLowerCase(ch) || !keySet.contains(Character.toString(ch))){
				estValide = false;
			}
		}
		return estValide;
	}

	/**
	 * Methode pour remplir les Hashmap avec les abbreviation et combinaisons 
	 * de codons pour les acides amines et les codes IUPAC
	 */
	private static void remplirCartes() {
		codonCarte.put("Ala", Arrays.asList("GCU", "GCC", "GCA", "GCG"));
		codonCarte.put("Arg", Arrays.asList("CGU", "CGC", "CGA", "CGG", "AGA", "AGG"));
		codonCarte.put("Asn", Arrays.asList("AAU", "AAC"));
		codonCarte.put("Asp", Arrays.asList("GAU", "GAC"));
		codonCarte.put("Cys", Arrays.asList("UGU", "UGC"));
		codonCarte.put("Glu", Arrays.asList("GAA", "GAG"));
		codonCarte.put("Gln", Arrays.asList("CAA", "CAG"));
		codonCarte.put("Gly", Arrays.asList("GGU", "GGC", "GGA","GGG"));
		codonCarte.put("His", Arrays.asList("CAU", "CAC"));
		codonCarte.put("Ile", Arrays.asList("AUU","AUC","AUA"));
		codonCarte.put("Leu", Arrays.asList("UUA","UUG","CUU","CUC","CUA","CUG"));
		codonCarte.put("Lys", Arrays.asList("AAA","AAG"));
		codonCarte.put("Met", Arrays.asList("AUG"));
		codonCarte.put("Phe", Arrays.asList("UUU","UUC"));
		codonCarte.put("Pro", Arrays.asList("CCU","CCC","CCA","CCG"));
		codonCarte.put("Pyl", Arrays.asList("UAG"));
		codonCarte.put("Sec", Arrays.asList("UGA"));
		codonCarte.put("Ser", Arrays.asList("UCU","UCC","UCA","UCG","AGU","AGC"));
		codonCarte.put("Thr", Arrays.asList("ACU","ACC","ACA","ACG"));
		codonCarte.put("Trp", Arrays.asList("UGG"));
		codonCarte.put("Tyr", Arrays.asList("UAU","UAC"));
		codonCarte.put("Val", Arrays.asList("GUU","GUC","GUA","GUG"));
	    
		codesIUPAC.put("A", Arrays.asList("A"));
		codesIUPAC.put("C", Arrays.asList("C"));
		codesIUPAC.put("G", Arrays.asList("G"));
		codesIUPAC.put("U", Arrays.asList("U"));
	    codesIUPAC.put("R", Arrays.asList("A","G"));
	    codesIUPAC.put("Y", Arrays.asList("C","U"));
	    codesIUPAC.put("S", Arrays.asList("C","G"));
	    codesIUPAC.put("W", Arrays.asList("A","U"));
	    codesIUPAC.put("K", Arrays.asList("G","U"));
	    codesIUPAC.put("M", Arrays.asList("A","C"));
	    codesIUPAC.put("B", Arrays.asList("C","G","U"));
	    codesIUPAC.put("D", Arrays.asList("A","G","U"));
	    codesIUPAC.put("H", Arrays.asList("A","C","U"));
	    codesIUPAC.put("V", Arrays.asList("A","C", "G"));
	    codesIUPAC.put("N", Arrays.asList("A","C","G","U"));
	}

	/**
	 * Methode qui retourne la liste de resultats ou l'abbreviation est trouvee, 
	 * en utilisant l'iterateur et validant que le resultat satisfait aux conditions de recherche
	 * @param abbreviation a rechercher dans la chaine ARN
	 * @return les index ou la combinaison a ete trouvee, liste vide sinon
	 */
	public List<Integer> getResultatsAA(String abbreviation) {
		List<Integer> resultats = new ArrayList<>();
		List<String> liste = codonCarte.get(abbreviation);
		ListIterator<String> iterateurListe = liste.listIterator();
		while(iterateurListe.hasNext()){
			String codon = iterateurListe.next();
			int indice = arn.indexOf(codon);
			while(indice >= 0 && (indice % 3 == 0)){
				resultats.add(indice + 1);
				indice = arn.indexOf(codon, indice + 3);
			}
		}
		return resultats;
	}
	
	/**
	 * Methode qui retourne la liste de resultats ou la sous-sequence est trouvee, 
	 * en creant une liste de toutes les listes de combinaisons possibles pour la requete et en trouvant les index dans la chaine ARN
	 * @param soussequence a rechercher dans les combinaisons possible des ensembles IUPAC
	 * @return les index ou la combinaison a ete trouvee, liste vide sinon
	 */
	public List<Integer> getResultatsIUPAC(String soussequence) {
		char[] caracsArray = soussequence.toCharArray();
		List<Integer> resultats = new ArrayList<>();
		List<List<String>> listes = new ArrayList<>();
		for(char ch : caracsArray){
			listes.add(codesIUPAC.get(Character.toString(ch)));
		}
	    combinerListes(listes);
	    
	    ListIterator<String> iterateurListe = resultat.listIterator();
	    while(iterateurListe.hasNext()){
	    	String codon = iterateurListe.next();
			int indice = arn.indexOf(codon);
			while(indice >= 0 && (indice % 3 == 0)){
				resultats.add(indice + 1);
				indice = arn.indexOf(codon, indice + codon.length());
			}
	    }
	    return resultats;
	}

	/**
	 * Methode qui combine toutes les listes de codes IUPAC possibles et donne une liste de toutes les combinaisons
	 * @param listes a combiner
	 */
	private void combinerListes(List<List<String>> listes) {
		List<String> resultats = new ArrayList<>(Collections.nCopies(listes.size(), ""));
	    merge(0, listes, resultats);
	}

	/**
	 * Methode recursive qui combine les listes
	 * @param indice
	 * @param listes
	 * @param resultats
	 */
	private void merge(int indice, List<List<String>> listes, List<String> resultats) {
		List<String> liste = listes.get(indice);
		for(int i=0; i< liste.size(); i++){
			resultats.set(indice, liste.get(i));
			if(indice == listes.size() - 1){
				resultat.add(resultats.stream().collect(Collectors.joining()));
			}else{
				merge(indice+1, listes, resultats);
			}
		}
	}
}
