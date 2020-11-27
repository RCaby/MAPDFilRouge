package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.binome03.arcs.ArcTtoP;

/**
 * Classe adaptant les arcsTtoP pour afficher dans PNEditor. Le choix de cet
 * adapter plutôt qu'un autre se fait dans PNEditorAdapter. Garde en mémoire
 * l'arc, la place et la transition associés.
 */
public class ArcTtoPAdapter extends ArcAdapter {

	public ArcTtoPAdapter(ArcTtoP a, TransitionAdapter t, PlaceAdapter p) {
		super(a, t, p);
	}

	@Override
	public AbstractNode getSource() {
		return transitionAdapter;
	}

	@Override
	public AbstractNode getDestination() {
		return placeAdapter;
	}

	public ArcTtoP getArc() {
		return (ArcTtoP) arc;
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
		return ((ArcTtoP) arc).getValue();
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		((ArcTtoP) arc).setValue(multiplicity);

	}

}
