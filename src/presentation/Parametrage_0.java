package presentation;

import javax.swing.*;
import java.awt.*;

public class Parametrage_0 extends JFrame {
    // components
    // JPanels
    // buttonsPanel = [about : 1 + dev : 2]
    private final JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 20, 20));
    // sidePanel = [titre : north + buttonsPanel : center]
    private final JPanel sidePanel = new JPanel(new BorderLayout(10, 10));
    // bodyPanel = [sidePanel : west + panel : center]
    private final JPanel bodyPanel = new JPanel(new BorderLayout(10, 10));
    // panelRetour = [retour : 1]
    private final JPanel panelRetour = new JPanel(new FlowLayout(FlowLayout.LEFT));
    // panelLogout = [logout : 1]
    private final JPanel panelLogout = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    // panel = [scrollPane : 1]
    private final JPanel panel = new JPanel(new GridLayout(1, 1));
    // JLabels
    private final JLabel titre = new JLabel("Parametrage");
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("src\\images\\back.png");
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

    public Parametrage_0(boolean isAdmin) {
        // titre:
        super("Parametrage");

        // changing the Background of the JFrame:
        this.getContentPane().setBackground(Color.WHITE);
        panelRetour.setBackground(Color.WHITE);
        panelLogout.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);
        bodyPanel.setBackground(Color.WHITE);
        sidePanel.setBackground(Color.WHITE);

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
            new Login_0();
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

        // adding border to the JPanels and JScrollPanes:
        panel.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        1,
                        0,
                        0 ,
                        Color.BLACK
                )
        );
        scrollPane.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        1,
                        0,
                        0 ,
                        Color.BLACK
                )
        );
        sidePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        50,
                        200,
                        100,
                        20
                )
        );
        buttonsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        200,
                        20
                )
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

        // adding components to the panelRetour JPanel:
        panelRetour.add(retour);

        // adding components to the panelLogout JPanel:
        panelLogout.add(logout);

        // adding components to the buttonsPanel JPanel:
        buttonsPanel.add(about);
        buttonsPanel.add(dev);

        // adding components to the sidePanel JPanel:
        sidePanel.add(titre, BorderLayout.NORTH);
        sidePanel.add(buttonsPanel);

        // adding components to the bodyPanel JPanel:
        bodyPanel.add(scrollPane);
        bodyPanel.add(sidePanel, BorderLayout.WEST);

        // adding components to the JFrame:
        this.add(panelRetour, BorderLayout.NORTH);
        this.add(panelLogout, BorderLayout.EAST);
        this.add(bodyPanel);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Parametrage_0(true);
    }
}
