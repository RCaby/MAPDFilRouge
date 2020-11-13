package nodes;

import java.util.ArrayList;

import arcs.Arc;
import arcs.ArcPtoT;
import arcs.ArcTtoP;

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
		inLinks = new ArrayList<ArcPtoT>();
		outLinks = new ArrayList<ArcTtoP>();
	}

	/**
	 * Méthode déterminant si la transition est tirable.
	 * 
	 * ((Test unitaire : tester avec un false puis aucun false, avec plusieurs true))
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
	 * Méthode gérant le tirage en "déplaçant" les jetons.
	 * 
	 * ((Test unitaire : vérifier que les places en amont ont moins de jetons
	 * et les places en aval plus de jetons))
	 */
	public void step() {
		for (Arc arc : inLinks) {
			arc.moveTokens();
		}
		for (Arc arc : outLinks) {
			arc.moveTokens();
		}
	}

	public ArrayList<ArcPtoT> getInLinks() {
		return inLinks;
	}

	public ArrayList<ArcTtoP> getOutLinks() {
		return outLinks;
	}

}
