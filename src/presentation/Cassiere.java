package presentation;

import javax.swing.*;
import java.awt.*;

public class Cassiere extends JFrame {
    // components
    // ImageIcons
    private ImageIcon settingsIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\settings.png");
    // JButtons
    private final JButton paramtres = new JButton("parametres");
    // JLabels

    public Cassiere() {
        // titre:
        super("Cassiere");

        // setting the Layout Manager for the JFrame
        this.setLayout(null);

        // changing the Background of the JFrame
        this.getContentPane().setBackground(new Color(80, 80, 80));

        // toolkit --> getting screen size (xSize, ySize) and tasBarSize:
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        Insets screenMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = screenMax.bottom;

        // resizing the icons:
        settingsIcon = Utils.resizeImageIcon(settingsIcon, 50, 50);

        // adding the ImageIcons to the JButtons:
        paramtres.setIcon(settingsIcon);

        // setting focusable for the JButtons
        paramtres.setFocusable(false);

        //  making the borders and the background transparent for the paramtres JButton:
        paramtres.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        paramtres.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // setting the vertical and horizontal text positions for the paramtres JButton:
        paramtres.setVerticalTextPosition(SwingConstants.BOTTOM);
        paramtres.setHorizontalTextPosition(SwingConstants.CENTER);

        // setting the fonts + colors:
        //fonts
        paramtres.setFont(new Font(null, Font.PLAIN, 20));

        // colors (Foreground + Background)
        // Foreground
        paramtres.setForeground(Color.WHITE);

        // Background
        paramtres.setBackground(new Color(80, 80, 80));

        // adding the ActionListener to the JButtons:
        paramtres.addActionListener(e -> {
            new Parametrage(false);
            dispose();
        });

        // setting the bounds of the components:
        paramtres.setBounds(
                xSize - 160,
                40,
                120,
                80
        );

        // adding components to the JFrame:
        this.add(paramtres);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Cassiere();
    }
}
