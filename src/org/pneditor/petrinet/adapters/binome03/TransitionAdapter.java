package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

/**
 * Classe adaptant la transition pour l'afficher dans PNEditor, elle garde en
 * mémoire la transition associée.
 */
public class TransitionAdapter extends AbstractTransition {

	private Transition transition;

	public TransitionAdapter(String label, Transition t) {
		super(label);
		transition = t;
	}

	public Transition getTransition() {
		return transition;
	}

}
