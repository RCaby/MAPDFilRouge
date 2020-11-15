package tests.arcs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import petriNet.PetriNet;

public class CleanerArcTest {

    @Test
    public void isWorkingTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        net.addCleanerArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().get(0).isWorking());

        net = new PetriNet();
        net.addPlace(0);
        net.addTransition();
        net.addCleanerArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        Assertions.assertFalse(net.getTransitionsList().get(0).getInLinks().get(0).isWorking());

    }

    @Test
    public void moveTokensTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        net.addCleanerArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.getTransitionsList().get(0).step();
        Assertions.assertTrue(net.getPlacesList().get(0).getTokensNumber() == 0);

    }
}
