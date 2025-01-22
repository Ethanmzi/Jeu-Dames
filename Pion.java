public class Pion extends Piece {
    public Pion(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean deplacementValide(Case depart, Case destination, Plateau plateau) {
        // Implémentation des règles de déplacement pour un pion
        int dx = Math.abs(destination.getX() - depart.getX());
        int dy = Math.abs(destination.getY() - depart.getY());
        return dx == 1 && dy == 1; // Déplacement diagonal simple
    }
}
