public class Jeu {
    private Plateau plateau;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurActuel;

    public Jeu() {
        initialiser();
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

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.lancer();
    }
    
    private boolean tourBlanc = true; // Le joueur blanc commence

    public boolean estTourBlanc() {
        return tourBlanc;
    }

    public void changerTour() {
        tourBlanc = !tourBlanc;
    }
}
