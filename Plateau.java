public class Plateau {
    private Case[][] cases;

    public Plateau() {
        cases = new Case[8][8];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j] = new Case(i, j);
                if ((i + j) % 2 == 1) { // Cases noires
                    if (i < 3) cases[i][j].setPiece(new Pion(false)); // Joueur 2
                    else if (i > 4) cases[i][j].setPiece(new Pion(true)); // Joueur 1
                }
            }
        }
    }

    public Case getCase(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) return cases[x][y];
        return null;
    }
}