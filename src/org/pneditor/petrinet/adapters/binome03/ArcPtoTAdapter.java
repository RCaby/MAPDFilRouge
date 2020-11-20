package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome03.arcs.RegularArc;

public class ArcPtoTAdapter extends AbstractArc {
	
	private RegularArc arc;
	private TransitionAdapter ta;
	private PlaceAdapter pa;
	
	public ArcPtoTAdapter(RegularArc a, TransitionAdapter t, PlaceAdapter p) {
		arc = a;
		ta = t;
		pa = p;
	}

	@Override
	public AbstractNode getSource() {
		return pa;
	}

	@Override
	public AbstractNode getDestination() {
		return ta;
	}
	
	public RegularArc getArc() {
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