import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique {
    private Jeu jeu;
    private Plateau plateau;

    public InterfaceGraphique(Jeu jeu, Plateau plateau) {
        this.jeu = jeu;
        this.plateau = plateau;
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
                panelPlateau.add(caseButton);
            }
        }
        frame.add(panelPlateau);
        frame.setVisible(true);
    }
}
