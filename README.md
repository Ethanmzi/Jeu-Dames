# dames-java
Jeu de dames en Java, interface Swing 
# Equipe Projet 
Ethan 
Nathan
Jsp son nom au dernier
# Cahier des charges
* Dans un premier temps, il faut créer un jeu fonctionnel dont les règles reposent sur l'utilisateur (pas de vérifications)
* On veut un plateau de jeu, des pions, des dames, des cases de différentes couleurs (voir PDF moodle)

# Règles
* le périmètre est une zone d'invincibilité (on peut pas manger et atterir en dehors du plateau)
* les pions peuvent manger les dames
* les dames peuvent se déplacer et manger comme elles le veulent, même en arrière (il faut avoir une case libre par contre) 
# Comment ça marche
Partie input/affichage :
Ca affichera le plateau et permettra à l'utilisateur de déplacer le curseur
le curseur se souvient de la dernière position séléctionnée,

on peut afficher instantanément le plateau sans rien demander au joueur, il faut faire plateau.afficher()
De plus nous avons un suivi des cases sur lequels nos pions se deplacent via le terminal lorsqu'on execute le programme
avec la commande ``java jeu``

Les coordonnées : j'utilise un système de coordonnées : un tableau d'entiers [x,y]. Attention : quand on parcourt un tableau 2D,
Y représente le numéro des lignes (un tableu 1D) et X celui des colonnes (objets contenus dans le tableau 1D Y) 
