package presentation;

import javax.swing.*;
import java.awt.*;

public class Parametrage extends JFrame {
    // components
    // JPanels
    private final JPanel panel = new JPanel(new GridLayout(1, 1));
    // JLabels
    private final JLabel titre = new JLabel("Parametrage");
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\back.png");
    // JButtons
    private final JButton retour = new JButton();
    private final JButton logout = new JButton("se déconnecter");
    private final JButton about = new JButton("À propos des logiciels");
    private final JButton dev = new JButton("Développeur");
    // JTextArea
    private final JTextArea textArea = new JTextArea();
    // JScrollPane
    private final JScrollPane scrollPane = new JScrollPane(textArea);
    // String
    private String text;

    public Parametrage(boolean isAdmin) {
        // titre:
        super("Parametrage");

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

        // setting focusable for the JButtons
        retour.setFocusable(false);
        dev.setFocusable(false);
        about.setFocusable(false);
        logout.setFocusable(false);

        // making the borders and the background transparent for the retour JButton:
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            if (isAdmin) {
                new Admin();
            } else {
                new Cassiere();
            }

            dispose();
        });
        logout.addActionListener(e -> {
            new Login();
            dispose();
        });
        about.addActionListener(e -> {
            text = """
                 SuperMarketApp est une application de bureau Java qui vous aide à gérer votre entreprise de supermarché. 
                 Elle utilise Swing comme framework d’interface utilisateur graphique et prend en charge plusieurs rôles et fonctions. 
                 Avec SuperMarketApp, vous pouvez:
                                   
                 - Vous connecter en tant qu’administrateur ou caissier en utilisant votre nom d’utilisateur et votre mot de passe.
                 - Accéder à la page du tableau de bord en tant qu’administrateur pour gérer les stocks, ajouter ou supprimer des produits et gérer les utilisateurs.
                 - Accéder à la page du caissier en tant que caissier pour insérer des codes produits, traiter les paiements, imprimer des reçus et gérer les retours et échanges.
                
                 Il fonctionne sur n’importe quelle plateforme prenant en charge l’environnement d’exécution Java (JRE). 
                 Il dispose également d’un système de base de données sécurisé et fiable qui stocke toutes vos données localement. 
                 SuperMarketApp est la solution parfaite pour vos besoins de supermarché.""";
            textArea.setText(text);
            about.setBackground(Color.GREEN);
            dev.setBackground(Color.GRAY);
        });
        dev.addActionListener(e -> {
            text = "";
            textArea.setText(text);
            dev.setBackground(Color.GREEN);
            about.setBackground(Color.GRAY);
        });

        // setting the fonts + colors:
        //fonts:
        titre.setFont(new Font(null, Font.PLAIN, 50));
        dev.setFont(new Font(null, Font.PLAIN, 20));
        about.setFont(new Font(null, Font.PLAIN, 20));
        logout.setFont(new Font(null, Font.PLAIN, 20));
        textArea.setFont(new Font(null, Font.PLAIN, 20));

        // colors (Foreground + Background):
        // Foreground:
        titre.setForeground(Color.BLACK);

        // Background:
        retour.setBackground(Color.WHITE);
        logout.setBackground(Color.GRAY);
        about.setBackground(Color.GREEN);
        dev.setBackground(Color.GRAY);

        // setting the bounds of the components:
        retour.setBounds(
                40,
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
        about.setBounds(
                titre.getX(),
                titre.getY() + titre.getHeight() + 20,
                titre.getWidth() - 50,
                80
        );
        dev.setBounds(
                about.getX(),
                about.getY() + about.getHeight() + 20,
                titre.getWidth() - 50,
                80
        );
        logout.setBounds(
                xSize - 220,
                retour.getY(),
                180,
                60
        );
        panel.setBounds(
                dev.getX() + dev.getWidth() + 20,
                titre.getY(),
                730,
                (ySize - taskBarSize) - 180
        );

        // adding border to the JPanels and JScrollPanes:
        panel.setBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0 , Color.BLACK)
        );
        scrollPane.setBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0 , Color.BLACK)
        );

        // text init
        text = """
                 SuperMarketApp est une application de bureau Java qui vous aide à gérer votre entreprise de supermarché. 
                 Elle utilise Swing comme framework d’interface utilisateur graphique et prend en charge plusieurs rôles et fonctions. 
                 Avec SuperMarketApp, vous pouvez:
                                   
                 - Vous connecter en tant qu’administrateur ou caissier en utilisant votre nom d’utilisateur et votre mot de passe.
                 - Accéder à la page du tableau de bord en tant qu’administrateur pour gérer les stocks, ajouter ou supprimer des produits et gérer les utilisateurs.
                 - Accéder à la page du caissier en tant que caissier pour insérer des codes produits, traiter les paiements, imprimer des reçus et gérer les retours et échanges.
                
                 Il fonctionne sur n’importe quelle plateforme prenant en charge l’environnement d’exécution Java (JRE). 
                 Il dispose également d’un système de base de données sécurisé et fiable qui stocke toutes vos données localement. 
                 SuperMarketApp est la solution parfaite pour vos besoins de supermarché.""";
        textArea.setText(text);

        // textArea setup
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMaximumSize(new Dimension(panel.getWidth(), panel.getHeight()));

        // adding components to the JPanel 'panel'
        panel.add(scrollPane);

        // adding components to the JFrame:
        this.add(retour);
        this.add(titre);
        this.add(logout);
        this.add(about);
        this.add(dev);
        this.add(panel);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }
}
