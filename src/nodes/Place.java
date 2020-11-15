package nodes;

import java.util.ArrayList;
import java.util.List;

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
		this();
		tokensNumber = number;

	}

	public Place() {
		this.links = new ArrayList<>();
		tokensNumber = 0;
	}

	public int getTokensNumber() {
		return tokensNumber;
	}

	public void setTokensNumber(int i) {
		this.tokensNumber = i;
	}

	public List<Arc> getLinks() {
		return links;
	}

}
