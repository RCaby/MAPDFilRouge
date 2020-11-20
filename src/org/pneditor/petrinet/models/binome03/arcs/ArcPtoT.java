package org.pneditor.petrinet.models.binome03.arcs;

import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

/**
 * La classe abstraite des arcs entrants dans les transitions. Il a trois types
 * d'arcs entrants : Regular ("normal"), Videur et Zéro.
 * 
 * @author r19caby, t19borde
 *
 */
public abstract class ArcPtoT extends Arc {

	protected ArcPtoT(Place p, Transition t) {
		super(p, t);
	}

	/**
	 * Méthode permettant de tester si l'arc fonctionne. La méthode est redéfinie
	 * dans les classes filles.
	 * 
	 * @return true - Initialisation de la méthode.
	 */
	public boolean isWorking() {
		return true;
	}

	/**
	 * Méthode surchargée permettant de retirer cet arc de la place et de la
	 * transition associées.
	 * 
	 * ((Test unitaire : vérifier les longueurs des listes + non-présence de
	 * l'élément))
	 */
	@Override
	public void withdraw() {
		linkedPlace.getLinks().remove(this);
		linkedTransition.getInLinks().remove(this);
	}

}
