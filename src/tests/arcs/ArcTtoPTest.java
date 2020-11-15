package tests.arcs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import petriNet.PetriNet;

public class ArcTtoPTest {

    @Test
    public void moveTokensTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        net.addArcTtoP(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        net.getArcsList().get(0).moveTokens();
        Assertions.assertTrue(net.getPlacesList().get(0).getTokensNumber() == 2);

    }

    @Test
    public void withdrawTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addPlace(2);
        net.addTransition();
        net.addArcTtoP(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(1), net.getTransitionsList().get(0), 2);
        net.getArcsList().get(0).withdraw();
        Assertions.assertTrue(net.getPlacesList().get(0).getLinks().isEmpty());
        Assertions.assertTrue(net.getTransitionsList().get(0).getOutLinks().size() == 1
                && net.getTransitionsList().get(0).getOutLinks().get(0).getLinkedPlace().getTokensNumber() == 2);
    }

}
