package presentation;

import dao.GestionImpl;
import dao.IGestion;
import metier.entity.Produit;
import metier.entity.Utilisateur;
import presentation.tableModeles.TableModeleProduit;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AjoutProduit extends JFrame {
    // components
    // DefaultTableCellRenderer
    private final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
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
    private ImageIcon backIcon = new ImageIcon("src\\images\\back.png");
    private ImageIcon findIcon = new ImageIcon("src\\images\\find.png");
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

        // centering each table entry to center by changing the cell renderer of each one :
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // getting the list of Produit and updating the TableModeleProduit's data:
        me.chargerTable(gestion.getAllProduit());

        // adding tool tips
        rechercherTextField.setToolTipText("faire une recherche");

        // updating nomProduitTextField, categorieTextField, prixTextField JTextFields when codeProduitTextField is not empty
        new javax.swing.Timer(0, e -> {
            if (!codeProduitTextField.getText().equals("")) {
                try {
                    int code = Integer.parseInt(codeProduitTextField.getText());
                    Produit produit = gestion.getProduit(code);

                    if (produit != null) {
                        if (nomProduitTextField.getText().equals("")) {
                            nomProduitTextField.setText(produit.getNom());
                        }
                        if (categorieTextField.getText().equals("")) {
                            categorieTextField.setText(produit.getCategorie());
                        }

                        if (prixTextField.getText().equals("")) {
                            prixTextField.setText(String.valueOf(produit.getPrix()));
                        }
                    } else {
                        nomProduitTextField.setText("");
                        categorieTextField.setText("");
                        prixTextField.setText("");
                    }
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                }
            } else {
                nomProduitTextField.setText("");
                categorieTextField.setText("");
                prixTextField.setText("");
            }
        }).start();

        // adding the mouseListener to the JTable:
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow();

                    int id = (int) target.getValueAt(row, 0);

                    codeProduitTextField.setText(String.valueOf(id));

                    Produit produit = gestion.getProduit(id);

                    nomProduitTextField.setText(produit.getNom());
                    categorieTextField.setText(produit.getCategorie());
                    prixTextField.setText(String.valueOf(produit.getPrix()));
                }
            }
        });

        // adding the ActionListener to the JButtons:
        retour.addActionListener(e -> {
            new Admin();
            dispose();
        });
        rechercher.addActionListener(e -> {
            List<Produit> list = gestion.getProduitPMC(rechercherTextField.getText());

            me.chargerTable(list);
        });
        ajouter.addActionListener(e -> {
            if (
                    nomProduitTextField.getText().equals("")
                    || categorieTextField.getText().equals("")
                    || prixTextField.getText().equals("")
            ) {
                JOptionPane.showMessageDialog(
                        AjoutProduit.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    double price = Double.parseDouble(prixTextField.getText());

                    Produit produit_1 = new Produit(
                            nomProduitTextField.getText(),
                            categorieTextField.getText(),
                            price
                    );
                    Produit produit = gestion.getAllProduit().stream().filter(produit_1::equals).findFirst().orElse(produit_1);

                    if (produit.equals(produit_1)) {
                        gestion.ajouterProduit(produit);

                        JOptionPane.showMessageDialog(
                                AjoutProduit.this,
                                "produit à été ajouter!"
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                AjoutProduit.this,
                                "produit deja existant!"
                        );
                    }
                }  catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            AjoutProduit.this,
                            "erreur de saisie:\n" + e1.getMessage()
                    );
                }

                me.chargerTable(gestion.getAllProduit());
            }
        });
        update.addActionListener(e -> {
            if (
                    codeProduitTextField.getText().equals("")
                    || nomProduitTextField.getText().equals("")
                    || categorieTextField.getText().equals("")
                    || prixTextField.getText().equals("")
            ) {
                JOptionPane.showMessageDialog(
                        AjoutProduit.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int code = Integer.parseInt(codeProduitTextField.getText());
                    double price = Double.parseDouble(prixTextField.getText());
                    Produit produit = gestion.getProduit(code);

                    if (produit == null) {
                        JOptionPane.showMessageDialog(
                                AjoutProduit.this,
                                "impossible de faire le mis à jour ce produit!"
                        );
                    } else {
                        produit.setNom(nomProduitTextField.getText());
                        produit.setCategorie(String.valueOf(categorieTextField.getText()));
                        produit.setPrix(price);

                        JOptionPane.showMessageDialog(
                                AjoutProduit.this,
                                "produit à été modifier!"
                        );

                        gestion.modifierProduit(produit);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            AjoutProduit.this,
                            "erreur de saisie de l'id ou le prix!"
                    );
                }

                me.chargerTable(gestion.getAllProduit());
            }
        });
        supprimer.addActionListener(e -> {
            if (codeProduitTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(
                        AjoutProduit.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int code = Integer.parseInt(codeProduitTextField.getText());

                    if (gestion.getProduit(code) == null) {
                        JOptionPane.showMessageDialog(
                                AjoutProduit.this,
                                "impossible de supprimer ce produit!"
                        );
                    } else {
                        gestion.supprimerProduit(code);

                        JOptionPane.showMessageDialog(
                                AjoutProduit.this,
                                "produit à été supprimer!"
                        );
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            AjoutProduit.this,
                            "erreur de saisie de l'id!"
                    );
                }

                me.chargerTable(gestion.getAllProduit());
            }
        });
        annuler.addActionListener(e -> {
            codeProduitTextField.setText("");
            nomProduitTextField.setText("");
            categorieTextField.setText("");
            prixTextField.setText("");
        });

        // setting the fonts + colors:
        //fonts:
        titre.setFont(new Font(null, Font.PLAIN, 50));
        annuler.setFont(new Font(null, Font.PLAIN, 20));
        ajouter.setFont(new Font(null, Font.PLAIN, 20));
        supprimer.setFont(new Font(null, Font.PLAIN, 20));
        update.setFont(new Font(null, Font.PLAIN, 20));
        codeProduit.setFont(new Font(null, Font.PLAIN, 20));
        nomProduit.setFont(new Font(null, Font.PLAIN, 20));
        categorie.setFont(new Font(null, Font.PLAIN, 20));
        prix.setFont(new Font(null, Font.PLAIN, 20));
        prixTextField.setFont(new Font(null, Font.PLAIN, 15));
        categorieTextField.setFont(new Font(null, Font.PLAIN, 15));
        nomProduitTextField.setFont(new Font(null, Font.PLAIN, 15));
        codeProduitTextField.setFont(new Font(null, Font.PLAIN, 15));
        rechercherTextField.setFont(new Font(null, Font.PLAIN, 15));

        // colors (Foreground + Background):
        // Foreground:
        titre.setForeground(Color.BLACK);
        ajouter.setForeground(Color.WHITE);
        annuler.setForeground(Color.WHITE);
        update.setForeground(Color.WHITE);
        supprimer.setForeground(Color.WHITE);

        // Background:
        retour.setBackground(Color.WHITE);
        rechercher.setBackground(Color.WHITE);
        ajouter.setBackground(new Color(20, 204, 204));
        annuler.setBackground(new Color(20, 204, 204));
        update.setBackground(new Color(20, 204, 204));
        supprimer.setBackground(new Color(20, 204, 204));

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
                120,
                40
        );
        rechercher.setBounds(
                rechercherTextField.getX() + rechercherTextField.getWidth() + 20,
                rechercherTextField.getY(),
                40,
                40
        );
        codeProduit.setBounds(
                titre.getX(),
                titre.getY() + 80,
                200,
                40
        );
        codeProduitTextField.setBounds(
                codeProduit.getX(),
                codeProduit.getY() + 40,
                200,
                40
        );
        nomProduit.setBounds(
                codeProduitTextField.getX(),
                codeProduitTextField.getY() + 40,
                200,
                40
        );
        nomProduitTextField.setBounds(
                nomProduit.getX(),
                nomProduit.getY() + 40,
                200,
                40
        );
        categorie.setBounds(
                nomProduitTextField.getX(),
                nomProduitTextField.getY() + 40,
                200,
                40
        );
        categorieTextField.setBounds(
                categorie.getX(),
                categorie.getY() + 40,
                200,
                40
        );
        prix.setBounds(
                categorieTextField.getX(),
                categorieTextField.getY() + 40,
                200,
                40
        );
        prixTextField.setBounds(
                prix.getX(),
                prix.getY() + 40,
                200,
                40
        );
        ajouter.setBounds(
                prixTextField.getX(),
                prixTextField.getY() + 100,
                200,
                40
        );
        update.setBounds(
                prixTextField.getX() + ajouter.getWidth() + 10,
                prixTextField.getY() + 100,
                220,
                40
        );
        supprimer.setBounds(
                prixTextField.getX(),
                update.getY() + 50,
                220,
                40
        );
        annuler.setBounds(
                prixTextField.getX() + supprimer.getWidth() + 10,
                update.getY() + 50,
                200,
                40
        );

        // adding border to the JTextFields:
        rechercherTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        codeProduitTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        nomProduitTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        categorieTextField.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0 , Color.BLACK)
        );
        prixTextField.setBorder(
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
        this.add(codeProduit);
        this.add(codeProduitTextField);
        this.add(nomProduit);
        this.add(nomProduitTextField);
        this.add(categorie);
        this.add(categorieTextField);
        this.add(prix);
        this.add(prixTextField);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }
}
