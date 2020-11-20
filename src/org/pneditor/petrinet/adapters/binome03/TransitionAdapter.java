package org.pneditor.petrinet.adapters.binome03;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

public class TransitionAdapter extends AbstractTransition {
	
	private Transition transition;

	public TransitionAdapter(String label, Transition t) {
		super(label);
		transition = t;
		// TODO Auto-generated constructor stub
	}
	
	public Transition getTransition() {
		return transition;
	}

}
