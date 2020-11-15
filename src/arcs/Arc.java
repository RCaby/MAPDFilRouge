package arcs;

import nodes.Place;
import nodes.Transition;

/**
 * Classe abstraite de tous les arcs.
 * 
 * @author r19caby, t19borde
 *
 */
public abstract class Arc {

	protected Place linkedPlace;
	protected Transition linkedTransition;

	protected Arc(Place p, Transition t) {
		this.linkedPlace = p;
		this.linkedTransition = t;
	}

	/**
	 * Déclenche le retrait ou l'ajout de jetons sur la place reliée à l'arc, selon
	 * le type de l'arc et la configuration (la place est-elle le point de départ ou
	 * l'arrivée ?).
	 */
	public void moveTokens() {
	}

	/**
	 * Déclenche le retrait de toutes les références à cet arc dans la place et la
	 * transition qui lui sont liées.
	 */
	public void withdraw() {
	}

	public Place getLinkedPlace() {
		return linkedPlace;
	}

	public Transition getLinkedTransition() {
		return linkedTransition;
	}

}
