package presentation;

import javax.swing.*;
import java.awt.*;

public class GererInventaire extends JFrame {
    // components
    // JLabels
    private final JLabel titre = new JLabel("Gerer Inventaire");
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\back.png");
    private ImageIcon stockInIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\stocker.png");
    private ImageIcon stockOutIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\destocker.png");
    // JButtons
    private final JButton retour = new JButton();
    private final JButton stockIn = new JButton("Stocker");
    private final JButton stockOut = new JButton("Destocker");

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
        stockInIcon = Utils.resizeImageIcon(stockInIcon, 100, 100);
        stockOutIcon = Utils.resizeImageIcon(stockOutIcon, 100, 100);

        // adding the ImageIcons to the JButtons:
        retour.setIcon(backIcon);
        stockIn.setIcon(stockInIcon);
        stockOut.setIcon(stockOutIcon);

        // setting focusable for the JButtons
        retour.setFocusable(false);
        stockIn.setFocusable(false);
        stockOut.setFocusable(false);

        //  making the borders and the background transparent for the retour JButton:
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // setting the vertical and horizontal text positions for the stockIn JButton:
        stockIn.setVerticalTextPosition(SwingConstants.BOTTOM);
        stockIn.setHorizontalTextPosition(SwingConstants.CENTER);

        // setting the vertical and horizontal text positions for the stockOut JButton:
        stockOut.setVerticalTextPosition(SwingConstants.BOTTOM);
        stockOut.setHorizontalTextPosition(SwingConstants.CENTER);

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            new Admin();
            dispose();
        });
        stockIn.addActionListener(e -> {
            new Stocker();
            dispose();
        });
        stockOut.addActionListener(e -> {
            new Destocker();
            dispose();
        });

        // setting the fonts + colors:
        //fonts:
        titre.setFont(new Font(null, Font.PLAIN, 50));
        stockIn.setFont(new Font(null, Font.PLAIN, 20));
        stockOut.setFont(new Font(null, Font.PLAIN, 20));

        // colors (Foreground + Background):
        // Foreground:
        titre.setForeground(Color.BLACK);

        // Background:
        retour.setBackground(Color.WHITE);

        // setting the bounds of the retour JButton:
        retour.setBounds(40,
                80,
                100,
                60
        );
        titre.setBounds(
                (xSize / 2) - 570,
                120,
                400,
                80
        );
        stockIn.setBounds(
                titre.getX(),
                titre.getY() + titre.getHeight() + 20,
                titre.getWidth(),
                200
        );
        stockOut.setBounds(
                titre.getX(),
                stockIn.getY() + stockIn.getHeight() + 20,
                titre.getWidth(),
                200
        );

        // adding components to the JFrame:
        this.add(retour);
        this.add(titre);
        this.add(stockIn);
        this.add(stockOut);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }
}
