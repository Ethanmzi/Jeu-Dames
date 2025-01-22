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
                JButton caseButton = new JButton();
                caseButton.setBackground((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                caseButton.setEnabled((i + j) % 2 != 0); // Désactiver les cases claires
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
}
