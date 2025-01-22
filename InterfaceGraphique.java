import javax.swing.*;
import java.awt.*;


public class InterfaceGraphique {
    private Jeu jeu;
    private Plateau plateau;
    private JButton[][] boutons;
    private ImageIcon iconPionBlanc;
    private ImageIcon iconPionNoir;

    public InterfaceGraphique(Jeu jeu, Plateau plateau) {
        this.jeu = jeu;
        this.plateau = plateau;
        this.boutons = new JButton[8][8];
        // Chargement des images
        iconPionBlanc = new ImageIcon(getClass().getResource("/resources/PionBlanc.png"));
        iconPionNoir = new ImageIcon(getClass().getResource("/resources/PionNoir.png"));
    }

    public InterfaceGraphique(Plateau plateau, Jeu jeu) {
        this.plateau = plateau;
        this.jeu = jeu;
        this.boutons = new JButton[8][8];

        frame = new JFrame("Jeu de Dames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Création du label pour afficher le tour du joueur
        labelTour = new JLabel("Tour du joueur : Blanc", SwingConstants.CENTER);
        frame.add(labelTour, BorderLayout.NORTH); // Ajoute le label en haut de la fenêtre

        // Création du plateau de jeu
        JPanel panelPlateau = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boutons[i][j] = new JButton();
                boutons[i][j].setBackground((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                boutons[i][j].setOpaque(true);
                boutons[i][j].setBorderPainted(false);

                int x = i;
                int y = j;
                boutons[i][j].addActionListener(e -> gererClic(x, y));

                panelPlateau.add(boutons[i][j]);
            }
        }
        frame.add(panelPlateau, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        mettreAJourPieces();
    }


    private void mettreAJourPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case currentCase = plateau.getCase(i, j);
                if (!currentCase.estVide()) {
                    Piece piece = currentCase.getPiece();
                    if (piece instanceof Pion) {
                        // Affiche l'icône en fonction de la couleur de la pièce
                        if (piece.estBlanc()) {
                            boutons[i][j].setIcon(iconPionBlanc);
                        } else {
                            boutons[i][j].setIcon(iconPionNoir);
                        }
                    }
                } else {
                    boutons[i][j].setIcon(null); // Pas d'icône pour une case vide
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
                System.out.println("Selection de la piece a la case : (" + x + ", " + y + ")");
                caseSelectionnee = clicCase;
                boutons[x][y].setBackground(Color.BLUE); // Met en surbrillance la sélection
            }
        } else {
            // Déplacement ou annulation de la sélection
            if (clicCase.estVide() && mouvementValide(caseSelectionnee, clicCase)) {
                // Déplacement de la pièce
                System.out.println("Deplacement de la piece de (" + caseSelectionnee.getX() + ", " + caseSelectionnee.getY() + ") a (" + x + ", " + y + ")");
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


    private void reinitialiserSelection() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) { // Remettre les cases noires à leur couleur d'origine
                    boutons[i][j].setBackground(Color.DARK_GRAY);
                }
            }
        }
        caseSelectionnee = null;
    }
}
