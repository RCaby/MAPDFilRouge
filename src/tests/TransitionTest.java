package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nodes.Place;
import petriNet.PetriNet;

/**
 * 
 * @author R19caby, T19Borde
 *
 */
public class TransitionTest {

    @Test
    public void stepTest() {
        // Création du réseau de test
        PetriNet net = new PetriNet();

        net.addTransition();
        net.addPlace(2);
        net.addPlace(3);
        net.addPlace(0);
        net.addPlace(1);
        for (Place place : net.getPlacesList()) {
            if (place.getTokensNumber() == 2) {
                net.addRegularArc(place, net.getTransitionsList().get(0), 1);
            }
            if (place.getTokensNumber() == 3) {
                net.addRegularArc(place, net.getTransitionsList().get(0), 2);
            }
            if (place.getTokensNumber() == 0) {
                net.addArcTtoP(place, net.getTransitionsList().get(0), 1);
            }
            if (place.getTokensNumber() == 1) {
                net.addArcTtoP(place, net.getTransitionsList().get(0), 2);
            }
        }
        // Le réseau est prêt.
        int tokensNumber1Before = net.getPlacesList().get(0).getTokensNumber();
        int tokensNumber2Before = net.getPlacesList().get(1).getTokensNumber();
        int tokensNumber3Before = net.getPlacesList().get(2).getTokensNumber();
        int tokensNumber4Before = net.getPlacesList().get(3).getTokensNumber();

        net.getTransitionsList().get(0).step();

        int tokensNumber1After = net.getPlacesList().get(0).getTokensNumber();
        int tokensNumber2After = net.getPlacesList().get(1).getTokensNumber();
        int tokensNumber3After = net.getPlacesList().get(2).getTokensNumber();
        int tokensNumber4After = net.getPlacesList().get(3).getTokensNumber();

        Assertions.assertTrue(tokensNumber1Before > tokensNumber1After);
        Assertions.assertTrue(tokensNumber2Before > tokensNumber2After);
        Assertions.assertTrue(tokensNumber3Before < tokensNumber3After);
        Assertions.assertTrue(tokensNumber4Before < tokensNumber4After);

    }

    @Test
    public void isPickableTest() {
        // Création du réseau de test
        PetriNet net = new PetriNet();

        // Premier cas : la transition est tirable, sauf pour un arc.
        net.addTransition();
        net.addPlace(2);
        net.addPlace(3);
        net.addPlace(2);
        net.addPlace(2);
        net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 100);
        net.addArcTtoP(net.getPlacesList().get(2), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(3), net.getTransitionsList().get(0), 1);
        Assertions.assertFalse(net.getTransitionsList().get(0).isPickable());

        // Second cas : la transition est tirable.
        net = new PetriNet();
        net.addTransition();
        net.addPlace(2);
        net.addPlace(3);
        net.addPlace(2);
        net.addPlace(2);
        net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(2), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(3), net.getTransitionsList().get(0), 1);
        Assertions.assertTrue(net.getTransitionsList().get(0).isPickable());

        // Troisième cas : la transition est non tirable a cause d'un arc zero.
        // Le zéro arc ne sera pas actif car la place associée n'est pas vide.
        net = new PetriNet();
        net.addTransition();
        net.addPlace(2);
        net.addPlace(3);
        net.addPlace(2);
        net.addPlace(2);
        net.addZeroArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(2), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(3), net.getTransitionsList().get(0), 1);
        Assertions.assertFalse(net.getTransitionsList().get(0).isPickable());

        // Quatrième cas : la transition est tirable avec un arc zero.
        net = new PetriNet();
        net.addTransition();
        net.addPlace(0);
        net.addPlace(3);
        net.addPlace(2);
        net.addPlace(2);
        net.addZeroArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(2), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(3), net.getTransitionsList().get(0), 1);
        Assertions.assertTrue(net.getTransitionsList().get(0).isPickable());

        // Cinquième cas : la transition est non tirable a cause d'un arc videur.
        net = new PetriNet();
        net.addTransition();
        net.addPlace(0);
        net.addPlace(3);
        net.addPlace(2);
        net.addPlace(2);
        net.addCleanerArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(2), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(3), net.getTransitionsList().get(0), 1);
        Assertions.assertFalse(net.getTransitionsList().get(0).isPickable());

        // Dernier cas : la transition est tirable avec un arc videur.
        net = new PetriNet();
        net.addTransition();
        net.addPlace(2);
        net.addPlace(3);
        net.addPlace(2);
        net.addPlace(2);
        net.addCleanerArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(2), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(3), net.getTransitionsList().get(0), 1);
        Assertions.assertTrue(net.getTransitionsList().get(0).isPickable());
    }
}
