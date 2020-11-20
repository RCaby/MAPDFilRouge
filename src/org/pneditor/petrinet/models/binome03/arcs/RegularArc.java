package org.pneditor.petrinet.models.binome03.arcs;

import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

/**
 * Classe des arcs "normaux" entrant dans une transition.
 * 
 * @author r19caby, t19borde
 *
 */
public class RegularArc extends ArcPtoT {

	private int value;

	public RegularArc(Place p, Transition t, int i) {
		super(p, t);
		value = i;
	}

	/**
	 * Méthode déterminant si l'arc autorise le tirage de la transition associée.
	 */
	@Override
	public boolean isWorking() {
		return value <= linkedPlace.getTokensNumber();
	}

	/**
	 * Méthode diminuant le nombre de jetons de la place selon la valeur de l'arc.
	 */
	@Override
	public void moveTokens() {
		linkedPlace.setTokensNumber(linkedPlace.getTokensNumber() - value);
	}

}
