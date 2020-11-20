package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome03.arcs.ArcTtoP;

public class ArcTtoPAdapter extends AbstractArc {
	
	private ArcTtoP arc;
	private TransitionAdapter ta;
	private PlaceAdapter pa;
	
	public ArcTtoPAdapter(ArcTtoP a, TransitionAdapter t, PlaceAdapter p) {
		arc = a;
		ta = t;
		pa = p;
	}

	@Override
	public AbstractNode getSource() {
		return ta;
	}

	@Override
	public AbstractNode getDestination() {
		return pa;
	}
	
	public ArcTtoP getArc() {
		return arc;
	}

	@Override
	public boolean isReset() {
		return false;
	}

	@Override
	public boolean isRegular() {
		return true;
	}

	@Override
	public boolean isInhibitory() {
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return arc.getValue();
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		arc.setValue(multiplicity);
		
	}

}
