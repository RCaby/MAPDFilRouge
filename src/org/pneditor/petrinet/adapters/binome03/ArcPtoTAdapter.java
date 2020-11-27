package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome03.arcs.RegularArc;

/**
 * Classe adaptant l'arcPtoT pour affichage dans PNEditor. Le choix de
 * l'adaptateur Arc se fait dans PetriNetAdapter. Garde en mémoire l'arc, la
 * transition et la place associée.
 */
public class ArcPtoTAdapter extends ArcAdapter {

	public ArcPtoTAdapter(RegularArc a, TransitionAdapter t, PlaceAdapter p) {
		super(a, t, p);
	}

	@Override
	public AbstractNode getSource() {
		return placeAdapter;
	}

	@Override
	public AbstractNode getDestination() {
		return transitionAdapter;
	}

	public RegularArc getArc() {
		return (RegularArc) arc;
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
		return ((RegularArc) arc).getValue();
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		((RegularArc) arc).setValue(multiplicity);

	}

}