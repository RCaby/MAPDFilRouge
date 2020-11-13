package nodes;

import java.util.ArrayList;

import arcs.Arc;

/**
 * Classe des places. La place a accès à une liste d'arcs et un nombre de
 * jetons.
 * 
 * @author r19caby, t19borde
 *
 */
public class Place {
	private int tokensNumber;
	private ArrayList<Arc> links;

	public Place(int number) {
		tokensNumber = number;
		this.links = new ArrayList<Arc>();
	}

	public int getTokensNumber() {
		return tokensNumber;
	}

	public void setTokensNumber(int i) {
		this.tokensNumber = i;
	}

	public ArrayList<Arc> getLinks() {
		return links;
	}

}
