import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique {
    private Jeu jeu;
    private Plateau plateau;
    private JButton[][] boutons;

    public InterfaceGraphique(Jeu jeu, Plateau plateau) {
        this.jeu = jeu;
        this.plateau = plateau;
        this.boutons = new JButton[8][8];
    }

    public void afficher() {
        JFrame frame = new JFrame("Jeu de Dames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel panelPlateau = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int finalI = i;
                int finalJ = j;
                JButton caseButton = new JButton();
                caseButton.setBackground((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                caseButton.setEnabled((i + j) % 2 != 0); // Activer uniquement les cases noires
                caseButton.addActionListener(e -> gererClic(finalI, finalJ));
                boutons[i][j] = caseButton;
                panelPlateau.add(caseButton);
            }
        }
        frame.add(panelPlateau);
        frame.setVisible(true);

        mettreAJourPieces();
    }


    public void mettreAJourPieces() {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Case currentCase = plateau.getCase(i, j);
            Piece piece = currentCase.getPiece();
            JButton bouton = boutons[i][j];

            if (piece != null) {
                // Utilisez des cercles pour représenter les pièces
                if (piece.estBlanc()) {
                    bouton.setText("⚪"); // Pion blanc
                } else {
                    bouton.setText("⚫"); // Pion noir
                }
            } else {
                bouton.setText(""); // Case vide
                }
            }
        }
    }


    private Case caseSelectionnee = null; // Stocke la case actuellement sélectionnée


   private void gererClic(int x, int y) {
        Case clicCase = plateau.getCase(x, y);

        if (caseSelectionnee == null) {
            // Sélection d'une pièce
            if (!clicCase.estVide() && clicCase.getPiece().estBlanc() == jeu.estTourBlanc()) {
                caseSelectionnee = clicCase;
                boutons[x][y].setBackground(Color.BLUE); // Met en surbrillance la sélection
            }
        } else {
            // Déplacement ou annulation de la sélection
            if (clicCase.estVide() && mouvementValide(caseSelectionnee, clicCase)) {
                // Déplacement de la pièce
                Piece piece = caseSelectionnee.getPiece();
                caseSelectionnee.setPiece(null); // La case d'origine devient vide
                clicCase.setPiece(piece); // La nouvelle case contient la pièce

                // Changer de joueur
                jeu.changerTour();
            }
            // Réinitialisation de la sélection dans tous les cas
            reinitialiserSelection();
        }

        // Rafraîchit l'affichage
        mettreAJourPieces();
    }



    private boolean mouvementValide(Case depart, Case arrivee) {
        if (!arrivee.estVide()) {
            return false; // La case d'arrivée doit être vide
        }

        Piece piece = depart.getPiece();
        int dx = Math.abs(depart.getX() - arrivee.getX());
        int dy = Math.abs(depart.getY() - arrivee.getY());

        // Vérifie un déplacement d'une case en diagonale
        if (dx == 1 && dy == 1) {
            // Vérifie la direction du déplacement pour les pions
            if (piece instanceof Pion) {
                if (piece.estBlanc()) {
                    return arrivee.getX() < depart.getX(); // Les pions blancs montent
                } else {
                    return arrivee.getX() > depart.getX(); // Les pions noirs descendent
                }
            }
            return true; // Les dames peuvent bouger dans toutes les directions
        }

        // Ajoutez la logique pour la capture si nécessaire
        return false;
    }
}
