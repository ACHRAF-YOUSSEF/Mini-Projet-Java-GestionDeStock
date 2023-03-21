package presentation;

import dao.GestionImpl;
import dao.IGestion;
import metier.entity.Produit;
import metier.entity.Utilisateur;
import presentation.tableModeles.TableModeleProduit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AjoutProduit extends JFrame {
    // components
    // JPanel
    private final JPanel tableJPanel = new JPanel(new GridLayout(1, 1));
    // JLabels
    private final JLabel titre = new JLabel("Ajout Produit");
    private final JLabel codeProduit = new JLabel("Code Produit");
    private final JLabel nomProduit = new JLabel("Nom Produit");
    private final JLabel categorie = new JLabel("Categorie");
    private final JLabel prix = new JLabel("Prix");
    // JTextFields
    private final JTextField rechercherTextField = new JTextField();
    private final JTextField codeProduitTextField = new JTextField();
    private final JTextField nomProduitTextField = new JTextField();
    private final JTextField categorieTextField = new JTextField();
    private final JTextField prixTextField = new JTextField();
    // ImageIcons
    private ImageIcon backIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\back.png");
    private ImageIcon findIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\find.png");
    // JButtons
    private final JButton retour = new JButton();
    private final JButton rechercher = new JButton();
    private final JButton ajouter = new JButton("créer un produit");
    private final JButton update = new JButton("mis à jour un produit");
    private final JButton supprimer = new JButton("supprimer un produit");
    private final JButton annuler = new JButton("annuler");
    // IGestion
    private final IGestion gestion = GestionImpl.getGestion();
    // TableModeleProduit
    private final TableModeleProduit me = new TableModeleProduit();
    // JTable
    private final JTable table = new JTable(me);
    // JScrollPane
    private final JScrollPane jsp = new JScrollPane(table);

    public AjoutProduit() {
        // titre:
        super("AjoutProduit");

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
        findIcon = Utils.resizeImageIcon(findIcon, 40, 40);

        // adding the ImageIcons to the JButtons:
        retour.setIcon(backIcon);
        rechercher.setIcon(findIcon);

        // setting focusable for the JButtons:
        retour.setFocusable(false);
        rechercher.setFocusable(false);
        annuler.setFocusable(false);
        ajouter.setFocusable(false);
        supprimer.setFocusable(false);
        update.setFocusable(false);

        // making the borders and the background transparent for the retour + rechercher JButtons:
        // retour JButton:
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // rechercher JButton:
        rechercher.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        rechercher.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // getting the list of Produit and updating the TableModeleProduit's data:
        me.chargerTable(gestion.getAllProduit());

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            new Admin();
            dispose();
        });
        rechercher.addActionListener(e -> {
            List<Produit> list = gestion.getProduitPMC(rechercherTextField.getText());

            me.chargerTable(list);
        });
        ajouter.addActionListener(e -> {});
        update.addActionListener(e -> {});
        supprimer.addActionListener(e -> {});
        annuler.addActionListener(e -> {
            codeProduitTextField.setText("");
            nomProduitTextField.setText("");
            categorieTextField.setText("");
            prixTextField.setText("");
        });

        // setting the fonts + colors:
        //fonts:
        titre.setFont(new Font(null, Font.PLAIN, 50));
        rechercherTextField.setFont(new Font(null, Font.PLAIN, 15));

        // colors (Foreground + Background):
        // Foreground:
        titre.setForeground(Color.BLACK);

        // Background:
        retour.setBackground(Color.WHITE);
        rechercher.setBackground(Color.WHITE);

        // setting the bounds of the retour JButton:
        retour.setBounds(
                40,
                80,
                100,
                60
        );
        titre.setBounds(100,
                150,
                400,
                60
        );
        tableJPanel.setBounds(
                xSize - 850,
                220,
                800,
                (ySize - taskBarSize) - 300
        );
        rechercherTextField.setBounds(
                tableJPanel.getX(),
                tableJPanel.getY() - 50,
                400,
                40
        );
        rechercher.setBounds(
                rechercherTextField.getX() + rechercherTextField.getWidth() + 20,
                rechercherTextField.getY(),
                40,
                40
        );

        // adding border to the JTextFields:
        rechercherTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );

        // adding the JScrollPane to the tableJPanel JPanel:
        tableJPanel.add(jsp);

        // adding components to the JFrame:
        this.add(retour);
        this.add(titre);
        this.add(rechercher);
        this.add(rechercherTextField);
        this.add(tableJPanel);
        this.add(ajouter);
        this.add(update);
        this.add(supprimer);
        this.add(annuler);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AjoutProduit();
    }
}
