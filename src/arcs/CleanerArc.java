package arcs;

import nodes.Place;
import nodes.Transition;

/**
 * La classe des arcs videurs, allant d'une place à une transition.
 * 
 * @author r19caby, t19borde
 *
 */
public class CleanerArc extends ArcPtoT {

	public CleanerArc(Place p, Transition t) {
		super(p, t);
	}

	/**
	 * Méthode testant si l'arc videur est actif ou non.
	 */
	@Override
	public boolean isWorking() {
		return linkedPlace.getTokensNumber() > 0;
	}

	/**
	 * Méthode retirant tous les jetons de la place, car l'arc est videur.
	 */
	@Override
	public void moveTokens() {
		linkedPlace.setTokensNumber(0);
	}

}
