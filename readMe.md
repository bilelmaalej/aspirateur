# Aspirateur Automatique

Ce programme permet de simuler un aspirateur automatique se déplaçant dans une grille et exécutant des instructions données.

## Configuration requise

- Java 17 ou version ultérieure

## Utilisation

1. Clonez ou téléchargez le projet.

2. Importez le projet dans votre IDE préféré.

3. Exécutez la classe `Main` pour lancer l'application.

4. La position finale de l'aspirateur sera affichée à la console.

5. Exécutez les tests presents dans la classe AspirateurAutomatiqueTest pour pouvoir tester l'application


## Contraintes

- La position initiale de l'aspirateur doit être à l'intérieur de la grille. Une exception sera levée si la position est invalide.

- Les instructions valides sont : "D" (pivoter à droite), "G" (pivoter à gauche), "A" (avancer d'une case). Une exception sera levée pour toute autre instruction invalide.

## Exemple

Dimension de la grille : x=10 y=10
Position initiale de l'aspirateur : x=5 y=5 orientation=N
Instructions : DADADADAA

Position finale : x=5 y=6 orientation=N
