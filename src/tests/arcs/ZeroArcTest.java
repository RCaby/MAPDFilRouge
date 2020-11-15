package tests.arcs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import petriNet.PetriNet;

public class ZeroArcTest {

    @Test
    public void isWorkingTest() {
        // Premier cas, l'arc zéro n'est pas actif.
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        net.addZeroArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        Assertions.assertFalse(net.getTransitionsList().get(0).getInLinks().get(0).isWorking());

        // Second cas, l'arc zéro est actf.
        net = new PetriNet();
        net.addPlace(0);
        net.addTransition();
        net.addZeroArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().get(0).isWorking());
    }

}
