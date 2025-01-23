public class Plateau {
    private Case[][] cases;

    public Plateau() {
        cases = new Case[8][8];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) { // Placer des pions uniquement sur les cases sombres
                    if (i < 3) {
                        plateau.getCase(i, j).setPiece(new Pion(Couleur.NOIR)); // Les pions noirs sur les 3 premières lignes
                    } else if (i > 4) {
                        plateau.getCase(i, j).setPiece(new Pion(Couleur.BLANC)); // Les pions blancs sur les 3 dernières lignes
                    }
                }
            }
        }
    }

    public Case getCase(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) return cases[x][y];
        return null;
    }
}