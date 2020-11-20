package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.binome03.arcs.ArcTtoP;
import org.pneditor.petrinet.models.binome03.arcs.CleanerArc;
import org.pneditor.petrinet.models.binome03.arcs.RegularArc;
import org.pneditor.petrinet.models.binome03.arcs.ZeroArc;
import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;
import org.pneditor.petrinet.models.binome03.petriNet.PetriNet;

public class PetriNetAdapter extends PetriNetInterface {

	private PetriNet petri;

	public PetriNetAdapter() {
		petri = new PetriNet();
	}

	@Override
	public AbstractPlace addPlace() {
		petri.addPlace(0);
		Place p = petri.getPlacesList().get(petri.getPlacesList().size() - 1);
		PlaceAdapter pa = new PlaceAdapter(" ", p);
		return pa;
	}

	@Override
	public AbstractTransition addTransition() {
		petri.addTransition();
		Transition t = petri.getTransitionsList().get(petri.getTransitionsList().size() - 1);
		TransitionAdapter ta = new TransitionAdapter(" ", t);
		return ta;

	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if (source.isPlace()) {
			PlaceAdapter pa = (PlaceAdapter) source;
			Place p = pa.getPlace();
			TransitionAdapter ta = (TransitionAdapter) destination;
			Transition t = ta.getTransition();
			petri.addRegularArc(p, t, 1);
			RegularArc ra = (RegularArc) petri.getArcsList().get(petri.getArcsList().size()-1);
			ArcPtoTAdapter apta = new ArcPtoTAdapter(ra, ta, pa);
			return apta;
		} else {
			TransitionAdapter ta = (TransitionAdapter) source;
			Transition t = ta.getTransition();
			PlaceAdapter pa = (PlaceAdapter) destination;
			Place p = pa.getPlace();
			petri.addArcTtoP(p, t, 1);
			ArcTtoP ra = (ArcTtoP) petri.getArcsList().get(petri.getArcsList().size()-1);
			ArcTtoPAdapter atpa = new ArcTtoPAdapter(ra, ta, pa);
			return atpa;
		}
		
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		PlaceAdapter pa = (PlaceAdapter) place;
		Place p = pa.getPlace();
		TransitionAdapter ta = (TransitionAdapter) transition;
		Transition t = ta.getTransition();
		petri.addZeroArc(p, t);
		ZeroArc a = (ZeroArc) petri.getArcsList().get(petri.getArcsList().size()-1);
		InhibitoryArcAdapter iaa = new InhibitoryArcAdapter(a, ta, pa);
		return iaa;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		PlaceAdapter pa = (PlaceAdapter) place;
		Place p = pa.getPlace();
		TransitionAdapter ta = (TransitionAdapter) transition;
		Transition t = ta.getTransition();
		petri.addCleanerArc(p, t);
		CleanerArc a = (CleanerArc) petri.getArcsList().get(petri.getArcsList().size()-1);
		ResetArcAdapter raa = new ResetArcAdapter(a, ta, pa);
		return raa;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		PlaceAdapter pa = (PlaceAdapter) place;
		Place p = pa.getPlace();
		petri.removePlace(p);
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		TransitionAdapter ta = (TransitionAdapter) transition;
		Transition t = ta.getTransition();
		petri.removeTransition(t);
	}

	@Override
	public void removeArc(AbstractArc arc) {
		if (arc.isInhibitory()) {
			InhibitoryArcAdapter iaa = (InhibitoryArcAdapter) arc;
			ZeroArc za = iaa.getArc();
			petri.removeArc(za);
		} else if (arc.isReset()) {
			ResetArcAdapter raa = (ResetArcAdapter) arc;
			CleanerArc ca = raa.getArc();
			petri.removeArc(ca);
		} else if (arc.isSourceAPlace()) {
			ArcPtoTAdapter apta = (ArcPtoTAdapter) arc;
			RegularArc ra = apta.getArc();
			petri.removeArc(ra);
		} else {
			ArcTtoPAdapter atpa = (ArcTtoPAdapter) arc;
			ArcTtoP atp = atpa.getArc();
			petri.removeArc(atp);
		}
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter ta = (TransitionAdapter) transition;
		Transition t = ta.getTransition();
		return t.isPickable();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter ta = (TransitionAdapter) transition;
		Transition t = ta.getTransition();
		t.step();
	}

}
