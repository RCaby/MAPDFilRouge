package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pneditor.petrinet.models.binome03.arcs.ArcTtoP;
import org.pneditor.petrinet.models.binome03.arcs.CleanerArc;
import org.pneditor.petrinet.models.binome03.arcs.RegularArc;
import org.pneditor.petrinet.models.binome03.arcs.ZeroArc;
import org.pneditor.petrinet.models.binome03.petriNet.PetriNet;

public class PetriNetTest {

    @Test
    public void addTransitionTest() {
        PetriNet net = new PetriNet();
        net.addTransition();
        Assertions.assertTrue(net.getTransitionsList().size() == 1);
    }

    @Test
    public void addPlaceTest() {
        PetriNet net = new PetriNet();
        int before = net.getPlacesList().size(); // Aucune raison que ce ne soit pas 0.
        net.addPlace(3);
        int after = net.getPlacesList().size();
        Assertions.assertTrue(after - before == 1);
        Assertions.assertTrue(net.getPlacesList().get(0).getTokensNumber() == 3);

    }

    @Test
    public void removePlaceTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addPlace(3);
        net.addTransition();
        net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 1);
        int lengthArcListBefore = net.getArcsList().size();
        int lengthTransitionsListBefore = net.getTransitionsList().size();
        int lengthPlacesListBefore = net.getPlacesList().size();
        int lengthListLinksInBefore = net.getTransitionsList().get(0).getInLinks().size();
        net.removePlace(net.getPlacesList().get(0));
        int lengthArcListAfter = net.getArcsList().size();
        int lengthTransitionsListAfter = net.getTransitionsList().size();
        int lengthPlacesListAfter = net.getPlacesList().size();
        int lengthListLinksInAfter = net.getTransitionsList().get(0).getInLinks().size();
        Assertions.assertTrue(lengthArcListBefore - lengthArcListAfter == 1);
        Assertions.assertTrue(lengthTransitionsListBefore == lengthTransitionsListAfter);
        Assertions.assertTrue(lengthPlacesListBefore - lengthPlacesListAfter == 1);
        Assertions.assertTrue(net.getPlacesList().get(0).getTokensNumber() == 3);
        Assertions.assertTrue(lengthListLinksInBefore - lengthListLinksInAfter == 1);
        Assertions.assertTrue(
                net.getTransitionsList().get(0).getInLinks().get(0).getLinkedPlace().getTokensNumber() == 3);

    }

    @Test
    public void addArcTtoPTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        Assertions.assertEquals(0, net.getArcsList().size());
        net.addArcTtoP(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        Assertions.assertEquals(1, net.getArcsList().size());
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedPlace().equals(net.getPlacesList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedTransition().equals(net.getTransitionsList().get(0)));
        Assertions.assertTrue(net.getTransitionsList().get(0).getOutLinks().get(0).equals(net.getArcsList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0) instanceof ArcTtoP);

        boolean flag = false;
        try {
            net.addArcTtoP(net.getPlacesList().get(0), net.getTransitionsList().get(0), -1);
        } catch (IllegalArgumentException e) {
            flag = true;
        }
        Assertions.assertTrue(flag);
    }

    @Test
    public void addRegularArcTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        Assertions.assertEquals(0, net.getArcsList().size());
        net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        Assertions.assertEquals(1, net.getArcsList().size());
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedPlace().equals(net.getPlacesList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedTransition().equals(net.getTransitionsList().get(0)));
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().get(0).equals(net.getArcsList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0) instanceof RegularArc);

        boolean flag = false;
        try {
            net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), -1);
        } catch (IllegalArgumentException e) {
            flag = true;
        }
        Assertions.assertTrue(flag);
    }

    @Test
    public void addZeroArcTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        Assertions.assertEquals(0, net.getArcsList().size());
        net.addZeroArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        Assertions.assertEquals(1, net.getArcsList().size());
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedPlace().equals(net.getPlacesList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedTransition().equals(net.getTransitionsList().get(0)));
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().get(0).equals(net.getArcsList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0) instanceof ZeroArc);

    }

    @Test
    public void addCleanerArcTest() {
        PetriNet net = new PetriNet();
        net.addPlace(1);
        net.addTransition();
        Assertions.assertEquals(0, net.getArcsList().size());
        net.addCleanerArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        Assertions.assertEquals(1, net.getArcsList().size());
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedPlace().equals(net.getPlacesList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0).getLinkedTransition().equals(net.getTransitionsList().get(0)));
        Assertions.assertTrue(net.getTransitionsList().get(0).getInLinks().get(0).equals(net.getArcsList().get(0)));
        Assertions.assertTrue(net.getArcsList().get(0) instanceof CleanerArc);
    }

    /**
     * Méthode retirant un arc du PetriNet. La méthode élimine toute référence à cet
     * arc dans ses voisins (place et transition) avant de le supprimer.
     * 
     * ((Test unitaire : longueur de arcsList et inLinks+outLinks + non-présence de
     * l'élément dans ces listes))
     * 
     * @param arc - L'arc à retirer.
     */
    public void removeArcTest() {
        PetriNet net = new PetriNet();

        net.addPlace(0);
        net.addPlace(1);
        net.addPlace(2);
        net.addPlace(3);
        net.addTransition();

        net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), 1);
        net.addZeroArc(net.getPlacesList().get(1), net.getTransitionsList().get(0));
        net.addCleanerArc(net.getPlacesList().get(2), net.getTransitionsList().get(0));
        net.addArcTtoP(net.getPlacesList().get(3), net.getTransitionsList().get(0), 1);

        // Cas 1 : Un regularArc
        int lenghtArcsListBeforeRegular = net.getTransitionsList().get(0).getInLinks().size();
        Assertions.assertTrue(net.getPlacesList().get(0).getLinks().size() == 1);
        net.removeArc(net.getPlacesList().get(0).getLinks().get(0));
        int lenghtArcsListAfterRegular = net.getTransitionsList().get(0).getInLinks().size();
        Assertions.assertTrue(lenghtArcsListBeforeRegular - lenghtArcsListAfterRegular == 1);

        // Cas 2 : Un ZeroArc
        int lenghtArcsListBeforeZero = net.getTransitionsList().get(0).getInLinks().size();
        Assertions.assertTrue(net.getPlacesList().get(1).getLinks().size() == 1);
        net.removeArc(net.getPlacesList().get(1).getLinks().get(0));
        int lenghtArcsListAfterZero = net.getTransitionsList().get(0).getInLinks().size();
        Assertions.assertTrue(lenghtArcsListBeforeZero - lenghtArcsListAfterZero == 1);

        // Cas 3 : Un CleanerArc
        int lenghtArcsListBeforeCleaner = net.getTransitionsList().get(0).getInLinks().size();
        Assertions.assertTrue(net.getPlacesList().get(2).getLinks().size() == 1);
        net.removeArc(net.getPlacesList().get(2).getLinks().get(0));
        int lenghtArcsListAfterCleaner = net.getTransitionsList().get(0).getInLinks().size();
        Assertions.assertTrue(lenghtArcsListBeforeCleaner - lenghtArcsListAfterCleaner == 1);

        // Dernier cas : Un ArcTtoP
        int lenghtArcsListBeforeTtoP = net.getTransitionsList().get(0).getOutLinks().size();
        Assertions.assertTrue(net.getPlacesList().get(3).getLinks().size() == 1);
        net.removeArc(net.getPlacesList().get(3).getLinks().get(0));
        int lenghtArcsListAfterTtoP = net.getTransitionsList().get(0).getOutLinks().size();
        Assertions.assertTrue(lenghtArcsListBeforeTtoP - lenghtArcsListAfterTtoP == 1);

    }

    @Test
    public void removeTransitionTest() {
        PetriNet net = new PetriNet();
        net.addTransition();
        net.removeTransition(net.getTransitionsList().get(0));
        Assertions.assertTrue(net.getTransitionsList().isEmpty());

        net.addTransition();
        Assertions.assertTrue(net.getTransitionsList().size() == 1);
        net.addPlace(3);
        net.addPlace(3);
        net.addArcTtoP(net.getPlacesList().get(1), net.getTransitionsList().get(0), 2);
        net.addRegularArc(net.getPlacesList().get(0), net.getTransitionsList().get(0), 3);

        net.removeTransition(net.getTransitionsList().get(0));
        Assertions.assertTrue(net.getArcsList().isEmpty());
        Assertions.assertTrue(net.getPlacesList().size() == 2);
        Assertions.assertTrue(net.getTransitionsList().isEmpty());
    }

    /**
     * Méthode permettant de choisir une transition dans le PetriNet, et de lance
     * son tirage si possible.
     * 
     * ((Test unitaire : lancer pour une transition tirable et une non-tirable et
     * vérifier le déplacement des jetons que dans le premier cas))
     * 
     * @param transition - La transition à tirer.
     */
    @Test
    public void chooseTransitionTest() {
        // Cas 1, transition tirable
        PetriNet net = new PetriNet();
        net.addPlace(0);
        net.addPlace(1);
        net.addPlace(2);
        net.addTransition();
        net.addZeroArc(net.getPlacesList().get(0), net.getTransitionsList().get(0));
        net.addRegularArc(net.getPlacesList().get(1), net.getTransitionsList().get(0), 1);
        net.addArcTtoP(net.getPlacesList().get(2), net.getTransitionsList().get(0), 3);
        Assertions.assertTrue(net.getTransitionsList().get(0).isPickable());
        int nbJetonsPlace0Before = net.getPlacesList().get(0).getTokensNumber();
        int nbJetonsPlace1Before = net.getPlacesList().get(1).getTokensNumber();
        net.chooseTransition(net.getTransitionsList().get(0));
        Assertions.assertTrue(nbJetonsPlace0Before == net.getPlacesList().get(0).getTokensNumber());
        Assertions.assertTrue(nbJetonsPlace1Before - net.getPlacesList().get(1).getTokensNumber() == 1);
        Assertions.assertTrue(net.getPlacesList().get(2).getTokensNumber() == 5);

    }

}
