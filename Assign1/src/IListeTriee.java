
/**
 * Write a description of interface IListeTriee here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public interface IListeTriee <T extends Comparable>
{
    int ajouter(IListeTriee<T> autreListe);

    boolean ajouter(T element);

    void debut();

    T elementCourant();

    void positionner(T element);

    void fin();

    boolean precedent();

    boolean suivant();

    T supprimer();

    boolean supprimer(T element);

    IListeTriee<T> supprimer(IListeTriee<T> autreListe);

    boolean elementExiste(T element);

    int nbrElements();

    boolean estVide();

    IListeTriee<T> sousListe(T elementDebut,T elementFin);

    IListeTriee<T> elementsCommuns(IListeTriee<T> autreListe);
    
    void vider();
    
    

    
}
