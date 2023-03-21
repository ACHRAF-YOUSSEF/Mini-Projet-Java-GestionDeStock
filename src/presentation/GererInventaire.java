package presentation;

import javax.swing.*;
import java.awt.*;

public class GererInventaire extends JFrame {
    // components
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\back.png");
    // JButtons
    private final JButton retour = new JButton();

    public GererInventaire() {
        // titre:
        super("GererInventaire");

        // setting the Layout Manager for the JFrame
        this.setLayout(null);

        // changing the Background of the JFrame:
        this.getContentPane().setBackground(Color.WHITE);

        // toolkit --> getting screen size (xSize, ySize) and tasBarSize:
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        Insets screenMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = screenMax.bottom;

        // resizing the icons:
        backIcon = Utils.resizeImageIcon(backIcon, 100, 80);

        // adding the ImageIcons to the JButtons:
        retour.setIcon(backIcon);

        // setting focusable for the retour JButton + making the borders and the background transparent:
        retour.setFocusable(false);
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            new Admin();
            dispose();
        });

        // setting the fonts + colors:
        //fonts:

        // colors (Foreground + Background):
        // Foreground:

        // Background:
        retour.setBackground(Color.WHITE);

        // setting the bounds of the retour JButton:
        retour.setBounds(40, 80, 100, 60);

        // adding components to the JFrame:
        this.add(retour);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GererInventaire();
    }
}
