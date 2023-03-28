package presentation;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin extends JFrame {
    // SimpleDateFormat
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Date date = new Date();
    // components
    // ImageIcons
    private ImageIcon settingsIcon = new ImageIcon("src\\images\\settings.png");
    private ImageIcon userIcon = new ImageIcon("src\\images\\user.png");
    private ImageIcon ajouterIcon = new ImageIcon("src\\images\\ajouter.png");
    private ImageIcon gererInventaireIcon = new ImageIcon("src\\images\\gererInventaire.png");
    // JButtons
    private final JButton paramtres = new JButton("parametres");
    private final JButton ajoutProduit = new JButton("ajout produit");
    private final JButton gererUtilisateur = new JButton("gerer utilisateur");
    private final JButton gererInventaire = new JButton("gerer inventaire");
    // JLabels
    private final JLabel tableauDeBord = new JLabel("tableau de bord");
    private final JLabel time = new JLabel(dateFormat.format(date));

    public Admin() {
        // titre:
        super("Admin");

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
        userIcon = Utils.resizeImageIcon(userIcon, 100, 100);
        ajouterIcon = Utils.resizeImageIcon(ajouterIcon, 100, 100);
        gererInventaireIcon = Utils.resizeImageIcon(gererInventaireIcon, 100, 100);

        // adding the ImageIcons to the JButtons:
        paramtres.setIcon(settingsIcon);
        gererUtilisateur.setIcon(userIcon);
        ajoutProduit.setIcon(ajouterIcon);
        gererInventaire.setIcon(gererInventaireIcon);

        // setting focusable for the JButtons
        paramtres.setFocusable(false);
        gererUtilisateur.setFocusable(false);
        gererInventaire.setFocusable(false);
        ajoutProduit.setFocusable(false);

        //  making the borders and the background transparent for the paramtres JButton:
        paramtres.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        paramtres.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // setting the vertical and horizontal text positions for the paramtres JButton:
        paramtres.setVerticalTextPosition(SwingConstants.BOTTOM);
        paramtres.setHorizontalTextPosition(SwingConstants.CENTER);

        // setting the vertical and horizontal text positions for the gererUtilisateur JButton:
        gererUtilisateur.setVerticalTextPosition(SwingConstants.BOTTOM);
        gererUtilisateur.setHorizontalTextPosition(SwingConstants.CENTER);

        // setting the vertical and horizontal text positions for the ajoutProduit JButton:
        ajoutProduit.setVerticalTextPosition(SwingConstants.BOTTOM);
        ajoutProduit.setHorizontalTextPosition(SwingConstants.CENTER);

        // setting the vertical and horizontal text positions for the gererInventaire JButton:
        gererInventaire.setVerticalTextPosition(SwingConstants.BOTTOM);
        gererInventaire.setHorizontalTextPosition(SwingConstants.CENTER);

        // setting the fonts + colors:
        //fonts
        time.setFont(new Font(null, Font.PLAIN, 25));
        paramtres.setFont(new Font(null, Font.PLAIN, 20));
        ajoutProduit.setFont(new Font(null, Font.PLAIN, 20));
        gererUtilisateur.setFont(new Font(null, Font.PLAIN, 20));
        gererInventaire.setFont(new Font(null, Font.PLAIN, 20));
        tableauDeBord.setFont(new Font(null, Font.PLAIN, 50));

        // colors (Foreground + Background)
        // Foreground
        time.setForeground(Color.WHITE);
        paramtres.setForeground(Color.WHITE);
        tableauDeBord.setForeground(Color.WHITE);
        ajoutProduit.setForeground(Color.WHITE);
        gererUtilisateur.setForeground(Color.WHITE);
        gererInventaire.setForeground(Color.WHITE);

        // Background
        paramtres.setBackground(new Color(80, 80, 80));
        ajoutProduit.setBackground(new Color(160, 0, 150));
        gererUtilisateur.setBackground(new Color(0, 0, 240));
        gererInventaire.setBackground(new Color(0, 180, 0));

        // adding the ActionListener to the JButtons:
        paramtres.addActionListener(e -> {
            new Parametrage_0(true);
            dispose();
        });
        ajoutProduit.addActionListener(e -> {
            new AjoutProduit();
            dispose();
        });
        gererUtilisateur.addActionListener(e -> {
            new GererUtilisateur();
            dispose();
        });
        gererInventaire.addActionListener(e -> {
            new GererInventaire_0();
            dispose();
        });

        // setting the bounds of the components:
        time.setBounds(
                xSize - 140,
                (ySize - taskBarSize) - 150,
                100,
                80
        );
        tableauDeBord.setBounds(
                (xSize / 2) - 570,
                120,
                400,
                80
        );
        paramtres.setBounds(
                xSize - 160,
                40,
                120,
                80
        );
        gererUtilisateur.setBounds(
                (xSize / 2) - 570,
                200,
                200,
                350
        );
        ajoutProduit.setBounds(
                (xSize / 2) - 350,
                200,
                400,
                350
        );
        gererInventaire.setBounds(
                gererUtilisateur.getX(),
                gererUtilisateur.getY() + gererUtilisateur.getHeight() + 20,
                gererUtilisateur.getWidth() + ajoutProduit.getWidth() + 20,
                150
        );

        // updating the date using a Timer
        javax.swing.Timer timer = new javax.swing.Timer(0, e -> {
            date.setTime(System.currentTimeMillis());
            time.setText(dateFormat.format(date));
        });

        timer.start();

        // adding components to the JFrame:
        this.add(time);
        this.add(tableauDeBord);
        this.add(paramtres);
        this.add(ajoutProduit);
        this.add(gererUtilisateur);
        this.add(gererInventaire);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }
}
