package org.pneditor.petrinet.models.binome03.petriNet;

import java.util.ArrayList;
import java.util.List;

import org.pneditor.petrinet.models.binome03.arcs.Arc;
import org.pneditor.petrinet.models.binome03.arcs.ArcPtoT;
import org.pneditor.petrinet.models.binome03.arcs.ArcTtoP;
import org.pneditor.petrinet.models.binome03.arcs.CleanerArc;
import org.pneditor.petrinet.models.binome03.arcs.RegularArc;
import org.pneditor.petrinet.models.binome03.arcs.ZeroArc;
import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

import java.util.Iterator;

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
		transitionsList = new ArrayList<>();
		arcsList = new ArrayList<>();
		placesList = new ArrayList<>();
	}

	/**
	 * Utilisé uniquement pour les tests
	 * 
	 * @return la liste des transitions du réseau
	 */
	public List<Transition> getTransitionsList() {
		return transitionsList;
	}

	/**
	 * Utilisé uniquement pour les tests
	 * 
	 * @return la liste des arcs du réseau
	 */
	public List<Arc> getArcsList() {
		return arcsList;
	}

	/**
	 * Utilisé uniquement pour les tests
	 * 
	 * @return la liste des places du réseau
	 */
	public List<Place> getPlacesList() {
		return placesList;
	}

	/**
	 * Fonction ajoutant une place au PetriNet. Elle n'a aucun jeton et n'est reliée
	 * à rien.
	 */
	public void addPlace(int nb) {
		Place newPlace = new Place(nb);
		placesList.add(newPlace);
	}

	/**
	 * Retire une place du réseau. La méthode se charge d'éliminer tous les arcs qui
	 * lui sont reliés, avant de l'enlever de la liste des places du PetriNet.
	 * 
	 * @param place - La place à retirer.
	 */
	public void removePlace(Place place) {
		// Retrait des arcs sortant de la place.
		ArrayList<Arc> toBeRemoved = new ArrayList<>();
		Iterator<Arc> itr = place.getLinks().iterator();
		while (itr.hasNext()) {
			Arc arc = itr.next();
			toBeRemoved.add(arc);
		}
		for (Arc arc : toBeRemoved) {
			removeArc(arc);
		}
		placesList.remove(place);
	}

	/**
	 * Méthode ajoutant un arc Transition vers Place au PetriNet, en initialisant sa
	 * valeur à value.
	 * 
	 * @param place      - La place d'arrivée.
	 * @param transition - La transition de départ.
	 * @param value      - La valeur de l'arc.
	 */
	public void addArcTtoP(Place place, Transition transition, int value) {
		if (value < 0) {
			throw new IllegalArgumentException("Un arc ne peut pas avoir de valeur négative !");
		} else {
			ArcTtoP newArcTtoP = new ArcTtoP(place, transition, value);
			arcsList.add(newArcTtoP);
			transition.getOutLinks().add(newArcTtoP);
			place.getLinks().add(newArcTtoP);
		}

	}

	/**
	 * Méthode ajoutant un arc allant d'une place à une transition, en initialisant
	 * sa valeur à value. C'est un arc "normal" : ni videur ni zéro.
	 * 
	 * @param place      - La place de départ.
	 * @param transition - La transition d'arrivée.
	 * @param value      - La valeur de l'arc.
	 */
	public void addRegularArc(Place place, Transition transition, int value) {
		if (value < 0) {
			throw new IllegalArgumentException("Un arc ne peut pas avoir de valeur négative !");
		} else {
			RegularArc newRegularArc = new RegularArc(place, transition, value);
			arcsList.add(newRegularArc);
			transition.getInLinks().add(newRegularArc);
			place.getLinks().add(newRegularArc);
		}
	}

	/**
	 * Méthode ajoutant un arc zéro allant d'une place à une transition.
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
	 * @param arc - L'arc à retirer.
	 */
	public void removeArc(Arc arc) {
		arc.withdraw();
		arcsList.remove(arc);
	}

	/**
	 * Méthode ajoutant une transition au PetriNet. Elle n'est reliée à rien.
	 */
	public void addTransition() {
		Transition newTransition = new Transition();
		transitionsList.add(newTransition);
	}

	/**
	 * Méthode retirant une transition du réseau. La méthode se charge d'éliminer
	 * tous les arcs qui lui sont reliés avant de retirer la transition.
	 * 
	 * @param transition - La transition à retirer.
	 */
	public void removeTransition(Transition transition) {
		// Retrait des arcs entrants dans la transition
		ArrayList<ArcPtoT> toBeRemovedIn = new ArrayList<>();
		Iterator<ArcPtoT> itrIn = transition.getInLinks().iterator();
		while (itrIn.hasNext()) {
			ArcPtoT arc = itrIn.next();
			toBeRemovedIn.add(arc);
		}
		for (ArcPtoT arc : toBeRemovedIn) {
			removeArc(arc);
		}

		// Retrait des arcs sortants dans la transition
		ArrayList<ArcTtoP> toBeRemovedOut = new ArrayList<>();
		Iterator<ArcTtoP> itrOut = transition.getOutLinks().iterator();
		while (itrOut.hasNext()) {
			ArcTtoP arc = itrOut.next();
			toBeRemovedOut.add(arc);
		}
		for (ArcTtoP arc : toBeRemovedOut) {
			removeArc(arc);
		}

		// Retrait de la transition
		transitionsList.remove(transition);
	}

	/**
	 * Méthode permettant de choisir une transition dans le PetriNet, et de lance
	 * son tirage si possible.
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
