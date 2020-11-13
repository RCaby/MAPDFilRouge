package petriNet;

import java.util.ArrayList;
import java.util.Iterator;

import arcs.Arc;
import arcs.ArcPtoT;
import arcs.ArcTtoP;
import arcs.CleanerArc;
import arcs.RegularArc;
import arcs.ZeroArc;
import nodes.Place;
import nodes.Transition;

/**
 * La classe façade du programme.
 * 
 * @author r19caby, t19borde
 *
 */
public class PetriNet {
	private ArrayList<Transition> transitionsList;
	private ArrayList<Arc> arcsList;
	private ArrayList<Place> placesList;

	public PetriNet() {
		transitionsList = new ArrayList<Transition>();
		arcsList = new ArrayList<Arc>();
		placesList = new ArrayList<Place>();
	}

	/**
	 * Utilisé uniquement pour les tests
	 * 
	 * @return la liste des transitions du réseau
	 */
	public ArrayList<Transition> getTransitionsList() {
		return transitionsList;
	}

	/**
	 * Utilisé uniquement pour les tests
	 * 
	 * @return la liste des arcs du réseau
	 */
	public ArrayList<Arc> getArcsList() {
		return arcsList;
	}

	/**
	 * Utilisé uniquement pour les tests
	 * 
	 * @return la liste des places du réseau
	 */
	public ArrayList<Place> getPlacesList() {
		return placesList;
	}

	/**
	 * Fonction ajoutant une place au PetriNet. Elle n'a aucun jeton et n'est reliée
	 * à rien.
	 * 
	 * ((Test unitaire possible : tester la longueur de placesList avant et après))
	 */
	public void addPlace(int nb) {
		Place newPlace = new Place(nb);
		placesList.add(newPlace);
	}

	/**
	 * Retire une place du réseau. La méthode se charge d'éliminer tous les arcs qui
	 * lui sont reliés, avant de l'enlever de la liste des places du PetriNet.
	 * 
	 * ((Test unitaire : vérifier que arcsList a été diminué de place.getLinks()
	 * + longueur de arcsList et non-présence de l'élement concerné
	 * + avec une transition que l'on sait reliée à la place, vérifier que l'arc n'est plus dans ses listes))
	 * 
	 * @param place - La place a retirer.
	 */
	public void removePlace(Place place) {
		// Retrait des arcs sortant de la place.
		Iterator<Arc> itr = place.getLinks().iterator();
		while (itr.hasNext()) {
			Arc arc = itr.next();
			removeArc(arc);
		}
		placesList.remove(place);
	}

	/**
	 * Méthode ajoutant un arc Transition vers Place au PetriNet, en initialisant sa
	 * valeur à value.
	 * 
	 * ((Test unitaire : longueur de arcsList avant et après
	 * + avec la transition concernée, longueur de outLinks.
	 * + avec la place concernée, longueur de links
	 * Levée d'exception : que le int passé en paramètre est bien >0))
	 * 
	 * @param place      - La place d'arrivée.
	 * @param transition - La transition de départ.
	 * @param value      - La valeur de l'arc.
	 */
	public void addArcTtoP(Place place, Transition transition, int value) {
		ArcTtoP newArcTtoP = new ArcTtoP(place, transition, value);
		arcsList.add(newArcTtoP);
		transition.getOutLinks().add(newArcTtoP);
		place.getLinks().add(newArcTtoP);

	}

	/**
	 * Méthode ajoutant un arc allant d'une place à une transition, en initialisant
	 * sa valeur à value. C'est un arc "normal" : ni videur ni zéro.
	 * 
	 * ((Test unitaire et levée d'exception semblables à ceux de addArcTtoP))
	 * 
	 * @param place      - La place de départ.
	 * @param transition - La transition d'arrivée.
	 * @param value      - La valeur de l'arc.
	 */
	public void addRegularArc(Place place, Transition transition, int value) {
		RegularArc newRegularArc = new RegularArc(place, transition, value);
		arcsList.add(newRegularArc);
		transition.getInLinks().add(newRegularArc);
		place.getLinks().add(newRegularArc);
	}

	/**
	 * Méthode ajoutant un arc zéro allant d'une place à une transition.
	 * 
	 * ((Test unitaire : longueur de PetriNet.arcsList, Transition.inLinks, Place.links))
	 * 
	 * @param place      - La place de départ.
	 * @param transition - La transition d'arrivée.
	 */
	public void addZeroArc(Place place, Transition transition) {
		ZeroArc newZeroArc = new ZeroArc(place, transition);
		arcsList.add(newZeroArc);
		transition.getInLinks().add(newZeroArc);
		place.getLinks().add(newZeroArc);
	}

	/**
	 * Méthode ajoutant un arc videur allant d'une place à une transition.
	 * 
	 * ((Idem que addZeroArc))
	 * 
	 * @param place      - La place de départ.
	 * @param transition - La transition d'arrivée.
	 */
	public void addCleanerArc(Place place, Transition transition) {
		CleanerArc newCleanerArc = new CleanerArc(place, transition);
		arcsList.add(newCleanerArc);
		transition.getInLinks().add(newCleanerArc);
		place.getLinks().add(newCleanerArc);
	}

	/**
	 * Méthode retirant un arc du PetriNet. La méthode élimine toute référence à cet
	 * arc dans ses voisins (place et transition) avant de le supprimer.
	 * 
	 * ((Test unitaire : longueur de arcsList et inLinks+outLinks
	 * + non-présence de l'élément dans ces listes))
	 * 
	 * @param arc - L'arc à retirer.
	 */
	public void removeArc(Arc arc) {
		arc.withdraw();
		arcsList.remove(arc);
	}

	/**
	 * Méthode ajoutant une transition au PetriNet. Elle n'est reliée à rien.
	 * 
	 * ((Test unitaire : longueur de transitionsList))
	 */
	public void addTransition() {
		Transition newTransition = new Transition();
		transitionsList.add(newTransition);
	}

	/**
	 * Méthode retirant une transition du réseau. La méthode se charge d'éliminer
	 * tous les arcs qui lui sont reliés avant de retirer la transition.
	 * 
	 * ((Test unitaire : longueur de transitionsList
	 * + non-présence de l'élément concerné
	 * + avec une place reliée suppression de l'arc pour entrant et sortant))
	 * 
	 * @param transition - La transition à retirer.
	 */
	public void removeTransition(Transition transition) {
		// Retrait des arcs entrants dans la transition
		Iterator<ArcPtoT> itr = transition.getInLinks().iterator();
		while (itr.hasNext()) {
			ArcPtoT arc = itr.next();
			removeArc(arc);
		}

		// Retrait des arcs sortants dans la transition
		Iterator<ArcTtoP> itr2 = transition.getOutLinks().iterator();
		while (itr2.hasNext()) {
			ArcTtoP arc = itr2.next();
			removeArc(arc);
		}
		// Retrait de la transition
		transitionsList.remove(transition);
	}

	/**
	 * Méthode permettant de choisir une transition dans le PetriNet, et de lance
	 * son tirage si possible.
	 * 
	 * ((Test unitaire : lancer pour une transition tirable et une non-tirable
	 * et vérifier le déplacement des jetons que dans le premier cas))
	 * 
	 * @param transition - La transition à tirer.
	 */
	public void chooseTransition(Transition transition) {
		if (transition.isPickable()) {
			System.out.println("Transition tirable ; les jetons vont être déplacés");
			transition.step();
			System.out.println("Transition tirée, étape terminée");
		} else {
			System.out.println("La transition entrée n'est pas tirable");
		}
	}

}
