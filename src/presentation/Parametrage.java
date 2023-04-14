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

    public Parametrage(boolean isAdmin, String name) {
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
                new Caissiere(name);
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
            text = """
                    Titre : Guide de développement de "Gestion De Stock" d'Achraf Youssef
                                        
                    Introduction :
                    "Gestion De Stock" est une application Java de bureau développée par Achraf Youssef.
                    Cette application est conçue pour aider les utilisateurs à gérer leur stock et leur inventaire facilement.
                    Dans ce guide, nous vous guiderons à travers les étapes pour commencer avec "Gestion De Stock", y compris le téléchargement et la configuration de l'application, ainsi que quelques instructions d'utilisation de base.
                                        
                    Téléchargement de "Gestion De Stock" :
                                        
                    Le code source de "Gestion De Stock" peut être trouvé sur le profil GitHub d'Achraf Youssef à l'adresse https://github.com/ACHRAF-YOUSSEF/Mini-Projet-Java-GestionDeStock.
                    Vous pouvez télécharger le code source en cliquant sur le bouton vert "Code" et en sélectionnant "Download ZIP".
                    Extrayez le fichier ZIP téléchargé dans un répertoire de votre choix.
                     
                    Configuration de "Gestion De Stock" :
                                        
                    Ouvrez le répertoire du projet dans votre environnement de développement Java préféré, tel que Eclipse ou IntelliJ IDEA.
                    Si votre environnement de développement ne reconnaît pas le projet comme un projet Java, vous devrez peut-être créer un nouveau projet Java et importer le code source.
                    Ajoutez les bibliothèques suivantes au chemin de classe de votre projet :
                     
                    - lombok
                    - jcalendar-1.4
                    - itextpdf-5.1.0
                    - mysql-connector-java-5.1.47
                    - icepdf-viewer-7.0.2
                    - icepdf-core-7.0.2
                     
                    Une fois le projet configuré et les bibliothèques ajoutées au chemin de classe, exécutez la classe principale, "GestionDeStock.java".
                    Utilisation de "Gestion De Stock" :
                                        
                    "Gestion De Stock" vous permet d'ajouter et de supprimer des articles de votre inventaire, ainsi que de visualiser et de mettre à jour les articles existants.
                    Vous pouvez exporter les données de votre inventaire vers un fichier PDF pour des fins de sauvegarde.
                    Cliquez simplement sur le bouton "Imprimer" pour l'imprimer.
                     
                    Conclusion :
                     
                    "Gestion De Stock" est un outil de gestion d'inventaire puissant et convivial développé par Achraf Youssef.
                    Que vous soyez propriétaire d'une petite entreprise ou que vous cherchiez simplement un moyen de suivre votre inventaire personnel, "Gestion De Stock" est un excellent choix.
                    Pour plus d'informations, veuillez vous référer au référentiel GitHub à l'adresse https://github.com/ACHRAF-YOUSSEF/Mini-Projet-Java-GestionDeStock.
                    Pour voir les autres projets d'Achraf Youssef, visitez son profil GitHub à l'adresse https://github.com/ACHRAF-YOUSSEF
                    """;
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
                20,
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

        // adding border to the panel JPanel, scrollPane JScrollPane and textArea JTextArea:
        panel.setBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0 , Color.BLACK)
        );
        scrollPane.setBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0 , Color.BLACK)
        );
        textArea.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
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
