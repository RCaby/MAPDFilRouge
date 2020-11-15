# MAPDFilRouge

  
Pour lancer le programme de test : lancer la classe Test.java.
Pour lancer les test unitaires, des classes de tests contenant un test par méthode sont disponibles dans le dossier tests.

Il y a quelques différences entre la conception et notre implémentation :
* nous avons dû programmer des getters supplémentaires (notamment pour les paramètres de PetriNet) pour les vérifications des tests. Ils pourront peut-être être supprimés lorsque nous aurons programmé des tests unitaires pour chaque fonction
* nous avons ajouté des getters pour les listes Transition.outLinks, Transition.inLinks et Place.links. Lorsqu'une place ou une transition est supprimé depuis le PetriNet, il faut supprimer tous les arcs concernés de PetriNet.arcsList et c'est là qu'ils sont utilisés
