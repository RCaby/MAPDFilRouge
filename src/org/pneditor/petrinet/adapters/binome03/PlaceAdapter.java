package org.pneditor.petrinet.adapters.binome03;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.binome03.nodes.Place;

public class PlaceAdapter extends AbstractPlace {
	
	private Place place;
	
	public PlaceAdapter(String label, Place p) {
		super(label);
		place = p;
	}
	
	@Override
	public void addToken() {
		place.setTokensNumber(place.getTokensNumber()+1);
	}

	@Override
	public void removeToken() {
		place.setTokensNumber(place.getTokensNumber()-1);
	}
	
	public Place getPlace() {
		return place;
	}

	@Override
	public int getTokens() {
		return place.getTokensNumber();
	}

	@Override
	public void setTokens(int tokens) {
		place.setTokensNumber(tokens);
	}

}
