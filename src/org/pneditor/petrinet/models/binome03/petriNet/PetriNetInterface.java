package org.pneditor.petrinet.models.binome03.petriNet;


import org.pneditor.petrinet.models.binome03.arcs.Arc;
import org.pneditor.petrinet.models.binome03.nodes.Place;
import org.pneditor.petrinet.models.binome03.nodes.Transition;

public interface PetriNetInterface {

	/**
	 * Fonction ajoutant une place au PetriNet. Elle n'a aucun jeton et n'est reliée
	 * à rien.
	 */
	public void addPlace(int nb);

	/**
	 * Retire une place du réseau. La méthode se charge d'éliminer tous les arcs qui
	 * lui sont reliés, avant de l'enlever de la liste des places du PetriNet.
	 * 
	 * @param place - La place à retirer.
	 */
	public void removePlace(Place place);

	/**
	 * Méthode ajoutant un arc Transition vers Place au PetriNet, en initialisant sa
	 * valeur à value.
	 * 
	 * @param place      - La place d'arrivée.
	 * @param transition - La transition de départ.
	 * @param value      - La valeur de l'arc.
	 */
	public void addArcTtoP(Place place, Transition transition, int value);

	/**
	 * Méthode ajoutant un arc allant d'une place à une transition, en initialisant
	 * sa valeur à value. C'est un arc "normal" : ni videur ni zéro.
	 * 
	 * @param place      - La place de départ.
	 * @param transition - La transition d'arrivée.
	 * @param value      - La valeur de l'arc.
	 */
	public void addRegularArc(Place place, Transition transition, int value);

	/**
	 * Méthode ajoutant un arc zéro allant d'une place à une transition.
	 * 
	 * @param place      - La place de départ.
	 * @param transition - La transition d'arrivée.
	 */
	public void addZeroArc(Place place, Transition transition);

	/**
	 * Méthode ajoutant un arc videur allant d'une place à une transition.
	 * 
	 * @param place      - La place de départ.
	 * @param transition - La transition d'arrivée.
	 */
	public void addCleanerArc(Place place, Transition transition);

	/**
	 * Méthode retirant un arc du PetriNet. La méthode élimine toute référence à cet
	 * arc dans ses voisins (place et transition) avant de le supprimer.
	 * 
	 * @param arc - L'arc à retirer.
	 */
	public void removeArc(Arc arc);

	/**
	 * Méthode ajoutant une transition au PetriNet. Elle n'est reliée à rien.
	 */
	public void addTransition();

	/**
	 * Méthode retirant une transition du réseau. La méthode se charge d'éliminer
	 * tous les arcs qui lui sont reliés avant de retirer la transition.
	 * 
	 * @param transition - La transition à retirer.
	 */
	public void removeTransition(Transition transition);

	/**
	 * Méthode permettant de choisir une transition dans le PetriNet, et de lance
	 * son tirage si possible.
	 * 
	 * @param transition - La transition à tirer.
	 */
	public void chooseTransition(Transition transition);

}
