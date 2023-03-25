package presentation;

import dao.GestionImpl;
import dao.IGestion;
import metier.entity.Inventaire;
import presentation.tableModeles.TableModeleCaissiereTable1;
import presentation.tableModeles.TableModeleCaissiereTable2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Cassiere extends JFrame {
    // components
    // JPanel
    private final JPanel prixTotaleJPanel = new JPanel(new GridLayout(1, 1));
    private final JPanel argentARetournerJPanel = new JPanel(new GridLayout(1, 1));
    private final JPanel tableJPanel1 = new JPanel(new GridLayout(1, 1));
    private final JPanel tableJPanel2 = new JPanel(new GridLayout(1, 1));
    // JLabels
    private final JLabel leftArrowJLabel = new JLabel(">>>>>");
    private final JLabel titreJLabel = new JLabel("Cassiere");
    private final JLabel codeProduitJLabel = new JLabel("Code Produit:");
    private final JLabel stockDisponibleJLabel = new JLabel("Stock Disponible:");
    private final JLabel nomProduitJLabel = new JLabel("Nom Produit:");
    private final JLabel categorieJLabel = new JLabel("Categorie:");
    private final JLabel prixJLabel = new JLabel("Prix:");
    private final JLabel quantiteJLabel = new JLabel("Qte:");
    private final JLabel argentTotaleJLabel = new JLabel("Argent Totale:");
    private final JLabel prixTotaleJLabel1 = new JLabel("Prix Totale:");
    private final JLabel prixTotaleJLabel2 = new JLabel("0.0");
    private final JLabel argentARetournerJLabel1 = new JLabel("Argent A Retourner:");
    private final JLabel argentARetournerJLabel2 = new JLabel("0.0");
    // ImageIcons
    private ImageIcon settingsIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\settings.png");
    private ImageIcon findIcon = new ImageIcon("D:\\semestre 2\\java avancée\\ex_cours\\chapitre4\\GestionDeStock\\src\\images\\find.png");
    // JTextFields
    private final JTextField rechercherTextField = new JTextField();
    private final JTextField codeProduitJTextField = new JTextField();
    private final JTextField stockDisponibleJTextField = new JTextField();
    private final JTextField nomProduitJTextField = new JTextField();
    private final JTextField categorieJTextFieldl = new JTextField();
    private final JTextField prixJTextField = new JTextField();
    private final JTextField quantiteJTextField = new JTextField();
    private final JTextField argentTotaleJTextField = new JTextField();
    // JButtons
    private final JButton paramtres = new JButton("parametres");
    private final JButton rechercher = new JButton();
    private final JButton insertCommande = new JButton("Insérer La Commande");
    private final JButton annulerCommande = new JButton("Annuler La Commande");
    private final JButton annuler = new JButton("Annuler");
    private final JButton print = new JButton("Imprimer");
    // IGestion
    private final IGestion gestion = GestionImpl.getGestion();
    // TableModeleCaissiereTable1
    private final TableModeleCaissiereTable1 me1 = new TableModeleCaissiereTable1();
    private final TableModeleCaissiereTable2 me2 = new TableModeleCaissiereTable2();
    // JTables
    private final JTable table1 = new JTable(me1);
    private final JTable table2 = new JTable(me2);
    // JScrollPanes
    private final JScrollPane jsp1 = new JScrollPane(table1);
    private final JScrollPane jsp2 = new JScrollPane(table2);

    public Cassiere() {
        // titreJLabel:
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
        findIcon = Utils.resizeImageIcon(findIcon, 40, 40);
        leftArrowJLabel.setFont(new Font(null, Font.PLAIN, 40));
        codeProduitJLabel.setFont(new Font(null, Font.PLAIN, 20));
        stockDisponibleJLabel.setFont(new Font(null, Font.PLAIN, 20));
        nomProduitJLabel.setFont(new Font(null, Font.PLAIN, 20));
        categorieJLabel.setFont(new Font(null, Font.PLAIN, 20));
        prixJLabel.setFont(new Font(null, Font.PLAIN, 20));
        quantiteJLabel.setFont(new Font(null, Font.PLAIN, 20));
        rechercherTextField.setFont(new Font(null, Font.PLAIN, 15));
        codeProduitJTextField.setFont(new Font(null, Font.PLAIN, 15));
        stockDisponibleJTextField.setFont(new Font(null, Font.PLAIN, 15));
        nomProduitJTextField.setFont(new Font(null, Font.PLAIN, 15));
        categorieJTextFieldl.setFont(new Font(null, Font.PLAIN, 15));
        prixJTextField.setFont(new Font(null, Font.PLAIN, 15));
        quantiteJTextField.setFont(new Font(null, Font.PLAIN, 15));

        // adding the ImageIcons to the JButtons:
        paramtres.setIcon(settingsIcon);
        rechercher.setIcon(findIcon);

        // setting focusable for the JButtons
        paramtres.setFocusable(false);
        rechercher.setFocusable(false);

        //  making the borders and the background transparent for the paramtres JButton:
        paramtres.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        paramtres.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // rechercher JButton:
        rechercher.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        rechercher.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // setting the vertical and horizontal text positions for the paramtres JButton:
        paramtres.setVerticalTextPosition(SwingConstants.BOTTOM);
        paramtres.setHorizontalTextPosition(SwingConstants.CENTER);

        // getting the list of Inventaire and updating the TableModeleCaissiereTable1's and TableModeleCaissiereTable2's data:
        me1.chargerTable(gestion.getAllInventaire());
        me2.chargerTable(gestion.getAllInventaire());

        // setting the fonts + colors:
        //fonts
        titreJLabel.setFont(new Font(null, Font.PLAIN, 50));
        paramtres.setFont(new Font(null, Font.PLAIN, 20));

        // colors (Foreground + Background)
        // Foreground
        paramtres.setForeground(Color.WHITE);
        rechercherTextField.setForeground(Color.WHITE);
        titreJLabel.setForeground(Color.WHITE);
        leftArrowJLabel.setForeground(Color.WHITE);
        codeProduitJLabel.setForeground(Color.WHITE);
        stockDisponibleJLabel.setForeground(Color.WHITE);
        nomProduitJLabel.setForeground(Color.WHITE);
        categorieJLabel.setForeground(Color.WHITE);
        prixJLabel.setForeground(Color.WHITE);
        quantiteJLabel.setForeground(Color.WHITE);

        // Background
        paramtres.setBackground(new Color(80, 80, 80));
        rechercher.setBackground(new Color(80, 80, 80));
        rechercherTextField.setBackground(new Color(80, 80, 80));
        argentTotaleJTextField.setBackground(new Color(80, 80, 80));

        // adding the ActionListener to the JButtons:
        paramtres.addActionListener(e -> {
            new Parametrage(false);
            dispose();
        });
        rechercher.addActionListener(e -> {
            List<Inventaire> list = gestion.getInventairePMC(rechercherTextField.getText());

            me1.chargerTable(list);
        });

        // setting the bounds of the components:
        paramtres.setBounds(
                xSize - 160,
                40,
                120,
                80
        );
        titreJLabel.setBounds(
                20,
                20,
                400,
                60
        );
        codeProduitJLabel.setBounds(
                titreJLabel.getX() + 10,
                titreJLabel.getY() + titreJLabel.getHeight() + 20,
                150,
                40
        );
        codeProduitJTextField.setBounds(
                codeProduitJLabel.getX() + codeProduitJLabel.getWidth(),
                codeProduitJLabel.getY(),
                200,
                codeProduitJLabel.getHeight()
        );
        prixJLabel.setBounds(
                codeProduitJTextField.getX() + codeProduitJTextField.getWidth() + 10,
                codeProduitJTextField.getY(),
                40,
                codeProduitJTextField.getHeight()
        );
        prixJTextField.setBounds(
                prixJLabel.getX() + prixJLabel.getWidth() + 10,
                prixJLabel.getY(),
                150,
                prixJLabel.getHeight()
        );
        stockDisponibleJLabel.setBounds(
                codeProduitJLabel.getX() - 10,
                codeProduitJLabel.getY() + codeProduitJLabel.getHeight() + 20,
                160,
                40
        );
        stockDisponibleJTextField.setBounds(
                stockDisponibleJLabel.getX() + stockDisponibleJLabel.getWidth(),
                stockDisponibleJLabel.getY(),
                200,
                stockDisponibleJLabel.getHeight()
        );
        nomProduitJLabel.setBounds(
                stockDisponibleJLabel.getX() + 20,
                stockDisponibleJLabel.getY() + stockDisponibleJLabel.getHeight() + 20,
                160,
                40
        );
        nomProduitJTextField.setBounds(
                nomProduitJLabel.getX() + nomProduitJLabel.getWidth() - 20,
                nomProduitJLabel.getY(),
                200,
                nomProduitJLabel.getHeight()
        );
        categorieJLabel.setBounds(
                nomProduitJLabel.getX(),
                nomProduitJLabel.getY() + nomProduitJLabel.getHeight() + 20,
                160,
                40
        );
        categorieJTextFieldl.setBounds(
                categorieJLabel.getX() + categorieJLabel.getWidth() - 20,
                categorieJLabel.getY(),
                200,
                categorieJLabel.getHeight()
        );
        quantiteJLabel.setBounds(
                stockDisponibleJTextField.getX() + stockDisponibleJTextField.getWidth() + 10,
                stockDisponibleJTextField.getY(),
                40,
                stockDisponibleJTextField.getHeight()
        );
        quantiteJTextField.setBounds(
                quantiteJLabel.getX() + quantiteJLabel.getWidth() + 10,
                quantiteJLabel.getY(),
                150,
                quantiteJLabel.getHeight()
        );
        tableJPanel2.setBounds(
                xSize - 700,
                350,
                650,
                (ySize - taskBarSize) - 500
        );
        tableJPanel1.setBounds(
                20,
                350,
                600,
                (ySize - taskBarSize) - 500
        );
        leftArrowJLabel.setBounds(
                tableJPanel1.getX() + tableJPanel1.getWidth() + 50,
                tableJPanel1.getY() + (tableJPanel1.getHeight() / 2),
                200,
                40
        );
        rechercher.setBounds(
                tableJPanel1.getX() + tableJPanel1.getWidth() - 40,
                tableJPanel1.getY() - 50,
                40,
                40
        );
        rechercherTextField.setBounds(
                rechercher.getX() - 20 - 160,
                rechercher.getY(),
                160,
                40
        );

        // adding border to the JTextFields:
        rechercherTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        codeProduitJTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        stockDisponibleJTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        nomProduitJTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        categorieJTextFieldl.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        prixJTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        quantiteJTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        argentTotaleJTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );

        // adding the JScrollPanes to the tableJPanel1 and 2 JPanels:
        tableJPanel1.add(jsp1);
        tableJPanel2.add(jsp2);

        // adding components to the JFrame:
        this.add(paramtres);
        this.add(leftArrowJLabel);
        this.add(titreJLabel);
        this.add(rechercher);
        this.add(rechercherTextField);
        this.add(tableJPanel1);
        this.add(tableJPanel2);
        this.add(codeProduitJLabel);
        this.add(stockDisponibleJLabel);
        this.add(nomProduitJLabel);
        this.add(categorieJLabel);
        this.add(prixJLabel);
        this.add(quantiteJLabel);
        this.add(codeProduitJTextField);
        this.add(stockDisponibleJTextField);
        this.add(nomProduitJTextField);
        this.add(categorieJTextFieldl);
        this.add(prixJTextField);
        this.add(quantiteJTextField);
        this.add(insertCommande);
        this.add(annulerCommande);
        this.add(annuler);
        this.add(argentARetournerJPanel);
        this.add(prixTotaleJPanel);
        this.add(argentTotaleJLabel);
        this.add(argentTotaleJTextField);
        this.add(print);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Cassiere();
    }
}
