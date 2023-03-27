package presentation;

import javax.swing.*;
import java.awt.*;

public class GererInventaire_0 extends JFrame {
    // components
    // JPanels
    // panelA = [titre : north + panelB : center]
    private final JPanel panelA = new JPanel(new BorderLayout(10, 10));
    // panelB = [stockIn : 1 + stockOut : 2]
    private final JPanel panelB = new JPanel(new GridLayout(2, 1, 10, 10));
    // panelC = [retour : 1]
    private final JPanel panelC = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    // JLabels
    private final JLabel titre = new JLabel("Gerer Inventaire");
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("src\\images\\back.png");
    private ImageIcon stockInIcon = new ImageIcon("src\\images\\stocker.png");
    private ImageIcon stockOutIcon = new ImageIcon("src\\images\\destocker.png");
    // JButtons
    private final JButton retour = new JButton();
    private final JButton stockIn = new JButton("Stocker");
    private final JButton stockOut = new JButton("Destocker");

    public GererInventaire_0() {
        // titre:
        super("GererInventaire");

        // changing the Background of the JFrame:
        this.getContentPane().setBackground(Color.WHITE);
        panelA.setBackground(Color.WHITE);
        panelB.setBackground(Color.WHITE);
        panelC.setBackground(Color.WHITE);

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

        // adding components to the panelC:
        panelC.add(retour);

        // adding components to the panelB:
        panelB.add(stockIn);
        panelB.add(stockOut);

        // adding components to the panelA:
        panelA.add(titre, BorderLayout.NORTH);
        panelA.add(panelB);

        // setting borders
        panelB.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        20,
                        500
                )
        );

        panelA.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        200,
                        20,
                        300
                )
        );

        // adding components to the JFrame:
        this.add(panelC, BorderLayout.NORTH);
        this.add(panelA);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GererInventaire_0();
    }
}
