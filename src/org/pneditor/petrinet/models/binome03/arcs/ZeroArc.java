package org.pneditor.petrinet.models.binome03.arcs;

import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

/**
 * Classe des arcs zéros. Ils partent d'une place vers une transition et ont
 * pour valeur fixe zéro.
 * 
 * @author r19caby, t19borde
 *
 */
public class ZeroArc extends ArcPtoT {

	public ZeroArc(Place p, Transition t) {
		super(p, t);
	}

	/**
	 * Méthode déterminant si l'arc est actif, ce qui n'est le cas que si la place
	 * liée est vide.
	 */
	@Override
	public boolean isWorking() {
		return linkedPlace.getTokensNumber() == 0;
	}

}
