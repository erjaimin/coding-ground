
/**
 * Write a description of class ListeTriee here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ListeTriee<T extends Comparable> implements IListeTriee<T> {
	// 2 attribut d'instance
	private ArrayList<T> elements;
	private int position = -1;
	
	/**
	 * 
	 */
	public ListeTriee() {
		this.elements = new ArrayList<>();
	}

	@Override
	public int ajouter(IListeTriee<T> autreListe) {
		int nbElements = 0;
		if(autreListe == null) 
			throw new NullPointerException();
		autreListe.debut();
		if(!this.elementExiste(autreListe.elementCourant())) {
			this.ajouter(autreListe.elementCourant());
			nbElements++;
		}
		while(autreListe.suivant()) {
			if(!this.elementExiste(autreListe.elementCourant())) {
				this.ajouter(autreListe.elementCourant());
				nbElements++;
			}
		}
		if(!this.estVide()) {
			this.debut();
		}
		return nbElements;	
	}

	@Override
	public boolean ajouter(T element) {
		if(element == null)
			throw new NullPointerException();
		if(this.elementExiste(element))
			return false;
		this.elements.add(element);;
		Collections.sort(elements);
		this.position = this.elements.indexOf(element);
		return true;
	}

	@Override
	public void debut() {
		if(this.estVide())
			throw new ListeVideException();
		this.position = 0;
	}

	@Override
	public T elementCourant() {
		if(position == -1)
			throw new ListeVideException();
		return this.elements.get(position);
	}

	@Override
	public void positionner(T element) {
		if(this.estVide())
			throw new ListeVideException();
		if(element == null) 
			throw new NullPointerException();
		if(this.elementExiste(element)) {
			this.position = this.elements.indexOf(element);
		}else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void fin() {
		if(this.estVide())
			throw new ListeVideException();
		this.position = this.nbrElements() - 1;
	}

	@Override
	public boolean precedent() {
		if(this.estVide())
			throw new ListeVideException();
		if(this.position == 0) {
			return false;
		}else {
			this.positionner(this.elements.get(position - 1));
			return true;
		}
	}

	@Override
	public boolean suivant() {
		if(this.estVide())
			throw new ListeVideException();
		if(this.position == this.nbrElements() - 1) {
			return false;
		}else {
			this.positionner(this.elements.get(position + 1));
			return true;
		}
	}

	@Override
	public T supprimer() {
		if(this.estVide())
			throw new ListeVideException();
		T removedElement = this.elements.remove(position);
		if(!this.estVide()) {
			if(position == 0) {
				this.debut();
			}else {
				this.precedent();
			}
		}
		return removedElement;
	}

	@Override
	public boolean supprimer(T element) {
		if(this.estVide())
			throw new ListeVideException();
		if(element == null)
			throw new NullPointerException();
		if(!this.elementExiste(element)) 
			return false;
		this.positionner(element);
		T supprimer = this.supprimer();
		if(supprimer != null)
			return true;
		return false;
	}

	@Override
	public IListeTriee<T> supprimer(IListeTriee<T> autreListe) {
		if(autreListe == null) 
			throw new NullPointerException();
		autreListe.debut();
		IListeTriee<T> newList = new ListeTriee<>();
		if(this.elementExiste(autreListe.elementCourant())) {
			this.supprimer(autreListe.elementCourant());
		}
		while(autreListe.suivant()) {
			if(this.elementExiste(autreListe.elementCourant())) {
				this.supprimer(autreListe.elementCourant());
			}
		}
		if(!newList.estVide()) {
			newList.debut();
		}
		return newList;
	}

	@Override
	public boolean elementExiste(T element) {
		return this.elements.contains(element);
	}

	@Override
	public int nbrElements() {
		return this.elements.size();
	}

	@Override
	public boolean estVide() {
		return this.nbrElements() == 0;
	}

	@Override
	public IListeTriee<T> sousListe(T elementDebut, T elementFin) {
		if(this.estVide())
			throw new ListeVideException();
		if(elementDebut == null || elementFin == null)
			throw new NullPointerException();
		if(elementDebut.compareTo(elementFin) > 0)
			throw new NoSuchElementException();
		IListeTriee<T> sublist = new ListeTriee<>();
		this.debut();
		if(this.elementCourant().compareTo(elementDebut) >= 0) {
			sublist.ajouter(this.elementCourant());
		}
		while(this.suivant()) {
			if(sublist.estVide()) {
				if(this.elementCourant().compareTo(elementDebut) >= 0) {
					sublist.ajouter(this.elementCourant());
				}
			}else {
				if(this.elementCourant().compareTo(elementFin) > 0) {
					break;
				}	
				sublist.ajouter(this.elementCourant());
			}
		}
		if(!sublist.estVide()) {
			sublist.debut();
		}
		return sublist;
	}

	@Override
	public IListeTriee<T> elementsCommuns(IListeTriee<T> autreListe) {
		if(autreListe == null) 
			throw new NullPointerException();
		IListeTriee<T> intersection = new ListeTriee<>();
		autreListe.debut();
		if(this.elementExiste(autreListe.elementCourant())) {
			intersection.ajouter(autreListe.elementCourant());
		}
		while(autreListe.suivant()) {
			if(this.elementExiste(autreListe.elementCourant())) {
				intersection.ajouter(autreListe.elementCourant());
			}
		}
		if(!intersection.estVide()) {
			intersection.debut();
		}
		return intersection;
	}

	@Override
	public void vider() {
		this.elements.clear();
	}

	/**
	 * <p>
	 * Retourne une representation de cette liste sous forme d'une chaine de
	 * caracteres, selon le format montre ci-dessous.
	 * </p>
	 * 
	 * <pre>
	 * Format de la chaine retournee : 
	 * 
	 *    "[]"                                   (si cette liste est vide)
	 *    "[E1, E2, ..., En] (element courant)"  (si cette liste n'est pas vide)
	 * 
	 * Exemple : Soit cette liste = [2, 3, 7, 9, 12, 25, 36, 42] dont l'element 
	 *           courant est 9. L'appel de toString sur cette liste retournera 
	 *           la chaine "[2, 3, 7, 9, 12, 25, 36, 42] (9)"
	 * </pre>
	 * 
	 * @return une representation de cette liste sous forme d'une chaine de
	 *         caracteres.
	 */
	@Override
	public String toString() {
		String s;
		if (elements.isEmpty()) {
			s = "[]";
		} else {
			s = elements.toString() + " (" + elements.get(position) + ")";
		}
		return s;
	}

}
