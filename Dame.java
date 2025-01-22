public class Dame extends Piece {
    public Dame(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean deplacementValide(Case depart, Case destination, Plateau plateau) {
        // Implémentation des règles de déplacement pour une dame
        int dx = Math.abs(destination.getX() - depart.getX());
        int dy = Math.abs(destination.getY() - depart.getY());
        return dx == dy; // Déplacement en ligne diagonale
    }
}
