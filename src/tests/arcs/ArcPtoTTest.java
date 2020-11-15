package tests.arcs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import petriNet.PetriNet;

public class ArcPtoTTest {

    @Test
    public void withdrawTest() {
        // Cas 1 : Regular
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addPlace(2);
        net.addTransition();
        net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 2);
        net.getArcsList().get(0).withdraw();
        Assertions.assertTrue(net.getPlacesList().get(0).getLinks().isEmpty());
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().size() == 1
                && net.getTransitionsList().get(0).getInLinks().get(0).getLinkedPlace().getTokensNumber() == 2);

        // Cas 2 : arc videur
        net = new PetriNet();
        net.addPlace(1);
        net.addPlace(2);
        net.addTransition();
        net.addCleanerArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.addCleanerArc(net.getPlacesList().get(1), net.getTransitionsList().get(0));
        net.getArcsList().get(0).withdraw();
        Assertions.assertTrue(net.getPlacesList().get(0).getLinks().isEmpty());
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().size() == 1
                && net.getTransitionsList().get(0).getInLinks().get(0).getLinkedPlace().getTokensNumber() == 2);

        // Cas 3 : arc zéro
        net = new PetriNet();
        net.addPlace(1);
        net.addPlace(2);
        net.addTransition();
        net.addZeroArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.addZeroArc(net.getPlacesList().get(1), net.getTransitionsList().get(0));
        net.getArcsList().get(0).withdraw();
        Assertions.assertTrue(net.getPlacesList().get(0).getLinks().isEmpty());
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().size() == 1
                && net.getTransitionsList().get(0).getInLinks().get(0).getLinkedPlace().getTokensNumber() == 2);
    }

}
