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
        // Sélectionner une pièce
            if (!clicCase.estVide() && clicCase.getPiece().estBlanc() == jeu.estTourBlanc()) {
            caseSelectionnee = clicCase;
            boutons[x][y].setBackground(Color.BLUE); // Mettre en évidence la sélection
            }
        } else {
        // Déplacer la pièce
            if (clicCase.estVide() && mouvementValide(caseSelectionnee, clicCase)) {
                Piece piece = caseSelectionnee.getPiece();
                caseSelectionnee.setPiece(null);
                clicCase.setPiece(piece);

            // Changer de joueur
                jeu.changerTour();
            }
            // Réinitialiser la sélection
            reinitialiserSelection();
        }
        mettreAJourPieces();
    }

    
    private boolean mouvementValide(Case depart, Case arrivee) {
        int dx = Math.abs(depart.getX() - arrivee.getX());
        int dy = Math.abs(depart.getY() - arrivee.getY());

        // Vérifie si le déplacement est d'une case en diagonale
        if (dx == 1 && dy == 1) {
            return true;
        }
        // Ajouter la logique pour capturer une pièce (prendre en compte les cases intermédiaires)
        // Exemple : dx == 2 && dy == 2 pour une capture

        return false;
    }
}
