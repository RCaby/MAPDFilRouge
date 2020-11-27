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
		Place place = petri.getPlacesList().get(petri.getPlacesList().size() - 1);
		PlaceAdapter pAdapter = new PlaceAdapter(" ", place);
		return pAdapter;
	}

	@Override
	public AbstractTransition addTransition() {
		petri.addTransition();
		Transition transition = petri.getTransitionsList().get(petri.getTransitionsList().size() - 1);
		TransitionAdapter tAdapter = new TransitionAdapter(" ", transition);
		return tAdapter;

	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if (source.isPlace()) {
			PlaceAdapter pAdapter = (PlaceAdapter) source;
			Place place = pAdapter.getPlace();
			TransitionAdapter tAdapter = (TransitionAdapter) destination;
			Transition transition = tAdapter.getTransition();
			petri.addRegularArc(place, transition, 1);
			RegularArc arc = (RegularArc) petri.getArcsList().get(petri.getArcsList().size()-1);
			ArcPtoTAdapter aAdapter = new ArcPtoTAdapter(arc, tAdapter, pAdapter);
			return aAdapter;
		} else {
			TransitionAdapter tAdapter = (TransitionAdapter) source;
			Transition transition = tAdapter.getTransition();
			PlaceAdapter pAdapter = (PlaceAdapter) destination;
			Place place = pAdapter.getPlace();
			petri.addArcTtoP(place, transition, 1);
			ArcTtoP arc = (ArcTtoP) petri.getArcsList().get(petri.getArcsList().size()-1);
			ArcTtoPAdapter aAdapter = new ArcTtoPAdapter(arc, tAdapter, pAdapter);
			return aAdapter;
		}
		
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		PlaceAdapter pAdapter = (PlaceAdapter) place;
		Place myPlace = pAdapter.getPlace();
		TransitionAdapter tAdapter = (TransitionAdapter) transition;
		Transition myTransition = tAdapter.getTransition();
		petri.addZeroArc(myPlace, myTransition);
		ZeroArc arc = (ZeroArc) petri.getArcsList().get(petri.getArcsList().size()-1);
		InhibitoryArcAdapter aAdapter = new InhibitoryArcAdapter(arc, tAdapter, pAdapter);
		return aAdapter;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		PlaceAdapter pAdapter = (PlaceAdapter) place;
		Place myPlace = pAdapter.getPlace();
		TransitionAdapter tAdapter = (TransitionAdapter) transition;
		Transition myTransition = tAdapter.getTransition();
		petri.addCleanerArc(myPlace, myTransition);
		CleanerArc arc = (CleanerArc) petri.getArcsList().get(petri.getArcsList().size()-1);
		ResetArcAdapter aAdapter = new ResetArcAdapter(arc, tAdapter, pAdapter);
		return aAdapter;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		PlaceAdapter pAdapter = (PlaceAdapter) place;
		Place myPlace = pAdapter.getPlace();
		petri.removePlace(myPlace);
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		TransitionAdapter tAdapter = (TransitionAdapter) transition;
		Transition myTransition = tAdapter.getTransition();
		petri.removeTransition(myTransition);
	}

	@Override
	public void removeArc(AbstractArc arc) {
		if (arc.isInhibitory()) {
			InhibitoryArcAdapter aAdapter = (InhibitoryArcAdapter) arc;
			ZeroArc myArc = aAdapter.getArc();
			petri.removeArc(myArc);
		} else if (arc.isReset()) {
			ResetArcAdapter aAdapter = (ResetArcAdapter) arc;
			CleanerArc myArc = aAdapter.getArc();
			petri.removeArc(myArc);
		} else if (arc.isSourceAPlace()) {
			ArcPtoTAdapter aAdapter = (ArcPtoTAdapter) arc;
			RegularArc myArc = aAdapter.getArc();
			petri.removeArc(myArc);
		} else {
			ArcTtoPAdapter aAdapter = (ArcTtoPAdapter) arc;
			ArcTtoP myArc = aAdapter.getArc();
			petri.removeArc(myArc);
		}
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter tAdapter = (TransitionAdapter) transition;
		Transition myTransition = tAdapter.getTransition();
		return myTransition.isPickable();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter tAdapter = (TransitionAdapter) transition;
		Transition myTransition = tAdapter.getTransition();
		myTransition.step();
	}

}
