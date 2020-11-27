# MAPDFilRouge
Notre code a été intégré dans le package : org.pneditor.petrinet.models.binome03.
Des adapteurs ont été rédigés dans : org.pneditor.petrinet.adapters.binome03.

Tests :
- Pour lancer le programme de test : lancer la classe Test.java. 
- Pour lancer les test unitaires, des classes de tests contenant un test par méthode sont disponibles dans le dossier tests. Il suffit de faire clic droit sur    un     fichier dont le nom se termine par "Test", et sélectionner "run as JUnit test", la bibliothèque JUnit étant inclue dans le projet.
- Attention : les tests ne concernent que notre code et donc pas la partie graphique ou les liens avec celle-ci.
    
Lancement du programme :
    - Le modèle par défaut est le notre, aucune modification n'est nécessaire à ce sujet.
    - La classe à lancer est org.pneditor.editor.Main.
    
Différences avec la conception :
- Il y a quelques différences entre la conception et notre implémentation :
  * nous avons dû programmer des getters supplémentaires (notamment pour les paramètres de PetriNet) pour les vérifications des tests. Ils pourront peut-être être supprimés lorsque nous aurons programmé des tests unitaires pour chaque fonction
  * nous avons ajouté des getters pour les listes Transition.outLinks, Transition.inLinks et Place.links. Lorsqu'une place ou une transition est supprimé depuis le PetriNet, il faut supprimer tous les arcs concernés de PetriNet.arcsList et c'est là qu'ils sont utilisés

Changement dans l'implémentation :
- des getters et setters ont été ajoutés pour les valeurs des arcs non videurs et non zéros.
