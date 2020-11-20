package org.pneditor.petrinet.models.binome03.arcs;

import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

/**
 * La classe des arcs sortant des transitions. Il n'y a qu'un seul type d'arc
 * sortant.
 * 
 * @author r19caby, t19borde
 *
 */
public class ArcTtoP extends Arc {

	private int value;
	
	/**
	 * Constructeur de la classe ArcTtoP
	 * 
	 * ((Levée d'exception : on doit avoir i>0))
	 * 
	 * @param p - la place concernée
	 * @param t - la transition concernée
	 * @param i - la valeur de l'arc
	 */
	public ArcTtoP(Place p, Transition t, int i) {
		super(p, t);
		value = i;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int i) {
		value = i;
	}

	/**
	 * Ajoute le bon nombre de jetons à la place d'arrivée de l'arc.
	 */
	@Override
	public void moveTokens() {
		linkedPlace.setTokensNumber(linkedPlace.getTokensNumber() + value);
	}

	/**
	 * Retire la référence de cet arc dans la place et la transition associées.
	 */
	@Override
	public void withdraw() {
		linkedPlace.getLinks().remove(this);
		linkedTransition.getOutLinks().remove(this);
	}

}
