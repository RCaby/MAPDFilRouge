package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.binome03.arcs.Arc;
import org.pneditor.petrinet.models.binome03.arcs.ArcTtoP;
import org.pneditor.petrinet.models.binome03.arcs.CleanerArc;
import org.pneditor.petrinet.models.binome03.arcs.RegularArc;
import org.pneditor.petrinet.models.binome03.arcs.ZeroArc;
import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;
import org.pneditor.petrinet.models.binome03.petriNet.PetriNet;

/**
 * Classe qui sert à adapter notre programmation du réseau de réseau au PNE Editor
 */
public class PetriNetAdapter extends PetriNetInterface {

	private PetriNet petri;

	public PetriNetAdapter() {
		petri = new PetriNet();
	}
	
	/**
	 * Permet d'ajouter une place dans notre réseau de Pétri.
	 * Renvoie son adapteur.
	 */
	@Override
	public AbstractPlace addPlace() {
		petri.addPlace(0);
		Place place = petri.getPlacesList().get(petri.getPlacesList().size() - 1);
		PlaceAdapter pAdapter = new PlaceAdapter(" ", place);
		return pAdapter;
	}
	
	/**
	 * Permet d'ajouter une transition dans notre réseau de Pétri.
	 * Renvoie son adapteur.
	 */
	@Override
	public AbstractTransition addTransition() {
		petri.addTransition();
		Transition transition = petri.getTransitionsList().get(petri.getTransitionsList().size() - 1);
		TransitionAdapter tAdapter = new TransitionAdapter(" ", transition);
		return tAdapter;

	}
	
	/**
	 * Permet d'ajouter un arc normal (dont on peut changer la multiplicité) dans notre réseau de Pétri.
	 * Renvoie son adapteur.
	 * Ici, il est nécessaire pour nous de déterminer si la source est la place ou la transition, car cela change la classe d'arc utilisée.
	 */
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
	
	/**
	 * Permet d'ajouter un arc zéro (ou arc inhibiteur) dans notre réseau de Pétri.
	 * Renvoie son adapteur.
	 */
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
	
	/**
	 * Permet d'ajouter un arc videur (ou arc réinitialisateur) dans notre réseau de Petri.
	 * Renvoie son adapteur.
	 */
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
	
	/**
	 * Permet de retirer une place de notre réseau de Pétri.
	 */
	@Override
	public void removePlace(AbstractPlace place) {
		PlaceAdapter pAdapter = (PlaceAdapter) place;
		Place myPlace = pAdapter.getPlace();
		petri.removePlace(myPlace);
	}
	
	/**
	 * Permet de retirer une transition de notre réseau de Pétri.
	 */
	@Override
	public void removeTransition(AbstractTransition transition) {
		TransitionAdapter tAdapter = (TransitionAdapter) transition;
		Transition myTransition = tAdapter.getTransition();
		petri.removeTransition(myTransition);
	}
	
	/**
	 * Permet de retirer un arc de notre 
	 */
	@Override
	public void removeArc(AbstractArc arc) {
		ArcAdapter aAdapter = (ArcAdapter) arc;
		Arc myArc = aAdapter.getArc();
		petri.removeArc(myArc);
	}
	
	/**
	 * 
	 */
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
