public class Jeu {
    private Plateau plateau;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurActuel;

    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        Jeu jeu = new Jeu();
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique(jeu, plateau);

        interfaceGraphique.afficher();
    }


    private void initialiser() {
        plateau = new Plateau();
        joueur1 = new Joueur("Joueur 1", true); // Couleur blanche
        joueur2 = new Joueur("Joueur 2", false); // Couleur noire
        joueurActuel = joueur1;
    }

    public void lancer() {
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique(this, plateau);
        interfaceGraphique.afficher();
    }

    public void changerJoueur() {
        joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }
    
    private boolean tourBlanc = true; // Le joueur blanc commence

    public boolean estTourBlanc() {
        return tourBlanc;
    }

    public void changerTour() {
        joueurActif = (joueurActif == Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
        // Met à jour l'affichage
        interfaceGraphique.mettreAJourTour(joueurActif);
    }

}
