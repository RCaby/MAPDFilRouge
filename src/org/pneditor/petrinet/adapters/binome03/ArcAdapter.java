package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome03.arcs.Arc;

public class ArcAdapter extends AbstractArc {
	
	protected Arc arc;
	protected TransitionAdapter transitionAdapter;
	protected PlaceAdapter placeAdapter;
	
	public ArcAdapter(Arc a, TransitionAdapter t, PlaceAdapter p) {
		arc = a;
		transitionAdapter = t;
		placeAdapter = p;
	}
	
	public Arc getArc() {
		return arc;
	}
	
	@Override
	public AbstractNode getSource() {
		return null;
	}

	@Override
	public AbstractNode getDestination() {
		return null;
	}

	@Override
	public boolean isReset() {
		return false;
	}

	@Override
	public boolean isRegular() {
		return false;
	}

	@Override
	public boolean isInhibitory() {
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return 0;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		
	}

}
