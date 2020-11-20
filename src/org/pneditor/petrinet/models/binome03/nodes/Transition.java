package org.pneditor.petrinet.models.binome03.nodes;

import java.util.ArrayList;
import java.util.List;

import org.pneditor.petrinet.models.binome03.arcs.Arc;
import org.pneditor.petrinet.models.binome03.arcs.ArcPtoT;
import org.pneditor.petrinet.models.binome03.arcs.ArcTtoP;

/**
 * Classe des transitions. Une transition contient une liste d'arcs entrants et
 * d'arcs sortants. La transition gère elle-même le test de tirabilité et le
 * tirage.
 * 
 * @author r19caby, t19borde
 *
 */
public class Transition {
	private ArrayList<ArcPtoT> inLinks;
	private ArrayList<ArcTtoP> outLinks;

	public Transition() {
		inLinks = new ArrayList<>();
		outLinks = new ArrayList<>();
	}

	/**
	 * Méthode déterminant si la transition est tirable.
	 * 
	 * @return result - La transition est-elle tirable ?
	 */
	public boolean isPickable() {
		boolean result = true;
		for (ArcPtoT arc : inLinks) {
			result = result && arc.isWorking();
		}
		return result;
	}

	/**
	 * Méthode gérant le tirage en "déplaçant" les jetons, en supposant la
	 * transition tirable.
	 */
	public void step() {
		for (Arc arc : inLinks) {
			arc.moveTokens();
		}
		for (Arc arc : outLinks) {
			arc.moveTokens();
		}
	}

	public List<ArcPtoT> getInLinks() {
		return inLinks;
	}

	public List<ArcTtoP> getOutLinks() {
		return outLinks;
	}

}
