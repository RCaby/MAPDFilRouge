package tests;

import org.pneditor.petrinet.models.binome03.petriNet.PetriNet;

public class Test {

	public static void main(String[] args) {
		boolean test = true;
		PetriNet reseau = new PetriNet();

		// Création des transitions
		reseau.addTransition();
		reseau.addTransition();

		// Création des places
		reseau.addPlace(1);
		reseau.addPlace(2);
		reseau.addPlace(1);
		reseau.addPlace(0);
		reseau.addPlace(0);

		// Créations des arcs
		reseau.addRegularArc(reseau.getPlacesList().get(0), reseau.getTransitionsList().get(0), 1);
		reseau.addArcTtoP(reseau.getPlacesList().get(1), reseau.getTransitionsList().get(0), 1);
		reseau.addZeroArc(reseau.getPlacesList().get(1), reseau.getTransitionsList().get(0));
		reseau.addRegularArc(reseau.getPlacesList().get(1), reseau.getTransitionsList().get(1), 2);
		reseau.addCleanerArc(reseau.getPlacesList().get(2), reseau.getTransitionsList().get(1));
		reseau.addArcTtoP(reseau.getPlacesList().get(3), reseau.getTransitionsList().get(1), 3);
		reseau.addCleanerArc(reseau.getPlacesList().get(3), reseau.getTransitionsList().get(0));
		reseau.addArcTtoP(reseau.getPlacesList().get(4), reseau.getTransitionsList().get(0), 5);

		// Test de validité
		System.out.println("Tests arcs");
		test = test && reseau.getTransitionsList().get(0).getInLinks().get(0).isWorking() == true;
		test = test && reseau.getTransitionsList().get(0).getInLinks().get(1).isWorking() == false;
		test = test && reseau.getTransitionsList().get(0).getInLinks().get(1).isWorking() == false;
		System.out.println("Test de la fonction isWorking des arcs : " + test);

		System.out.println("Tests transitions");
		test = test && reseau.getTransitionsList().get(0).isPickable() == false;
		test = test && reseau.getTransitionsList().get(1).isPickable() == true;
		System.out.println("Test de la fonction isPickable des transitions : " + test);

		// Etape non valide puis valide et re-tests de validité sur les transitions
		System.out.println("Etapes et nouveaux tests");
		reseau.chooseTransition(reseau.getTransitionsList().get(0));
		reseau.chooseTransition(reseau.getTransitionsList().get(1));
		test = test && reseau.getTransitionsList().get(0).isPickable() == true;
		test = test && reseau.getTransitionsList().get(1).isPickable() == false;
		System.out.println("Test des tirages et des nouveaux isPickable : " + test);

	}

}
